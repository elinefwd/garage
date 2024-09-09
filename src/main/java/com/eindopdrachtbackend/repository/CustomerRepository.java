package com.eindopdrachtbackend.repository;

import com.eindopdrachtbackend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Method to find a customer by its customer Id
    Customer findByCustomerId(Long customerId); // Find customer by Id
    // Method to delete a customer by its customer Id
    void deleteByCustomerId(Long customerId); // Delete customer by Id
}

