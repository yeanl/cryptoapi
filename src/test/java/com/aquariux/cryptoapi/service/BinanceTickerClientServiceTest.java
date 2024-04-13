package com.aquariux.cryptoapi.service;

import com.aquariux.cryptoapi.dto.BinanceTickerResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class BinanceTickerClientServiceTest {

    @Autowired
    private BinanceTickerClientService btcService;

    @Test
    public void printListBinanceTicker(){

        List<BinanceTickerResponseDTO> collect = btcService.getBinancePrice().stream()
                .filter(a -> a.getSymbol().equalsIgnoreCase("ETHUSDT") || a.getSymbol().equalsIgnoreCase("BTCUSDT"))
                .collect(Collectors.toList());
        //.forEach(System.out::println);
        System.out.println(collect);

    }

}
