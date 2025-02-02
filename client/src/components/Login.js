import React, {useState, useContext} from "react";
import { Link, useNavigate } from "react-router-dom";
import "../style/Login.css";
import { login } from "../services/authService.js";
import { AuthContext } from "../contexts/AuthContext";

const LoginForm = () => {
    const navigate = useNavigate();
    const { userLogin } = useContext(AuthContext)
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = async (e) => {
      e.preventDefault();

      console.log(username, password);
      
      try {        
        const result = await login(username, password);
        userLogin(result);
        navigate('/');
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
              type="username" 
              className="searchBoxLog" 
              placeholder="username" 
              value={username}
              onChange={(e) => setUsername(e.target.value)}
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
            Don't have an account? <Link to="/signup">Sign up</Link> {}
          </div>
        </div>
        <div className="secondHalf"></div>
      </div>
    </div>
  );
};

export default LoginForm;