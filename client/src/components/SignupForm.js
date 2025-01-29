import React from "react";
import { Link } from "react-router-dom";
import "../style/Login.css";

const SignupForm = () => {
  return (
    <body className="signupBody">
      <div className="loginContainer">
        <div className="logoLog">
          <Link to="/">
            <img
              src="https://gdm-catalog-fmapi-prod.imgix.net/ProductLogo/537ec30a-379d-42ed-9912-75af8cb47205.png?auto=format%2Ccompress&fit=max&w=256&q=75&ch=Width%2CDPR"
              alt="logo"
            />
          </Link>
        </div>
        <div className="firstHalf">
          <header className="login">Sign up</header>
          <form className="inputContainer">
            <div className="inputBox">
              <input
                type="text"
                className="searchBoxLog"
                placeholder="Full name"
              />
            </div>
            <div className="inputBox">
              <input type="text" className="searchBoxLog" placeholder="Email" />
            </div>
            <div className="inputBox">
              <input
                type="password"
                className="searchBoxLog"
                placeholder="Password"
              />
            </div>
            <div className="submitBtnLog">
              <button type="submit">Sign up</button>
            </div>
          </form>
          <div className="signupPrompt">
            Already have an account? <a href="login.html">Log in</a>
          </div>
        </div>
        <div className="secondHalf"></div>
      </div>
    </body>
  );
};

export default SignupForm;
