import React, { useState, useEffect, useContext } from "react";
import { getReviewsByEventId } from "../services/reviewService";
import { AuthContext } from "../contexts/AuthContext";

function Reviews({ eventId, setReviewExists }) {
  const [reviews, setReviews] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  
  const { user } = useContext(AuthContext);

  useEffect(() => {
    const fetchReviews = async () => {
      try {
        const reviewsData = await getReviewsByEventId(eventId);
        setReviews(reviewsData);

        reviewsData.forEach(r => {
          if (r.userId == user.userId) {
            setReviewExists(true);
            return;
          }})
      } catch (err) {
        setError(`Failed to fetch reviews: ${err.message}`);
      } finally {
        setLoading(false);
      }
    };

    if (eventId) {
      fetchReviews();
    }
  }, [eventId]);


  if (loading) return <p>Fetching reviews...</p>;
  if (error) return <p style={{ color: "red" }}>{error}</p>;

  return (
    <div>
      <h2>Reviews</h2>
      {reviews.length === 0 ? (
        <p>No reviews yet.</p>
      ) : (
        <ul>
          {reviews.map((review) => (
            <li key={review.id || review.comment}>
              <p>
                <strong>Rating:</strong> {review.rating}
              </p>
              <p>
                <strong>Comment:</strong> {review.comment}
              </p>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default Reviews;