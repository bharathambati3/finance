package com.mad.projects.finance.db.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "mf_investment")
public class MFInvestment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userId;

    @ManyToOne
    @JoinColumn(name = "scheme_id")
    private MFScheme scheme;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    private LocalDateTime purchasedOn;

    private LocalDateTime investedOn;

    private BigDecimal amount;

    private BigDecimal purchaseNav;

    @Column(columnDefinition = "enum('LUMPSUM', 'SIP')")
    @Enumerated(EnumType.STRING)
    private InvestmentType investmentType;

    public MFInvestment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public MFScheme getScheme() {
        return scheme;
    }

    public void setScheme(MFScheme scheme) {
        this.scheme = scheme;
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

    public InvestmentType getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(InvestmentType investmentType) {
        this.investmentType = investmentType;
    }
}
