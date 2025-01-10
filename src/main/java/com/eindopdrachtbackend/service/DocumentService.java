package com.eindopdrachtbackend.service;


import com.eindopdrachtbackend.model.Document;
import com.eindopdrachtbackend.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    // Existing method to save document based on filename and content
    public Document saveDocument(String filename, byte[] fileContent) {
        Document document = new Document();
        document.setFilename(filename);
        document.setFileContent(fileContent);
        return documentRepository.save(document);
    }

    // New method to save a complete Document object
    public void saveDocument(Document document) {
        documentRepository.save(document); // Save the document in the database
    }
}

