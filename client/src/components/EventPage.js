import React from 'react';
import Navbar from './NavBar';
import EventInfo from './EventInfo';
import ReservationComponent from './ReservationComponent';
import '../style/eventStyle.css';

const EventPage = () => {
    return (
        <div id="eventPage">
            <Navbar />
            <div className="mainContainer">
                <div className="bannerContainer">
                    <img src="https://design-assets.adobeprojectm.com/content/download/express/public/urn:aaid:sc:VA6C2:d97d126b-e18a-5797-8802-2b457ac10518/component?assetType=TEMPLATE&etag=867813dd4f024cae8df2d0dfd2a91435&revision=e707f25d-6eef-4d42-9881-f62be17f2998&component_id=f5e79f54-25f0-40f7-9a1e-a87b7f696f81" alt="banner" />
                </div>
                <div className="eventAndReservationContainer">
                    <EventInfo />
                    <ReservationComponent />
                </div>
            </div>
        </div>
    );
};

export default EventPage;
