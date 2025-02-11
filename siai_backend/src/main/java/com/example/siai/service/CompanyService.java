package com.example.siai.service;

import com.example.siai.entity.Company;
import com.example.siai.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Integer id) {
        return companyRepository.findById(id).orElse(null);
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Integer id, Company updated) {
        Optional<Company> optional = companyRepository.findById(id);
        if (optional.isPresent()) {
            Company existing = optional.get();
            existing.setName(updated.getName());
            existing.setTicker(updated.getTicker());
            existing.setIndustry(updated.getIndustry());
            existing.setSector(updated.getSector());
            return companyRepository.save(existing);
        }
        return null; // or throw new NotFoundException(...) etc.
    }

    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }
    public void deleteAllCompanies() {
        companyRepository.deleteAll();
    }
}
