package com.example.event_manager.exception;

public class NotCreatorException extends Exception {
    private long userId;
    private long eventId;

    public NotCreatorException(long userId, long eventId) {
        super("User " + userId + " is not creator of " + eventId + "!");
        this.userId = userId;
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return "AlreadyRelatedEntitiesException{" +
                "entityId1=" + userId +
                ", entityId2=" + eventId +
                '}';
    }
}
