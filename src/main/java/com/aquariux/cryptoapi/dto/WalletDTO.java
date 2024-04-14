package com.aquariux.cryptoapi.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletDTO {

    private long id;
    private BigDecimal balance;

}
