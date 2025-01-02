package com.example.SpringProject.Media;

import com.example.SpringProject.BaseEntity.BaseEntity;
import com.example.SpringProject.Event.Event;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Media extends BaseEntity {
    private String url;
    @ManyToOne
    private Event event;

    public Media() {
    }

    public Media(long id, String url, Event event) {
        super(id);
        this.url = url;
        this.event = event;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
