package com.aquariux.cryptoapi.repository;

import com.aquariux.cryptoapi.entity.PriceAgg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PriceAggregateRepository extends JpaRepository<PriceAgg, Long>  {

    @Query(value = "select top 1 * from price_agg WHERE symbol=?1 order by id desc",nativeQuery=true)
    PriceAgg getLatestBestPriceAgg(String symbol); //findTopByOrderByIdDesc

    //select top 1 * from price_agg  where symbol='ETHUSDT' order by id desc


}
