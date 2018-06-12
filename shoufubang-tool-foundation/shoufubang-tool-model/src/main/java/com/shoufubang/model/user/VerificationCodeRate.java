package com.shoufubang.model.user;

import java.util.Date;

public class VerificationCodeRate {
	
	private String id;
	private String mobile;
	private Date lastSendTime;
	private Byte numberOfTen;
	private Byte numberToday;
	private Date now;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getLastSendTime() {
		return lastSendTime;
	}

	public void setLastSendTime(Date lastSendTime) {
		this.lastSendTime = lastSendTime;
	}

	public Byte getNumberOfTen() {
		return numberOfTen;
	}

	public void setNumberOfTen(Byte numberOfTen) {
		this.numberOfTen = numberOfTen;
	}

	public Byte getNumberToday() {
		return numberToday;
	}

	public void setNumberToday(Byte numberToday) {
		this.numberToday = numberToday;
	}

	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}
}