package com.solactive.ticksexportservice.service;

import com.solactive.ticksexportservice.model.Tick;
import com.solactive.ticksexportservice.util.TicksDataUtil;
import org.springframework.stereotype.Service;

@Service
public class TicksCommandServiceImpl implements TicksCommandService {

    private final TicksDataUtil ticksDataUtil;

    public TicksCommandServiceImpl(TicksDataUtil ticksDataUtil) {
        this.ticksDataUtil = ticksDataUtil;
    }

    /**
     * This method will save new tick into the static ricToTickMap inside ticksDataUtil
     *
     * @param tick Object which needs to be saved
     */
    @Override
    public void save(Tick tick) {
        ticksDataUtil.save(tick);
    }
}
