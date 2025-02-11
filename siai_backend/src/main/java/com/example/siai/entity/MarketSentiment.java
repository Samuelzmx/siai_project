package com.example.siai.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "market_sentiment")
public class MarketSentiment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sentimentId;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Temporal(TemporalType.TIMESTAMP)
    private Date sentimentDate;

    private Double sentimentScore;
    private String source;

    @Lob
    private String keyPhrases;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public MarketSentiment() {}

    public Integer getSentimentId() {
        return sentimentId;
    }

    public void setSentimentId(Integer sentimentId) {
        this.sentimentId = sentimentId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Date getSentimentDate() {
        return sentimentDate;
    }

    public void setSentimentDate(Date sentimentDate) {
        this.sentimentDate = sentimentDate;
    }

    public Double getSentimentScore() {
        return sentimentScore;
    }

    public void setSentimentScore(Double sentimentScore) {
        this.sentimentScore = sentimentScore;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getKeyPhrases() {
        return keyPhrases;
    }

    public void setKeyPhrases(String keyPhrases) {
        this.keyPhrases = keyPhrases;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
