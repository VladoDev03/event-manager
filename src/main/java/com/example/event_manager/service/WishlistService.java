package com.example.event_manager.service;

import com.example.event_manager.dto.CreateEventDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {
    private final List<CreateEventDto> wishlist = new ArrayList<>();

    // Add an event to the wishlist
    public CreateEventDto addEventToWishlist(CreateEventDto event) {
        wishlist.add(event);
        return event;  // Return the added event
    }

    // Remove an event from the wishlist
    public boolean removeEventFromWishlist(long eventId) {
        Optional<CreateEventDto> eventToRemove = wishlist.stream()
                .filter(event -> event.getId() == eventId)
                .findFirst();

        if (eventToRemove.isPresent()) {
            wishlist.remove(eventToRemove.get());
            return true;
        }
        return false;  // Event not found in wishlist
    }


    // Get all events in the wishlist
    public List<CreateEventDto> getWishlist() {
        return new ArrayList<>(wishlist);  // Return a copy to prevent external modification
    }
}
