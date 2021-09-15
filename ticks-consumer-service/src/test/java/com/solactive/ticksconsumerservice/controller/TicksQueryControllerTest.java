package com.solactive.ticksconsumerservice.controller;

import com.solactive.ticksconsumerservice.dto.TickDataDto;
import com.solactive.ticksconsumerservice.dto.TicksDataDto;
import com.solactive.ticksconsumerservice.service.TicksQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TicksQueryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicksQueryService ticksQueryService;

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void testTicksDataFetch() throws Exception {
        TickDataDto tick = TickDataDto.builder().timestamp(1631550723l).closePrice(1.0).price(1.0).currency("EUR").ric("ABC").build();
        List<TickDataDto> tickList = List.of(tick);
        TicksDataDto ticksDataDto=TicksDataDto.builder().isClosedPriceAvailable(true).ticks(tickList).build();
        when(ticksQueryService.fetchTicksDataByRIC(anyString())).thenReturn(ticksDataDto);
        this.mockMvc.perform(
                        get("/ticks/ABC").contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("ticks")));
    }

}
