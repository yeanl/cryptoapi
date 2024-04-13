package com.aquariux.cryptoapi.controller;

import com.aquariux.cryptoapi.dto.PriceAggDTO;
import com.aquariux.cryptoapi.service.PriceAggregateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trade/v1")
public class TradeController {

    private final PriceAggregateService paService;

    @GetMapping("/price/latest/{symbol}")
    public PriceAggDTO getLatestBestPriceAgg(@PathVariable String symbol){
        return paService.getLatestBestPriceAgg(symbol);

    }

}
