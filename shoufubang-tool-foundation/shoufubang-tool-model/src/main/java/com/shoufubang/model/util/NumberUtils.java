package com.shoufubang.model.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberUtils {
	
	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
	public static final BigDecimal ONE_PERCENT = new BigDecimal(0.01);
	/**
	 * 格式化成保留两位小数,三位一逗号
	 */
	public static final DecimalFormat FORMATER = new DecimalFormat("#,##0.00");
	/**
	 * 格式化成保留两位小数,无逗号
	 */
	public static DecimalFormat FORMAT = new DecimalFormat("0.00"); 
	
	/**
	 * 格式化两位小数加三位一逗号
	 * @param num
	 * @return
	 */
	public static String format(BigDecimal num) {
		return FORMATER.format(num);
	}

	/**
	 * 按指定format进行格式化
	 * @param num
	 * @param format
	 * @return
	 */
	public static String format(BigDecimal num, DecimalFormat format) {
		return format.format(num);
	}
	
	/**
	 * 如果是空返回0
	 * @param num
	 * @return
	 */
	public static BigDecimal exNull(BigDecimal num) {
		return num != null ? num : BigDecimal.ZERO;
	}
	
	/**
	 * 加法
	 * @param nums
	 * @return
	 */
	public static BigDecimal add(BigDecimal... nums) {
		BigDecimal totalAmount = BigDecimal.ZERO;
		for (BigDecimal num : nums) {
			if (num != null) {
				totalAmount = totalAmount.add(num);
			}
		}
		return totalAmount;
	}
	
	/**
	 * 减法
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static BigDecimal sub(BigDecimal num1, BigDecimal num2) {
		return (num1 != null ? num1 : BigDecimal.ZERO).subtract(num2 != null ? num2 : BigDecimal.ZERO);
	}
	
	/**
	 * 乘法
	 * @param nums
	 * @return
	 */
	public static BigDecimal mul(BigDecimal... nums) {
		BigDecimal totalAmount = BigDecimal.ZERO;
		int count = 0;
		for (BigDecimal num : nums) {
			if (num == null || BigDecimal.ZERO.compareTo(num) == 0) {
				return BigDecimal.ZERO;
			}
			if (count == 0) {
				totalAmount = num;
			} else {
				totalAmount = totalAmount.multiply(num);
			}
			count++;
		}
		return totalAmount;
	}
	
	/**
	 * 除法
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static BigDecimal div(BigDecimal num1, BigDecimal num2) {
		if (num1 == null || num2 == null) {
			return BigDecimal.ZERO;
		}
		return num1.divide(num2, 6, BigDecimal.ROUND_HALF_DOWN);
	}
	
	/**
	 * 除法
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static BigDecimal div(BigDecimal num1, BigDecimal num2, int scale) {
		if (num1 == null || num2 == null || num1.compareTo(BigDecimal.ZERO) == 0 || num2.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		}
		return num1.divide(num2, scale, BigDecimal.ROUND_HALF_DOWN);
	}

	/**
	 * 处理小数点位数，默认保留两位小数
	 * @param num
	 * @return
	 */
	public static BigDecimal round(BigDecimal num) {
		return round(num, 2);
	}
	
	/**
	 * 四舍五入
	 * @param value
	 *  @param scale 小数点后num位
	 * @return
	 */
	public static BigDecimal round(BigDecimal num, int scale) {
		return num == null ? BigDecimal.ZERO : num.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	
	/**
	 * 处理小数点位数，默认为小数点后两位
	 * @param num
	 * @return
	 */
	public static Double round(Double num) {
		return round(num, 2);
	}
	
	/**
	 * 处理小数点位数
	 * @param num
	 * @param scale 小数点后num位
	 * @return
	 */
	public static Double round(Double num, int scale) {
		return num == null ? 0d : round(new BigDecimal(num), scale).doubleValue();
	}

	/**
	 * 向上取整
	 * @param value
	 * @return
	 */
	public static BigDecimal ceilNumber(BigDecimal num) {
		return num == null ? BigDecimal.ZERO : num.setScale(0, BigDecimal.ROUND_CEILING);
	}
	
	/**
	 * 判断是否大于0
	 * @param value
	 * @return
	 */
	public static boolean greaterThanZero(BigDecimal num) {
		return num == null ? false : num.compareTo(BigDecimal.ZERO) > 0;
	}
	
	/**
	 * 判断是否大于等于0
	 * @param value
	 * @return
	 */
	public static boolean greaterEqualZero(BigDecimal num) {
		return num == null ? false : num.compareTo(BigDecimal.ZERO) > 0;
	}
	
	/**
	 * 判断是否大于
	 * @param value
	 * @return
	 */
	public static boolean greaterThan(BigDecimal num1, BigDecimal num2) {
		if (num1 == null) {
			return false;
		}
		return num1.compareTo(num2 != null ? num2 : BigDecimal.ZERO) > 0;
	}

	
	/**
	 * 判断是否大于等于
	 * @param value
	 * @return
	 */
	public static boolean greaterEqual(BigDecimal num1, BigDecimal num2) {
		if (num1 == null) {
			return false;
		}
		return num1.compareTo(num2 != null ? num2 : BigDecimal.ZERO) >= 0;
	}

	
	/**
	 * 判断是否小于
	 * @param value
	 * @return
	 */
	public static boolean lessThan(BigDecimal num1, BigDecimal num2) {
		if (num1 == null) {
			return false;
		}
		return num1.compareTo(num2 != null ? num2 : BigDecimal.ZERO) < 0;
	}
	/**
	 * 将double类型的数据格式化成字符串表示,保留两位小数
	 * @param d
	 * @return
	 */
	public static String format(Double d) {
		if(d==null) return null;
		return NumberUtils.format(new BigDecimal(d),NumberUtils.FORMAT);
	}
	
	/**
	 * 将object类型对象转换为double类型
	 * @param obj 要转换成double类型的对象
	 * @param decimal 保留的小数位数
	 * @return
	 */
	public static double toDouble(Object obj, int decimal) {
		if(decimal<0) {
			throw new IllegalArgumentException("illegal decimal value ["+decimal+"]");
		}
		String s = "0";
		if(obj!=null) {
			s = obj.toString().trim();
		}
		if(s.length()==0) {
			s = "0";
		}
		BigDecimal bg = new BigDecimal(s);
		return bg.setScale(decimal, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 将object类型对象转换为double类型,保留两位小数
	 * @param obj 要转换成double类型的对象
	 * @return
	 */
	public static double toDouble(Object obj) {
		return toDouble(obj, 2);
	}
	public static void main(String[] args) {
//		System.out.println(NumberUtils.mul(BigDecimal.ONE, new BigDecimal(1000),new BigDecimal(898)));
//		System.out.println(NumberUtils.format(100d));
		
		getRandomNum(0.5, 2.0, 4, 4);
		
		
	}
	
	
	
	/**
	 * @param min  小数最小值
	 * @param max  小数最大值
	 * @param scl  小数最大位数
	 * @param cnt  产生数量
	 * @return
	 */
	public static Map<String, Object> getRandomNum(Double min,Double max,int scl,int cnt) {
		Map<String, Object> listRandomNum = new HashMap<String,Object>();
		Map<String, Object> map = new HashMap<String,Object>();
	    int    pow = (int) Math.pow(10, scl); // 用于提取指定小数位
	    System.out.println(pow);
	    double sum =  0; // 用于验证总和
	    double sumone = 0;
	    double one = 0 ;

	    for (int i = 0; i < cnt; i ++) {
	        if ( i <= cnt - 1 ) {
	            // min~max 指定小数位的随机数
	            one = Math.floor(((Math.random() * (max-min))+min) * pow) / pow;
	           
	            
	        } 
	        sum += one;
	        listRandomNum.put(GetUtils.sixCode(), one);
	      
	        // 输出
	        //System.out.printf("%.4f\r\n", one);
	    }
	    
	    
	    // 验证
	    System.out.println(sum);
		return listRandomNum;
	}	
	
	
	
}
