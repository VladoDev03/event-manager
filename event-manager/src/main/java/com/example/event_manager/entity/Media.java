package com.example.event_manager.entity;

import javax.persistence.*;

@Entity
public class Media extends BaseEntity {
    private String url;
    private String publicId;
    @ManyToOne (fetch = FetchType.LAZY)
    private Event event;

    public Media() {}

    public Media(String url, String publicId) {
        this.url = url;
        this.publicId = publicId;
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
