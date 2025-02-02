package com.example.event_manager.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservationTicketDto {
    //Event info
    private long eventId;
    private String eventTitle;
    private LocalDateTime eventStartTime;
    private String eventLocation;
    private BigDecimal eventPrice;

    //Reservation info
    private long reservationId;
    private String reservationContactNames;
    private String reservationEmail;
    private String reservationQrCode;

    //Guest info
    private long guestId;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public LocalDateTime getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(LocalDateTime eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public BigDecimal getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(BigDecimal eventPrice) {
        this.eventPrice = eventPrice;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservationContactNames() {
        return reservationContactNames;
    }

    public void setReservationContactNames(String reservationContactNames) {
        this.reservationContactNames = reservationContactNames;
    }

    public String getReservationEmail() {
        return reservationEmail;
    }

    public void setReservationEmail(String reservationEmail) {
        this.reservationEmail = reservationEmail;
    }

    public String getReservationQrCode() {
        return reservationQrCode;
    }

    public void setReservationQrCode(String reservationQrCode) {
        this.reservationQrCode = reservationQrCode;
    }

    public long getGuestId() {
        return guestId;
    }

    public void setGuestId(long guestId) {
        this.guestId = guestId;
    }
}
