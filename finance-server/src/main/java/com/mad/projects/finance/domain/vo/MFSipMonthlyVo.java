package com.mad.projects.finance.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MFSipMonthlyVo {
    private MFSchemeVo scheme;
    private LocalDate sipDate;
    private BigDecimal amount;
    private BigDecimal purchaseNav;
    private LocalDateTime investedOn;

    public void setScheme(MFSchemeVo scheme) {
        this.scheme = scheme;
    }

    public MFSchemeVo getScheme() {
        return scheme;
    }

    public LocalDate getSipDate() {
        return sipDate;
    }

    public void setSipDate(LocalDate sipDate) {
        this.sipDate = sipDate;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setPurchaseNav(BigDecimal purchaseNav) {
        this.purchaseNav = purchaseNav;
    }

    public BigDecimal getPurchaseNav() {
        return purchaseNav;
    }

    public void setInvestedOn(LocalDateTime investedOn) {
        this.investedOn = investedOn;
    }

    public LocalDateTime getInvestedOn() {
        return investedOn;
    }
}
