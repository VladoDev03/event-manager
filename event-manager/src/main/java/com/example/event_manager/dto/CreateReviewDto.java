package com.example.event_manager.dto;

import com.example.event_manager.entity.Rating;

import java.time.LocalDateTime;

public class CreateReviewDto {
    private Rating rating;
    private String comment;
    private LocalDateTime reviewTime;
    private long eventId;
    private long userId;

    public CreateReviewDto(Rating rating, String comment, LocalDateTime reviewTime, long eventId, long userId) {
        this.rating = rating;
        this.comment = comment;
        this.reviewTime = reviewTime;
        this.eventId = eventId;
        this.userId = userId;
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

    public long getEventId() {
        return eventId;
    }

    public long getUserId() {
        return userId;
    }
}
