package com.mad.projects.finance.domain.vo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class InvestmentInfo {

    private LocalDateTime latestNavUpdate;
    private Integer id;
    /**
     * name can be company name, scheme name
     */
    private String name;
    private BigDecimal amount;
    private BigDecimal currentValue;
    private BigDecimal earnings;

    public InvestmentInfo(Integer id, String name, BigDecimal amount, BigDecimal currentValue) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.currentValue = currentValue;
    }

    public InvestmentInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCurrentValue() {
        return currentValue.setScale(2, RoundingMode.HALF_UP);
    }

    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    public BigDecimal getEarnings() {
        return currentValue.subtract(amount).setScale(2, RoundingMode.HALF_UP);
    }

    public void setEarnings(BigDecimal earnings) {
        this.earnings = earnings;
    }

    public LocalDateTime getLatestNavUpdate() {
        return latestNavUpdate;
    }

    public InvestmentInfo setLatestNavUpdate(LocalDateTime latestNavUpdate) {
        this.latestNavUpdate = latestNavUpdate;
        return this;
    }
}