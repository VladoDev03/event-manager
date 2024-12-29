package com.example.event_manager.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class EventOnLocation extends BaseEntity{
    @ManyToOne
    private Event event;
    @ManyToOne
    private Location location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event getEvent() {
        return event;
    }

    public Location getLocation() {
        return location;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
