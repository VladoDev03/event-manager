import React, {useState} from "react";
import { Link } from "react-router-dom";
import "../style/Login.css";
import {login} from "../services/authService.js";

const LoginForm = () => {
    
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = async (e) => {
      e.preventDefault();
      try {
        await login(email, password);
      } catch (error) {
        let errorMessage = document.getElementById('errorMessage');
        errorMessage.innerText = 'Invalid username or password.';
        errorMessage.style.color = 'red';
      }
    }
    


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
          <header className="login">Log in</header>
          <h6 id="errorMessage"></h6>
          <form className="inputContainer">
            <div className="inputBox">
              <input 
              type="email" 
              className="searchBoxLog" 
              placeholder="Email" 
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              />
            </div>
            <div className="inputBox">
              <input
                type="password"
                className="searchBoxLog"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
            </div>
            <div className="submitBtnLog">
              <button 
              type="submit"
              onClick={handleSubmit}
              >Log in</button>
            </div>
          </form>
          <div className="signupPrompt">
            Don't have an account? <a href="signup.html">Sign up</a>
          </div>
        </div>
        <div className="secondHalf"></div>
      </div>
    </div>
  );
};

export default LoginForm;