package com.example.SpringProject.Location;

import com.example.SpringProject.BaseEntity.BaseEntity;
import com.example.SpringProject.EventOnLocation.EventOnLocation;
import javax.persistence.*;


import java.util.Set;

@Entity
@Table
public class Location extends BaseEntity {
    private String name;
    private String address;
    private String city;
    private String country;
    private int maxCapacity;
    @OneToMany(mappedBy = "location")
    private Set<EventOnLocation> eventOnLocations;

    public Location() {
    }

    public Location(long id, String name, String address, String city, String country, int maxCapacity, Set<EventOnLocation> eventOnLocations) {
        super(id);
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.maxCapacity = maxCapacity;
        this.eventOnLocations = eventOnLocations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Set<EventOnLocation> getEventOnLocations() {
        return eventOnLocations;
    }

    public void setEventOnLocations(Set<EventOnLocation> eventOnLocations) {
        this.eventOnLocations = eventOnLocations;
    }
}
