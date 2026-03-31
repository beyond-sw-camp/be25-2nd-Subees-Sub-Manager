package com.subees.subscription.consumption.model.vo;

public enum BillingCycle {
    ONE_WEEK("1W"),
    ONE_MONTH("1M"),
    SIX_MONTH("6M"),
    ONE_YEAR("1Y");

    private final String code;

    BillingCycle(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
