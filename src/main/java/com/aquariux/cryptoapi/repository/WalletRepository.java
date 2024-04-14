package com.aquariux.cryptoapi.repository;

import com.aquariux.cryptoapi.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
