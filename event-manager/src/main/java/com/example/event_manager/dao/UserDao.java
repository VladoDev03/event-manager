package com.example.event_manager.dao;

import com.example.event_manager.configuration.SessionFactoryUtil;
import com.example.event_manager.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    public static void saveUser(User guest) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(guest);
            transaction.commit();
        }
    }

    public static void createUser(User guest) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(guest);
            transaction.commit();
        }
    }

    public static void updateUser(User guest) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(guest);
            transaction.commit();
        }
    }

    public static void deleteUser(User guest) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(guest);
            transaction.commit();
        }
    }

    public static List<User> getUsers() {
        List<User> guests;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            guests = session.createQuery("select g from com.example.event_manager.entity.User g", User.class)
                    .getResultList();
            transaction.commit();
        }
        return guests;
    }

    public static User getUserById(long id) {
        User guest;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            guest = session.get(User.class, id);
            transaction.commit();
        }
        return guest;
    }
}
