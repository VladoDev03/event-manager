import { useState } from "react";

const useWishlist = () => {
  const [wishlist, setWishlist] = useState([]);

  const addToWishlist = (event) => {
    setWishlist((prev) => [...prev, event]);
  };

  const removeFromWishlist = (id) => {
    setWishlist((prev) => prev.filter((event) => event.id !== id));
  };

  return {
    wishlist,
    addToWishlist,
    removeFromWishlist,
  };
};

export default useWishlist;
