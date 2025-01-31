import { React, useContext } from "react";
import { Link } from "react-router-dom";
import "../style/NavBar.css";
import { AuthContext } from '../contexts/AuthContext';
import SearchBarComponent from './SearchBarComponent';


const Navbar = () => {
    const { user } = useContext(AuthContext);


  return (
    <header className="headerContainer">
      <div className="logo">
        <Link to="/">
          <img
            src="https://1000logos.net/wp-content/uploads/2017/05/Pepsi-logo.png"
            alt="logo"
          />
        </Link>
      </div>

      <SearchBarComponent/>


      { user.userId ? (<div className="navOptionsContainer">
        <ul className="optionsList">
          <li className="login">
            <Link to="/findEvents">Find events</Link> {}
          </li>
          <li className="login">
            <a href="create-event" target="_blank">
              Create events
            </a>
          </li>
          <li className="login">
            <Link to="/myTickets">Find my tickets</Link> {}
          </li>
          <li className="login">
            <Link to="/wishlist">Wish list</Link> {}
          </li>
          <li className="logout">
            <Link to="/logout">Logout</Link> {}
          </li>
        </ul>
      </div>) 
      :
      (<div className="navOptionsContainer">
        <ul className="optionsList">
          <li className="login">
            <Link to="/findEvents">Find events</Link> {}
          </li>
          <li className="login">
          <Link to="/signup">
              Create events
            </Link> {}
          </li>
          <li className="login">
            <Link to="/signup">Find my tickets</Link> {}
          </li>
          <li className="login">
            <Link to="/signup">Wish list</Link> {}
          </li>
          <li className="login">
            <Link to="/login">Log in</Link> {}
          </li>
          <li className="signup">
            <Link to="/signup">Sign up</Link> {}
          </li>
        </ul>
      </div>) 
      }
    </header>
  );
};

export default Navbar;
