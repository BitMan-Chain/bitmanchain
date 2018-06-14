package com.shoufubang.pay.lianpay.util;

import java.util.regex.Pattern;

/**
 * 正则表达式校验
 * @author shmily
 * @date Feb 24, 2011 10:54:59 AM
 */
public class Validator{

    /**
     * 检查是否为中文名称
     * @param s
     * @return
     */
    public static boolean isChinaName(String name)
    {
        if (FuncUtils.isNull(name))
        {
            return false;
        }
        return Pattern.matches("[\u4e00-\u9fa5]{2,4}", name);
    }

    /**
     * 检查是否为中文名称
     * @param 校验为2位到10位的中文
     * @return
     */
    public static boolean isChinaName2(String name)
    {
        if (FuncUtils.isNull(name))
        {
            return false;
        }
        return Pattern.matches("[\u4e00-\u9fa5]{2,10}", name);
    }

    /**
     * 手机号码检查
     * @param str
     * @return
     */
    public static boolean isCellPhone(String str)
    {
        if (FuncUtils.isNull(str))
            return false;
        return Pattern.matches("1[34578][0-9]{9}", str.trim());
    }

    /**
     * 手机号码检查
     * @param str
     * @return 可以不填写
     */
    public static boolean isCellPhone2(String str)
    {
        return Pattern.matches("^(1[3458][0-9]{9})?$", str.trim());
    }

    /**
     * 固定号码检查
     * @param str
     * @return只检查格式为057158110033
     */
    public static boolean isFixedPhone(String str)
    {
        if (FuncUtils.isNull(str))
            return false;
        return Pattern.matches("^[08][0-9]{10,11}$", str.trim());
    }

    /**
     * 固定号码检查
     * @param str 可以不填写
     * @return 检查0571（021）-58110033（5432191）格式的电话号码
     */
    public static boolean isFixedPhone2(String str)
    {
        return Pattern.matches("^(0\\d{2,3}-\\d{7,8})?$", str.trim());
    }

    /**
     * 检查EMAIL 
     * 地址结尾必须是以com|cn|com|cn|net|org|gov|gov.cn|edu|edu.cn结尾
     * @param str
     */
    public static boolean isMailAddress(String str)
    {
        if (FuncUtils.isNull(str))
            return false;
        return Pattern.matches(
                "\\w+\\@\\w+\\.(com|cn|com.cn|net|org|gov|gov.cn|edu|edu.cn)",
                str.trim());
    }

    /**
     * 4-15字母和数字
     * 不分区大小写,不能以数字开头
     * @param str
     */
    public static boolean isNr(String str)
    {
        if (FuncUtils.isNull(str))
            return false;
        return Pattern.matches("^[a-zA-Z][0-9a-zA-Z]{3,14}$", str.trim());
    }

    /**
     * 检查密码有效性
     * 密码规则：6-20位数字、字母、或字符组合
     * 不能完全连续或完全一致的字符
     * @param password
     * @return
     */
    public static boolean isPassword(String password)
    {
        if (FuncUtils.isNull(password))
            return false;
        password = password.trim();
        if (isOneChar(password))
        {
            return false;
        }
        if (isSeriesChar(password))
        {
            return false;
        }
        return Pattern.matches("^[0-9a-zA-Z~!@#$%^&*()_+|\\[\\]\\{\\}]{6,20}$",
                password);
    }

    /**
     * 
     * 判断字符串中的字符是否完全相同，完全相同返回TRUE
     * 空返回FALSE  只有一个字符返回TRUE
     * @param password 
     * @return
     */
    public static boolean isOneChar(String password)
    {
        if (FuncUtils.isNull(password))
        {
            return false;
        }
        char[] chars = new char[password.length()];
        password.getChars(0, password.length(), chars, 0);
        char temp = chars[0];
        for (char p : chars)
        {
            if (temp != p)
            {
                return false;
            }
        }
        return true;
    }

    private static boolean isSeriesChar(String password)
    {
        if (FuncUtils.isNull(password))
        {
            return false;
        }
        char[] chars = new char[password.length()];
        password.getChars(0, password.length(), chars, 0);
        char temp = chars[0];
        for (int i = 1; i < chars.length; i++)
        {
            if (Math.abs(temp - chars[i]) != 1)
            {
                return false;
            }
            temp = chars[i];
        }
        return true;
    }

    /**
     * 15或者18位身份证校验
     * @param str
     */
    public static boolean isIdCard(String str)
    {
        if (FuncUtils.isNull(str))
            return false;
        return Pattern.matches("^\\d{15}(\\d{2}[\\dxX])?$", str.trim());
    }

    /**
     * 
     * 功能描述：金额校验，必须大于0.01
     * @param str
     * @return
     */
    public static boolean isMoney(String money)
    {
        if (FuncUtils.isNull(money))
        {
            return false;
        }
        if (!DataTypeUtil.isNumeric(money))
        {
            return false;
        }
        if (Double.parseDouble(money) >= 0.01)
        {
            return true;
        }
        return false;
    }
}
