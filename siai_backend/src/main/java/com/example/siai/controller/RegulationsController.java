package com.example.siai.controller;

import com.example.siai.entity.Regulations;
import com.example.siai.service.RegulationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regulations")
@CrossOrigin(origins = "http://localhost:3000")
public class RegulationsController {

    @Autowired
    private RegulationsService regService;

    @GetMapping
    public ResponseEntity<List<Regulations>> getAllRegulations() {
        return ResponseEntity.ok(regService.getAllRegulations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Regulations> getRegulationById(@PathVariable Integer id) {
        Regulations reg = regService.getRegulationById(id);
        if (reg == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reg);
    }

    @PostMapping
    public ResponseEntity<Regulations> createRegulation(@RequestBody Regulations newReg) {
        Regulations created = regService.createRegulation(newReg);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Regulations> updateRegulation(@PathVariable Integer id, @RequestBody Regulations updated) {
        Regulations result = regService.updateRegulation(id, updated);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegulation(@PathVariable Integer id) {
        regService.deleteRegulation(id);
        return ResponseEntity.noContent().build();
    }
}
