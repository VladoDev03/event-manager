package com.example.event_manager.service;

import com.example.event_manager.dao.EventDao;
import com.example.event_manager.dao.ReservationDao;
import com.example.event_manager.dto.CreateReservationDto;
import com.example.event_manager.dto.ReservationDto;
import com.example.event_manager.dto.ReservationTicketDto;
import com.example.event_manager.entity.Reservation;
import com.google.zxing.WriterException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    public ReservationDto createReservation(CreateReservationDto createReservationDto) throws IOException, WriterException {
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
                ReservationDao.getReservationQrCode(reservation.getId())
        );
        return reservationDto;
    }

    public void deleteReservation(long id) {
        Reservation reservation = ReservationDao.getReservationById(id);
        if(reservation != null) {
            ReservationDao.deleteReservation(reservation);
        }
    }

    public List<Reservation> getEventReservations(long eventId) {
        return ReservationDao.getReservations()
                .stream()
                .filter(reservation -> ReservationDao.getReservationEvent(reservation.getId()).getId() == eventId)
                .collect(Collectors.toList());
    }

    public List<ReservationTicketDto> getGuestReservations(long guestId) {
        return ReservationDao.getReservations()
                .stream()
                .filter(reservation -> ReservationDao.getReservationEvent(reservation.getId()).getId() == guestId)
                .map(reservation -> ReservationDao.getReservationTicket(reservation.getId()))
                .collect(Collectors.toList());
    }

    public List<ReservationTicketDto> getFutureGuestReservations(long guestId) {
        return ReservationDao.getFutureGuestReservations(guestId)
                .stream()
                .map(reservation -> ReservationDao.getReservationTicket(reservation.getId()))
                .collect(Collectors.toList());
    }

    public List<ReservationTicketDto> getPreviousGuestReservations(long guestId) {
        return ReservationDao.getPreviousGuestReservations(guestId)
                .stream()
                .map(reservation -> ReservationDao.getReservationTicket(reservation.getId()))
                .collect(Collectors.toList());
    }
}
