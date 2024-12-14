import './App.css';
import { AuthProvider } from './contexts/AuthContext'
import Auth from './components/Auth';

function App() {
  return (
    <AuthProvider>
      <Auth />
    </AuthProvider>
  );
}

export default App;