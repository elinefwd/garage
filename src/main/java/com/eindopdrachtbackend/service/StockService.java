package com.eindopdrachtbackend.service;

import com.eindopdrachtbackend.model.Stock;
import com.eindopdrachtbackend.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public Stock createStock(Stock stock) {
        return stockRepository.save(stock); // Save the stock item
    }

    public List<Stock> getAllStock() {
        return stockRepository.findAll(); // Retrieve all stock items
    }

    public Optional<Stock> getStockById(Long id) {
        return stockRepository.findById(id); // Get stock item by ID
    }

    public Stock updateStock(Stock stock) {
        return stockRepository.save(stock); // Update stock item
    }

    public void deleteStock(Long id) {
        stockRepository.deleteById(id); // Delete stock item
    }
}
