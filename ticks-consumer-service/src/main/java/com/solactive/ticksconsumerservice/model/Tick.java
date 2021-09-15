package com.solactive.ticksconsumerservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.solactive.ticksconsumerservice.constants.ErrorConstants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tick {

    @NotNull(message = TICK_TIMESTAMP_VALIDATION_MESSAGE)
    @JsonProperty("TIMESTAMP")
    private Long timestamp;

    @NotNull(message = TICK_PRICE_VALIDATION_MESSAGE)
    @JsonProperty("PRICE")
    private Double price;

    @JsonProperty("CLOSE_PRICE")
    private Double closePrice;

    @NotBlank(message = TICK_CURRENCY_VALIDATION_MESSAGE)
    @JsonProperty("CURRENCY")
    private String currency;

    @NotBlank(message = TICK_RIC_VALIDATION_MESSAGE)
    @JsonProperty("RIC")
    private String ric;
}
