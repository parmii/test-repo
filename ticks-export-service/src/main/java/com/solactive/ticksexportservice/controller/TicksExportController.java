package com.solactive.ticksexportservice.controller;


import com.solactive.ticksexportservice.exception.ClosedPriceNotFoundException;
import com.solactive.ticksexportservice.exception.TickNotFoundException;
import com.solactive.ticksexportservice.model.TicksData;
import com.solactive.ticksexportservice.util.TicksDataUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import static com.solactive.ticksexportservice.constants.Constants.*;
import static com.solactive.ticksexportservice.constants.ErrorConstants.*;

@Slf4j
@RestController
public class TicksExportController {

    private final TicksDataUtil ticksDataUtil;


    public TicksExportController(TicksDataUtil ticksDataUtil) {
        this.ticksDataUtil = ticksDataUtil;
    }

    /**
     * This method will export the CSV based on the RIC, If closed price for the given RIC is available
     *
     * @param ric      String RIC value should be provided
     * @param response CSV file will be generated as a response
     */
    @Operation(summary = EXPORT_API_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = EXPORT_API_SUCCESS_DESCRIPTION)})
    @GetMapping(value = "/export/{ric}", produces = MEDIA_TYPE_TEXT_CSV)
    public void exportTicksByRIC(@PathVariable String ric, HttpServletResponse response) {
        Optional<TicksData> ticksData = ticksDataUtil.fetchTicksByRIC(ric);
        if (ticksData.isEmpty())
            throw new TickNotFoundException(TICK_NOT_FOUND);
        if (!Objects.requireNonNull(ticksData.get()).isClosedPriceAvailable())
            throw new ClosedPriceNotFoundException(CLOSE_PRICE_NOT_FOUND_MESSAGE);
        generateCSV(response, ticksData, ric);
    }

    /**
     * This is a private method which is responsible for generating the csv file
     *
     * @param response  CSV file
     * @param ticksData Data received from consumer service
     * @param ric       RIC received from the API URL
     */
    private void generateCSV(HttpServletResponse response, Optional<TicksData> ticksData, String ric) {
        String fileName = "tick-" + ric + "-" + new Date().getTime() + ".csv ";
        response.setContentType(MEDIA_TYPE_TEXT_CSV);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        try (OutputStream outputStream = response.getOutputStream()) {
            StringBuilder sb = new StringBuilder();
            sb.append(CSV_COLUMNS).append("\n");
            Objects.requireNonNull(ticksData.get()).getTicks().forEach(tick -> {
                Date date = new Date(tick.getTimestamp() * 1000);
                sb.append(date).append(",").append(tick.getPrice()).append(",").append(tick.getClosePrice()).append(",").append(tick.getCurrency()).append(",").append(tick.getRic()).append("\n");
            });
            outputStream.write(sb.toString().getBytes());
            outputStream.flush();
        } catch (Exception e) {
            log.error(EXPORT_TO_CSV_FAILED, e);
        }
    }
}