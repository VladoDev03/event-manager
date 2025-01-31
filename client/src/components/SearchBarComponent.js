import React, { useState } from 'react';
import { useHistory, useNavigate } from 'react-router-dom';
import { fetchSearchedEvents } from '../services/eventService';
import "../style/NavBar.css";

const SearchBarComponent = () => {
    const navigate = useNavigate();

    const [title, setTitle] = useState('');
    const [location, setLocation] = useState('');

    const handleSearch = async () => { 
        const params = new URLSearchParams(); 
        
        if (title) params.append('title', title);
        if (location) params.append('location', location);

        navigate(`/searchEvents/${params}`);
        await fetchSearchedEvents(params); 
    };
    
    return (
        <div className="searchbarContainer">
        <div className="searchEvents">
          <input
            type="text"
            className="searchBoxNav"
            placeholder="Search events"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </div>
        <div className="searchCity">
          <hr />
          <input
            type="text"
            className="searchBoxNav"
            placeholder="Choose a location"
            value={location}
            onChange={(e) => setLocation(e.target.value)}
          />
        </div>
        <div className="searchButtonContainer">
          <button className="searchButton" onClick={handleSearch}>
            <svg
              aria-label="search button"
              xmlns="http://www.w3.org/2000/svg"
              width="36"
              height="36"
              fill="none"
            >
              <circle cx="18" cy="18" r="18"></circle>
              <path
                fill="#fff"
                fillRule="evenodd"
                d="M20.926 19.426a6 6 0 1 0-1.454 1.468L24.5 26l1.5-1.5-5.074-5.074ZM16 20a4 4 0 1 0 0-8 4 4 0 0 0 0 8Z"
                clipRule="evenodd"
              ></path>
            </svg>

            
          </button>
        </div>
      </div>
    );
};

export default SearchBarComponent;
