package com.example.event_manager.controller;

import com.example.event_manager.bindingmodels.User;
import com.example.event_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public User getUser() {
        return new User("test", "test123", "");
    }

    @PostMapping("/register")
    public User createUser() {
        return new User("test", "test123", "");
    }
}
