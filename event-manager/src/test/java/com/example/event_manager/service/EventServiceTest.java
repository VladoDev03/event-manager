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
//

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // Uses in-memory database
@Transactional
public class EventServiceTest {

    @Autowired
    private EventService eventService;

//    @Autowired
//    private UserDao userDao;
//
//    @Autowired
//    private EventDao eventDao;

    private static User testUser;

    @BeforeAll
    static void setup() {
        testUser = new User("tanya_meh",
                "0000",
                "Tanya",
                "Mehandzhvieva",
                LocalDateTime.now());
        UserDao.createUser(testUser);  // Persist user for event creation
    }

    @Test
    void testCreateEvent() {
        CreateEventDto eventDto = new CreateEventDto(
                "Java Lecture",
                "Lecture in NB",
                new BigDecimal("50.00"),
                100,
                LocalDateTime.now(),
                EventCategory.BUSINESS,
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
                "Test Event 2",
                "Event Description",
                new BigDecimal("30.00"),
                200,
                LocalDateTime.now(),
                EventCategory.EDUCATION,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2),
                "Los Angeles",
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
                "User Event 1",
                "Event Description 1",
                new BigDecimal("40.00"),
                50,
                LocalDateTime.now(),
                EventCategory.MUSIC,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2),
                "Chicago",
                testUser.getId()
        );

        CreateEventDto eventDto2 = new CreateEventDto(
                "User Event 2",
                "Event Description 2",
                new BigDecimal("60.00"),
                150,
                LocalDateTime.now(),
                EventCategory.CHARITY,
                LocalDateTime.now().plusDays(3),
                LocalDateTime.now().plusDays(4),
                "San Francisco",
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
                "Update Event",
                "Event Description",
                new BigDecimal("25.00"),
                100,
                LocalDateTime.now(),
                EventCategory.CULTURE,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(2),
                "Miami",
                testUser.getId()
        );

        CreateEventDto createdEvent = eventService.createEvent(eventDto);
        long eventId = createdEvent.getId();

        CreateEventDto updateDto = new CreateEventDto(
                "Updated Title",
                "Updated Description",
                new BigDecimal("75.00"),
                300,
                createdEvent.getCreationDate(),
                createdEvent.getCategory(),
                createdEvent.getStartTime(),
                createdEvent.getEndTime(),
                createdEvent.getLocation(),
                createdEvent.getUserId()
        );

        eventService.updateEvent(eventId, updateDto);
        CreateEventDto updatedEvent = eventService.getEventById(eventId);

        assertEquals("Updated Title", updatedEvent.getTitle());
        assertEquals(new BigDecimal("75.00"), updatedEvent.getPrice());
    }

}
