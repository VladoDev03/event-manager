import React from "react";
import { Route, Routes } from "react-router-dom";
import HomePage from "./components/HomePage";
import Wishlist from "./components/Wishlist";
import EventForm from "./components/EventForm";
import SearchResultPage from "./components/SearchResultPage";
import EventPage from "./components/EventPage";
import MyTickets from "./components/MyTickets";
import { Auth } from "./components/Auth";
import { ImageForm } from "./components/ImageForm";
import { DeleteMedia } from "./components/DeleteMedia";
import { ReviewForm } from "./components/ReviewForm";
import { DeleteReview } from "./components/DeleteReview";
import { AuthProvider } from "./contexts/AuthContext";
import useWishlist from "./hooks/useWishlist";

function App() {
  const { wishlist, addToWishlist, removeFromWishlist } = useWishlist();

  return (
    <AuthProvider>
      <div>
      <Routes>
        <Route
          path="/"
          element={<HomePage addToWishlist={addToWishlist} />}
        />

        <Route
          path="/wishlist"
          element={
            <Wishlist
              wishlist={wishlist}
              removeFromWishlist={removeFromWishlist}
            />
          }
        />

        <Route path="/create-event" element={<EventForm />} />

        <Route
          path="/login"
          element={
            <div>
              <Auth />
              <ImageForm />
              <DeleteMedia />
              <ReviewForm />
              <DeleteReview />
            </div>
          }
        />

        <Route
          path="/searchEvents"
          element={
            <SearchResultPage />
          }
        />
        
        <Route
          path="/event/:id"
          element={
            <EventPage />
          } 
        />

        <Route 
        path="/myTickets"
        element={
          <MyTickets />
        }
        />


      </Routes>
    </div>
    </AuthProvider>
  );
}

export default App;
