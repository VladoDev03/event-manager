package com.example.event_manager.controller;

import com.example.event_manager.entity.EventCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EventCategoryController {
    @GetMapping("/categories")
    public List<String> getCategories() {
        return Arrays.stream(EventCategory.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
