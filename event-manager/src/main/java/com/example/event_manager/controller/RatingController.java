package com.example.event_manager.controller;

import com.example.event_manager.entity.EventCategory;
import com.example.event_manager.entity.Rating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RatingController {
    @GetMapping("/ratings")
    public List<String> getRatings() {
        return Arrays.stream(Rating.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
