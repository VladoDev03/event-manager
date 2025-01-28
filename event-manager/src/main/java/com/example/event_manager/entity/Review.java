package com.example.event_manager.entity;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class Review extends BaseEntity {
    @Enumerated
    private Rating rating;
    private String comment;
    private LocalDateTime reviewTime;
    @OneToOne
    private Reservation reservation;

    public Review() {}

    public Review(Rating rating, String comment, LocalDateTime reviewTime) {
        this.rating = rating;
        this.comment = comment;
        this.reviewTime = reviewTime;
    }

    public Rating getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getReviewDate() {
        return reviewTime;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setReviewDate(LocalDateTime reviewTime) {
        this.reviewTime = reviewTime;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
