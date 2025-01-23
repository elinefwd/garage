package com.eindopdrachtbackend.repository;

import com.eindopdrachtbackend.model.Customer;
import com.eindopdrachtbackend.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    // Method to find documents based on the customer's name (if using the name directly)
    List<Document> findByCustomer_Name(String name); // This will look for the 'name' field in Customer

    // Alternatively, if you already have the Customer object
    List<Document> findByCustomer(Customer customer);
}
