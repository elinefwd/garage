package com.eindopdrachtbackend.controller;

import com.eindopdrachtbackend.dto.StockDto;
import com.eindopdrachtbackend.exception.InvalidInput;
import com.eindopdrachtbackend.model.Stock;
import com.eindopdrachtbackend.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @PostMapping
    public ResponseEntity<Map<String, Object>> createStock(@RequestBody StockDto stockDto) {
        // Validaties
        if (stockDto.getPartName() == null || stockDto.getPartName().isBlank()) {
            throw new InvalidInput("Partnaam mag niet leeg zijn");
        }
        if (stockDto.getPrice() == null || stockDto.getPrice() <= 0) {
            throw new InvalidInput("Prijs moet groter dan nul zijn");
        }

        Stock stock = new Stock();
        stock.setPartName(stockDto.getPartName());
        stock.setPrice(stockDto.getPrice());

        Stock createdStock = stockService.createStock(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Stock aangemaakt",
                "data", createdStock
        ));
    }


    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStock() {
        List<Stock> stocks = stockService.getAllStock();
        return ResponseEntity.ok(Map.of(
                "message", "Stock list retrieved",
                "data", stocks
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getStockById(@PathVariable Long id) {
        Optional<Stock> stockOpt = stockService.getStockById(id);
        if (stockOpt.isPresent()) {
            return ResponseEntity.ok(Map.of(
                    "message", "Stock found",
                    "data", stockOpt.get()
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", "Stock not found for id " + id
            ));
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateStock(@PathVariable Long id, @RequestBody Stock stock) {
        stock.setId(id);
        Stock updatedStock = stockService.updateStock(stock);
        return ResponseEntity.ok(Map.of(
                "message", "Stock updated successfully",
                "data", updatedStock
        ));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.ok(Map.of("message", "Stock deleted successfully"));
    }
}
