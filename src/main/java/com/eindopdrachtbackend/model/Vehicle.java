package com.eindopdrachtbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleID;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false) // Explicitly define the join column
    private Customer customer; // Linking to Customer

    @NotBlank(message = "License Plate is required")
    private String licensePlate;

    @NotBlank(message = "Model is required")
    private String model;

    private int year;
    private String uploadedDocuments;

    // Getters and Setters
    public Long getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(Long vehicleID) {
        this.vehicleID = vehicleID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getUploadedDocuments() {
        return uploadedDocuments;
    }

    public void setUploadedDocuments(String uploadedDocuments) {
        this.uploadedDocuments = uploadedDocuments;
    }
}
