package com.eindopdrachtbackend.integrationtest;

import com.eindopdrachtbackend.model.Stock;
import com.eindopdrachtbackend.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StockControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    // Test 1: Verifying GET endpoint for a Stock by ID
    @Test
    void testGetStockById() throws Exception {
        Stock stock = new Stock();
        stock.setId(1L);
        stock.setPartName("Transmission");
        stock.setQuantity(10);
        stock.setPrice(199.99);

        when(stockService.getStockById(1L)).thenReturn(Optional.of(stock));

        mockMvc.perform(get("/api/stock/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.partName").value("Transmission"))
                .andExpect(jsonPath("$.quantity").value(10))
                .andExpect(jsonPath("$.price").value(199.99));
    }

    // Test 2: Verifying POST endpoint for creating a Stock
    @Test
    void testCreateStock() throws Exception {
        Stock stock = new Stock();
        stock.setId(2L);
        stock.setPartName("Engine");
        stock.setQuantity(5);
        stock.setPrice(299.99);

        when(stockService.createStock(any(Stock.class))).thenReturn(stock);

        String stockJson = "{\"partName\": \"Engine\", \"quantity\": 5, \"price\": 299.99}";

        mockMvc.perform(post("/api/stock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stockJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.partName").value("Engine"))
                .andExpect(jsonPath("$.quantity").value(5))
                .andExpect(jsonPath("$.price").value(299.99));
    }
}
