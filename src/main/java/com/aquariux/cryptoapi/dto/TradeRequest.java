package com.aquariux.cryptoapi.dto;

import lombok.Data;

@Data
public class TradeRequest {

    private long walletId;
    private long priceAggId;
    private TradeReqDTO trade;
}
