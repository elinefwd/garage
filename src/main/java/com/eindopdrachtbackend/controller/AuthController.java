package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.dto.UserDto;
import com.eindopdrachtbackend.exception.UserNotFound;
import com.eindopdrachtbackend.model.User;
import com.eindopdrachtbackend.security.JwtUtil; // Import the JwtUtil
import com.eindopdrachtbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/auth") // Base URL for authentication-related APIs
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil; // Autowire the JWT utility for token generation

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Autowire the password encoder

    @PostMapping("/login") // Endpoint for user login
    public ResponseEntity<?> login(@Valid @RequestBody UserDto userDto) {
        // Fetch user by username
        User user = userService.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new UserNotFound("User not found with username: " + userDto.getUsername()));

        // Verify password using BCrypt
        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password.");
        }


        // Generate JWT token
        String token = jwtUtil.generateToken(user.getUsername(), String.valueOf(user.getRole())); // Include role if necessary

        // Return token in the response
        return ResponseEntity.ok().body(new AuthResponse(token)); // Create an AuthResponse class to encapsulate the response
    }

    private boolean verifyPassword(String inputPassword, String storedPassword) {
        return passwordEncoder.matches(inputPassword, storedPassword); // Hash comparison
    }

    // Create a response class to include token
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
