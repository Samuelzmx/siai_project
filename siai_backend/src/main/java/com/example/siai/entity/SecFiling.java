package com.example.siai.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sec_filing")
public class SecFiling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer filingId;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    // Example: store the enum as String (S-1, S-1A, 10-K, etc.)
    private String filingType;

    @Temporal(TemporalType.DATE)
    private Date filingDate;

    @Lob
    private String filingContent;

    @Column(length = 500)
    private String url;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public SecFiling() {}

    // getters, setters
    public Integer getFilingId() {
        return filingId;
    }

    public void setFilingId(Integer filingId) {
        this.filingId = filingId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getFilingType() {
        return filingType;
    }

    public void setFilingType(String filingType) {
        this.filingType = filingType;
    }

    public Date getFilingDate() {
        return filingDate;
    }

    public void setFilingDate(Date filingDate) {
        this.filingDate = filingDate;
    }

    public String getFilingContent() {
        return filingContent;
    }

    public void setFilingContent(String filingContent) {
        this.filingContent = filingContent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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