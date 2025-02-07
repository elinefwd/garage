package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.model.Inspection;
import com.eindopdrachtbackend.model.Vehicle;
import com.eindopdrachtbackend.service.CustomerService;
import com.eindopdrachtbackend.service.InspectionService;
import com.eindopdrachtbackend.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inspections")
public class InspectionController {

    @Autowired
    private InspectionService inspectionService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Inspection>> getAllInspections() {
        List<Inspection> inspections = inspectionService.getAllInspections(); // Get all inspections
        System.out.println("Retrieved inspections: " + inspections); // Log the retrieved inspections
        return ResponseEntity.ok(inspections); // Return the list of inspections with a 200 OK status
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<Inspection> getInspectionById(@PathVariable Long id) {
        Inspection inspection = inspectionService.findById(id);
        if (inspection != null) {
            return ResponseEntity.ok(inspection); // Return the found inspection
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if not found
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @PostMapping
    public Inspection registerInspection(@RequestBody Inspection inspection) {
        if (inspection.getVehicle() != null) {
            System.out.println("Looking for Vehicle ID: " + inspection.getVehicle().getVehicleID());
            Vehicle vehicle = vehicleService.findById(inspection.getVehicle().getVehicleID());
            if (vehicle == null) {
                throw new RuntimeException("Vehicle not found");
            }
            inspection.setVehicle(vehicle);
        } else {
            throw new RuntimeException("Vehicle information is required");
        }

        // Calculate initial cost based on actions entered
        double totalCost = calculateCost(inspection.getAction());
        inspection.setCost(totalCost); // Set the calculated cost
        return inspectionService.registerInspection(inspection);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @PutMapping("/{id}")
    public ResponseEntity<Inspection> updateInspection(@PathVariable Long id, @RequestBody Inspection inspection) {
        Inspection existingInspection = inspectionService.findById(id);
        if (existingInspection == null) {
            return ResponseEntity.notFound().build();
        }

        // Update fields
        existingInspection.setAction(inspection.getAction());
        existingInspection.setDate(inspection.getDate());

        // Calculate cost based on the updated actions
        existingInspection.setCost(calculateCost(inspection.getAction())); // Update the cost based on new actions

        Inspection updatedInspection = inspectionService.registerInspection(existingInspection);
        return ResponseEntity.ok(updatedInspection);
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
                    // Handle unknown actions if necessary or ignore
                    break;
            }
        }

        return totalCost; // Return the total calculated cost
    }

}

