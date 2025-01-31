package com.example.event_manager.service;

import com.example.event_manager.dao.EventDao;
import com.example.event_manager.dao.UserDao;
import com.example.event_manager.dto.CreateEventDto;
import com.example.event_manager.dto.DisplayEventDto;
import com.example.event_manager.dto.FilterRequest;
import com.example.event_manager.entity.Event;
import com.example.event_manager.entity.EventCategory;
import com.example.event_manager.entity.User;
import com.example.event_manager.exception.MinGreaterThanMaxException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    public CreateEventDto createEvent(CreateEventDto createEventDto) {
        User user = UserDao.getUserById(createEventDto.getUserId());

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
        Event event = EventDao.getEventById(id);
        if (event != null) {
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
        return EventDao.getAllDisplayEventDto();
    }

    public List<DisplayEventDto> filterEventsAfterSearch(List <DisplayEventDto> initialEvents, EventCategory eventCategory, BigDecimal minPrice, BigDecimal maxPrice, LocalDateTime start, LocalDateTime end) {
        return initialEvents.stream()
                .filter(event -> (eventCategory == null || event.getCategory().equals(eventCategory)) &&
                        (minPrice == null || event.getPrice().compareTo(minPrice) >= 0) &&
                        (maxPrice == null || event.getPrice().compareTo(maxPrice) <= 0) &&
                        ((end == null || event.getStartTime().isBefore(end)) ||
                                (start == null || event.getEndTime().isAfter(start))))
                .collect(Collectors.toList());
    }

    //search
    public List<DisplayEventDto> getEventsByName(String title){
        return EventDao.getEventsByName(title);
    }

    public List<DisplayEventDto> filterEvents(FilterRequest filterRequest) {
        if(filterRequest.getMinPrice() != null && filterRequest.getMaxPrice() != null) {
            if (filterRequest.getMinPrice().compareTo(filterRequest.getMaxPrice()) > 0) {
                throw new MinGreaterThanMaxException("Minimum price is greater than maximum price.");
            }
        }
        if(filterRequest.getStartDateTime() != null && filterRequest.getEndDateTime() != null) {
            if (filterRequest.getStartDate().isAfter(filterRequest.getEndDate())) {
                throw new MinGreaterThanMaxException("Start date is after end date.");
            }
        }

        return EventDao.getFilteredEventsByCategoryStartTimeEndTimePrice(
                filterRequest.getCategory(),
                filterRequest.getStartDateTime(),
                filterRequest.getEndDateTime(),
                filterRequest.getMinPrice(),
                filterRequest.getMaxPrice()
        );
    }
}
