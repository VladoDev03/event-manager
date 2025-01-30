import React from 'react';
import "../style/Homepage.css";
import { useNavigate } from 'react-router-dom';
import { format } from 'date-fns';

const EventsContainer = ({ events, addToWishlist }) => {
    const navigate = useNavigate();

    const handleEventClick = (eventId) => {
        navigate(`/event/${eventId}`);
    };

    const formatDateTime = (dateTimeString) => {
      return format(dateTimeString, "dd MMMM yyyy HH:mm");
    };

    return (
        <div className="eventsContainer">
            {events.map((event) => (
              <section key={event.id} className="eventWrapper" onClick={() => handleEventClick(event.id)}>
                <a className="eventImg" href="/event">
                  {/* <img src={event.image} alt="event" /> */}
                </a>
                <div className="eventDescription">
                  <h3>{event.title}</h3>
                  <p>{formatDateTime(event.startTime)}</p>
                  <p>{event.locationName}</p>
                  <p>{event.price} BGN</p>
                </div>
                <button
                  className="favoriteButton"
                  onClick={() => addToWishlist(event)}
                >
                  ❤️
                </button>
              </section>
            ))}
          </div>
    );
};

export default EventsContainer;
