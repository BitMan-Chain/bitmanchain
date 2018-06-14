package com.shoufubang.pay.lianpay.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataTypeUtil{

    public static boolean isNumeric(String str)
    {
        if (null == str || str.equals("") || str.equals("null"))
        {
            return false;
        }
        if (isInt(str))
            return true;
        Pattern pattern = Pattern.compile("^[0-9]{1,}\\.[0-9]{1,2}$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches())
        {
            return false;
        }
        return true;
    }

    public static boolean isNumericOrNull(String str)
    {
        if (null == str || str.equals("") || str.equals("null"))
        {
            return true;
        }
        if (isInt(str))
            return true;
        Pattern pattern = Pattern.compile("^[0-9]{1,}\\.[0-9]{1,}$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches())
        {
            return false;
        }
        return true;
    }

    public static boolean isInt(String str)
    {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches())
        {
            return false;
        }
        return true;
    }

    /**
     * 数据规则校验
     * 1.若字符串是连续的数字或字母组成,返回true
     * 2.若字符串是相同的数字或字母组成,返回true
     * @param targetStr：目标字符串
     * @return
     */
    public static boolean numberValidator(String targetStr)
    {
        if (FuncUtils.isNull(targetStr))
        {
            return false;
        }
        int[] strArray = new int[targetStr.length()];
        for (int x = 0; x < targetStr.length(); x++)
        {
            strArray[x] = targetStr.charAt(x);
        }
        boolean con = false;
        boolean isBig = false;
        for (int i = 0; i < strArray.length - 1; i++)
        {
            if (i == 0)
            {
                isBig = (strArray[i] - strArray[i + 1] == 1 || strArray[i]
                        - strArray[i + 1] == 0) ? true : false;
            }
            if (isBig)
                con = strArray[i] - strArray[i + 1] == 1
                        || strArray[i] - strArray[i + 1] == 0;
            else
                con = strArray[i] - strArray[i + 1] == -1;
            if (!con)
                return con;
        }
        return con;
    }

    /**
     * 
     * @param d
     * @return
     */
    public static boolean checkDouble(Double d)
    {
        if (null == d || d.compareTo(new Double(0)) <= 0)
        {
            return false;
        }
        return true;
    }

    public static String[] spiltStr(String fieldsru, String tag)
    {
        char dot = tag.charAt(0);
        String field;
        field = fieldsru + dot;
        int num = 0;
        int field_len = field.length();
        for (int i = 0; i < field_len; i++)
        {
            if (field.charAt(i) == dot)
            {
                num++;
            }
        }
        String[] returnarray = new String[num];
        int begin = 0;
        int end;
        for (int j = 0; j < num; j++)
        {
            end = field.indexOf(dot, begin);
            returnarray[j] = field.substring(begin, end);
            begin = end + 1;
        }
        return returnarray;
    }

    public static String toUtf8String(String s)
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255)
            {
                sb.append(c);
            } else
            {
                byte[] b;
                try
                {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex)
                {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++)
                {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    /**
     * 字符串转化为整形
     * @param str
     * @return
     */
    public static int parseInt(String str)
    {
        if (str == null || str.equals(""))
            return 0;
        return Integer.parseInt(str);
    }
}
