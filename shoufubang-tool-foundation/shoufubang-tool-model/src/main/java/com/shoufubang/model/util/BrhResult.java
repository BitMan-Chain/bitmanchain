package com.shoufubang.model.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 返回信息
 * @author star
 *
 */
public class BrhResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 该方法是否出错
	 */
	private boolean isSuccess=true;
	private String  message="";
	private List<String> errors=new ArrayList<String>();
	/**
	 * 参数map
	 */
	private Map<String,Object> params = new HashMap<String, Object>();
	/**
	 * 结果map
	 */
	private Map<String,Object> results = new HashMap<String, Object>();
	
	
//	public BrhResult() {
//		params.put("tablePrefix", Global.htzConfig.get("tableprefix"));
//	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public Map<String, Object> getResults() {
		return results;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public void setResults(Map<String, Object> results) {
		this.results = results;
	}
	
	
}

