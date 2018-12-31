package com.mad.projects.finance.domain.mapper.entity;

import com.mad.projects.finance.db.entity.MFSipRegInformation;
import com.mad.projects.finance.db.entity.RecurringType;
import com.mad.projects.finance.util.DateUtil;

import java.math.BigDecimal;

public class MFSipRegInformationMapper {

    private Integer id;
    private String userId;
    private MFSchemeMapper scheme;
    private RecurringType recurringType;
    private String createdOn;
    private String updatedOn;
    private String registeredOn;
    private String startDate;
    private String endDate;
    private BigDecimal amount;

    public Integer getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public MFSchemeMapper getScheme() {
        return scheme;
    }

    public RecurringType getRecurringType() {
        return recurringType;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public static MFSipRegInformationMapper map(MFSipRegInformation info) {
        MFSipRegInformationMapper mapper = new MFSipRegInformationMapper();
        mapper.amount = info.getAmount();
        mapper.recurringType = info.getRecurringType();
        mapper.id = info.getId();
        mapper.userId = info.getUserId();
        mapper.scheme = MFSchemeMapper.map(info.getScheme());
        mapper.createdOn = DateUtil.format(info.getCreatedOn());
        mapper.endDate = DateUtil.format(info.getEndDate());
        mapper.startDate = DateUtil.format(info.getStartDate());
        mapper.updatedOn = DateUtil.format(info.getUpdatedOn());
        mapper.registeredOn = DateUtil.format(info.getRegisteredOn());

        return mapper;
    }
}
