package com.example.siai.service;

import com.example.siai.entity.DebtInterestRates;
import com.example.siai.repository.DebtInterestRatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DebtInterestRatesService {

    @Autowired
    private DebtInterestRatesRepository debtRepo;

    public List<DebtInterestRates> getAllDebtRates() {
        return debtRepo.findAll();
    }

    public DebtInterestRates getDebtRatesById(Integer id) {
        return debtRepo.findById(id).orElse(null);
    }

    public DebtInterestRates createDebtRates(DebtInterestRates rates) {
        return debtRepo.save(rates);
    }

    public DebtInterestRates updateDebtRates(Integer id, DebtInterestRates updated) {
        Optional<DebtInterestRates> optional = debtRepo.findById(id);
        if (optional.isPresent()) {
            DebtInterestRates existing = optional.get();
            existing.setCompany(updated.getCompany());
            existing.setDate(updated.getDate());
            existing.setBondYield(updated.getBondYield());
            existing.setCreditRating(updated.getCreditRating());
            existing.setSpread(updated.getSpread());
            existing.setCreatedAt(updated.getCreatedAt());
            existing.setUpdatedAt(updated.getUpdatedAt());
            return debtRepo.save(existing);
        }
        return null;
    }

    public void deleteDebtRates(Integer id) {
        debtRepo.deleteById(id);
    }
    public void deleteAllDebtRates() {
        debtRepo.deleteAll();
    }
}
