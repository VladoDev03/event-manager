package com.example.event_manager.dao;

import com.example.event_manager.configuration.SessionFactoryUtil;
import com.example.event_manager.dto.CreateReviewDto;
import com.example.event_manager.entity.Review;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDao {
    public static void createReview(CreateReviewDto review) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Review reviewToAdd = new Review(review.getRating(), review.getComment(), review.getReviewTime());
            Transaction transaction = session.beginTransaction();
            session.save(reviewToAdd);
            transaction.commit();
        }
    }
}
