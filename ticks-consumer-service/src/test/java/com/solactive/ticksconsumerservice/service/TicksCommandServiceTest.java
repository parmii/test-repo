package com.solactive.ticksconsumerservice.service;

import com.solactive.ticksconsumerservice.model.Tick;
import com.solactive.ticksconsumerservice.util.TicksDataUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TicksCommandServiceTest {

    TicksDataUtil ticksDataUtil = mock(TicksDataUtil.class);

    @Autowired
    private TicksCommandServiceImpl ticksCommandService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void testSave() {
        ticksCommandService = new TicksCommandServiceImpl(ticksDataUtil, restTemplate);
        Tick tick = new Tick();
        doNothing().when(ticksDataUtil).save(tick);
        ticksCommandService.save(tick);
        verify(ticksDataUtil, times(1)).save(tick);
    }
}
