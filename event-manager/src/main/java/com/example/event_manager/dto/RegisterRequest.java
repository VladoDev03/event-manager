package com.example.event_manager.dto;

public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public RegisterRequest(String firstname, String lastname, String email, String password) {
        setFirstName(firstname);
        setLastName(lastname);
        setUsername(email);
        setPassword(password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
