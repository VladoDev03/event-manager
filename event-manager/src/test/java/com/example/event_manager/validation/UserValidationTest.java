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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserValidationTest {
    private List<String> validate(User user) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(user)
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }

    @Test
    void whenInvalidUsername_thenAssertConstraintViolations() {
        User user = new User();
        user.setId(1);
        user.setUsername("username");
        user.setPassword("0000");
        user.setFirstName("Tanya");
        user.setLastName("Mehandzhvieva");
        user.setCreationDate(LocalDateTime.now());

        List<String> messages = validate(user);

        assertTrue(messages.contains("username and name are not a valid names."));
    }

    @Test
    void whenInvalidFirstName_thenAssertConstraintViolations() {
        User user = new User();
        user.setId(1);
        user.setUsername("tanya_meh");
        user.setPassword("0000");
        user.setFirstName("name");
        user.setLastName("Mehandzhvieva");
        user.setCreationDate(LocalDateTime.now());

        List<String> messages = validate(user);

        assertTrue(messages.contains("name is not a valid name."));
    }

    @Test
    void whenInvalidLastName_thenAssertConstraintViolations() {
        User user = new User();
        user.setId(1);
        user.setUsername("tanya_meh");
        user.setPassword("0000");
        user.setFirstName("Tanya");
        user.setLastName("name");
        user.setCreationDate(LocalDateTime.now());

        List<String> messages = validate(user);

        assertTrue(messages.contains("name is not a valid name."));
    }

    @Test
    void whenInvalidCreationDate_thenAssertConstraintViolations() {
        User user = new User();
        user.setId(1);
        user.setUsername("tanya_meh");
        user.setPassword("0000");
        user.setFirstName("Tanya");
        user.setLastName("Mehandzhvieva");
        user.setCreationDate(LocalDateTime.of(2026,1,1,1,1));

        List<String> messages = validate(user);

        assertTrue(messages.contains("Creation date must be in the past or present."));
    }
    
}
