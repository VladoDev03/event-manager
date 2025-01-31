package com.example.SpringProject.EventOnLocation;

import com.example.SpringProject.BaseEntity.BaseEntity;
import com.example.SpringProject.Event.Event;
import com.example.SpringProject.Location.Location;
import javax.persistence.*;


import java.time.LocalDateTime;

@Entity
public class EventOnLocation extends BaseEntity {
    @ManyToOne
    private Event event;
    @ManyToOne
    private Location location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public EventOnLocation() {
    }

    public EventOnLocation(long id, Event event, Location location, LocalDateTime startTime, LocalDateTime endTime) {
        super(id);
        this.event = event;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
