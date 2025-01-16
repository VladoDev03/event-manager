package com.example.event_manager.entity;

import com.example.event_manager.configuration.QRCodeGenerator;
import com.example.event_manager.dao.EventDao;
import com.example.event_manager.dao.GuestDao;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Entity
public class Reservation extends BaseEntity {
    private LocalDateTime purchaseDate;
    @ManyToOne
    private Event event;
    @ManyToOne
    private Guest guest;
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

    public Guest getGuest() {
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

    @Transient
    public String getQrCode() {
        String qrString = "Reservation id:" + getId() + " by guest id: " + guest.getId() + " for " + getFirstName() + " " + getLastName() + ", event id: " + event.getId();
        String topText = event.getTitle();
        String bottomText = firstName + " " + lastName;
        return QRCodeGenerator.getBase64QRCode(qrString, topText, bottomText);
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setGuest(Guest guest) {
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
