package com.example.SpringProject.Event.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EventDto {
    private String title;
    private String description;
    private BigDecimal price;
    private int capacity;
    private LocalDate creationDate;
    private LocalDate updateDate;
    private Long creatorId;


    public EventDto() {
    }

    public EventDto(String title, String description,
                    BigDecimal price, int capacity, LocalDate creationDate, LocalDate updateDate, Long creatorId) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.capacity = capacity;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.creatorId = creatorId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
