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
import { ImageForm } from "./components/ImageForm";
import { AuthProvider } from "./contexts/AuthContext";
import { Logout } from "./components/Logout";
import { NotFound } from "./components/NotFoundPage";

function App() {
  return (
    <AuthProvider>
      <div>
        <Routes>
          <Route
            path="/"
            element={<HomePage />}
          />

          <Route
            path="/wishlist"
            element={
              <Wishlist />
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
            element={<SearchResultPage />}
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

        <Route
          path="/notFound"
          element={
            <NotFound />
        }
        />

        <Route
          path="/*"
          element={
            <NotFound />
        }
        />

        </Routes>
      </div>
    </AuthProvider>
  );
}

export default App;
