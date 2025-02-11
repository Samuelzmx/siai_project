package com.example.siai.controller;

import com.example.siai.entity.MergersAcquisitions;
import com.example.siai.service.MergersAcquisitionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mergersacquisitions")
@CrossOrigin(origins = "http://localhost:3000")
public class MergersAcquisitionsController {

    @Autowired
    private MergersAcquisitionsService maService;

    @GetMapping
    public ResponseEntity<List<MergersAcquisitions>> getAllMAs() {
        return ResponseEntity.ok(maService.getAllMAs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MergersAcquisitions> getMAById(@PathVariable Integer id) {
        MergersAcquisitions ma = maService.getMAById(id);
        if (ma == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ma);
    }

    @PostMapping
    public ResponseEntity<MergersAcquisitions> createMA(@RequestBody MergersAcquisitions newMA) {
        MergersAcquisitions created = maService.createMA(newMA);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MergersAcquisitions> updateMA(@PathVariable Integer id, @RequestBody MergersAcquisitions updated) {
        MergersAcquisitions result = maService.updateMA(id, updated);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMA(@PathVariable Integer id) {
        maService.deleteMA(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllMAs() {
        maService.deleteAllMAs();
        return ResponseEntity.noContent().build();
    }
}
