package com.eindopdrachtbackend.repository;

import com.eindopdrachtbackend.model.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectionRepository extends JpaRepository<Inspection, Long> {
    // You can add custom query methods here if needed
}
