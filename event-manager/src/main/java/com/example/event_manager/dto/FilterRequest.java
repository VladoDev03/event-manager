package com.example.event_manager.dto;

import com.example.event_manager.entity.Event;
import com.example.event_manager.entity.EventCategory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FilterRequest {
    private List<DisplayEventDto> initialEvents;
    private EventCategory category;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private LocalDate startDate;
    private LocalDate endDate;

    public List<DisplayEventDto> getInitialEvents() {
        return initialEvents;
    }

    public void setInitialEvents(List<DisplayEventDto> initialEvents) {
        this.initialEvents = initialEvents;
    }

    public EventCategory getCategory() {
        return category;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDateTime getStartDateTime() {
        if(startDate == null) {
            return null;
        }
        return LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), 0,0);
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDateTime getEndDateTime() {
        if(endDate == null) {
            return null;
        }
        return LocalDateTime.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth(), 23,59);
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}
