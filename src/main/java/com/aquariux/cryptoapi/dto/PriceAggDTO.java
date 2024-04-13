package com.aquariux.cryptoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceAggDTO {

    private long id;
    private String bidResource;
    private String askResource;
    private LocalDateTime dateTime;
    private String symbol;
    private BigDecimal bidPrice;
    private BigDecimal askPrice;
}
