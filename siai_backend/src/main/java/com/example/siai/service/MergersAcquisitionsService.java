package com.example.siai.service;

import com.example.siai.entity.MergersAcquisitions;
import com.example.siai.repository.MergersAcquisitionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MergersAcquisitionsService {

    @Autowired
    private MergersAcquisitionsRepository maRepository;

    public List<MergersAcquisitions> getAllMAs() {
        return maRepository.findAll();
    }

    public MergersAcquisitions getMAById(Integer id) {
        return maRepository.findById(id).orElse(null);
    }

    public MergersAcquisitions createMA(MergersAcquisitions ma) {
        return maRepository.save(ma);
    }

    public MergersAcquisitions updateMA(Integer id, MergersAcquisitions updated) {
        Optional<MergersAcquisitions> optional = maRepository.findById(id);
        if (optional.isPresent()) {
            MergersAcquisitions existing = optional.get();
            existing.setAcquiringCompany(updated.getAcquiringCompany());
            existing.setAcquiredCompany(updated.getAcquiredCompany());
            existing.setAnnouncementDate(updated.getAnnouncementDate());
            existing.setCompletionDate(updated.getCompletionDate());
            existing.setDealValue(updated.getDealValue());
            existing.setDetails(updated.getDetails());
            existing.setCreatedAt(updated.getCreatedAt());
            existing.setUpdatedAt(updated.getUpdatedAt());
            return maRepository.save(existing);
        }
        return null;
    }

    public void deleteMA(Integer id) {
        maRepository.deleteById(id);
    }
    public void deleteAllMAs() {
        maRepository.deleteAll();
    }
}
