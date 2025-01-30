import React, { useState } from "react";
import { Link } from "react-router-dom";
import * as authService from '../services/authService'
import "../style/Login.css";

export const SignupForm = () => {
  const [formData, setFormData] = useState({
    fullName: "",
    username: "",
    password: ""
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Form submitted:", formData);

    const newUser = {
        firstName: formData.fullName.split(" ")[0],
        lastName: formData.fullName.split(" ")[1],
        username: formData.username,
        password: formData.password,
    };
    
    console.log("New User:", newUser);

    authService.register(newUser)
                .then(result => {
                    console.log(result);
                });
  };

  return (
    <div className="signupBody">
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
          <form className="inputContainer" onSubmit={handleSubmit}>
            <div className="inputBox">
              <input
                type="text"
                name="fullName"
                className="searchBoxLog"
                placeholder="Full name"
                value={formData.fullName}
                onChange={handleChange}
                required
              />
            </div>
            <div className="inputBox">
              <input
                type="username"
                name="username"
                className="searchBoxLog"
                placeholder="Username"
                value={formData.username}
                onChange={handleChange}
                required
              />
            </div>
            <div className="inputBox">
              <input
                type="password"
                name="password"
                className="searchBoxLog"
                placeholder="Password"
                value={formData.password}
                onChange={handleChange}
                required
              />
            </div>
            <div className="submitBtnLog">
              <button type="submit">Sign up</button>
            </div>
          </form>
          <div className="signupPrompt">
            Already have an account? <Link to="/Login">Log in</Link>
          </div>
        </div>
        <div className="secondHalf"></div>
      </div>
    </div>
  );
};