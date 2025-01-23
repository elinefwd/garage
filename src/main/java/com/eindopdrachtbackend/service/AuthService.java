package com.eindopdrachtbackend.service;

import com.eindopdrachtbackend.exception.UserNotFound;
import com.eindopdrachtbackend.model.ApplicationUser; // Update import to ApplicationUser
import com.eindopdrachtbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository; // Repository to access user data

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Password encoder for hashing

    public ApplicationUser authenticate(String username, String rawPassword) { // Change return type to ApplicationUser
        ApplicationUser user = userRepository.findByUsername(username) // Change to ApplicationUser
                .orElseThrow(() -> new UserNotFound("User not found with username: " + username));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid username or password."); // Consider creating a custom exception for this
        }

        return user; // Return the authenticated user if successful
    }
}
