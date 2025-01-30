package com.example.event_manager.service;

import com.example.event_manager.dao.UserDao;
import com.example.event_manager.dto.*;
import com.example.event_manager.entity.User;
import com.example.event_manager.exception.ExistingUserException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    private final List<UserListDto> users;

    public AuthService(
            JwtService jwtService,
            PasswordEncoder passwordEncoder
    ) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;

        users = new ArrayList<>();
    }

    public AuthenticationResponse register(RegisterRequest request) throws ExistingUserException {
        if (UserDao.getUserByUsername(request.getUsername()) != null) {
            throw new ExistingUserException(request.getUsername());
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserListDto user = new UserListDto(request.getUsername(), encodedPassword, request.getFirstName(), request.getLastName(), LocalDateTime.now());
        User newUser = new User(user.getUsername(), encodedPassword, request.getFirstName(), request.getLastName(), LocalDateTime.now());

        UserDao.createUser(newUser);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken, null)
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        var user = users.stream()
                .filter(u -> u.getUsername().equals(request.getEmail()))
                .findFirst()
                .get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken, null)
                .build();
    }
}
