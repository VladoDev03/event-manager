package com.example.event_manager.controller;

import com.example.event_manager.dto.CreateReservationDto;
import com.example.event_manager.dto.ReservationDto;
import com.example.event_manager.dto.ReservationTicketDto;
import com.example.event_manager.entity.Reservation;
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
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody CreateReservationDto request) throws WriterException, IOException {
        try {
            ReservationDto reservationDto = reservationService.createReservation(request);
            System.out.println(reservationDto.getQrCode());
            return new ResponseEntity<>(reservationDto, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>("Reservation deleted successfully", HttpStatus.OK);
    }


    @GetMapping("/{guestId}")
    public List<ReservationTicketDto> getGuestReservations(@PathVariable long guestId) {
        System.out.println("loading tickets");
        return reservationService.getGuestReservations(guestId);
    }
}
