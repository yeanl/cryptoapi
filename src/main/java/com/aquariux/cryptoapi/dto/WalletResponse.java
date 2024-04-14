package com.aquariux.cryptoapi.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletResponse {

    //private Customer customer;
    private WalletDTO wallet;
    private List<CryptoTypeDTO> cryptoType;
}
