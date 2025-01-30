import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Modal from 'react-modal';

import Navbar from './NavBar';
import EventInfo from './EventInfo';
import ReservationComponent from './ReservationComponent';

import '../style/eventStyle.css';
import '../style/ReservationForm.css';

import { createReservation } from '../services/reservationService';

const EventPage = () => {
    
    const navigate = useNavigate();
    
    const [modalIsOpen, setModalIsOpen] = useState(false);
    const [successModalIsOpen, setSuccessModalIsOpen] = useState(false);

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');

    const openModal = () => {
        setModalIsOpen(true);
    }

    const closeModal = () => {
        setModalIsOpen(false);
    }

    const openSuccessModal = () => {
        setModalIsOpen(false);
        setSuccessModalIsOpen(true);
    }

    const closeSuccessModal = () => {
        setSuccessModalIsOpen(false);
    }

    const goToMyTickets = () => {
        setSuccessModalIsOpen(false);
        navigate('/myTickets');
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await createReservation(1, 1, firstName, lastName, email);
            openSuccessModal();
        } catch (error) {
            alert('Error creating reservation. Please try again.');
        }
    }

    return (
        <div id="eventPage">
            <Navbar />
            <div className="mainContainer">
                <div className="bannerContainer">
                    <img src="https://design-assets.adobeprojectm.com/content/download/express/public/urn:aaid:sc:VA6C2:d97d126b-e18a-5797-8802-2b457ac10518/component?assetType=TEMPLATE&etag=867813dd4f024cae8df2d0dfd2a91435&revision=e707f25d-6eef-4d42-9881-f62be17f2998&component_id=f5e79f54-25f0-40f7-9a1e-a87b7f696f81" alt="banner" />
                </div>
                <div className="eventAndReservationContainer">
                    <EventInfo />
                    <ReservationComponent openModal={openModal}/>
                </div>
            </div>

            <Modal 
                isOpen={modalIsOpen} 
                onRequestClose={closeModal} 
                contentLabel="Reservation Form" 
                className="modal" 
                overlayClassName="modalOverlay" 
            > 
                <div className="container"> 
                    <h1>Event Reservation Form</h1> 
                    <button className="close-modal" onClick={closeModal}>&#10005;</button> 
                    <form> 
                        <h2>Contact Information</h2> 
                        <div className="names"> 
                            <div className="first-name"> 
                                <label htmlFor="first-name">First Name:</label> 
                                <input 
                                type="text" 
                                id="first-name"
                                value={firstName}
                                onChange={(e) => setFirstName(e.target.value)}
                                required /> 
                            </div> 
                            <div className="last-name"> 
                                <label htmlFor="last-name">Last Name:</label> 
                                <input 
                                type="text" 
                                id="last-name" 
                                value={lastName}
                                onChange={(e) => setLastName(e.target.value)}
                                required /> 
                            </div>
                        </div> 
                            
                        <label htmlFor="email">Email Address:</label> 
                        <input 
                        type="email" 
                        id="email" 
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required /> 

                        <button className="finish-reservation" type="submit" onClick={handleSubmit}>Finish reservation</button> 
                    </form> 
                </div> 
            </Modal>

            <Modal 
                isOpen={successModalIsOpen} 
                onRequestClose={closeSuccessModal} 
                contentLabel="Reservation Success" 
                className="modal" 
                overlayClassName="modalOverlay" 
            >
                <div className="container" id="successful-reservation">
                    <button className="close-modal" onClick={closeSuccessModal}>&#10005;</button> 
                    <h1>Reservation successful!</h1> 
                    <p>Your reservation has been successfully made.</p> 
                    <button className="go-to-tickets" onClick={goToMyTickets}>Go to my tickets</button> 
                </div>
            </Modal>
        </div>
    );
};

Modal.setAppElement('#root');
export default EventPage;
