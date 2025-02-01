package com.example.event_manager.service;

import com.example.event_manager.dto.CreateEventDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {
    private final List<CreateEventDto> wishlist = new ArrayList<>();

    public CreateEventDto addEventToWishlist(CreateEventDto event) {
        wishlist.add(event);
        return event;
    }

    public boolean removeEventFromWishlist(long eventId) {
        Optional<CreateEventDto> eventToRemove = wishlist.stream()
                .filter(event -> event.getId() == eventId)
                .findFirst();

        if (eventToRemove.isPresent()) {
            wishlist.remove(eventToRemove.get());
            return true;
        }
        return false;
    }

    public List<CreateEventDto> getWishlist() {
        return new ArrayList<>(wishlist);
    }
}
