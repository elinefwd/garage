package com.eindopdrachtbackend.integrationtest;

import com.eindopdrachtbackend.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
