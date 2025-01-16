package com.example.event_manager.dao;

import com.example.event_manager.configuration.SessionFactoryUtil;
import com.example.event_manager.entity.EventOnLocation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventOnLocationDao {
    public static void saveEventOnLocation(EventOnLocation eventOnLocation) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(eventOnLocation);
            transaction.commit();
        }
    }

    public static void createEventOnLocation(EventOnLocation eventOnLocation) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(eventOnLocation);
            transaction.commit();
        }
    }

    public static void updateEventOnLocation(EventOnLocation eventOnLocation) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(eventOnLocation);
            transaction.commit();
        }
    }

    public static void deleteEventOnLocation(EventOnLocation eventOnLocation) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(eventOnLocation);
            transaction.commit();
        }
    }

    public static EventOnLocation getEventOnLocationById(long id) {
        EventOnLocation eventOnLocation;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            eventOnLocation = session.get(EventOnLocation.class, id);
            transaction.commit();
        }

        return eventOnLocation;
    }

}
