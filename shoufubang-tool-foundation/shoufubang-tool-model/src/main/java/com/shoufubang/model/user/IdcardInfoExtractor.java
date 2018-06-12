package com.shoufubang.model.user;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import htz.util.lang.Predef;
  
/**  
 *         <p>  
 *         类说明:提取身份证相关信息  
 *         </p>  
 */  
public class IdcardInfoExtractor {   
    // 省份   
    private String province;   
    // 城市   
    private String city;   
    // 区县   
    private String region;   
    // 年份   
    private int year;   
    // 月份   
    private int month;   
    // 日期   
    private int day;   
    // 性别   
    private String gender;   
    // 出生日期   
    private String birthday;   
  
    private Map<String, String> cityCodeMap = new HashMap<String, String>() {   
        {   
            this.put("11", "北京");   
            this.put("12", "天津");   
            this.put("13", "河北");   
            this.put("14", "山西");   
            this.put("15", "内蒙古");   
            this.put("21", "辽宁");   
            this.put("22", "吉林");   
            this.put("23", "黑龙江");   
            this.put("31", "上海");   
            this.put("32", "江苏");   
            this.put("33", "浙江");   
            this.put("34", "安徽");   
            this.put("35", "福建");   
            this.put("36", "江西");   
            this.put("37", "山东");   
            this.put("41", "河南");   
            this.put("42", "湖北");   
            this.put("43", "湖南");   
            this.put("44", "广东");   
            this.put("45", "广西");   
            this.put("46", "海南");   
            this.put("50", "重庆");   
            this.put("51", "四川");   
            this.put("52", "贵州");   
            this.put("53", "云南");   
            this.put("54", "西藏");   
            this.put("61", "陕西");   
            this.put("62", "甘肃");   
            this.put("63", "青海");   
            this.put("64", "宁夏");   
            this.put("65", "新疆");   
            this.put("71", "台湾");   
            this.put("81", "香港");   
            this.put("82", "澳门");   
            this.put("91", "国外");   
        }   
    };   
       
    private IdcardValidator validator = null;   
       
    /**  
     * 通过构造方法初始化各个成员属性  
     */  
    public IdcardInfoExtractor(String idcard) {   
        try {   
            validator = new IdcardValidator();   
            if (validator.isValidatedAllIdcard(idcard)) {   
                if (idcard.length() == 15) {   
                    idcard = validator.convertIdcarBy15bit(idcard);   
                }   
                // 获取省份   
                String provinceId = idcard.substring(0, 2);   
                Set<String> key = this.cityCodeMap.keySet();   
                for (String id : key) {   
                    if (id.equals(provinceId)) {   
                        this.province = this.cityCodeMap.get(id);   
                        break;   
                    }   
                }   
  
                // 获取性别   
                String id17 = idcard.substring(16, 17);   
                if (Integer.parseInt(id17) % 2 != 0) {   
                    this.gender = "1";//男   
                } else {   
                    this.gender = "2";//女
                }   
  
                // 获取出生日期   
                String birthday = idcard.substring(6, 14); 
                StringBuffer sb = new StringBuffer(birthday);
                sb.insert(4, "-");
                sb.insert(7, "-");
                this.birthday =  sb.toString();  
                String[] s = sb.toString().split("-");
                this.year = Predef.toInt(s[0], 0);
                this.month =Predef.toInt(s[1],0);
                this.day = 	Predef.toInt(s[2],0);
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
    }   
  
    /**  
     * @return the province  
     */  
    public String getProvince() {   
        return province;   
    }   
  
    /**  
     * @return the city  
     */  
    public String getCity() {   
        return city;   
    }   
  
    /**  
     * @return the region  
     */  
    public String getRegion() {   
        return region;   
    }   
  
    /**  
     * @return the year  
     */  
    public int getYear() {   
        return year;   
    }   
  
    /**  
     * @return the month  
     */  
    public int getMonth() {   
        return month;   
    }   
  
    /**  
     * @return the day  
     */  
    public int getDay() {   
        return day;   
    }   
  
    /**  
     * @return the gender  
     */  
    public String getGender() {   
        return gender;   
    }   
  
    /**  
     * @return the birthday  
     */  
    public String getBirthday() {   
        return birthday;   
    }   
  
    @Override  
    public String toString() {   
        return "省份：" + this.province + ",性别：" + this.gender + ",出生日期："  
                + this.birthday;   
    }   
    public static int getAge(int birthDateyear,int birth_month) {
    	int age = 0;

    	Date now = new Date();

    	SimpleDateFormat format_y = new
    	SimpleDateFormat("yyyy");
    	SimpleDateFormat format_M = new
    	SimpleDateFormat("MM");

    	String this_year = 
    	format_y.format(now);

  
    	String this_month =
    	format_M.format(now);

    	// 初步，估算
    	age =
    	Integer.parseInt(this_year) - birthDateyear + 1;

    	// 如果未到出生月份，则age - 1
    	if
    	(Integer.parseInt(this_month)- birth_month < 0)
    	age -=
    	1;
    	if (age <
    	0)
    	age =
    	0;
    	return age;
    	}
    public static void main(String[] args) {   
        String idcard = "360721199110124817";   
        IdcardInfoExtractor ie = new IdcardInfoExtractor(idcard);   
        System.out.println(ie.toString());   
    }   
}  