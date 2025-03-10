import React, { useState, useContext } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Modal from "react-modal";

import Navbar from "./NavBar";
import EventInfo from "./EventInfo";
import ReservationComponent from "./ReservationComponent";
import { AuthContext } from "../contexts/AuthContext";

import "../style/eventStyle.css";
import "../style/ReservationForm.css";

import { createReservation } from "../services/ReservationService";
import { ReviewForm } from "./ReviewForm";

const EventPage = () => {
  const navigate = useNavigate();
  const { user } = useContext(AuthContext);
  const { eventId } = useParams();
  const [reviews, setReviews] = useState([]);

  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [successModalIsOpen, setSuccessModalIsOpen] = useState(false);
  const [fullCapacityIsOpen, setFullCapacityIsOpen] = useState(false);

  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [eventUserId, setEventUserId] = useState(0);
  const [eventEndDate, setEventEndDate] = useState();
  const [reviewExists, setReviewExists] = useState(false);

  const openModal = () => {
    setModalIsOpen(true);
  };

  const closeModal = () => {
    setModalIsOpen(false);
  };

  const openSuccessModal = () => {
    setModalIsOpen(false);
    setSuccessModalIsOpen(true);
  };

  const closeSuccessModal = () => {
    setSuccessModalIsOpen(false);
  };

  const goToMyTickets = () => {
    setSuccessModalIsOpen(false);
    navigate("/myTickets");
  };

  const openFullCapacityModal = () => {
    setModalIsOpen(false);
    setFullCapacityIsOpen(true);
  };

  const closeFullCapacityModal = () => {
    setFullCapacityIsOpen(false);
  };

  const [errors, setErrors] = useState({ firstName: "", lastName: "" });
  const validateForm = () => {
    let errorsCopy = { ...errors };
    let isValid = true;

    if (firstName.length < 2 || firstName.length > 50) {
      errorsCopy.firstName = "First name must be between 2 and 50 characters";
      isValid = false;
    } else {
      errorsCopy.firstName = "";
    }
    if (lastName.length < 2 || lastName.length > 50) {
      errorsCopy.lastName = "First name must be between 2 and 50 characters";
      isValid = false;
    } else {
      errorsCopy.lastName = "";
    }

    setErrors(errorsCopy);
    return isValid;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm()) {
      return;
    }

    try {
      await createReservation(eventId, user.userId, firstName, lastName, email);
      openSuccessModal();
    } catch (error) {
      openFullCapacityModal();
      // alert('Error creating reservation. Please try again.');
    }
  };

  const compareDates = (eventEndDate) => {
    let now = new Date();
    let eventDate = new Date(eventEndDate);

    if (now.getTime() < eventDate.getTime()) {
      return true;
    } else if (now.getTime() >= eventDate.getTime()) {
      return false;
    }
  };

  const redirectToAddMedia = () => {
    navigate(`../add-media/${eventId}`);
  };

  return (
    <div id="eventPage">
      <Navbar />
      <div className="mainContainer">
        <div className="eventAndReservationContainer">
          <EventInfo
            eventId={eventId}
            setEventUserId={setEventUserId}
            setEventEndDate={setEventEndDate}
            setReviewExists={setReviewExists}
            reviews={reviews}
            setReviews={setReviews}
          />
          {compareDates(eventEndDate) ? (
            <ReservationComponent openModal={openModal} />
          ) : (
            ""
          )}
        </div>
      </div>
      <Modal
        isOpen={modalIsOpen}
        onRequestClose={closeModal}
        contentLabel="Reservation Form"
        className="modal"
        overlayClassName="modalOverlay"
      >
        <div className="container">
          <h1>Event Reservation Form</h1>
          <button className="close-modal" onClick={closeModal}>
            &#10005;
          </button>
          <form>
            <h2>Contact Information</h2>
            <div className="names">
              <div className="first-name">
                <label htmlFor="first-name">First Name:</label>
                <input
                  type="text"
                  id="first-name"
                  value={firstName}
                  onChange={(e) => setFirstName(e.target.value)}
                  required
                />
                {errors.firstName && (
                  <p className="error-message">{errors.firstName}</p>
                )}
              </div>
              <div className="last-name">
                <label htmlFor="last-name">Last Name:</label>
                <input
                  type="text"
                  id="last-name"
                  value={lastName}
                  onChange={(e) => setLastName(e.target.value)}
                  required
                />
                {errors.lastName && (
                  <p className="error-message">{errors.lastName}</p>
                )}
              </div>
            </div>

            <label htmlFor="email">Email Address:</label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />

            <button
              className="finish-reservation"
              type="submit"
              onClick={handleSubmit}
            >
              Finish reservation
            </button>
          </form>
        </div>
      </Modal>
      <Modal
        isOpen={successModalIsOpen}
        onRequestClose={closeSuccessModal}
        contentLabel="Reservation Success"
        className="modal"
        overlayClassName="modalOverlay"
      >
        <div className="container" id="successful-reservation">
          <button className="close-modal" onClick={closeSuccessModal}>
            &#10005;
          </button>
          <h1>Reservation successful!</h1>
          <p>Your reservation has been successfully made.</p>
          <button className="go-to-tickets" onClick={goToMyTickets}>
            Go to my tickets
          </button>
        </div>
      </Modal>
      <Modal
        isOpen={fullCapacityIsOpen}
        onRequestClose={closeFullCapacityModal}
        contentLabel="Reservation fail"
        className="modal"
        overlayClassName="modalOverlay"
      >
        <div className="container" id="unsuccessful-reservation">
          <button className="close-modal" onClick={closeFullCapacityModal}>
            &#10005;
          </button>
          <h1>No more free spots for this event.</h1>
          <p>
            We're sorry to inform you that this event is at its full capacity.
          </p>
          <h3>Best of luck next time!</h3>
        </div>
      </Modal>
      <div className="eventAndReservationContainer">
        {user.userId != eventUserId &&
        !compareDates(eventEndDate) &&
        !reviewExists ? (
          <ReviewForm
            eventId={eventId}
            setReviewExists={setReviewExists}
            reviews={reviews}
            setReviews={setReviews}
          />
        ) : (
          ""
        )}
        {user.userId == eventUserId ? (
          <button
            className="submitBtn"
            type="button"
            onClick={redirectToAddMedia}
          >
            Add Media
          </button>
        ) : (
          ""
        )}
      </div>
    </div>
  );
};

Modal.setAppElement("#root");
export default EventPage;
