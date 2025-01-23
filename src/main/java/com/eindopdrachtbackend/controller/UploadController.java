package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.model.Document;
import com.eindopdrachtbackend.model.Customer;
import com.eindopdrachtbackend.service.DocumentService;
import com.eindopdrachtbackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/upload") // Base URL for upload-related APIs
public class UploadController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private CustomerService customerService; // Use of CustomerService to find customers

    @PreAuthorize("hasRole('ADMIN')") // Restrict access to ADMIN
    @PostMapping
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("customerId") Long customerId) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty.");
        }

        try {
            // Get the file content as a byte array
            byte[] fileContent = file.getBytes();
            String filename = file.getOriginalFilename();

            // Create and save the document
            Document document = documentService.saveDocument(filename, fileContent); // Save document with filename and file content

            // Fetch the customer and associate the document with it
            Optional<Customer> optionalCustomer = customerService.getCustomerById(customerId);
            if (optionalCustomer.isPresent()) {
                document.setCustomer(optionalCustomer.get()); // Link document to the customer
                documentService.saveDocument(document); // Save again to keep the customer link
            } else {
                return ResponseEntity.badRequest().body("Customer not found.");
            }

            return ResponseEntity.ok("File uploaded successfully: " + filename);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file: " + e.getMessage());
        }
    }
}
