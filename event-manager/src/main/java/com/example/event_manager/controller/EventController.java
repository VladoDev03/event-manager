package com.example.event_manager.controller;

import com.example.event_manager.dto.CreateEventDto;
import com.example.event_manager.dto.DisplayEventDto;
import com.example.event_manager.dto.FilterRequest;
import com.example.event_manager.entity.Event;
import com.example.event_manager.entity.EventCategory;
import com.example.event_manager.exception.MinGreaterThanMaxException;
import com.example.event_manager.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
//        return ResponseEntity.ok(new CreateEventDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateEventDto> getEventById(@PathVariable long id) {
        CreateEventDto createEventDto = eventService.getEventById(id);
        if (createEventDto != null) {
            return new ResponseEntity<>(createEventDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable long id, @RequestBody CreateEventDto createEventDto) {
        eventService.updateEvent(id, createEventDto);
        return new ResponseEntity<>("Event updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>("Event deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/allEvents")
    public List<DisplayEventDto> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping("/filterEvents")
    public List<DisplayEventDto> filterEvents(@RequestBody FilterRequest filterRequest) {
        try {
            return eventService.filterEvents(filterRequest);
        } catch (MinGreaterThanMaxException e) {
            return null;
        }
    }

}
