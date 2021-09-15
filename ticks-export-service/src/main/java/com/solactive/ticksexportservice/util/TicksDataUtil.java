package com.solactive.ticksexportservice.util;

import com.solactive.ticksexportservice.model.Tick;
import com.solactive.ticksexportservice.model.TicksData;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TicksDataUtil {

    private static final Map<String, TicksData> ricToTickMap = new HashMap<>();

    /**
     * This method will add tick into the ricToTickMap and RIC will be used as a key
     *
     * @param tick
     */
    public void save(Tick tick) {
        List<Tick> ticks = new ArrayList<>();
        ticks.add(tick);
        if (ricToTickMap.containsKey(tick.getRic())) {
            ticks.addAll(ricToTickMap.get(tick.getRic()).getTicks());
        }
        TicksData ticksData = TicksData.builder()
                .ticks(ticks)
                .isClosedPriceAvailable(tick.getClosePrice() != null)
                .build();
        ricToTickMap.put(tick.getRic(), ticksData);
    }

    /**
     * This method will return Optional of TicksData based on RIC
     *
     * @param ric
     * @return Optional<TicksData>
     */
    public Optional<TicksData> fetchTicksByRIC(String ric) {
        return Optional.ofNullable(ricToTickMap.get(ric));
    }


}
