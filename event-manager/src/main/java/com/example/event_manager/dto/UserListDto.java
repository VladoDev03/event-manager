package com.example.event_manager.dto;

import java.time.LocalDateTime;

public class UserListDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime creationDate;

    public UserListDto() {
    }

    public UserListDto(String username, String password, String firstName, String lastName, LocalDateTime creationDate) {
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}