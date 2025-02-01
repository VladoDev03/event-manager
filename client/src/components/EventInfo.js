import React, { useEffect, useState } from "react";
import { getEventById } from "../services/eventService";
import { format } from "date-fns";
import "../style/Homepage.css";
import { useNavigate } from "react-router-dom";
import Reviews from "./Reviews";

const EventInfo = ({ eventId, setEventUserId, setEventEndDate, setReviewExists }) => {
  const navigate = useNavigate();
  const [event, setEvent] = useState(null);

  const formatDate = (date) => {
    return format(date, "dd MMMM yyyy");
  };
  const formatTime = (time) => {
    return format(time, "dd MMMM yyyy HH:mm");
  };

  useEffect(() => {
    const fetchEvent = async () => {
      try{
        const data = await getEventById(eventId);
        setEvent(data);
        setEventUserId(data.userId);
        setEventEndDate(formatTime(data.endTime));
      } catch (error) {
        console.error("Failed to fetch event", error);
        navigate("/notFound");
      }
    };
    fetchEvent();
  }, [eventId]);

  if (!event) return <p>Loading event details...</p>;

  const date = event.startTime
    ? new Date(event.startTime).toISOString().split("T")[0]
    : "";

  return (
    <div className="eventInfoContainer">
      <p className="eventDateHeader">{formatDate(date)}</p>
      <h1 className="eventTitle">{event.title || "Event title"}</h1>
      <div className="dateAndTimeWrapper">
        <h2>Date and time</h2>
        <span>
          {formatTime(event.startTime) || "Time not specified"} |{" "}
          {formatTime(event.endTime) || "Time not specified"}
        </span>
      </div>
      <div className="locationWrapper">
        <h2>Location</h2>
        <span>{event.location || "Location not specified"}</span>
      </div>
      <div className="eventDescriptionInfo">
        <h2>About this event</h2>
        <p>{event.description || "No description available."}</p>
      </div>
      <h2>Media</h2>
      <div className="mediaDisplay">
        {event.medias.map((m) =>
          m.url.includes("video") ? (
            <video key={m.url} src={m.url} controls width="100%" />
          ) : (
            <img key={m.url} src={m.url} alt="Event media" width="100%" />
          )
        )}
      </div>
    <Reviews eventId={eventId} userId={event.userId} setReviewExists={setReviewExists}/>
    </div>
  );
};

export default EventInfo;
