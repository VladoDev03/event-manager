package com.example.SpringProject.Event;

import com.example.SpringProject.Event.Dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/event")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventDto> getEvents(){
        return eventService.getEvents();
    }

    @PostMapping
    public void saveEvent(@RequestBody Event event){
        eventService.saveEvent(event);
    }

    @GetMapping("/{id}")
    public EventDto getEventById(@PathVariable Long id){
        return eventService.getEventById(id);
    }

    @GetMapping("/name/{title}")
    public List<EventDto> getEventsByName(@PathVariable(required = false) String title){
        return eventService.getEventsByName(title);
    }

    @GetMapping("creatorUsername/{username}")
    public List<EventDto> getEventsByCreatorUserName(@PathVariable String username){
        return eventService.getEventsByCreatorUserName(username);
    }

    @GetMapping("date/{date}")
    public List<EventDto> getEventsByDate(@PathVariable LocalDate date){
        return eventService.getAllByDate(date);
    }
}
