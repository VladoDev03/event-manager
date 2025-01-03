import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { AuthProvider } from './contexts/AuthContext';
import { Auth } from './components/Auth';
import { ImageForm } from './components/ImageForm';
import { DeleteMedia } from './components/DeleteMedia';
import { ReviewForm } from './components/ReviewForm';
import { DeleteReview } from './components/DeleteReview';
import HomePage from './components/HomePage';
import EventForm from './components/EventForm';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/create-event" element={<EventForm />} />
        <Route path="/login" element={
          <AuthProvider>
            <Auth />
            <ImageForm />
            <DeleteMedia />
            <ReviewForm />
            <DeleteReview />
          </AuthProvider>
        } />
      </Routes>
    </Router>
  );
}

export default App;
