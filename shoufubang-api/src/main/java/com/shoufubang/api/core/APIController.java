package com.shoufubang.api.core;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class APIController {
	
	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", "尚立帅");
		map.put("certCode", "410521199205100512");
		map.put("telNO", "18600151712");
		map.put("bankCertNO", "6222020200064820406");
		ResponseResult responseResult = APIUtil.verifyBankCard4Param(map);
		
		System.out.println(responseResult.getCode() + "-" + responseResult.getMessage());
		
		return responseResult.getCode() + "-" + responseResult.getMessage();
	}

}
