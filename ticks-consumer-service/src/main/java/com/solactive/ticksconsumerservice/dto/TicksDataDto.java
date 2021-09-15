package com.solactive.ticksconsumerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicksDataDto {
    private boolean isClosedPriceAvailable;
    private List<TickDataDto> ticks = new ArrayList<>();
}
