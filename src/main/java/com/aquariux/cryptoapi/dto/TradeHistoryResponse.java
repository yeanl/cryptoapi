package com.aquariux.cryptoapi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TradeHistoryResponse {

    private String symbol;
    private LocalDateTime dateTime;
    private boolean hasSell;
    private boolean hasBuy;
    private BigDecimal quantity;
    private BigDecimal amount;

}
