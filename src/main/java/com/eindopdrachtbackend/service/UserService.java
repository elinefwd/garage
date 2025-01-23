package com.eindopdrachtbackend.service;

import com.eindopdrachtbackend.model.ApplicationUser; // Updated import
import com.eindopdrachtbackend.repository.UserRepository;
import com.eindopdrachtbackend.exception.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ApplicationUser createUser(String username, String password, String role) {
        ApplicationUser user = new ApplicationUser(); // Change to ApplicationUser
        user.setUsername(username);
        user.setPassword(hashPassword(password)); // Hash the password before storing
        user.setRole(role); // Set the role directly as a String
        return userRepository.save(user); // Save the user object
    }

    public ApplicationUser getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("User not found with id: " + id)); // Throw exception if not found
    }

    public Optional<ApplicationUser> findByUsername(String username) {
        return userRepository.findByUsername(username); // This relies on your UserRepository
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) { // Check if user exists
            throw new UserNotFound("User not found with id: " + id); // Throw exception if not found
        }
        userRepository.deleteById(id); // Room for implementing business logic
    }

    // Placeholder for actual password hashing logic
    private String hashPassword(String password) {
        // Implement your password hashing logic here
        return password; // For demonstration, return the original password
    }

    public ApplicationUser updateUser(ApplicationUser user) { // Change to ApplicationUser
        if (!userRepository.existsById(user.getUserId())) { // Check if user exists
            throw new UserNotFound("User not found with id: " + user.getUserId()); // Throw exception if not found
        }
        return userRepository.save(user); // Ensure you save the updated user
    }
}
