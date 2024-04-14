package com.aquariux.cryptoapi.service;


import com.aquariux.cryptoapi.dto.HoubiTickerResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


@Service
public class HoubiTickerClientService {

    private final RestClient restClient;

    public HoubiTickerClientService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.huobi.pro/market/tickers")
                .build();
    }

    public HoubiTickerResponseDTO getHoubiPrice(){
        return restClient
                .get()
                .retrieve()
                .body(HoubiTickerResponseDTO.class);
    }




}
