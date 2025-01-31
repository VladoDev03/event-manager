package com.example.SpringProject.User;

import com.example.SpringProject.BaseEntity.BaseEntity;
import javax.persistence.*;


import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
public class User extends BaseEntity {
    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private LocalDateTime creationDate;

    public User() {
    }

    public User(String username, LocalDateTime creationDate, String secondName, String firstName, String password) {
        this.username = username;
        this.creationDate = creationDate;
        this.secondName = secondName;
        this.firstName = firstName;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
