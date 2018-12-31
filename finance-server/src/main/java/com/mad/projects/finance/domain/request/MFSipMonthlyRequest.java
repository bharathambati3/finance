package com.mad.projects.finance.domain.request;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

public class MFSipMonthlyRequest {

    private String userId;
    private Year year;
    private Month month;

    public MFSipMonthlyRequest() {
    }

    public String getUserId() {
        return userId;
    }

    public Year getYear() {
        if (year == null) {
            return Year.now();
        }
        return year;
    }

    public Month getMonth() {
        if (month == null) {
            return LocalDate.now().getMonth();
        }
        return month;
    }
}
