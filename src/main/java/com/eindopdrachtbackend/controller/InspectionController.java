package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.model.Inspection;
import com.eindopdrachtbackend.service.InspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inspections")
public class InspectionController {

    @Autowired
    private InspectionService inspectionService;

    @GetMapping
    public List<Inspection> getAllInspections() {
        return inspectionService.getAllInspections(); // Public access for viewing all inspections
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')") // Allow ADMIN and EMPLOYEE to register inspections
    @PostMapping
    public Inspection registerInspection(@RequestBody Inspection inspection) {
        return inspectionService.registerInspection(inspection);
    }

    // Add additional endpoints for specific inspection needs or calculations
}
