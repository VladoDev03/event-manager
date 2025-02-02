package com.example.event_manager.service;

import com.example.event_manager.dao.EventDao;
import com.example.event_manager.dao.ReservationDao;
import com.example.event_manager.dao.ReviewDao;
import com.example.event_manager.dao.UserDao;
import com.example.event_manager.dto.CreateReviewDto;
import com.example.event_manager.dto.ReviewDataDto;
import com.example.event_manager.dto.UploadReviewDto;
import com.example.event_manager.entity.Reservation;
import com.example.event_manager.entity.Review;
import com.example.event_manager.exception.EntitiesNotConnectedException;
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

        Reservation reservation = null;
        try {
            reservation = ReservationDao.getReservationByEventIdAndUserId(createReviewDto.getEventId(), createReviewDto.getUserId());
        } catch (EntitiesNotConnectedException e) {
            throw new RuntimeException(e);
        }
        ReviewDao.createReview(createReviewDto, reservation);

        return createReviewDto;
    }

    public void deleteReview(long reviewId) {
        Review review = null;

        try {
            review = ReviewDao.getReviewById(reviewId);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }

        ReviewDao.deleteReview(review);
    }

    public List<ReviewDataDto> getReviewsByEventId(long eventId) {
        List<Review> reviews = null;
        reviews = ReviewDao.getReviewsByEventId(eventId);

        return reviews.stream()
                .map(review -> new ReviewDataDto(review.getRating(), review.getComment(), review.getReservation().getEvent().getId(), review.getReservation().getGuest().getId()))
                .collect(Collectors.toList());
    }
}