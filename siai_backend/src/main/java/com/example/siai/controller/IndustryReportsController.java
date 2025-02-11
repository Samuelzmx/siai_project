package com.example.siai.controller;

import com.example.siai.entity.IndustryReports;
import com.example.siai.service.IndustryReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/industryreports")
@CrossOrigin(origins = "http://localhost:3000")
public class IndustryReportsController {

    @Autowired
    private IndustryReportsService reportsService;

    @GetMapping
    public ResponseEntity<List<IndustryReports>> getAllIndustryReports() {
        List<IndustryReports> list = reportsService.getAllIndustryReports();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IndustryReports> getIndustryReportById(@PathVariable Integer id) {
        IndustryReports report = reportsService.getIndustryReportsById(id);
        if (report == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(report);
    }

    @PostMapping
    public ResponseEntity<IndustryReports> createIndustryReport(@RequestBody IndustryReports newReport) {
        IndustryReports created = reportsService.createIndustryReports(newReport);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IndustryReports> updateIndustryReport(@PathVariable Integer id,
                                                                @RequestBody IndustryReports updated) {
        IndustryReports result = reportsService.updateIndustryReports(id, updated);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIndustryReport(@PathVariable Integer id) {
        reportsService.deleteIndustryReports(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllIndustryReports() {
        reportsService.deleteAllIndustryReports();
        return ResponseEntity.noContent().build();
    }
}
