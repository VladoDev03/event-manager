package com.example.event_manager.dao;

import com.example.event_manager.configuration.QRCodeGenerator;
import com.example.event_manager.configuration.SessionFactoryUtil;
import com.example.event_manager.dto.CreateReservationDto;
import com.example.event_manager.dto.ReservationTicketDto;
import com.example.event_manager.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReservationDao {
    public static void saveReservation(Reservation reservation) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(reservation);
            transaction.commit();
        }
    }

    public static void createReservation(Reservation reservation) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(reservation);
            transaction.commit();
        }
    }

    public static void updateReservation(Reservation reservation) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(reservation);
            transaction.commit();
        }
    }

    public static void deleteReservation(Reservation reservation) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(reservation);
            transaction.commit();
        }
    }

    public static List<Reservation> getReservations() {
        List<Reservation> reservations;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            reservations = session.createQuery("select r from Reservation r join fetch r.event join fetch r.guest", Reservation.class)
                    .getResultList();
            transaction.commit();
        }
        return reservations;
    }

    public static List<Reservation> getFutureGuestReservations(long guestId) {
        List<Reservation> reservations;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            reservations = session.createQuery("select r from Reservation r join fetch r.event join fetch r.guest where r.guest.id = :guestId and r.event.startTime > now()", Reservation.class)
                    .setParameter("guestId", guestId)
                    .getResultList();
            transaction.commit();
        }
        return reservations;
    }

    public static List<Reservation> getPreviousGuestReservations(long guestId) {
        List<Reservation> reservations;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            reservations = session.createQuery("select r from Reservation r join fetch r.event join fetch r.guest where r.guest.id = :guestId and r.event.startTime <= now()", Reservation.class)
                    .setParameter("guestId", guestId)
                    .getResultList();
            transaction.commit();
        }
        return reservations;
    }

    public static Reservation getReservationById(long id) {
        Reservation reservation;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            reservation = session.get(Reservation.class, id);
            transaction.commit();
        }
        return reservation;
    }

    public static Event getReservationEvent(long id) {
        Reservation reservation;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            reservation = session
                    .createQuery("select r from Reservation r join fetch r.event where r.id = :id", Reservation.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return reservation.getEvent();
    }

    public static User getReservationGuest(long id) {
        Reservation reservation;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            reservation = session
                    .createQuery("select r from Reservation r join fetch r.guest where r.id = :id", Reservation.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return reservation.getGuest();
    }

    public static String getReservationNames(long id) {
        Reservation reservation;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            reservation = session.get(Reservation.class, id);
            transaction.commit();
        }
        return reservation.getFirstName() + " " + reservation.getLastName();
    }

    public static LocalDateTime getReservationPurchaseDate(long id) {
        Reservation reservation;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            reservation = session.get(Reservation.class, id);
            transaction.commit();
        }
        return reservation.getPurchaseDate();
    }

    public static Review getReservationReview(long id) {
        Reservation reservation;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            reservation = session.get(Reservation.class, id);
            transaction.commit();
        }
        return reservation.getReview();
    }

    public static String getReservationQrCode(long id) {
        Reservation reservation = getReservationById(id);
        Event event = getReservationEvent(id);
        User guest = getReservationGuest(id);
        String qrString = "Reservation id:" + id + " by guest id: " + guest.getId() + " for " + reservation.getFirstName() + " " + reservation.getLastName() + ", event id: " + event.getId();
        String topText = event.getTitle();
        String bottomText = reservation.getFirstName() + " " + reservation.getLastName();
        return QRCodeGenerator.getBase64QRCode(qrString, topText, bottomText);
    }

    public static Reservation saveReservationDto(CreateReservationDto createReservationDto) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Reservation reservation = new Reservation();
            reservation.setEvent(EventDao.getEventById(createReservationDto.getEventId()));
            reservation.setGuest(UserDao.getUserById(createReservationDto.getGuestId()));
            reservation.setFirstName(createReservationDto.getFirstName());
            reservation.setLastName(createReservationDto.getLastName());
            reservation.setEmail(createReservationDto.getEmail());
            reservation.setPurchaseDate(LocalDateTime.now());
            session.save(reservation);
            transaction.commit();

            return reservation;
        }

    }

    public static ReservationTicketDto getReservationTicket(long id) {
        Reservation reservation = ReservationDao.getReservationById(id);

        //getting Event
        Event event = ReservationDao.getReservationEvent(id);

        //getting Guest
        User guest = ReservationDao.getReservationGuest(id);

        ReservationTicketDto reservationTicketDto = new ReservationTicketDto();

        reservationTicketDto.setEventId(event.getId());
        reservationTicketDto.setEventTitle(event.getTitle());
        reservationTicketDto.setEventStartTime(event.getStartTime());
        reservationTicketDto.setEventLocation(EventDao.getEventLocation(event.getId()));
        reservationTicketDto.setEventPrice(event.getPrice());

        reservationTicketDto.setReservationId(reservation.getId());
        reservationTicketDto.setReservationContactNames(reservation.getFirstName() + " " + reservation.getLastName());
        reservationTicketDto.setReservationEmail(reservation.getEmail());
        reservationTicketDto.setReservationQrCode(getReservationQrCode(id));

        reservationTicketDto.setGuestId(guest.getId());

        return reservationTicketDto;
    }

}