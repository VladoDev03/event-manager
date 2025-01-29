package com.example.event_manager.dao;

import com.example.event_manager.configuration.SessionFactoryUtil;
import com.example.event_manager.entity.Event;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public static List<Event> getAllEvents() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Event", Event.class).list();
        }
    }
}

