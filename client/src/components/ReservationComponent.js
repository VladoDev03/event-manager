import React, { useState } from 'react';
import { createReservation } from '../services/ReservationService';

const ReservationComponent = ({ openModal, eventId, guestId, firstName, lastName, email }) => {
    const [qrCode, setQrCode] = useState('');
    const [error, setError] = useState('');

    const handleReservation = async () => {
        try {
            const data = await createReservation( 1, 1, "Stoyanka", "Mehandzhieva", "tanya@gmail.com"/* eventId, guestId */);
            setQrCode(data.qrCode);
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
            {/*<h3>Your QR Code</h3>
                    {qrCode && <img src={`data:image/png;base64,${qrCode}`} alt="QR Code" />}
                    <button onClick={closeModal}>Close</button>*/}
        </div>
    );
};

export default ReservationComponent;
