package com.example.event_manager.controller;

import com.example.event_manager.dto.CreateReviewDto;
import com.example.event_manager.dto.UploadReviewDto;
import com.example.event_manager.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
@CrossOrigin(origins = "*")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping()
    public ResponseEntity<?> uploadReview(@RequestBody UploadReviewDto review) {
        CreateReviewDto result =  reviewService.createReview(review);
        return ResponseEntity.ok(result);
    }
}
