package com.example.event_manager.service;

import com.example.event_manager.dao.EventDao;
import com.example.event_manager.dto.CreateEventDto;
import com.example.event_manager.entity.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
