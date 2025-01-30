package com.example.event_manager.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation extends BaseEntity {
    private LocalDateTime purchaseDate;
    @ManyToOne (fetch = FetchType.LAZY)
    private Event event;
    @ManyToOne (fetch = FetchType.LAZY)
    private User guest;
    private String firstName;
    private String lastName;
    private String email;
    @OneToOne (mappedBy = "reservation")
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

//    @Transient
//    public String getQrCode() {
//        String qrString = "Reservation id:" + getId() + " by guest id: " + guest.getId() + " for " + getFirstName() + " " + getLastName() + ", event id: " + event.getId();
//        String topText = event.getTitle();
//        String bottomText = firstName + " " + lastName;
//        return QRCodeGenerator.getBase64QRCode(qrString, topText, bottomText);
//    }

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
