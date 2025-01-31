import { React, useContext, useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { AuthContext } from "../contexts/AuthContext";
import { getEventById } from "../services/eventService";

const ReservationComponent = ({
  openModal,
  guestId,
  firstName,
  lastName,
  emial,
}) => {
  const { user } = useContext(AuthContext);
  const [error, setError] = useState("");
  const [eventPrice, setEventPrice] = useState(null);
  const { eventId } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    const fetchEventPrice = async () => {
      try {
        const data = await getEventById(eventId);
        if (data && data.price) {
          setEventPrice(data.price);
        }
        console.log(eventPrice);
      } catch (error) {
        console.error("Error fetching event price:", error);
      }
    };

    fetchEventPrice();
  }, [eventId]);

  const handleReservation = async () => {
    try {
      if (user.userId) {
        setError("");
        openModal();
      } else {
        navigate("/login");
      }
    } catch (error) {
      setError(error.message);
      console.error("Error making reservation:", error);
    }
  };

  return (
    <div className="eventReservationWrapper">
      <p>{eventPrice} BGN</p>
      <div className="reservationBtn">
        <button onClick={handleReservation}>Reserve a spot</button>
      </div>
    </div>
  );
};

export default ReservationComponent;
