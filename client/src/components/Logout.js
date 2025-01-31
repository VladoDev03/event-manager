import { useContext, useEffect } from "react";
import { AuthContext } from "../contexts/AuthContext";
import { useNavigate } from "react-router-dom";

export const Logout = () => {
  const { userLogout } = useContext(AuthContext);
  const navigate = useNavigate();

  useEffect(() => {
    userLogout();
    navigate("/");
  }, []);
};
