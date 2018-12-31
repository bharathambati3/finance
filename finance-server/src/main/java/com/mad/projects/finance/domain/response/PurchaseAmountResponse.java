package com.mad.projects.finance.domain.response;

public class PurchaseAmountResponse extends BaseResponse {

    private String amount;

    public PurchaseAmountResponse() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
