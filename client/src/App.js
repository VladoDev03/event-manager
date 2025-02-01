import React from "react";
import { Route, Routes } from "react-router-dom";
import HomePage from "./components/HomePage";
import Wishlist from "./components/Wishlist";
import EventForm from "./components/EventForm";
import MyEvents from "./components/MyEvents";
import SearchResultPage from "./components/SearchResultsPage";
import FindEventsPage from "./components/FindEventsPage";
import EventPage from "./components/EventPage";
import MyTickets from "./components/MyTickets";
import Login from "./components/Login";
import { SignupForm } from "./components/SignupForm";
import { Auth } from "./components/Auth";
import { ImageForm } from "./components/ImageForm";
import { DeleteMedia } from "./components/DeleteMedia";
import { ReviewForm } from "./components/ReviewForm";
import { DeleteReview } from "./components/DeleteReview";
import { AuthProvider } from "./contexts/AuthContext";
import useWishlist from "./hooks/useWishlist";
import { Logout } from "./components/Logout";

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
          <Route path="/myEvents" element={<MyEvents />} />

          {/* <Route
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
        /> */}

          <Route
            path="/searchEvents/:params"
            element={<SearchResultPage addToWishlist={addToWishlist} />}
          />

          <Route path="/findEvents" element={<FindEventsPage />} />

          <Route path="/event/:eventId" element={<EventPage />} />

          <Route path="/myTickets" element={<MyTickets />} />

          <Route path="/login" element={<Login />} />

          <Route
            path="/signup"
            element={
              <div>
                <SignupForm />
              </div>
            }
          />

          <Route
            path="/logout"
            element={
              <div>
                <Logout />
              </div>
            }
          />

          <Route
            path="/add-media/:eventId"
            element={
              <div>
                <ImageForm />
              </div>
            }
          />
        </Routes>
      </div>
    </AuthProvider>
  );
}

export default App;
