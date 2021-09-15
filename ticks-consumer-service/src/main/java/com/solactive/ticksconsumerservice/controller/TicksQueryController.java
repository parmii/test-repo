package com.solactive.ticksconsumerservice.controller;

import com.solactive.ticksconsumerservice.dto.TickDataDto;
import com.solactive.ticksconsumerservice.dto.TicksDataDto;
import com.solactive.ticksconsumerservice.service.TicksQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.solactive.ticksconsumerservice.constants.Constants.*;
import static com.solactive.ticksconsumerservice.constants.ErrorConstants.MSG_ACCESS_DENIED;

@RestController
public class TicksQueryController {
    private final TicksQueryService ticksQueryService;

    public TicksQueryController(TicksQueryService ticksQueryService) {
        this.ticksQueryService = ticksQueryService;
    }

    /**
     * This method is protected by Authority ADMIN, user must have ADMIN role to access the API
     * A JWT should be generated using /generate API and passed as a Bearer Token
     * @param ric RIC name
     * @return Returns TicksDataDto, which contains all the tick values for a given RIC
     */
    @Operation(summary = TICKS_LOOKUP_API_SUMMARY,
            description = TICKS_LOOKUP_API_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = MSG_TICK_FETCHED_SUCCESSFULLY,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TickDataDto.class))}),
            @ApiResponse(responseCode = "403", description = MSG_ACCESS_DENIED)})
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/ticks/{ric}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TicksDataDto fetchTicksByRIC(@PathVariable String ric) {
        return ticksQueryService.fetchTicksDataByRIC(ric);
    }
}