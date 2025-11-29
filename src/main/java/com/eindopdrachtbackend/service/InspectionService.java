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
    private VehicleService vehicleService;

    public List<Inspection> getAllInspections() {
        return inspectionRepository.findAll();
    }

    public Inspection findById(Long id) {
        Optional<Inspection> opt = inspectionRepository.findById(id);
        return opt.orElse(null);
    }

    public Inspection registerInspection(Inspection inspection) {
        if (inspection.getVehicle() != null && inspection.getVehicle().getVehicleID() != null) {
            Vehicle vehicle = vehicleService.findById(inspection.getVehicle().getVehicleID());
            if (vehicle == null) {
                throw new RuntimeException("Vehicle not found");
            }
            inspection.setVehicle(vehicle);
        } else {
            throw new RuntimeException("Vehicle information is required");
        }
        double totalCost = calculateCost(inspection.getAction());
        inspection.setCost(totalCost);
        return inspectionRepository.save(inspection);
    }

    public double calculateCost(String actions) {
        double totalCost = 0.0;
        String[] actionList = actions.split(",");
        for (String action : actionList) {
            switch (action.trim()) {
                case "Basic Check": totalCost += 50; break;
                case "Full Inspection": totalCost += 150; break;
                case "Replacement Mirror": totalCost += 75; break;
                case "Replacement Wheel": totalCost += 120; break;
                case "Replacement Wipers": totalCost += 30; break;
                default: break;
            }
        }
        return totalCost;
    }

    public void deleteById(Long id) {
        inspectionRepository.deleteById(id);
    }
}
