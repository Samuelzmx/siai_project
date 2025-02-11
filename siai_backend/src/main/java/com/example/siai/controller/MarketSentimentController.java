package com.example.siai.controller;

import com.example.siai.entity.MarketSentiment;
import com.example.siai.service.MarketSentimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marketsentiment")
@CrossOrigin(origins = "http://localhost:3000")
public class MarketSentimentController {

    @Autowired
    private MarketSentimentService sentimentService;

    @GetMapping
    public ResponseEntity<List<MarketSentiment>> getAllMarketSentiment() {
        return ResponseEntity.ok(sentimentService.getAllSentiment());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarketSentiment> getSentimentById(@PathVariable Integer id) {
        MarketSentiment ms = sentimentService.getSentimentById(id);
        if (ms == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ms);
    }

    @PostMapping
    public ResponseEntity<MarketSentiment> createMarketSentiment(@RequestBody MarketSentiment newSentiment) {
        MarketSentiment created = sentimentService.createSentiment(newSentiment);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarketSentiment> updateMarketSentiment(@PathVariable Integer id, @RequestBody MarketSentiment updated) {
        MarketSentiment result = sentimentService.updateSentiment(id, updated);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarketSentiment(@PathVariable Integer id) {
        sentimentService.deleteSentiment(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllSentiment() {
        sentimentService.deleteAllSentiment();
        return ResponseEntity.noContent().build();
    }
}
