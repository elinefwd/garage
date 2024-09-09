package com.eindopdrachtbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleID;

    @ManyToOne
    private Customer customer;

    private String licensePlate;
    private String model;
    private int year;
    private String uploadedDocuments;
    // Add any additional attributes as needed

    // Getters and Setters
}


