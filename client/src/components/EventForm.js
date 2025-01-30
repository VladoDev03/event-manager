import React, { useState, useEffect, useContext } from "react";
import "../style/CreateEvent.css";
import { createEvent } from "../services/eventService";
import { fetchCategories } from "../services/categoriesService";
import { AuthContext } from "../contexts/AuthContext";
import { ImageForm } from "./ImageForm";

const EventForm = () => {
  const [categories, setCategories] = useState([]);
  const { user } = useContext(AuthContext);

  const [formData, setFormData] = useState({
    title: "",
    category: "",
    startTime: "",
    endTime: "",
    location: "",
    capacity: 0,
    description: "",
    price: 0,
  });

  useEffect(() => {
    const loadCategories = async () => {
      try {
        const data = await fetchCategories();
        setCategories(data);
      } catch (error) {
        console.error("Failed to fetch categories", error);
      }
    };
    loadCategories();
  }, []);

  const handleChange = (e) => {
    const { id, value } = e.target;
    setFormData((prev) => ({ ...prev, [id]: value }));
  };

  const [errors, setErrors] = useState({
    title: "",
    capacity: "",
  });

  const pictureHandler = (e) => {
    const selectedFiles = e.target.files;
    if (selectedFiles && selectedFiles.length > 0) {
      setFiles(selectedFiles);
    } else {
      setFiles(null);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    formData.category = formData.category.toUpperCase().replaceAll(" ", "_");
    console.log(formData);

    let isValid = true;
    let errorsCopy = { ...errors };

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
    let resEvent;

    try {
      resEvent = await createEvent(formData);
      alert("Event created successfully!");
      setFormData({
        title: "",
        category: "",
        startTime: "",
        endTime: "",
        location: "",
        capacity: 0,
        description: "",
        price: 0,
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
            src="https://1000logos.net/wp-content/uploads/2017/05/Pepsi-logo.png"
            alt="logo"
          />
        </a>
      </div>
      <div className="mainContainerCreateEvent">
        <form onSubmit={handleSubmit} method="post" encType="multipart/form-data">
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
                {categories.map((cat) => (
                  <option key={cat} value={cat}>
                    {cat}
                  </option>
                ))}
              </select>
            </div>
          </div>

          {/* Date & Time Section */}
          <h2 className="sectionTitle">Date & Time</h2>
          <div className="formInline">
            <div className="formWrapper">
              <label htmlFor="startTime">Start Time *</label>
              <input
                type="datetime-local"
                id="startTime"
                value={formData.startTime}
                max={formData.endTime}
                onChange={handleChange}
                required
              />
            </div>
            <div className="formWrapper">
              <label htmlFor="endTime">End Time *</label>
              <input
                type="datetime-local"
                id="endTime"
                value={formData.endTime}
                min={formData.startTime}
                onChange={handleChange}
                required
              />
            </div>
          </div>

          {/* Location Section */}
          <h2 className="sectionTitle">Location</h2>
          <div className="formWrapper">
            <label htmlFor="location">
              Where will your event take place? *
            </label>
            <textarea
              id="location"
              value={formData.location}
              onChange={handleChange}
              placeholder="Write an address"
              required
            />
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
          <h2 className="sectionTitle">What's the price for your event?</h2>
          <div className="formWrapper">
            <label htmlFor="price">Price *</label>
            <input
              type="number"
              id="price"
              value={formData.price}
              onChange={handleChange}
              placeholder="0"
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