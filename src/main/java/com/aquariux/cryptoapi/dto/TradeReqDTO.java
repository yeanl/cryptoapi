package com.aquariux.cryptoapi.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TradeReqDTO {

    private String symbol;
    private long walletId;
    private boolean hasSell;
    private boolean hasBuy;
    private BigDecimal quantity;
    private BigDecimal amount;
}
