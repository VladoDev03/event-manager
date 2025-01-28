import React, { useState } from 'react';
import Navbar from './NavBar';
import EventInfo from './EventInfo';
import ReservationComponent from './ReservationComponent';
import '../style/eventStyle.css';
import '../style/ReservationForm.css'
import Modal from 'react-modal';

const EventPage = () => {
    const [modalIsOpen, setModalIsOpen] = useState(false);

    const openModal = () => {
        setModalIsOpen(true);
    }; 
    
    const closeModal = () => {
        setModalIsOpen(false);
    };

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
                    contentLabel="Reservation QR Code"
                    className="modal"
                    overlayClassName="modalOverlay"
                >
                    <div className="container">
                        <h1>Event Reservation Form</h1>
                        <form>
                            <h2>Contact Information</h2>
                            <div className="names">
                                <div className="first-name">
                                    <label htmlFor="first-name">First Name:</label>
                                    <input type="text" id="first-name" name="first-name" required />
                                </div>
                                <div className="last-name">
                                    <label htmlFor="last-name">Last Name:</label>
                                    <input type="text" id="last-name" name="last-name" required />
                                </div>
                            </div>
                            <label htmlFor="email">Email Address:</label>
                            <input type="email" id="email" name="email" required />

                            <h2>Order Summary</h2>
                            <div className="order-summary">
                                <p><strong>Event:</strong> Speech Storytelling Masterclass</p>
                                <p><strong>Date:</strong> Wednesday, April 9, 9:00 - 10:30 pm EEST</p>
                                <p><strong>Location:</strong> NBU, 21 Montevideo street, Sofia, Bulgaria</p>
                                <p><strong>Fee:</strong> Free ($0.00)</p>
                            </div>

                            <button type="submit">Finish reservation</button>
                        </form>
                        <button onClick={closeModal}>Close</button>
                    </div>
                </Modal>
        </div>
    );
};

export default EventPage;
