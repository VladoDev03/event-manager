import React, { useContext, useEffect, useState } from "react";
import "../style/Homepage.css";
import { useNavigate } from "react-router-dom";
import { format } from "date-fns";
import { AuthContext } from "../contexts/AuthContext";
import { addToWishlist, isEventInWishlist } from "../services/wishlistService";

const EventsContainer = ({ events }) => {
  const navigate = useNavigate();
  const { user } = useContext(AuthContext);
  const [wishlist, setWishlist] = useState({});

  useEffect(() => {
    if (user?.userId) {
      const fetchWishlistStatus = async () => {
        const wishlistStatus = {};
        for (const event of events) {
          wishlistStatus[event.id] = await isEventInWishlist(
            event.id,
            user.userId
          );
        }
        setWishlist(wishlistStatus);
      };
      fetchWishlistStatus();
    }
  }, [user, events]);

  const handleAddToWishlist = async (eventId) => {
    if (!user?.userId) {
      navigate("/login");
      return;
    }

    if (wishlist[eventId]) {
      alert("Event is already in your wishlist! ❤️");
      return;
    }

    await addToWishlist(eventId, user.userId);
    setWishlist((prev) => ({ ...prev, [eventId]: true }));
    alert("Added to wishlist ❤️");
  };

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
              onClick={(e) => {
                handleAddToWishlist(event.id);
                e.stopPropagation();
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
