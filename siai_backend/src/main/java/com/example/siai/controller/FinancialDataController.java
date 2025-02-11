package com.example.siai.controller;

import com.example.siai.entity.FinancialData;
import com.example.siai.service.FinancialDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/financialdata")
@CrossOrigin(origins = "http://localhost:3000")
public class FinancialDataController {

    @Autowired
    private FinancialDataService financialDataService;

    @GetMapping
    public ResponseEntity<List<FinancialData>> getAllFinancialData() {
        List<FinancialData> list = financialDataService.getAllFinancialData();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialData> getFinancialDataById(@PathVariable Integer id) {
        FinancialData data = financialDataService.getFinancialDataById(id);
        if (data == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }

    @PostMapping
    public ResponseEntity<FinancialData> createFinancialData(@RequestBody FinancialData newData) {
        FinancialData created = financialDataService.createFinancialData(newData);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinancialData> updateFinancialData(@PathVariable Integer id,
                                                             @RequestBody FinancialData updated) {
        FinancialData result = financialDataService.updateFinancialData(id, updated);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinancialData(@PathVariable Integer id) {
        financialDataService.deleteFinancialData(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllFinancialData() {
        financialDataService.deleteAllFinancialData();
        return ResponseEntity.noContent().build();
    }
}
