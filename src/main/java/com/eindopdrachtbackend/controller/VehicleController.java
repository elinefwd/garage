package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.model.Vehicle;
import com.eindopdrachtbackend.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER', 'EMPLOYEE')")
    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        // Delegate the creation logic to the service
        Vehicle createdVehicle = vehicleService.createVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicle); // Return created vehicle and 201 Created status
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        vehicle.setVehicleID(id); // Make sure to set the ID for the update
        Vehicle updatedVehicle = vehicleService.updateVehicle(vehicle);
        if (updatedVehicle != null) {
            return ResponseEntity.ok(updatedVehicle);
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.findById(id);
        if (vehicle != null) {
            return ResponseEntity.ok(vehicle); // Return 200 OK with the vehicle
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
