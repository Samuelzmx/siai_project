package com.example.siai.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "macro_indicators")
public class MacroIndicators {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer macroId;

    @Temporal(TemporalType.DATE)
    private Date indicatorDate;

    private Double gdp;
    private Double cpi;
    private Double unemploymentRate;
    private Double interestRate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public MacroIndicators() {}

    public Integer getMacroId() {
        return macroId;
    }

    public void setMacroId(Integer macroId) {
        this.macroId = macroId;
    }

    public Date getIndicatorDate() {
        return indicatorDate;
    }

    public void setIndicatorDate(Date indicatorDate) {
        this.indicatorDate = indicatorDate;
    }

    public Double getGdp() {
        return gdp;
    }

    public void setGdp(Double gdp) {
        this.gdp = gdp;
    }

    public Double getCpi() {
        return cpi;
    }

    public void setCpi(Double cpi) {
        this.cpi = cpi;
    }

    public Double getUnemploymentRate() {
        return unemploymentRate;
    }

    public void setUnemploymentRate(Double unemploymentRate) {
        this.unemploymentRate = unemploymentRate;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
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
