package com.eindopdrachtbackend.service;

import com.eindopdrachtbackend.model.Inspection;
import com.eindopdrachtbackend.model.Vehicle;
import com.eindopdrachtbackend.repository.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InspectionService {

    @Autowired
    private InspectionRepository inspectionRepository;

    @Autowired
    private VehicleService vehicleService; // Inject the VehicleService

    public List<Inspection> getAllInspections() {
        return inspectionRepository.findAll(); // Return all inspections from the repository
    }

    public Inspection findById(Long id) {
        Optional<Inspection> optionalInspection = inspectionRepository.findById(id);
        return optionalInspection.orElse(null); // Return null if not found
    }

    public Inspection registerInspection(Inspection inspection) {
        // Validate that the vehicle exists
        if (inspection.getVehicle() != null && inspection.getVehicle().getVehicleID() != null) {
            System.out.println("Registering inspection: " + inspection); // Log the incoming inspection object

            Vehicle vehicle = vehicleService.findById(inspection.getVehicle().getVehicleID());
            if (vehicle == null) {
                throw new RuntimeException("Vehicle not found");
            }
            inspection.setVehicle(vehicle); // Set the existing vehicle
        } else {
            throw new RuntimeException("Vehicle information is required");
        }

        // Calculate costs based on the actions selected
        double totalCost = calculateCost(inspection.getAction());
        inspection.setCost(totalCost); // Set the calculated cost
        return inspectionRepository.save(inspection); // Save the inspection and return it
    }




    private double calculateCost(String actions) {
        double totalCost = 0.0;

        // Split the actions by comma or other delimiters
        String[] actionList = actions.split(",");

        for (String action : actionList) {
            switch (action.trim()) { // Trim whitespace from the action
                case "Basic Check":
                    totalCost += 50.00; // Cost for Basic Check
                    break;
                case "Full Inspection":
                    totalCost += 150.00; // Cost for Full Inspection
                    break;
                case "Replacement Mirror":
                    totalCost += 75.00; // Cost for Mirror Replacement
                    break;
                case "Replacement Wheel":
                    totalCost += 120.00; // Cost for Wheel Replacement
                    break;
                case "Replacement Wipers":
                    totalCost += 30.00; // Cost for Wiper Replacement
                    break;
                default:
                    // Log or handle unknown actions if necessary
                    break;
            }
        }

        return totalCost; // Return the total calculated cost
    }
}
