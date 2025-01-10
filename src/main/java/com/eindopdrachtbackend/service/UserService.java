package com.eindopdrachtbackend.service;

import com.eindopdrachtbackend.model.User;
import com.eindopdrachtbackend.model.Role; // Import the Role enum
import com.eindopdrachtbackend.repository.UserRepository;
import com.eindopdrachtbackend.exception.UserNotFound; // Import the UserNotFound exception
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String username, String password, Role role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashPassword(password)); // Hash the password before storing
        user.setRole(role.getValue()); // Set the role using the integer value from the Role enum
        return userRepository.save(user); // Save the user object
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("User not found with id: " + id)); // Throw exception if not found
    }

    public Optional<User> findByUsername(String username) {
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

    public User updateUser(User user) {
        if (!userRepository.existsById(user.getUserId())) { // Check if user exists
            throw new UserNotFound("User not found with id: " + user.getUserId()); // Throw exception if not found
        }
        return userRepository.save(user); // Ensure you save the updated user
    }
}
