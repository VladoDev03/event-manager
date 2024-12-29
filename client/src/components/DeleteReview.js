import * as reviewService from '../services/reviewService';
import { useState } from 'react';

export function DeleteReview() {
    const [reviewId, setReviewId] = useState('');

    const reviewHandler = e => {
        setReviewId(e.target.value);
    };

    const submitHandler = async (e) => {
        e.preventDefault();
        
        const result = await reviewService.deleteReview(reviewId);
        console.log("Review URL:", result);
    };

    return (
        <form onSubmit={submitHandler} method="post">
            <div>
                <label htmlFor="picture">Review Id</label>
                <input
                    id="review-id"
                    type="text"
                    name="review-id"
                    value={reviewId}
                    onChange={reviewHandler}
                    required
                />
            </div>
            <input type="submit" value="Delete" />
        </form>
    );
}
