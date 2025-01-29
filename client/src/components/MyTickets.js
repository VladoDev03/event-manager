import React, { useEffect, useState} from 'react';
import Modal from 'react-modal';
import Navbar from './NavBar';
import TicketsContainer from './TicketsContainer';
import { deleteReservation, fetchReservations } from '../services/reservationService';
import '../style/myTickets.css';

const MyTickets = () => {
    const [tickets, setTickets] = useState([]);
    const [qrCode, setQrCode] = useState('');
    const [qrModalIsOpen, setqrModalIsOpen] = useState(false);
    const [confirmCancelModalIsOpen, setConfirmCancelModalIsOpen] = useState(false);
    const [reservationId, setReservationId] = useState('');

    useEffect(() => {
            const fetchData = async () => {
                try {
                    const ticketsData = await fetchReservations(1 /*guest id*/);
                    setTickets(ticketsData || []);
                } catch (error) {
                    console.error('Error fetching tickets', error);
                }
            };
    
            fetchData();
        }, []
    );

    const setQr = (qr) => {
        setQrCode(qr);
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
        console.log(reservationId);
    }

    const closeConfirmCancelModal = () => {
        setConfirmCancelModalIsOpen(false);
        setReservationId();
        console.log(reservationId)
    }

    const cancelReservation = async (e) => {
        e.preventDefault();
        try {
            await deleteReservation(reservationId);
        } catch (error) {
            alert('Error creating reservation. Please try again.');
        }
    }
        
	return (
        <div id="myTickets">
            <Navbar />

            <div className="mainContainer">
                <div className="myTicketsContainer">
                    <TicketsContainer tickets={tickets} setQr={setQr} openQrModal={openQrModal} openConfirmCancelModal={openConfirmCancelModal}/>
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
                    {qrCode && <img src={`data:image/png;base64,${qrCode}`} alt="QR Code" />}
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
