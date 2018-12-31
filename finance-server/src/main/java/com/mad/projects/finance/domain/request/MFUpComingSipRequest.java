package com.mad.projects.finance.domain.request;

import com.mad.projects.finance.domain.vo.DateTimeRange;

public class MFUpComingSipRequest {
    private String userId;
    private Integer upComingSip;
    private DateTimeRange range;

    public MFUpComingSipRequest() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUpComingSip() {
        return upComingSip;
    }

    public void setUpComingSip(Integer upComingSip) {
        this.upComingSip = upComingSip;
    }

    public DateTimeRange getRange() {
        return range;
    }

    public void setRange(DateTimeRange range) {
        this.range = range;
    }
}
