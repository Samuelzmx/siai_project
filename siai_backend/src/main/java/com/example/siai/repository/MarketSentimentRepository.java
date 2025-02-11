package com.example.siai.repository;

import com.example.siai.entity.MarketSentiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Repository
public interface MarketSentimentRepository extends JpaRepository<MarketSentiment, Integer> {

    // 1) find by date range
    @Query("SELECT ms FROM MarketSentiment ms WHERE ms.sentimentDate BETWEEN :start AND :end")
    List<MarketSentiment> findBySentimentDateBetween(@Param("start") Date start,
                                                     @Param("end") Date end);

    // 2) find by sentimentScore > X
    List<MarketSentiment> findBySentimentScoreGreaterThan(Double score);

    // 3) partial match on source ignoring case
    List<MarketSentiment> findBySourceIgnoreCase(String source);

    // 4) find by company ticker
    @Query("SELECT ms FROM MarketSentiment ms WHERE ms.company.ticker = :ticker")
    List<MarketSentiment> findByCompanyTicker(@Param("ticker") String ticker);
}
