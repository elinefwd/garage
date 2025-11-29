package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.dto.InspectionDto;
import com.eindopdrachtbackend.model.Inspection;
import com.eindopdrachtbackend.model.Vehicle;
import com.eindopdrachtbackend.service.InspectionService;
import com.eindopdrachtbackend.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inspections")
public class InspectionController {

    @Autowired
    private InspectionService inspectionService;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllInspections() {
        List<Inspection> inspections = inspectionService.getAllInspections();
        return ResponseEntity.ok(Map.of(
                "message", "All inspections retrieved",
                "data", inspections
        ));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteInspection(@PathVariable Long id) {
        if (inspectionService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Inspection not found for id " + id));
        }
        inspectionService.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Inspection deleted"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getInspectionById(@PathVariable Long id) {
        Inspection inspection = inspectionService.findById(id);
        if (inspection != null) {
            return ResponseEntity.ok(Map.of(
                    "message", "Inspection found",
                    "data", inspection
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Inspection not found for id " + id));
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @PostMapping
    public ResponseEntity<Map<String, Object>> registerInspection(@RequestBody InspectionDto inspectionDto) {
        if (inspectionDto.getVehicleId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Vehicle ID is required"));
        }
        Vehicle vehicle = vehicleService.findById(inspectionDto.getVehicleId());
        if (vehicle == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Vehicle not found"));
        }

        // Maak een nieuwe entity van de DTO
        Inspection inspection = new Inspection();
        inspection.setVehicle(vehicle);
        inspection.setAction(inspectionDto.getAction());
        inspection.setDate(inspectionDto.getDate());

        double totalCost = inspectionService.calculateCost(inspectionDto.getAction());

        // Sla de inspectie op
        Inspection saved = inspectionService.registerInspection(inspection);

        // Geef reactie met bericht en data
        return ResponseEntity.ok(Map.of(
                "message", "Inspection registered successfully",
                "data", saved
        ));
    }

}