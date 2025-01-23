package com.eindopdrachtbackend.model;

import jakarta.persistence.*;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the document

    private String filename; // Original name of the uploaded file

    @Lob // Indicates that this is a large object (for storing file content)
    private byte[] fileContent; // Content of the uploaded file

    @ManyToOne // This indicates a many-to-one relationship with Customer
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    private Customer customer; // Link to the associated customer

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Additional method to easily fetch the customer's name
    public String getCustomerName() {
        return customer != null ? customer.getName() : null; // Safely retrieve customer's name
    }
}
