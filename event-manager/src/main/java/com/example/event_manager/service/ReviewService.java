package com.example.event_manager.service;

import com.example.event_manager.dao.EventDao;
import com.example.event_manager.dao.ReviewDao;
import com.example.event_manager.dao.UserDao;
import com.example.event_manager.dto.CreateReviewDto;
import com.example.event_manager.dto.ReviewDataDto;
import com.example.event_manager.dto.UploadReviewDto;
import com.example.event_manager.entity.Review;
import com.example.event_manager.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    public CreateReviewDto createReview(UploadReviewDto reviewDto) {
        try {
            EventDao.getEventById(reviewDto.getEventId());
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            UserDao.getUserById(reviewDto.getUserId());
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }

        CreateReviewDto createReviewDto = new CreateReviewDto(
                reviewDto.getRating(),
                reviewDto.getComment(),
                LocalDateTime.now(),
                reviewDto.getEventId(),
                reviewDto.getUserId()
        );

        ReviewDao.createReview(createReviewDto);

        return createReviewDto;
    }

    public void deleteReview(long reviewId) {
        Review review = ReviewDao.getReviewById(reviewId);
        ReviewDao.deleteReview(review);
    }

    public List<ReviewDataDto> getReviewsByEventId(long eventId) {
        List<Review> reviews = ReviewDao.getReviewsByEventId(eventId);
        return reviews.stream()
                .map(review -> new ReviewDataDto(review.getRating(), review.getComment(), review.getReservation().getEvent().getId(), review.getReservation().getGuest().getId()))
                .collect(Collectors.toList());
    }
}
