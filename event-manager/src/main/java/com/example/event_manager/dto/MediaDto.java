package com.example.event_manager.dto;

import com.example.event_manager.entity.Event;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

public class MediaDto {
    private String url;
    private String publicId;

    public MediaDto(String url, String publicId) {
        this.url = url;
        this.publicId = publicId;
    }

    public String getPublicId() {
        return publicId;
    }

    public String getUrl() {
        return url;
    }
}