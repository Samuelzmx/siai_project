package com.example.siai.repository;

import com.example.siai.entity.Regulations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Repository
public interface RegulationsRepository extends JpaRepository<Regulations, Integer> {

    // 1) find by effectiveDate before certain date
    List<Regulations> findByEffectiveDateBefore(Date date);

    // 2) find by jurisdiction ignoring case
    List<Regulations> findByJurisdictionIgnoreCase(String jurisdiction);

    // 3) partial match on title
    List<Regulations> findByTitleContainingIgnoreCase(String partialTitle);

    // 4) custom
    @Query("SELECT r FROM Regulations r WHERE r.effectiveDate BETWEEN :start AND :end")
    List<Regulations> findRegulationsBetweenDates(@Param("start") Date start, @Param("end") Date end);
}
