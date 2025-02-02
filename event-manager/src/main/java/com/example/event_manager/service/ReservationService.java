package com.example.event_manager.service;

import com.example.event_manager.configuration.QRCodeGenerator;
import com.example.event_manager.dao.EventDao;
import com.example.event_manager.dao.ReservationDao;
import com.example.event_manager.dao.UserDao;
import com.example.event_manager.dto.CreateReservationDto;
import com.example.event_manager.dto.ReservationDto;
import com.example.event_manager.dto.ReservationTicketDto;
import com.example.event_manager.entity.Event;
import com.example.event_manager.entity.Reservation;
import com.example.event_manager.entity.User;
import com.example.event_manager.exception.EntityNotFoundException;
import com.example.event_manager.exception.EventFinishedException;
import com.google.zxing.WriterException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    public Reservation getReservationById(long id) {
        try {
            return ReservationDao.getReservationById(id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public ReservationDto createReservation(CreateReservationDto createReservationDto) throws IOException, WriterException, EntityNotFoundException, EventFinishedException {
        Event event;

        try {
            event = EventDao.getEventById(createReservationDto.getEventId());
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            UserDao.getUserById(createReservationDto.getGuestId());
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (LocalDateTime.now().isAfter(event.getEndTime())) {
            throw new EventFinishedException(createReservationDto.getEventId(), event.getTitle());
        }

        if (EventDao.isEventFull(createReservationDto.getEventId())){
            throw new IllegalStateException("Event is full");
        }

        Reservation reservation = ReservationDao.saveReservationDto(createReservationDto);
        ReservationDto reservationDto = new ReservationDto(
                ReservationDao.getReservationEvent(reservation.getId()).getId(),
                ReservationDao.getReservationGuest(reservation.getId()).getId(),
                reservation.getFirstName(),
                reservation.getLastName(),
                reservation.getEmail(),
                this.getReservationQrCode(reservation.getId())
        );
        return reservationDto;
    }

    public String getReservationQrCode(long id) {
        Reservation reservation = this.getReservationById(id);
        Event event = null;
        try {
            event = ReservationDao.getReservationEvent(id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
        User guest = null;

        try {
            guest = ReservationDao.getReservationGuest(id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }

        String qrString = "Reservation id:" + id + " by guest id: " + guest.getId() + " for " + reservation.getFirstName() + " " + reservation.getLastName() + ", event id: " + event.getId();
        String topText = event.getTitle();
        String bottomText = reservation.getFirstName() + " " + reservation.getLastName();
        return QRCodeGenerator.getBase64QRCode(qrString, topText, bottomText);
    }

    public void deleteReservation(long id) {
        Reservation reservation;
        try {
            reservation = ReservationDao.getReservationById(id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }

        ReservationDao.deleteReservation(reservation);
    }

    public ReservationTicketDto getReservationTicket(long id) {
        Reservation reservation = this.getReservationById(id);

        //getting Event
        Event event = null;
        try {
            event = ReservationDao.getReservationEvent(id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }

        //getting Guest
        User guest = null;
        try {
            guest = ReservationDao.getReservationGuest(id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }

        ReservationTicketDto reservationTicketDto = new ReservationTicketDto();

        reservationTicketDto.setEventId(event.getId());
        reservationTicketDto.setEventTitle(event.getTitle());
        reservationTicketDto.setEventStartTime(event.getStartTime());
        reservationTicketDto.setEventLocation(event.getLocation());
        reservationTicketDto.setEventPrice(event.getPrice());

        reservationTicketDto.setReservationId(reservation.getId());
        reservationTicketDto.setReservationContactNames(reservation.getFirstName() + " " + reservation.getLastName());
        reservationTicketDto.setReservationEmail(reservation.getEmail());
        reservationTicketDto.setReservationQrCode(this.getReservationQrCode(id));

        reservationTicketDto.setGuestId(guest.getId());

        return reservationTicketDto;
    }

    public List<ReservationTicketDto> getFutureGuestReservations(long guestId) {
        return ReservationDao.getFutureGuestReservations(guestId)
                .stream()
                .map(reservation -> this.getReservationTicket(reservation.getId()))
                .collect(Collectors.toList());
    }

    public List<ReservationTicketDto> getPreviousGuestReservations(long guestId) {
        return ReservationDao.getPreviousGuestReservations(guestId)
                .stream()
                .map(reservation -> this.getReservationTicket(reservation.getId()))
                .collect(Collectors.toList());
    }
}
