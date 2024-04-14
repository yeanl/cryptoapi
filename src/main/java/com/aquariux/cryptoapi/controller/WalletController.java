package com.aquariux.cryptoapi.controller;

import com.aquariux.cryptoapi.dto.WalletResponse;
import com.aquariux.cryptoapi.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wallet/v1")
public class WalletController {

    private final WalletService service;

    @GetMapping("/mywallet/{walletId}")
    public WalletResponse getWalletDetail(@PathVariable long walletId){
        return service.getWalletDetail(walletId);
    }


}
