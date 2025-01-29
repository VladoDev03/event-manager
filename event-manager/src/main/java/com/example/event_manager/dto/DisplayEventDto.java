package com.example.event_manager.dto;

import com.example.event_manager.entity.EventCategory;
import com.example.event_manager.entity.Location;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DisplayEventDto {
    private long id;
    private String title;
    private String description;
    private EventCategory category;
    private String locationName;
    private BigDecimal price;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public DisplayEventDto(long id, String title, String description, EventCategory category, String locationName, BigDecimal price, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.locationName = locationName;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventCategory getCategory() {
        return category;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
