import React, { useState } from 'react';
import Navbar from "./NavBar";
import FilterComponent from "./FilterComponent";
import "../style/Homepage.css";
import { useLocation } from 'react-router-dom';
import { fetchFilteredEvents } from '../services/eventService';
import EventsContainer from './EventsContainer';

const SearchResultPage = () => {
    const location = useLocation();
    const initialEvents = location.state?.initialEvents || [];

    const [events, setEvents] = useState([initialEvents]);

    const handleFilter = async (params) => {
        params.initialEvents = initialEvents;
        try {
            const eventsData = await fetchFilteredEvents(params);
            setEvents(eventsData);
        } catch (error) {
            console.error('Error fetching events', error);
        }
    };

    return (
    <>
        <Navbar/>
        <div className="mainContainer">
            <FilterComponent onFilter={handleFilter}/>
            <EventsContainer events={events} />
        </div>
    </>
);
};

export default SearchResultPage;