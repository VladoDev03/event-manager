import React, { useState, useEffect, useContext } from "react";
import Navbar from "./NavBar";
import EventsContainer from "./EventsContainer";
import { AuthContext } from "../contexts/AuthContext";
import { getEventsByUser } from "../services/eventService";

const MyEvents = ({}) => {
  const [events, setEvents] = useState([]);
  const { user } = useContext(AuthContext);

  useEffect(() => {
    const fetchUserEvents = async () => {
      try {
        const data = await getEventsByUser(user.userId);
        console.log(data);
        setEvents(data);
      } catch (error) {
        console.error("Error fetching user events:", error);
      }
    };

    if (user?.userId) {
      fetchUserEvents();
    }
  }, [user?.userId]);

  return (
    <>
      <Navbar />
      <div className="mainContainer">
        <div className="popularEventsContainer">
          <div className="popularEventsTitleContainer">
            <h1>My Events</h1>
          </div>
        </div>
        <EventsContainer events={events} />
      </div>
    </>
  );
};

export default MyEvents;
