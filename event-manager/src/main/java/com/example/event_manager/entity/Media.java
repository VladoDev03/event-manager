package com.example.event_manager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Media extends BaseEntity {
    @NotBlank(message="Url must not be blank!")
    @Column(unique = true, nullable=false)
    private String url;

    @NotBlank(message="Public id must not be blank!")
    @Column(unique = true, nullable=false, name = "public_id")
    private String publicId;

    @ManyToOne (fetch = FetchType.LAZY)
    private Event event;

    public Media() {}

    public Media(String url, String publicId) {
        this.url = url;
        this.publicId = publicId;
    }

    public Media(String url, String publicId, Event event) {
        this.url = url;
        this.publicId = publicId;
        this.event = event;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}