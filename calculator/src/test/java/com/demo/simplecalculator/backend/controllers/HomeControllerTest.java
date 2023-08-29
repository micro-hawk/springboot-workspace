package com.demo.simplecalculator.backend.controllers;

import com.demo.simplecalculator.backend.service.CalculatorHistoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;


@WebMvcTest
@ExtendWith(SpringExtension.class)
public class HomeControllerTest {
    
    @Autowired
    MockMvc mockMvc;
    
    @MockBean
    private CalculatorHistoryService calculatorHistoryService;
    
    @Test
    void getHomeRedirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(forwardedUrl("/index.html"));
    }
    
}
