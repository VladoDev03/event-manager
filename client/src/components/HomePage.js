import React, { useState, useEffect } from "react";
import "../style/Homepage.css";
import Navbar from "./NavBar";

const HomePage = ({ addToWishlist }) => {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    const fetchEvents = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/events");
        if (!response.ok) {
          throw new Error("Failed to fetch events");
        }
        const data = await response.json();
        setEvents(data);
      } catch (error) {
        console.error("Error fetching events:", error);
      }
    };

    fetchEvents();
  }, []);

  return (
    <>
      <Navbar />
      <div className="mainContainer">
        <div className="bannerContainer">
          <img
            src="https://design-assets.adobeprojectm.com/content/download/express/public/urn:aaid:sc:VA6C2:d97d126b-e18a-5797-8802-2b457ac10518/component?assetType=TEMPLATE&etag=867813dd4f024cae8df2d0dfd2a91435&revision=e707f25d-6eef-4d42-9881-f62be17f2998&component_id=f5e79f54-25f0-40f7-9a1e-a87b7f696f81"
            alt="banner"
          />
        </div>

        <div className="popularEventsContainer">
          <div className="popularEventsTitleContainer">
            <div className="popularEventsTitle">
              <h2>Popular events</h2>
            </div>
            <a className="exporeMoreEvents" href="event.html">
              <span>Explore more events</span>
            </a>
          </div>
          <div className="eventsContainer">
            {events.map((event) => (
              <section key={event.id} className="eventWrapper">
                <div className="eventDescription">
                  <h3>{event.title}</h3>
                  <p>{event.date}</p>
                  <p>{event.location}</p>
                  <p>${event.price}</p>
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
        </div>
      </div>
    </>
  );
};

export default HomePage;
