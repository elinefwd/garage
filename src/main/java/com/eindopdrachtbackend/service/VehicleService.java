package com.eindopdrachtbackend.service;

import com.eindopdrachtbackend.model.Customer; // Import Customer model
import com.eindopdrachtbackend.model.Vehicle;
import com.eindopdrachtbackend.repository.CustomerRepository; // Ensure you create a repository for Customer
import com.eindopdrachtbackend.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository; // Repository for database interaction

    @Autowired
    private CustomerRepository customerRepository; // Repository to find customers

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        // Check of kenteken al bestaat
        Optional<Vehicle> existingVehicle = vehicleRepository.findByLicensePlate(vehicle.getLicensePlate());
        if (existingVehicle.isPresent()) {
            throw new RuntimeException("Kenteken bestaat al");
        }

        // Controleer of de customer bestaat, dat doe je al
        if (vehicle.getCustomer() != null && vehicle.getCustomer().getCustomerId() != null) {
            Optional<Customer> optionalCustomer = customerRepository.findById(vehicle.getCustomer().getCustomerId());
            if (optionalCustomer.isPresent()) {
                vehicle.setCustomer(optionalCustomer.get());
            } else {
                throw new RuntimeException("Customer niet gevonden");
            }
        } else {
            throw new RuntimeException("Customer ID is vereist");
        }

        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle); // Save should update if it exists
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public Vehicle findById(Long id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        return optionalVehicle.orElse(null); // Return null if not found
    }
}
