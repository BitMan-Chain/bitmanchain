package com.shoufubang.model.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.FileNameMap;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import arch.util.lang.Predef;
import htz.util.format.MoneyTransferUtil;
import htz.util.lang.NumberUtil;
import htz.util.lang.TimeUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



	/**
	 * 
	 * @author shuaili
     * @date  2016年6月3日 下午1:22:18 
	 * @Title  
	 * @Description 
	 * @param  params 
	 * @return 
	 * @throws
	 * @version V1.0
	 */
public class Common1 {
	private static Logger logger = LoggerFactory.getLogger(Common1.class);
    private static MessageSource mr = null;
    public static double RC_EPS = 1.0e-6; // Tolerance
    private static Random random = new Random();
    private static final String randChars = "0123456789abcdefghigklmnopqrstuvtxyzABCDEFGHIGKLMNOPQRSTUVWXYZ";

    private static Map<String, String[]> timeZoneIDs = new LinkedHashMap<String, String[]>(32);
    static {
        timeZoneIDs.put("-12", new String[] { "GMT-12:00", "(GMT -12:00) Eniwetok, Kwajalein" });
        timeZoneIDs.put("-11", new String[] { "GMT-11:00", "(GMT -11:00) Midway Island, Samoa" });
        timeZoneIDs.put("-10", new String[] { "GMT-10:00", "(GMT -10:00) Hawaii" });
        timeZoneIDs.put("-9", new String[] { "GMT-09:00", "(GMT -09:00) Alaska" });
        timeZoneIDs.put("-8", new String[] { "GMT-08:00", "(GMT -08:00) Pacific Time (US &amp; Canada), Tijuana" });
        timeZoneIDs.put("-7", new String[] { "GMT-07:00", "(GMT -07:00) Mountain Time (US &amp; Canada), Arizona" });
        timeZoneIDs.put("-6", new String[] { "GMT-06:00", "(GMT -06:00) Central Time (US &amp; Canada), Mexico City" });
        timeZoneIDs.put("-5", new String[] { "GMT-05:00", "(GMT -05:00) Eastern Time (US &amp; Canada), Bogota, Lima, Quito" });
        timeZoneIDs.put("-4", new String[] { "GMT-04:00", "(GMT -04:00) Atlantic Time (Canada), Caracas, La Paz" });
        timeZoneIDs.put("-3.5", new String[] { "GMT-03:30", "(GMT -03:30) Newfoundland" });
        timeZoneIDs.put("-3", new String[] { "GMT-03:00", "(GMT -03:00) Brassila, Buenos Aires, Georgetown, Falkland Is" });
        timeZoneIDs.put("-2", new String[] { "GMT-02:00", "(GMT -02:00) Mid-Atlantic, Ascension Is., St. Helena" });
        timeZoneIDs.put("-1", new String[] { "GMT-01:00", "(GMT -01:00) Azores, Cape Verde Islands" });
        timeZoneIDs.put("0", new String[] { "GMT", "(GMT) Casablanca, Dublin, Edinburgh, London, Lisbon, Monrovia" });
        timeZoneIDs.put("1", new String[] { "GMT+01:00", "(GMT +01:00) Amsterdam, Berlin, Brussels, Madrid, Paris, Rome" });
        timeZoneIDs.put("2", new String[] { "GMT+02:00", "(GMT +02:00) Cairo, Helsinki, Kaliningrad, South Africa" });
        timeZoneIDs.put("3", new String[] { "GMT+03:00", "(GMT +03:00) Baghdad, Riyadh, Moscow, Nairobi" });
        timeZoneIDs.put("3.5", new String[] { "GMT+03:30", "(GMT +03:30) Tehran" });
        timeZoneIDs.put("4", new String[] { "GMT+04:00", "(GMT +04:00) Abu Dhabi, Baku, Muscat, Tbilisi" });
        timeZoneIDs.put("4.5", new String[] { "GMT+04:30", "(GMT +04:30) Kabul" });
        timeZoneIDs.put("5", new String[] { "GMT+05:00", "(GMT +05:00) Ekaterinburg, Islamabad, Karachi, Tashkent" });
        timeZoneIDs.put("5.5", new String[] { "GMT+05:30", "(GMT +05:30) Bombay, Calcutta, Madras, New Delhi" });
        timeZoneIDs.put("5.75", new String[] { "GMT+05:45", "(GMT +05:45) Katmandu" });
        timeZoneIDs.put("6", new String[] { "GMT+06:00", "(GMT +06:00) Almaty, Colombo, Dhaka, Novosibirsk" });
        timeZoneIDs.put("6.5", new String[] { "GMT+06:30", "(GMT +06:30) Rangoon" });
        timeZoneIDs.put("7", new String[] { "GMT+07:00", "(GMT +07:00) Bangkok, Hanoi, Jakarta" });
        timeZoneIDs.put("8", new String[] { "GMT+08:00", "(GMT +08:00) Beijing, Hong Kong, Perth, Singapore, Taipei" });
        timeZoneIDs.put("9", new String[] { "GMT+09:00", "(GMT +09:00) Osaka, Sapporo, Seoul, Tokyo, Yakutsk" });
        timeZoneIDs.put("9.5", new String[] { "GMT+09:30", "(GMT +09:30) Adelaide, Darwin" });
        timeZoneIDs.put("10", new String[] { "GMT+10:00", "(GMT +10:00) Canberra, Guam, Melbourne, Sydney, Vladivostok" });
        timeZoneIDs.put("11", new String[] { "GMT+11:00", "(GMT +11:00) Magadan, New Caledonia, Solomon Islands" });
        timeZoneIDs.put("12", new String[] { "GMT+12:00", "(GMT +12:00) Auckland, Wellington, Fiji, Marshall Island" });
    }

    private static final char[] pregChars = { '.', '\\', '+', '*', '?', '[', '^', ']', '$', '(', ')', '{', '}', '=', '!', '<', '>', '|', ':' };

