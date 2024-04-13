package com.aquariux.cryptoapi.utils;

import com.aquariux.cryptoapi.dto.BinanceTickerResponseDTO;
import com.aquariux.cryptoapi.dto.CommonTicker;
import com.aquariux.cryptoapi.dto.HoubiDataDTO;
import com.aquariux.cryptoapi.entity.PriceAgg;
import com.aquariux.cryptoapi.service.BinanceTickerClientService;
import com.aquariux.cryptoapi.service.HoubiTickerClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class CalPriceAggregateTest {

    @Autowired
    private HoubiTickerClientService htcService;

    @Autowired
    private BinanceTickerClientService btcService;

    @Test
    public void findBestPriceForETHUSDT(){
        BinanceTickerResponseDTO binancePrice = btcService.getBinancePrice().stream()
                .filter(a -> a.getSymbol().equalsIgnoreCase("ETHUSDT"))
                .findFirst().get();

        HoubiDataDTO houbiPrice = htcService.getHoubiPrice().getData().stream()
                .filter(a -> a.getSymbol().equalsIgnoreCase("ethusdt") )
                .findFirst().get();

        CommonTicker commonTickerB = CommonTicker.builder()
                .symbol(binancePrice.getSymbol())
                .bidPrice(new BigDecimal(binancePrice.getBidPrice()))
                .bidQty(new BigDecimal(binancePrice.getBidQty()))
                .askPrice(new BigDecimal(binancePrice.getAskPrice()))
                .askQty(new BigDecimal(binancePrice.getAskQty()))
                .source("Binance")
                .build();
        CommonTicker commonTickerH = CommonTicker.builder()
                .symbol(houbiPrice.getSymbol())
                .bidPrice(houbiPrice.getBid())
                .bidQty(houbiPrice.getBidSize())
                .askPrice(houbiPrice.getAsk())
                .askQty(houbiPrice.getAskSize())
                .source("Houbi")
                .build();

        List<CommonTicker> commonTickers = new ArrayList<>();
        commonTickers.add(commonTickerB);
        commonTickers.add(commonTickerH);

        //best bid
        CommonTicker bidPrice = commonTickers.stream()
                .sorted(Comparator.comparing(CommonTicker::getBidQty, Comparator.reverseOrder()))
                .findFirst()
                .get();
        //.collect(Collectors.toList());
        CommonTicker askPrice = commonTickers.stream()
                .sorted(Comparator.comparing(CommonTicker::getAskQty, Comparator.reverseOrder()))
                .findFirst()
                .get();

        commonTickers.stream().forEach(System.out::println);
        System.out.println(bidPrice);
        System.out.println(askPrice);

//


    }

}
