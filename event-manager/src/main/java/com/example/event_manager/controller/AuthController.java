package com.example.event_manager.controller;

import com.example.event_manager.dto.AuthenticationRequest;
import com.example.event_manager.dto.AuthenticationResponse;
import com.example.event_manager.dto.RegisterRequest;
import com.example.event_manager.exception.ExistingUserException;
import com.example.event_manager.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        try {
            AuthenticationResponse result = authService.register(request);
            return ResponseEntity.ok(result);
        } catch (ExistingUserException e) {
            return ResponseEntity.badRequest().body(new AuthenticationResponse(null, e.getMessage(), 0));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        try{
            AuthenticationResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            AuthenticationResponse response = AuthenticationResponse.builder()
                    .accessToken(null, e.getMessage(), 0)
                    .build();
            return ResponseEntity.badRequest().body(response);
        }



    }


}
