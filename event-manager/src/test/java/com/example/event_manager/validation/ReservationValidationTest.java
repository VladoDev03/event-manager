package com.example.event_manager.validation;

import com.example.event_manager.entity.Event;
import com.example.event_manager.entity.Reservation;
import com.example.event_manager.entity.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationValidationTest {

    private List<String> validate(Reservation reservation) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(reservation)
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }

    @Test
    void whenInvalidPurchaseDate_thenAssertConstraintViolations() {
        Event event = new Event();
        event.setTitle("Lecture");
        event.setDescription("Lecture in NBU");
        event.setLocation("NBU");
        event.setPrice(BigDecimal.valueOf(0.00));
        event.setCapacity(50);
        event.setStartTime(LocalDateTime.now().plusDays(1));
        event.setEndTime(LocalDateTime.now().plusDays(2));
        event.setCreationDate(LocalDateTime.now());

        User user = new User();
        user.setId(1);
        user.setUsername("tanya_meh");
        user.setPassword("0000");
        user.setFirstName("Tanya");
        user.setLastName("Mehandzhvieva");
        user.setCreationDate(LocalDateTime.now());
        
        Reservation reservation = new Reservation();
        reservation.setPurchaseDate(LocalDateTime.of(2026, 1,1,1,1));
        reservation.setEvent(event);
        reservation.setGuest(user);
        reservation.setFirstName("Jane");
        reservation.setLastName("Doe");
        reservation.setEmail("janedoe@mail.com");

        List<String> messages = validate(reservation);

        assertTrue(messages.contains("Purchase date must be in the past or present."));
    }

    @Test
    void whenInvalidFirstName_thenAssertConstraintViolations() {
        Event event = new Event();
        event.setTitle("Lecture");
        event.setDescription("Lecture in NBU");
        event.setLocation("NBU");
        event.setPrice(BigDecimal.valueOf(0.00));
        event.setCapacity(50);
        event.setStartTime(LocalDateTime.now().plusDays(1));
        event.setEndTime(LocalDateTime.now().plusDays(2));
        event.setCreationDate(LocalDateTime.now());

        User user = new User();
        user.setId(1);
        user.setUsername("tanya_meh");
        user.setPassword("0000");
        user.setFirstName("Tanya");
        user.setLastName("Mehandzhvieva");
        user.setCreationDate(LocalDateTime.now());

        Reservation reservation = new Reservation();
        reservation.setPurchaseDate(LocalDateTime.of(2024, 1,1,1,1));
        reservation.setEvent(event);
        reservation.setGuest(user);
        reservation.setFirstName("name");
        reservation.setLastName("Doe");
        reservation.setEmail("janedoe@mail.com");

        List<String> messages = validate(reservation);

        assertTrue(messages.contains("name is not a valid name."));
    }

    @Test
    void whenInvalidLastName_thenAssertConstraintViolations() {
        Event event = new Event();
        event.setTitle("Lecture");
        event.setDescription("Lecture in NBU");
        event.setLocation("NBU");
        event.setPrice(BigDecimal.valueOf(0.00));
        event.setCapacity(50);
        event.setStartTime(LocalDateTime.now().plusDays(1));
        event.setEndTime(LocalDateTime.now().plusDays(2));
        event.setCreationDate(LocalDateTime.now());

        User user = new User();
        user.setId(1);
        user.setUsername("tanya_meh");
        user.setPassword("0000");
        user.setFirstName("Tanya");
        user.setLastName("Mehandzhvieva");
        user.setCreationDate(LocalDateTime.now());

        Reservation reservation = new Reservation();
        reservation.setPurchaseDate(LocalDateTime.of(2024, 1,1,1,1));
        reservation.setEvent(event);
        reservation.setGuest(user);
        reservation.setFirstName("Jane");
        reservation.setLastName("name");
        reservation.setEmail("janedoe@mail.com");

        List<String> messages = validate(reservation);

        assertTrue(messages.contains("name is not a valid name."));
    }

    @Test
    void whenInvalidEmail_thenAssertConstraintViolations() {
        Event event = new Event();
        event.setTitle("Lecture");
        event.setDescription("Lecture in NBU");
        event.setLocation("NBU");
        event.setPrice(BigDecimal.valueOf(0.00));
        event.setCapacity(50);
        event.setStartTime(LocalDateTime.now().plusDays(1));
        event.setEndTime(LocalDateTime.now().plusDays(2));
        event.setCreationDate(LocalDateTime.now());

        User user = new User();
        user.setId(1);
        user.setUsername("tanya_meh");
        user.setPassword("0000");
        user.setFirstName("Tanya");
        user.setLastName("Mehandzhvieva");
        user.setCreationDate(LocalDateTime.now());

        Reservation reservation = new Reservation();
        reservation.setPurchaseDate(LocalDateTime.of(2024, 1,1,1,1));
        reservation.setEvent(event);
        reservation.setGuest(user);
        reservation.setFirstName("Jane");
        reservation.setLastName("Doe");
        reservation.setEmail("email");

        List<String> messages = validate(reservation);

        assertTrue(messages.contains("Email must be in email format."));
    }

}
