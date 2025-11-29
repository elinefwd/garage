package com.eindopdrachtbackend.service;

import com.eindopdrachtbackend.model.Customer;
import com.eindopdrachtbackend.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreateCustomer() {
        // Arrange
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setAddress("123 Main St");
        customer.setPhoneNumber("1234567890");
        customer.setEmail("john.doe@example.com");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        // Act
        Customer createdCustomer = customerService.createCustomer("John Doe", "123 Main St", "1234567890", "john.doe@example.com");

        // Assert
        assertNotNull(createdCustomer);
        assertEquals("John Doe", createdCustomer.getName());
        assertEquals("123 Main St", createdCustomer.getAddress());
        assertEquals("1234567890", createdCustomer.getPhoneNumber());
        assertEquals("john.doe@example.com", createdCustomer.getEmail());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetCustomerById_Exists() {
        // Arrange
        Customer customer = new Customer();
        customer.setCustomerId(1L); // Use setCustomerId instead of setId
        customer.setName("John Doe");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // Act
        Optional<Customer> foundCustomer = customerService.getCustomerById(1L);

        // Assert
        assertTrue(foundCustomer.isPresent());
        assertEquals("John Doe", foundCustomer.get().getName());
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetCustomerById_NotExists() {
        // Arrange
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        Optional<Customer> foundCustomer = customerService.getCustomerById(99L);

        // Assert
        assertFalse(foundCustomer.isPresent());
        verify(customerRepository, times(1)).findById(99L);
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteCustomer() {
        // Arrange
        Long customerId = 1L;

        // Act
        customerService.deleteCustomer(customerId);

        // Assert
        verify(customerRepository, times(1)).deleteById(customerId);
    }
}

