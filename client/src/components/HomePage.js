import React, { useState, useEffect } from "react";
import { Link, Navigate, useNavigate } from "react-router-dom";
import "../style/Homepage.css";
import Navbar from "./NavBar";
import EventsContainer from "./EventsContainer";
import { fetchAllEvents } from "../services/eventService";

const HomePage = ({ addToWishlist }) => {
  const navigate = useNavigate();
  const [initialEvents, setInitialEvents] = useState([]);
  const [events, setEvents] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const eventsData = await fetchAllEvents();
        setInitialEvents(eventsData || []);
        setEvents(eventsData || []);
      } catch (error) {
        console.error("Error fetching events", error);
      }
    };
    fetchData();
  }, []);

  return (
    <>
      <Navbar />
      <div className="mainContainer">
        <div className="popularEventsContainer">
          <div className="popularEventsTitleContainer">
            <div className="popularEventsTitle">
              <h2>Popular events</h2>
            </div>
            <Link to="/searchEvents">Explore more events</Link> {}
          </div>
        </div>
        <EventsContainer events={events} addToWishlist={addToWishlist} />
      </div>
    </>
  );
};

export default HomePage;
