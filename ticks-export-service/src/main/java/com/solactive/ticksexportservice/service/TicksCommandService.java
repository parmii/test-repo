package com.solactive.ticksexportservice.service;

import com.solactive.ticksexportservice.model.Tick;

public interface TicksCommandService {
    void save(Tick tick);
}
