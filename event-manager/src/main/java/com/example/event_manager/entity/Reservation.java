package com.example.event_manager.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class Reservation extends BaseEntity {
    private LocalDateTime purchaseDate;
    @ManyToOne
    private Event event;
    @ManyToOne
    private Guest guest;
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

    public Review getReview() {
        return review;
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

    public void setReview(Review review) {
        this.review = review;
    }
}
