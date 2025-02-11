package com.example.siai.service;

import com.example.siai.entity.Regulations;
import com.example.siai.repository.RegulationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegulationsService {

    @Autowired
    private RegulationsRepository regRepo;

    public List<Regulations> getAllRegulations() {
        return regRepo.findAll();
    }

    public Regulations getRegulationById(Integer id) {
        return regRepo.findById(id).orElse(null);
    }

    public Regulations createRegulation(Regulations reg) {
        return regRepo.save(reg);
    }

    public Regulations updateRegulation(Integer id, Regulations updated) {
        Optional<Regulations> optional = regRepo.findById(id);
        if (optional.isPresent()) {
            Regulations existing = optional.get();
            existing.setTitle(updated.getTitle());
            existing.setEffectiveDate(updated.getEffectiveDate());
            existing.setJurisdiction(updated.getJurisdiction());
            existing.setSummary(updated.getSummary());
            existing.setLink(updated.getLink());
            existing.setCreatedAt(updated.getCreatedAt());
            existing.setUpdatedAt(updated.getUpdatedAt());
            return regRepo.save(existing);
        }
        return null;
    }

    public void deleteRegulation(Integer id) {
        regRepo.deleteById(id);
    }
}
