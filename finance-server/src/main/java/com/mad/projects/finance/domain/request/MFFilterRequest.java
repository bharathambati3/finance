package com.mad.projects.finance.domain.request;

import com.mad.projects.finance.db.entity.FundType;
import com.mad.projects.finance.db.entity.InvestmentType;
import com.mad.projects.finance.domain.vo.DateTimeRange;

public class MFFilterRequest {
    private String userId;
    private Integer companyId;
    private Integer schemeId;
    private FundType fundType;
    private InvestmentType investmentType;
    private DateTimeRange dateTimeRange;
    private Boolean taxSaving;

    public MFFilterRequest() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCompanyId() {
        if (companyId != null && companyId == 0) {
            return null;
        }
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

    public DateTimeRange getDateTimeRange() {
        return dateTimeRange;
    }

    public void setDateTimeRange(DateTimeRange dateTimeRange) {
        this.dateTimeRange = dateTimeRange;
    }

    public Boolean getTaxSaving() {
        return taxSaving;
    }

    public void setTaxSaving(Boolean taxSaving) {
        this.taxSaving = taxSaving;
    }

    public InvestmentType getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(InvestmentType investmentType) {
        this.investmentType = investmentType;
    }
}
