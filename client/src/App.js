import './App.css';
import { useState } from 'react';
import * as authService from './services/authService'

function App() {
  const [regData, setRegData] = useState();
  const [logData, setLogData] = useState();

  const newUser = {
    email: "testUser1@gmail.com",
    password: "testtesttest"
  };

  const registerHandler = () => {
    authService.register(newUser)
      .then(result => {
        setRegData(result);
        console.log(result);
      })
  };

  const loginHandler = () => {
    authService.login(newUser)
      .then(result => {
        setLogData(result);
        console.log(result);
      })
  };

  return (
    <div className="App">
      <h1 onClick={registerHandler}>Register</h1>
      {/* <h2>{regData?.email ?? 'email'}</h2> */}
      {/* <h2>{regData?.password ?? 'password'}</h2> */}
      <h1 onClick={loginHandler}>Login</h1>
      {/* <h2>{logData?.email ?? 'email'}</h2>
      <h2>{logData?.password ?? 'password'}</h2> */}
      <h2>{regData?.access_token ?? 'regToken'}</h2>
      <h2>{logData?.access_token ?? 'logToken'}</h2>
    </div>
  );
}

export default App;
