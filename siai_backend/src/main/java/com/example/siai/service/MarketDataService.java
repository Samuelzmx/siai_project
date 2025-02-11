package com.example.siai.service;

import com.example.siai.entity.MarketData;
import com.example.siai.repository.MarketDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketDataService {

    @Autowired
    private MarketDataRepository marketDataRepository;

    public List<MarketData> getAllMarketData() {
        return marketDataRepository.findAll();
    }

    public MarketData getMarketDataById(Integer id) {
        return marketDataRepository.findById(id).orElse(null);
    }

    public MarketData createMarketData(MarketData data) {
        return marketDataRepository.save(data);
    }

    public MarketData updateMarketData(Integer id, MarketData updated) {
        Optional<MarketData> optional = marketDataRepository.findById(id);
        if (optional.isPresent()) {
            MarketData existing = optional.get();
            existing.setCompany(updated.getCompany());
            existing.setDataDate(updated.getDataDate());
            existing.setOpenPrice(updated.getOpenPrice());
            existing.setClosePrice(updated.getClosePrice());
            existing.setHighPrice(updated.getHighPrice());
            existing.setLowPrice(updated.getLowPrice());
            existing.setVolume(updated.getVolume());
            existing.setCreatedAt(updated.getCreatedAt());
            existing.setUpdatedAt(updated.getUpdatedAt());
            return marketDataRepository.save(existing);
        }
        return null;
    }

    public void deleteMarketData(Integer id) {
        marketDataRepository.deleteById(id);
    }
    public void deleteAllMarketData() {
        marketDataRepository.deleteAll();
    }
}
