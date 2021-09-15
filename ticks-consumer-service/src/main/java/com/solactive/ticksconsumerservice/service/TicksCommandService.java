package com.solactive.ticksconsumerservice.service;

import com.solactive.ticksconsumerservice.model.Tick;

public interface TicksCommandService {
    void save(Tick tick);
}
