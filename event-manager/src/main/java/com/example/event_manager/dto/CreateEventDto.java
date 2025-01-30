package com.example.event_manager.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateEventDto {
    private long id;
    private String title;
    private String description;
    private BigDecimal price;
    private int capacity;
    private LocalDateTime creationDate;

    public CreateEventDto() {
    }

    public CreateEventDto(String title, String description, BigDecimal price, int capacity, LocalDateTime creationDate) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.capacity = capacity;
        this.creationDate = creationDate;
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

    public BigDecimal getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
