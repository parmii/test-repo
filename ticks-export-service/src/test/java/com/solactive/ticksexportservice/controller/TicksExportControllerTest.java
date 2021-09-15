package com.solactive.ticksexportservice.controller;

import com.solactive.ticksexportservice.model.TicksData;
import com.solactive.ticksexportservice.util.TicksDataUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicksExportController.class)
@AutoConfigureMockMvc
public class TicksExportControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicksDataUtil ticksDataUtil;

    @Test
    public void testExportTicksByRIC() throws Exception {
        TicksData ticksData = new TicksData();
        ticksData.setClosedPriceAvailable(true);
        when(ticksDataUtil.fetchTicksByRIC("ABC")).thenReturn(Optional.ofNullable(ticksData));
        this.mockMvc.perform(
                        get("/export/ABC").contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testExportTicksByRICError() throws Exception {
        TicksData ticksData = new TicksData();
        when(ticksDataUtil.fetchTicksByRIC("ABC")).thenReturn(Optional.ofNullable(ticksData));
        this.mockMvc.perform(
                        get("/export/ABC").contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().is4xxClientError());
    }
}
