package com.example.event_manager.dao;

import com.example.event_manager.configuration.SessionFactoryUtil;
import com.example.event_manager.entity.Location;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class LocationDao {
    public static void saveLocation(Location location) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(location);
            transaction.commit();
        }
    }

    public static void createLocation(Location location) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(location);
            transaction.commit();
        }
    }

    public static void updateLocation(Location location) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(location);
            transaction.commit();
        }
    }

    public static void deleteLocation(Location location) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(location);
            transaction.commit();
        }
    }

    public static Location getLocationById(long id) {
        Location location;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            location = session.get(Location.class, id);
            transaction.commit();
        }
        return location;
    }

}