    /**
     * 判断对象是否为空，判断字符串为空串，判断集合和map为空，
     *
     * @param obj
     * @return
     */
    public static boolean empty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String && "".equals(obj.toString().trim())) {
            return true;
        } else if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        } else if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        } else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {
            return true;
        }
        return false;
    }

    /**
     * 转换springmvc RequestParam 中传来的中文乱码
     *
     * @param str
     */
    public static String encodeString(String str) {
        if (null == str) {
            return null;
        }
        try {
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /*
     * 把十六进制Unicode编码字符串转换为中文字符串
     */
    public static String unicodeToString(String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}){4})");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }

    /**
     * 把String按照,分开成数组,同时将字符串中的null或空去除掉
     *
     * @param idString
     * @return
     */
    public static String[] StringToStringArrayWithoutNull(String idString, String separator) {
        String[] s = {};
        List<String> beans = new ArrayList<String>();
        if (!Common1.empty(idString)) {
            String[] ids = idString.split(separator);
            for (int i = 0; i < ids.length; i++) {
                if (null == ids[i] || "null".equals(ids[i]) || "".equals(ids[i])) {
                    continue;
                }
                beans.add(ids[i]);
            }
        }
        return beans.toArray(s);
    }

    // match a number with optional '-' and decimal.
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * 从1到最大数的随机数
     *
     * @param max
     * @return
     */
    public static int rand(int max) {
        return rand(1, max);
    }

    /**
     * 指定范围的随机数
     *
     * @param min
     * @param max
     * @return
     */
    public static int rand(int min, int max) {
        if (min < max) {
            return random.nextInt(max - min + 1) + min;
        } else {
            return min;
        }
    }

    /**
     * 把字符串转换为map对象
     *
     * @param s
     * @return
     */
    public static Map<String, Object> stringToMap(String s) {
        JsonParser jp = new JsonParser();
        JsonObject je = (JsonObject) jp.parse(s);
        return getMap(je);
    }

    /**
     * JsonObject 转换为map
     *
     * @param ja
     * @return
     */
    public static Map<String, Object> getMap(JsonObject ja) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (Iterator<Map.Entry<String, JsonElement>> ite = ja.entrySet().iterator(); ite.hasNext();) {
            Map.Entry<String, JsonElement> je = ite.next();
            String key = je.getKey();
            if (je.getValue().isJsonObject()) {
                map.put(key, getMap((JsonObject) je.getValue()));
            } else {
                map.put(key, je.getValue().getAsString());
            }
        }
        return map;
    }
    
    /**
     * 界定符转义
     *
     * @param text
     * @param delimiter
     * @return
     */
    public static String pregQuote(String text, char... delimiter) {
        StringBuffer sb = new StringBuffer(text.length() * 2);
        StringCharacterIterator iterator = new StringCharacterIterator(text);
        char character = iterator.current();
        while (character != StringCharacterIterator.DONE) {
            boolean flag = false;
            for (char c : pregChars) {
                if (character == c) {
                    flag = true;
                    break;
                }
            }
            if (!flag && delimiter != null) {
                for (char d : delimiter) {
                    if (character == d) {
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) {
                sb.append('\\');
            }
            sb.append(character);
            character = iterator.next();
        }
        return sb.toString();
    }

    /**
     * 将数组对象，或者容器对象，或者map对象的每个内容通过separator链接起来返回结果字符串
     *
     * @param data
     * @param separator
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String implode(Object data, String separator) {
        if (data == null) {
            return "";
        }
        StringBuffer out = new StringBuffer();
        if (data instanceof Object[]) {
            boolean flag = false;
            for (Object obj : (Object[]) data) {
                if (flag) {
                    out.append(separator);
                } else {
                    flag = true;
                }
                out.append(obj);
            }
        } else if (data instanceof Map) {
            Map temp = (Map) data;
            Set<Object> keys = temp.keySet();
            boolean flag = false;
            for (Object key : keys) {
                if (flag) {
                    out.append(separator);
                } else {
                    flag = true;
                }
                out.append(temp.get(key));
            }
        } else if (data instanceof Collection) {
            boolean flag = false;
            for (Object obj : (Collection) data) {
                if (flag) {
                    out.append(separator);
                } else {
                    flag = true;
                }
                out.append(obj);
            }
        } else {
            return data.toString();
        }
        return out.toString();
    }

    /**
     * 得到指定长度的字符串，
     *
     * @param length
     *            字符串长度
     * @param isOnlyNum
     *            是否只是数字
     * @return
     */
    public static String getRandStr(int length, boolean isOnlyNum) {
        int size = isOnlyNum ? 10 : 62;
        StringBuffer hash = new StringBuffer(length);
        for (int i = 0; i < length; i++) {
            hash.append(randChars.charAt(random.nextInt(size)));
        }
        return hash.toString();
    }

    /**
     * 对象转为json字符串
     *
     * @name 方法名： objectToJson
     * @author 作者： stone
     * @version 创建时间： 2011-11-11 下午07:01:14
     * @function 功能： 该方法...
     * @param o
     * @return String
     */
    public static String objectToJson(Object o) {
        return new Gson().toJson(o);
    }

    /**
     * 判断是否是email
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        return Common1.strlen(email) > 6 && email.matches("^[\\w\\-\\.]+@[\\w\\-\\.]+(\\.\\w+)+$");
    }

    /**
     * 判断是否是手机格式
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        return Common1.strlen(mobile)==11 && mobile.matches("^1\\d{10}$");
    }


    /**
     * 得到指定编码字符串长度
     *
     * @param text
     * @param charsetName
     * @return
     */
    public static int strlen(String text) {
        if (text == null || text.length() == 0) {
            return 0;
        }
        int length = 0;
        try {
            length = text.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return length;
    }

    /**
     * 读取资源文件
     *
     * @param request
     * @param key
     * @param args
     * @return
     */
    public static String getMessage(HttpServletRequest request, String key, Object... args) {
        if (key == null || key.length() == 0) {
            return key;
        }

        if (mr == null) {
            mr = new ClassPathXmlApplicationContext("spring/beans-message.xml");
        }
        Locale locale = null;
        if (!Common1.empty(request)) {
            locale = request.getLocale();
        } else {
            locale = Locale.CHINA;
        }
        String message = null;
        if (args == null || args.length == 0) {
            message = mr.getMessage(key, null, null, locale);
        } else {
            message = mr.getMessage(key, args, locale);
        }
        return message == null ? key : message;
    }

    public static int time() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * 得到时间的长度
     *
     * @param sConfig
     * @return
     */
    public static String getTimeOffset(Map<String, Object> sConfig) {
        String timeoffset = null;
        timeoffset = sConfig.get("timeoffset").toString();
        return timeoffset;
    }

    /**
     * 没有格式的在线ip
     *
     * @param request
     * @return
     */
    public static String getOnlineIP(HttpServletRequest request) {
        return getOnlineIP(request, false);
    }

    /**
     * 得到在线ip
     *
     * @param request
     * @param format
     * @return
     */
    public static String getOnlineIP(HttpServletRequest request, boolean format) {
         Map<String, Object> sGlobal = (Map<String, Object>)
         request.getAttribute("sGlobal");
        String onlineip = null;// (String) sGlobal.get("onlineip");
        if (onlineip == null) {
            onlineip = request.getHeader("x-forwarded-for");
            if (onlineip == null || onlineip.length() == 0 || "unknown".equalsIgnoreCase(onlineip)) {
            	onlineip = request.getHeader("Proxy-Client-IP");
            }

            if (onlineip == null || onlineip.length() == 0 || "unknown".equalsIgnoreCase(onlineip)) {
            	onlineip = request.getHeader("WL-Proxy-Client-IP");
            }

            if (onlineip == null || onlineip.length() == 0 || "unknown".equalsIgnoreCase(onlineip)) {
            	onlineip = request.getHeader("Proxy-Client-IP");
            }

            if (Common1.empty(onlineip) || "unknown".equalsIgnoreCase(onlineip)) {
                onlineip = request.getHeader("X-Real-IP");
            }
            if (Common1.empty(onlineip) || "unknown".equalsIgnoreCase(onlineip)) {
                onlineip = request.getRemoteAddr();
            }
            String[] a=onlineip.split(",");
            if(a.length>0)
                 onlineip=a[0];
            //logger.info("onlineip---->"+onlineip);
            onlineip = onlineip != null && onlineip.matches("^[\\d\\.]{7,15}$") ? onlineip : "unknown";
            // sGlobal.put("onlineip", onlineip);
        }
        if (format) {
            String[] ips = onlineip.split("\\.");
            String stip = "000";
            StringBuffer temp = new StringBuffer();
            for (int i = 0; i < 3; i++) {
                int ip = 0;
                if (i < ips.length) {
                    ip = intval(ips[i]);
                }
                temp.append(Common1.sprintf(stip, ip));
            }
            return temp.toString();
        } else {
            return onlineip;
        }
    }

    public static int intval(String s) {
        return intval(s, 10);
    }

    public static int intval(String s, int radix) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (radix == 0) {
            radix = 10;
        } else if (radix < Character.MIN_RADIX) {
            return 0;
        } else if (radix > Character.MAX_RADIX) {
            return 0;
        }
        int result = 0;
        int i = 0, max = s.length();
        int limit;
        int multmin;
        int digit;
        boolean negative = false;
        if (s.charAt(0) == '-') {
            negative = true;
            limit = Integer.MIN_VALUE;
            i++;
        } else {
            limit = -Integer.MAX_VALUE;
        }
        if (i < max) {
            digit = Character.digit(s.charAt(i++), radix);
            if (digit < 0) {
                return 0;
            } else {
                result = -digit;
            }
        }
        multmin = limit / radix;
        while (i < max) {
            digit = Character.digit(s.charAt(i++), radix);
            if (digit < 0) {
                break;
            }
            if (result < multmin) {
                result = limit;
                break;
            }
            result *= radix;
            if (result < limit + digit) {
                result = limit;
                break;
            }
            result -= digit;
        }
        if (negative) {
            if (i > 1) {
                return result;
            } else {
                return 0;
            }
        } else {
            return -result;
        }
    }

    /**
     * 格式化之后小数点格式
     *
     * @param format
     * @param number
     * @return
     */
    public static String sprintf(String format, double number) {
        return new DecimalFormat(format).format(number);
    }

    /**
     * 通过时间戳得到时间格式的字符串
     *
     * @param format
     * @param timestamp
     * @param timeoffset
     * @return
     */
    public static String gmdate(String format, int timestamp, String timeoffset) {
        return getSimpleDateFormat(format, timeoffset).format(timestamp * 1000l);
    }

    /**
     * 得到时间格式
     *
     * @param format
     * @param timeoffset
     * @return
     */
    public static SimpleDateFormat getSimpleDateFormat(String format, String timeoffset) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone(timeZoneIDs.get(timeoffset)[0]));
        return sdf;
    }

    public static String addSlashes(String text) {
        if (text == null || text.equals("")) {
            return "";
        }
        StringBuffer sb = new StringBuffer(text.length() * 2);
        StringCharacterIterator iterator = new StringCharacterIterator(text);
        char character = iterator.current();
        while (character != StringCharacterIterator.DONE) {
            switch (character) {
            case '\'':
            case '"':
            case '\\':
                sb.append("\\");
            default:
                sb.append(character);
                break;
            }
            character = iterator.next();
        }
        return sb.toString();
    }

    /**
     * 得到md5加密的字符串
     *
     * @param arg0
     * @return
     */
    public static String md5(String arg0) {
        return Md5Util.encode(arg0);
    }

    public static int range(Object value, int max, int min) {
        if (value instanceof String) {
            return Math.min(max, Math.max(intval((String) value), min));
        } else {
            return Math.min(max, Math.max((Integer) value, min));
        }
    }

    public static String stripSlashes(String text) {
        if (text == null || text.equals("")) {
            return "";
        }
        StringBuffer sb = new StringBuffer(text.length());
        StringCharacterIterator iterator = new StringCharacterIterator(text);
        char character = iterator.current();
        while (character != StringCharacterIterator.DONE) {
            switch (character) {
            case '\'':
                sb.append("'");
                break;
            case '"':
                sb.append('"');
                break;
            case '\\':
                sb.append(iterator.next());
                break;
            default:
                sb.append(character);
                break;
            }
            character = iterator.next();
        }
        return sb.toString();
    }

    /****************************************************************
     * @Title: checkIpSetting
     * @Description: TODO
     * @param ipSettingStr
     * @return
     * @return boolean
     * @version V1.0
     */
    public static boolean checkIpSettingStr(String ipSettingStr) {
        if (!Common1.empty(ipSettingStr)) {
            String[] ipArr = null;
            String[] numArr = null;
            if (ipSettingStr.indexOf("\r\n") != -1) {
                ipArr = ipSettingStr.split("\r\n");
                for (int i = 0; i < ipArr.length; i++) {
                    if (!empty(ipArr[i])) {
                        numArr = ipArr[i].split("\\.");
                        if (checkIpNum(numArr)) {
                            continue;
                        } else {
                            return false;
                        }
                    }
                }
            } else {
                if (ipSettingStr.indexOf(".") != -1) {
                    numArr = ipSettingStr.split("\\.");
                    if (!checkIpNum(numArr)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /****************************************************************
     * @Title: checkIpNum
     * @Description: TODO
     * @param numArr
     * @return
     * @return boolean
     * @version V1.0
     */
    private static boolean checkIpNum(String[] numArr) {
        if (!empty(numArr)) {
            for (int i = 0; i < numArr.length; i++) {
                if (!empty(numArr[i])) {
                    if (isNumeric(numArr[i])) {
                        Integer num = Integer.parseInt(numArr[i]);
                        if (num < 0 || num > 255) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 能够访问的ip
     *
     * @param ip
     * @param ipAccess
     * @return
     */
    public static boolean ipAccess(String ip, Object ipAccess) {
        return empty(ipAccess) ? true : ip.matches("^(" + pregQuote(String.valueOf(ipAccess), '/').replaceAll("\r\n", "|").replaceAll(" ", "")
                + ").*");
    }

    /**
     * 禁止访问的ip
     *
     * @param ip
     * @param ipBanned
     * @return
     */
    public static boolean ipBanned(String ip, Object ipBanned) {
        return empty(ipBanned) ? false : ip.matches("^(" + pregQuote(String.valueOf(ipBanned), '/').replaceAll("\r\n", "|").replaceAll(" ", "")
                + ").*");
    }

    /**
     * 去掉字符串空格
     *
     * @param text
     * @return
     */
    public static String trim(String text) {
        if (text == null) {
            return "";
        }
        return text.trim();
    }

    /**
     * 得到文件大小描述字符串
     *
     * @param dataSize
     * @return
     */
    public static String formatSize(long dataSize) {
        dataSize = Math.abs(dataSize);
        if (dataSize >= 1099511627776d) {
            return (Math.round(dataSize / 1099511627776d * 100) / 100d) + " TB";
        } else if (dataSize >= 1073741824) {
            return (Math.round(dataSize / 1073741824d * 100) / 100d) + " GB";
        } else if (dataSize >= 1048576) {
            return (Math.round(dataSize / 1048576d * 100) / 100d) + " MB";
        } else if (dataSize >= 1024) {
            return (Math.round(dataSize / 1024d * 100) / 100d) + " KB";
        } else if (dataSize > 0) {
            return dataSize + " B ";
        } else {
            return "   0 B ";
        }
    }

    /**
     * 得到文件的后缀名
     *
     * @param filePath
     * @return
     */
    public static String fileext(String filePath) {
        int sl = filePath.length();
        if (sl < 2) {
            return "";
        }
        int lastPoint = filePath.lastIndexOf(".");
        if (lastPoint < 0) {
            return "";
        }
        return filePath.substring(lastPoint + 1, filePath.length());
    }

    public static boolean isArray(Object obj) {
        if (obj instanceof Object[]) {
            return true;
        } else if (obj instanceof Collection) {
            return true;
        } else if (obj instanceof Map) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断ext是否在source里面
     *
     * @param source
     * @param ext
     * @return
     */
    public static boolean in_array(Object source, Object ext) {
        return in_array(source, ext, false);
    }

    /**
     * 判断ext是否在source里面
     *
     * @param source
     * @param ext
     * @param strict
     *            true 表明所属类型也要一样
     * @return
     */
    public static boolean in_array(Object source, Object ext, boolean strict) {
        if (source == null || ext == null) {
            return false;
        }
        if (source instanceof Collection) {
            for (Object s : (Collection) source) {
                if (s.toString().equals(ext.toString())) {
                    if (strict) {
                        if ((s.getClass().getName().equals(ext.getClass().getName()))) {
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
            }
        } else {
            for (Object s : (Object[]) source) {
                if (s.toString().equals(ext.toString())) {
                    if (strict) {
                        if ((s.getClass().getName().equals(ext.getClass().getName()))) {
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /****************************************************************
     * @Title: readDir
     * @Description: TODO
     * @param dir
     * @param extarr
     * @return
     * @return File[]
     * @version V1.0
     */
    public static File[] readDir(String dir, final String... extarr) {
        File supDir = new File(dir);
        if (supDir.isDirectory()) {
            if (extarr == null || extarr.length == 0) {
                return supDir.listFiles();
            } else {
                FilenameFilter filenameFilter = new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        int tempI = name.lastIndexOf(".");
                        String postfix = null;
                        if (tempI >= 0) {
                            postfix = name.substring(tempI + 1);
                        } else {
                            postfix = name;
                        }
                        return in_array(extarr, postfix);
                    }
                };
                return supDir.listFiles(filenameFilter);
            }
        }
        return null;
    }

    /****************************************************************
     * @Title: stripSearchKey
     * @Description: TODO
     * @param string
     * @return
     * @return String
     * @version V1.0
     */
    public static String stripSearchKey(String string) {
        if (string == null || "".equals(string)) {
            return "";
        }
        string = string.trim();
        string = Common1.addSlashes(string).replace("*", "%");
        string = string.replace("_", "\\_");
        return string;
    }

    /****************************************************************
     * @Title: matches
     * @Description: TODO
     * @param content
     * @param regex
     * @return
     * @return boolean
     * @author huadong huadong19890803@163.com
     * @date 2011-10-8
     * @version V1.0
     */
    public static boolean matches(String content, String regex) {
        boolean flag = false;
        try {
            flag = new Perl5Matcher().contains(content, new Perl5Compiler().compile(regex));
        } catch (MalformedPatternException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 通过正则表达式匹配内容
     *
     * @param content
     * @param regex
     * @return
     */
    public static List<String> pregMatch(String content, String regex) {
        List<String> strList = new ArrayList<String>();
        try {
            Perl5Matcher patternMatcher = new Perl5Matcher();
            if (patternMatcher.contains(content, new Perl5Compiler().compile(regex))) {
                MatchResult result = patternMatcher.getMatch();
                for (int i = 0; i < result.groups(); i++) {
                    strList.add(result.group(i));
                }
                result = null;
            }
        } catch (MalformedPatternException e) {
            e.printStackTrace();
        }
        return strList;
    }

    /**
     * 与当前的小时差
     *
     * @param createTime
     * @return
     */
    public static long getGapHour(Date createTime) {
        // TODO Auto-generated method stub
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH");
        long hour = 0;

        try {
            /*
             * System.out.println(myFormatter.parse(myFormatter.format(new
             * Date())));
             * System.out.println(myFormatter.parse(myFormatter.format
             * (createTime)));
             */
            hour = (myFormatter.parse(myFormatter.format(new Date())).getTime() - myFormatter.parse(myFormatter.format(createTime)).getTime())
                    / (60 * 60 * 1000);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return hour;
    }

    public static boolean checkMobile(Object mobiles) {
        Pattern p = Pattern.compile("^1\\d{10}$");
        Matcher m = p.matcher(mobiles.toString());
        return m.matches();

    }

    /**
     * 截取字符串
     */
    public static String spiltTime(String time, String decollator) {
        String str = time;
        if (!Common1.empty(time)) {
            str = time.substring(0, time.lastIndexOf(decollator));
        }
        return str;
    }

    /**
     *
     * @author Administrator
     * @Title replaceStr
     * @Description 替换指定位置的字符
     * @param @param str
     * @param @param begin
     * @param @param end
     * @param @param replacement, if isloop eq true, the length of replacement must eq 1
     * @return void
     * @throws
     * @date 2013-11-18 下午08:38:06
     * @version V1.0
     */
    public static String replaceStr(String str, int begin, int end, String replacement, boolean isloop) {
        // TODO Auto-generated method stub
        String newStr = "";
        if (!Common1.empty(str)) {
        	str = str.trim();
            if (isloop) {
            	if(replacement.length()==1&&str.length()>=(begin+end+replacement.length())){
            		String tempment = "";
            		for (int i = 0; i < str.length()-end - begin; i++) {
            			tempment = tempment + replacement;
            		}
            		replacement = tempment;
            		newStr = str.substring(0, begin) + replacement + str.substring(str.length() - end, str.length());
            	}else{
            		newStr = str;
            	}
            }else if(begin>=0&&end>=0&&str.length()>=(begin+end)){
            	newStr = str.substring(0, begin) + replacement + str.substring(str.length() - end, str.length());
            }else{
            	newStr = str;
            }
        }
        return newStr;
    }

    /**
     *
     * @author Administrator
     * @Title formatDouble
     * @Description 处理doubl数据，保留小数点后2位
     * @param @return
     * @return String
     * @throws
     * @date 2013-11-18 下午11:44:50
     * @version V1.0
     */
    public static String formatDouble(double db) {
        // TODO Auto-generated method stub
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(db);
    }

    /**
     * try to parse the object to integer. if failed, return default value
     * @param obj
     * @param defaultVal
     * @return
     */
    public static int tryParseInt(Object obj, int defaultVal) {
        int returnVal = defaultVal;
        if (!Common1.empty(obj)) {
            try {
                returnVal = Integer.parseInt(obj.toString().trim());
            } catch (Exception e) {}
        }
        return returnVal;
    }

	/**
	 * 获取加密的随机码
	 * @param length -随机码的长度
	 * @return
	 */
	public static String getSalt(int length){
		String str =Common1.getRandStr(6, false)+System.currentTimeMillis();
    	String oldRandom = Common1.md5(str);
    	return oldRandom.substring(oldRandom.length()-length);
	}

	/**
	 * 加密原密码
	 * @param targetPasswd
	 * @return
	 */
	public static String encryptPasswd(String targetPasswd,String salt){
		if(!Common1.empty(salt)&&!Common1.empty(targetPasswd)){
			return Common1.md5(targetPasswd+salt);
		}else{
			return "";
		}
	}

	public static String getRMB(Object obj) {
		return MoneyTransferUtil.getRMB(obj);
	}

	/**
	 * 大于0的double，转换成人民币，整数部分每3位 ","分割，保留两位小数，第三位小数四位五入
	 */
	public static String getRMB(double d) {
		return MoneyTransferUtil.getRMB(d);
	}

	/**
	 * 对于double数据保留两位小数,第三位小数四舍五入
	 * @param d
	 * @return 返回String
	 */
	public static String retainTwoDecimalStr(double d) {
		return NumberUtil.retainTwoDecimalStr(d);
	}

	/**
	 * double数据向下取整
	 * @param d
	 * @return
	 */
	 public static int floor(double d) {
	  if (Math.abs(d - Math.round(d)) < RC_EPS) {
	   d = Math.round(d);
	  }
	  return (int) Math.floor(d);
	 }

	 public static String retainNoDecimalStr(Object obj){
		 String temp = "";
		 if(!Common1.empty(obj))
		 {
			 double db = Predef.toDouble(obj,0);
			 temp = retainNoDecimalStr(db);
		 }
		return temp;
	}

	/**
	 * 对于double数据截去小数
	 * @param d
	 * @return 返回String
	 */
	public static String retainNoDecimalStr(double d){
		BigDecimal b = new BigDecimal(d);
		BigDecimal df = new BigDecimal(b.setScale(0, BigDecimal.ROUND_DOWN).doubleValue());//直接删除多余的小数位
		DecimalFormat dformat = new DecimalFormat("#");
		String temp=dformat.format(df);
		return temp;
	}

	/**
	 * 保留一位小数不进行四舍五入
	 * @param d
	 * @return
	 */
	public static String retainOneDecimalStr(double d){
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
	public static String formateString(String rateStr, int n){
		if(rateStr.indexOf(".") != -1){
			for(int i=0;i<n;i++){
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
			for(int i=0;i<n;i++){
				if(i==0){
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
	public static String formateString(String str){
		String subStrC="";
	    if(str.length()>3){
			String subStr=str;

			int f=str.length()-1;
			int count=0;
			while(f>=0){
				count++;
				subStrC=subStr.charAt(f)+subStrC;
				if(count==3){
					subStrC=","+subStrC;
					count=0;
				}
			    f--;
			}
		}else{
			subStrC=str;
		}

	    if(subStrC.length()>1&&subStrC.charAt(0)==','){
	    	subStrC=subStrC.substring(1);
	    }

		return subStrC;
	}


	/**
	 * 对于double数据保留两位小数,第三位小数四舍五入
	 * @param d
	 * @return 返回double型 	 * 注意，可能会返回科学计数法的样式，如2.13000099E12
	 */
	public static double retainTwoDecimal(double d){
		BigDecimal b = new BigDecimal(d);
		BigDecimal df = new BigDecimal(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		DecimalFormat dformat = new DecimalFormat("#0.00");
		return Double.parseDouble(dformat.format(df));
	}

	/**
	 * 大于0的double，转换成人民币，整数部分每3位 ","分割，保留两位小数
	 * @return
	 */
	public static String getRMBDouble(String str){
		if(Common1.empty(str)){
			return "0.00";
		}
		if(str.indexOf(".") != -1){
			//获取小数点的位置  
			int num = 0;
			num = str.indexOf(".");

			//获取小数点后面的数字 是否有两位 不足两位补足两位  
			String dianAfter = str.substring(0,num+1);                    //整数部分+小数点
			String afterData = str.replace(dianAfter, "")+"0000";         //小数部分

			Double d = Double.valueOf("0."+afterData.substring(0,3));     //小数部分：四舍五入
			String s = retainTwoDecimal(d)+"0000";
			s = s.substring(s.indexOf(".")+1, s.indexOf(".")+3);

			return formateString(str.substring(0,num)) + "." + s;
		}else{
			return formateString(str)+".00";
		}
	}

	/**
	 * 对大数据处理保留两位小数，排除科学计算E
	 * @param str
	 * @return
	 */
	public static String retainTwoDecimalStr(String str){
		if(Common1.empty(str)){
			return "0.00";
		}
		if(str.indexOf(".") != -1){
			//获取小数点的位置  
			int num = 0;
			num = str.indexOf(".");

			//获取小数点后面的数字 是否有两位 不足两位补足两位  
			String dianAfter = str.substring(0,num+1);                    //整数部分+小数点
			String afterData = str.replace(dianAfter, "")+"0000";         //小数部分

			Double d = Double.valueOf("0."+afterData.substring(0,3));     //小数部分：四舍五入
			String s = retainTwoDecimal(d)+"0000";
			s = s.substring(s.indexOf(".")+1, s.indexOf(".")+3);

			return dianAfter + s;
		}else{
			return str+".00";
		}
	}

	/**
	 * 大于0的double，转换成人民币，整数部分每3位 ","分割，不保留小数
	 * @param obj
	 * @return
	 */
	public static String getRMBInteger(Object obj){
		if(obj==null||String.valueOf(obj).isEmpty()){
			obj="0";
		}
		return getRMBInteger(Double.valueOf(obj.toString()).doubleValue());
	}
	/**
	 * 大于0的double，转换成人民币，整数部分每3位 ","分割，不保留小数
	 * @param d
	 * @return
	 */
	public static String getRMBInteger(double d){
		/**去小数*/
		String temp=null;
		if(d>=0){
			temp=retainNoDecimalStr(d);
		}else{
			temp=retainNoDecimalStr(-d);
		}

		String subStrC="";
        if(temp.length()>3){
			String subStr=temp;

			int f=temp.length()-1;
			int count=0;
			while(f>=0){
				count++;
				subStrC=subStr.charAt(f)+subStrC;
				if(count==3){
					subStrC=","+subStrC;
					count=0;
				}
			    f--;
			}
		}else{
			subStrC=temp;
		}

        if(subStrC.length()>1&&subStrC.charAt(0)==','){
        	subStrC=subStrC.substring(1);
        }

        if(d<0){
        	subStrC="-"+subStrC;
        }
		return subStrC;
	}

	/**
	 * 把钱格式化成：**亿**万**元
	 * @return
	 */
	public static Long[] formateStringToYiWanYuan(String str){
		long yi = 100000000;
		long wan = 10000;
		long yuan = 1;

		Double d = Common1.empty(str)?0:Double.parseDouble(retainNoDecimalStr(str));
		long num1 = (long) (d/yi);//计算多少亿
		long num2 = (long) ((long) (d%yi)/wan);//计算多少万
		long num3 = (long) ((long) ((long) (d%yi)%wan))/yuan;//计算多少元
		Long[] strs = new Long[]{num1, num2, num3};
		return strs;
	}

	/**
	 * AccessFilter 中用到，为了设置basePath用于 Base标签
	 * 如果发现从Nginx传过来Nginx-SSL（nginx.conf文件配置），则认为是https。
	 * @param request
	 * @return
	 */
	public static String getBasePath(HttpServletRequest request)
	{
		String path = request.getContextPath()=="/"?"/": request.getContextPath()+"/";
		String scheme="1".equals(request.getHeader("Nginx-SSL"))?"https":request.getScheme() ;
		String basePath = scheme+ "://"
				+ request.getHeader("Host")
				+ path ;
		return basePath;
	}

	/**
	 *
	 * @return 如果可以执行，返回null，否则返回错误信息
	 * @throws Exception
	 */
	public static String getRunJobHostError()  {

		try
		{
			String host1 = java.net.InetAddress.getLocalHost().getHostAddress();

		//	String host2=Global.htzConfig.get("runJobHost")==null?null:Global.htzConfig.get("runJobHost").toString();

//			boolean b = host2 != null && ("*".equals(host2) || host2.indexOf(host1) >= 0);
//			if (b) return null;
//			return	 ("本机不允许执行定时任务。本机地址为：" + host1 + "，允许运行地址为：" + host2 + "（可以配置htz_config表runJobHost参数）");

		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
		return null;

	}

	 public static String extractFileName(String _sFilePathName)
	  {
	    int nPos=_sFilePathName.lastIndexOf('/');
	    int nPos2=_sFilePathName.lastIndexOf('\\');
	    if(nPos2>nPos) nPos=nPos2;
	    return _sFilePathName.substring(nPos+1);
	  }

	/**
	 *
	 * @param response
	 * @param filename
	 *            源文件名
	 * @param isAttachment
	 *            是否作为附件下载
	 * @param AttachmentName
	 *            附件名称
	 */
	public static void sendFile(HttpServletResponse response, String filename,
			boolean isAttachment, String AttachmentName)
			throws java.io.IOException {
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String contentType = fileNameMap.getContentTypeFor(filename);
		if (contentType == null)
			contentType = "application/unknown";
		response.setContentType(contentType);
		if (isAttachment) {
			if (AttachmentName == null || AttachmentName.length() == 0)
				AttachmentName =  extractFileName(filename);
			response.setHeader("Content-Disposition",
					"attachment;filename="
							+ new String(AttachmentName.getBytes("gb2312"),
									"iso8859-1"));

		}

		java.io.InputStream is = null;
		OutputStream os = response.getOutputStream();

		try {

			  {

				if (!new File(filename).exists())
					throw new java.io.FileNotFoundException(filename);
				is = new FileInputStream(filename);
			}
			IOUtils.copy (is, os);
			os.flush();
		} catch (IOException e) {
			response.setContentType("text/html; charset=UTF-8");
			//os.write(eToString(e).getBytes());
			//logger.error("", e);
			 throw e;
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (Exception e) {
				}

			try {
				os.close();
			} catch (Exception e) {
			}

		}

	}

	/**
	 * html转义
	 * @param content
	 * @return
	 */
	public static String htmlEscape(String content) {
		 if(content==null) return "";
		     String html = content;
		     html = html.replace( "'", "&apos;");//'
		     html = html.replaceAll( "&", "&amp;");//&
		     html = html.replace( "\"", "&quot;");  //"
		     html = html.replace( "\t", "&nbsp;&nbsp;");// 替换跳格
		     html = html.replace( " ", "&nbsp;");// 替换空格
		     html = html.replace("<", "&lt;");//<
		     html = html.replaceAll( ">", "&gt;");//>
		     html = html.replaceAll( "\n", "");//换行
		     html = html.replaceAll( "\r", "");//换行
		     return html;
	}

	/**
	 * html转义
	 * @param content
	 * @return
	 */
	public static String escapeTohtml(String content) {
		 if(content==null) return "";
		     String html = content;
		     html = html.replace( "&apos;", "'");//'
		     html = html.replaceAll("&amp;", "&");//&
		     html = html.replace( "&quot;", "\"");  //"
		     html = html.replace( "&nbsp;&nbsp;", "\t");// 替换跳格
		     html = html.replace("&nbsp;", " ");// 替换空格
		     html = html.replace("&lt;", "<");//<
		     html = html.replaceAll( "&gt;", ">");//>
		     return html;
	}

	/**
	 * 基本功能：过滤指定标签及其内容
	 * 如果不指定标签 默认['html','style','script','link']
	 *
	 * @param inputString
	 * @param tags
	 * @return
	 */
    public static String fiterHtmlTag(String inputString,Object... tags) {
    	  String htmlStr = inputString; //含html标签的字符串
    	  String textStr ="";
    	  java.util.regex.Pattern pattern;
	      java.util.regex.Matcher matcher;
	      try {
	    	  if(tags.length==0){
	    		  tags = new String[]{"html","style","script","link"};
	    	  }
		      for (int i = 0; i < tags.length; i++) {
		    	  String tag = (String) tags[i];
		    	  String regEx = "";
		    	  if("link".equalsIgnoreCase(tag)){
		    		  regEx = "<[\\s]*?"+tag+"[^>]*?[\\s\\S]*?\\/>";
		    	  }else{
		    		  regEx = "<[\\s]*?"+tag+"[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?"+tag+"[\\s]*?>"; //定义tag的正则表达式{或<tag[^>]*?>[\\s\\S]*?<\\/tag> }
		    	  }
		    	  pattern = Pattern.compile(regEx,Pattern.CASE_INSENSITIVE);
		    	  matcher = pattern.matcher(htmlStr);
		          htmlStr = matcher.replaceAll(""); //过滤script标签
		      }
	          textStr = htmlStr;
	      }catch(Exception e) {
	    	  logger.error("fiterHtmlTag: " + e.getMessage());
	      }

	      return textStr;//返回文本字符串
    }

	/**
	 * 根据ip获取省市信息
	 * @param ip
	 * @return
	 */
	public static String[] getAreaInfoList(String ip){
		String urlString = "";
		String[] areaArr = new String[10];
		String[] newAreaArr = new String[2];
		urlString = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?ip="+ip;
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"gb2312"));
			String line ="";
			StringBuffer resultBuffer = new StringBuffer();
			while ((line=br.readLine())!=null) {
				resultBuffer.append(line);

			}
			br.close();
			is.close();
			conn.disconnect();
			areaArr = (resultBuffer.toString()).split("	");
			newAreaArr[0] = areaArr[4];
			newAreaArr[1] = areaArr[5];
		} catch (Exception e) {
			// TODO: handle exception
		}

		return newAreaArr;
	}



	 /***
     * json字符串转java List
     * @param rsContent
     * @return
     * @throws Exception
     */
    private static List<Map<String, String>> jsonStringToList(String rsContent)
    {
    	List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
    	try{
    		if(!Common1.empty(rsContent)){
        		JSONArray arry = JSONArray.fromObject(rsContent);
                System.out.println("json字符串内容如下");
                System.out.println(arry);
                for (int i = 0; i < arry.size(); i++)
                {
                    JSONObject jsonObject = arry.getJSONObject(i);
                    Map<String, String> map = new HashMap<String, String>();
                    for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();)
                    {
                        String key = (String) iter.next();
                        String value = jsonObject.get(key).toString();
                        map.put(key, value);
                    }
                    rsList.add(map);
                }
        	}
    	}catch(Exception e)
		{
			throw new RuntimeException(e);
		}
        return rsList;
    }

    /**
     * 获取发送短信  时用户的昵称
     * @author cheng
     * 2014年5月7日
     * 下午2:49:19
     * @param id 用户主键
     * @param username 用户昵称
     * @param name 用户姓名
     * @return
     */
    public static String getNameforSend(Object id,Object username , Object name){
    	if(Common1.empty(name)){
    		if(checkContinouus(username, 7)){
    			return "用户" ;
    		}else{
    			return username.toString() ;
    		}
    	}else{
    		return name.toString();
    	}

//    	int uid = DataTypeUtil.getInteger(id);
//    	String uids ="161,185,882,1149,1391,1401,1422,1446,1517,1595,1598,1749,1751";
//    	if(uids.contains(String.valueOf(uid))){
//    		if(Common.empty(name)){
//    			return "用户" ;
//    		}else{
//    			return name.toString();
//    		}
//    	}else{
//    		if(Common.empty(name)){
//    			return username.toString() ;
//    		}else{
//    			return name.toString();
//    		}
//    	}
    }
    /**
     * 判断字符串中是否包含连续的n位数字
     * @author cheng
     * 2014年5月12日
     * 上午9:53:24
     * @param form_item 要验证的字符串
     * @param num 位数
     * @return 如果包含 返回true 否则 false
     */
    public static boolean checkContinouus(Object form_item,int num){
    	if(empty(form_item)){
    		return true ;
    	}
		Pattern p = Pattern.compile(".*\\d{"+num+"}.*");
        Matcher m = p.matcher(form_item.toString());
        return m.matches();
	}

    /**
     * 抽奖
     * @return Map，包含：  prize_id   奖品id
     * 				    reward   奖励
     *                  reward_type    对应的奖励类型
     *                  content 奖励的描述
     */
    public static Map<String,Object> getLotteryType(List<Map<String, Object>> prizes){
    	/**
    	 * 根据概率，返回信息
    	 *      1元红包  60%  == > 0
				5元红包  25% == > 1
				10元红包 14% == > 2
				50元红包  1% == > 3
				100元红包  0% == > 4
				1000元红包  0% == > 5
				小米盒子  0% == > 6
				iPad   0% == > 7
    	 */
    	int rand = rand(100);
    	if(!Common1.empty(prizes)){
    		Double lower_limit = (double) 0;
    		Double upper_limit = (double) 0;
    		Boolean flag = false;
    		for(Map<String, Object> map:prizes){
    			if(Common1.empty(map.get("id")) || Common1.empty(map.get("prize_name")) ||  Common1.empty(map.get("probability"))
    					|| Common1.empty(map.get("reward")) || Common1.empty(map.get("reward_type")) || Common1.empty(map.get("content"))
    					|| Common1.empty(map.get("pic"))){
    				return null;
    			}
    			map.put("prize_id", map.get("id"));
    			lower_limit = upper_limit;
    			upper_limit = lower_limit + Double.valueOf(map.get("probability")+"");
    			if(lower_limit<upper_limit){
    				if(lower_limit<rand && rand<=upper_limit){
    					flag = true;
        			}
    			}else{
    				flag = true;
    			}
    			if(flag){
    				return map;
    			}
    		}
    	}
    	return null;
    }

    public static String Html2Text(String inputString) {
      String htmlStr = inputString; //含html标签的字符串
      String textStr ="";
      Pattern p_script;
      Matcher m_script;
      Pattern p_style;
      Matcher m_style;
      Pattern p_html;
      Matcher m_html;

      try {
          String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
          String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
          String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

          p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
          m_script = p_script.matcher(htmlStr);
          htmlStr = m_script.replaceAll(""); //过滤script标签

          p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
          m_style = p_style.matcher(htmlStr);
          htmlStr = m_style.replaceAll(""); //过滤style标签

          p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
          m_html = p_html.matcher(htmlStr);
          htmlStr = m_html.replaceAll(""); //过滤html标签

       textStr = htmlStr;

      }catch(Exception e) {
              logger.error("Html2Text: " + e.getMessage());
      }

      return textStr;//返回文本字符串
    }

    /**
     * 判断字符串是否是整数
     */
    public static boolean isInteger(String value) {
     try {
         Integer.parseInt(value);
         return true;
     } catch (NumberFormatException e) {
         return false;
     }
    }

    /**
     * 判断字符串是否是整数
     */
    public static boolean isDouble(String value) {
     try {
         Double.parseDouble(value);
         return true;
     } catch (NumberFormatException e) {
         return false;
     }
    }

    /**
     * 随机生成字母、数字字符串（6个字符）
     * @return
     */
 	public static String randomString(){
		String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random  random = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<6;i++){
			int num = random.nextInt(62);
			sb.append(str.charAt(num));
		}
		return sb.toString();
	}

    /**
     * 随机生成手机号码
     * @return
     */
    public static String randomPhoneString(){
        Random random = new Random();
        StringBuffer sb = new StringBuffer().append("13");
        for(int i=0;i<9;i++){
            int num = random.nextInt(10);
            sb.append(num);
        }
        return sb.toString();
    }

 	/**
 	 * 替换字符串中的反斜杠
 	 * @param parameter
 	 * @return
 	 */
	public static String replaceStr(String parameter) {
		// TODO Auto-generated method stub
		if(!Common1.empty(parameter)){
			parameter = parameter.replaceAll("\\\\", "");
		}
		return parameter;
	}

	/**
     * 随机优惠券编号:yyyyMMdd（8位）+ 随机字母或数字（4位）
     * @return
     */
 	public static String randomJiaxiquanCode(){
 		String date = TimeUtil.getString(new Date(), "yyyyMMdd");
		String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random  random = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<4;i++){
			int num = random.nextInt(62);
			sb.append(str.charAt(num));
		}
		return date+sb.toString();
	}
	public static void main(String[] args) throws UnknownHostException, SocketException {
		// TODO Auto-generated method stub
		
		//得到IP，输出PC-201309011313/122.206.73.83
		InetAddress ia = InetAddress.getLocalHost();
		System.out.println(ia);
		getLocalMac(ia);
	}
	private static void getLocalMac(InetAddress ia) throws SocketException {
		// TODO Auto-generated method stub
		//获取网卡，获取地址
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		System.out.println("mac数组长度："+mac.length);
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i<mac.length; i++) {
			if(i!=0) {
				sb.append("-");
			}
			//字节转换为整数
			int temp = mac[i]&0xff;
			String str = Integer.toHexString(temp);
		//	System.out.println("每8位:"+str);
			if(str.length()==1) {
				sb.append("0"+str);
			}else {
				sb.append(str);
			}
		}
		System.out.println("本机MAC地址:"+sb.toString().toUpperCase());
	}
 		
 	
}
	
