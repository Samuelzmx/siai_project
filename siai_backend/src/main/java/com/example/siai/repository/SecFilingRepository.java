package com.example.siai.repository;

import com.example.siai.entity.SecFiling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SecFilingRepository extends JpaRepository<SecFiling, Integer> {

    // 1) Find by filingType ignoring case
    List<SecFiling> findAllByFilingTypeIgnoreCase(String filingType);

    // 2) Find all filings after a certain date
    List<SecFiling> findByFilingDateAfter(Date date);

    // 3) JPQL example: find all for a specific company ticker
    @Query("SELECT s FROM SecFiling s WHERE s.company.ticker = :ticker")
    List<SecFiling> findByCompanyTicker(@Param("ticker") String ticker);

    // 4) JPQL for date range
    @Query("SELECT s FROM SecFiling s WHERE s.filingDate BETWEEN :startDate AND :endDate")
    List<SecFiling> findFilingsBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT s FROM SecFiling s " +
           "WHERE s.company.name = :companyName " +
           "AND LOWER(s.filingType) = LOWER(:filingType) " +
           "AND s.filingDate = :filingDate")
    Optional<SecFiling> findByCompanyNameAndTypeAndDate(
        @Param("companyName") String companyName,
        @Param("filingType")  String filingType,
        @Param("filingDate")  Date filingDate
    );
}
