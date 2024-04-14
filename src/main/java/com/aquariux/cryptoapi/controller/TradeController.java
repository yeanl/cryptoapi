package com.aquariux.cryptoapi.controller;

import com.aquariux.cryptoapi.dto.TradeHistoryResponse;
import com.aquariux.cryptoapi.dto.TradeRequest;
import com.aquariux.cryptoapi.dto.TradeResponse;
import com.aquariux.cryptoapi.service.TradeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trade/v1")
public class TradeController {

    private final TradeService service;

    @PostMapping("/deal")
    public ResponseEntity<TradeResponse> createTrade(@RequestBody TradeRequest tradeRequest, HttpServletRequest request){
        return ResponseEntity.ok(service.createTrade(tradeRequest, request));
    }

    @GetMapping("/history/{walletId}")
    public List<TradeHistoryResponse> getTradeHistory(@PathVariable long walletId){
        return service.getTradeHistory(walletId);
    }


}
