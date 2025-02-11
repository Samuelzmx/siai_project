package com.example.siai.service;

import com.example.siai.entity.IndustryReports;
import com.example.siai.repository.IndustryReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndustryReportsService {

    @Autowired
    private IndustryReportsRepository reportsRepo;

    public List<IndustryReports> getAllIndustryReports() {
        return reportsRepo.findAll();
    }

    public IndustryReports getIndustryReportsById(Integer id) {
        return reportsRepo.findById(id).orElse(null);
    }

    public IndustryReports createIndustryReports(IndustryReports report) {
        return reportsRepo.save(report);
    }

    public IndustryReports updateIndustryReports(Integer id, IndustryReports updated) {
        Optional<IndustryReports> optional = reportsRepo.findById(id);
        if (optional.isPresent()) {
            IndustryReports existing = optional.get();
            existing.setIndustryName(updated.getIndustryName());
            existing.setReportTitle(updated.getReportTitle());
            existing.setReportDate(updated.getReportDate());
            existing.setContent(updated.getContent());
            existing.setLink(updated.getLink());
            existing.setCreatedAt(updated.getCreatedAt());
            existing.setUpdatedAt(updated.getUpdatedAt());
            return reportsRepo.save(existing);
        }
        return null;
    }

    public void deleteIndustryReports(Integer id) {
        reportsRepo.deleteById(id);
    }
    public void deleteAllIndustryReports() {
        reportsRepo.deleteAll();
    }
}
