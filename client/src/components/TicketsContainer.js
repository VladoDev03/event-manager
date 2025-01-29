import React from 'react';
import '../style/myTickets.css';
import { useNavigate } from 'react-router-dom'

const TicketsContainer = ({ tickets, setQr, openQrModal, openConfirmCancelModal }) => {
    const navigate = useNavigate();

    const handleEventClick = (eventId) => {
        navigate(`/event/${eventId}`);
    };


    return (
        <div className="ticketsContainer">
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
                                <p className="eventTime">{ticket.eventStartTime}</p>
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
                        <h4 
                        className="cancelReservation"
                        onClick={() => openConfirmCancelModal(ticket.reservationId)}
                        >
                            Cancel reservation
                        </h4>
                    </div>
                </section>
                )))
                : <p>No tickets yet.</p>
            }
        </div>
    );
};

export default TicketsContainer;
