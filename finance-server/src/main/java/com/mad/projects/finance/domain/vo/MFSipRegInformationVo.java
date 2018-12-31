package com.mad.projects.finance.domain.vo;

import com.mad.projects.finance.db.entity.MFSipRegInformation;
import com.mad.projects.finance.db.entity.RecurringType;
import com.mad.projects.finance.util.DateUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MFSipRegInformationVo {
    private Integer id;

    private String userId;

    private MFSchemeVo schemeVo;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    private LocalDateTime registeredOn;

    private RecurringType recurringType;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private BigDecimal amount;

    private Integer sipDate;

    public Integer getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public MFSchemeVo getSchemeVo() {
        return schemeVo;
    }

    public String getCreatedOn() {
        return DateUtil.format(createdOn);
    }

    public String getUpdatedOn() {
        return DateUtil.format(updatedOn);
    }

    public String getRegisteredOn() {
        return DateUtil.format(registeredOn);
    }

    public RecurringType getRecurringType() {
        return recurringType;
    }

    public String getStartDate() {
        return DateUtil.format(startDate);
    }

    public LocalDateTime getLocalStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return DateUtil.format(endDate);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setSchemeVo(MFSchemeVo schemeVo) {
        this.schemeVo = schemeVo;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public void setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
    }

    public void setRecurringType(RecurringType recurringType) {
        this.recurringType = recurringType;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public static MFSipRegInformationVo build(MFSipRegInformation info) {
        MFSipRegInformationVo vo = new MFSipRegInformationVo();
        vo.setAmount(info.getAmount());
        vo.setEndDate(info.getEndDate());
        vo.setStartDate(info.getStartDate());
        vo.setRecurringType(info.getRecurringType());
        vo.setRegisteredOn(info.getRegisteredOn());
        vo.setUpdatedOn(info.getUpdatedOn());
        vo.setCreatedOn(info.getCreatedOn());
        vo.setSchemeVo(MFSchemeVo.build(info.getScheme()));
        vo.setUserId(info.getUserId());
        vo.setId(info.getId());

        return vo;
    }

    public Integer getSipDate() {
        return startDate.getDayOfMonth();
    }
}
