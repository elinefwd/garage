package com.eindopdrachtbackend.service;

import com.eindopdrachtbackend.model.Inspection;
import com.eindopdrachtbackend.repository.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspectionService {

    @Autowired
    private InspectionRepository inspectionRepository; // Added missing semicolon

    public List<Inspection> getAllInspections() {
        return inspectionRepository.findAll();
    }

    public Inspection registerInspection(Inspection inspection) {
        // Calculate costs based on the action selected
        double totalCost = calculateCost(inspection.getAction());
        inspection.setCost(totalCost);
        return inspectionRepository.save(inspection);
    }

    private double calculateCost(String action) {
        // Implement your cost calculation logic here based on the selected action
        // For example:
        switch (action) {
            case "Basic Check":
                return 50.00;
            case "Full Inspection":
                return 150.00;
            // Add more actions and their costs as necessary
            default:
                return 0.00; // No cost for undefined actions
        }
    }
}
