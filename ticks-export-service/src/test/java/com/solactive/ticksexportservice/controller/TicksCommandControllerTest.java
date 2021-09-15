package com.solactive.ticksexportservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solactive.ticksexportservice.model.Tick;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TicksCommandControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testTicks() throws Exception {
        Tick tick = new Tick(1478192204000L, 5.24, 1D, "EUR", "AAPL.OQ");
        this.mockMvc.perform(
                        post("/ticks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(tick)))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    public void testTicksFailure() throws Exception {
        Tick tick = new Tick();
        this.mockMvc.perform(
                        post("/ticks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(tick)))
                .andDo(print()).andExpect(status().is4xxClientError());
    }
}
