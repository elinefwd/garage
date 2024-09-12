package com.eindopdrachtbackend.model;

public enum Role {
    ADMIN,
    EMPLOYEE,
    CUSTOMER;

    // Static method to get Role from String
    public static Role fromString(String roleString) {
        if (roleString == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }

        // Convert to uppercase for comparison
        try {
            return Role.valueOf(roleString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + roleString);
        }
    }
}
