package com.example.event_manager.exception;

public class MinGreaterThanMaxException extends RuntimeException{
    public MinGreaterThanMaxException(String errorMessage) {
        super(errorMessage);
    }

}
