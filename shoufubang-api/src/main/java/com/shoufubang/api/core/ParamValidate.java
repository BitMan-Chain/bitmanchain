package com.shoufubang.api.core;

import java.util.Map;

public class ParamValidate {

	private static ResponseResult paramValidate(Map<String, String> requestParam) {
		ResponseResult responseResult = ResponseResult.getResponseResult(true);
		if (requestParam == null || requestParam.size() == 0) {
			responseResult = ResponseResult.getResponseResult(false);
		}
		return responseResult;
	}
	
	
	
	/**
	 * 身份证认证参数校验器
	 * 
	 * @param requestParam
	 */
	public static ResponseResult idIdentityParamValidate(Map<String, String> requestParam) {
		
		ResponseResult responseResult = paramValidate(requestParam);
		
		if (!responseResult.getCode().equals("2000")) return responseResult;
		
		String userName = requestParam.get("userName");
		String certCode = requestParam.get("certCode");
		
		if (userName == null || "".equals(userName)) {
			responseResult = new ResponseResult("2001", "姓名参数输入不合法");
			return responseResult;
		}
		
		if (certCode == null || "".equals(certCode)) {
			responseResult = new ResponseResult("2001", "身份证号码参数输入不合法");
			return responseResult;
		}
		
		return responseResult;
		
	}


	/**
	 * 姓名手机身份证认证-参数校验
	 * @param requestParam
	 * @return
	 */
	public static ResponseResult userIdentityParamValidate(Map<String, String> requestParam) {
		ResponseResult responseResult = idIdentityParamValidate(requestParam);
		
		if (!responseResult.getCode().equals("2000")) return responseResult;
		
		String telNO = requestParam.get("telNO");
		if (telNO == null || "".equals(telNO)) {
			responseResult = new ResponseResult("2001", "手机号码参数输入不合法");
			return responseResult;
		}
		
		return responseResult;
	}


	/**
	 * 银行卡四要素认证-参数校验
	 * @param requestParam
	 * @return
	 */
	public static ResponseResult VerifyBankCard4ParamValidate(Map<String, String> requestParam) {
		ResponseResult responseResult = userIdentityParamValidate(requestParam);
		
		String bankCardNO = requestParam.get("bankCardNO");
		if (bankCardNO == null || "".equals(bankCardNO)) {
			responseResult = new ResponseResult("2001", "银行卡号参数输入不合法");
			return responseResult;
		}
		
		return responseResult;
	}

}
