package com.solactive.ticksconsumerservice.service;

import com.solactive.ticksconsumerservice.model.Tick;
import com.solactive.ticksconsumerservice.util.TicksDataUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TicksCommandServiceImpl implements TicksCommandService {

    private final TicksDataUtil ticksDataUtil;

    private final RestTemplate restTemplate;

    @Value("${export.service.uri}")
    private String exportURI;

    public TicksCommandServiceImpl(TicksDataUtil ticksDataUtil, RestTemplate restTemplate) {
        this.ticksDataUtil = ticksDataUtil;
        this.restTemplate = restTemplate;
    }

    /**
     * This method will save new tick into the static ricToTickMap inside ticksDataUtil
     * @param tick Object which needs to be saved
     */
    @Override
    public void save(Tick tick) {
        ticksDataUtil.save(tick);
        updateExportService(tick);
    }

    /**
     * Since we are using in memory solution, we need to pass the data to export service
     * so that it can be used for the export functionality
     * @param tick
     */
    private void updateExportService(Tick tick) {
        String uri = exportURI+"/ticks";
        restTemplate.postForEntity(uri, tick, Tick.class);
    }
}
