package com.example.event_manager.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Event extends BaseEntity {
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private EventCategory category;
    @ManyToOne (fetch = FetchType.LAZY)
    private Location location;
    private BigDecimal price;
    private int capacity;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
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

    public Event(String title, String description, BigDecimal price, int capacity, LocalDateTime creationDate) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.capacity = capacity;
        this.creationDate = creationDate;
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

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public Location getLocation() {
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

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public void setLocation(Location location) {
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
                ", location=" + location.getName() +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                "} " + super.toString();
    }
}
