package com.mad.projects.finance.domain.vo;

import com.mad.projects.finance.db.entity.InvestmentType;
import com.mad.projects.finance.util.DateUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class IndividualInvestmentInfo {
    private Integer id;
    private String name;
    private BigDecimal amount;
    private BigDecimal currentValue;
    private BigDecimal earnings;
    private InvestmentType investmentType;
    private LocalDateTime investedOn;
    private Period investmentPeriod;
    private LocalDateTime purchasedOn;
    private BigDecimal purchaseNav;
    private BigDecimal currentNav;

    public IndividualInvestmentInfo() {
    }

    public Integer getId() {
        return id;
    }

    public IndividualInvestmentInfo setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public IndividualInvestmentInfo setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getAmount() {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    public IndividualInvestmentInfo setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public BigDecimal getCurrentValue() {
        return currentValue.setScale(2, RoundingMode.HALF_UP);
    }

    public IndividualInvestmentInfo setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
        return this;
    }

    public BigDecimal getEarnings() {
        return currentValue.subtract(amount).setScale(2, RoundingMode.HALF_UP);
    }

    public void setEarnings(BigDecimal earnings) {
        this.earnings = earnings;
    }

    public InvestmentType getInvestmentType() {
        return investmentType;
    }

    public IndividualInvestmentInfo setInvestmentType(InvestmentType investmentType) {
        this.investmentType = investmentType;
        return this;
    }

    public String getInvestedOn() {
        return DateUtil.format(investedOn);
    }

    public IndividualInvestmentInfo setInvestedOn(LocalDateTime investedOn) {
        this.investedOn = investedOn;
        return this;
    }

    public String getInvestmentPeriod() {
        int between = (int) ChronoUnit.DAYS.between(investedOn, LocalDateTime.now());

        StringBuilder sb = new StringBuilder();
        int years = 0;
        int remDays = between;
        int daysInYear = 365;
        if (between > daysInYear) {
            years = between/daysInYear;
            remDays = between % daysInYear;
        }

        if (years > 0) {
            if (years == 1) {
                sb.append("1 year ");
            } else {
                sb.append(years).append(" years ");
            }
        }
        if (remDays == 1) {
            sb.append(remDays).append(" day");
        } else {
            sb.append(remDays).append(" days");
        }
        return sb.toString();
    }

    public void setInvestmentPeriod(Period investmentPeriod) {
        this.investmentPeriod = investmentPeriod;
    }

    public BigDecimal getPurchaseNav() {
        return purchaseNav;
    }

    public IndividualInvestmentInfo setPurchaseNav(BigDecimal purchaseNav) {
        this.purchaseNav = purchaseNav;
        return this;
    }

    public BigDecimal getCurrentNav() {
        return currentNav;
    }

    public IndividualInvestmentInfo setCurrentNav(BigDecimal currentNav) {
        this.currentNav = currentNav;
        return this;
    }

    public LocalDateTime getPurchasedOn() {
        return purchasedOn;
    }

    public IndividualInvestmentInfo setPurchasedOn(LocalDateTime purchasedOn) {
        this.purchasedOn = purchasedOn;
        return this;
    }
}
