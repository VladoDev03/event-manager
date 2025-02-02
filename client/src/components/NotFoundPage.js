import React, { useState, useEffect, useContext } from "react";
import "../style/NotFound.css";

export const NotFound = () => {
  return (
    <>
      <div className="logo">
        <a href="/">
          <img
            src="https://gdm-catalog-fmapi-prod.imgix.net/ProductLogo/537ec30a-379d-42ed-9912-75af8cb47205.png?auto=format%2Ccompress&fit=max&w=256&q=75&ch=Width%2CDPR"
            alt="logo"
          />
        </a>
      </div>
      <div className="mainContainerNotFound">
        <h1>Error 404</h1>
        <h2>Not Found</h2>
        <p>The resource requested could not be found on this sever!</p>
        <a href="/">
          <h5>Go back to event finder.</h5>
        </a>
      </div>
    </>
  );
};