package com.aquariux.cryptoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * to set all the retrieve data as same format
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonTicker {
    private String symbol;
    private BigDecimal bidPrice;
    private BigDecimal bidQty;
    private BigDecimal askPrice;
    private BigDecimal askQty;
    private String source;
}
