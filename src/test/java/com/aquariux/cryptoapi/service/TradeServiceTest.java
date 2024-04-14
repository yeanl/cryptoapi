package com.aquariux.cryptoapi.service;

import com.aquariux.cryptoapi.entity.PriceAgg;
import com.aquariux.cryptoapi.entity.Trade;
import com.aquariux.cryptoapi.entity.Wallet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.*;

@SpringBootTest
public class TradeServiceTest {
    @Autowired
    private TradeService tradeService;

    @Test
    public void checkWith3Min(){

        LocalDateTime tsWinthin = LocalDateTime.of(2024, Month.APRIL, 14, 11,28);
        LocalDateTime todayNow = LocalDateTime.now(ZoneId.of("Asia/Singapore"));
        System.out.println(todayNow.isBefore(tsWinthin.plusMinutes(3)));

        LocalTime curTime  = LocalTime.now(ZoneId.of("Asia/Singapore"));
        LocalTime timeSample = LocalTime.of(18,0);
        System.out.println(timeSample.isBefore(curTime));
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Singapore"));
        LocalDate dateSample = LocalDate.of(2024, Month.FEBRUARY, 22);//LocalDate.now(ZoneId.of("Asia/Singapore"));
        System.out.println(today+" "+dateSample.isBefore(today));
    }

    @Test
    public void checkBigDecimalValue(){
        Trade trade = new Trade();
        trade.setAmount(BigDecimal.valueOf(30000));
        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(5001200));
        PriceAgg priceAgg = new PriceAgg();
        priceAgg.setAskPrice(BigDecimal.valueOf(67890));

        System.out.println(wallet.getBalance().compareTo(trade.getAmount()));
        System.out.println(trade.getAmount().compareTo(priceAgg.getAskPrice()));
        if(wallet.getBalance().compareTo(trade.getAmount()) !=-1
                && trade.getAmount().compareTo(priceAgg.getAskPrice()) !=-1
        ){
            System.out.println("test");
        } else{
            System.out.println("elll");
        }

    }


}
