package com.example.siai.service;

import com.example.siai.entity.SecFiling;
import com.example.siai.repository.SecFilingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecFilingService {

    @Autowired
    private SecFilingRepository secFilingRepository;

    public List<SecFiling> getAllSecFilings() {
        return secFilingRepository.findAll();
    }

    public SecFiling getSecFilingById(Integer id) {
        return secFilingRepository.findById(id).orElse(null);
    }

    public SecFiling createSecFiling(SecFiling filing) {
        return secFilingRepository.save(filing);
    }

    public SecFiling updateSecFiling(Integer id, SecFiling updated) {
        Optional<SecFiling> optional = secFilingRepository.findById(id);
        if (optional.isPresent()) {
            SecFiling existing = optional.get();
            existing.setCompany(updated.getCompany());
            existing.setFilingType(updated.getFilingType());
            existing.setFilingDate(updated.getFilingDate());
            existing.setFilingContent(updated.getFilingContent());
            existing.setUrl(updated.getUrl());
            existing.setCreatedAt(updated.getCreatedAt());
            existing.setUpdatedAt(updated.getUpdatedAt());
            return secFilingRepository.save(existing);
        }
        return null; 
    }

    public void deleteSecFiling(Integer id) {
        secFilingRepository.deleteById(id);
    }
    public void deleteAllSecFilings() {
        secFilingRepository.deleteAll();
    }
    
}
