package com.example.SpringProject.Event;

import com.example.SpringProject.Event.Dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/event")
@CrossOrigin(origins = "http://localhost:5173")
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

    @GetMapping("/search")
    public List<EventDto> getEventsByCriteria(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "date", required = false) LocalDate date
    ) {
        // Handle combined filtering
        if (title != null && date != null) {
            return eventService.getEventsByTitleAndDate(title, date);
        } else if (title != null) {
            return eventService.getEventsByName(title);
        } else if (date != null) {
            return eventService.getAllByDate(date);
        } else {
            return eventService.getEvents(); // Return all events if no criteria provided
        }
    }


    @GetMapping("creatorUsername/{username}")
    public List<EventDto> getEventsByCreatorUserName(@PathVariable String username){
        return eventService.getEventsByCreatorUserName(username);
    }

    @GetMapping("/date")
    public List<EventDto> getEventsByDate(@RequestParam(value = "date", required = false) LocalDate date){
        return eventService.getAllByDate(date);
    }
}
