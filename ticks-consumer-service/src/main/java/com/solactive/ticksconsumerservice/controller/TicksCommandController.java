package com.solactive.ticksconsumerservice.controller;

import com.solactive.ticksconsumerservice.model.Tick;
import com.solactive.ticksconsumerservice.service.TicksCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.solactive.ticksconsumerservice.constants.Constants.*;

@RestController
public class TicksCommandController {

    private final TicksCommandService ticksCommandService;

    public TicksCommandController(TicksCommandService ticksCommandService) {
        this.ticksCommandService = ticksCommandService;
    }

    /**
     * This method will add new ticks based on the request body Tick
     * Closed price is Optional
     * @param tick Tick Object
     */
    @Operation(summary = TICKS_API_SUMMARY, description = TICKS_API_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = MSG_TICK_CREATED_SUCCESSFULLY)})
    @PostMapping(value = "/ticks", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addTicks(@Valid @RequestBody Tick tick) {
        ticksCommandService.save(tick);
    }
}
