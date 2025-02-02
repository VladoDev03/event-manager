package com.example.event_manager.entity;

import com.example.event_manager.validator.InvalidNames;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Event extends BaseEntity {
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    @Column(nullable = false)
    @InvalidNames(message = "Title is not a valid name")
    private String title;

    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private EventCategory category;

    @Size(min = 2, max = 100, message = "Location must be between 2 and 100 characters")
    @Column(nullable = false)
    @InvalidNames(message = "Location and address are not valid names")
    private String location;

    @DecimalMin(value = "0.0", message = "Price for area must be greater than or equal to 0")
    @Column(nullable = false)
    private BigDecimal price;

    @Min(value = 1, message = "Capacity must be greater than or equal to 1")
    @Column(nullable = false)
    private int capacity;

    @FutureOrPresent(message = "Start time must be in the present or future")
    @Column(nullable = false)
    private LocalDateTime startTime;

    @FutureOrPresent(message = "End time must be in the present or future")
    @Column(nullable = false)
    private LocalDateTime endTime;

    private LocalDateTime creationDate;

    @OneToMany (mappedBy = "event")
    private Set<Media> media;
    @ManyToOne (fetch = FetchType.LAZY)
    private User creator;
    @OneToMany (mappedBy = "event")
    private Set<Reservation> reservations;
    @ManyToMany (mappedBy = "wishlist")
    private Set<User> guestsHaveEventInWishlist;

    public Event() {
    }

    public Event(long id, String title, String description, BigDecimal price, int capacity, LocalDateTime creationDate,EventCategory category, LocalDateTime startTime, LocalDateTime endTime, String location) {
        this(title, description, price, capacity, creationDate, category, startTime, endTime, location);
        this.id = id;
    }

    public Event(String title, String description, BigDecimal price, int capacity, LocalDateTime creationDate,EventCategory category, LocalDateTime startTime, LocalDateTime endTime, String location) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.capacity = capacity;
        this.creationDate = creationDate;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
    public EventCategory getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getLocation() {
        return location;
    }

    public Set<Media> getMedia() {
        return media;
    }

    public User getCreator() {
        return creator;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public Set<User> getGuestsHaveEventInWishlist() {
        return guestsHaveEventInWishlist;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMedia(Set<Media> media) {
        this.media = media;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void setGuestsHaveEventInWishlist(Set<User> guestsHaveEventInWishlist) {
        this.guestsHaveEventInWishlist = guestsHaveEventInWishlist;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category.name() +
                ", price=" + price +
                ", capacity=" + capacity +
                ", location=" + location +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", creationDate=" + creationDate +
                "} " + super.toString();
    }
}
