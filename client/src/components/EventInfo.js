import React from 'react';

const EventInfo = () => {
    return (
        <div className="eventInfoContainer">
            <time className="eventDateHeader" dateTime="2024-11-07">Thursday, November 7</time>
            <h1 className="eventTitle">Event title</h1>
            <p className="eventSummary">Lorem ipsum dolor sit amet consectetur adipisicing elit. Ad nesciunt libero distinctio at eum, nulla pariatur quae doloribus perferendis error quidem dolores facilis repellendus ipsam fugit? Alias deserunt unde dignissimos.</p>
            <div className="dateAndTimeWrapper">
                <h2>Date and time</h2>
                <span>Thursday, November 7 6:30-8pm</span>
            </div>
            <div className="locationWrapper">
                <h2>Location</h2>
                <span>NBU</span>
            </div>
            <div className="eventDescription">
                <h2>About this event</h2>
                <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Veritatis cupiditate sed eligendi maiores exercitationem, sit numquam autem dolor! Illo magnam corrupti porro laborum molestias minus aliquid, ea excepturi laboriosam nisi!</p>
                <br />
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Iure iste animi adipisci cum eligendi, sunt itaque distinctio ab et. Porro incidunt vel magni nisi ducimus maxime earum quidem, beatae voluptatibus.</p>
            </div>
        </div>
    );
};

export default EventInfo;
