package com.mad.projects.finance.domain.vo;

import com.mad.projects.finance.domain.mapper.entity.MFSipRegInformationMapper;
import com.mad.projects.finance.util.DateUtil;

import java.time.LocalDateTime;

public class MFSipPaymentVo {

    private MFSipRegInformationMapper info;
    private LocalDateTime paymentDate;

    public MFSipPaymentVo() {
    }

    public MFSipPaymentVo(MFSipRegInformationMapper info, LocalDateTime paymentDate) {
        this.info = info;
        this.paymentDate = paymentDate;
    }

    public MFSipRegInformationMapper getInfo() {
        return info;
    }

    public void setInfo(MFSipRegInformationMapper info) {
        this.info = info;
    }

    public String getPaymentDate() {
        return DateUtil.format(paymentDate);
    }

    public LocalDateTime paymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
