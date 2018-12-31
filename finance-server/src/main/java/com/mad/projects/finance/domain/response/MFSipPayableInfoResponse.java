package com.mad.projects.finance.domain.response;

import com.mad.projects.finance.domain.vo.MFSipPayableVo;

import java.math.BigDecimal;
import java.util.List;

public class MFSipPayableInfoResponse extends BaseResponse{

    private List<MFSipPayableVo> payableVos;
    private BigDecimal totalAmount;
    int count;

    public MFSipPayableInfoResponse() {
    }

    public List<MFSipPayableVo> getPayableVos() {
        return payableVos;
    }

    public void setPayableVos(List<MFSipPayableVo> payableVos) {
        this.payableVos = payableVos;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
