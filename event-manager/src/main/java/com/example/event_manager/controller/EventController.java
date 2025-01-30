package com.example.event_manager.controller;

import com.example.event_manager.dto.CreateEventDto;
import com.example.event_manager.dto.DisplayEventDto;
import com.example.event_manager.dto.FilterRequest;
import com.example.event_manager.entity.Event;
import com.example.event_manager.entity.EventCategory;
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
    public ResponseEntity<CreateEventDto> createEvent(@RequestBody CreateEventDto createEventDto) {
        var res = eventService.createEvent(createEventDto);
        System.out.println(createEventDto);
//        return new ResponseEntity<>("Event created successfully", HttpStatus.CREATED);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateEventDto> getEventById(@PathVariable long id) {
        CreateEventDto createEventDto = eventService.getEventById(id);
        if (createEventDto != null) {
            return new ResponseEntity<>(createEventDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/allEvents")
    public List<DisplayEventDto> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping("/filterEvents")
    public List<DisplayEventDto> filterEvents(@RequestBody FilterRequest filterRequest) {
        return eventService.filterEvents(filterRequest);
    }
}