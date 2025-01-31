import React, { useEffect, useState } from 'react';
import Navbar from "./NavBar";
import FilterComponent from "./FilterComponent";
import "../style/Homepage.css";
import { useLocation, useParams } from 'react-router-dom';
import { fetchSearchedEvents, fetchFilteredSearchedEvents } from '../services/eventService';
import EventsContainer from './EventsContainer';

const SearchResultPage = ({addToWishlist}) => {

    const [events, setEvents] = useState([]);
    const { params } = useParams();

    useEffect(() => {
        const fetchData = async () => {
                try {
                    const eventsData = await fetchSearchedEvents(params);
                    console.log(eventsData);
                    setEvents(eventsData || []);
                } catch (error) {
                    console.error('Error fetching events', error);
                }
            };
    
            fetchData();
        }, [params]
    );

    console.log(events);

    const handleFilter = async (parameters) => {
        try {
            const eventsData = await fetchFilteredSearchedEvents(params + "&" + parameters);
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
            <EventsContainer events={events}  addToWishlist={addToWishlist} />
        </div>
    </>
);
};

export default SearchResultPage;