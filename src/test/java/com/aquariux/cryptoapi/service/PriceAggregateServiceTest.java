package com.aquariux.cryptoapi.service;

import com.aquariux.cryptoapi.dto.BinanceTickerResponseDTO;
import com.aquariux.cryptoapi.dto.CommonTicker;
import com.aquariux.cryptoapi.dto.HoubiDataDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class PriceAggregateServiceTest {
    @Autowired
    private PriceAggregateService paService;

    @Test
    public void printListBinanceTicker(){


        CommonTicker commonTickerB = CommonTicker.builder()
                .symbol("ETHUSDT")
                .bidPrice(BigDecimal.valueOf(123.2))
                .bidQty(BigDecimal.valueOf(1))
                .askPrice(BigDecimal.valueOf(456))
                .askQty(BigDecimal.valueOf(2))
                .source("Binance")
                .build();
        CommonTicker commonTickerH = CommonTicker.builder()
                .symbol("ETHUSDT")
                .bidPrice(BigDecimal.valueOf(123))
                .bidQty(BigDecimal.valueOf(2))
                .askPrice(BigDecimal.valueOf(455))
                .askQty(BigDecimal.valueOf(1))
                .source("Houbi")
                .build();

        List<CommonTicker> commonTickers = new ArrayList<>();
        commonTickers.add(commonTickerB);
        commonTickers.add(commonTickerH);

        paService.createPriceAgg(commonTickers);

    }

}
