package com.example.siai.repository;

import com.example.siai.entity.FinancialData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Repository
public interface FinancialDataRepository extends JpaRepository<FinancialData, Integer> {

    // 1) Find data by a date range (periodStart > X, periodEnd < Y)
    @Query("SELECT f FROM FinancialData f WHERE f.periodStart >= :start AND f.periodEnd <= :end")
    List<FinancialData> findByPeriodWithin(
        @Param("start") Date start,
        @Param("end") Date end
    );

    // 2) Find by revenue above a threshold
    List<FinancialData> findByRevenueGreaterThan(Double revenue);

    // 3) Example: find all by company's ticker
    @Query("SELECT f FROM FinancialData f WHERE f.company.ticker = :ticker")
    List<FinancialData> findAllByCompanyTicker(@Param("ticker") String ticker);
}
