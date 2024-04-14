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
@Table(name="trade")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String symbol;
    private LocalDateTime dateTime;
    private boolean hasSell;
    private boolean hasBuy;
    private BigDecimal quantity;
    private BigDecimal amount;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private Wallet wallet;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "price_agg_id", referencedColumnName = "id")
    private PriceAgg priceAgg;






}
