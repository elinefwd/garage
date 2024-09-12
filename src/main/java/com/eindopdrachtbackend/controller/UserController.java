package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.model.Role; // Make sure this import is present
import com.eindopdrachtbackend.dto.UserDto;
import com.eindopdrachtbackend.exception.Usernotfound;
import com.eindopdrachtbackend.model.User;
import com.eindopdrachtbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users") // Base URL for user-related APIs
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user (admin or employee)


    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) {
        // You may want to convert userDto to User before calling userService
        User user = new User(userDto.getUsername(), userDto.getPassword(), Role.fromString(userDto.getRole()));
        User createdUser = userService.createUser(user); // Update this line based on your UserService method
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }



    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new Usernotfound("User not found with id: " + id));
    }


    // Update user details
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setUserId(id); // Ensure we're using the right ID
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // Return a 204 No Content response
    }
}
