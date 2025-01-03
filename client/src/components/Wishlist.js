import React from "react";
import "../style/Wishlist.css";
import Navbar from "./NavBar";

const Wishlist = ({ wishlist, removeFromWishlist }) => {
  return (
    <>
      <Navbar />
      <div className="likesSection">
        <h1>Wish List</h1>
        <div>
          {wishlist.length > 0 ? (
            wishlist.map((event) => (
              <div key={event.id} className="eventWrapperWishList">
                <div className="eventDetails">
                  <a className="eventTitle" href="event.html">
                    <h3>{event.title}</h3>
                  </a>
                  <p class="eventDate">{event.date}</p>
                  <p>{event.location}</p>
                  <p>{event.price}</p>
                </div>
                <div class="eventImageContainer">
                  <img
                    src={event.image}
                    alt="JSTalks Event"
                    class="eventImage"
                  />
                  <div class="eventActions">
                    <button
                      className="removeButton"
                      onClick={() => removeFromWishlist(event.id)}
                    >
                      ❤️
                    </button>
                  </div>
                </div>
              </div>
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
