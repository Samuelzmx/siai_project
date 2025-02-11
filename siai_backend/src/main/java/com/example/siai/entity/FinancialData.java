package com.example.siai.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "financial_data")
public class FinancialData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer finId;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Temporal(TemporalType.DATE)
    private Date periodStart;

    @Temporal(TemporalType.DATE)
    private Date periodEnd;

    private Double revenue;
    private Double netIncome;
    private Double totalAssets;
    private Double totalLiabilities;
    private Double eps;          // decimal(10,4)
    private Double pERatio;      // p_e_ratio decimal(10,2)
    private Double evEbitda;     // ev_ebitda decimal(10,2)

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public FinancialData() {}

    // getters, setters
    public Integer getFinId() {
        return finId;
    }

    public void setFinId(Integer finId) {
        this.finId = finId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Date getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(Date periodStart) {
        this.periodStart = periodStart;
    }

    public Date getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Date periodEnd) {
        this.periodEnd = periodEnd;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    public Double getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(Double netIncome) {
        this.netIncome = netIncome;
    }

    public Double getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(Double totalAssets) {
        this.totalAssets = totalAssets;
    }

    public Double getTotalLiabilities() {
        return totalLiabilities;
    }

    public void setTotalLiabilities(Double totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }

    public Double getEps() {
        return eps;
    }

    public void setEps(Double eps) {
        this.eps = eps;
    }

    public Double getPERatio() {
        return pERatio;
    }

    public void setPERatio(Double pERatio) {
        this.pERatio = pERatio;
    }

    public Double getEvEbitda() {
        return evEbitda;
    }

    public void setEvEbitda(Double evEbitda) {
        this.evEbitda = evEbitda;
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
