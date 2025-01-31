package com.example.SpringProject.Event;

import com.example.SpringProject.BaseEntity.BaseEntity;
import com.example.SpringProject.Creator.Creator;
import com.example.SpringProject.EventOnLocation.EventOnLocation;
import com.example.SpringProject.Guest.Guest;
import com.example.SpringProject.Media.Media;
import com.example.SpringProject.Reservation.Reservation;
import javax.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
public class Event extends BaseEntity {
    private String title;
    private String description;
    private BigDecimal price;
    private int capacity;
    private String location;
    private LocalDate creationDate;
    @Column(name = "updateDate", columnDefinition = "DATE")
    private LocalDateTime updateDate;
    @OneToMany(mappedBy = "event")
    private Set<Media> media;
    @ManyToOne
    private Creator creator;
    @OneToMany(mappedBy = "event")
    private Set<Reservation> reservations;
    @ManyToMany(mappedBy = "wishlist")
    private Set<Guest> guestsHaveEventInWishlist;

    public Event() {
    }

    public Event(String title, String description,String location, BigDecimal price, int capacity, LocalDate creationDate, LocalDateTime updateDate, Creator creator, long id) {
        super(id);
        this.title = title;
        this.description = description;
        this.location = location;
        this.price = price;
        this.capacity = capacity;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.creator = creator;
    }

    public Event(String title, String description, BigDecimal price, int capacity, LocalDate creationDate, LocalDateTime updateDate,
                 String location, Set<Media> media, Creator creator, Set<Reservation> reservations,
                 Set<Guest> guestsHaveEventInWishlist, long id){
        super(id);
        this.title = title;
        this.description = description;
        this.price = price;
        this.capacity = capacity;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.location = location;
        this.media = media;
        this.creator = creator;
        this.reservations = reservations;
        this.guestsHaveEventInWishlist = guestsHaveEventInWishlist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocations(String location) {
        this.location = location;
    }

    public Set<Media> getMedia() {
        return media;
    }

    public void setMedia(Set<Media> media) {
        this.media = media;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Guest> getGuestsHaveEventInWishlist() {
        return guestsHaveEventInWishlist;
    }

    public void setGuestsHaveEventInWishlist(Set<Guest> guestsHaveEventInWishlist) {
        this.guestsHaveEventInWishlist = guestsHaveEventInWishlist;
    }
}
