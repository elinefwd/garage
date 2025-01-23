package com.eindopdrachtbackend.integrationtest;

import org.junit.jupiter.api.Test; // For @Test annotation

import com.eindopdrachtbackend.model.Stock;
import com.eindopdrachtbackend.service.StockService;
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

    private static final String STOCK_ENDPOINT = "/stock"; // Updated endpoint without "api"

    // Test 1: Verifying GET endpoint for a Stock by ID
    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetStockById() throws Exception {
        Stock stock = new Stock();
        stock.setId(1L);
        stock.setPartName("Transmission");
        stock.setQuantity(10);
        stock.setPrice(199.99);

        when(stockService.getStockById(1L)).thenReturn(Optional.of(stock));

        mockMvc.perform(get(STOCK_ENDPOINT + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.partName").value("Transmission"))
                .andExpect(jsonPath("$.quantity").value(10))
                .andExpect(jsonPath("$.price").value(199.99));
    }

    // Test 2: Verifying POST endpoint for creating a Stock
    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreateStock() throws Exception {
        Stock stock = new Stock();
        stock.setId(2L);
        stock.setPartName("Engine");
        stock.setQuantity(5);
        stock.setPrice(299.99);

        when(stockService.createStock(any(Stock.class))).thenReturn(stock);

        String stockJson = "{\"partName\": \"Engine\", \"quantity\": 5, \"price\": 299.99}";

        mockMvc.perform(post(STOCK_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stockJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.partName").value("Engine"))
                .andExpect(jsonPath("$.quantity").value(5))
                .andExpect(jsonPath("$.price").value(299.99));
    }

    // New test: Verifying GET endpoint for a Stock not found
    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetStockByIdNotFound() throws Exception {
        when(stockService.getStockById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get(STOCK_ENDPOINT + "/999"))
                .andExpect(status().isNotFound());
    }


    // Optionally: Include more tests for other scenarios as needed.
}
