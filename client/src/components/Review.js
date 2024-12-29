import React, { useState } from 'react';

export const Review = () => {
    const [rating, setRating] = useState(0); // Rating state (1 to 5)
    const [comment, setComment] = useState(''); // Comment state
    const [submitted, setSubmitted] = useState(false); // To track if the form is submitted

    // Handle the star rating click
    const handleRatingClick = (star) => {
        setRating(star);
    };

    // Handle the comment change
    const handleCommentChange = (event) => {
        setComment(event.target.value);
    };

    // Handle the form submission
    const handleSubmit = (event) => {
        event.preventDefault();
        if (rating === 0) {
            alert('Please select a rating.');
        } else {
            console.log('Review Submitted:', { rating, comment });
            // send to server
            setSubmitted(true); // Set submitted state to true
        }
    };

    return (
        <div>
            <h2>Leave a Review</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <h3>Rating</h3>
                    <div>
                        {[1, 2, 3, 4, 5].map((star) => (
                            <span
                                key={star}
                                onClick={() => handleRatingClick(star)}
                                style={{
                                    fontSize: '30px',
                                    cursor: 'pointer',
                                    color: star <= rating ? '#FFD700' : '#dcdcdc',
                                }}
                            >
                                ★
                            </span>
                        ))}
                    </div>
                </div>

                <div>
                    <h3>Comment</h3>
                    <textarea
                        value={comment}
                        onChange={handleCommentChange}
                        placeholder="Write your review..."
                        rows="4"
                        cols="50"
                    ></textarea>
                </div>

                <button type="submit">Submit Review</button>
            </form>

            {submitted && (
                <div>
                    <h3>Thank you for your review!</h3>
                    <p>Rating: {rating} stars</p>
                    <p>Comment: {comment}</p>
                </div>
            )}
        </div>
    );
};
