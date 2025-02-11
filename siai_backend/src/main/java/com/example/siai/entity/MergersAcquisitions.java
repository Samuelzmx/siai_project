package com.example.siai.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mergers_acquisitions")
public class MergersAcquisitions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maId;

    @ManyToOne
    @JoinColumn(name = "acquiring_company_id", nullable = false)
    private Company acquiringCompany;

    @ManyToOne
    @JoinColumn(name = "acquired_company_id", nullable = false)
    private Company acquiredCompany;

    @Temporal(TemporalType.DATE)
    private Date announcementDate;

    @Temporal(TemporalType.DATE)
    private Date completionDate;

    private Double dealValue;
    private String details;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public MergersAcquisitions() {}

    public Integer getMaId() {
        return maId;
    }

    public void setMaId(Integer maId) {
        this.maId = maId;
    }

    public Company getAcquiringCompany() {
        return acquiringCompany;
    }

    public void setAcquiringCompany(Company acquiringCompany) {
        this.acquiringCompany = acquiringCompany;
    }

    public Company getAcquiredCompany() {
        return acquiredCompany;
    }

    public void setAcquiredCompany(Company acquiredCompany) {
        this.acquiredCompany = acquiredCompany;
    }

    public Date getAnnouncementDate() {
        return announcementDate;
    }

    public void setAnnouncementDate(Date announcementDate) {
        this.announcementDate = announcementDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Double getDealValue() {
        return dealValue;
    }

    public void setDealValue(Double dealValue) {
        this.dealValue = dealValue;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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
