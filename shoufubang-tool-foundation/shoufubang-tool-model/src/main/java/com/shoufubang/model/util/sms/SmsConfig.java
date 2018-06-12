package com.shoufubang.model.util.sms;

import java.io.Serializable;

public class SmsConfig implements Serializable {
	private static final long serialVersionUID = 3500128507763416651L;

	private Long id;
	
	private String sendUrl;
	
	private String account;
	
	private String password;
	
	private String signName;
	
	private String portServer;
	
	private Long count;

	
	 

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSendUrl() {
		return sendUrl;
	}

	public void setSendUrl(String sendUrl) {
		this.sendUrl = sendUrl;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public String getPortServer() {
		return portServer;
	}

	public void setPortServer(String portServer) {
		this.portServer = portServer;
	}
}