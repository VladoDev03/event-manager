package com.example.event_manager.dto;

public class UserListDto {
    private String email;
    private String password;
    private String username;

    public UserListDto(String email, String password, String username) {
        setEmail(email);
        setPassword(password);
        setUsername(username);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
