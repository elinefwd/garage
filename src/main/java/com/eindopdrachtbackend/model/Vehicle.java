package com.eindopdrachtbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty; // Import the JsonProperty annotation
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("vehicleID") // Bind JSON field "vehicleID" to this field
    private Long vehicleID;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false) // Explicitly define the join column
    private Customer customer; // Linking to Customer

    @NotBlank(message = "License Plate is required") // Validation for not blank license plate
    private String licensePlate;

    @NotBlank(message = "Model is required") // Validation for not blank model
    private String model;

    private int year; // Represents the manufacture year of the vehicle

    private String uploadedDocuments; // Any documents uploaded related to the vehicle

    // Getters and Setters
    public Long getVehicleID() {
        return vehicleID; // Getter for vehicleID
    }

    public void setVehicleID(Long vehicleID) {
        this.vehicleID = vehicleID; // Setter for vehicleID
    }

    @JsonProperty("customer") // Include customer in the JSON response
    public Customer getCustomer() {
        return customer; // Getter for Customer
    }

    public void setCustomer(Customer customer) {
        this.customer = customer; // Setter for Customer
    }

    public String getLicensePlate() {
        return licensePlate; // Getter for licensePlate
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate; // Setter for licensePlate
    }

    public String getModel() {
        return model; // Getter for model
    }

    public void setModel(String model) {
        this.model = model; // Setter for model
    }

    public int getYear() {
        return year; // Getter for year
    }

    public void setYear(int year) {
        this.year = year; // Setter for year
    }

    public String getUploadedDocuments() {
        return uploadedDocuments; // Getter for uploadedDocuments
    }

    public void setUploadedDocuments(String uploadedDocuments) {
        this.uploadedDocuments = uploadedDocuments; // Setter for uploadedDocuments
    }
}
