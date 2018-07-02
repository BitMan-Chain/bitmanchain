package com.shoufubang.api.core;

/**
 * 响应结果
 * 
 * @author shanglishuai
 *
 */
public class ResponseResult {

	public String code;		// 响应码
	public String message;	// 响应消息

	public ResponseResult() {
		
	}
	
	/**
	 * 构造函数
	 * @param code
	 * @param message
	 */
	public ResponseResult(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public static ResponseResult getResponseResult(boolean isNormal) {
		ResponseResult responseResult = isNormal ? 
				new ResponseResult("2000", "操作成功") : new ResponseResult("2001", "操作失败");
		return responseResult;
	}
	
}
