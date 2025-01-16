package com.example.event_manager.dao;

import com.example.event_manager.configuration.SessionFactoryUtil;
import com.example.event_manager.entity.Event;
import com.example.event_manager.entity.EventOnLocation;
import com.example.event_manager.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public class EventDao {
    public static void createEvent(Event event) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(event);
            transaction.commit();
        }
    }

    public static Event getEventById(long id) {
        Event event;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            event = session.get(Event.class, id);
            transaction.commit();
        }
        return event;
    }

    public static void updateEvent(Event event) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(event);
            transaction.commit();
        }
    }

    public static void deleteEvent(Event event) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(event);
            transaction.commit();
        }
    }

    public static List<Event> getEvents() {
        List<Event> events;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            events = session
                    .createQuery("select e from Event e", Event.class)
                    .getResultList();
            transaction.commit();
        }
        return events;
    }

    public static Set<EventOnLocation> getEventEventOnLocations(long id) {
        Event event;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            event = session
                    .createQuery("select e from Event e join fetch e.eventOnLocations where e.id = :id", Event.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return event.getEventOnLocations();
    }

    public static LocalDateTime getEventStartTime(long eventId) {
        LocalDateTime start;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            start = session
                    .createQuery("select min(el.startTime) from EventOnLocation el where el.event.id = :eventId", LocalDateTime.class)
                    .setParameter("eventId", eventId)
                    .getSingleResult();
        }
        return start;

    }

    public static LocalDateTime getEventEndTime(long eventId) {
        LocalDateTime end;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            end = session
                    .createQuery("select max(el.endTime) from com.example.event_manager.entity.EventOnLocation el where el.event.id = :eventId", LocalDateTime.class)
                    .setParameter("eventId", eventId)
                    .getSingleResult();
        }
        return end;

    }

    public static Set<Reservation> getEventReservations(long id) {
        Event event;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            event = session
                    .createQuery("select e from Event e join fetch e.reservations where e.id = :id", Event.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return event.getReservations();
    }

    public static boolean isEventFull(long id) {
        Event event;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            event = session
                    .createQuery("select e from Event e left join fetch e.reservations where e.id = :id", Event.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return event.getCapacity() <= event.getReservations().size();
    }
}

