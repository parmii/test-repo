package com.solactive.ticksconsumerservice.service;

import com.solactive.ticksconsumerservice.dto.TicksDataDto;

public interface TicksQueryService {
    TicksDataDto fetchTicksDataByRIC(String ric);
}
