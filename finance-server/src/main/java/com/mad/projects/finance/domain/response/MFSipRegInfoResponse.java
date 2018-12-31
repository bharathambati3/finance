package com.mad.projects.finance.domain.response;

import com.mad.projects.finance.domain.vo.MFSipRegInformationVo;

import java.math.BigDecimal;
import java.util.List;

public class MFSipRegInfoResponse extends BaseResponse {
    private List<MFSipRegInformationVo> infos;
    private BigDecimal totalAmount;
    int count;

    public MFSipRegInfoResponse() {
    }

    public List<MFSipRegInformationVo> getInfos() {
        return infos;
    }

    public void setInfos(List<MFSipRegInformationVo> infos) {
        this.infos = infos;
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
