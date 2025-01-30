package com.example.event_manager.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Event extends BaseEntity {
    private String title;
    private String description;
    private BigDecimal price;
    private int capacity;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    @OneToMany (mappedBy = "event")
    private Set<EventOnLocation> eventOnLocations;
    @OneToMany (mappedBy = "event")
    private Set<Media> media;
    @ManyToOne
    private Creator creator;
    @OneToMany (mappedBy = "event")
    private Set<Reservation> reservations;
    @ManyToMany (mappedBy = "wishlist")
    private Set<Guest> guestsHaveEventInWishlist;

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

    public Set<EventOnLocation> getEventOnLocations() {
        return eventOnLocations;
    }

    public Set<Media> getMedia() {
        return media;
    }

    public Creator getCreator() {
        return creator;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public Set<Guest> getGuestsHaveEventInWishlist() {
        return guestsHaveEventInWishlist;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setEventOnLocations(Set<EventOnLocation> eventOnLocations) {
        this.eventOnLocations = eventOnLocations;
    }

    public void setMedia(Set<Media> media) {
        this.media = media;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void setGuestsHaveEventInWishlist(Set<Guest> guestsHaveEventInWishlist) {
        this.guestsHaveEventInWishlist = guestsHaveEventInWishlist;
    }
}
