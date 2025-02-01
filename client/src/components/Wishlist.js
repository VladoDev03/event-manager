import React, { useContext, useEffect, useState } from "react";
import "../style/Wishlist.css";
import Navbar from "./NavBar";
import { format } from "date-fns";
import EventsContainer from "./EventsContainer";
import { getWishlist, removeFromWishlist } from '../services/wishlistService';
import { AuthContext } from "../contexts/AuthContext";

const Wishlist = () => {
  const [events, setEvents] = useState([]);
  const { user } = useContext(AuthContext);

  useEffect(() => {
    const fetchWishlist = async () => {
      try {
        const data = await getWishlist(user.userId);
        console.log(data);
        setEvents(data);
      } catch (err) {
        console.log(err)
      }
    }

    fetchWishlist();
  }, []);

  const formatDateTime = (dateTimeString) => {
    return format(dateTimeString, "dd MMMM yyyy HH:mm");
  };

  const handleDelete = eventId => {
    setEvents(oldEvents => {
      const newList = [];

      oldEvents.forEach(e => {
        if (e.id != eventId) {
          newList.push(e);
        }
      });

      return newList;
    });
  };

  return (
    <>
      <Navbar />
      <div className="likesSection">
        <h1>Wish List</h1>
        <div className="eventsContainer">
          {events.length > 0 ? (
            events.map((event) => (
              <section key={event.id} className="eventWrapper">
                <div className="eventDescription">
                  <h3>{event.title}</h3>
                  <p>{formatDateTime(event.startTime)}</p>
                  <p>{event.locationName}</p>
                  <p>{event.price} BGN</p>
                </div>
                <button
                  className="removeButton"
                  onClick={async e => {
                    e.stopPropagation();
                    const data = await removeFromWishlist(event.id, user.userId);
                    console.log(data);
                    handleDelete(event.id);
                  }}
                >
                  ❤️
                </button>
              </section>
            ))
          ) : (
            <p>No events in your wishlist yet.</p>
          )}
        </div>
      </div>
    </>
  );
};

export default Wishlist;