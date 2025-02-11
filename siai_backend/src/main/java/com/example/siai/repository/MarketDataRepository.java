package com.example.siai.repository;

import com.example.siai.entity.MarketData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Repository
public interface MarketDataRepository extends JpaRepository<MarketData, Integer> {

    // 1) Find records within a date range
    @Query("SELECT m FROM MarketData m WHERE m.dataDate BETWEEN :start AND :end")
    List<MarketData> findMarketDataByDateRange(@Param("start") Date start, @Param("end") Date end);

    // 2) volume above certain threshold
    List<MarketData> findByVolumeGreaterThan(Long minVolume);

    // 3) find by ticker
    @Query("SELECT m FROM MarketData m WHERE m.company.ticker = :ticker")
    List<MarketData> findByCompanyTicker(@Param("ticker") String ticker);
}
