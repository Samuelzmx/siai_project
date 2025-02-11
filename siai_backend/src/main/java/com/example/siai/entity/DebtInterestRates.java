package com.example.siai.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "debt_interest_rates")
public class DebtInterestRates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer debtId;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Temporal(TemporalType.DATE)
    private Date date;

    private Double bondYield;
    private String creditRating;
    private Double spread;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public DebtInterestRates() {}

    public Integer getDebtId() {
        return debtId;
    }

    public void setDebtId(Integer debtId) {
        this.debtId = debtId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getBondYield() {
        return bondYield;
    }

    public void setBondYield(Double bondYield) {
        this.bondYield = bondYield;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }

    public Double getSpread() {
        return spread;
    }

    public void setSpread(Double spread) {
        this.spread = spread;
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
