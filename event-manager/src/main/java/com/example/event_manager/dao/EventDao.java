package com.example.event_manager.dao;

import com.example.event_manager.configuration.SessionFactoryUtil;
import com.example.event_manager.entity.Event;
import com.example.event_manager.entity.Location;
import com.example.event_manager.entity.Reservation;
import com.example.event_manager.dto.DisplayEventDto;
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
                    .createQuery("select e from Event e join fetch e.location left join fetch e.media left join fetch e.reservations left join fetch e.guestsHaveEventInWishlist", Event.class)
                    .getResultList();
            transaction.commit();
        }
        return events;
    }

    public static Location getEventLocation(long id) {
        Event event;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            event = session
                    .createQuery("select e from Event e join fetch e.location where e.id = :id", Event.class)
                    .setParameter("id", id)
                    .getSingleResult();
            transaction.commit();
        }
        return event.getLocation();
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

    //    public DisplayEventDto(long id, String title, String description, EventCategory category, String locationName, BigDecimal price, LocalDateTime startTime, LocalDateTime endTime) {
    public static List<DisplayEventDto> getAllDisplayEventDto() {
        List<DisplayEventDto> events;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            events = session
                    .createQuery("SELECT new com.example.event_manager.dto.DisplayEventDto(e.id, e.title, e.description, e.category, e.location.name, e.price, e.startTime, e.endTime) FROM Event e", DisplayEventDto.class)
                    .getResultList();
            transaction.commit();
        }
        return events;
    }
}

