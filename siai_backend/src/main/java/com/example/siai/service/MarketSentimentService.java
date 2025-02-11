package com.example.siai.service;

import com.example.siai.entity.MarketSentiment;
import com.example.siai.repository.MarketSentimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketSentimentService {

    @Autowired
    private MarketSentimentRepository sentimentRepo;

    public List<MarketSentiment> getAllSentiment() {
        return sentimentRepo.findAll();
    }

    public MarketSentiment getSentimentById(Integer id) {
        return sentimentRepo.findById(id).orElse(null);
    }

    public MarketSentiment createSentiment(MarketSentiment ms) {
        return sentimentRepo.save(ms);
    }

    public MarketSentiment updateSentiment(Integer id, MarketSentiment updated) {
        Optional<MarketSentiment> optional = sentimentRepo.findById(id);
        if (optional.isPresent()) {
            MarketSentiment existing = optional.get();
            existing.setCompany(updated.getCompany());
            existing.setSentimentDate(updated.getSentimentDate());
            existing.setSentimentScore(updated.getSentimentScore());
            existing.setSource(updated.getSource());
            existing.setKeyPhrases(updated.getKeyPhrases());
            existing.setCreatedAt(updated.getCreatedAt());
            existing.setUpdatedAt(updated.getUpdatedAt());
            return sentimentRepo.save(existing);
        }
        return null;
    }

    public void deleteSentiment(Integer id) {
        sentimentRepo.deleteById(id);
    }
    public void deleteAllSentiment() {
        sentimentRepo.deleteAll();
    }
}
