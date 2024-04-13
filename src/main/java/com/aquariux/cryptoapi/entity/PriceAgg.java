package com.aquariux.cryptoapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name="price_agg")
public class PriceAgg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String bidResource;
    private String askResource;
    private LocalDateTime dateTime;
    private String symbol;
    private BigDecimal bidPrice;
    private BigDecimal askPrice;



}
