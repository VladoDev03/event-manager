package com.example.event_manager.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Location extends BaseEntity {
    private String name;
    private String address;
    private String city;
    private String country;
    private int maxCapacity;
    @OneToMany (mappedBy = "location")
    private Set<EventOnLocation> eventsOnLocation;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Set<EventOnLocation> getEventsOnLocation() {
        return eventsOnLocation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setEventsOnLocation(Set<EventOnLocation> eventsOnLocation) {
        this.eventsOnLocation = eventsOnLocation;
    }
}
