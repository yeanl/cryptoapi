package com.aquariux.cryptoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CryptoTypeDTO {

    private long id;

    private String symbol;
    private BigDecimal quantity;

}
