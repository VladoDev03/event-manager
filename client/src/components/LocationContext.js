import React, { createContext, useState, useContext } from 'react';

const LocationContext = createContext();

export const useLocationContext = () => useContext(LocationContext);

export function LocationProvider({ children }) {
    const [location, setLocation] = useState('');
    return (
        <LocationContext.Provider value={{ location, setLocation }}>
            {children}
        </LocationContext.Provider>
    );
};
