import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import axios from 'axios';

const SearchComponent = () => {
    const [location, setLocation] = useState('');
    const [name, setName] = useState('');
    const history = useHistory();

    const handleSearch = async () => {
        try {
            const params = {location, name,};
            const response = await axios.get('http://localhost:8080/searchEvents', {params});
            history.push({
                pathname: '/filterEvents',
                state: {initialEvents: response.data}
            });
        } catch (error) {
            console.error('Error searching events', error);
        }
    };

    return (
        <div className="search-container">
            <h1>Find Events by Location or Name</h1>
            <input
                type="text"
                value={location}
                onChange={(e) => setLocation(e.target.value)}
                placeholder="Enter location"
            />
            <input
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)} placeholder="Enter name"
            />
            <button
                onClick={handleSearch}>Search
            </button>
        </div>
    );
};

export default SearchComponent;
