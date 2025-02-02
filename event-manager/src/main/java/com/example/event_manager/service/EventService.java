package com.example.event_manager.service;

import com.example.event_manager.dao.EventDao;
import com.example.event_manager.dao.UserDao;
import com.example.event_manager.dto.*;
import com.example.event_manager.entity.Event;
import com.example.event_manager.entity.EventCategory;
import com.example.event_manager.entity.User;
import com.example.event_manager.exception.EntityNotFoundException;
import com.example.event_manager.exception.MinGreaterThanMaxException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    public CreateEventDto createEvent(CreateEventDto createEventDto) {
        User user = null;
        try {
            user = UserDao.getUserById(createEventDto.getUserId());
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }

        Event event = new Event(
                createEventDto.getId(),
                createEventDto.getTitle(),
                createEventDto.getDescription(),
                createEventDto.getPrice(),
                createEventDto.getCapacity(),
                LocalDateTime.now(),
                createEventDto.getCategory(),
                createEventDto.getStartTime(),
                createEventDto.getEndTime(),
                createEventDto.getLocation()
        );
        event.setCreator(user);
        EventDao.createEvent(event);

        createEventDto.setId(event.getId());

        return createEventDto;
    }

    public CreateEventDto getEventById(long id) {
        Event event = null;
        try {
            event = EventDao.getEventById(id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }

        return new CreateEventDto(
                event.getTitle(),
                event.getDescription(),
                event.getPrice(),
                event.getCapacity(),
                event.getCreationDate(),
                event.getCategory(),
                event.getStartTime(),
                event.getEndTime(),
                event.getLocation(),
                event.getCreator().getId()
        );
    }

    public EventMediaDto getEventWithMediaById(long id) {
        Event event;
        try {
            event = EventDao.getEventByIdWithMedia(id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Event not found for ID: " + id, e);
        }

        return new EventMediaDto(
                event.getTitle(),
                event.getDescription(),
                event.getPrice(),
                event.getCapacity(),
                event.getCreationDate(),
                event.getCategory(),
                event.getStartTime(),
                event.getEndTime(),
                event.getLocation(),
                event.getCreator().getId(),
                event.getMedia().stream()
                        .map(m -> new MediaDto(m.getUrl(), m.getPublicId()))
                        .toList()
        );
    }


    public List<DisplayEventDto> getEventsByUser(long userId) {
        List<Event> events = null;
        try {
            events = EventDao.findByUserId(userId);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }

        return events.stream()
                .map(event -> new DisplayEventDto(
                        event.getId(),
                        event.getTitle(),
                        event.getDescription(),
                        event.getCategory(),
                        event.getLocation(),
                        event.getPrice(),
                        event.getStartTime(),
                        event.getEndTime()
                ))
                .collect(Collectors.toList());
    }

    public void updateEvent(long id, CreateEventDto createEventDto) throws EntityNotFoundException {
        Event event = EventDao.getEventById(id);
        event.setTitle(createEventDto.getTitle());
        event.setDescription(createEventDto.getDescription());
        event.setPrice(createEventDto.getPrice());
        event.setCapacity(createEventDto.getCapacity());
        event.setCreationDate(createEventDto.getCreationDate());
        EventDao.updateEvent(event);
    }

    public void deleteEvent(long id) throws EntityNotFoundException {
        Event event = EventDao.getEventById(id);
        EventDao.deleteEvent(event);
    }

    public List<DisplayEventDto> getAllEvents() {
        return EventDao.getAllDisplayEventDto();
    }

    public List<DisplayEventDto> getFutureEvents() {
        return EventDao.getFutureEvents()
                .stream()
                .map(e -> new DisplayEventDto(
                        e.getId(),
                        e.getTitle(),
                        e.getDescription(),
                        e.getCategory(),
                        e.getLocation(),
                        e.getPrice(),
                        e.getStartTime(),
                        e.getEndTime()
                ))
                .toList();
    }

    //search
    public List<DisplayEventDto> getEventsByName(String title){
        return EventDao.getEventsByName(title);
    }

    public List<DisplayEventDto> getEventsByLocation(String location){
        return EventDao.getEventsByLocation(location);
    }

    public List<DisplayEventDto> getEventsByTitleAndLocation(String title, String location){
        return EventDao.getEventsByNameAndLocation(title, location);
    }

    public List<DisplayEventDto> filterEvents(EventCategory eventCategory, LocalDateTime startDateTime, LocalDateTime endDateTime, BigDecimal minPrice, BigDecimal maxPrice) {
        if(minPrice != null && maxPrice != null) {
            if (minPrice.compareTo(maxPrice) > 0) {
                throw new MinGreaterThanMaxException("Minimum price is greater than maximum price.");
            }
        }
        if(startDateTime != null && endDateTime != null) {
            if (startDateTime.isAfter(endDateTime)) {
                throw new MinGreaterThanMaxException("Start date is after end date.");
            }
        }

        return EventDao.getFilteredEventsByCategoryStartTimeEndTimePrice(
                eventCategory,
                startDateTime,
                endDateTime,
                minPrice,
                maxPrice
        );
    }

    public List<DisplayEventDto> filterEventsAfterSearch(String title, String location, EventCategory eventCategory, LocalDateTime startDateTime, LocalDateTime endDateTime, BigDecimal minPrice, BigDecimal maxPrice) {
        if(minPrice != null && maxPrice!= null) {
            if (minPrice.compareTo(maxPrice) > 0) {
                throw new MinGreaterThanMaxException("Minimum price is greater than maximum price.");
            }
        }
        if(startDateTime != null && endDateTime != null) {
            if (startDateTime.isAfter(endDateTime)) {
                throw new MinGreaterThanMaxException("Start date is after end date.");
            }
        }

        return EventDao.getFilteredEventsAfterSearch(title, location, eventCategory, startDateTime, endDateTime, minPrice, maxPrice);
    }
}
