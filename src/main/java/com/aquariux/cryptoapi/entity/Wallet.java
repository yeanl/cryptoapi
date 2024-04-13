package com.aquariux.cryptoapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    private List<CrytoType> crytoTypes;


}
