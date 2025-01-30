package com.example.event_manager.service;

import com.example.event_manager.dto.AuthenticationRequest;
import com.example.event_manager.dto.AuthenticationResponse;
import com.example.event_manager.dto.RegisterRequest;
import com.example.event_manager.dto.UserListDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthService(
            JwtService jwtService,
            PasswordEncoder passwordEncoder, UserService userService
    ) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;

        this.userService = userService;
    }

//    public AuthenticationResponse register(RegisterRequest request) {
//        String encodedPassword = passwordEncoder.encode(request.getPassword());
//
//        UserListDto user = new UserListDto(request.getEmail(), encodedPassword, "");
//        users.add(user);
//
//        var jwtToken = jwtService.generateToken(user);
//
//        return AuthenticationResponse.builder()
//                .accessToken(jwtToken)
//                .build();
//    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        UserListDto user = userService.getUserByEmail(request.getEmail());

        if (user == null) {
            throw new RuntimeException("Invalid credentials");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public boolean checkIfUserExists(AuthenticationRequest request) {
        if(userService.getUserByEmail(request.getEmail()) == null) {
            return false;
        }
        return true;
    }
}
