package com.mad.projects.finance.domain.request;

import com.mad.projects.finance.db.entity.FundType;
import com.mad.projects.finance.domain.vo.DateTimeRange;

public class MFSipInfoFilterRequest {

    private String userId;
    private Integer companyId;
    private Integer schemeId;
    private FundType fundType;
    private DateTimeRange registeredDateRange;
    private Boolean taxSaving;
    private Integer sipDate;

    public MFSipInfoFilterRequest() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Integer schemeId) {
        this.schemeId = schemeId;
    }

    public FundType getFundType() {
        return fundType;
    }

    public void setFundType(FundType fundType) {
        this.fundType = fundType;
    }

    public DateTimeRange getRegisteredDateRange() {
        return registeredDateRange;
    }

    public void setRegisteredDateRange(DateTimeRange registeredDateRange) {
        this.registeredDateRange = registeredDateRange;
    }

    public Boolean getTaxSaving() {
        return taxSaving;
    }

    public void setTaxSaving(Boolean taxSaving) {
        this.taxSaving = taxSaving;
    }

    public Integer getSipDate() {
        return sipDate;
    }

    public void setSipDate(Integer sipDate) {
        this.sipDate = sipDate;
    }
}
