package com.example.event_manager.entity;

import com.example.event_manager.validator.InvalidNames;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

@Entity
public class Reservation extends BaseEntity {
    @PastOrPresent(message = "Purchase date must be in the past or present.")
    @Column(nullable = false)
    private LocalDateTime purchaseDate;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Event event;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User guest;
    @Column(nullable = false)
    @InvalidNames(message = "name is not a valid name.")
    private String firstName;
    @Column(nullable = false)
    @InvalidNames(message = "name is not a valid name.")
    private String lastName;
    @Email(message = "Email must be in email format.")
    @Column(nullable = false)
    private String email;
    @OneToOne (mappedBy = "reservation")
    @JoinColumn (unique = true)
    private Review review;

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public Event getEvent() {
        return event;
    }

    public User getGuest() {
        return guest;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Review getReview() {
        return review;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setGuest(User guest) {
        this.guest = guest;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setReview(Review review) {
        this.review = review;
    }

}
