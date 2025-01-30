package com.example.event_manager.dao;

import com.example.event_manager.configuration.SessionFactoryUtil;
import com.example.event_manager.entity.Event;
import com.example.event_manager.entity.EventCategory;
import com.example.event_manager.entity.Location;
import com.example.event_manager.entity.Reservation;
import com.example.event_manager.dto.DisplayEventDto;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

//    public static Location getEventLocation(long id) {
//        Event event;
//        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//            event = session
//                    .createQuery("select e from Event e join fetch e.location where e.id = :id", Event.class)
//                    .setParameter("id", id)
//                    .getSingleResult();
//            transaction.commit();
//        }
//        return event.getLocation();
//    }

    public static String getEventLocation(long id) {
        Event event;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            event = session
                    .createQuery("select e from Event e where e.id = :id", Event.class)
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

    public static List<DisplayEventDto> getFilteredEventsByCategoryStartTimeEndTimePrice(EventCategory eventCategory, LocalDateTime startDateTime, LocalDateTime endDateTime, BigDecimal minPrice, BigDecimal maxPrice) {
        List<Object[]> results;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> cr = cb.createQuery(Object[].class);

            Root<Event> root = cr.from(Event.class);
            Join<Event, Location> eventLocationJoin = root.join("location");

            cr.multiselect(
                    root.get("id"),
                    root.get("title"),
                    root.get("description"),
                    root.get("category"),
                    eventLocationJoin.get("name"),
                    root.get("price"),
                    root.get("startTime"),
                    root.get("endTime")
            );

            Predicate wherePredicate = cb.conjunction();

            if(eventCategory != null) {
                wherePredicate = cb.and(wherePredicate, cb.equal(root.get("category"), eventCategory));
            }

            if(startDateTime != null) {
                wherePredicate = cb.and(wherePredicate, cb.greaterThanOrEqualTo(root.get("endTime"), startDateTime));
            }

            if(endDateTime != null) {
                wherePredicate = cb.and(wherePredicate, cb.lessThanOrEqualTo(root.get("startTime"), endDateTime));
            }

            if(minPrice != null) {
                wherePredicate = cb.and(wherePredicate, cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }

            if(maxPrice != null) {
                wherePredicate = cb.and(wherePredicate, cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            cr.where(wherePredicate);
            cr.orderBy(cb.asc(root.get("startTime")));

            results = session.createQuery(cr).getResultList();
        }

        return results.stream()
                .map(result -> new DisplayEventDto(
                        (long) result[0],
                        (String) result[1],
                        (String) result[2],
                        (EventCategory) result[3],
                        (String) result[4],
                        (BigDecimal) result[5],
                        (LocalDateTime) result[6],
                        (LocalDateTime) result[7]))
                .collect(Collectors.toList());
    }
}