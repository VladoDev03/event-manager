package com.example.event_manager.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.TreeSet;

public class InvalidNamesValidator implements ConstraintValidator<InvalidNames, String> {
    private final Set<String> reservedWord = new TreeSet<>();

    @Override
    public void initialize(InvalidNames constraintAnnotation) {
        reservedWord.addAll(Set.of("event", "name", "address", "location"));
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return reservedWord.stream().map(String::toLowerCase).noneMatch(value::contains);
    }
}
