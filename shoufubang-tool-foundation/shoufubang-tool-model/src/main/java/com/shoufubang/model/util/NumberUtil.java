package com.shoufubang.model.util;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

public final class NumberUtil {
	
	
	private static final Integer NUM_ROUND = 10000;
	/**
	 * 判断是否为手机号
	 * @param str
	 * @return
	 */
	public static boolean isPhone(String str) {
		
		if(StringUtils.isEmpty(str)) {
			return false;
		}
		str = str.trim();
        return str.length() == 11 && NumberUtils.isNumber(str);
	}
	
	
	/**判断是否为数字
	 * @param
	 */
	public static Boolean isNumber(Object obj) {
		if (null==obj) {
			return false;
		}
		
		return Pattern.compile("([1-9]+[0-9]*|0)(\\.[\\d]+)?").matcher(obj.toString()).matches();
	}
	
	
	public static int toInt(Object o,int nDefault)
	{
		if(o==null) return nDefault;
		try
		{
			if(o instanceof String) return  Integer.parseInt((String)o); 
			if(o instanceof Number)   return ((Number)o).intValue();
			return Integer.parseInt((String)o.toString());
		  
			 
		}
		catch(Exception e)
		{
			return nDefault;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(toInt(new Double(22), 0));
	}
	
	public static Double toDouble(Object o,Double nDefault)
	{
		if(o==null) return nDefault;
		try
		{
			if(o instanceof String) return  Double.parseDouble((String)o); 
			if(o instanceof Number)   return ((Number)o).doubleValue();
			return Double.parseDouble((String)o.toString());
		  
			 
		}
		catch(Exception e)
		{
			return nDefault;
		}
	}
	
	public static int toInt(String s) {
		return NumberUtils.toInt(s, -1);
	}
	
	
	public static Long getMathRound() {
		
		return  Math.round(NUM_ROUND * Math.random());
	}

}
