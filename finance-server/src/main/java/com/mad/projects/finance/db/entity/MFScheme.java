package com.mad.projects.finance.db.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "mf_schemes")
public class MFScheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private MFCompany company;

    private String name;

    @Column(columnDefinition = "enum('LARGE_CAP', 'MID_CAP', 'SMALL_CAP', 'MULTI_CAP', 'HYBRID', 'ELSS')")
    @Enumerated(EnumType.STRING)
    private FundType fundType;

    private Boolean isTaxSaving;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    private BigDecimal currentNav;

    private LocalDateTime latestNavDate;

    private String amfiCode;

    public MFScheme() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public MFCompany getCompany() {
        return company;
    }

    public void setCompany(MFCompany company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FundType getFundType() {
        return fundType;
    }

    public void setFundType(FundType fundType) {
        this.fundType = fundType;
    }

    public Boolean getTaxSaving() {
        return isTaxSaving;
    }

    public void setTaxSaving(Boolean taxSaving) {
        isTaxSaving = taxSaving;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public BigDecimal getCurrentNav() {
        return currentNav;
    }

    public void setCurrentNav(BigDecimal currentNav) {
        this.currentNav = currentNav;
    }

    public LocalDateTime getLatestNavDate() {
        return latestNavDate;
    }

    public void setLatestNavDate(LocalDateTime latestNavDate) {
        this.latestNavDate = latestNavDate;
    }

    public String getAmfiCode() {
        return amfiCode;
    }

    public void setAmfiCode(String amfiCode) {
        this.amfiCode = amfiCode;
    }

    @Override
    public String toString() {
        return "MFScheme{" +
                "id=" + id +
                ", company=" + company +
                ", name='" + name + '\'' +
                ", fundType=" + fundType +
                ", isTaxSaving=" + isTaxSaving +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", currentNav=" + currentNav +
                ", latestNavDate=" + latestNavDate +
                ", amfiCode='" + amfiCode + '\'' +
                '}';
    }
}
