package com.eindopdrachtbackend.service;

import com.eindopdrachtbackend.model.Customer;
import com.eindopdrachtbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public Customer createCustomer(String name, String address, String phoneNumber, String email) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setPhoneNumber(phoneNumber);
        customer.setEmail(email);
        Customer savedCustomer = customerRepository.save(customer);
        System.out.println("Customer opgeslagen met ID: " + savedCustomer.getCustomerId());
        return savedCustomer;
    }



    public Optional<Customer> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public Customer updateCustomer(Customer updatedCustomer) {
        // Validate that the customer exists before updating
        if (!customerRepository.existsById(updatedCustomer.getCustomerId())) {
            throw new IllegalArgumentException("Customer not found");
        }
        return customerRepository.save(updatedCustomer);
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    // Method to find the customer by username
    public Customer findByUsername(String username) {
        return customerRepository.findByName(username) // Assuming `findByUsername` is implemented in `CustomerRepository`
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with username: " + username));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


}
