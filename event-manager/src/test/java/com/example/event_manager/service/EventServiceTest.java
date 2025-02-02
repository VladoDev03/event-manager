package com.example.event_manager.service;

import com.example.event_manager.dao.EventDao;
import com.example.event_manager.dao.UserDao;
import com.example.event_manager.dto.CreateEventDto;
import com.example.event_manager.dto.DisplayEventDto;
import com.example.event_manager.entity.EventCategory;
import com.example.event_manager.entity.User;
import com.example.event_manager.exception.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    private static User testUser;

    @BeforeAll
    static void setup() {
        testUser = new User("tanya_meh",
                "0000",
                "Tanya",
                "Mehandzhvieva",
                LocalDateTime.now());
        UserDao.createUser(testUser);
    }

    @Test
    void testCreateEvent() {
        CreateEventDto eventDto = new CreateEventDto(
                "Java Lecture",
                "Lecture in NBU",
                new BigDecimal("0.00"),
                50,
                LocalDateTime.now(),
                EventCategory.EDUCATION,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2),
                "NBU",
                testUser.getId()
        );

        CreateEventDto savedEvent = eventService.createEvent(eventDto);
        assertNotNull(savedEvent.getId());
    }

    @Test
    void testGetEventById() {
        CreateEventDto eventDto = new CreateEventDto(
                "Java Lecture",
                "Lecture in NBU",
                new BigDecimal("0.00"),
                50,
                LocalDateTime.now(),
                EventCategory.EDUCATION,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2),
                "NBU",
                testUser.getId()
        );

        CreateEventDto createdEvent = eventService.createEvent(eventDto);
        CreateEventDto retrievedEvent = eventService.getEventById(createdEvent.getId());

        assertNotNull(retrievedEvent);
        assertEquals(eventDto.getTitle(), retrievedEvent.getTitle());
    }

    @Test
    void testGetEventsByUser() {
        CreateEventDto eventDto1 = new CreateEventDto(
                "Java Lecture",
                "Lecture in NBU",
                new BigDecimal("0.00"),
                50,
                LocalDateTime.now(),
                EventCategory.EDUCATION,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2),
                "NBU",
                testUser.getId()
        );

        CreateEventDto eventDto2 = new CreateEventDto(
                "JS Lecture",
                "Lecture in NBU",
                new BigDecimal("10.00"),
                50,
                LocalDateTime.now(),
                EventCategory.EDUCATION,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2),
                "NBU",
                testUser.getId()
        );

        eventService.createEvent(eventDto1);
        eventService.createEvent(eventDto2);

        List<DisplayEventDto> userEvents = eventService.getEventsByUser(testUser.getId());
        assertEquals(2, userEvents.size());
    }

    @Test
    void testUpdateEvent() throws EntityNotFoundException {
        CreateEventDto eventDto = new CreateEventDto(
                "Java Lecture",
                "Lecture in NBU",
                new BigDecimal("0.00"),
                50,
                LocalDateTime.now(),
                EventCategory.EDUCATION,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2),
                "NBU",
                testUser.getId()
        );

        CreateEventDto createdEvent = eventService.createEvent(eventDto);
        long eventId = createdEvent.getId();

        CreateEventDto updateDto = new CreateEventDto(
                "JS Lecture",
                "Lecture in NBU",
                new BigDecimal("10.00"),
                100,
                createdEvent.getCreationDate(),
                createdEvent.getCategory(),
                createdEvent.getStartTime(),
                createdEvent.getEndTime(),
                createdEvent.getLocation(),
                createdEvent.getUserId()
        );

        eventService.updateEvent(eventId, updateDto);
        CreateEventDto updatedEvent = eventService.getEventById(eventId);

        assertEquals("JS Lecture", updatedEvent.getTitle());
        assertEquals(new BigDecimal("10.00"), updatedEvent.getPrice());
    }

}
