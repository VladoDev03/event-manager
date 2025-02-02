package com.example.event_manager.dao;

import com.example.event_manager.configuration.QRCodeGenerator;
import com.example.event_manager.configuration.SessionFactoryUtil;
import com.example.event_manager.dto.CreateReservationDto;
import com.example.event_manager.dto.ReservationTicketDto;
import com.example.event_manager.entity.*;
import com.example.event_manager.exception.EntitiesNotConnectedException;
import com.example.event_manager.exception.EntityNotFoundException;
import jakarta.validation.Valid;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReservationDao {
    public static void createReservation(@Valid Reservation reservation) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(reservation);
            transaction.commit();
        }
    }

    public static void updateReservation(@Valid Reservation reservation) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(reservation);
            transaction.commit();
        }
    }

    public static void deleteReservation(@Valid Reservation reservation) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(reservation);
            transaction.commit();
        }
    }

    public static Reservation getReservationById(long id) throws EntityNotFoundException {
        Reservation reservation;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            reservation = session.get(Reservation.class, id);
            transaction.commit();
        }

        if(reservation == null) {
            throw new EntityNotFoundException(id);
        }

        return reservation;
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

    public static Event getReservationEvent(long id) throws EntityNotFoundException {
        Reservation reservation;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            reservation = session
                    .createQuery("select r from Reservation r join fetch r.event where r.id = :id", Reservation.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }

        if(reservation == null) {
            throw new EntityNotFoundException(id);
        }

        return reservation.getEvent();
    }

    public static User getReservationGuest(long id) throws EntityNotFoundException {
        Reservation reservation;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            reservation = session
                    .createQuery("select r from Reservation r join fetch r.guest where r.id = :id", Reservation.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }

        if(reservation == null) {
            throw new EntityNotFoundException(id);
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
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static Reservation getReservationByEventIdAndUserId(long eventId, long userId) throws EntitiesNotConnectedException {
        Reservation reservation;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            reservation = session
                    .createQuery(
                            "select r from Reservation r" +
                                    " join fetch r.guest as g" +
                                    " join fetch r.event as e" +
                                    " where r.guest.id = :userId" +
                                    " and r.event.id = :eventId",
                            Reservation.class)
                    .setParameter("userId", userId)
                    .setParameter("eventId", eventId)
                    .getSingleResult();
            transaction.commit();
        }

        if(reservation == null) {
            throw new EntitiesNotConnectedException(eventId, userId);
        }

        return reservation;
    }

}
