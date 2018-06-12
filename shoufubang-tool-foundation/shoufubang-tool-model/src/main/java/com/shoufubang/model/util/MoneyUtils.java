package com.shoufubang.model.util;



import org.apache.commons.lang3.math.NumberUtils;

import htz.util.format.MoneyTransferUtil;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;


/**
 * Created by Administrator on 2015/7/15.
 */
public class MoneyUtils {
	
	private static final Double COMPARE_MONEY = 0.0;
	private static final Double POINT = 100.0;
	private static final int SCALE = 2;
	/** 大写数字 */
	private static final String[] NUMBERS = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
	/** 整数部分的单位 */
	private static final String[] IUNIT = { "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟" };
	/** 小数部分的单位 */
	private static final String[] DUNIT = { "角", "分", "厘" };


	public static BigDecimal stringToBigDecimal(String money) {
        if(StringUtils.empty(money))return BigDecimal.ZERO;
        money=money.replaceAll("[,]", "");
        BigDecimal bigDecimal;
        bigDecimal = new BigDecimal(money);
        bigDecimal=bigDecimal.multiply(new BigDecimal(1), MathContext.UNLIMITED).setScale(2, BigDecimal.ROUND_HALF_UP);
        return bigDecimal;
    }
    
    
    /**
	 * 格式化小数，若小数为0，则删除0，若小数大于0则保留
	 * @param object
	 * @return
	 */
	public static String formatDecimal(Object object) {
		// TODO Auto-generated method st ub
		long result = 0;
		if(!StringUtils.empty(object)) {
			double temp = 0;
			try{
				temp = Double.parseDouble(object.toString());
			}catch(Exception e) {
				return result+"";
			}
			if(temp % 1.0 == 0) {
				result = (long)temp;
			}else{
				return temp+"";
			}
		}
		return result+"";
	}
	
	
	public static String toStr(boolean v) {
		return Boolean.toString(v);
	}
	public static String toStr(int v) {
		return Integer.toString(v);
	}
	public static String toStr(long v) {
		return Long.toString(v);
	}
	public static String toStr(double v) {
		return Double.toString(v);
	}
	public static String toStr(Object o) {
		return o == null ? "" : String.valueOf(o);
	}
	
	public static double toDouble(String s) {
		return NumberUtils.toDouble(s, -1);
	}
	public static double toDouble(String s, double def) {
		return NumberUtils.toDouble(s, def);
	}
	public static double toDouble(Object obj) {
		return toDouble(obj, -1);
	}
	public static double toDouble(Object obj, double def) {
		if (obj == null) return def;
		if (obj instanceof String) return NumberUtils.toDouble((String) obj, def);
		if (obj instanceof Number) return ((Number) obj).doubleValue();
		throw new IllegalArgumentException("invalid type for toDouble: " + obj.getClass().getCanonicalName());
	}
	
	public static String retainNoDecimalStr(Object obj) {
		 String temp = "";
		 if(!StringUtils.empty(obj))
		 {
			 double db = toDouble(obj,0);
			 temp = retainNoDecimalStr(db);
		 }
		return temp;
	}
	
	/**
	 * 用圆圈圈展示项目进度时，使用，取整
	 * 0<d<1 返回1 ；99<d<100 返回99 ； 其余截取小数部分
	 */
	public static String doProgess(Double progress) {
		if(toDouble("0")<progress && progress<toDouble("1")) {
			return toStr(1);
		}else if(toDouble("99")<progress && progress<toDouble("100")) {
			return toStr(99);
		}
		return retainNoDecimalStr(progress);
	}
	
	/**
	 * 大于0的double，转换成人民币，整数部分每3位 ","分割，保留两位小数，第三位小数四位五入
	 */
	public static String getRMB(double d) {
		return MoneyTransferUtil.getRMB(d);
	}
	
	


	/**
	 * @Description 对于double数据截去小数
	 * @param d 
	 * @return 返回String
	 */
	public static String retainNoDecimalStr(double d) {
		BigDecimal b = new BigDecimal(d);
		BigDecimal df = new BigDecimal(b.setScale(0, BigDecimal.ROUND_DOWN).doubleValue());//直接删除多余的小数位
		DecimalFormat dformat = new DecimalFormat("#");
		String temp=dformat.format(df);
		return temp;
	}
	
