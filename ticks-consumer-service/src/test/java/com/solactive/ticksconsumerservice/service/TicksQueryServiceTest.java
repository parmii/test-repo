package com.solactive.ticksconsumerservice.service;

import com.solactive.ticksconsumerservice.exception.TickNotFoundException;
import com.solactive.ticksconsumerservice.model.TicksData;
import com.solactive.ticksconsumerservice.util.TicksDataUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TicksQueryServiceTest {

    @Autowired
    private TicksQueryServiceImpl ticksQueryService;

    @MockBean
    private TicksDataUtil ticksDataUtil;

    @Test
    public void testSave() {
        when(ticksDataUtil.fetchTicksByRIC(anyString())).thenReturn(Optional.ofNullable(new TicksData()));
        assertThat(ticksQueryService.fetchTicksDataByRIC(anyString())).isNotNull();
    }

    @Test
    public void testSaveError() {
        Assertions.assertThrows(TickNotFoundException.class, () -> {
            ticksQueryService.fetchTicksDataByRIC(anyString());
        });
    }
}
