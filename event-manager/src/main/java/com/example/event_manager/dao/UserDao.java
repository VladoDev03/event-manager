package com.example.event_manager.dao;

import com.example.event_manager.configuration.SessionFactoryUtil;
import com.example.event_manager.entity.Reservation;
import com.example.event_manager.entity.User;
import com.example.event_manager.exception.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    public static void saveUser(User user) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    public static void createUser(User user) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    public static void updateUser(User user) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        }
    }

    public static void deleteUser(User user) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        }
    }

    public static List<User> getUsers() {
        List<User> users;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            users = session.createQuery("select g from com.example.event_manager.entity.User g", User.class)
                    .getResultList();
            transaction.commit();
        }
        return users;
    }

    public static User getUserById(long id) throws EntityNotFoundException {
        User user;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
        }

        if (user == null) {
            throw new EntityNotFoundException(id);
        }

        return user;
    }

    public static User getUserByEmail(String email) {
        User user;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            user = session
                    .createQuery("select u from User u where u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
            transaction.commit();
            return user;

        } catch (NoResultException nre) {
            return null;
        }

    }

    public static User getUserByUsername(String username) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User WHERE username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
