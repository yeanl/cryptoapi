package com.aquariux.cryptoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoubiTickerResponseDTO {

    private List<HoubiDataDTO> data;
    private String status;
    //private Integer ts;






}
