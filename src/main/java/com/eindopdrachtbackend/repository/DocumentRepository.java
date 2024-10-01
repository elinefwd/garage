// src/main/java/com/eindopdrachtbackend/repository/DocumentRepository.java

package com.eindopdrachtbackend.repository;

import com.eindopdrachtbackend.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
