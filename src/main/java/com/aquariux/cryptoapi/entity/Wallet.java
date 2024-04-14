package com.aquariux.cryptoapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name="wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigDecimal balance;

    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)//,optional = false)
    @JoinColumn(name="customer_id", referencedColumnName = "id")
    private Customer customer;


    @OneToMany(mappedBy="wallet",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CryptoType> cryptoTypes;

    @OneToMany(mappedBy="wallet",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trade> trades;


    public void addCrytoType(CryptoType cryptoType){
        if (cryptoType !=null){
            if (cryptoTypes == null){
                cryptoTypes = new ArrayList<>();
            }
            cryptoType.setWallet(this);
            cryptoTypes.add(cryptoType);
        }
    }

    public void addTrade(Trade trade){
        if (trade!=null){
            if (trades == null){
                trades = new ArrayList<>();
            }
            trade.setWallet(this);
            trades.add(trade);
        }
    }


}
