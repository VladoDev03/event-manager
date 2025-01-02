package com.example.SpringProject.Creator;

import com.example.SpringProject.Event.Event;
import com.example.SpringProject.User.User;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class Creator extends User {
    @OneToMany(mappedBy = "creator")
    private List<Event> events;

    public Creator() {
    }

    public Creator(List<Event> events) {
        this.events = events;
    }

    public Creator(User user) {
        super(user.getUsername(),user.getCreationDate(),user.getFirstName(),user.getSecondName(),user.getPassword());
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
