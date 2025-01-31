import React, { useState } from "react";
import { Link } from "react-router-dom";
import "../style/Homepage.css";
import Navbar from "./NavBar";
import EventsContainer from "./EventsContainer";

const HomePage = ({ addToWishlist }) => {
  const [events] = useState([
    {
      id: 1,
      title: "Event 1",
      startTime: "2024-01-05T18:30:00",
      locationName: "NBU",
      price: "Free",
      image: "...",
    },
    {
      id: 2,
      title: "Event 2",
      startTime: "2024-01-06T18:30:00",
      locationName: "Arena Armeec",
      price: "$10",
      image: "...",
    },
  ]);

  return (
    <>
      <Navbar />
      <div className="mainContainer">
        <div className="bannerContainer">
          <img
            src="https://design-assets.adobeprojectm.com/content/download/express/public/urn:aaid:sc:VA6C2:d97d126b-e18a-5797-8802-2b457ac10518/component?assetType=TEMPLATE&etag=867813dd4f024cae8df2d0dfd2a91435&revision=e707f25d-6eef-4d42-9881-f62be17f2998&component_id=f5e79f54-25f0-40f7-9a1e-a87b7f696f81"
            alt="banner"
          />
        </div>
        <div className="iconCategoryBrowseContainer">
          <a className="iconCategory">
            <div className="iconWrapper">
              <img
                src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhcGz5Y2viMsk4NWscvM51coJgpM4X2FYDcw&s"
                alt="iconParty"
              />
              <p className="iconTitle">Party</p>
            </div>
          </a>
          <a className="iconCategory">
            <div className="iconWrapper">
              <img
                src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhcGz5Y2viMsk4NWscvM51coJgpM4X2FYDcw&s"
                alt="iconParty"
              />
              <p className="iconTitle">Party</p>
            </div>
          </a>
          <a className="iconCategory">
            <div className="iconWrapper">
              <img
                src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhcGz5Y2viMsk4NWscvM51coJgpM4X2FYDcw&s"
                alt="iconParty"
              />
              <p className="iconTitle">Party</p>
            </div>
          </a>
        </div>
        <hr />
        <div className="popularEventsContainer">
          <div className="popularEventsTitleContainer">
            <div className="popularEventsTitle">
              <h2>Popular events</h2>
            </div>
            <a className="exporeMoreEvents" href="event.html">
              <span>Explore more events</span>
            </a>
          </div>
          <EventsContainer events={events} addToWishlist={addToWishlist} />
        </div>
      </div>
    </>
  );
};

export default HomePage;
