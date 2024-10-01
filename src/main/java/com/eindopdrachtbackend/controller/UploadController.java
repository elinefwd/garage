package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.model.Document;
import com.eindopdrachtbackend.repository.DocumentRepository;
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
    private DocumentRepository documentRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String uploadDir = "uploads"; // Directory to save the uploaded files
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs(); // Create the directory if it doesn't exist
        }

        // Retrieve the original filename of the uploaded file
        String filename = file.getOriginalFilename();

        // Construct the file path where the file will be saved
        String filePath = uploadDir + File.separator + filename;

        try {
            // Save the file locally
            file.transferTo(new File(filePath));

            // Save file metadata in the database
            Document document = new Document();
            document.setFilename(filename);  // Use the original filename here
            document.setFilepath(filePath);  // Save the path of the file
            documentRepository.save(document); // Save metadata to the database

            return ResponseEntity.ok("File uploaded successfully: " + filename);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
        }
    }
}

