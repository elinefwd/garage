package com.eindopdrachtbackend.repository;

import com.eindopdrachtbackend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Method to find a customer by their customer ID
    Customer findByCustomerId(Long customerId);


    // Method to delete a customer by their customer ID
    void deleteByCustomerId(Long customerId);


    // Method to find a customer by their name
    Optional<Customer> findByName(String name); // Now finding by name
}

