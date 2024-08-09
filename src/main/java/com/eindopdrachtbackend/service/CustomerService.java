package com.eindopdrachtbackend.service;

import com.eindopdrachtbackend.model.Customer;
import com.eindopdrachtbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(Long customerId) { // Change int to Long
        return customerRepository.findById(customerId);
    }

    public Customer updateCustomer(Customer updatedCustomer) {
        return customerRepository.save(updatedCustomer);
    }

    public void deleteCustomer(Long customerId) { // Change int to Long
        customerRepository.deleteById(customerId);
    }
}
