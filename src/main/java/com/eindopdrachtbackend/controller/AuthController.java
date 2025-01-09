package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.dto.UserDto;
import com.eindopdrachtbackend.exception.UserNotFound;
import com.eindopdrachtbackend.model.User;
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
    private BCryptPasswordEncoder passwordEncoder; // Autowire the password encoder

   // @PostMapping("/login") // Endpoint for user login
//    public ResponseEntity<User> login(@Valid @RequestBody UserDto userDto) {
  //      User user = userService.findByUsername(userDto.getUsername())
       //         .orElseThrow(() -> new UserNotFound("User not found with username: " + userDto.getUsername()));

        // Password verification logic
    //    if (!verifyPassword(userDto.getPassword(), user.getPassword())) {
      //      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401 Unauthorized
       // }

      //  return ResponseEntity.ok(user); // User authenticated
    //}

    @PostMapping("/login") // Endpoint for user login
    public ResponseEntity<User> login(@Valid @RequestBody UserDto userDto) {
        // Fetch user by username
        User user = userService.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new UserNotFound("User not found with username: " + userDto.getUsername()));

        // Remove password verification for testing
        // Instead of matching the password, just return the user if found
        // If you want to compare against a hardcoded password for testing, you could do:
        if (!userDto.getPassword().equals("wachtwoord1")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401 Unauthorized
        }

        return ResponseEntity.ok(user); // User authenticated
    }

    private boolean verifyPassword(String inputPassword, String storedPassword) {
        return passwordEncoder.matches(inputPassword, storedPassword); // Hash comparison
    }
}
