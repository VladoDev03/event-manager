package com.example.event_manager.controller;

import com.example.event_manager.dto.CreateEventDto;
import com.example.event_manager.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin(origins = "*")
public class WishlistController {
    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    // Add an event to the wishlist
    @PostMapping("/add")
    public CreateEventDto addEventToWishlist(@RequestBody CreateEventDto event) {
        return wishlistService.addEventToWishlist(event);
    }

    // Remove an event from the wishlist
    @DeleteMapping("/remove/{id}")
    public boolean removeEventFromWishlist(@PathVariable Long id) {
        return wishlistService.removeEventFromWishlist(id);
    }

    // Get all events in the wishlist
    @GetMapping
    public List<CreateEventDto> getWishlist() {
        return wishlistService.getWishlist();
    }
}

