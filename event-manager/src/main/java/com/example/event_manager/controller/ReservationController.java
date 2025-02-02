package com.example.event_manager.controller;

import com.example.event_manager.dto.CreateReservationDto;
import com.example.event_manager.dto.ReservationDto;
import com.example.event_manager.dto.ReservationTicketDto;
import com.example.event_manager.entity.Reservation;
import com.example.event_manager.exception.EntityNotFoundException;
import com.example.event_manager.exception.EventFinishedException;
import com.example.event_manager.service.ReservationService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody CreateReservationDto request) throws WriterException, IOException {
        try {
            ReservationDto reservationDto = reservationService.createReservation(request);
            return new ResponseEntity<>(reservationDto, HttpStatus.CREATED);
        } catch (IllegalStateException | EventFinishedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>("Reservation deleted successfully", HttpStatus.OK);
    }


    @GetMapping("/future/{guestId}")
    public List<ReservationTicketDto> getFutureGuestReservations(@PathVariable long guestId) {
        System.out.println("loading tickets");
        return reservationService.getFutureGuestReservations(guestId);
    }

    @GetMapping("/previous/{guestId}")
    public List<ReservationTicketDto> getPreviousGuestReservations(@PathVariable long guestId) {
        System.out.println("loading tickets");
        return reservationService.getPreviousGuestReservations(guestId);
    }
}
