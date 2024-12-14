package com.example.event_manager.service;

import com.example.event_manager.bindingmodels.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final List<User> users;

    public CustomUserDetailsService() {
        users = new ArrayList<>();
        users.addAll(List.of(
                new User("test@test.test", "123", "test"),
                new User("testuser@test.test", "123", "testuser")
        ));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getEmail())
                        .password(user.getPassword())
                        .roles("USER") // Add roles as needed
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
