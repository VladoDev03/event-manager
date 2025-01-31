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
		event1.setLocation("Ndk Sofia");
		event1.setStartTime(LocalDateTime.of(2024,12,27,18,30));
		event1.setEndTime(LocalDateTime.of(2024,12,27,22,30));
		EventDao.createEvent(event1);

		Event event2 = new Event();
		event2.setTitle("subiranka");
		event2.setPrice(BigDecimal.valueOf(2));
		event2.setCapacity(30);
		event2.setCategory(EventCategory.CULTURE);
		event2.setLocation("Ndk Sofia");
		event2.setStartTime(LocalDateTime.of(2024,12,29,18,30));
		event2.setEndTime(LocalDateTime.of(2024,12,30,20,30));
		EventDao.createEvent(event2);

		Event event3 = new Event();
		event3.setTitle("teatur");
		event3.setPrice(BigDecimal.valueOf(20));
		event3.setCapacity(30);
		event3.setCategory(EventCategory.CULTURE);
		event3.setLocation("Vitoshka");
		event3.setStartTime(LocalDateTime.of(2025,12,27,18,30));
		event3.setEndTime(LocalDateTime.of(2025,12,27,22,30));
		EventDao.createEvent(event3);

		EventService eventService = new EventService();
		List<Event> initialEvents = EventDao.getEvents();
		for(Event event :initialEvents){
			System.out.println("Initial " + event);
		}

//		List<Event> eventsByFilters = eventService.filterEvents(initialEvents, null, BigDecimal.valueOf(2), BigDecimal.valueOf(20), LocalDateTime.of(2024,12,27,20,20), LocalDateTime.of(2024,12,28,8,20));
//		for(Event event :eventsByFilters){
//			System.out.println(event);
//		}
		System.out.println("done");

		User guest = new User();
		guest.setFirstName("Tanya");
		guest.setLastName("Mehandzhieva");
		guest.setUsername("tanya_meh");
		guest.setPassword("0000");
		UserDao.saveUser(guest);
		/**/

		System.out.println(EventDao.isEventFull(1));

		EventService eventService1 = new EventService();
		System.out.println(eventService1.getEventsByName("e"));
	}
}
