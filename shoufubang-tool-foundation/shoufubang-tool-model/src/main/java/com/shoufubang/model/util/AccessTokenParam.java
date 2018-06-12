package com.shoufubang.model.util;

import java.util.Arrays;

public class AccessTokenParam {

	@Override
	public String toString() {
		return "AccessTokenParam [key=" + key + ", mac=" + mac + ", date="
				+ date + ", deskey=" + deskey + ", sp=" + Arrays.toString(sp)
				+ ", accesstoken=" + accesstoken + "]";
	}
	private String key ="" ;
	private String mac ="" ;
	private long date ;
	private long deskey ;
	private String[] sp ;
	private String accesstoken  ="" ;
	public String getAccesstoken() {
		return accesstoken;
	}
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public long getDeskey() {
		return deskey;
	}
	public void setDeskey(long deskey) {
		this.deskey = deskey;
	}
	public String[] getSp() {
		return sp;
	}
	public void setSp(String[] sp) {
		this.sp = sp;
	}
}
