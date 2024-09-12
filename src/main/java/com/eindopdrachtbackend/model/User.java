package com.eindopdrachtbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "application_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String password; // Should be hashed before storing
    private Role role; // Use the Role enum instead of String

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role; // Assign the Role enum
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password; // Consider omitting this getter for security
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() { // Change return type to Role
        return role; // Return the Role enum directly
    }

    public void setRole(Role role) {
        this.role = role; // This setter should accept a Role type
    }
}
