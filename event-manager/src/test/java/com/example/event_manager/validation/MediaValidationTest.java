package com.example.event_manager.validation;

import com.example.event_manager.entity.Media;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MediaValidationTest {
    @Test
    public void urlCannotBeEmptyTest() {
        Media media = new Media("", "Test");

        List<String> messages = validate(media);

        assertTrue(messages.contains("Url must not be blank!"));
    }

    @Test
    public void urlCannotBeWhiteSpaceTest() {
        Media media = new Media("   ", "Test");

        List<String> messages = validate(media);

        assertTrue(messages.contains("Url must not be blank!"));
    }

    @Test
    public void publicIdCannotBeEmptyTest() {
        Media media = new Media("Test", "");

        List<String> messages = validate(media);

        assertTrue(messages.contains("Public id must not be blank!"));
    }

    @Test
    public void publicIdCannotBeWhiteSpaceTest() {
        Media media = new Media("Test", "   ");

        List<String> messages = validate(media);

        assertTrue(messages.contains("Public id must not be blank!"));
    }

    private List<String> validate(Media media) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        return validator.validate(media)
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }
}