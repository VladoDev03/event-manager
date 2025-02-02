import React, { useEffect, useState, useContext } from 'react';
import { Navigate, useNavigate } from "react-router-dom";
import Modal from 'react-modal';
import Navbar from './NavBar';
import TicketsContainer from './TicketsContainer';
import { deleteReservation, fetchFutureReservations, fetchPreviousReservations } from '../services/ReservationService';
import { AuthContext } from '../contexts/AuthContext';

import '../style/myTickets.css';

const MyTickets = () => {
    const navigate = useNavigate();
    const { user } = useContext(AuthContext);

    useEffect(() => {
        });
    
    const [tickets, setTickets] = useState([]);
    const [previousTickets, setPreviousTickets] = useState([]);
    const [qrCode, setQrCode] = useState('')
    const [qrCodeId, setQrCodeId] = useState('');
    const [qrModalIsOpen, setqrModalIsOpen] = useState(false);
    const [confirmCancelModalIsOpen, setConfirmCancelModalIsOpen] = useState(false);
    const [reservationId, setReservationId] = useState('');

    useEffect(() => {
        if(!user.userId){
          navigate("/login");
    }else{
        fetchData();
    }
        
    }, []
    );

    const fetchData = async () => {
        try {
            const ticketsData = await fetchFutureReservations(user.userId);
            setTickets(ticketsData || []);
        } catch (error) {
            console.error('Error fetching tickets', error);
        }

        try {
            const previousTicketsData = await fetchPreviousReservations(user.userId);
            setPreviousTickets(previousTicketsData || []);
        } catch (error) {
            console.error('Error fetching tickets', error);
        }
    };

    const setQr = (qr) => {
        setQrCode(qr);
    }

    const setQrId = (qrId) => {
        setQrCodeId(qrId);
    }

    const openQrModal = () => {
        setqrModalIsOpen(true);
    }
   
   const closeQrModal = () => {
        setqrModalIsOpen(false);
    }

    const openConfirmCancelModal = (reservation_id) => {
        setConfirmCancelModalIsOpen(true);
        setReservationId(reservation_id);
    }

    const closeConfirmCancelModal = () => {
        setConfirmCancelModalIsOpen(false);
        setReservationId();
    }

    const cancelReservation = async (e) => {
        e.preventDefault();
        try {
            await deleteReservation(reservationId);
            closeConfirmCancelModal();
            fetchData();
        } catch (error) {
            alert('Error creating reservation. Please try again.');
        }
    }
        
	return (
        <div id="myTickets">
            <Navbar />

            <div className="mainContainer">
                <div className="myTicketsContainer">
                    <TicketsContainer tickets={tickets} previousTickets={previousTickets} setQr={setQr} setQrId={setQrId} openQrModal={openQrModal} openConfirmCancelModal={openConfirmCancelModal}/>
                </div>
            </div>

            <Modal 
                isOpen={qrModalIsOpen} 
                onRequestClose={closeQrModal} 
                contentLabel="Reservation QR Code" 
                className="modal" 
                overlayClassName="modalOverlay" 
            >
                <div className="container" id="qr-code"> 
                    <h3>Your QR Code</h3>
                    <button className="close-modal" onClick={closeQrModal}>&#10005;</button>
                    {qrCode && 
                    <a className="ticketDownload" href={`data:image/png;base64,${qrCode}`} download={`ticket#${qrCodeId}`}>
                    <img src={`data:image/png;base64,${qrCode}`} alt="QR Code" />
                    <h4>Download ticket</h4>
                    </a>
                    }
                </div>
            </Modal> 

            <Modal 
                isOpen={confirmCancelModalIsOpen} 
                onRequestClose={closeConfirmCancelModal} 
                contentLabel="Confirm cancelation" 
                className="modal" 
                overlayClassName="modalOverlay" 
            >
                <div className="container" id="confirm-cancelation"> 
                    <h3>Are you sure you want to cancel this reservation?</h3>
                    <button className="close-modal" onClick={closeConfirmCancelModal}>&#10005;</button>
                    <button className="confirm-cancel-btn" id="keep-reservation" onClick={closeConfirmCancelModal}>No, keep reservation</button>
                    <button className="confirm-cancel-btn" id="conf-cancel-reservation" onClick={cancelReservation}>Yes, cancel reservation</button>
                </div>
            </Modal> 
        </div>
        
    );

    
};

export default MyTickets;