import React, { useEffect, useState } from 'react';
import { getEventById } from '../services/eventService';
import { format } from 'date-fns';

const EventInfo = ({ eventId, setEventUserId, setEventEndDate }) => {
    const [event, setEvent] = useState(null);

    const formatDate = (date) => {
        return format(date, "dd MMMM yyyy");
    };

    const formatTime = (time) => {
        return format(time, "dd MMMM yyyy HH:mm");
    };

    useEffect(() => {
        const fetchEvent = async () => {
            const data = await getEventById(eventId);
            setEvent(data);
            setEventUserId(data.userId);
            setEventEndDate(formatDate(data.endTime));
        };
        fetchEvent();
    }, [eventId]);

    if (!event) return <p>Loading event details...</p>;

    const date = event.startTime ? new Date(event.startTime).toISOString().split('T')[0] : "";

    return (
        <div className="eventInfoContainer">
            <p className="eventDateHeader">{formatDate(date)}</p>
            <h1 className="eventTitle">{event.title || "Event title"}</h1>
            <p className="eventSummary">{event.summary || "No summary available."}</p>
            <div className="dateAndTimeWrapper">
                <h2>Date and time</h2>
                <span>{formatTime(event.startTime) || "Time not specified"} | {formatTime(event.endTime) || "Time not specified"}</span>
            </div>
            <div className="locationWrapper">
                <h2>Location</h2>
                <span>{event.location || "Location not specified"}</span>
            </div>
            <div className="eventDescription">
                <h2>About this event</h2>
                <p>{event.description || "No description available."}</p>
            </div>
            <div>
                {
                    event.medias.map(m => {
                        return <img key={m.url} src={m.url}></img>
                    })
                }
            </div>
        </div>
    );
};

export default EventInfo;
