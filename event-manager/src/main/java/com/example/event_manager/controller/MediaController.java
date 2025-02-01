package com.example.event_manager.controller;

import com.example.event_manager.exception.NotCreatorException;
import com.example.event_manager.service.MediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/media")
@CrossOrigin(origins = "*")
public class MediaController {
    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping()
    public ResponseEntity<?> uploadMedia(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("eventId") long eventId,
            @RequestParam("userId") long userId
    ) {
        try {
            mediaService.createMedia(files, eventId, userId);
            return ResponseEntity.status(201).body("Media uploaded successfully");
        } catch (IOException | NotCreatorException e) {
            return ResponseEntity.status(500).body("Failed to upload images: " + e.getMessage());
        }
    }

    @DeleteMapping("/{mediaId}")
    public ResponseEntity<?> deleteMedia(@PathVariable String mediaId) {
        try {
            mediaService.deleteMedia(Long.parseLong(mediaId));
            return ResponseEntity.ok(mediaId);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to delete image: " + e.getMessage());
        }
    }
}