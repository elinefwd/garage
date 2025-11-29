package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.dto.UserDto;
import com.eindopdrachtbackend.exception.UserNotFound;
import com.eindopdrachtbackend.model.ApplicationUser;
import com.eindopdrachtbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@Valid @RequestBody UserDto userDto) {
        ApplicationUser createdUser = userService.createUser(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getRole()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "User successfully created",
                "data", createdUser
        ));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {
        Optional<ApplicationUser> userOptional = Optional.ofNullable(userService.getUserById(id));
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(Map.of(
                    "message", "User found",
                    "data", userOptional.get()
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "User not found with id " + id));
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        List<ApplicationUser> users = userService.getAllUsers();
        return ResponseEntity.ok(Map.of(
                "message", "All users retrieved",
                "data", users
        ));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER', 'EMPLOYEE')")
    @GetMapping("/username/{username}")
    public ResponseEntity<Map<String, Object>> getUserByUsername(@PathVariable String username) {
        ApplicationUser user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFound("User not found with username: " + username));
        return ResponseEntity.ok(Map.of(
                "message", "User found",
                "data", user
        ));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Long id, @RequestBody ApplicationUser user) {
        user.setUserId(id);
        ApplicationUser updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(Map.of(
                "message", "User successfully updated",
                "data", updatedUser
        ));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Service is running!");
    }
}
