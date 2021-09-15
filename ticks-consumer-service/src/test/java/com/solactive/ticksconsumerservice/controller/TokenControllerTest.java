package com.solactive.ticksconsumerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solactive.ticksconsumerservice.request.AuthenticationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TokenControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("admin", "admin123");
        this.mockMvc.perform(
                        post("/generateToken")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(authenticationRequest)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("jwt")));
    }

    @Test
    public void shouldReturnErrorMessage() throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("admin", "admin1234");
        this.mockMvc.perform(
                        post("/generateToken")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(authenticationRequest)))
                .andDo(print()).andExpect(status().is5xxServerError());
    }
}
