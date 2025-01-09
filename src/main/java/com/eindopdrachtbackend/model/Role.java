package com.eindopdrachtbackend.model;

public enum Role {
    ADMIN(1),
    USER(2),
    EMPLOYEE(3),
    CUSTOMER(4);

    private final int value;

    // Constructor
    Role(int value) {
        this.value = value;
    }

    // Get the integer value associated with the role
    public int getValue() {
        return this.value;
    }

    // Static method to convert an integer into a Role enum
    public static Role fromInt(int value) {
        for (Role role : Role.values()) {
            if (role.getValue() == value) {
                return role; // Return the matching Role
            }
        }
        throw new IllegalArgumentException("No role found for value: " + value);
    }
}
