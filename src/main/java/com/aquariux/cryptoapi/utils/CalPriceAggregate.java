package com.aquariux.cryptoapi.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aquariux.cryptoapi.dto.BinanceTickerResponseDTO;
import com.aquariux.cryptoapi.dto.CommonTicker;
import com.aquariux.cryptoapi.dto.HoubiDataDTO;
import com.aquariux.cryptoapi.service.BinanceTickerClientService;
import com.aquariux.cryptoapi.service.HoubiTickerClientService;
import com.aquariux.cryptoapi.service.PriceAggregateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class CalPriceAggregate {

    private static final Logger log = LoggerFactory.getLogger(CalPriceAggregate.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private BinanceTickerClientService btcService;

    @Autowired
    private HoubiTickerClientService htcService;

    @Autowired
    private PriceAggregateService paService;


    private void combineDataForETHUSDT(){
        BinanceTickerResponseDTO binancePrice = btcService.getBinancePrice().stream()
                .filter(a -> a.getSymbol().equalsIgnoreCase("ETHUSDT"))
                .findFirst().get();

        HoubiDataDTO houbiPrice = htcService.getHoubiPrice().getData().stream()
                .filter(a -> a.getSymbol().equalsIgnoreCase("ETHUSDT") )
                .findFirst().get();

        List<CommonTicker> commonTickers = dataMap(binancePrice, houbiPrice);

        paService.createPriceAgg(commonTickers);

    }

    private void combineDataForBTCUSDT(){
        BinanceTickerResponseDTO binancePrice = btcService.getBinancePrice().stream()
                .filter(a -> a.getSymbol().equalsIgnoreCase("BTCUSDT"))
                .findFirst().get();

        HoubiDataDTO houbiPrice = htcService.getHoubiPrice().getData().stream()
                .filter(a -> a.getSymbol().equalsIgnoreCase("BTCUSDT") )
                .findFirst().get();

        List<CommonTicker> commonTickers = dataMap(binancePrice, houbiPrice);

        paService.createPriceAgg(commonTickers);

    }

    private List<CommonTicker> dataMap(BinanceTickerResponseDTO binancePrice, HoubiDataDTO houbiPrice){
        CommonTicker commonTickerB = CommonTicker.builder()
                .symbol(binancePrice.getSymbol())
                .bidPrice(new BigDecimal(binancePrice.getBidPrice()))
                .bidQty(new BigDecimal(binancePrice.getBidQty()))
                .askPrice(new BigDecimal(binancePrice.getAskPrice()))
                .askQty(new BigDecimal(binancePrice.getAskQty()))
                .source("Binance")
                .build();
        CommonTicker commonTickerH = CommonTicker.builder()
                .symbol(houbiPrice.getSymbol().toUpperCase())
                .bidPrice(houbiPrice.getBid())
                .bidQty(houbiPrice.getBidSize())
                .askPrice(houbiPrice.getAsk())
                .askQty(houbiPrice.getAskSize())
                .source("Houbi")
                .build();

        List<CommonTicker> commonTickers = new ArrayList<>();
        commonTickers.add(commonTickerB);
        commonTickers.add(commonTickerH);

        return commonTickers;
    }



    @Scheduled(fixedRate = 10000) //10000
    public void reportCurrentTime() {
        combineDataForETHUSDT();
        combineDataForBTCUSDT();
        log.info("The time is now {}", dateFormat.format(new Date()));
    }


}
