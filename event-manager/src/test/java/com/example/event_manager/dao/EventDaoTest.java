package com.example.event_manager.dao;

import com.example.event_manager.configuration.SessionFactoryUtil;
import com.example.event_manager.dao.EventDao;
import com.example.event_manager.dto.DisplayEventDto;
import com.example.event_manager.entity.Event;
import com.example.event_manager.entity.EventCategory;
import com.example.event_manager.exception.EntityNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class EventDaoTest {
    private Event event;

    @BeforeEach
    public void setUp() {
        event = new Event("Java lecture", "Lecture in NBU",
                new BigDecimal("0.00"), 100,
                LocalDateTime.now(), EventCategory.EDUCATION,
                LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2),
                "NBU");
    }

    @Test
    public void testCreateEvent() {
        EventDao.createEvent(event);
        assertNotNull(event.getId());
    }

    @Test
    public void testGetEventById() throws EntityNotFoundException {
        EventDao.createEvent(event);
        Event fetchedEvent = EventDao.getEventById(event.getId());
        assertNotNull(fetchedEvent);
        assertEquals(event.getId(), fetchedEvent.getId());
    }

    @Test
    public void testUpdateEvent() throws EntityNotFoundException {
        EventDao.createEvent(event);
        event.setTitle("Updated Title");
        EventDao.updateEvent(event);
        Event updatedEvent = EventDao.getEventById(event.getId());
        assertEquals("Updated Title", updatedEvent.getTitle());
    }


    @Test
    public void testGetAllEvents() {
        EventDao.createEvent(event);
        List<Event> events = EventDao.getEvents();
        assertFalse(events.isEmpty());
    }

    @Test
    public void testIsEventFull() throws EntityNotFoundException {
        EventDao.createEvent(event);
        assertFalse(EventDao.isEventFull(event.getId()));
    }

    @Test
    public void testGetEventsByName() {
        EventDao.createEvent(event);
        List<DisplayEventDto> events = EventDao.getEventsByName("Java lecture");
        assertFalse(events.isEmpty());
        assertEquals("Java lecture", events.get(0).getTitle());
    }
    @Test
    public void testGetEventsByLocation() {
        EventDao.createEvent(event);
        List<DisplayEventDto> events = EventDao.getEventsByLocation("NBU");
        assertFalse(events.isEmpty());
    }

}