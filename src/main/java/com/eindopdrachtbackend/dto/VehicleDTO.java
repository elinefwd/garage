package com.eindopdrachtbackend.dto;

public class VehicleDTO {
    private Long customerId; // Optional: ID of existing customer

    private String name; // New customer information (if creating a new customer)
    private String address;
    private String phoneNumber;
    private String email;

    private String licensePlate;
    private String model;
    private int year;

    // Getters and Setters
}
