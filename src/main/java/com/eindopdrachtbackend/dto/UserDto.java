package com.eindopdrachtbackend.dto;

import com.eindopdrachtbackend.model.Role; // Import the Role enum
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {

    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    // Change role from int to Role enum
    private Role role; // Now a Role enum instead of int

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() { // Change return type to Role
        return role;
    }

    public void setRole(Role role) { // Change parameter type to Role
        this.role = role;
    }
}
