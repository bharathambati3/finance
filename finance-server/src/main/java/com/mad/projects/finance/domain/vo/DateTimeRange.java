package com.mad.projects.finance.domain.vo;

import java.time.LocalDateTime;

public class DateTimeRange {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public DateTimeRange(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DateTimeRange() {
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public static DateTimeRange getCommonRange(DateTimeRange first, DateTimeRange second) {
        //take the highest start date.
        LocalDateTime startDate = (first.getStartDate().isAfter(second.getStartDate())) ? first.getStartDate() : second.getStartDate();
        //take the lowest end date.
        LocalDateTime endDate = (first.getEndDate().isBefore(second.getEndDate())) ? first.getEndDate() : second.getEndDate();

        if (endDate.isBefore(startDate)) {
            return null;
        } else {
            return new DateTimeRange(startDate, endDate);
        }
    }
}
