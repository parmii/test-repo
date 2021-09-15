package com.solactive.ticksexportservice.service;

import com.solactive.ticksexportservice.model.Tick;
import com.solactive.ticksexportservice.util.TicksDataUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TicksCommandServiceTest {

    TicksDataUtil ticksDataUtil = mock(TicksDataUtil.class);

    @Autowired
    private TicksCommandServiceImpl ticksCommandService;

    @Test
    public void testSave() {
        ticksCommandService = new TicksCommandServiceImpl(ticksDataUtil);
        Tick tick = new Tick();
        doNothing().when(ticksDataUtil).save(tick);
        ticksCommandService.save(tick);
        verify(ticksDataUtil, times(1)).save(tick);
    }
}
