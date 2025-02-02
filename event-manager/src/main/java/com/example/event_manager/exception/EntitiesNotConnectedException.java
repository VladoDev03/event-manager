package com.example.event_manager.exception;

public class EntitiesNotConnectedException extends Exception{
    private final long entity1Id;
    private final long entity2Id;

    public EntitiesNotConnectedException(long entity1Id, long entity2Id) {
        super("Entity " + entity1Id + " and entity " + entity2Id + " are not connected.");
        this.entity1Id = entity1Id;
        this.entity2Id = entity2Id;
    }

    @Override
    public String toString() {
        return "EntityNotFoundException{" +
                "entityId=" + entity1Id +
                "entityId=" + entity2Id +
                '}';
    }
}
