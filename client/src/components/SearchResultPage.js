import React, { useEffect, useState } from "react";
import Navbar from "./NavBar";
import FilterComponent from "./FilterComponent";
import "../style/Homepage.css";
import { useLocation } from "react-router-dom";
import { fetchAllEvents, fetchFilteredEvents } from "../services/eventService";
import EventsContainer from "./EventsContainer";

const SearchResultPage = ({ addToWishlist }) => {
  const location = useLocation();
  // const initialEvents = location.state?.initialEvents || [];

  const [events, setEvents] = useState([]);
  const [initialEvents, setInitialEvents] = useState([]);

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

  console.log(events);

  const handleFilter = async (params) => {
    params.initialEvents = initialEvents;
    try {
      const eventsData = await fetchFilteredEvents(params);
      setEvents(eventsData);
    } catch (error) {
      console.error("Error fetching events", error);
    }
  };

  return (
    <>
      <Navbar />
      <div className="mainContainer">
        <FilterComponent onFilter={handleFilter} />
        <EventsContainer events={events} addToWishlist={addToWishlist} />
      </div>
    </>
  );
};

export default SearchResultPage;
