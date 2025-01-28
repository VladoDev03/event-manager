package com.example.event_manager.dto;

import com.example.event_manager.entity.EventCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DisplayEventDto {
    private long id;
    private String title;
    private String description;
    private EventCategory category;
    private BigDecimal price;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public DisplayEventDto(long id, String title, String description, EventCategory category, BigDecimal price/*, LocalDateTime startTime, LocalDateTime endTime*/) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
//        this.startTime = startTime;
//        this.endTime = endTime;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public EventCategory getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
