package com.example.event_manager.validation;

import com.example.event_manager.entity.Rating;
import com.example.event_manager.entity.Review;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReviewValidationTest {
    @Test
    public void commentCannotBeEmptyTest() {
        Review review = new Review(Rating.EXCELLENT, "", LocalDateTime.now());

        List<String> messages = validate(review);

        assertTrue(messages.contains("Comment must not be blank!"));
    }

    @Test
    public void commentCannotBeWhiteSpaceTest() {
        Review review = new Review(Rating.EXCELLENT, "  ", LocalDateTime.now());

        List<String> messages = validate(review);

        assertTrue(messages.contains("Comment must not be blank!"));
    }

    @Test
    public void commentMaxLengthTest() {
        Review review = new Review(
                Rating.EXCELLENT,
                "aaaaaaaaaa" +
                        "aaaaaaaaaa" +
                        "aaaaaaaaaa" +
                        "aaaaaaaaaa" +
                        "aaaaaaaaaa" +
                        "aaaaaaaaaa" +
                        "aaaaaaaaaa" +
                        "aaaaaaaaaa" +
                        "aaaaaaaaaa" +
                        "aaaaaaaaaa" +
                        "aaaaaaaaaa",
                LocalDateTime.now()
        );

        List<String> messages = validate(review);

        assertTrue(messages.contains("Comment must not be longer than 100 characters!"));
    }

    @Test
    public void reviewTimeCannotBeInTheFutureTest() {
        Review review = new Review(Rating.EXCELLENT, "Very Vool!", LocalDateTime.now().plusDays(1));

        List<String> messages = validate(review);

        assertTrue(messages.contains("Review time cannot be in the future!"));
    }

    private List<String> validate(Review review) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        return validator.validate(review)
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }
}