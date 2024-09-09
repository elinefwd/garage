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
    private Long userId; // Changed from userID to userId for consistency

    private String username;
    private String password;
    private String role;

    // Default constructor
    public User() {
    }

    // Getters and Setters
    public Long getUserId() { // Changed from getUserID to getUserId
        return userId;
    }

    public void setUserId(Long userId) { // Changed from setUserID to setUserId
        this.userId = userId;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
