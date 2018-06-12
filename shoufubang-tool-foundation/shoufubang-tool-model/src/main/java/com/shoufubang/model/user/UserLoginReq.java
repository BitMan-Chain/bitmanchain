/*  
 * @(#) UserLoginReq.java Create on 2015年12月2日 下午1:55:02   
 *   
 * Copyright 2015 by xl.   
 */


package com.shoufubang.model.user;

/**
 * 用户登录请求VO
 * @author wanggh
 * @date   2015年12月2日
 */
public class UserLoginReq {
	private String terminal;//
	private String channel;//
	private String action;//
	private String channelUid;//
	private String mobile;//
	private String password;//
	private String verificationCode;//v
	private String inviteFrom;//
	private String ip;//
	private String token;//
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getChannelUid() {
		return channelUid;
	}
	public void setChannelUid(String channelUid) {
		this.channelUid = channelUid;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	public String getInviteFrom() {
		return inviteFrom;
	}
	public void setInviteFrom(String inviteFrom) {
		this.inviteFrom = inviteFrom;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
