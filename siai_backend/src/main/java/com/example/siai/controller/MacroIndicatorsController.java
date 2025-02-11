package com.example.siai.controller;

import com.example.siai.entity.MacroIndicators;
import com.example.siai.service.MacroIndicatorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/macroindicators")
@CrossOrigin(origins = "http://localhost:3000")
public class MacroIndicatorsController {

    @Autowired
    private MacroIndicatorsService macroService;

    @GetMapping
    public ResponseEntity<List<MacroIndicators>> getAllMacroIndicators() {
        List<MacroIndicators> list = macroService.getAllMacroIndicators();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MacroIndicators> getMacroIndicatorById(@PathVariable Integer id) {
        MacroIndicators indicator = macroService.getMacroIndicatorsById(id);
        if (indicator == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(indicator);
    }

    @PostMapping
    public ResponseEntity<MacroIndicators> createMacroIndicator(@RequestBody MacroIndicators newIndicator) {
        MacroIndicators created = macroService.createMacroIndicators(newIndicator);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MacroIndicators> updateMacroIndicator(@PathVariable Integer id,
                                                                @RequestBody MacroIndicators updated) {
        MacroIndicators result = macroService.updateMacroIndicators(id, updated);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMacroIndicator(@PathVariable Integer id) {
        macroService.deleteMacroIndicators(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllMacroIndicators() {
        macroService.deleteAllMacroIndicators();
        return ResponseEntity.noContent().build();
    }
}
