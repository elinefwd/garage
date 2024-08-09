package com.eindopdrachtbackend.repository;

import com.eindopdrachtbackend.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    // You can define custom query methods here if needed
}
