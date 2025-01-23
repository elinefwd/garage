package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.model.Customer;
import com.eindopdrachtbackend.model.Document;
import com.eindopdrachtbackend.service.CustomerService;
import com.eindopdrachtbackend.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers") // Base URL for customer-related APIs
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DocumentService documentService; // Service to handle document-related operations

    // Allow ADMIN and EMPLOYEE to create a new customer
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer.getName(), customer.getAddress(), customer.getPhoneNumber(), customer.getEmail());
    }

    // Allow ADMIN and EMPLOYEE to get customer by ID
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Allow ADMIN and EMPLOYEE to update customer details
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        customer.setCustomerId(id); // Ensure we're using the right ID
        Customer updatedCustomer = customerService.updateCustomer(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    // Allow ADMIN and EMPLOYEE to delete customer by ID
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build(); // Return a 204 No Content response
    }

    // Allow customers to view their own details
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/me")
    public ResponseEntity<Customer> getCurrentCustomer(Principal principal) {
        // Assume you have a service method to find a customer by their username/email
        Customer customer = customerService.findByUsername(principal.getName());
        return ResponseEntity.ok(customer);
    }

    // Allow customers to view documents related to their account
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/me/documents")
    public ResponseEntity<List<Document>> getMyDocuments(Principal principal) {
        List<Document> documents = documentService.getDocumentsForCustomer(principal.getName());
        return ResponseEntity.ok(documents);
    }
}
