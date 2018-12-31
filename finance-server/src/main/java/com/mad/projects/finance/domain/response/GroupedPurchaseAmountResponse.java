package com.mad.projects.finance.domain.response;

import com.mad.projects.finance.domain.vo.InvestmentInfo;

import java.util.List;

public class GroupedPurchaseAmountResponse extends BaseResponse {

    List<InvestmentInfo> investments;

    public GroupedPurchaseAmountResponse() {
    }

    public List<InvestmentInfo> getInvestments() {
        return investments;
    }

    public void setInvestments(List<InvestmentInfo> investments) {
        this.investments = investments;
    }
}
