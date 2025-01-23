package com.eindopdrachtbackend.service;

import com.eindopdrachtbackend.model.Document;
import com.eindopdrachtbackend.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    // Method to save a new document with filename and file content
    public Document saveDocument(String filename, byte[] fileContent) {
        Document document = new Document();
        document.setFilename(filename);
        document.setFileContent(fileContent);
        return documentRepository.save(document); // Save the new document
    }

    // Method to save an existing document (after linking it to a customer)
    public void saveDocument(Document document) {
        documentRepository.save(document); // Save the document to the repository
    }

    // Method to get all documents for a specific customer by their name
    public List<Document> getDocumentsForCustomer(String name) {
        return documentRepository.findByCustomer_Name(name); // Assuming this method is defined in DocumentRepository
    }

    // Other methods...
}

