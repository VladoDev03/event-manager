package com.example.event_manager.exception;

public class ExistingUserException extends Exception {
    private String username;

    public ExistingUserException(String username) {
        super("User with username \"" + username + "\" already exists!");
        this.username = username;
    }

    @Override
    public String toString() {
        return "ExistingUserException{" +
                "username='" + username + '\'' +
                '}';
    }
}