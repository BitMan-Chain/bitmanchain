package com.shoufubang.model.user;

import java.util.Date;

public class VerificationCode {
	private String id;

	private String userId;

	private String phone;

	private Byte codeuse;

	private String verificationCode;

	private Byte codeType;//1:短信、2:图片、3:语音
	private Date createTime;
	private Date endTime;
	private String email;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Byte getCodeuse() {
		return codeuse;
	}

	public void setCodeuse(Byte codeuse) {
		this.codeuse = codeuse;
	}

	public Byte getCodeType() {
		return codeType;
	}

	public void setCodeType(Byte codeType) {
		this.codeType = codeType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}