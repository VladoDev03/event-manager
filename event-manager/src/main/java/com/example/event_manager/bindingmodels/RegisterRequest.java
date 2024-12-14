package com.example.event_manager.bindingmodels;

public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    public RegisterRequest(String firstname, String lastname, String email, String password) {
        setFirstname(firstname);
        setLastname(lastname);
        setEmail(email);
        setPassword(password);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