	/**
	 * @Description 对于double数据保留两位小数,第三位小数四舍五入
	 * @param d 
	 * @return 返回String
	 */
	public static String retainTwoDecimalStr(double d) {
		BigDecimal b = new BigDecimal(d);
		BigDecimal df = new BigDecimal(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		DecimalFormat dformat = new DecimalFormat("#.00");
		String temp=dformat.format(df);
		if(temp.startsWith(".")) {
			return "0"+temp;
		}
		return temp;
	}
	
	/**
	 * 对于double数据保留两位小数,第三位小数四舍五入
	 * @param d 
	 * @return 返回double型 	 * 注意，可能会返回科学计数法的样式，如2.13000099E12
	 */
	public static double retainTwoDecimal(double d) {
		BigDecimal b = new BigDecimal(d);
		BigDecimal df = new BigDecimal(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		DecimalFormat dformat = new DecimalFormat("#0.00");
		return Double.parseDouble(dformat.format(df));
	}
	
	/**
	 * 保留一位小数不进行四舍五入
	 * @param d
	 * @return
	 */
	public static String retainOneDecimalStr(double d) {
		BigDecimal b = new BigDecimal(d);
		BigDecimal df = new BigDecimal(b.setScale(1, BigDecimal.ROUND_DOWN).doubleValue());//直接删除多余的小数位
		DecimalFormat dformat = new DecimalFormat("#0.0");
		String temp=dformat.format(df);
		return temp;
	}
	
	/**
	 * 格式化字符串 保留n位小数，不足补0
	 * @param rateStr
	 * @return
	 */
	public static String formateString(String rateStr, int n) {  
		if(rateStr.indexOf(".") != -1) {  
			for(int i=0;i<n;i++) {
				rateStr = rateStr + "0";
			}
			
			//获取小数点的位置  
			int num = 0;  
			num = rateStr.indexOf(".");  
			
			//获取小数点后面的数字 是否有两位 不足两位补足两位  
			String dianAfter = rateStr.substring(0,num+1);      //整数部分+小数点
			String afterData = rateStr.replace(dianAfter, "");  //小数部分
			
			return rateStr.substring(0,num) + "." + afterData.substring(0,n);  
		}else{  
			for(int i=0;i<n;i++) {
				if(i==0) {
					rateStr = rateStr + ".";
				}
				rateStr = rateStr + "0";
			}
			return rateStr;  
		}  
	}  
	/**
	 * 格式化字符串 每隔三个加","
	 * @return
	 */
	public static String formateString(String str) {  
		String subStrC="";
	    if(str.length()>3) {
			String subStr=str;
			
			int f=str.length()-1;
			int count=0;
			while(f>=0) {
				count++;
				subStrC=subStr.charAt(f)+subStrC;
				if(count==3) {
					subStrC=","+subStrC;
					count=0;
				}
			    f--;	
			}
		}else{
			subStrC=str;
		}
	    
	    if(subStrC.length()>1&&subStrC.charAt(0)==',') {
	    	subStrC=subStrC.substring(1);
	    }
	    
		return subStrC;
	}
	
	/**
	 * double数据向下取整
	 * @param d
	 * @return
	 */
	 public static int floor(double d) {
	  if (Math.abs(d - Math.round(d)) < (1.0e-6)) {
	   d = Math.round(d);
	  }
	  return (int) Math.floor(d);
	 }

	 
	 /**
	  * 系统中钱是按照分计算
	  * @param amount
	  * @return
	  */
	public static int to_cents(double amount) {
		return  (int) Math.round(amount * 100);
	}
	/**
	 * eg：10.0万
	 * @param money
	 * @return
	 */
	public static String limitToStr(Double money) {
		if (money % 10000 == 0)
			return (money / 10000) + "万";
		return money + "元";
	}
	/**
	 * eg：10万
	 * @param money
	 * @return
	 */
	public static String limitToStr2(Double money) {
		if (money % 10000 == 0)
			return (int)(money / 10000) + "万";
		return money.intValue() + "元";
	}

	public static Boolean CompareTo(Double remainMoney) {
		if (remainMoney.compareTo(COMPARE_MONEY) < 0) {
			return true;
		}
		return false;
	}
	
	//将分转换成元
	public static Double pointsToUnit(int money) {
		
		BigDecimal pointMal = new BigDecimal(money);
		
		BigDecimal Unit = pointMal.divide(new BigDecimal(POINT), SCALE, BigDecimal.ROUND_HALF_UP);
		
		return Unit.doubleValue();
	}


	/**
	 * 得到大写金额。
	 */
	public static String toChineseMoney(String str) {
		str = str.replaceAll(",", "");// 去掉","
		String integerStr;// 整数部分数字
		String decimalStr;// 小数部分数字

		// 初始化：分离整数部分和小数部分
		if (str.indexOf(".") > 0) {
			integerStr = str.substring(0, str.indexOf("."));
			decimalStr = str.substring(str.indexOf(".") + 1);
		} else if (str.indexOf(".") == 0) {
			integerStr = "";
			decimalStr = str.substring(1);
		} else {
			integerStr = str;
			decimalStr = "";
		}
		// integerStr去掉首0，不必去掉decimalStr的尾0(超出部分舍去)
		if (!integerStr.equals("")) {
			integerStr = Long.toString(Long.parseLong(integerStr));
			if (integerStr.equals("0")) {
				integerStr = "";
			}
		}
		// overflow超出处理能力，直接返回
		if (integerStr.length() > IUNIT.length) {
			System.out.println(str + ":超出处理能力");
			return str;
		}

		int[] integers = toArray(integerStr);// 整数部分数字
		boolean isMust5 = isMust5(integerStr);// 设置万单位
		int[] decimals = toArray(decimalStr);// 小数部分数字
		return getChineseInteger(integers, isMust5) + getChineseDecimal(decimals);
	}

	/**
	 * 整数部分和小数部分转换为数组，从高位至低位
	 */
	private static int[] toArray(String number) {
		int[] array = new int[number.length()];
		for (int i = 0; i < number.length(); i++) {
			array[i] = Integer.parseInt(number.substring(i, i + 1));
		}
		return array;
	}

	/**
	 * 得到中文金额的整数部分。
	 */
	private static String getChineseInteger(int[] integers, boolean isMust5) {
		StringBuffer chineseInteger = new StringBuffer("");
		int length = integers.length;
		for (int i = 0; i < length; i++) {
			// 0出现在关键位置：1234(万)5678(亿)9012(万)3456(元)
			// 特殊情况：10(拾元、壹拾元、壹拾万元、拾万元)
			String key = "";
			if (integers[i] == 0) {
				if ((length - i) == 13)// 万(亿)(必填)
					key = IUNIT[4];
				else if ((length - i) == 9)// 亿(必填)
					key = IUNIT[8];
				else if ((length - i) == 5 && isMust5)// 万(不必填)
					key = IUNIT[4];
				else if ((length - i) == 1)// 元(必填)
					key = IUNIT[0];
				// 0遇非0时补零，不包含最后一位
				if ((length - i) > 1 && integers[i + 1] != 0)
					key += NUMBERS[0];
			}
			chineseInteger.append(integers[i] == 0 ? key
					: (NUMBERS[integers[i]] + IUNIT[length - i - 1]));
		}
		return chineseInteger.toString();
	}

	/**
	 * 得到中文金额的小数部分。
	 */
	private static String getChineseDecimal(int[] decimals) {
		StringBuffer chineseDecimal = new StringBuffer("");
		for (int i = 0; i < decimals.length; i++) {
			// 舍去3位小数之后的
			if (i == 3)
				break;
			chineseDecimal.append(decimals[i] == 0 ? ""
					: (NUMBERS[decimals[i]] + DUNIT[i]));
		}
		return chineseDecimal.toString();
	}

	/**
	 * 判断第5位数字的单位"万"是否应加。
	 */
	private static boolean isMust5(String integerStr) {
		int length = integerStr.length();
		if (length > 4) {
			String subInteger = "";
			if (length > 8) {
				// 取得从低位数，第5到第8位的字串
				subInteger = integerStr.substring(length - 8, length - 4);
			} else {
				subInteger = integerStr.substring(0, length - 4);
			}
			return Integer.parseInt(subInteger) > 0;
		} else {
			return false;
		}
	}



    public static void main(String[] args) {
    	double value = stringToBigDecimal("100").doubleValue();
    	System.out.println(limitToStr2(value));
    }
}
