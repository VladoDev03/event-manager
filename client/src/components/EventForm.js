import React, { useState, useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";
import "../style/CreateEvent.css";
import { createEvent } from "../services/eventService";
import { fetchCategories } from "../services/categoriesService";
import { AuthContext } from "../contexts/AuthContext";

const EventForm = () => {
  const [categories, setCategories] = useState([]);
  const { user } = useContext(AuthContext);
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    userId: user.userId,
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
    location: "",
    capacity: "",
    price: "",
    description: "",
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    formData.category = formData.category.toUpperCase().replaceAll(" ", "_");
    console.log(formData);

    let isValid = true;
    let errorsCopy = { ...errors };

    if (formData.title.length < 2 || formData.title.length > 100) {
      errorsCopy.title = "Title must be between 2 and 100 characters";
      isValid = false;
    } else {
      errorsCopy.title = "";
    }
    if (formData.location.length < 2 || formData.location.length > 100) {
      errorsCopy.location = "Title must be between 2 and 100 characters";
      isValid = false;
    } else {
      errorsCopy.location = "";
    }
    if (formData.capacity <= 0) {
      errorsCopy.capacity = "Capacity must be greater than zero";
      isValid = false;
    } else {
      errorsCopy.capacity = "";
    }
    if (formData.price < 0) {
      errorsCopy.price = "Price must be greater than zero";
      isValid = false;
    } else {
      errorsCopy.price = "";
    }
    if (
      formData.description.length < 10 ||
      formData.description.length > 1000
    ) {
      errorsCopy.description =
        "Description must be between 10 and 1000 characters";
      isValid = false;
    } else {
      errorsCopy.description = "";
    }

    setErrors(errorsCopy);
    if (!isValid) return;
    let resEvent;

    try {
      resEvent = await createEvent(formData);
      console.log(formData);
      alert("Event created successfully!");
      setFormData({
        userId: user.userId,
        title: "",
        category: "",
        startTime: "",
        endTime: "",
        location: "",
        capacity: 0,
        description: "",
        price: 0,
      });
      navigate(`../add-media/${resEvent.id}`);
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
        <form
          onSubmit={handleSubmit}
          method="post"
          encType="multipart/form-data"
        >
          <h1>Create an event</h1>
          <h2 className="sectionTitle">Event Details</h2>
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
              {categories.map((cat) => (
                <option key={cat} value={cat}>
                  {cat}
                </option>
              ))}
            </select>
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
                min={new Date().toISOString().slice(0, -8)}
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
                min={
                  formData.startTime > new Date().toISOString().slice(0, -8)
                    ? formData.startTime
                    : new Date().toISOString().slice(0, -8)
                }
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
            {errors.location && (
              <p className="error-message">{errors.location}</p>
            )}
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
            {errors.description && (
              <p className="error-message">{errors.description}</p>
            )}
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
