package com.example.event_manager.controller;

import com.example.event_manager.dto.CreateEventDto;
import com.example.event_manager.dto.DisplayEventDto;
import com.example.event_manager.dto.WishlistRequest;
import com.example.event_manager.exception.EntityNotFoundException;
import com.example.event_manager.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> addEventToWishlist(@RequestBody WishlistRequest wishlist) throws EntityNotFoundException {
        System.out.println("Workds");
        wishlistService.addEventToWishlist(wishlist.getEventId(), wishlist.getUserId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Remove an event from the wishlist
    @DeleteMapping("/remove")
    public boolean removeEventFromWishlist(@RequestBody WishlistRequest wishlist) {
        return wishlistService.removeEventFromWishlist(wishlist.getEventId(), wishlist.getUserId());
    }

    // Get all events in the wishlist
    @GetMapping("/{id}")
    public List<DisplayEventDto> getWishlist(@PathVariable long id) {
        return wishlistService.getWishlist(id);
    }
}

