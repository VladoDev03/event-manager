package com.example.event_manager.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "guest")
    private Set<Reservation> reservations;
    @ManyToMany
    private Set<Event> wishlist;

    @OneToMany (mappedBy = "creator")
    private Set<Event> events;

    public User(String username, String email, String password, String firstName, String lastName, LocalDateTime creationDate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creationDate = creationDate;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
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

    public void setEmail(String email) {
        this.email = email;
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

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Event> getWishlist() {
        return wishlist;
    }

    public void setWishlist(Set<Event> wishlist) {
        this.wishlist = wishlist;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
