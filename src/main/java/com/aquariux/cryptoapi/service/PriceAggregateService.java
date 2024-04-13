package com.aquariux.cryptoapi.service;

import com.aquariux.cryptoapi.dto.CommonTicker;
import com.aquariux.cryptoapi.dto.PriceAggDTO;
import com.aquariux.cryptoapi.entity.PriceAgg;
import com.aquariux.cryptoapi.repository.PriceAggregateRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceAggregateService {

    private final PriceAggregateRepository priceAggregateRepository;
    private final ModelMapper mapper;

    public void createPriceAgg(List<CommonTicker> commonTickers){

        //best bid
        CommonTicker bidPrice = commonTickers.stream()
                .sorted(Comparator.comparing(CommonTicker::getBidQty, Comparator.reverseOrder()))
                .findFirst()
                .get();
        //best ask
        CommonTicker askPrice = commonTickers.stream()
                .sorted(Comparator.comparing(CommonTicker::getAskQty, Comparator.reverseOrder()))
                .findFirst()
                .get();

        PriceAgg priceAgg = PriceAgg.builder()
                .symbol(bidPrice.getSymbol())
                .bidResource(bidPrice.getSource())
                .askResource(askPrice.getSource())
                .bidPrice(bidPrice.getBidPrice())
                .askPrice(askPrice.getAskPrice())
                .dateTime(LocalDateTime.now())
                .build();

        priceAggregateRepository.save(priceAgg);
    }


    public PriceAggDTO getLatestBestPriceAgg(String symbol) {
        PriceAgg priceAgg = priceAggregateRepository.getLatestBestPriceAgg(symbol);
        return mapToPriceAggDTO(priceAgg);
    }

    private PriceAggDTO mapToPriceAggDTO(PriceAgg priceAgg){
        PriceAggDTO priceAggDTO = mapper.map(priceAgg, PriceAggDTO.class);
        return priceAggDTO;
    }
}
