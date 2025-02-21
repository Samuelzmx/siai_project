package com.example.siai.repository;

import com.example.siai.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    // 1) Basic finder by unique ticker (returns 0 or 1 result)
    Optional<Company> findByTicker(String ticker);

    // 2) Find multiple companies by industry
    List<Company> findAllByIndustry(String industry);

    // 3) Find companies by an exact name, ignoring case
    List<Company> findAllByNameIgnoreCase(String name);

    // 4) Find companies by partial name match, ignoring case
    List<Company> findByNameContainingIgnoreCase(String partialName);

    // 5) Example of a custom JPQL query if you want a more complex filter
    @Query("SELECT c FROM Company c WHERE c.sector = :sector AND c.industry = :industry")
    List<Company> findBySectorAndIndustry(
            @Param("sector") String sector,
            @Param("industry") String industry
    );

    // 6) Another custom example: fetch all companies with a ticker that starts with a prefix
    @Query("SELECT c FROM Company c WHERE c.ticker LIKE CONCAT(:prefix, '%')")
    List<Company> findByTickerStartingWith(@Param("prefix") String prefix);
}