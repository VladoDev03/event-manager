package com.example.event_manager.dto;

public class CreateMediaDto {
    private final String url;
    private final String publicId;
    private final long eventId;

    public CreateMediaDto(String url, String publicId, long eventId) {
        this.url = url;
        this.publicId = publicId;
        this.eventId = eventId;
    }

    public String getUrl() {
        return url;
    }

    public String getPublicId() {
        return publicId;
    }

    public long getEventId() {
        return eventId;
    }
}
