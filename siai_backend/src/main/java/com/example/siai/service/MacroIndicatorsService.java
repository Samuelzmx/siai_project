package com.example.siai.service;

import com.example.siai.entity.MacroIndicators;
import com.example.siai.repository.MacroIndicatorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MacroIndicatorsService {

    @Autowired
    private MacroIndicatorsRepository macroRepo;

    public List<MacroIndicators> getAllMacroIndicators() {
        return macroRepo.findAll();
    }

    public MacroIndicators getMacroIndicatorsById(Integer id) {
        return macroRepo.findById(id).orElse(null);
    }

    public MacroIndicators createMacroIndicators(MacroIndicators mi) {
        return macroRepo.save(mi);
    }

    public MacroIndicators updateMacroIndicators(Integer id, MacroIndicators updated) {
        Optional<MacroIndicators> optional = macroRepo.findById(id);
        if (optional.isPresent()) {
            MacroIndicators existing = optional.get();
            existing.setIndicatorDate(updated.getIndicatorDate());
            existing.setGdp(updated.getGdp());
            existing.setCpi(updated.getCpi());
            existing.setUnemploymentRate(updated.getUnemploymentRate());
            existing.setInterestRate(updated.getInterestRate());
            existing.setCreatedAt(updated.getCreatedAt());
            existing.setUpdatedAt(updated.getUpdatedAt());
            return macroRepo.save(existing);
        }
        return null;
    }

    public void deleteMacroIndicators(Integer id) {
        macroRepo.deleteById(id);
    }
    public void deleteAllMacroIndicators() {
        macroRepo.deleteAll();
    }
}
