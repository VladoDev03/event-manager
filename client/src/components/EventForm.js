import React, { useState } from "react";
import "../style/CreateEvent.css";
import { createEvent } from "../services/eventService";

const EventForm = () => {
  const [formData, setFormData] = useState({
    title: "",
    category: "",
    startDate: "",
    startTime: "",
    endTime: "",
    location: "",
    capacity: 0,
    price: "",
    description: "",
  });

  const [errors, setErrors] = useState({
    title: "",
    capacity: "",
  });

  const handleChange = (e) => {
    const { id, value } = e.target;
    setFormData((prev) => ({ ...prev, [id]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    let isValid = true;
    let errorsCopy = { ...errors };

    if (
      formData.title !==
      formData.title.charAt(0).toUpperCase() + formData.title.slice(1)
    ) {
      errorsCopy.title = "Event title must start with an uppercase letter.";
      isValid = false;
    } else {
      errorsCopy.title = "";
    }

    if (formData.capacity <= 0) {
      errorsCopy.capacity = "Capacity must be greater than zero.";
      isValid = false;
    } else {
      errorsCopy.capacity = "";
    }

    if (formData.price <= 0) {
      errorsCopy.price = "Price must be greater than zero.";
      isValid = false;
    } else {
      errorsCopy.price = "";
    }

    setErrors(errorsCopy);

    if (!isValid) return;

    try {
      await createEvent(formData);
      alert("Event created successfully!");
      setFormData({
        title: "",
        category: "",
        startDate: "",
        startTime: "",
        endTime: "",
        location: "",
        capacity: 0,
        price: "",
        description: "",
      });
    } catch (error) {
      alert("Error creating event. Please try again.");
    }
  };

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
      <div className="mainContainerCreateEvent">
        <form onSubmit={handleSubmit}>
          <h1>Create an event</h1>
          <h2 className="sectionTitle">Event Details</h2>
          <div className="eventDetails">
            <div className="formWrapper">
              <label htmlFor="title">Event Title *</label>
              <input
                type="text"
                id="title"
                value={formData.title}
                onChange={handleChange}
                placeholder="Enter the name of your event"
                required
              />
              {errors.title && <p className="error-message">{errors.title}</p>}
            </div>

            <div className="formWrapper">
              <label htmlFor="category">Event Category *</label>
              <select
                id="category"
                value={formData.category}
                onChange={handleChange}
                required
              >
                <option value="">Please select one</option>
                <option value="music">Music</option>
                <option value="conference">Conference</option>
                <option value="workshop">Workshop</option>
              </select>
            </div>
          </div>

          {/* Date & Time Section */}
          <h2 className="sectionTitle">Date & Time</h2>
          <div className="formInline">
            <div className="formWrapper">
              <label htmlFor="startDate">Start Date *</label>
              <input
                type="date"
                id="startDate"
                value={formData.startDate}
                onChange={handleChange}
                required
              />
            </div>
            <div className="formWrapper">
              <label htmlFor="startTime">Start Time *</label>
              <input
                type="time"
                id="startTime"
                value={formData.startTime}
                onChange={handleChange}
                required
              />
            </div>
            <div className="formWrapper">
              <label htmlFor="endTime">End Time</label>
              <input
                type="time"
                id="endTime"
                value={formData.endTime}
                onChange={handleChange}
              />
            </div>
          </div>

          {/* Location Section */}
          <h2 className="sectionTitle">Location</h2>
          <div className="formWrapper">
            <label htmlFor="location">
              Where will your event take place? *
            </label>
            <select
              id="location"
              value={formData.location}
              onChange={handleChange}
              required
            >
              <option value="">Please select one</option>
              <option value="online">Online</option>
              <option value="venue1">Venue 1</option>
              <option value="venue2">Venue 2</option>
            </select>
          </div>

          {/* Capacity Section */}
          <h2 className="sectionTitle">What's the capacity for your event?</h2>
          <div className="formWrapper">
            <label htmlFor="capacity">Total capacity *</label>
            <input
              type="number"
              id="capacity"
              value={formData.capacity}
              onChange={handleChange}
              placeholder="0"
              required
            />
            {errors.capacity && (
              <p className="error-message">{errors.capacity}</p>
            )}
          </div>

          {/* Price Section */}
          <h2 className="sectionTitle">What's the price of your event?</h2>
          <div className="formWrapper">
            <label htmlFor="price">Event Price *</label>
            <input
              type="number"
              id="price"
              value={formData.price}
              onChange={handleChange}
              placeholder="Enter price"
              required
            />
            {errors.price && <p className="error-message">{errors.price}</p>}
          </div>

          {/* Additional Information Section */}
          <h2 className="sectionTitle">Additional Information</h2>
          <div className="formWrapper">
            <label htmlFor="description">Event Description *</label>
            <textarea
              id="description"
              value={formData.description}
              onChange={handleChange}
              placeholder="Describe what's special about your event & other important details"
              required
            />
          </div>

          {/* Submit Button */}
          <button type="submit" className="submitBtn">
            Save & Continue
          </button>
        </form>
      </div>
    </>
  );
};

export default EventForm;
