package com.aquariux.cryptoapi.repository;

import com.aquariux.cryptoapi.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Long> {

    List<Trade> findByWallet_id(long id);

}
