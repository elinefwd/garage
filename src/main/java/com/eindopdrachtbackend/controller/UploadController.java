package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.dto.DocumentDto;
import com.eindopdrachtbackend.model.Customer;
import com.eindopdrachtbackend.model.Document;
import com.eindopdrachtbackend.service.CustomerService;
import com.eindopdrachtbackend.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private CustomerService customerService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Map<String, Object>> handleFileUpload(@ModelAttribute DocumentDto documentDto) {
        if (documentDto.getFileContent() == null || documentDto.getFileContent().length == 0) {
            return ResponseEntity.badRequest().body(Map.of("message", "File is empty."));
        }

        try {
            String filename = documentDto.getFilename();
            byte[] fileContent = documentDto.getFileContent();

            Document document = documentService.saveDocument(filename, fileContent);

            Optional<Customer> optionalCustomer = customerService.getCustomerById(documentDto.getCustomerId());
            if (optionalCustomer.isPresent()) {
                document.setCustomer(optionalCustomer.get());
                documentService.saveDocument(document);
                return ResponseEntity.ok(Map.of(
                        "message", "File uploaded successfully",
                        "filename", filename,
                        "documentId", document.getId()
                ));
            } else {
                return ResponseEntity.badRequest().body(Map.of("message", "Customer not found."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to upload file: " + e.getMessage()));
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/documents")
    public ResponseEntity<Map<String, Object>> getAllDocuments() {
        List<Document> docs = documentService.getAllDocuments();
        return ResponseEntity.ok(Map.of("message", "All documents retrieved", "data", docs));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/documents/{id}")
    public ResponseEntity<Map<String, Object>> getDocumentById(@PathVariable Long id) {
        Optional<Document> docOpt = documentService.findById(id);
        if (docOpt.isPresent()) {
            return ResponseEntity.ok(Map.of(
                    "message", "Document found",
                    "data", docOpt.get()
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Document not found for id " + id));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/documents/{id}")
    public ResponseEntity<Map<String, String>> deleteDocument(@PathVariable Long id) {
        try {
            documentService.deleteById(id);
            return ResponseEntity.ok(Map.of("message", "Document deleted"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Document not found"));
        }
    }
}
