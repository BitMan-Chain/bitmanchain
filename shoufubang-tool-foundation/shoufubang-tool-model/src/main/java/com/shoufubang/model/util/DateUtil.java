package com.shoufubang.model.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @date 2017-05-10 上午 09:08:00
 * @since: JDK 1.7
 * @Version：  V1.0
 */
public class DateUtil {

    /**
     * 声明一个日志记录器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 判断是否是对应的格式的日期字符串
     * @param dateStr
     * @param datePattern
     * @return
     */
    public static boolean isRightDateStr(String dateStr,String datePattern){
        DateFormat dateFormat  = new SimpleDateFormat(datePattern);
        try {
            //采用严格的解析方式，防止类似 “2017-05-35” 类型的字符串通过
            dateFormat.setLenient(false);
            dateFormat.parse(dateStr);
            Date date = (Date)dateFormat.parse(dateStr);
            //重复比对一下，防止类似 “2017-5-15” 类型的字符串通过
            String newDateStr = dateFormat.format(date);
            if(dateStr.equals(newDateStr)){
                return true;
            }else {
                LOGGER.error("字符串dateStr:{}， 不是严格的 datePattern:{} 格式的字符串",dateStr,datePattern);
                return false;
            }
        } catch (ParseException e) {
            LOGGER.error("字符串dateStr:{}，不能按照 datePattern:{} 样式转换",dateStr,datePattern);
            return false;
        }
    }
}