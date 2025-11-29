package com.eindopdrachtbackend.service;

import com.eindopdrachtbackend.model.Document;
import com.eindopdrachtbackend.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        documentRepository.save(document); // Save the document
    }

    // Method to get all documents
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    // Method to find a document by ID
    public Optional<Document> findById(Long id) {
        return documentRepository.findById(id);
    }

    // Method to delete a document by ID
    public void deleteById(Long id) {
        if (documentRepository.existsById(id)) {
            documentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Document not found");
        }
    }

    // Method to get documents for a specific customer by naam
    public List<Document> getDocumentsForCustomer(String name) {
        return documentRepository.findByCustomer_Name(name);  // Zorg dat die methode bestaat in je repo
    }
}
