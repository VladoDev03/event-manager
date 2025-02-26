import { useState, useContext } from 'react';
import * as authService from '../services/authService'
import { AuthContext } from "../contexts/AuthContext";

export function Auth() {
    const { userLogin, userLogout } = useContext(AuthContext)

    const [regData, setRegData] = useState();
    const [logData, setLogData] = useState();

    const newUser = {
        username: "testUser1@gmail.com",
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
                userLogin(result);
                setLogData(result);
                console.log(result);
            })
    };

    const logoutHandler = () => {
        userLogout();
        setRegData('');
        setLogData('');
    };

    return (
        <div className='App'>
            <h1 onClick={registerHandler}>Register</h1>
            <h1 onClick={loginHandler}>Login</h1>
            <h1 onClick={logoutHandler}>Logout</h1>
            <h2>{regData?.access_token ?? 'regToken'}</h2>
            <h2>{logData?.access_token ?? 'logToken'}</h2>
        </div>
    );
}
