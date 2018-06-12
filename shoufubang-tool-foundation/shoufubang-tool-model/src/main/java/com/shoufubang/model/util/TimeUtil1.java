package com.shoufubang.model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



/**
 * TimeUtil1
 * @author shuaili 2016-6-2
 *
 */
public class TimeUtil1 {
	public static long getNowTimestampMillis(){
		long curTime = System.currentTimeMillis();
		return curTime;
	}
	public static int getNowTimestampSecond(){
		long curTime = System.currentTimeMillis()/1000;
		return (int)curTime;
	}
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATE_FORMAT2 = "yyyy-MM";
	public static final String DATE_SHORT_FORMAT = "yyyyMMdd";
	public static final String DATE_SLASH_FORMAT = "yyyy/MM/dd";
	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String TIME_SHORT_FORMAT="yyyyMMddHHmmss";
	
	//改成线程安全方式20150916
	private static ThreadLocal<SimpleDateFormat> dateFormat = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(DATE_FORMAT);
		}
	};
	
	//改成线程安全方式20150916
	private static ThreadLocal<SimpleDateFormat> dateFormat2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(DATE_FORMAT2);
		}
	};
	
	private static ThreadLocal<SimpleDateFormat> dateShortFormat = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(DATE_SHORT_FORMAT);
		}
	};
	
	private static ThreadLocal<SimpleDateFormat> dateTimeFormat = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(TIME_FORMAT);
		}
	};
	
	private static ThreadLocal<SimpleDateFormat> dateFormatSlash = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(DATE_SLASH_FORMAT);
		}
	};
	private static ThreadLocal<SimpleDateFormat> TimeShortFormat = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(TIME_SHORT_FORMAT);
		}
	};
	
	
	/**
	 * <pre>dateTimeAdd(时间加法：当前时间为date类型，加 毫秒数)   
	 * 创建人：彭榉 pengju918@sina.com    
	 * 创建时间：2017年1月9日 下午3:11:19    
	 * 修改人：彭榉 pengju918@sina.com     
	 * 修改时间：2017年1月9日 下午3:11:19    
	 * 修改备注： 
	 * @param date 当前时间
	 * @param longTime 追加的毫秒数
	 * @return</pre> 
	 */
	public static String dateTimeAdd(Date date,long longTime) {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  Date now = new Date();
		  Date afterDate = new Date(now .getTime() + longTime);
		return sdf.format(afterDate);
	}
	
	public static String dateTime2Add(Date date,long longTime) {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		  Date now = new Date();
		  Date afterDate = new Date(now .getTime() + longTime);
		return sdf.format(afterDate);
	}
	
	/**
	 * Date --> String(yyyy-MM-dd)
	 * @param date
	 * @return
	 */
	public static String dateFormat(Date date) {
		if (date == null) {
			return "";
		}

		return dateFormat.get().format(date);
	}
	
	/**
	 * <pre>dateFormat3(yyyyMMddHHmmss时间戳字符串)   
	 * 创建人：邓恒dengheng@163.com  
	 * 创建时间：2017年3月30日 下午3:52:03    
	 * 修改人：邓恒dengheng@163.com    
	 * 修改时间：2017年3月30日 下午3:52:03    
	 * 修改备注： 
	 * @param date
	 * @return</pre>
	 */
	public static String dateFormat3(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
	/**
	 * <pre>dateFormatString(MMddHHmmss时间戳字符串)   
	 * 创建人：engheng@163.com  
	 * 创建时间：2017年3月30日 下午3:52:03    
	 * 修改人：邓恒dengheng@163.com    
	 * 修改时间：2017年3月30日 下午3:52:03    
	 * 修改备注： 
	 * @param date
	 * @return</pre>
	 */
	
	public static String dateFormatString(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
		return sdf.format(date);
	}
	
	/**
	 * <pre>dateFormat(yyyy-MM)   
	 * 创建人：彭榉 pengju918@sina.com    
	 * 创建时间：2017年1月12日 下午1:54:21    
	 * 修改人：彭榉 pengju918@sina.com     
	 * 修改时间：2017年1月12日 下午1:54:21    
	 * 修改备注： 
	 * @param date
	 * @return</pre>
	 */
	public static String dateFormat2(Date date) {
		if (date == null) {
			return "";
		}
		
		return dateFormat2.get().format(date);
	}
	
	/**
	 * Double/Integer/Long --> String(yyyy-MM-dd)
	 * @param time
	 * @return
	 */
	public static String dateFormat(Object time){
		if(time == null) return "";
		
		Long value = null;
		if(time instanceof Double) {
			value = ((Double)time).longValue();
		} else if(time instanceof Integer) {
			value = ((Integer)time).longValue();
		} else {
			value = (Long) time;
		}
		if(value.toString().length() == 10) value = value*1000;
		
		return dateFormat.get().format(new Date(value));
	}
	
	/**
	 * Double/Integer/Long --> String(yyyyMMdd)
	 * @param time
	 * @return
	 */
	public static String dateShortFormat(Object time) {
		if (time == null)
			return "";

		Long value = null;
		if (time instanceof Double) {
			value = ((Double) time).longValue();
		} else if (time instanceof Integer) {
			value = ((Integer) time).longValue();
		} else {
			value = (Long) time;
		}
		if (value.toString().length() == 10) {
			value = value * 1000;
		}

		return dateShortFormat.get().format(new Date(value));
	}
	
	/**
	 * Double/Integer/Long --> String(yyyy/MM/dd)
	 * @param time
	 * @return
	 */
	public static String dateFormatSlash(Object time){
		if(time == null) return "";
		
		Long value = null;
		if(time instanceof Double) {
			value = ((Double)time).longValue();
		} else if(time instanceof Integer) {
			value = ((Integer)time).longValue();
		} else {
			value = (Long) time;
		}
		if(value.toString().length() == 10) value = value*1000;
		
		return dateFormatSlash.get().format(new Date(value));
	}
	
	/**
	 * Date --> String(yyyy-MM-dd HH:mm:ss)
	 * @param date
	 * @return
	 */
	public static String dateTimeFormat(Date date) {
		if (date == null){
			return "";
		}
		return dateTimeFormat.get().format(date);
	}
	
	/**
	 * Double/Integer/Long --> String(yyyy-MM-dd HH:mm:ss)
	 * @param time
	 * @return
	 */
	public static String dateTimeFormat(Object time){
		if(time == null) return "";
		
		Long value = null;
		if(time instanceof Double) {
			value = ((Double)time).longValue();
		} else if(time instanceof Integer) {
			value = ((Integer)time).longValue();
		} else {
			value = (Long) time;
		}
		
		if(value.toString().length() == 10) value = value*1000;
		
		return dateTimeFormat.get().format(new Date(value));
	}
	
	@SuppressWarnings("deprecation")
	public static Date dateParse(Long date) {
		if(date == null) return null;
		
		Date newDate = new Date(date*1000);
		newDate.setHours(0);
		newDate.setMinutes(0);
		newDate.setSeconds(0);
		
		return newDate;
	}
	
	public static Date dateParse(String date) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		try {
			return dateFormat.get().parse(date);
		} catch (ParseException e) {
		}
		
		return null;
	}

	public static Date timeParse(String date) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		try {
			return dateTimeFormat.get().parse(date);
		} catch (ParseException e) {
		}
		return null;
	}
	
	public static Long getCurrentTime() {
		return System.currentTimeMillis()/1000;
	}
	
	public static Date getCurrentDate() {
		try {
			return dateFormat.get().parse(dateFormat.get().format(new Date()));
		} catch (ParseException e) {
		}
		return null;
	}
	
	public static String getCurrentDateStr() {
		return dateShortFormat.get().format(new Date());
	}
	
	public static String getCurrentTimeStr(){
		return TimeShortFormat.get().format(new Date());
	}
	/**
	 * 日期装long
	 * @param date
	 * @return
	 */
	public static Long convert(Date date) {
		if(date == null) return null;
		
		return date.getTime()/1000;
	}
	
	/**
	 * 日期字符串转long
	 * @param date
	 * @return
	 */
	public static Long convert(String date) {
		if(date == null) return null;
		
		try {
			if(date.length() > 10) 
				return (dateTimeFormat.get().parse(date)).getTime()/1000;
			
			return (dateFormat.get().parse(date)).getTime()/1000;
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 在给定的时间上加/减XX天
	 * @param date 当前时间
	 * @param days 要加/减的天数
	 * @return
	 */
	public static Date addDay(Date date, Integer days) {
		if(date == null || days == null || days == 0) return date;
		
		Calendar calendar = Calendar.getInstance();   
		calendar.setTime(date); 
		calendar.add(Calendar.DATE, days);
		
		return calendar.getTime();
	}
	
	/**
	 * 在给定的时间上加/减XX天
	 * @param date 当前时间
	 * @param days 要加/减的天数
	 * @return
	 */
	public static Long addDay(Long date, Integer days) {
		if(date == null || days == null || days == 0) return date;
		
		Calendar calendar = Calendar.getInstance();   
		calendar.setTimeInMillis(date*1000);
		calendar.add(Calendar.DATE, days);
		
		return calendar.getTimeInMillis()/1000;
	}
	
	 
	
	/**
	 * 在给定的时间上加/减XX个月
	 * @param date 当前时间
	 * @param months 要加/减的月数
	 * @return
	 */
	public static Date addMonth(Date date, Integer months) {
		if(date == null || months == null || months == 0) return date;
		
		Calendar calendar = Calendar.getInstance();   
		calendar.setTime(date); 
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}
	
	
	
	
	
	/**
	 * 在给定的时间上加/减XX个月
	 * @param date 当前时间
	 * @param months 要加/减的月数
	 * @return
	 */
	public static Long addMonth(Long date, Integer months) {
		if(date == null || months == null || months == 0) return date;
		
		Calendar calendar = Calendar.getInstance();   
		calendar.setTimeInMillis(date*1000); 
		calendar.add(Calendar.MONTH, months);
		
		return calendar.getTimeInMillis()/1000;
	}
	


	
	

	/**
	 * 计算两个日期之间相差的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetween(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	} 
	
	/**
	 * 计算两个日期之间相差的天数,超过30天的月份按30天算(计算利息用)
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetweenMore(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		int date1Year = cal.get(Calendar.YEAR);//得到年
		int date1Month = cal.get(Calendar.MONTH) + 1;//得到月
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		int date2Year = cal.get(Calendar.YEAR);//得到年
		int date2Month = cal.get(Calendar.MONTH) + 1;//得到月
		int date2Day = cal.get(Calendar.DAY_OF_MONTH);//得到日
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		int days = 0;
		if(date1Year == date2Year){//相同年份的时候
			for(int i = date1Month;i <= date2Month; i++){
				if("1,3,5,7,8,10,12".contains(String.valueOf(i))){
					days = days + 1;	
				}
			}
			if(date2Day <= 30){
				days = days -1;
			}
		}else{//不同年份的时候，如2015-6-1 至2016-5-1
			//计算两个时间相差月数
			int months = (date2Year - date1Year)*12 + (date2Month - date1Month);
			for(int i = date1Month; i <= (date1Month + months); i++){
				int j = 0;
				if(i/12 > 0){
					j = i - 12*(i/12);
				}
				if("1,3,5,7,8,10,12".contains(String.valueOf(j == 0 ? i : j))){
					days = days + 1;	
				}
			}
			if(date2Day <= 30){
				days = days -1;
			}
		}
		return Integer.parseInt(String.valueOf(between_days-days));
	} 

	/**
	 * 计算两个日期之间相差的月数
	 */
	public static int monthBetween(Date date1, Date date2){
		Calendar bef = Calendar.getInstance();
		Calendar aft = Calendar.getInstance();
		bef.setTime(date1);
		aft.setTime(date2);
		int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
		return result;
	}
	
	/**
	 * 计算两个日期之间相差几分钟
	 */
	public static long minuteBetween(Long date1,Long date2){
		long s = (date1 - date2) / 60000;
		return s;
	}
	/**
	 * 计算两个日期之间相差几小时
	 */
	public static long minuteHours(Long date1,Long date2){
		long s = (date1 - date2) /3600000;
		return s;
	}
	
	/**
	 * 计算两个日期之间相差多少天
	 */
	public static long minuteDays(Long date1,Long date2){
		long s = (date1 - date2) /86400000;
		return s;
	}
	
	/**
	 * 获取当前月份的第一天
	 * @param date
	 * @return
	 */
	public static Long getMonthFirstDay(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));     
	    calendar.set(Calendar.HOUR_OF_DAY, 0);  
	    calendar.set(Calendar.MINUTE, 0);  
	    calendar.set(Calendar.SECOND,0);  
	    return calendar.getTimeInMillis()/1000; 
	}
	
	/**
	 * 获取指定时间(0点0分0秒)
	 * @param date
	 * @param type -1：上一天 0：当天 1：下一天
	 * @return
	 */
	public static Long getDateTime(Date date, int type) {
		if(date == null) date = new Date();
		date = addDay(date, type);
		
		String strDate = dateFormat.get().format(date) + " 00:00:00";
		try {
			return dateTimeFormat.get().parse(strDate).getTime()/1000;
		} catch (ParseException e) {
		}
		
		return null;
	}
	/**
	 * 获取当天00：00：00
	 * @param date
	 * @return
	 */
	public static Long getDateIntiTime(Long time){
		String strTime=dateTimeFormat(time).substring(0, 10);
		return convert(strTime);
	}
	/**
	 * 获取当天23：59：59
	 * @param date
	 * @return
	 */
	public static Long getDateLastTime(Long time){
		String strTime=dateTimeFormat(time).substring(0, 10);
		return convert(strTime)+(3600*24L)-1L;
	}
	/**
     * 将10位日期格式化为8位
     * @param dt
     * @return
     */
	
	 public static String getShortDate(String dt){
	        if(dt != null){
	            java.text.SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
	            try {
	                Date date = myFormat.parse(dt);
	                return getDate(date,DATE_SHORT_FORMAT);
	            } catch (ParseException e) {
	                return dt;
	            }
	        }else
	            return dt;
	    }
	 //格式化日期
	 public static String getDate(java.util.Date date, String format) {
	        String result = null;
	        try {
	            java.text.SimpleDateFormat myFormat = new SimpleDateFormat(format);
	            result = myFormat.format(date);
	        } catch (Exception e) {
	            return null;
	        }
	        return result;
	    }
	 
	 
	 public static Date stringToDate(String date) {
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   try {
		System.out.println(sdf.parse(date));
		   return sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	   
	 }
	 
	 public static Date stringFormatToDate(String date) {
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   try {
			System.out.println(sdf.parse(date));
			   return sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		   
	 }
	 
	 public static Date stringToDateSimp(String date) {
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		   try {
			System.out.println(sdf.parse(date));
			   return sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		   
	}
	 
	 
	 public static void main(String[] args) {
		System.out.println(TimeUtil1.dateFormat(addDay(new Date(), 30)));
		
	}
}