import React, { useState } from 'react';
import * as reviewService from '../services/reviewService';

export const Review = () => {
    const [rating, setRating] = useState(0);
    const [comment, setComment] = useState('');
    const [submitted, setSubmitted] = useState(false);

    const handleRatingClick = star => {
        setRating(star);
    };

    const handleCommentChange = event => {
        setComment(event.target.value);
    };

    const handleSubmit = async event => {
        event.preventDefault();
        if (rating === null) {
            alert('Please select a rating.');
        } else {
            const review = { rating, comment };
            const result = await reviewService.uploadReview(review);

            console.log('Review Submitted:', result);
            console.log(review);
            
            setSubmitted(true);
        }
    };

    return (
        <div>
            <h2>Leave a Review</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <h3>Rating</h3>
                    <div>
                        {[0, 1, 2, 3, 4].map((star) => (
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
                    <p>Rating: {rating + 1} stars</p>
                    <p>Comment: {comment}</p>
                </div>
            )}
        </div>
    );
};
