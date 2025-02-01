const baseUrl = "http://localhost:8080/api/wishlist";

export const addToWishlist = async (eventId, userId) => {
  const wishlist = {
    eventId,
    userId
  };

  const response = await fetch(`${baseUrl}/add`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(wishlist)
  });
  if (!response.ok) {
    throw new Error("Failed to add to wishlist");
  }
};

export const getWishlist = async (userId) => {
  const response = await fetch(`${baseUrl}/${userId}`);
  if (!response.ok) {
    throw new Error("Failed to fetch wishlist");
  }
  return response.json();
};

export const removeFromWishlist = async (eventId, userId) => {
  const wishlist = {
    eventId,
    userId
  };

  const response = await fetch(`${baseUrl}/remove`, {
    method: "DELETE",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(wishlist)
  });
  if (!response.ok) {
    throw new Error("Failed to remove from wishlist");
  }
};
