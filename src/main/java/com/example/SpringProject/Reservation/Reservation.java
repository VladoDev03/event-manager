package com.example.SpringProject.Reservation;

import com.example.SpringProject.BaseEntity.BaseEntity;
import com.example.SpringProject.Event.Event;
import com.example.SpringProject.Guest.Guest;
import com.example.SpringProject.Review.Review;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Reservation extends BaseEntity {
    private LocalDateTime purchaseDate;
    @ManyToOne
    private Event event;
    @ManyToOne
    private Guest guest;
    @OneToOne(mappedBy = "reservation")
    private Review review;

    public Reservation() {
    }

    public Reservation(long id, LocalDateTime purchaseDate, Event event, Guest guest, Review review) {
        super(id);
        this.purchaseDate = purchaseDate;
        this.event = event;
        this.guest = guest;
        this.review = review;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
