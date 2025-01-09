package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.model.Document;
import com.eindopdrachtbackend.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class UploadController {

    @Autowired
    private DocumentService documentService; // Gebruik van de DocumentService

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String uploadDir = "uploads"; // Directory om de geüploade bestanden op te slaan
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs(); // Maak de directory aan als deze niet bestaat
        }

        // Haal de originele bestandsnaam van het geüploade bestand op
        String filename = file.getOriginalFilename();

        if (filename == null || filename.isEmpty()) {
            return ResponseEntity.badRequest().body("File name is invalid.");
        }

        // Bepaal het pad waar het bestand opgeslagen zal worden
        String filePath = uploadDir + File.separator + filename;

        try {
            // Sla het bestand lokaal op
            file.transferTo(new File(filePath));

            // Bewaar bestandsmetadata in de database
            documentService.saveDocument(filename, filePath); // Gebruik de service hier

            return ResponseEntity.ok("File uploaded successfully: " + filename);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file: " + e.getMessage());
        }
    }
}
