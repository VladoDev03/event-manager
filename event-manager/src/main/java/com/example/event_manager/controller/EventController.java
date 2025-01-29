package com.example.event_manager.controller;

import com.example.event_manager.dto.CreateEventDto;
import com.example.event_manager.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody CreateEventDto createEventDto) {
        eventService.createEvent(createEventDto);
        return new ResponseEntity<>("Event created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateEventDto> getEventById(@PathVariable long id) {
        CreateEventDto createEventDto = eventService.getEventById(id);
        if (createEventDto != null) {
            return new ResponseEntity<>(createEventDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<CreateEventDto>> getAllEvents() {
        List<CreateEventDto> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
