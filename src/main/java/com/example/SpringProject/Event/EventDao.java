package com.example.SpringProject.Event;

import com.example.SpringProject.configuration.SessionFactoryUtil;
import com.mysql.cj.protocol.x.Notice;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Repository
public class EventDao {
    public static void createEvent(Event event) {

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            System.out.println("Saving event with date: " + event.getUpdateDate());
            session.save(event);
            transaction.commit();
            Event fetchedEvent = session.get(Event.class, event.getId());
            System.out.println("Fetched date: " + fetchedEvent.getUpdateDate());
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
                    .createQuery("SELECT e FROM Event e", Event.class)
                    .getResultList();
            transaction.commit();
        }
        return events;
    }

    public static List<Event> getEventsByNameAndLocation(String title, String location){
        List<Event> events;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            events = session.createQuery("SELECT e FROM Event e WHERE " +
                    "(:title IS NULL OR LOWER(e.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
                    "(:location IS NULL OR e.location = :location)", Event.class)
                    .setParameter("title",title)
                    .setParameter("location", location)
                    .getResultList();
            transaction.commit();
        }
        return events;
    }

    public static List<Event> getEventsByName(String title) {
        List<Event> events;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            events = session.createQuery("SELECT e FROM Event e WHERE (:title IS NULL OR LOWER(e.title) LIKE LOWER(CONCAT('%', :title, '%')))",
                    Event.class).setParameter("title",title).getResultList();
            transaction.commit();
        }
        return events;
    }


    public static List<Event> getEventsByLocation(String location){
        List<Event> events;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            events = session.createQuery("SELECT e FROM Event e WHERE (:location IS NULL OR LOWER(e.location) LIKE LOWER(CONCAT('%', :location, '%')))",Event.class)
                    .setParameter("location",location)
                    .getResultList();
            transaction.commit();
        }
        return events;
    }

    public static List<Event> getEventsByCreatorUsername(String username){
        List<Event> events;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            events = session.createQuery("SELECT e FROM Event e WHERE e.creator.username = :username",Event.class)
                    .setParameter("username",username)
                    .getResultList();
            transaction.commit();
        }
        return events;
    }
}
