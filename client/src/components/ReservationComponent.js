import React, { use, useState } from 'react';
import { createReservation } from '../services/ReservationService';

const ReservationComponent = ({ openModal, eventId, guestId, firstName, lastName, emial }) => {
    const [error, setError] = useState('');

    const handleReservation = async () => {
        try {
            setError('');
            openModal();
        } catch (error) {
            setError(error.message);
            console.error('Error making reservation:', error);
        }
    };

    return (
        <div className="eventReservationWrapper">
            <div className="reservationBtn">
                <button onClick={handleReservation}>Reserve a spot</button>
            </div>
        </div>
    );
};

export default ReservationComponent;
