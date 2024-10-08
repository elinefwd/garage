package com.eindopdrachtbackend.service;

import com.eindopdrachtbackend.model.User;
import com.eindopdrachtbackend.model.Role; // Import the Role enum
import com.eindopdrachtbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String username, String password, Role role) { // Change the third parameter to Role
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashPassword(password)); // Hash the password before storing
        user.setRole(role); // Set the role directly as Role
        return userRepository.save(user); // Save the user object
    }


    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id); // Use the repository to find the user
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id); // Room for implementing business logic
    }

    // Placeholder for actual password hashing logic
    private String hashPassword(String password) {
        // Implement your password hashing logic here
        return password; // Return the hashed password
    }

    public User updateUser(User user) {
        return user;
    }

    public User createUser(User user) {
        return user;
    }
}
