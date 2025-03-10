package com.example.event_manager.controller;

import com.example.event_manager.dto.CreateEventDto;
import com.example.event_manager.dto.DisplayEventDto;
import com.example.event_manager.dto.EventMediaDto;
import com.example.event_manager.entity.EventCategory;
import com.example.event_manager.exception.EntityNotFoundException;
import com.example.event_manager.exception.MinGreaterThanMaxException;
import com.example.event_manager.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        CreateEventDto res = eventService.createEvent(createEventDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/createdEvents/{userId}")
    public List<DisplayEventDto> getEventsByUser(@PathVariable long userId) {
        return eventService.getEventsByUser(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventMediaDto> getEventById(@PathVariable long id) {
        EventMediaDto dto = eventService.getEventWithMediaById(id);
        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/future")
    public ResponseEntity<List<DisplayEventDto>> getFutureEvents() {
        List<DisplayEventDto> dtos = eventService.getFutureEvents();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable long id, @RequestBody CreateEventDto createEventDto) throws EntityNotFoundException {
        eventService.updateEvent(id, createEventDto);
        return new ResponseEntity<>("Event updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable long id) throws EntityNotFoundException {
        eventService.deleteEvent(id);
        return new ResponseEntity<>("Event deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/allEvents")
    public List<DisplayEventDto> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/search")
    public List<DisplayEventDto> getEventsByCriteria(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "location", required = false) String location
    ) {
        // Handle combined filtering
        if (title != null && location != null) {
            return eventService.getEventsByTitleAndLocation(title, location);
        } else if (title != null) {
            return eventService.getEventsByName(title);
        } else if (location != null) {
            return eventService.getEventsByLocation(location);
        } else {
            return null;
        }
    }

    @GetMapping("/filterEvents")
    public List<DisplayEventDto> filterEvents(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "startDate", required = false) String startDateTime,
            @RequestParam(value = "endDate", required = false) String endDateTime,
            @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice
    ) {
        try {
            LocalDate startDateParam = null;
            LocalDate endDateParam = null;
            LocalDateTime startDateTimeParam = null;
            LocalDateTime endDateTimeParam = null;

            if(startDateTime!= null && !startDateTime.equals("null")){
                startDateParam = LocalDate.parse(startDateTime);
                startDateTimeParam = LocalDateTime.of(startDateParam.getYear(), startDateParam.getMonth(), startDateParam.getDayOfMonth(), 0, 0);
            }

            if(endDateTime != null && !endDateTime.equals("null")){
                endDateParam = LocalDate.parse(endDateTime);
                endDateTimeParam = LocalDateTime.of(endDateParam.getYear(), endDateParam.getMonth(), endDateParam.getDayOfMonth(), 23,59);
            }

            if(category == null || category.equals("null")) {
                return eventService.filterEvents(
                        null,
                        startDateTimeParam,
                        endDateTimeParam,
                        minPrice,
                        maxPrice);
            }
            return eventService.filterEvents(
                    EventCategory.valueOf(category.toUpperCase()),
                    startDateTimeParam,
                    endDateTimeParam,
                    minPrice,
                    maxPrice);
        } catch (MinGreaterThanMaxException e) {
            return null;
        }
    }

    @GetMapping("/search/filter")
    public List<DisplayEventDto> getFilteredEventsAfterSearch(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "startDate", required = false) String startDateTime,
            @RequestParam(value = "endDate", required = false) String endDateTime,
            @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice
    ) {
        try {
            LocalDate startDateParam = null;
            LocalDate endDateParam = null;
            LocalDateTime startDateTimeParam = null;
            LocalDateTime endDateTimeParam = null;

            if(startDateTime!= null && !startDateTime.equals("null")){
                startDateParam = LocalDate.parse(startDateTime);
                startDateTimeParam = LocalDateTime.of(startDateParam.getYear(), startDateParam.getMonth(), startDateParam.getDayOfMonth(), 0, 0);
            }

            if(endDateTime != null && !endDateTime.equals("null")){
                endDateParam = LocalDate.parse(endDateTime);
                endDateTimeParam = LocalDateTime.of(endDateParam.getYear(), endDateParam.getMonth(), endDateParam.getDayOfMonth(), 23,59);
            }

            if(category == null || category.equals("null")) {
                return eventService.filterEventsAfterSearch(title,
                        location,
                        null,
                        startDateTimeParam,
                        endDateTimeParam,
                        minPrice,
                        maxPrice);
            }
            return eventService.filterEventsAfterSearch(title,
                    location,
                    EventCategory.valueOf(category.toUpperCase()),
                    startDateTimeParam,
                    endDateTimeParam,
                    minPrice,
                    maxPrice);
        } catch (MinGreaterThanMaxException e) {
            return null;
        }
    }

}
