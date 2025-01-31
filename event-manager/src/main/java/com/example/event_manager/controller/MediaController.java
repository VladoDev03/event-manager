package com.example.event_manager.controller;

import com.example.event_manager.service.CloudinaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/media")
@CrossOrigin(origins = "*")
public class MediaController {
    private final CloudinaryService cloudinaryService;

    public MediaController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping()
    public ResponseEntity<?> uploadMedia(@RequestParam("files") MultipartFile[] files, @RequestParam("eventId") long eventId) {
        try {
            String imageUrls = cloudinaryService.uploadMedia(files, eventId);
            return ResponseEntity.ok(imageUrls);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload images: " + e.getMessage());
        }
    }

    @DeleteMapping("/{mediaId}")
    public ResponseEntity<?> deleteMedia(@PathVariable String mediaId) {
        try {
            cloudinaryService.deleteMedia(Long.parseLong(mediaId));
            return ResponseEntity.ok(mediaId);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to delete image: " + e.getMessage());
        }
    }
}
