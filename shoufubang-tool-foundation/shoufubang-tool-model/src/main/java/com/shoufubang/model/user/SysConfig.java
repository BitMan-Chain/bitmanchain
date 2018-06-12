package com.shoufubang.model.user;

import java.math.BigDecimal;

public class SysConfig
{
    private String id;

    private String allowPlatformWithdrawal;
    private Integer redeemFreeCount;
    private Double redeemPoundageRate;
    private Double redeemMinPoundage;
    private BigDecimal interestPoundageRate;
    private Integer dayCount;
    private Double award;
    private byte smsLimitSwitch;
    private byte smsVerifiactionCode;

    public Integer getRedeemFreeCount() {
        return redeemFreeCount;
    }

    public void setRedeemFreeCount(Integer redeemFreeCount) {
        this.redeemFreeCount = redeemFreeCount;
    }

    public Double getRedeemPoundageRate() {
        return redeemPoundageRate;
    }

    public void setRedeemPoundageRate(Double redeemPoundageRate) {
        this.redeemPoundageRate = redeemPoundageRate;
    }

    public Double getRedeemMinPoundage() {
        return redeemMinPoundage;
    }

    public void setRedeemMinPoundage(Double redeemMinPoundage) {
        this.redeemMinPoundage = redeemMinPoundage;
    }

    public Integer getDayCount() {
        return dayCount;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }

    public Double getAward() {
        return award;
    }

    public void setAward(Double award) {
        this.award = award;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAllowPlatformWithdrawal() {
        return allowPlatformWithdrawal;
    }

    public void setAllowPlatformWithdrawal(String allowPlatformWithdrawal) {
        this.allowPlatformWithdrawal = allowPlatformWithdrawal;
    }

	public byte getSmsLimitSwitch() {
		return smsLimitSwitch;
	}

	public void setSmsLimitSwitch(byte smsLimitSwitch) {
		this.smsLimitSwitch = smsLimitSwitch;
	}

	public BigDecimal getInterestPoundageRate() {
		return interestPoundageRate;
	}

	public void setInterestPoundageRate(BigDecimal interestPoundageRate) {
		this.interestPoundageRate = interestPoundageRate;
	}

	public byte getSmsVerifiactionCode() {
		return smsVerifiactionCode;
	}

	public void setSmsVerifiactionCode(byte smsVerifiactionCode) {
		this.smsVerifiactionCode = smsVerifiactionCode;
	}
}
