import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import "../style/NavBar.css";

const SearchBarComponent = () => {
  const [title, setTitle] = useState("");
  const [location, setLocation] = useState("");
  // const [location, setLocation] = useState('');
  // const [name, setName] = useState('');
  // const history = useHistory();

  // const handleSearch = async () => {
  //     try {
  //         const params = {location, name,};
  //         const response = await fetch('http://localhost:8080/searchEvents', {params});
  //         history.push({
  //             pathname: '/filterEvents',
  //             state: {initialEvents: response.data}
  //         });
  //     } catch (error) {
  //         console.error('Error searching events', error);
  //     }
  // };

  return (
    <div className="searchbarContainer">
      <div className="searchEvents">
        <input
          type="text"
          className="searchBoxNav"
          placeholder="Search events"
        />
      </div>
      <div className="searchCity">
        <hr />
        <input
          type="text"
          className="searchBoxNav"
          placeholder="Choose a location"
        />
      </div>
      <div className="searchButtonContainer">
        <button className="searchButton">
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
