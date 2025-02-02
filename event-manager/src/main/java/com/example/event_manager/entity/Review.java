package com.example.event_manager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
public class Review extends BaseEntity {
    @Enumerated
    @Column(nullable=false, name = "rating")
    private Rating rating;

    @NotBlank(message="Comment must not be blank!")
    @Size(max=100, message = "Comment must not be longer than 100 characters!")
    @Column(nullable=false)
    private String comment;

    @PastOrPresent(message = "Review time cannot be in the future!")
    @Column(name = "review_time")
    private LocalDateTime reviewTime;

    @OneToOne
    @JoinColumn(unique = true)
    private Reservation reservation;

    public Review() {}

    public Review(Rating rating, String comment, LocalDateTime reviewTime, Reservation reservation) {
        this(rating, comment, reviewTime);
        this.reservation = reservation;
    }

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