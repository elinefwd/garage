package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.model.Stock;
import com.eindopdrachtbackend.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.eindopdrachtbackend.service.StockService;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stock") // Ensure this matches your test setup
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        Stock createdStock = stockService.createStock(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStock);
    }

    // Other methods remain unchanged



    // Get all stock items
    @GetMapping
    public List<Stock> getAllStock() {
        return stockService.getAllStock();
    }

    // Get stock item by ID
    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id) {
        Optional<Stock> stock = stockService.getStockById(id);
        return stock.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update stock item
    @PutMapping("/{id}")
    public Stock updateStock(@PathVariable Long id, @RequestBody Stock stock) {
        stock.setId(id); // Make sure to set the ID correctly for update
        return stockService.updateStock(stock);
    }

    // Delete stock item by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build(); // Return a 204 No Content response
    }
}
