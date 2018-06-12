package com.shoufubang.model.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	private static final String CHARSET_UTF_8 = "UTF-8";
	private static final Pattern URL = Pattern.compile(
		"^((https|http|ftp|rtsp|mms)?://)" 
	     + "+(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" 
	     + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" 
	     + "|" 
	     + "([0-9a-z_!~*'()-]+\\.)*" 
	     + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." 
	     + "[a-z]{2,6})" 
	     + "(:[0-9]{1,4})?" 
	     + "((/?)|" 
	     + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$", Pattern.CASE_INSENSITIVE
	);
	private static final Pattern EMAIL = Pattern.compile("^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$");
	private static final Pattern NUMERIC = Pattern.compile("^[0-9]+$");
	private static final Pattern MONEY = Pattern.compile("^[0-9]+$|^[0-9]+\\.[0-9]{1,6}$");
	private static final Pattern CH_CHAR = Pattern.compile("^[\u4e00-\u9fa5]+$");
	private static final Pattern PHONE = Pattern.compile("^1[3|4|5|7|8]([0-9])\\d{8}$");
	private static final Pattern TELPHONE = Pattern.compile("^(0[0-9]{2,4}-?[0-9]{7,8})|(1[3|4|5|7|8][0-9]{9})$");
	
	public static boolean checkUrl(String url) {
		Matcher m = URL.matcher(url);
		return m.matches();
	}
	
	/**
	 * 验证邮箱
	 * @param email
	 * @return
	 */
	public static Boolean checkEmail(String email) {
        Matcher m = EMAIL.matcher(email);     
        return m.matches();
	}
	
	/**
	 * 验证手机号
	 * @param phone
	 * @return
	 */
	public static Boolean checkPhone(String phone) {
        Matcher m = PHONE.matcher(phone);     
        return m.matches();
	}
	
	/**
	 * 验证固话和手机
	 */
	public static Boolean checkTelPhone(String tel){
		Matcher m = TELPHONE.matcher(tel);     
        return m.matches();
	}
	
	/**
	 * 是否是中文字符
	 * @param str
	 * @return
	 */
	public static boolean is_chinese(String str) {
		if (str == null) {
			return false;
		}
		Matcher result = CH_CHAR.matcher(str);
		return result.matches();
	}
	
	/**
	 * 
	 * @Description: 判断字符串是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {  
        Matcher isNum = NUMERIC.matcher(str);  
        if (isNum.matches()) {  
            return true;  
        } else {  
            return false;  
        }  
    }
	
	/**
	 * 是否为金额
	 * @param str
	 * @return
	 */
	public static boolean isMoney(String str) {
		return MONEY.matcher(str).matches();
	}
	
	/**
	 * 字符串按指定符号分割为数组
	 * 
	 * @param str
	 * @param Symbol
	 * @return
	 * @throws Exception 
	 */
	public static String[] split(String str, String symbol) throws Exception {
		if (str.length() < 1 || symbol.length() < 1) {
			try {
				throw new Exception("字符错误");
			} catch (Exception e) {
				throw new Exception(e);
			}
		}
		String[] strSplit = str.split(symbol);
		return strSplit;
	}
	
	/**
	 * 是否为空
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return str == null || ((str.trim().length()) == 0) || "null".equals(str);
	}

	/**
	 * 是否不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}
	
	/**
	 * 生成32位的唯一序列号
	 * @return
	 */
	public static String genenrateUniqueInd() {
		return Md5Util.encode(UUID.randomUUID().toString());
	}
	
	/**
	 * 生成订单序列号
	 * 日期加上(currentNum+1) pg: 201506160001
	 * @param curNumIndex
	 * @return
	 */
	public static String generateSerialno(int currentNum) {
		String nextNumStr = String.valueOf(currentNum + 1);
		while (nextNumStr.length() < 4) {
			nextNumStr = "0" + nextNumStr;
		}
		return TimeUtil1.getCurrentDateStr() + nextNumStr;
	}
	
	/**
	 * 将list转成String,并按impStr进行分隔
	 * @param objList
	 * @param impStr
	 * @return
	 */
	public static String importStr(List<?> objList, String impStr) {
		String resultStr = "";
		for (Object obj : objList) {
			if (obj == null) {
				continue;
			}
			if ("".equals(resultStr)) {
				resultStr = obj.toString();
			} else {
				resultStr += impStr + obj.toString();
			}
		}
		return resultStr;
	}
	
	/**
	 * 生成16位订单序列号
	 * 日期加上(currentNum+1) pg: 201506160001
	 * @param curNumIndex
	 * @return
	 */
	public static String getSerialno() {
		String base = UUID.randomUUID().toString();
		Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < 16; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	}
	
	public static List<String> getImageSrc(String htmlStr) {
		Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
		Matcher m = p.matcher(htmlStr);
		List<String> srcs = new ArrayList<String>();
		while (m.find()) {
			srcs.add(m.group(1));
		}
		return srcs;
	}
	
	/**
	 * 将对象转化成String 进行比较
	 * @param fstObj
	 * @param secObj
	 * @return
	 */
	public static boolean isEqual(Object fstObj, Object secObj) {
		if (fstObj == null && secObj == null) {
			return true;
		}
		if (fstObj == null || secObj == null) {
			return false;
		}
		return fstObj.toString().equals(secObj.toString());
	}
	
	/**
	 * 截取limitLength长度的字符串
	 * @param str
	 * @param limitLength
	 * @return
	 */
	public static String subString(String str, int limitLength) {
		return isNotBlank(str) && str.length() > limitLength ? str.substring(0, limitLength) : str;
	}
	
	/**
	 * 字符串编码
	 * @param sStr
	 * @param sEnc
	 * @return String
	 */
	public static String encode(String sStr, String sEnc) {
		String sReturnCode = sStr;
		try {
			sReturnCode = URLEncoder.encode(sStr, sEnc);
		} catch (UnsupportedEncodingException ex) {}
		return sReturnCode;
	}
	
	/**
	 * 字符串解码
	 * @param sStr
	 * @param sEnc
	 * @return String
	 */
	public static String decode(String sStr, String sEnc) {
		String sReturnCode = sStr;
		try {
			sReturnCode = URLDecoder.decode(sStr, sEnc);
		} catch (UnsupportedEncodingException ex) {}
		return sReturnCode;
	}
	/**
	 * 去除html标签
	 * @param sStr
	 * @param sEnc
	 * @return String
	 */
	public static String delHtml(String str){
		String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
        Pattern p_script=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(str); 
        return m_script.replaceAll("").trim(); //过滤script标签 
	}
	  public static String f(String format, Object... args) {
			return String.format(format, args);
		}
	  public static String format(String time) {
	    	if (null == time) {
				return null;
			}
	    	if(time.indexOf("-")==-1) {
				return time;
			}
	    	return time.substring(0,10).replaceAll("-", "");
	    }
	    
	    public static String decode(String value) {
	    	if (empty(value)) {
				return "";
			}
	    	try {
	    		String decode = URLDecoder.decode(value, CHARSET_UTF_8);
	    		return decode;
			} catch (Exception e) {
				return "";
			}
	    }
	    @SuppressWarnings({"rawtypes", "unused"})
	    public static<T> boolean empty(T obj) {
	        boolean empty=false;
	        if(null==obj)return true;

	        if(obj instanceof String) {
	            String value=(String)obj;
	            if(null==value||"".equals(value))empty=true;
	            else
	                empty=false;
	        }else if(obj instanceof Map) {
	            Map map=(Map)obj;
	            if(null==map||map.size()<1||map.isEmpty())empty=true;
	            else
	                empty=false;
	        }else if(obj instanceof List) {
	            List list=(List)obj;
	            if(null==list||list.size()<1)empty=true;
	            else
	                empty=false;
	        }else if(obj instanceof  String[]) {
	            String[] values=(String[])obj;
	            if(null==values||values.length<1)empty=true;
	        }else if(obj instanceof  Integer) {
	            Integer v=(Integer)obj;
	            if(null==v || 0 == v)empty=true;
	            else
	                empty=false;
	        }
	        return empty;
	    }
	    public static String getEncoding(String str) {      
		       String encode = "GB2312";      
		      try {      
		          if (str.equals(new String(str.getBytes(encode), encode))) {      
		               String s = encode;      
		              return new String(str.getBytes("GB2312"), "UTF-8").toString();      
		           }      
		       } catch (Exception exception) {      
		       }      
		       encode = "ISO-8859-1";      
		      try {      
		          if (str.equals(new String(str.getBytes(encode), encode))) {      
		               String s1 = encode;      
		              return new String(str.getBytes("ISO-8859-1"), "UTF-8").toString();      
		           }      
		       } catch (Exception exception1) {      
		       }      
		       encode = "UTF-8";      
		      try {      
		          if (str.equals(new String(str.getBytes(encode), encode))) {      
		               String s2 = encode;      
		              return str;      
		           }      
		       } catch (Exception exception2) {      
		       }      
		       encode = "GBK";      
		      try {      
		          if (str.equals(new String(str.getBytes(encode), encode))) {      
		               String s3 = encode;      
		              return str;      
		           }      
		       } catch (Exception exception3) {      
		       }      
		      return "";      
		   }
	    public static String getStrFormat(String value , String format) {
	    	try {
				return new String(value.getBytes(format), CHARSET_UTF_8);
			} catch (Exception e) {
				return "";
			}
	    }
	    
	    public static String appendFromMap(Map<String, String> map) {
	    	StringBuffer append = new StringBuffer();
	    	if (!empty(map)) {
	    		int index = 0;
	    		for(Entry<String, String> entry :map.entrySet()) {
	    			if(index < map.size() - 1) {
	    				append.append(entry.getKey()).append("=").append(entry.getValue()).append("|");
	    			}else {
	    				append.append(entry.getKey()).append("=").append(entry.getValue());
	    			}
	    			
	    			index ++;
	    		}
			}
	    	return append.toString();
	    }
	    public static String[]  splitString (String str) {
	    	String[] newString = str.split("\\.");
			return newString;
	    }
	    public static  String  isNumber(String str){  
	        String reg = "^[0-9]+(.[0-9]+)?$"; 
	        if(str.matches(reg)){
	        	str=str.substring(0, str.lastIndexOf("."));
	        	
	        }
	        return str;  
	    }  
}

