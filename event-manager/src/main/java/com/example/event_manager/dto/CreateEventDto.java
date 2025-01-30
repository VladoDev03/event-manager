package com.example.event_manager.dto;

import com.example.event_manager.entity.EventCategory;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateEventDto {
    private long id;
    private String title;
    private String description;
    private BigDecimal price;
    private int capacity;
    private LocalDateTime creationDate;
    private EventCategory category;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;

    public CreateEventDto() {
    }

    public CreateEventDto(String title, String description, BigDecimal price, int capacity, LocalDateTime creationDate, EventCategory category, LocalDateTime startTime, LocalDateTime endTime, String location) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.capacity = capacity;
        this.creationDate = creationDate;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
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

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public EventCategory getCategory() {
        return category;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CreateEventDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", capacity=" + capacity +
                ", creationDate=" + creationDate +
                ", category=" + category +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", location" + location +
                '}';
    }
}