package com.shoufubang.model.util;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;




public class GetUtils {
	
	/**
	 * 获取request
	 * @return
	 */

	
	
	/**
	 * 获取用户登陆IP
	 * @return
	 * @throws Exception
	 */
	 public static String getIpAddr(HttpServletRequest request) {
	        String ip = request.getHeader("X-Real-IP");
	        if (null != ip && !"".equals(ip.trim())
	                && !"unknown".equalsIgnoreCase(ip)) {
	            return ip;
	        }
	        ip = request.getHeader("X-Forwarded-For");
	        if (null != ip && !"".equals(ip.trim())
	                && !"unknown".equalsIgnoreCase(ip)) {
	            // get first ip from proxy ip
	            int index = ip.indexOf(',');
	            if (index != -1) {
	                return ip.substring(0, index);
	            } else {
	                return ip;
	            }
	        }
	        return request.getRemoteAddr();
	    }
	
	/**
	 * 六位数字验证码
	 * @return
	 */
	public static String sixCode() {
		String result = "";
		for (int i = 0; i < 6; i++) {
			result += new Random().nextInt(10);
		}
		return result;
	}
	
	/**
	 * 数字字母验证码
	 */
    public static String getStringRandom(int length) {  
          
        String val = "";  
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
              
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }  
}
