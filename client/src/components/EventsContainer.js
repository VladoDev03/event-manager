import React, { useContext } from "react";
import "../style/Homepage.css";
import { useNavigate } from "react-router-dom";
import { format } from "date-fns";
import { AuthContext } from "../contexts/AuthContext";
import { addToWishlist } from "../services/wishlistService";

const EventsContainer = ({ events }) => {
  const navigate = useNavigate();
  const {user} = useContext(AuthContext);

  const handleEventClick = (eventId) => {
    navigate(`/event/${eventId}`);
  };

  const formatDateTime = (dateTimeString) => {
    return format(dateTimeString, "dd MMMM yyyy HH:mm");
  };

  return (
    <div className="popularEventsContainer">
      <div className="eventsContainer">
        {events.map((event) => (
          <section
            key={event.id}
            className="eventWrapper"
            onClick={() => handleEventClick(event.id)}
          >
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
              onClick={e => {
                e.stopPropagation();
                addToWishlist(event.id, user.userId)
              }}
            >
              ❤️
            </button>
          </section>
        ))}
      </div>
    </div>
  );
};

export default EventsContainer;
