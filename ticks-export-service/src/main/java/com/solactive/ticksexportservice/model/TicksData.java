package com.solactive.ticksexportservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TicksData {
    private boolean isClosedPriceAvailable;
    private List<Tick> ticks = new ArrayList<>();
}
