package com.example.event_manager.dao;

import com.example.event_manager.configuration.SessionFactoryUtil;
import com.example.event_manager.dto.CreateReviewDto;
import com.example.event_manager.entity.Reservation;
import com.example.event_manager.entity.Review;
import com.example.event_manager.exception.EntitiesNotConnectedException;
import com.example.event_manager.exception.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDao {
    public static Review getReviewById(long id) throws EntityNotFoundException {
        Review review;

        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            review = session.get(Review.class, id);
            transaction.commit();
        }

        if(review == null) {
            throw new EntityNotFoundException(id);
        }
        return review;
    }

    public static void createReview(CreateReviewDto review, Reservation reservation) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Review reviewToAdd = new Review(
                        review.getRating(),
                        review.getComment(),
                        review.getReviewTime(),
                        reservation
            );

            Transaction transaction = session.beginTransaction();
            session.save(reviewToAdd);
            transaction.commit();
        }
    }

    public static void deleteReview(Review review) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(review);
            transaction.commit();
        }
    }

    public static List<Review> getReviewsByEventId(long eventId) {
        List<Review> reviews;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Review> query = session.createQuery("select r from Review r join fetch r.reservation res join fetch res.event e where e.id = :eventId", Review.class);
            query.setParameter("eventId", eventId);
            reviews = query.list();
        }
        return reviews;
    }
}