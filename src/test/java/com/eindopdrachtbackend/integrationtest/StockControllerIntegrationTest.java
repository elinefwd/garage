package com.eindopdrachtbackend.integrationtest;

import org.junit.jupiter.api.Test; // For @Test annotation

import com.eindopdrachtbackend.model.Stock;
import com.eindopdrachtbackend.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StockControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    // Test 1: Verifying GET endpoint for a Stock by ID
    @Test
    @WithMockUser // This simulates a logged-in user
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
    @WithMockUser // This simulates a logged-in user
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

    // Optionally: Add more tests for error scenarios
    // For example: Handling stock not found, bad requests, etc.
}
