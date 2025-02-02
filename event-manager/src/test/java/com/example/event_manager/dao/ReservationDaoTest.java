package com.example.event_manager.dao;

import com.example.event_manager.configuration.SessionFactoryUtil;
import com.example.event_manager.entity.Event;
import com.example.event_manager.entity.Reservation;
import com.example.event_manager.entity.User;
import com.example.event_manager.exception.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Ensures tests run in a specific order
class ReservationDaoTest {

    private static long testReservationId;
    private static long testGuestId;
    private static long testEventId;

    @BeforeAll
    static void setup() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Event event = new Event();
            event.setTitle("Lecture");
            event.setDescription("Lecture in NBU");
            event.setLocation("NBU");
            event.setPrice(BigDecimal.valueOf(0.00));
            event.setCapacity(50);
            event.setStartTime(LocalDateTime.now().plusDays(1));
            event.setEndTime(LocalDateTime.now().plusDays(2));
            event.setCreationDate(LocalDateTime.now());
            session.save(event); // Save first
            session.flush(); // Ensure event gets an ID before moving on
            testEventId = event.getId();

            User user = new User();
//            user.setId(1);
            user.setUsername("tanya_meh");
            user.setPassword("0000");
            user.setFirstName("Tanya");
            user.setLastName("Mehandzhvieva");
            user.setCreationDate(LocalDateTime.now());
            session.save(user); // Save first
            session.flush(); // Ensure guest gets an ID before moving on
            testGuestId = user.getId();

            // Commit the transaction to persist event and user
            transaction.commit();
        }

        // Start a new transaction to save reservation
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Event event = session.get(Event.class, testEventId); // Retrieve persisted event
            User guest = session.get(User.class, testGuestId); // Retrieve persisted user

            Reservation reservation = new Reservation();
            reservation.setPurchaseDate(LocalDateTime.of(2024, 1,1,1,1));
            reservation.setEvent(event);
            reservation.setGuest(guest);
            reservation.setFirstName("Jane");
            reservation.setLastName("Doe");
            reservation.setEmail("janedoe@mail.com");

            session.save(reservation);
            transaction.commit();

            testReservationId = reservation.getId();
        }
    }


    @Test
    @Order(1)
    void testGetReservationById_Success() {
        Reservation reservation = null;
        try {
            reservation = ReservationDao.getReservationById(testReservationId);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(reservation);
        assertEquals("Jane", reservation.getFirstName());
        assertEquals(testReservationId, reservation.getId());
    }

    @Test
    @Order(2)
    void testGetReservationById_NotFound() {
        long nonExistentId = 9999L;
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            ReservationDao.getReservationById(nonExistentId);
        });
        assertEquals("Entity " + nonExistentId + " is not found.", exception.getMessage());
    }

    @Test
    @Order(3)
    void testGetFutureGuestReservations() {
        List<Reservation> reservations = ReservationDao.getFutureGuestReservations(testGuestId);
        assertFalse(reservations.isEmpty());
        assertEquals(1, reservations.size());
    }

    @Test
    @Order(4)
    void testGetPreviousGuestReservations_Empty() {
        List<Reservation> reservations = ReservationDao.getPreviousGuestReservations(testGuestId);
        assertTrue(reservations.isEmpty());
    }

    @Test
    @Order(5)
    void testDeleteReservation() {
        Reservation reservation = null;
        try {
            reservation = ReservationDao.getReservationById(testReservationId);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(reservation);

        ReservationDao.deleteReservation(reservation);

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            ReservationDao.getReservationById(testReservationId);
        });
        assertEquals("Entity " + testReservationId + " is not found.", exception.getMessage());
    }

}
