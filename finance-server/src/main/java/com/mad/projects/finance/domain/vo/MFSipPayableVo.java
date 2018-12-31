package com.mad.projects.finance.domain.vo;

import com.mad.projects.finance.db.entity.MFSipRegInformation;

import java.time.LocalDateTime;
import java.util.List;

public class MFSipPayableVo {
    private MFSipRegInformation info;
    private List<LocalDateTime> paymentDates;

    public MFSipPayableVo() {
    }

    public MFSipRegInformation getInfo() {
        return info;
    }

    public void setInfo(MFSipRegInformation info) {
        this.info = info;
    }

    public List<LocalDateTime> getPaymentDates() {
        return paymentDates;
    }

    public void setPaymentDates(List<LocalDateTime> paymentDates) {
        this.paymentDates = paymentDates;
    }
}
