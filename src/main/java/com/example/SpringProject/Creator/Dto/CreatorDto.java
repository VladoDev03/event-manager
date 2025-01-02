package com.example.SpringProject.Creator.Dto;

import com.example.SpringProject.Event.Dto.EventDto;
import com.example.SpringProject.Event.Event;

import java.util.List;

public class CreatorDto {
    private String name;

    public CreatorDto(String name) {
        this.name = name;
    }

    public CreatorDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
