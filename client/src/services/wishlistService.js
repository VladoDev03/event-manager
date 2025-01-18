const baseUrl = "http://localhost:8080/api/wishlist";

export const addToWishlist = async (event) => {
  const response = await fetch(`${baseUrl}/add`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(event),
  });
  if (!response.ok) {
    throw new Error("Failed to add to wishlist");
  }
};

export const getWishlist = async () => {
  const response = await fetch(`${baseUrl}`);
  if (!response.ok) {
    throw new Error("Failed to fetch wishlist");
  }
  return response.json();
};

export const removeFromWishlist = async (id) => {
  const response = await fetch(`${baseUrl}/remove/${id}`, { method: "DELETE" });
  if (!response.ok) {
    throw new Error("Failed to remove from wishlist");
  }
};
