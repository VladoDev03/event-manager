package com.example.event_manager;

import com.example.event_manager.configuration.SessionFactoryUtil;
import com.example.event_manager.dao.*;
import com.example.event_manager.entity.*;
import com.example.event_manager.service.EventService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class EventManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(EventManagerApplication.class, args);
		SessionFactoryUtil.getSessionFactory().openSession();

		/**/
		Location location1 = new Location();
		location1.setName("Ndk Sofia");
		location1.setAddress("Vitosha street 45");
		location1.setCity("Sofia");
		location1.setCountry("Bulgaria");
		location1.setMaxCapacity(45);
		LocationDao.createLocation(location1);

		Location location2 = new Location();
		location2.setName("Ndk Sofia podlez");
		location2.setAddress("Vitosha street 45");
		location2.setCity("Sofia");
		location2.setCountry("Bulgaria");
		location2.setMaxCapacity(45);
		LocationDao.createLocation(location2);

		Location location3 = new Location();
		location3.setName("Vitoshka");
		location3.setAddress("Vitosha street 45");
		location3.setCity("Sofia");
		location3.setCountry("Bulgaria");
		location3.setMaxCapacity(45);
		LocationDao.createLocation(location3);

		Location location4 = new Location();
		location4.setName("Ndk plovdiv");
		location4.setAddress("Vitosha street 45");
		location4.setCity("Plovdiv");
		location4.setCountry("Bulgaria");
		location4.setMaxCapacity(45);
		LocationDao.createLocation(location4);

		Event event1 = new Event();
		event1.setTitle("koncert");
		event1.setPrice(BigDecimal.valueOf(25));
		event1.setCapacity(30);
		event1.setCategory(EventCategory.MUSIC);
		event1.setCreationDate(LocalDateTime.now());
		EventDao.createEvent(event1);

/*		Reservation reservation = new Reservation();
		reservation.setEvent(event1);
		ReservationDao.saveReservation(reservation);*/

		Event event2 = new Event();
		event2.setTitle("subiranka");
		event2.setPrice(BigDecimal.valueOf(2));
		EventDao.createEvent(event2);

		Event event3 = new Event();
		event3.setTitle("teatur");
		event3.setPrice(BigDecimal.valueOf(20));
		EventDao.createEvent(event3);

		EventOnLocation eventOnLocation1 = new EventOnLocation();
		eventOnLocation1.setLocation(location1);
		eventOnLocation1.setEvent(event1);
		eventOnLocation1.setStartTime(LocalDateTime.of(2024,12,27,18,30));
		eventOnLocation1.setEndTime(LocalDateTime.of(2024,12,27,22,30));
		EventOnLocationDao.saveEventOnLocation(eventOnLocation1);

		EventOnLocation eventOnLocation2 = new EventOnLocation();
		eventOnLocation2.setLocation(location1);
		eventOnLocation2.setEvent(event2);
		eventOnLocation2.setStartTime(LocalDateTime.of(2024,12,29,18,30));
		eventOnLocation2.setEndTime(LocalDateTime.of(2024,12,30,20,30));
		EventOnLocationDao.saveEventOnLocation(eventOnLocation2);

		EventOnLocation eventOnLocation3 = new EventOnLocation();
		eventOnLocation3.setLocation(location2);
		eventOnLocation3.setEvent(event1);
		eventOnLocation3.setStartTime(LocalDateTime.of(2025,12,27,18,30));
		eventOnLocation3.setEndTime(LocalDateTime.of(2025,12,27,22,30));
		EventOnLocationDao.saveEventOnLocation(eventOnLocation3);

		EventOnLocation eventOnLocation4 = new EventOnLocation();
		eventOnLocation4.setLocation(location3);
		eventOnLocation4.setEvent(event3);
		eventOnLocation4.setStartTime(LocalDateTime.of(2025,12,27,18,30));
		eventOnLocation4.setEndTime(LocalDateTime.of(2025,12,27,22,30));
		EventOnLocationDao.saveEventOnLocation(eventOnLocation4);

		EventService eventService = new EventService();
		List<Event> initialEvents = EventDao.getEvents();
		for(Event event :initialEvents){
			System.out.println("Initial " + event);
		}

		List<Event> eventsByFilters = eventService.filterEvents(initialEvents, null, BigDecimal.valueOf(2), BigDecimal.valueOf(20), LocalDateTime.of(2024,12,27,20,20), LocalDateTime.of(2024,12,28,8,20));
		for(Event event :eventsByFilters){
			System.out.println(event);
		}
		System.out.println("done");

		Guest guest = new Guest();
		guest.setFirstName("Tanya");
		guest.setLastName("Mehandzhieva");
		guest.setUsername("tanya_meh");
		guest.setPassword("0000");
		GuestDao.saveGuest(guest);
		/**/

		System.out.println(EventDao.isEventFull(1));
	}
}
