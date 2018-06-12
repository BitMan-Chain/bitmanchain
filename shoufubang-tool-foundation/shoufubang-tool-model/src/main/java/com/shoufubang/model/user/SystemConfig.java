package com.shoufubang.model.user;

import java.math.BigDecimal;

public class SystemConfig {
    private String id;

    private Byte allowPlatformWithdrawal;

    private Integer redeemFreeCount;

    private BigDecimal redeemPoundageRate;

    private BigDecimal redeemMinPoundage;

    private Integer dayCount;

    private BigDecimal award;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Byte getAllowPlatformWithdrawal() {
        return allowPlatformWithdrawal;
    }

    public void setAllowPlatformWithdrawal(Byte allowPlatformWithdrawal) {
        this.allowPlatformWithdrawal = allowPlatformWithdrawal;
    }

    public Integer getRedeemFreeCount() {
        return redeemFreeCount;
    }

    public void setRedeemFreeCount(Integer redeemFreeCount) {
        this.redeemFreeCount = redeemFreeCount;
    }

    public BigDecimal getRedeemPoundageRate() {
        return redeemPoundageRate;
    }

    public void setRedeemPoundageRate(BigDecimal redeemPoundageRate) {
        this.redeemPoundageRate = redeemPoundageRate;
    }

    public BigDecimal getRedeemMinPoundage() {
        return redeemMinPoundage;
    }

    public void setRedeemMinPoundage(BigDecimal redeemMinPoundage) {
        this.redeemMinPoundage = redeemMinPoundage;
    }

    public Integer getDayCount() {
        return dayCount;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }

    public BigDecimal getAward() {
        return award;
    }

    public void setAward(BigDecimal award) {
        this.award = award;
    }
}