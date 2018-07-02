package com.shoufubang.api.core;

import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shoufubang.api.wanjie.DesUtil;
import com.shoufubang.api.wanjie.WJHttpUtil;

/**
 * Hello world!
 *
 */
public class APIUtil {
	

	private static Logger logger = Logger.getLogger(APIUtil.class);
	
//	public static void main(String[] args) {
////		Map<String, String> map = new HashMap<String, String>();
////		map.put("userName", "尚立帅");
////		map.put("certCode", "410521199205100512");
////		map.put("telNO", "18600151712");
////		map.put("bankCertNO", "6222020200064820406");
////		ResponseResult responseResult = verifyBankCard4Param(map);
////		
////		System.out.println(responseResult.getCode() + "-" + responseResult.getMessage());
//		
//		String resultjson = "{\"data\":{\"checkResult\":\"00\"},\"message\":\"查询成功\"}";
//		
////		Gson gson = new Gson();
////		Map<String, String> resultmap = gson.fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
//		Map<String, String> resultmap = JSON.parseObject(resultjson, new TypeReference<Map<String, String>>() {});
//		
//		
//		System.out.println(resultmap);
//		
//		
//		
//	}
	
	
	/**
	 * 身份证认证
	 * @param requestParam 身份证认证参数
	 * "userName":"张三"
	 * "certCode":"110110198909890987"
	 * @return 认证结果
	 * responseResult.getCode().equals("2000"): 查询成功
	 * !responseResult.getCode().equals("2000"): 查询成功, 通过responseResult.getMessage()获取原因
	 * 
	 */
	public static ResponseResult idIdentity(Map<String, String> requestParam) {
		
		logger.info("身份证认证-请求参数:" + requestParam);
		
		ResponseResult responseResult = ParamValidate.idIdentityParamValidate(requestParam);
		
		if (responseResult.getCode().equals("2000")) {
			
			requestParam.put("authID", String.valueOf(new Random().nextInt(999999)));
			String desparams = DesUtil.getDesparam(APIConfig.APPKEY_WANJIE, requestParam);
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("appid=").append(APIConfig.APPID_WANJIE).append("&desparams=").append(desparams);
			
			String wjurl = APIConfig.WJ_URL + "/credit/IDidentity";
			
			String resultjson = WJHttpUtil.getSend(wjurl, buffer.toString());
			logger.info("万界接口返回结果:" + resultjson);
			
			Map<String, String> resultmap = JSON.parseObject(resultjson, new TypeReference<Map<String, String>>() {});
			String result = resultmap.get("result");
			
			if (!result.equals("0"))  responseResult = new ResponseResult("20001", resultmap.get("message"));
			
		}
		
		return responseResult;
	}
	
	/**
	 * 姓名手机身份证认证
	 * @param requestParam
	 * "userName":"张三"
	 * "certCode":"110110198909890987"
	 * "telNO":"15990878909"
	 * @return 认证结果
	 * responseResult.getCode().equals("2000"): 查询成功
	 * !responseResult.getCode().equals("2000"): 查询成功, 通过responseResult.getMessage()获取原因
	 */
	public static ResponseResult userIdentity(Map<String, String> requestParam) {
		logger.info("姓名手机身份证认证-请求参数:" + requestParam);
		
		ResponseResult responseResult = ParamValidate.userIdentityParamValidate(requestParam);
		
		if (responseResult.getCode().equals("2000")) {
			
			requestParam.put("authID", String.valueOf(new Random().nextInt(999999)));
			String desparams = DesUtil.getDesparam(APIConfig.APPKEY_WANJIE, requestParam);
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("appid=").append(APIConfig.APPID_WANJIE).append("&desparams=").append(desparams);
			
			String wjurl = APIConfig.WJ_URL + "/telphone/userIdentity";
			
			String resultjson = WJHttpUtil.getSend(wjurl, buffer.toString());
			logger.info("万界接口返回结果:" + resultjson);
			
			Map<String, String> resultmap = JSON.parseObject(resultjson, new TypeReference<Map<String, String>>() {});
			String result = resultmap.get("result");
			
			if (!result.equals("0"))  responseResult = new ResponseResult("20001", resultmap.get("message"));
			
		}
		
		return responseResult;
	}
	
	/**
	 * 银行卡四要素认证
	 * @param requestParam
	 * "userName":"张三"
	 * "certCode":"110110198909890987"
	 * "telNO":"15990878909"
	 * "bankCardNO":"62220202030398767890"
	 * @return 认证结果
	 * responseResult.getCode().equals("2000"): 查询成功
	 * !responseResult.getCode().equals("2000"): 查询成功, 通过responseResult.getMessage()获取原因
	 */
	public static ResponseResult verifyBankCard4Param(Map<String, String> requestParam) {
		logger.info("银行卡四要素认证-请求参数:" + requestParam);
		
		ResponseResult responseResult = ParamValidate.VerifyBankCard4ParamValidate(requestParam);
		
		if (responseResult.getCode().equals("2000")) {
			
			requestParam.put("authID", String.valueOf(new Random().nextInt(999999)));
			
			String desparams = DesUtil.getDesparam(APIConfig.APPKEY_WANJIE, requestParam);
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("appid=").append(APIConfig.APPID_WANJIE).append("&desparams=").append(desparams);
			
			String wjurl = APIConfig.WJ_URL + "/credit/VerifyBankCard4Param";
			
			String resultjson = WJHttpUtil.getSend(wjurl, buffer.toString());
			logger.info("万界接口返回结果:" + resultjson);
			
			Map<String, String> resultmap = JSON.parseObject(resultjson, new TypeReference<Map<String, String>>() {});
			String result = resultmap.get("result");
			
			if (!result.equals("0"))  responseResult = new ResponseResult("20001", resultmap.get("message"));
			
		}
		
		return responseResult;
	}
	
	
}
