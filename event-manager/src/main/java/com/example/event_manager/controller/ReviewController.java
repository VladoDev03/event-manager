package com.example.event_manager.controller;

import com.example.event_manager.dto.CreateReviewDto;
import com.example.event_manager.dto.ReviewDataDto;
import com.example.event_manager.dto.UploadReviewDto;
import com.example.event_manager.exception.EntityNotFoundException;
import com.example.event_manager.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/review")
@CrossOrigin(origins = "")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping()
    public ResponseEntity<?> uploadReview(@RequestBody UploadReviewDto review) {
        try {
            CreateReviewDto result = reviewService.createReview(review);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable String reviewId) {
        reviewService.deleteReview(Long.parseLong(reviewId));
        return ResponseEntity.ok(reviewId);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<ReviewDataDto>> getReviewsByEventId(@PathVariable long eventId) {
        try {
            List<ReviewDataDto> reviews = reviewService.getReviewsByEventId(eventId);
            return ResponseEntity.ok(reviews);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}