package com.aquariux.cryptoapi.service;

import com.aquariux.cryptoapi.dto.HoubiDataDTO;
import com.aquariux.cryptoapi.dto.HoubiTickerResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class HoubiTickerClientServiceTest {

    @Autowired
    private HoubiTickerClientService htcService;

    @Test
    public void printListHoubiTicker(){

       // List<HoubiDataDTO> data = htcService.getHoubiPrice().getData();

        List<HoubiDataDTO> collect = htcService.getHoubiPrice().getData().stream()
                .filter(a -> a.getSymbol().equalsIgnoreCase("ethusdt") || a.getSymbol().equalsIgnoreCase("BTCUSDT"))
                .collect(Collectors.toList());
        //.forEach(System.out::println);
        System.out.println(htcService.getHoubiPrice().getStatus());
        System.out.println(collect);

    }

}
