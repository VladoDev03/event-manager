package com.example.event_manager.exception;

public class EntityNotFoundException extends Exception {
    private final long entityId;

    public EntityNotFoundException(long entityId) {
        super("Entity " + entityId + " is not found.");
        this.entityId = entityId;
    }

    @Override
    public String toString() {
        return "EntityNotFoundException{" +
                "entityId=" + entityId +
                '}';
    }
}