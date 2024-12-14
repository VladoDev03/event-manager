package com.example.event_manager.bindingmodels;

public class AuthenticationRequest {
    private String email;
    private String password;

    public AuthenticationRequest(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
