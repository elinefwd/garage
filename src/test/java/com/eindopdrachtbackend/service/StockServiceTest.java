package com.eindopdrachtbackend.service;

import com.eindopdrachtbackend.model.Stock;
import com.eindopdrachtbackend.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockService stockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStock() {
        // Arrange
        Stock stock = new Stock();
        stock.setPartName("Transmission");
        stock.setQuantity(10);
        stock.setPrice(199.99);

        when(stockRepository.save(any(Stock.class))).thenReturn(stock);

        // Act
        Stock createdStock = stockService.createStock(stock);

        // Assert
        assertNotNull(createdStock);
        assertEquals("Transmission", createdStock.getPartName());
        assertEquals(10, createdStock.getQuantity());
        assertEquals(199.99, createdStock.getPrice(), 0.01);
        verify(stockRepository, times(1)).save(stock);
    }

    @Test
    void testGetAllStock() {
        // Arrange
        Stock stock1 = new Stock();
        Stock stock2 = new Stock();
        stock1.setPartName("Transmission");
        stock2.setPartName("Engine");

        when(stockRepository.findAll()).thenReturn(List.of(stock1, stock2));

        // Act
        List<Stock> stockList = stockService.getAllStock();

        // Assert
        assertEquals(2, stockList.size());
        verify(stockRepository, times(1)).findAll();
    }

    @Test
    void testGetStockById_Exists() {
        // Arrange
        Stock stock = new Stock();
        stock.setId(1L);
        stock.setPartName("Transmission");

        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        // Act
        Optional<Stock> foundStock = stockService.getStockById(1L);

        // Assert
        assertTrue(foundStock.isPresent());
        assertEquals("Transmission", foundStock.get().getPartName());
        verify(stockRepository, times(1)).findById(1L);
    }

    @Test
    void testGetStockById_NotExists() {
        // Arrange
        when(stockRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        Optional<Stock> foundStock = stockService.getStockById(99L);

        // Assert
        assertFalse(foundStock.isPresent());
        verify(stockRepository, times(1)).findById(99L);
    }

    @Test
    void testUpdateStock() {
        // Arrange
        Stock stock = new Stock();
        stock.setId(1L);
        stock.setPartName("Transmission Updated");
        stock.setQuantity(15);
        stock.setPrice(249.99);

        when(stockRepository.save(any(Stock.class))).thenReturn(stock);

        // Act
        Stock updatedStock = stockService.updateStock(stock);

        // Assert
        assertNotNull(updatedStock);
        assertEquals("Transmission Updated", updatedStock.getPartName());
        assertEquals(15, updatedStock.getQuantity());
        assertEquals(249.99, updatedStock.getPrice(), 0.01);
        verify(stockRepository, times(1)).save(stock);
    }

    @Test
    void testDeleteStock() {
        // Arrange
        Long stockId = 1L;

        // Act
        stockService.deleteStock(stockId);

        // Assert
        verify(stockRepository, times(1)).deleteById(stockId);
    }
}
