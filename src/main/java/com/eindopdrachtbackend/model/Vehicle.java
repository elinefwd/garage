package com.eindopdrachtbackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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


