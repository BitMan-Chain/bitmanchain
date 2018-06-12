package com.shoufubang.model.util;

public class RemindInfo {
	
	private Long memberId;
	
	private String memberName;
	
	private String email;
	
	private Long phone;
	
	private Integer type;
	
	private Long cateId;
	
	private RemindParam param;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getCateId() {
		return cateId;
	}

	public void setCateId(Long cateId) {
		this.cateId = cateId;
	}

	public RemindParam getParam() {
		return param;
	}

	public void setParam(RemindParam param) {
		this.param = param;
	}
	
	
}
