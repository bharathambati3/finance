package com.mad.projects.finance.domain.request;

import com.mad.projects.finance.db.entity.InvestmentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class MFInvestmentRequest {

    @NotNull
    private Integer schemeId;
    @NotNull
    private LocalDateTime purchasedOn;
    @NotNull
    private LocalDateTime investedOn;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private BigDecimal purchaseNav;
    @NotNull
    private InvestmentType type;
    @NotNull
    private String userId;

    public MFInvestmentRequest() {
    }

    public Integer getSchemeId() {
        return schemeId;
    }
    
    public void setSchemeId(Integer schemeId) {
        this.schemeId = schemeId;
    }

    public LocalDateTime getPurchasedOn() {
        return purchasedOn;
    }

    public void setPurchasedOn(LocalDateTime purchasedOn) {
        this.purchasedOn = purchasedOn;
    }

    public LocalDateTime getInvestedOn() {
        return investedOn;
    }

    public void setInvestedOn(LocalDateTime investedOn) {
        this.investedOn = investedOn;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPurchaseNav() {
        return purchaseNav;
    }

    public void setPurchaseNav(BigDecimal purchaseNav) {
        this.purchaseNav = purchaseNav;
    }

    public InvestmentType getType() {
        return type;
    }

    public void setType(InvestmentType type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
