package com.eindopdrachtbackend.repository;

import com.eindopdrachtbackend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

Customer createCustomer(Customer customer);
    Customer getCustomerById(int customerId);
    Customer updateCustomer(Customer customer);
    void deleteCustomer(int customerId);
}
