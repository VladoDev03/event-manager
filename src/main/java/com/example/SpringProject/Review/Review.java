package com.example.SpringProject.Review;

import com.example.SpringProject.BaseEntity.BaseEntity;
import com.example.SpringProject.Reservation.Reservation;
import javax.persistence.*;


import java.time.LocalDateTime;

@Entity
public class Review extends BaseEntity {
    @Enumerated
    private Rating rating;
    private String comment;
    private LocalDateTime reviewTime;
    @OneToOne
    private Reservation reservation;

    public Review() {
    }

    public Review(long id, Rating rating, String comment, LocalDateTime reviewTime, Reservation reservation) {
        super(id);
        this.rating = rating;
        this.comment = comment;
        this.reviewTime = reviewTime;
        this.reservation = reservation;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(LocalDateTime reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
