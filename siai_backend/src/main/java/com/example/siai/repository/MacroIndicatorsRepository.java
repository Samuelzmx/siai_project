package com.example.siai.repository;

import com.example.siai.entity.MacroIndicators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Repository
public interface MacroIndicatorsRepository extends JpaRepository<MacroIndicators, Integer> {

    // 1) find indicators by date range
    @Query("SELECT mi FROM MacroIndicators mi WHERE mi.indicatorDate BETWEEN :start AND :end")
    List<MacroIndicators> findByIndicatorDateBetween(@Param("start") Date start,
                                                     @Param("end") Date end);

    // 2) find where GDP > X
    List<MacroIndicators> findByGdpGreaterThan(Double gdp);

    // 3) find by interestRate < Y
    List<MacroIndicators> findByInterestRateLessThan(Double rate);
}
