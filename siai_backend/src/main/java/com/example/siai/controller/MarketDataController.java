package com.example.siai.controller;

import com.example.siai.entity.MarketData;
import com.example.siai.service.MarketDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marketdata")
@CrossOrigin(origins = "http://localhost:3000")
public class MarketDataController {

    @Autowired
    private MarketDataService marketDataService;

    @GetMapping
    public ResponseEntity<List<MarketData>> getAllMarketData() {
        List<MarketData> list = marketDataService.getAllMarketData();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarketData> getMarketDataById(@PathVariable Integer id) {
        MarketData data = marketDataService.getMarketDataById(id);
        if (data == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }

    @PostMapping
    public ResponseEntity<MarketData> createMarketData(@RequestBody MarketData newData) {
        MarketData created = marketDataService.createMarketData(newData);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarketData> updateMarketData(@PathVariable Integer id, @RequestBody MarketData updated) {
        MarketData result = marketDataService.updateMarketData(id, updated);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarketData(@PathVariable Integer id) {
        marketDataService.deleteMarketData(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllMarketData() {
        marketDataService.deleteAllMarketData();
        return ResponseEntity.noContent().build();
    }
}
