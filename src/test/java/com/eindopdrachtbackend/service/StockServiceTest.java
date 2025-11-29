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
    void testCreateStock_AdminRole() {
        Stock stock = new Stock();
        stock.setPartName("Transmission");
        stock.setQuantity(10);
        stock.setPrice(199.99);

        when(stockRepository.save(any(Stock.class))).thenReturn(stock);

        Stock createdStock = stockService.createStock(stock);

        assertNotNull(createdStock);
        assertEquals("Transmission", createdStock.getPartName());
        assertEquals(10, createdStock.getQuantity());
        assertEquals(199.99, createdStock.getPrice(), 0.01);

        verify(stockRepository, times(1)).save(stock);
    }

    @Test
    void testGetAllStock_ForAdminRole() {
        Stock stock1 = new Stock();
        stock1.setPartName("Transmission");
        Stock stock2 = new Stock();
        stock2.setPartName("Engine");

        when(stockRepository.findAll()).thenReturn(List.of(stock1, stock2));

        List<Stock> stockList = stockService.getAllStock();

        assertEquals(2, stockList.size());
        verify(stockRepository, times(1)).findAll();
    }

    @Test
    void testGetStockById_Exists_ForEmployeeRole() {
        Stock stock = new Stock();
        stock.setId(1L);
        stock.setPartName("Transmission");

        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        Optional<Stock> found = stockService.getStockById(1L);

        assertTrue(found.isPresent());
        assertEquals("Transmission", found.get().getPartName());
        verify(stockRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteStock() {
        Long stockId = 1L;

        // Geen teruggaand object nodig, alleen verificatie
        stockService.deleteStock(stockId);

        verify(stockRepository, times(1)).deleteById(stockId);
    }
}
