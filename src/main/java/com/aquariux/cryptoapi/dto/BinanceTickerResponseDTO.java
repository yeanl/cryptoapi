package com.aquariux.cryptoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BinanceTickerResponseDTO {

    private String symbol;
    private String bidPrice;
    private String bidQty;
    private String askPrice;
    private String askQty;



}
