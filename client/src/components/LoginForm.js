import React from "react";
import { Link } from "react-router-dom";
import "../style/Login.css";

const LoginForm = () => {
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
          <header className="login">Log in</header>
          <form className="inputContainer">
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
              <button type="submit">Log in</button>
            </div>
          </form>
          <div className="signupPrompt">
            Don't have an account? <a href="signup.html">Sign up</a>
          </div>
        </div>
        <div className="secondHalf"></div>
      </div>
    </body>
  );
};

export default LoginForm;
