package com.aquariux.cryptoapi.service;

import com.aquariux.cryptoapi.dto.TradeReqDTO;
import com.aquariux.cryptoapi.dto.TradeHistoryResponse;
import com.aquariux.cryptoapi.dto.TradeRequest;
import com.aquariux.cryptoapi.dto.TradeResponse;
import com.aquariux.cryptoapi.entity.CryptoType;
import com.aquariux.cryptoapi.entity.PriceAgg;
import com.aquariux.cryptoapi.entity.Trade;
import com.aquariux.cryptoapi.entity.Wallet;
import com.aquariux.cryptoapi.repository.CryptoTypeRepository;
import com.aquariux.cryptoapi.repository.PriceAggregateRepository;
import com.aquariux.cryptoapi.repository.TradeRepository;
import com.aquariux.cryptoapi.repository.WalletRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final TradeRepository tradeRepository;
    private final PriceAggregateRepository priceRepository;
    private final WalletRepository walletRepository;
    private final CryptoTypeRepository cryptoTypeRepository;
    private final ModelMapper mapper;

    Logger logger = LoggerFactory.getLogger(TradeService.class);


    public TradeResponse createTrade(TradeRequest tradeRequest, HttpServletRequest request) {
        //1.check the timing exceed 3 min
        //2. isBuy or isSell, calculate
        //
        PriceAgg priceAggReq = priceRepository.findById(tradeRequest.getPriceAggId()).orElseThrow();;
        if (priceAggReq!=null){
            if (checkPlaceOrderTimeWithin(priceAggReq.getDateTime())){

                Trade tradeReq =mapToEntity(tradeRequest.getTrade());
                Wallet walletReq = walletRepository.findById(tradeRequest.getWalletId()).orElseThrow();
                if (tradeReq.isHasBuy()){
                    //0 if x and y are equal, 1 if x is greater than y and -1 if x is smaller than y
                    if (walletReq.getBalance().compareTo(tradeReq.getAmount()) !=-1 &&
                            (tradeReq.getAmount().compareTo(priceAggReq.getAskPrice()) !=-1)){
                        BigDecimal quantity = tradeReq.getAmount().divide(priceAggReq.getAskPrice(),4,RoundingMode.CEILING);
                        logger.debug("quantity:" + quantity);
                        logger.debug("trade amount:"+tradeReq.getAmount());
                        logger.debug("ask price:" + priceAggReq.getAskPrice());
                        walletReq.setBalance(walletReq.getBalance().subtract(tradeReq.getAmount()));
                        Trade tradeResult = Trade.builder()
                                .symbol(priceAggReq.getSymbol())
                                .dateTime(LocalDateTime.now())
                                .wallet(walletReq)
                                .priceAgg(priceAggReq)
                                .hasBuy(true)
                                .quantity(quantity)
                                .amount(tradeReq.getAmount())
                                .build();
                        tradeRepository.save(tradeResult);

                        CryptoType cryptoTypeReq = cryptoTypeRepository.findBySymbolAndWallet_id(priceAggReq.getSymbol(),walletReq.getId());
                        if (cryptoTypeReq!=null){
                            BigDecimal quanAdd = cryptoTypeReq.getQuantity().add(quantity);
                            cryptoTypeReq.setQuantity(quanAdd);
                            cryptoTypeRepository.save(cryptoTypeReq);
                        } else{
                            CryptoType cryptoType = CryptoType.builder()
                                    .quantity(quantity)
                                    .symbol(priceAggReq.getSymbol())
                                    .wallet(walletReq)
                                    .build();
                            cryptoTypeRepository.save(cryptoType);
                        }
                    } else{
                        return TradeResponse.builder().result("Insufficient credit.").build();
                    }

                } else if(tradeReq.isHasSell()){
                    CryptoType cryptoTypeReq = cryptoTypeRepository.findBySymbolAndWallet_id(priceAggReq.getSymbol(), walletReq.getId());
                    if (cryptoTypeReq != null){
                        if(cryptoTypeReq.getQuantity().compareTo(tradeReq.getQuantity()) != -1){
                            BigDecimal amount = tradeReq.getQuantity().multiply(priceAggReq.getBidPrice());
                            BigDecimal quantity = cryptoTypeReq.getQuantity().subtract(tradeReq.getQuantity());
                            cryptoTypeReq.setQuantity(quantity);
                            System.out.println(cryptoTypeReq.getQuantity());
                            walletReq.setBalance(walletReq.getBalance().add(amount));
                            System.out.println(walletReq.getBalance());
                            Trade tradeResult = Trade.builder()
                                    .symbol(priceAggReq.getSymbol())
                                    .dateTime(LocalDateTime.now())
                                    .wallet(walletReq)
                                    .priceAgg(priceAggReq)
                                    .hasSell(true)
                                    .quantity(tradeReq.getQuantity())
                                    .amount(amount)
                                    .build();
                            tradeRepository.save(tradeResult);
                            cryptoTypeRepository.save(cryptoTypeReq);

                        } else{
                            return TradeResponse.builder().result("Insufficient quantity.").build();
                        }

                    } else {
                        return TradeResponse.builder().result("No record found.").build();
                    }

                }


            } else{
                return TradeResponse.builder().result("Failed. Transaction time expired(> 3 mins)." +
                        "Please perform new transaction with latest price").build();
            }


        }

        return TradeResponse.builder().result("Success").build();
    }

    //Check if the transaction triggered within 3 min
    private boolean checkPlaceOrderTimeWithin(LocalDateTime dateTimeRecord) {
        LocalDateTime tsWinthin = dateTimeRecord.plusMinutes(3);
        LocalDateTime currNow = LocalDateTime.now(ZoneId.of("Asia/Singapore"));
        if(currNow.isBefore(tsWinthin)){
            return true;
        }
        return false;

    }

    private Trade mapToEntity(TradeReqDTO tradeReqDTO) {
        Trade trade = mapper.map(tradeReqDTO, Trade.class);
        return trade;
    }


    public List<TradeHistoryResponse> getTradeHistory(long walletId) {
        List<Trade> historyList = tradeRepository.findByWallet_id(walletId);
        List<TradeHistoryResponse> tradeHistoryResponses = historyList.stream()
                .map(trade -> mapper.map(trade, TradeHistoryResponse.class))
                .collect(Collectors.toList());
        return tradeHistoryResponses;
    }
}


/**
 * BUY
 * {
 *     "walletId": 1,
 *     "priceAggId": 3,
 *     "trade": {
 *         "symbol": "BTCUSDT",
 *         "hasBuy": true,
 *         "amount": 40000
 *     }
 * }
 *
 * -----
 * SELL
 * {
 *     "walletId": 1,
 *     "priceAggId": 1,
 *     "trade": {
 *         "symbol": "BTCUSDT",
 *         "hasSell": true,
 *         "quantity": 10
 *     }
 * }
 *
 *
 */
