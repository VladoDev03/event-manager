import React from "react";
import { Link } from "react-router-dom";
import "../style/NavBar.css";

const Navbar = () => {
  return (
    <header className="headerContainer">
      <div className="logo">
        <Link to="/">
          <img
            src="https://gdm-catalog-fmapi-prod.imgix.net/ProductLogo/537ec30a-379d-42ed-9912-75af8cb47205.png?auto=format%2Ccompress&fit=max&w=256&q=75&ch=Width%2CDPR"
            alt="logo"
          />
        </Link>
      </div>

      <div className="searchbarContainer">
        <div className="searchEvents">
          <input
            type="text"
            className="searchBox"
            placeholder="Search events"
          />
        </div>
        <div className="searchCity">
          <hr />
          <input
            type="text"
            className="searchBox"
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

      <div className="navOptionsContainer">
        <ul className="optionsList">
          <li className="login1">
            <a href="findEventsPage.html">Find events</a>
          </li>
          <li className="login1">
            <a href="create-event" target="_blank">
              Create events
            </a>
          </li>
          <li className="login1">
            <a href="myTicketsPage.html">Find my tickets</a>
          </li>
          <li className="login1">
            <Link to="/wishlist">Wish list</Link> {}
          </li>
          <li className="login1">
            {/* <a href="login.html">Log in</a> */}
            <Link to="/login">Log in</Link> {}
          </li>
          <li className="signup">
            <Link to="/signup">Sign up</Link> {}
          </li>
        </ul>
      </div>
    </header>
  );
};

export default Navbar;
