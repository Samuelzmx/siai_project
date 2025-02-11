package com.example.siai.repository;

import com.example.siai.entity.MergersAcquisitions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Repository
public interface MergersAcquisitionsRepository extends JpaRepository<MergersAcquisitions, Integer> {

    // 1) find deals after certain date
    List<MergersAcquisitions> findByAnnouncementDateAfter(Date date);

    // 2) find by acquiring company
    @Query("SELECT m FROM MergersAcquisitions m WHERE m.acquiringCompany.companyId = :companyId")
    List<MergersAcquisitions> findAllByAcquiringCompany(@Param("companyId") Integer companyId);

    // 3) find by acquired company ticker
    @Query("SELECT m FROM MergersAcquisitions m WHERE m.acquiredCompany.ticker = :ticker")
    List<MergersAcquisitions> findAllByAcquiredCompanyTicker(@Param("ticker") String ticker);
}
