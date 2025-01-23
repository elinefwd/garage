package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.model.Stock;
import com.eindopdrachtbackend.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stock") // Ensure this matches your test setup
public class StockController {

    @Autowired
    private StockService stockService;

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')") // Allow ADMIN and EMPLOYEE to create stock
    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        Stock createdStock = stockService.createStock(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStock);
    }

    @GetMapping
    public List<Stock> getAllStock() {
        return stockService.getAllStock(); // No restriction, allow all roles to view stock
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id) {
        Optional<Stock> stock = stockService.getStockById(id);
        return stock.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')") // Allow ADMIN and EMPLOYEE to update stock
    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long id, @RequestBody Stock stock) {
        stock.setId(id); // Make sure to set the ID correctly for update
        Stock updatedStock = stockService.updateStock(stock);
        return ResponseEntity.ok(updatedStock);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')") // Allow ADMIN and EMPLOYEE to delete stock
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build(); // Return a 204 No Content response
    }
}
