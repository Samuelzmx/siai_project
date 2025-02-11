package com.example.siai.controller;

import com.example.siai.entity.DebtInterestRates;
import com.example.siai.service.DebtInterestRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/debtinterest")
@CrossOrigin(origins = "http://localhost:3000")
public class DebtInterestRatesController {
    private DebtInterestRatesService debtService;

    @Autowired
    private DebtInterestRatesService debtRatesService;

    @GetMapping
    public ResponseEntity<List<DebtInterestRates>> getAllDebtRates() {
        List<DebtInterestRates> list = debtRatesService.getAllDebtRates();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DebtInterestRates> getDebtRatesById(@PathVariable Integer id) {
        DebtInterestRates found = debtRatesService.getDebtRatesById(id);
        if (found == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(found);
    }

    @PostMapping
    public ResponseEntity<DebtInterestRates> createDebtRates(@RequestBody DebtInterestRates newRates) {
        DebtInterestRates created = debtRatesService.createDebtRates(newRates);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DebtInterestRates> updateDebtRates(@PathVariable Integer id,
                                                             @RequestBody DebtInterestRates updated) {
        DebtInterestRates result = debtRatesService.updateDebtRates(id, updated);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDebtRates(@PathVariable Integer id) {
        debtRatesService.deleteDebtRates(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllDebtRates() {
        debtService.deleteAllDebtRates();
        return ResponseEntity.noContent().build();
    }
    
}
