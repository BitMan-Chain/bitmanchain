package com.shoufubang.model.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrhMsg {

	private int code = 0;
	private String msg = "";
	private String url = "";
	private String verson = "1.0";

	private List<Object> data = new ArrayList<>();
	private Map<String, Object> mapData = new HashMap<>();

	public String getUrl() {
		return url;
	}

	public Map<String, Object> getMapData() {
		return mapData;
	}

	public void setMapData(Map<String, Object> mapData) {
		this.mapData = mapData;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public List<Object> getData() {
		return data;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}

	public String getVerson() {
		return verson;
	}

	public void setVerson(String verson) {
		this.verson = verson;
	}

	public void setCodeAndMsg(int code, String msg) {
		setCode(code);
		setMsg(msg);
	}

}
