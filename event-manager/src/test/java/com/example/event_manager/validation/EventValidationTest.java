package com.example.event_manager.validation;

import com.example.event_manager.entity.Event;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class EventValidationTest {
    private List<String> validate(Event event) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(event)
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }

    @Test
    public void testInvalidTitle() {
        Event event = new Event();
        event.setTitle("A");
        event.setDescription("Lecture in NBU");
        event.setLocation("NBU");
        event.setPrice(BigDecimal.valueOf(0.00));
        event.setCapacity(50);
        event.setStartTime(LocalDateTime.now().plusDays(1));
        event.setEndTime(LocalDateTime.now().plusDays(2));
        event.setCreationDate(LocalDateTime.now());

        List<String> messages = validate(event);

        assertTrue(messages.contains("Title must be between 2 and 100 characters"));
    }

    @Test
    public void testInvalidDescription() {
        Event event = new Event();
        event.setTitle("Java lecture");
        event.setDescription("Lecture");
        event.setLocation("NBU");
        event.setPrice(BigDecimal.valueOf(0.00));
        event.setCapacity(50);
        event.setStartTime(LocalDateTime.now().plusDays(1));
        event.setEndTime(LocalDateTime.now().plusDays(2));
        event.setCreationDate(LocalDateTime.now());

        List<String> messages = validate(event);

        assertTrue(messages.contains("Description must be between 10 and 1000 characters"));
    }

    @Test
    public void testNegativePrice() {
        Event event = new Event();
        event.setTitle("Java lecture");
        event.setDescription("Lecture in NBU");
        event.setLocation("NBU");
        event.setPrice(BigDecimal.valueOf(-5.00));
        event.setCapacity(50);
        event.setStartTime(LocalDateTime.now().plusDays(1));
        event.setEndTime(LocalDateTime.now().plusDays(2));
        event.setCreationDate(LocalDateTime.now());

        List<String> messages = validate(event);

        assertTrue(messages.contains("Price for area must be greater than or equal to 0"));
    }

    @Test
    public void testZeroCapacity() {
        Event event = new Event();
        event.setTitle("Java lecture");
        event.setDescription("Lecture in NBU");
        event.setLocation("NBU");
        event.setPrice(BigDecimal.valueOf(0.00));
        event.setCapacity(0);
        event.setStartTime(LocalDateTime.now().plusDays(1));
        event.setEndTime(LocalDateTime.now().plusDays(2));
        event.setCreationDate(LocalDateTime.now());

        List<String> messages = validate(event);

        assertTrue(messages.contains("Capacity must be greater than or equal to 1"));
    }

    @Test
    public void testPastStartTime() {
        Event event = new Event();
        event.setTitle("Java lecture");
        event.setDescription("Lecture in NBU");
        event.setLocation("NBU");
        event.setPrice(BigDecimal.valueOf(0.00));
        event.setCapacity(50);
        event.setStartTime(LocalDateTime.now().minusDays(1));
        event.setEndTime(LocalDateTime.now().plusDays(2));
        event.setCreationDate(LocalDateTime.now());

        List<String> messages = validate(event);

        assertTrue(messages.contains("Start time must be in the present or future"));
    }

    @Test
    public void testPastEndTime() {
        Event event = new Event();
        event.setTitle("Java lecture");
        event.setDescription("Lecture in NBU");
        event.setLocation("NBU");
        event.setPrice(BigDecimal.valueOf(0.00));
        event.setCapacity(50);
        event.setStartTime(LocalDateTime.now().minusDays(1));
        event.setEndTime(LocalDateTime.now().plusDays(0));
        event.setCreationDate(LocalDateTime.now());

        List<String> messages = validate(event);

        assertTrue(messages.contains("End time must be in the present or future"));
    }



}
