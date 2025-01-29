import React, { use, useState } from 'react';
import { createReservation } from '../services/reservationService';

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
            {/* <Modal
                isOpen={modalIsOpen}
                onRequestClose={closeModal}
                contentLabel="Reservation QR Code"
                className="modal"
                overlayClassName="modalOverlay"
            >
                <h3>Your QR Code</h3>
                {qrCode && <img src={`data:image/png;base64,${qrCode}`} alt="QR Code" />}
                <button onClick={closeModal}>Close</button>
            </Modal> */}
        </div>
    );
};

export default ReservationComponent;
