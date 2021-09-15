package com.solactive.ticksconsumerservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TickDataDto {

    @JsonProperty("TIMESTAMP")
    private Long timestamp;
    @JsonProperty("PRICE")
    private Double price;
    @JsonProperty("CLOSE_PRICE")
    private Double closePrice;
    @JsonProperty("CURRENCY")
    private String currency;
    @JsonProperty("RIC")
    private String ric;
}
