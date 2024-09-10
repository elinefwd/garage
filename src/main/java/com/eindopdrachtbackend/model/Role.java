package com.eindopdrachtbackend.model;

public enum Role {
    ADMIN,
    EMPLOYEE;

    // Static method to get Role from String
    public static Role fromString(String roleString) {
        if (roleString == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }

        try {
            return Role.valueOf(roleString.toUpperCase()); // Convert to uppercase for safe comparison
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + roleString);
        }
    }
}
