package com.example.event_manager.controller;

import com.example.event_manager.dto.UploadReviewDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
@CrossOrigin(origins = "*")
public class ReviewController {
    @PostMapping()
    public ResponseEntity<?> uploadReview(@RequestBody UploadReviewDto review) {
        return ResponseEntity.ok(review);
    }
}
