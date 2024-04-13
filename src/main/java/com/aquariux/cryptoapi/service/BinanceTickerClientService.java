package com.aquariux.cryptoapi.service;

import com.aquariux.cryptoapi.dto.BinanceTickerResponseDTO;
import com.aquariux.cryptoapi.dto.CommonTicker;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BinanceTickerClientService {

    private final RestClient restClient;
    //private List<BinanceTickerResponseDTO> body;


    public BinanceTickerClientService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.binance.com/api/v3/ticker/bookTicker")
                .build();
    }

    public List<BinanceTickerResponseDTO> getBinancePrice(){
        return restClient
                .get()
                .retrieve()
                .body(new ParameterizedTypeReference<List<BinanceTickerResponseDTO>>() {});
    }






}
