package com.example.event_manager.dao;

import com.example.event_manager.configuration.SessionFactoryUtil;
import com.example.event_manager.dto.CreateReservationDto;
import com.example.event_manager.entity.Event;
import com.example.event_manager.entity.Guest;
import com.example.event_manager.entity.Reservation;
import com.example.event_manager.entity.Review;
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
            reservations = session.createQuery("select r from com.example.event_manager.entity.Reservation r", Reservation.class)
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
            reservation = session.get(Reservation.class, id);
            transaction.commit();
        }
        return reservation.getEvent();
    }

    public static Guest getReservationGuest(long id) {
        Reservation reservation;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            reservation = session.get(Reservation.class, id);
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

    public static Reservation saveReservationDto(CreateReservationDto createReservationDto) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Reservation reservation = new Reservation();
            reservation.setEvent(EventDao.getEventById(createReservationDto.getEventId()));
            reservation.setGuest(GuestDao.getGuestById(createReservationDto.getGuestId()));
            reservation.setFirstName(createReservationDto.getFirstName());
            reservation.setLastName(createReservationDto.getLastName());
            reservation.setEmail(createReservationDto.getEmail());
            reservation.setPurchaseDate(LocalDateTime.now());
            session.save(reservation);
            transaction.commit();

            return reservation;
        }

    }

}
