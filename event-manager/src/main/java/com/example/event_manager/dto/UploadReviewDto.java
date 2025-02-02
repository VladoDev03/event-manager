package com.example.event_manager.dto;

import com.example.event_manager.entity.Rating;

public class UploadReviewDto {
    private final Rating rating;
    private final String comment;
    private final long eventId;
    private final long userId;

    public UploadReviewDto(Rating rating, String comment, long eventId, long userId) {
        this.rating = rating;
        this.comment = comment;
        this.eventId = eventId;
        this.userId = userId;
    }

    public Rating getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public long getEventId() {
        return eventId;
    }

    public long getUserId() {
        return userId;
    }
}