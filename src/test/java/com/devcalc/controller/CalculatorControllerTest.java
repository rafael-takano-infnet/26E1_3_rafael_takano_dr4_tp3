package com.devcalc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void sqrtEndpointReturnsResult() throws Exception {
        mockMvc.perform(get("/sqrt").param("x", "16"))
                .andExpect(status().isOk())
                .andExpect(content().string("4.0"));
    }

    @Test
    void sqrtEndpointRejectsNegativeNumbers() throws Exception {
        mockMvc.perform(get("/sqrt").param("x", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: Cannot calculate square root of a negative number"));
    }
}
