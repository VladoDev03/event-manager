package org.example.entity;

import javax.persistence.*;

@Entity
public class Media extends BaseEntity{
    private String url;
    @ManyToOne
    private Event event;

    public String getUrl() {
        return url;
    }

    public Event getEvent() {
        return event;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
