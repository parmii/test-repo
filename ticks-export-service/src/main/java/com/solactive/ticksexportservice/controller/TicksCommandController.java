package com.solactive.ticksexportservice.controller;

import com.solactive.ticksexportservice.model.Tick;
import com.solactive.ticksexportservice.service.TicksCommandService;
import io.swagger.v3.oas.annotations.Hidden;
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

import static com.solactive.ticksexportservice.constants.Constants.MSG_TICK_CREATED_SUCCESSFULLY;
import static com.solactive.ticksexportservice.constants.Constants.TICKS_API_SUMMARY;

@Hidden
@RestController
public class TicksCommandController {

    private final TicksCommandService ticksCommandService;

    public TicksCommandController(TicksCommandService ticksCommandService) {
        this.ticksCommandService = ticksCommandService;
    }

    /**
     * This method will add new ticks based on the request body Tick
     * Closed price is Optional
     *
     * @param tick Tick Object
     */
    @Operation(summary = TICKS_API_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = MSG_TICK_CREATED_SUCCESSFULLY)})
    @Hidden
    @PostMapping(value = "/ticks", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addTicks(@Valid @RequestBody Tick tick) {
        ticksCommandService.save(tick);
    }
}
