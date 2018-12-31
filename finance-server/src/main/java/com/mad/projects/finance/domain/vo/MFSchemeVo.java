package com.mad.projects.finance.domain.vo;

import com.mad.projects.finance.db.entity.FundType;
import com.mad.projects.finance.db.entity.MFScheme;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MFSchemeVo {

    private Integer id;

    private MFCompanyVo company;

    private String name;

    private FundType fundType;

    private Boolean isTaxSaving;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    private BigDecimal currentNav;

    private LocalDateTime latestNavDate;

    private String amfiCode;

    public Integer getId() {
        return id;
    }

    public MFCompanyVo getCompany() {
        return company;
    }

    public String getName() {
        return name;
    }

    public FundType getFundType() {
        return fundType;
    }

    public Boolean getTaxSaving() {
        return isTaxSaving;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public BigDecimal getCurrentNav() {
        return currentNav;
    }

    public LocalDateTime getLatestNavDate() {
        return latestNavDate;
    }

    public String getAmfiCode() {
        return amfiCode;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCompany(MFCompanyVo company) {
        this.company = company;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFundType(FundType fundType) {
        this.fundType = fundType;
    }

    public void setTaxSaving(Boolean taxSaving) {
        isTaxSaving = taxSaving;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public void setCurrentNav(BigDecimal currentNav) {
        this.currentNav = currentNav;
    }

    public void setLatestNavDate(LocalDateTime latestNavDate) {
        this.latestNavDate = latestNavDate;
    }

    public void setAmfiCode(String amfiCode) {
        this.amfiCode = amfiCode;
    }

    public static MFSchemeVo build(MFScheme scheme) {
        MFSchemeVo mfSchemeVo = new MFSchemeVo();

        mfSchemeVo.setId(scheme.getId());
        mfSchemeVo.setAmfiCode(scheme.getAmfiCode());
        mfSchemeVo.setLatestNavDate(scheme.getLatestNavDate());
        mfSchemeVo.setCurrentNav(scheme.getCurrentNav());
        mfSchemeVo.setUpdatedOn(scheme.getUpdatedOn());
        mfSchemeVo.setCreatedOn(scheme.getCreatedOn());
        mfSchemeVo.setTaxSaving(scheme.getTaxSaving());
        mfSchemeVo.setFundType(scheme.getFundType());
        mfSchemeVo.setName(scheme.getName());
        mfSchemeVo.setCompany(MFCompanyVo.build(scheme.getCompany()));

        return mfSchemeVo;
    }
}
