package com.example.siai.repository;

import com.example.siai.entity.DebtInterestRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Repository
public interface DebtInterestRatesRepository extends JpaRepository<DebtInterestRates, Integer> {

    // 1) find by date
    List<DebtInterestRates> findByDate(Date date);

    // 2) find by bondYield > X
    List<DebtInterestRates> findByBondYieldGreaterThan(Double yield);

    // 3) find by creditRating ignoring case
    List<DebtInterestRates> findByCreditRatingIgnoreCase(String creditRating);

    // 4) custom query: find all for a given company ticker
    @Query("SELECT d FROM DebtInterestRates d WHERE d.company.ticker = :ticker")
    List<DebtInterestRates> findAllByCompanyTicker(@Param("ticker") String ticker);
}
