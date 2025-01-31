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

    public List<EventDto> getEvents(){
        return EventDao.getEvents().stream().map(event -> new EventDto(
                event.getTitle(),
                event.getDescription(),
                event.getLocation(),
                event.getPrice(),
                event.getCapacity(),
                event.getCreationDate(),
                event.getUpdateDate(),
                event.getCreator().getId()
        )).collect(Collectors.toList());
    }
    public void saveEvent(Event event){
        EventDao.createEvent(event);
    }

    public EventDto getEventById(Long id){
         Event event = EventDao.getEventById(id);

         if (event != null){
             return new EventDto(
                     event.getTitle(),
                     event.getDescription(),
                     event.getLocation(),
                     event.getPrice(),
                     event.getCapacity(),
                     event.getCreationDate(),
                     event.getUpdateDate(),
                     event.getCreator().getId());
         }
         return null;
    }
    public List<EventDto> getEventsByName(String title){
        return EventDao.getEventsByName(title).stream().map(event -> new EventDto(
                event.getTitle(),
                event.getDescription(),
                event.getLocation(),
                event.getPrice(),
                event.getCapacity(),
                event.getCreationDate(),
                event.getUpdateDate(),
                event.getCreator().getId()
        )).collect(Collectors.toList());
    }
    public List<EventDto> getEventsByCreatorUserName(String username){
        return EventDao.getEventsByCreatorUsername(username)
                .stream().map(event -> new EventDto(
                        event.getTitle(),
                        event.getDescription(),
                        event.getLocation(),
                        event.getPrice(),
                        event.getCapacity(),
                        event.getCreationDate(),
                        event.getUpdateDate(),
                        event.getCreator().getId()
                )).collect(Collectors.toList());
    }
    public List<EventDto> getAllByLocation(String location){
        return EventDao.getEventsByLocation(location)
                .stream().map(event -> new EventDto(
                        event.getTitle(),
                        event.getDescription(),
                        event.getLocation(),
                        event.getPrice(),
                        event.getCapacity(),
                        event.getCreationDate(),
                        event.getUpdateDate(),
                        event.getCreator().getId()
                )).collect(Collectors.toList());
    }

    public List<EventDto> getEventsByTitleAndLocation(String title, String location) {
        return EventDao.getEventsByNameAndLocation(title, location)
                .stream().map(event -> new EventDto(
                        event.getTitle(),
                        event.getDescription(),
                        event.getLocation(),
                        event.getPrice(),
                        event.getCapacity(),
                        event.getCreationDate(),
                        event.getUpdateDate(),
                        event.getCreator().getId()
                )).collect(Collectors.toList());
    }
}
