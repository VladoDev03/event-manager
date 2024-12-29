import './App.css';
import { AuthProvider } from './contexts/AuthContext'
import { Auth } from './components/Auth';
import { ImageForm } from './components/ImageForm';
import { DeleteMedia } from './components/DeleteMedia';
import { ReviewForm } from './components/ReviewForm';
import { DeleteReview } from './components/DeleteReview';

function App() {
  return (
    <AuthProvider>
      <Auth />
      <ImageForm />
      <DeleteMedia />
      <ReviewForm />
      <DeleteReview />
    </AuthProvider>
  );
}

export default App;