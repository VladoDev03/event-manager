package com.example.event_manager.dto;

import java.time.LocalDateTime;

public class CreateUserDto {
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final LocalDateTime creationDate;

    public CreateUserDto(String username, String password, String firstName, String lastName, LocalDateTime creationDate) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creationDate = creationDate;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

}
