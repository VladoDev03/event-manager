package com.example.event_manager.service;

import com.example.event_manager.dao.EventDao;
import com.example.event_manager.dto.CreateEventDto;
import com.example.event_manager.dto.DisplayEventDto;
import com.example.event_manager.entity.Event;
import com.example.event_manager.entity.EventCategory;
import com.example.event_manager.entity.EventOnLocation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.event_manager.dao.EventDao.getEvents;

@Service
public class EventService {
    public void createEvent(CreateEventDto createEventDto) {
        Event event = new Event(
                createEventDto.getTitle(),
                createEventDto.getDescription(),
                createEventDto.getPrice(),
                createEventDto.getCapacity(),
                LocalDateTime.now()
        );
        EventDao.createEvent(event);
    }

    public CreateEventDto getEventById(long id) {
        Event event = EventDao.getEventById(id);
        if (event != null) {
            return new CreateEventDto(
                    event.getTitle(),
                    event.getDescription(),
                    event.getPrice(),
                    event.getCapacity(),
                    event.getCreationDate()
            );
        }
        return null;
    }

    public void updateEvent(long id, CreateEventDto createEventDto) {
        Event event = EventDao.getEventById(id);
        if (event != null) {
            event.setTitle(createEventDto.getTitle());
            event.setDescription(createEventDto.getDescription());
            event.setPrice(createEventDto.getPrice());
            event.setCapacity(createEventDto.getCapacity());
            event.setCreationDate(createEventDto.getCreationDate());
            EventDao.updateEvent(event);
        }
    }

    public void deleteEvent(long id) {
        Event event = EventDao.getEventById(id);
        if (event != null) {
            EventDao.deleteEvent(event);
        }
    }

    public List<DisplayEventDto> getAllEvents() {
        return EventDao.getAllDisolayEventDto();
    }

//    public List<Event> filterEvents(List <Event> initialEvents, EventCategory eventCategory, BigDecimal minPrice, BigDecimal maxPrice, LocalDateTime start, LocalDateTime end) {
//        return initialEvents.stream()
//                .filter(event -> (eventCategory == null || event.getCategory().equals(eventCategory)) &&
//                        (minPrice == null || event.getPrice().compareTo(minPrice) >= 0) &&
//                        (maxPrice == null || event.getPrice().compareTo(maxPrice) <= 0) &&
//                        ((end == null || EventDao.getEventStartTime(event.getId()).isBefore(end)) ||
//                        (start == null || EventDao.getEventEndTime(event.getId()).isAfter(start))))
//                .collect(Collectors.toList());
//    }

    public List<DisplayEventDto> filterEvents(List <DisplayEventDto> initialEvents, EventCategory eventCategory, BigDecimal minPrice, BigDecimal maxPrice, LocalDateTime start, LocalDateTime end) {
        return initialEvents.stream()
                .filter(event -> (eventCategory == null || event.getCategory().equals(eventCategory)) &&
                        (minPrice == null || event.getPrice().compareTo(minPrice) >= 0) &&
                        (maxPrice == null || event.getPrice().compareTo(maxPrice) <= 0) &&
                        ((end == null || EventDao.getEventStartTime(event.getId()).isBefore(end)) ||
                                (start == null || EventDao.getEventEndTime(event.getId()).isAfter(start))))
                .collect(Collectors.toList());
    }
}
