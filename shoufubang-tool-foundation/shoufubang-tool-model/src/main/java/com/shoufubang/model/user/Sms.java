package com.shoufubang.model.user;



import java.util.Date;

public class Sms {
    private String id;

    private String userId;

    private String mobile;

    private String message;

    private Date sendTime;

    private Byte tradeType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

	public Byte getTradeType() {
		return tradeType;
	}

	public void setTradeType(Byte tradeType) {
		this.tradeType = tradeType;
	}
}