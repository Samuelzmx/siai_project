package com.example.siai.controller;

import com.example.siai.entity.SecFiling;
import com.example.siai.service.SecFilingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/secfiling")
@CrossOrigin(origins = "http://localhost:3000")
public class SecFilingController {

    @Autowired
    private SecFilingService secFilingService;

    @GetMapping
    public ResponseEntity<List<SecFiling>> getAllSecFilings() {
        return ResponseEntity.ok(secFilingService.getAllSecFilings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SecFiling> getSecFilingById(@PathVariable Integer id) {
        SecFiling filing = secFilingService.getSecFilingById(id);
        if (filing == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(filing);
    }

    @PostMapping
    public ResponseEntity<SecFiling> createSecFiling(@RequestBody SecFiling filing) {
        SecFiling newFiling = secFilingService.createSecFiling(filing);
        return ResponseEntity.ok(newFiling);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SecFiling> updateSecFiling(@PathVariable Integer id, @RequestBody SecFiling updated) {
        SecFiling result = secFilingService.updateSecFiling(id, updated);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSecFiling(@PathVariable Integer id) {
        secFilingService.deleteSecFiling(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllSecFilings() {
        secFilingService.deleteAllSecFilings();
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
