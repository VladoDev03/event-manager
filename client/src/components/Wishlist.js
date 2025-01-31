import React from "react";
import "../style/Wishlist.css";
import Navbar from "./NavBar";
import { format } from "date-fns";

const Wishlist = ({ wishlist, removeFromWishlist }) => {
  const formatDateTime = (dateTimeString) => {
    return format(dateTimeString, "dd MMMM yyyy HH:mm");
  };

  return (
    <>
      <Navbar />
      <div className="likesSection">
        <h1>Wish List</h1>
        <div className="eventsContainer">
          {wishlist.length > 0 ? (
            wishlist.map((event) => (
              <section key={event.id} className="eventWrapper">
                <div className="eventDescription">
                  <h3>{event.title}</h3>
                  <p>{formatDateTime(event.startTime)}</p>
                  <p>{event.locationName}</p>
                  <p>{event.price} BGN</p>
                </div>
                <button
                  className="removeButton"
                  onClick={(e) => {
                    e.stopPropagation();
                    removeFromWishlist(event.id);
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
