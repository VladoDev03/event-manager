import React, { useState } from 'react';
import '../style/CreateEvent.css';
import { createEvent } from '../services/eventService';

const EventForm = () => {
  const [formData, setFormData] = useState({
    title: '',
    category: '',
    startDate: '',
    startTime: '',
    endTime: '',
    location: '',
    capacity: 0,
    description: '',
  });

  const handleChange = (e) => {
    const { id, value } = e.target;
    setFormData((prev) => ({ ...prev, [id]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await createEvent(formData);
      alert('Event created successfully!');
      setFormData({
        title: '',
        category: '',
        startDate: '',
        startTime: '',
        endTime: '',
        location: '',
        capacity: 0,
        description: '',
      });
    } catch (error) {
      alert('Error creating event. Please try again.');
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
            <label htmlFor="location">Where will your event take place? *</label>
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
