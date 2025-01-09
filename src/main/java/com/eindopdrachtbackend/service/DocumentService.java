package com.eindopdrachtbackend.service;

import com.eindopdrachtbackend.model.Document;
import com.eindopdrachtbackend.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document saveDocument(String filename, String filepath) {
        Document document = new Document();
        document.setFilename(filename);
        document.setFilepath(filepath);
        return documentRepository.save(document);
    }
}
