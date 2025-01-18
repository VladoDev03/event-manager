import React, { use, useState } from 'react';
import Modal from 'react-modal';
import { createReservation } from '../services/ReservationService';

const ReservationComponent = ({ eventId, guestId, firstName, lastName, emial }) => {
    const [qrCode, setQrCode] = useState('');
    const [modalIsOpen, setModalIsOpen] = useState(false);
    const [error, setError] = useState('');

    const handleReservation = async () => {
        try {
            const data = await createReservation( 1, 1, "Stoyanka", "Mehandzhieva", "tanya@gmail.com"/* eventId, guestId */);
            setQrCode(data.qrCode);
            setError('');
            setModalIsOpen(true);
        } catch (error) {
            setError(error.message);
            console.error('Error making reservation:', error);
        }
    };

    const closeModal = () => {
        setModalIsOpen(false);
        setQrCode('');
    };

    return (
        <div className="eventReservationWrapper">
            <div className="reservationBtn">
                <button onClick={handleReservation}>Reserve a spot</button>
            </div>
            <Modal
                isOpen={modalIsOpen}
                onRequestClose={closeModal}
                contentLabel="Reservation QR Code"
                className="modal"
                overlayClassName="modalOverlay"
            >
                <h3>Your QR Code</h3>
                {qrCode && <img src={`data:image/png;base64,${qrCode}`} alt="QR Code" />}
                <button onClick={closeModal}>Close</button>
            </Modal>
        </div>
    );
};

// Optional: Add some styles for the modal
Modal.setAppElement('#root');
const customStyles = {
    content: {
        top: '50%',
        left: '50%',
        right: 'auto',
        bottom: 'auto',
        marginRight: '-50%',
        transform: 'translate(-50%, -50%)'
    }
};
Modal.defaultStyles.content = { ...Modal.defaultStyles.content, ...customStyles };

Modal.setAppElement('#root');
export default ReservationComponent;
