import React from 'react';
import '../style/myTickets.css';
import { useNavigate } from 'react-router-dom';
import { format } from 'date-fns';


const TicketsContainer = ({ tickets, previousTickets, setQr, setQrId, openQrModal, openConfirmCancelModal }) => {
    const navigate = useNavigate();

    const handleEventClick = (eventId) => {
        navigate(`/event/${eventId}`);
    };

    const formatDateTime = (dateTimeString) => {
        return format(dateTimeString, "dd MMMM yyyy HH:mm");
      };

    return (
        <div className="ticketsContainer">
            <div className="futureTickets">
                <h1 className="ticketType">Tickets for future events</h1>
                {
                tickets.length > 0 ?
                    (tickets.map((ticket) => (
                    <section key={ticket.reservationId} 
                    className="ticketWrapper"
                    >
                        <div className="ticketInfo">
                            <div className="eventInfo" 
                            onClick={() => handleEventClick(ticket.eventId)}
                            >
                                <h2 className="eventTitle">{ticket.eventTitle}</h2>
                                <div className="eventDetails">
                                    <p className="eventTime">{formatDateTime(ticket.eventStartTime)}</p>
                                    <p className="eventLocation">{ticket.eventLocation}</p>
                                    <p className="eventPrice">{ticket.eventPrice} BGN</p>
                                </div>
                            </div>

                            <div className="contactInfo">
                                <p className="contactNames">{ticket.reservationContactNames}</p>
                                <p className="contactEmail">{ticket.reservationEmail}</p>
                            </div>
                        </div>

                        <div className="ticketButtons">
                            <h4 
                            className="openTicket"
                            onClick={() => {
                                openQrModal(ticket)
                                setQr(ticket.reservationQrCode)
                                setQrId(ticket.reservationId)
                            }}
                            >
                                Open ticket
                            </h4>
                            <h4 
                            className="cancelReservation"
                            onClick={() => openConfirmCancelModal(ticket.reservationId)}
                            >
                                Cancel reservation
                            </h4>
                        </div>
                    </section>
                    )))
                    : <p className="noTickets">No tickets for future events yet.</p>
                }
            </div>

            <div className="futureTickets">
                <h1 className="ticketType">Tickets for previous events</h1>
                {
                previousTickets.length > 0 ?
                    (previousTickets.map((ticket) => (
                    <section key={ticket.reservationId} 
                    className="ticketWrapper"
                    >
                        <div className="ticketInfo">
                            <div className="eventInfo" 
                            onClick={() => handleEventClick(ticket.eventId)}
                            >
                                <h2 className="eventTitle">{ticket.eventTitle}</h2>
                                <div className="eventDetails">
                                    <p className="eventTime">{formatDateTime(ticket.eventStartTime)}</p>
                                    <p className="eventLocation">{ticket.eventLocation}</p>
                                    <p className="eventPrice">{ticket.eventPrice} BGN</p>
                                </div>
                            </div>

                            <div className="contactInfo">
                                <p className="contactNames">{ticket.reservationContactNames}</p>
                                <p className="contactEmail">{ticket.reservationEmail}</p>
                            </div>
                        </div>

                        <div className="ticketButtons">
                            <h4 
                            className="openTicket"
                            onClick={() => {
                                openQrModal(ticket)
                                setQr(ticket.reservationQrCode)}}
                            >
                                Open ticket
                            </h4>
                        </div>
                    </section>
                    )))
                    : <p className="noTickets">No tickets for future events yet.</p>
                }
            </div>
        </div>
    );
};

export default TicketsContainer;