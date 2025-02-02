package com.example.event_manager.dto;

public class WishlistRequest {
    private long eventId;
    private long userId;

    public WishlistRequest(long eventId, long userId) {
        this.eventId = eventId;
        this.userId = userId;
    }

    public long getEventId() {
        return eventId;
    }

    public long getUserId() {
        return userId;
    }
}
