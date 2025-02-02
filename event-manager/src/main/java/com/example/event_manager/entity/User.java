package com.example.event_manager.entity;

import com.example.event_manager.validator.InvalidNames;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "`User`")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity {
    @InvalidNames(message = "username and name are not a valid names.")
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @InvalidNames(message = "name is not a valid name.")
    @Column(nullable = false)
    private String firstName;
    @InvalidNames(message = "name is not a valid name.")
    private String lastName;
    @PastOrPresent(message = "Creation date must be in the past or present.")
    @Column(nullable = false)
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "guest")
    private Set<Reservation> reservations;

    @ManyToMany
    @JoinTable(name = "guestsHaveEventInWishlist")
    private Set<Event> wishlist;

    @OneToMany (mappedBy = "creator")
    private Set<Event> events;

    public User(String username, String password, String firstName, String lastName, LocalDateTime creationDate) {
        this.username = username;
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
