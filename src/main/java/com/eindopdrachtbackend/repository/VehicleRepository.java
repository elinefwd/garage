package com.eindopdrachtbackend.repository;

import com.eindopdrachtbackend.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    // You can add custom query methods here if needed
}
