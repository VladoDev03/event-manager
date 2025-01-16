package com.example.event_manager.dao;

import com.example.event_manager.configuration.SessionFactoryUtil;
import com.example.event_manager.entity.Guest;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GuestDao {
    public static void saveGuest(Guest guest) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(guest);
            transaction.commit();
        }
    }

    public static void createGuest(Guest guest) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(guest);
            transaction.commit();
        }
    }

    public static void updateGuest(Guest guest) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(guest);
            transaction.commit();
        }
    }

    public static void deleteGuest(Guest guest) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(guest);
            transaction.commit();
        }
    }

    public static List<Guest> getGuests() {
        List<Guest> guests;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            guests = session.createQuery("select g from com.example.event_manager.entity.Guest g", Guest.class)
                    .getResultList();
            transaction.commit();
        }
        return guests;
    }

    public static Guest getGuestById(long id) {
        Guest guest;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            guest = session.get(Guest.class, id);
            transaction.commit();
        }
        return guest;
    }
}
