package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.dto.UserDto;
import com.eindopdrachtbackend.model.ApplicationUser; // Updated import
import com.eindopdrachtbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.eindopdrachtbackend.exception.UserNotFound;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApplicationUser> createUser(@Valid @RequestBody UserDto userDto) {
        ApplicationUser createdUser = userService.createUser(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getRole() // Ensure this is a String
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationUser> getUserById(@PathVariable Long id) {
        Optional<ApplicationUser> userOptional = Optional.ofNullable(userService.getUserById(id));
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            throw new UserNotFound("User not found with id: " + id);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER', 'EMPLOYEE')")
    @GetMapping("/username/{username}")
    public ResponseEntity<ApplicationUser> getUserByUsername(@PathVariable String username) {
        ApplicationUser user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFound("User not found with username: " + username));
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApplicationUser> updateUser(@PathVariable Long id, @RequestBody ApplicationUser user) {
        user.setUserId(id);
        ApplicationUser updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
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
