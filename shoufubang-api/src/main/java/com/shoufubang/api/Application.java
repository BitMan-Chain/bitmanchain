package com.shoufubang.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shoufubang.api.core.APIUtil;
import com.shoufubang.api.core.ResponseResult;

@Controller
@EnableAutoConfiguration
public class Application {
	
	@RequestMapping("/")
    @ResponseBody
    String home(){
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("userName", "尚立帅");
//		map.put("certCode", "410521199205100512");
//		map.put("telNO", "18600151712");
//		map.put("bankCardNO", "6222020200064820406");
//		ResponseResult responseResult = APIUtil.verifyBankCard4Param(map);
//		
//		System.out.println(responseResult.getCode() + "-" + responseResult.getMessage());
		
//		return responseResult.getCode() + "-" + responseResult.getMessage();
		
		
		return "Hello world!!!!";
		
    }
	
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}
