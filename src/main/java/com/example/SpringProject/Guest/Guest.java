package com.example.SpringProject.Guest;

import com.example.SpringProject.BaseEntity.BaseEntity;
import com.example.SpringProject.Event.Event;
import com.example.SpringProject.Reservation.Reservation;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Guest extends BaseEntity {
    @OneToMany(mappedBy = "guest")
    private Set<Reservation> reservations;
    @ManyToMany
    private Set<Event> wishlist;

    public Guest() {
    }

    public Guest(long id, Set<Reservation> reservations, Set<Event> wishlist) {
        super(id);
        this.reservations = reservations;
        this.wishlist = wishlist;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Event> getWishlist() {
        return wishlist;
    }

    public void setWishlist(Set<Event> wishlist) {
        this.wishlist = wishlist;
    }
}
