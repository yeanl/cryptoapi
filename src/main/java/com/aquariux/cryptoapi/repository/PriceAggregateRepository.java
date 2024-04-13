package com.aquariux.cryptoapi.repository;

import com.aquariux.cryptoapi.entity.PriceAgg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceAggregateRepository extends JpaRepository<PriceAgg, Long>  {



}
