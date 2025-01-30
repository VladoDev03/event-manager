import { React, useContext, useState } from 'react';
import { useNavigate } from "react-router-dom";

import { AuthContext } from '../contexts/AuthContext';

const ReservationComponent = ({ openModal, eventId, guestId, firstName, lastName, emial }) => {
    const { user } = useContext(AuthContext);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleReservation = async () => {
        try {
            if (user.userId) {
                setError('');
                openModal();
            } else {
                navigate('/login');
            }
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
