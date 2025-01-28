package com.example.event_manager.dto;

import com.example.event_manager.entity.Rating;

import java.time.LocalDateTime;

public class CreateReviewDto {
    private Rating rating;
    private String comment;
    private LocalDateTime reviewTime;

    public CreateReviewDto(Rating rating, String comment, LocalDateTime reviewTime) {
        this.rating = rating;
        this.comment = comment;
        this.reviewTime = reviewTime;
    }

    public String getComment() {
        return comment;
    }

    public Rating getRating() {
        return rating;
    }

    public LocalDateTime getReviewTime() {
        return reviewTime;
    }
}
