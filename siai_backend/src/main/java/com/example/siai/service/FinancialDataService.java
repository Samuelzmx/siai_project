package com.example.siai.service;

import com.example.siai.entity.FinancialData;
import com.example.siai.repository.FinancialDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialDataService {

    @Autowired
    private FinancialDataRepository financialDataRepository;

    public List<FinancialData> getAllFinancialData() {
        return financialDataRepository.findAll();
    }

    public FinancialData getFinancialDataById(Integer id) {
        return financialDataRepository.findById(id).orElse(null);
    }

    public FinancialData createFinancialData(FinancialData data) {
        return financialDataRepository.save(data);
    }

    public FinancialData updateFinancialData(Integer id, FinancialData updated) {
        Optional<FinancialData> optional = financialDataRepository.findById(id);
        if (optional.isPresent()) {
            FinancialData existing = optional.get();
            existing.setCompany(updated.getCompany());
            existing.setPeriodStart(updated.getPeriodStart());
            existing.setPeriodEnd(updated.getPeriodEnd());
            existing.setRevenue(updated.getRevenue());
            existing.setNetIncome(updated.getNetIncome());
            existing.setTotalAssets(updated.getTotalAssets());
            existing.setTotalLiabilities(updated.getTotalLiabilities());
            existing.setEps(updated.getEps());
            existing.setPERatio(updated.getPERatio());
            existing.setEvEbitda(updated.getEvEbitda());
            existing.setCreatedAt(updated.getCreatedAt());
            existing.setUpdatedAt(updated.getUpdatedAt());
            return financialDataRepository.save(existing);
        }
        return null;
    }

    public void deleteFinancialData(Integer id) {
        financialDataRepository.deleteById(id);
    }
    public void deleteAllFinancialData() {
        financialDataRepository.deleteAll();
    }
}
