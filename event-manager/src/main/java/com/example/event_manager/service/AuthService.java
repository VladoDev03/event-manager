package com.example.event_manager.service;

import com.example.event_manager.dao.UserDao;
import com.example.event_manager.dto.AuthenticationRequest;
import com.example.event_manager.dto.AuthenticationResponse;
import com.example.event_manager.dto.RegisterRequest;
import com.example.event_manager.dto.UserListDto;
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

    public AuthService(
            JwtService jwtService,
            PasswordEncoder passwordEncoder
    ) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthenticationResponse register(RegisterRequest request) throws ExistingUserException {
        if (UserDao.getUserByUsername(request.getUsername()) != null) {
            throw new ExistingUserException(request.getUsername());
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserListDto user = new UserListDto(request.getUsername(), encodedPassword, request.getFirstName(), request.getLastName(), LocalDateTime.now());
        User newUser = new User(user.getUsername(), encodedPassword, request.getFirstName(), request.getLastName(), LocalDateTime.now());

        UserDao.createUser(newUser);

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken, null, newUser.getId())
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        User user = UserDao.getUserByUsername(request.getUsername());
        UserListDto userListDto = new UserListDto();
        if(user != null) {
            userListDto.setUsername(user.getUsername());
            userListDto.setPassword(user.getPassword());
            userListDto.setFirstName(user.getFirstName());
            userListDto.setLastName(user.getLastName());
            userListDto.setCreationDate(user.getCreationDate());

        } else {
            System.out.println("first");
            System.out.println(request.getUsername());
            throw new RuntimeException("Invalid credentials");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            System.out.println("second");
            throw new RuntimeException("Invalid credentials");
        }

        String jwtToken = jwtService.generateToken(userListDto);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken, null, user.getId())
                .build();
    }
}