package com.example.event_manager.dto;

import com.example.event_manager.entity.Rating;

public class UploadReviewDto {
    private final Rating rating;
    private final String comment;

    public UploadReviewDto(Rating rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public Rating getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
