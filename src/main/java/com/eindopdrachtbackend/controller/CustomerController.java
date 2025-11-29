package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.dto.CustomerDto;
import com.eindopdrachtbackend.exception.UserNotFound;
import com.eindopdrachtbackend.model.ApplicationUser;
import com.eindopdrachtbackend.model.Customer;
import com.eindopdrachtbackend.model.Document;
import com.eindopdrachtbackend.repository.CustomerRepository;
import com.eindopdrachtbackend.repository.UserRepository;
import com.eindopdrachtbackend.service.CustomerService;
import com.eindopdrachtbackend.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.eindopdrachtbackend.exception.InvalidInput;


import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createCustomer(@RequestBody CustomerDto customerDto) {
        // Validaties toevoegen
        if (customerDto.getName() == null || customerDto.getName().isBlank()) {
            throw new InvalidInput("Naam mag niet leeg zijn");
        }
        if (customerDto.getEmail() == null || !customerDto.getEmail().contains("@")) {
            throw new InvalidInput("Ongeldig emailadres");
        }

        ApplicationUser user = userRepository.findById(customerDto.getUserId())
                .orElseThrow(() -> new UserNotFound("User not found"));

        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setAddress(customerDto.getAddress());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setEmail(customerDto.getEmail());
        customer.setUser(user);

        Customer savedCustomer = customerRepository.save(customer);

        return ResponseEntity.ok(Map.of(
                "message", "Customer created successfully",
                "data", savedCustomer
        ));
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customerOpt = customerService.getCustomerById(id);
        if (customerOpt.isPresent()) {
            return ResponseEntity.ok(Map.of(
                    "message", "Customer found",
                    "data", customerOpt.get()
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Customer not found for id " + id));
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(Map.of(
                "message", "All customers retrieved",
                "data", customers
        ));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        customer.setCustomerId(id);
        Customer updatedCustomer = customerService.updateCustomer(customer);
        return ResponseEntity.ok(Map.of(
                "message", "Customer updated successfully",
                "data", updatedCustomer
        ));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(Map.of("message", "Customer deleted successfully"));
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getCurrentCustomer(Principal principal) {
        Customer customer = customerService.findByUsername(principal.getName());
        return ResponseEntity.ok(Map.of(
                "message", "Your customer details",
                "data", customer
        ));
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/me/documents")
    public ResponseEntity<Map<String, Object>> getMyDocuments(Principal principal) {
        List<Document> docs = documentService.getDocumentsForCustomer(principal.getName());
        return ResponseEntity.ok(Map.of(
                "message", "Your documents",
                "data", docs
        ));
    }
}
