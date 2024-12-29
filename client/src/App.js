import './App.css';
import { AuthProvider } from './contexts/AuthContext'
import { Auth } from './components/Auth';
import { ImageForm } from './components/ImageForm';
import { DeleteMedia } from './components/DeleteMedia';
import { Review } from './components/Review';

function App() {
  return (
    <AuthProvider>
      <Auth />
      <ImageForm />
      <DeleteMedia />
      <Review />
    </AuthProvider>
  );
}

export default App;