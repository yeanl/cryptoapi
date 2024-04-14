package com.aquariux.cryptoapi.repository;

import com.aquariux.cryptoapi.entity.CryptoType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CryptoTypeRepository extends JpaRepository<CryptoType, Long>  {

    CryptoType findBySymbolAndWallet_id(String symbol, long id);

    List<CryptoType> findByWallet_id(long id);

}
