package com.solactive.ticksconsumerservice.service;

import com.solactive.ticksconsumerservice.dto.TicksDataDto;
import com.solactive.ticksconsumerservice.exception.TickNotFoundException;
import com.solactive.ticksconsumerservice.model.TicksData;
import com.solactive.ticksconsumerservice.util.TicksDataUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.solactive.ticksconsumerservice.constants.ErrorConstants.TICK_NOT_FOUND;

@Service
public class TicksQueryServiceImpl implements TicksQueryService {

    private final TicksDataUtil tickDataUtil;

    private final ModelMapper modelMapper;

    public TicksQueryServiceImpl(TicksDataUtil tickDataUtil, ModelMapper modelMapper) {
        this.tickDataUtil = tickDataUtil;
        this.modelMapper = modelMapper;
    }

    /**
     * This method fetches the data from static ricToTickMap by RIC
     * @param ric
     * @return TicksDataDto which contains the Tick and closed_price information
     */
    @Override
    public TicksDataDto fetchTicksDataByRIC(String ric) {
        Optional<TicksData> ticksData = tickDataUtil.fetchTicksByRIC(ric);
        if (ticksData.isEmpty())
            throw new TickNotFoundException(TICK_NOT_FOUND);
        return this.convertToTickDataDto(ticksData.get());
    }

    /**
     * This is used to map TicksData Object to TicksDataDto
     * @param ticksData
     * @return DTO Object of TicksData
     */
    private TicksDataDto convertToTickDataDto(TicksData ticksData) {
        return modelMapper.map(ticksData, TicksDataDto.class);
    }
}
