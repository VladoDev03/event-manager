package com.example.event_manager.service;

import com.example.event_manager.dao.EventDao;
import com.example.event_manager.dto.CreateEventDto;
import com.example.event_manager.entity.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<CreateEventDto> getAllEvents() {
        List<Event> events = EventDao.getAllEvents();
        return events.stream()
                .map(event -> new CreateEventDto(
                        event.getTitle(),
                        event.getDescription(),
                        event.getPrice(),
                        event.getCapacity(),
                        event.getCreationDate()
                ))
                .collect(Collectors.toList());
    }
}
