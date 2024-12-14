package com.example.event_manager.service;

import com.example.event_manager.bindingmodels.AuthenticationRequest;
import com.example.event_manager.bindingmodels.AuthenticationResponse;
import com.example.event_manager.bindingmodels.RegisterRequest;
import com.example.event_manager.bindingmodels.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    private final List<User> users;

    public AuthService(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            PasswordEncoder passwordEncoder
            ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;

        users = new ArrayList<>();
    }

    public AuthenticationResponse register(RegisterRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(request.getEmail(), encodedPassword, "");
        users.add(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        var user = users.stream()
                .filter(u -> u.getEmail().equals(request.getEmail()))
                .findFirst()
                .get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
