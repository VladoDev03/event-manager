package com.example.event_manager.dto;

public class CreateMediaDto {
    private final String url;
    private final String publicId;

    public CreateMediaDto(String url, String publicId) {
        this.url = url;
        this.publicId = publicId;
    }

    public String getUrl() {
        return url;
    }

    public String getPublicId() {
        return publicId;
    }
}
