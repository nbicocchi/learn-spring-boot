package com.baeldung.ls.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenProjectExists_thenGetSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/projects/1")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                        .value("Project 1"));
    }

    @Test
    public void whenProjectNotExists_thenGetFailure() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/projects/11")
                        .contentType("application/json"))
                .andExpect(status().isNotFound());
    }
}
