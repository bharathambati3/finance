package com.mad.projects.finance.domain.request;

import com.mad.projects.finance.domain.vo.DateTimeRange;

public class MFSipPayableFilterRequest {

    private String userId;
    private DateTimeRange payableSipInDateRange;

    public MFSipPayableFilterRequest() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public DateTimeRange getPayableSipInDateRange() {
        return payableSipInDateRange;
    }

    public void setPayableSipInDateRange(DateTimeRange payableSipInDateRange) {
        this.payableSipInDateRange = payableSipInDateRange;
    }
}
