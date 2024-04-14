package com.aquariux.cryptoapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy="priceAgg",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trade> trades;

    public void addTrades(Trade trade){
        if (trade != null){
            if(trades == null){
                trades = new ArrayList<>();
            }
            trade.setPriceAgg(this);
            trades.add(trade);
        }
    }



}
