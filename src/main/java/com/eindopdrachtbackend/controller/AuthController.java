package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.dto.UserDto;
import com.eindopdrachtbackend.exception.UserNotFound;
import com.eindopdrachtbackend.model.ApplicationUser;
import com.eindopdrachtbackend.security.JwtUtil;
import com.eindopdrachtbackend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth") // Base URL for authentication-related APIs
public class AuthController {

    @Autowired
    private AuthService authService; // AuthService will handle authentication logic

    @Autowired
    private JwtUtil jwtUtil; // Autowire JWT utility for token generation

    @PostMapping("/login") // Endpoint for user login
    public ResponseEntity<?> login(@Valid @RequestBody UserDto userDto) {
        try {
            ApplicationUser authenticatedUser = authService.authenticate(userDto.getUsername(), userDto.getPassword());
            String token = jwtUtil.generateToken(((ApplicationUser) authenticatedUser).getUsername(), String.valueOf(authenticatedUser.getRole()));
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (UserNotFound e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    public static class AuthResponse {
        private String token;

        public AuthResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }
}
