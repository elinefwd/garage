package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.dto.UserDto;
import com.eindopdrachtbackend.model.User;
import com.eindopdrachtbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.eindopdrachtbackend.exception.UserNotFound;

import java.util.Optional;

@RestController
@RequestMapping("/users") // Basis-URL voor gebruikersgerelateerde API's
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) {
        // Directly use the values from UserDto to create the user
        User createdUser = userService.createUser(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getRole() // Ensure this returns an instance of Role
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    // Haal gebruiker op bij ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = Optional.ofNullable(userService.getUserById(id));
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get()); // Return ResponseEntity with user
        } else {
            throw new UserNotFound("User not found with id: " + id); // Throw exception if not found
        }
    }


    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFound("User not found with username: " + username));
        return ResponseEntity.ok(user); // Return the found user
    }


    // Werk gebruikersdetails bij
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setUserId(id); // Zorg ervoor dat we de juiste ID gebruiken
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    // Verwijder gebruiker op ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // Bevestig met een 204 No Content-respons
    }


    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Service is running!");
    }
}

