package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.dto.UserDto;
import com.eindopdrachtbackend.model.User;
import com.eindopdrachtbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize; // Import for role-based access control
import org.springframework.web.bind.annotation.*;
import com.eindopdrachtbackend.exception.UserNotFound;

import java.util.Optional;

@RestController
@RequestMapping("/users") // Base URL for user-related APIs
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')") // Accessible to ADMIN only
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) {
        User createdUser = userService.createUser(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getRole()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'EMPLOYEE')") // Accessible to ADMIN, USER, and EMPLOYEE
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = Optional.ofNullable(userService.getUserById(id));
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get()); // Return user details
        } else {
            throw new UserNotFound("User not found with id: " + id);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'EMPLOYEE')") // Accessible to ADMIN, USER, and EMPLOYEE
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFound("User not found with username: " + username));
        return ResponseEntity.ok(user); // Return the found user
    }

    @PreAuthorize("hasRole('USER')") // Allow regular users to update their details
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setUserId(id);
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')") // Accessible to ADMIN only
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Service is running!");
    }
}


