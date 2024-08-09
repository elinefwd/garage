package com.eindopdrachtbackend.service;

import com.eindopdrachtbackend.model.User;
import com.eindopdrachtbackend.repository.UserRepository; // Ensure this import is correct
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Autowire the UserRepository

    public User createUser(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Be mindful of password handling (e.g., hash passwords)
        user.setRole(role);

        return userRepository.save(user); // Save the new user to the database
    }

    public Optional<User> getUserById(Long userId) { // Change int to Long if your User ID is Long
        return userRepository.findById(userId); // Retrieve user by ID
    }

    public User updateUser(User updatedUser) {
        return userRepository.save(updatedUser); // Update the user
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId); // Delete the user by ID
    }
}
