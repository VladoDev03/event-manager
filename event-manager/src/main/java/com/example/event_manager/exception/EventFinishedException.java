package com.example.event_manager.exception;

public class EventFinishedException extends Exception {
    long eventId;
    private String eventName;

    public EventFinishedException(long eventId, String eventName) {
        super("Event {" + eventId + "} \"" + eventName + "\" has already finished!");
        this.eventId = eventId;
        this.eventName = eventName;
    }

    @Override
    public String toString() {
        return "EventFinishedException{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                '}';
    }
}