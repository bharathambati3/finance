package com.mad.projects.finance.domain.mapper.entity;

import com.mad.projects.finance.db.entity.FundType;
import com.mad.projects.finance.db.entity.MFScheme;
import com.mad.projects.finance.util.DateUtil;

import java.math.BigDecimal;

public class MFSchemeMapper {

    private Integer id;
    private String name;
    private FundType fundType;
    private Boolean isTaxSaving;
    private BigDecimal currentNav;
    private String createdOn;
    private String updatedOn;
    private String latestNavDate;
    private MFCompanyMapper company;

    public Integer getId() {
        return id;
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

    public BigDecimal getCurrentNav() {
        return currentNav;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public String getLatestNavDate() {
        return latestNavDate;
    }

    public MFCompanyMapper getCompany() {
        return company;
    }

    public static MFSchemeMapper map(MFScheme scheme) {
        MFSchemeMapper mapper = new MFSchemeMapper();

        mapper.id = scheme.getId();
        mapper.name = scheme.getName();
        mapper.fundType = scheme.getFundType();
        mapper.isTaxSaving = scheme.getTaxSaving();
        mapper.createdOn = DateUtil.format(scheme.getCreatedOn());
        mapper.updatedOn = DateUtil.format(scheme.getUpdatedOn());
        mapper.latestNavDate = DateUtil.format(scheme.getLatestNavDate());
        mapper.currentNav = scheme.getCurrentNav();
        mapper.company = MFCompanyMapper.map(scheme.getCompany());

        return mapper;
    }
}
