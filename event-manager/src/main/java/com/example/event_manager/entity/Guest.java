package com.example.event_manager.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Guest extends User{
    @OneToMany (mappedBy = "guest")
    private Set<Reservation> reservations;
    @ManyToMany
    private Set<Event> wishlist;

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public Set<Event> getWishlist() {
        return wishlist;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void setWishlist(Set<Event> wishlist) {
        this.wishlist = wishlist;
    }
}
