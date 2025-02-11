package com.example.siai.repository;

import com.example.siai.entity.IndustryReports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Repository
public interface IndustryReportsRepository extends JpaRepository<IndustryReports, Integer> {

    // 1) find by industryName ignoring case
    List<IndustryReports> findAllByIndustryNameIgnoreCase(String industryName);

    // 2) reports after certain date
    List<IndustryReports> findByReportDateAfter(Date date);

    // 3) partial match on reportTitle
    List<IndustryReports> findByReportTitleContainingIgnoreCase(String partialTitle);

    // 4) custom example
    @Query("SELECT ir FROM IndustryReports ir WHERE ir.industryName = :industry AND ir.reportDate <= :cutoff")
    List<IndustryReports> findReportsBeforeDate(
            @Param("industry") String industry,
            @Param("cutoff") Date cutoff
    );
}
