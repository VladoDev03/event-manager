package com.example.event_manager.service;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final List<User> users;

    UserService() {
        users = new ArrayList<>();
    }

    public User AddUser(User user) {
        users.add(user);
        return user;
    }
}
