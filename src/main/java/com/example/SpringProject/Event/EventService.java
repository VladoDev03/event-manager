package com.example.SpringProject.Event;

import com.example.SpringProject.Event.Dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDto> getEvents(){
        return eventRepository.findAll().stream().
                map(event -> new EventDto(
                        event.getTitle(),
                        event.getDescription(),
                        event.getPrice(),
                        event.getCapacity(),
                        event.getCreationDate(),
                        event.getUpdateDate(),
                        event.getCreator().getId()))
                .collect(Collectors.toList());
    }
    public void saveEvent(Event event){
        eventRepository.save(event);
    }
    public EventDto getEventById(Long id){
         Event event = eventRepository.findById(id).orElse(null);

         if (event != null){
             return new EventDto(
                     event.getTitle(),
                     event.getDescription(),
                     event.getPrice(),
                     event.getCapacity(),
                     event.getCreationDate(),
                     event.getUpdateDate(),
                     event.getCreator().getId());
         }
         return null;
    }
    public List<EventDto> getEventsByName(String title){
        return eventRepository.getAllByName(title).stream().map(event -> new EventDto(
                event.getTitle(),
                event.getDescription(),
                event.getPrice(),
                event.getCapacity(),
                event.getCreationDate(),
                event.getUpdateDate(),
                event.getCreator().getId()
        )).collect(Collectors.toList());
    }
    public List<EventDto> getEventsByCreatorUserName(String username){
        return eventRepository.getAllByCreatorUserName(username)
                .stream().map(event -> new EventDto(
                        event.getTitle(),
                        event.getDescription(),
                        event.getPrice(),
                        event.getCapacity(),
                        event.getCreationDate(),
                        event.getUpdateDate(),
                        event.getCreator().getId()
                )).collect(Collectors.toList());
    }
    public List<EventDto> getAllByDate(LocalDate date){
        return eventRepository.getAllByDate(date)
                .stream().map(event -> new EventDto(
                        event.getTitle(),
                        event.getDescription(),
                        event.getPrice(),
                        event.getCapacity(),
                        event.getCreationDate(),
                        event.getUpdateDate(),
                        event.getCreator().getId()
                )).collect(Collectors.toList());
    }

    public List<EventDto> getEventsByTitleAndDate(String title, LocalDate date) {
        return eventRepository.findByTitleAndDate(title, date)
                .stream()
                .map(event -> new EventDto(event.getTitle(),
                        event.getDescription(),
                        event.getPrice(),
                        event.getCapacity(),
                        event.getCreationDate(),
                        event.getUpdateDate(),
                        event.getCreator().getId()
                )).collect(Collectors.toList());
    }
}
