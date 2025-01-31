package com.example.event_manager.dao;

import com.example.event_manager.configuration.SessionFactoryUtil;
import com.example.event_manager.dto.CreateReviewDto;
import com.example.event_manager.entity.Review;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDao {
    public static Review getReviewById(long id) {
        Review review;

        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            review = session.get(Review.class, id);
            transaction.commit();
        }

        return review;
    }

    public static void createReview(CreateReviewDto review) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Review reviewToAdd = new Review(
                    review.getRating(),
                    review.getComment(),
                    review.getReviewTime(),
                    ReservationDao.getReservationByEventIdAndUserId(review.getEventId(), review.getUserId())
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
}