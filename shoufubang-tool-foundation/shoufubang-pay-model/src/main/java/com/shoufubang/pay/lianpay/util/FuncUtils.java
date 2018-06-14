package com.shoufubang.pay.lianpay.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 * 公共处理组件
 * @author shmily
 * @date May 25, 2011 2:35:38 PM
 */
public class FuncUtils{

    /**
     * 获取唯一标识uuid
     * @return
     */
    public static String getUuid()
    {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /***************************************************************************
     * 方法名:getSysDate 功能描述:取系统日期 参数说明: 返回参数:返回系统日期串 编写:yajs 日期:2006.12.13 修改记录:
     **************************************************************************/
    public static String getSysDate()
    {
        SimpleDateFormat formdate = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date curDate = new java.util.Date();
        String ss;
        ss = formdate.format(curDate);
        return ss;
    }

    /***************************************************************************
     * 方法名:getSysTime 功能描述:取系统时间 参数说明: 返回参数:返回系统时间串 编写:yaojs 日期:2006.12.13 修改记录:
     **************************************************************************/
    public static String getSysTime()
    {
        SimpleDateFormat formdate = new SimpleDateFormat("HH:mm:ss");
        java.util.Date curDate = new java.util.Date();
        String ss;
        ss = formdate.format(curDate);
        return ss;
    }

    /***************************************************************************
     * 方法名:WriteFile 功能描述:写文件 参数说明: 返回参数:返回是否写成功 编写:yaojs 日期:2006.12.13 修改记录:
     **************************************************************************/
    public static boolean WriteFile(String fileName, String StrBuf)
            throws IOException
    {
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(StrBuf, 0, StrBuf.length());
        bw.newLine();
        bw.close();
        fw.close();
        return true;
    }

    /***************************************************************************
     * 方法名:FormatStringAddBlank 功能描述:给字符串后补空格 参数说明: 返回参数:返回后补的字符串 编写:yaojs
     * 日期:2006.12.13 修改记录:
     * 
     * @throws UnsupportedEncodingException
     **************************************************************************/
    public static String FormatStringAddBlank(String sReturnBuf, int length)
            throws Exception
    {
        StringBuffer tempBuffer = new StringBuffer();
        if (null == sReturnBuf || sReturnBuf.equals("")
                || sReturnBuf.equals("null"))
        {
            for (int i = 0; i < length; i++)
            {
                tempBuffer.append(" ");
            }
            return tempBuffer.toString();
        }
        String s2 = new String(sReturnBuf.getBytes("GB2312"), "ISO8859_1");
        int iLength = s2.length();
        if (length > iLength)
        {
            tempBuffer.append(sReturnBuf);
            for (int j = 0; j < length - iLength; j++)
            {
                tempBuffer.append(" ");
            }
            sReturnBuf = tempBuffer.toString();
        } else if (length < iLength)
        {
            sReturnBuf = absoluteSubstring(sReturnBuf, 0, length);
        }
        return sReturnBuf;
    }

    /***************************************************************************
     * 方法名:getStringLength 功能描述:获取字符的长度,包括汉字的长度 参数说明: 返回参数:返回长度 编写:yaojs
     * 日期:2006.12.13 修改记录:
     * 
     * @throws UnsupportedEncodingException
     **************************************************************************/
    public static int getStringLength(String s1) throws Exception
    {
        if (null == s1 || s1.equals(""))
            return 0;
        String s2 = new String(s1.getBytes("GB2312"), "ISO8859_1");
        return s2.length();
    }

    /***************************************************************************
     * 方法名:FormatStringAddZero 功能描述:给字符串前补0 参数说明: 返回参数:返回前补的字符串 编写:yaojs
     * 日期:2006.12.13 修改记录:
     **************************************************************************/
    public static String FormatStringAddZero(String sReturnBuf, int length)
    {
        StringBuffer tempBuffer = new StringBuffer();
        if (null == sReturnBuf || sReturnBuf.equals("")
                || sReturnBuf.equals("null"))
        {
            for (int i = 0; i < length; i++)
            {
                tempBuffer.append("0");
            }
            return tempBuffer.toString();
        }
        int iLength = sReturnBuf.length();
        if (length > iLength)
        {
            for (int j = 0; j < length - iLength; j++)
            {
                sReturnBuf = "0" + sReturnBuf;
            }
        } else if (length < iLength)
        {
            sReturnBuf = absoluteSubstring(sReturnBuf, 0, length);
        }
        return sReturnBuf;
    }

    /***************************************************************************
     * 方法名:MultString 功能描述:将字符串金额变为*100后的金额 参数说明:sMoney：需要变更的字符串， iEr：需要乘的数
     * flag：+代表乘,-代表除 返回参数:返回前补的字符串 编写:yaojs 日期:2006.12.13 修改记录:
     **************************************************************************/
    public static String MultString(String sMoney, int iEr, String flag)
    {
        if (null == sMoney || sMoney.equals(""))
            return "";
        if (null == flag || flag.equals(""))
            return "";
        sMoney = stringMoveZero(sMoney);
        double iTemp = new Double(sMoney).doubleValue();
        if (flag.equals("+"))
        {
            iTemp = iTemp * iEr;
            int aa = new Double(iTemp).intValue();
            return String.valueOf(aa);
        } else if (flag.equals("-"))
        {
            iTemp = iTemp / iEr;
        }
        return String.valueOf(iTemp);
    }

    /***************************************************************************
     * 方法名:MultStringExt 功能描述:将字符串金额变为*100后的金额 参数说明:sMoney：需要变更的字符串， iEr：需要乘的数
     * flag：+代表乘,-代表除 返回参数:返回前补的字符串 编写:yaojs 日期:2006.12.13 修改记录:
     **************************************************************************/
    public static String MultStringExt(String sMoney, int iEr, String flag)
    {
        if (null == sMoney || sMoney.equals(""))
            return "";
        if (null == flag || flag.equals(""))
            return "";
        sMoney = stringMoveZero(sMoney);
        double iTemp = new Double(sMoney).doubleValue();
        if (flag.equals("+"))
        {
            iTemp = iTemp * iEr;
            double aa = new Double(iTemp).doubleValue();
            return String.valueOf(aa);
        } else if (flag.equals("-"))
        {
            iTemp = iTemp / iEr;
        }
        return String.valueOf(iTemp);
    }

    /***************************************************************************
     * 方法名:stringMoveZero 功能描述:将字符串金额中负号前的0去掉 参数说明:sMoney：需要变更的字符串， 返回参数:返回的字符串
     * 编写:yaojs 日期:2006.12.13 修改记录:
     **************************************************************************/
    public static String stringMoveZero(String sMoney)
    {
        if (null == sMoney || sMoney.equals(""))
            return "";
        int ilen = sMoney.indexOf("-");
        if (ilen > 0)
            return sMoney.substring(ilen, sMoney.length());
        return sMoney;
    }

    /**
     * 对一个字符串的绝对长度进行拆解(如果遇到汉字字符会把它当作两个字符处理)
     * 
     * @param s
     *            传入的字串
     * @param start
     *            起始绝对位置
     * @param end
     *            终止绝对位置
     * @return 返回的字串 编写:yaojs 日期:2006.12.13
     */
    public static String absoluteSubstring(String s, int start, int end)
    {
        if (s == null || s.equals(""))
        {
            return "";
        }
        try
        {
            String s2 = new String(s.getBytes("GB2312"), "ISO8859_1");
            s2 = s2.substring(start, end);
            return new String(s2.getBytes("ISO8859_1"), "GB2312");
        } catch (Exception e)
        {
            return "";
        }
    }

    /**
     * 对返回的多条记录的字符串进行处理，
     * 
     * @param s
     *            传入的字串
     * @param 单个记录的长度
     * @return 返回字符数组 编写:yaojs 日期:2006.12.13
     * @throws Exception
     */
    public static String[] getAbsoluteSubstringArray(String s, int ilength)
            throws Exception
    {
        if (s == null || s.equals(""))
        {
            return new String[0];
        }
        try
        {
            String s2 = new String(s.getBytes("GB2312"), "ISO8859_1");
            int ilen = s2.length() / ilength;
            if (ilen == 0)
                return new String[0];
            int start = 0;
            int end = ilength;
            String[] returnarray = new String[ilen];
            for (int i = 0; i < ilen; i++)
            {
                String s1 = s2.substring(start, end);
                start = end;
                end = end + ilength;
                returnarray[i] = new String(s1.getBytes("ISO8859_1"), "GB2312");
            }
            return returnarray;
        } catch (Exception e)
        {
            return new String[0];
        }
    }

    /**
     * 对返回的多条记录的字符串进行处理，
     * 
     * @param s
     *            传入的字串
     * @param iLength
     *            短信文本的长度
     * @param 记录数记录本条数据的长度
     * @return 返回字符数组 编写:yaojs 日期:2006.12.13
     * @throws Exception
     */
    public static String[] getAbsoluteSubstringArrayExt(String s, int ilength,
            int extlen) throws Exception
    {
        if (s == null || s.equals(""))
        {
            return new String[0];
        }
        try
        {
            String s2 = new String(s.getBytes("GB2312"), "ISO8859_1");
            int zlen = getStringLength(s);
            int start = 0;
            int end = 0;
            int iRow = new Integer(s2.substring(start, 2)).intValue();
            String[] returnarray = new String[iRow];
            start = start + 2;
            for (int i = 0; i < iRow; i++)
            {
                if (start >= zlen)
                    break;
                int ilen = new Integer(s2.substring(start, start + ilength))
                        .intValue();
                end = start + extlen + ilen;
                String s1 = s2.substring(start, end);
                start = end;
                returnarray[i] = new String(s1.getBytes("ISO8859_1"), "GB2312");
            }
            return returnarray;
        } catch (Exception e)
        {
            return new String[0];
        }
    }

    /**
     * 去除字符串中的所有的空格
     * @param str
     * @return 去除后的字符串
     */
    public static String trimAllBlank(String str)
    {
        return str.replace(" ", "");
    }

    /**
     * 对一个字符串按间隔分解
     * 
     * @param fieldsru
     *            传入的字串
     * @param tag
     *            间隔符
     * @return 返回的一个数组 编写:yaojs 日期:2006.12.13
     */
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

    /**
     * 对一个字符串判断是否为手机号码
     * 
     * @param checkMobile
     *            传入的字串
     * @return ture or false 编写:yaojs 日期:2006.12.13
     */
    public static boolean checkMobile(String fieldsru)
    {
        if (FuncUtils.isNull(fieldsru))
            return false;
        return Pattern.matches("1[3458][0-9]{9}", fieldsru.trim());
    }

    /**
     * 解密时转换成byte算法时补位
     * 
     * @param strhex
     *            传入的字串符
     * @return byte 编写:yaojs 日期:2006.12.13
     */
    public static byte[] hex2byte(String strhex)
    {
        if (strhex == null)
        {
            return null;
        }
        int l = strhex.length();
        if (l % 2 == 1)
        {
            return null;
        }

        byte[] b = new byte[l / 2];

        for (int i = 0; i != l / 2; i++)
        {

            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
                    16);
        }
        return b;
    }

    public static String strchange(String str)
    {
        if (null == str || str.equals("NULL") || str.equals("null"))
        {
            return "";
        } else
            return str;
    }

    public static boolean isNull(String str)
    {
        if (null == str || str.equals("NULL") || str.equals("null")
                || str.equals(""))
        {
            return true;
        } else
            return false;
    }

    public static String stringToThouMony(String sMoney)
    {
        if (null == sMoney || sMoney.equals(""))
            return "";
        double d = new Double(sMoney).doubleValue();
        DecimalFormat df = new DecimalFormat("##,###,###,###,##0.00");
        return df.format(d);
    }

    public static String FormatMoney(String sMoney)
    {
        if (null == sMoney || sMoney.equals(""))
            return "";
        double d = new Double(sMoney).doubleValue();
        DecimalFormat df = new DecimalFormat("##########0.00");
        return df.format(d);
    }

    /**
     * 对double类型进行造型,提供保留的小数位数,不进行四舍五入
     * @param val
     * @param precision
     * @return
     */
    public static double roundDouble(double val, int precision)
    {
        double factor = Math.pow(10, precision);
        return Math.floor(val * factor) / factor;
    }

    /**
     * 对平台手续费进行格式化
     * 1.首先保留四位小数进行四舍五入
     * 2.取结果,保留三位小数
     * @param fee
     * @param precision
     * @return
     */
    public static String formatFee(double fee, int precision)
    {
        DecimalFormat format = new DecimalFormat("#0.0000");
        return String.valueOf(roundDouble(
                Double.parseDouble(format.format(fee)), precision));
    }

    /**
     * 用户注册生成随机密码
     * @param index
     * @return
     */
    public static String getRandomNumber(int index)
    {
        // 生成随类
        Random random = new Random();
        String sRand = "";
        for (int i = 0; i < index; i++)
        {
            String rand = null;
            if (random.nextInt(6) == i)
            {
                rand = String.valueOf((char) (65 + random.nextInt(26)));
            } else if (random.nextInt(6) == i + 1)
            {
                rand = String.valueOf((char) (97 + random.nextInt(26)));
            } else
            {
                rand = String.valueOf(random.nextInt(10));
            }
            sRand += rand;
        }
        return sRand;
    }

    /**
     * 功能描述：取得隐藏后的手机号码
     * 手机号码隐藏第四位到第七位，中间用****代替
     * 例:13845671234 返回 138****1234
     * @param mobi
     * @return
     */
    public static String hiddenMobile(String mobi)
    {
        if (!Validator.isCellPhone(mobi))
        {
            return mobi;
        }
        CharSequence target = mobi.subSequence(3, 7);
        CharSequence replacement = new String("****");
        return mobi.replace(target, replacement);
    }

    /**
     * 功能描述：取得隐藏后的邮箱账号
     * 用户名长度=（1-3）首字母+***@abc.com；
     * 用户名长度>3 用户名最后三位用***代替；
     * 邮箱后缀全文显示。
     * @param email
     * @return
     */
    public static String hiddenEmail(String email)
    {
        if (!Validator.isMailAddress(email))
        {
            return email;
        }
        String[] temp = email.split("@");
        if (temp.length != 2)
        {
            return email;
        } else
        {
            StringBuffer sb = new StringBuffer();
            String pre = temp[0];
            if (pre.length() < 4)
            {
                sb.append(pre.substring(0, 1)).append("***").append("@")
                        .append(temp[1]);
            } else
            {
                CharSequence target = pre.subSequence(pre.length() - 3,
                        pre.length());
                CharSequence replacement = new String("***");
                pre.replace(target, replacement);
                sb.append(pre.replace(target, replacement)).append("@")
                        .append(temp[1]);
            }
            return sb.toString();
        }
    }

    /**
     * 里转元方法
     * @param money
     * @return
     */
    public static String formatLTYMoneny(String money)
    {
        if (money == null || money.trim().equals("") || money.equals("null"))
        {
            return "0";
        }
        try
        {
            double dmoney = Double.parseDouble(money);
            dmoney /= 1000;
            DecimalFormat format = new DecimalFormat("#0.00");
            return format.format(dmoney);
        } catch (Exception e)
        {
            return "0";
        }
    }

    /**
     * 
     * 功能描述：元转里方法
     * @param yuan
     * @return
     */
    public static String formatYTLMoneny(String yuan)
    {
        try
        {
            double dYuan = Double.parseDouble(yuan);
            double dLi = dYuan * 1000;
            DecimalFormat format = new DecimalFormat("#");
            return format.format(dLi);
        } catch (Exception e)
        {
            return "0";
        }

    }

    /**
     * 显示卡号后四位
     * @param cardNo
     * @return 卡号后四位
     */
    public static String hiddenCard(String cardNo)
    {
        if (null == cardNo)
        {
            return null;
        } else if (cardNo.length() <= 4)
        {
            return cardNo;
        } else
        {
            return cardNo.substring(cardNo.length() - 4, cardNo.length());
        }
    }

    /**
     * 隐藏身份证号的方法
     * @param idNo
     * @return 一前一后不隐藏 如：5*****************5
     */
    public static String hiddenCertifiID(String idNo)
    {

        String head = idNo.substring(0, 1);
        String end = idNo.substring(idNo.length() - 1);
        String sbf = head;
        for (int i = 0; i < idNo.length() - 2; i++)
        {
            sbf += "*";
        }
        sbf += end;
        return sbf;
    }

    /**
     * 功能描述：序列号处理-->微软格式 序列号头字母大于等于8的，添加00
     * @param numer
     * @return
     */
    public static String buildCertSerialnumber(String numer)
    {
        if (isNull(numer))
        {
            return numer;
        }
        String first = numer.substring(0, 1);
        if (Integer.valueOf(first, 16) >= 8)
        {
            return "00" + numer;
        }
        return numer;
    }

    /**
     * 功能描述：日期改变方法 
     * @param date
     * @param day 为改变日期的天数
     * @return
     */
    public static String changeDay(String date, int day)
    {
        if (date != null && date.trim().length() > 0)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try
            {
                Date newDate = sdf.parse(date);
                Calendar cal = Calendar.getInstance();
                cal.setTime(newDate);
                cal.add(Calendar.DATE, day);
                Date nextDate = cal.getTime();
                String next_dateStr = new SimpleDateFormat("yyyy-MM-dd")
                        .format(nextDate);
                return next_dateStr;
            } catch (Exception e)
            {
                return date;
            }

        }
        return date;
    }

    /**
     * 
     * 功能描述：获取真实的IP地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        if (!isNull(ip) && ip.contains(","))
        {
            String[] ips = ip.split(",");
            ip = ips[ips.length - 1];
        }
        return ip;
    }

    /**
     * 字符串前补0
     * @param sReturnBuf
     * @param length
     * @return
     */
    public static String formatStringAddZero(String sReturnBuf, int length)
    {
        StringBuffer tempBuffer = new StringBuffer();
        if (null == sReturnBuf || sReturnBuf.equals("")
                || sReturnBuf.equals("null"))
        {
            for (int i = 0; i < length; i++)
            {
                tempBuffer.append("0");
            }
            return tempBuffer.toString();
        }
        if (sReturnBuf.length() > length)
        {
            return sReturnBuf;
        }
        int iLength = sReturnBuf.length();
        if (length > iLength)
        {
            for (int j = 0; j < length - iLength; j++)
            {
                sReturnBuf = "0" + sReturnBuf;
            }
        } else if (length < iLength)
        {
            sReturnBuf = FuncUtils.absoluteSubstring(sReturnBuf, 0, length);
        }
        return sReturnBuf;
    }

    /**
     * 加密方法
     * @param str
     * @return
     */
    public static String encrytion(String str)
    {
        if (FuncUtils.isNull(str) || str.length() < 2)
        {
            return str;
        }
        int length = str.length();
        // 姓名加密
        if (length < 5)
        {
            return "*" + str.substring(1);
        }
        // 手机加密
        if (length == 11)
        {
            return str.substring(0, 3) + "****" + str.substring(7);
        }
        // 其他 末四位加密
        return str.substring(0, length - 4) + "**";
    }

    /**
     * 
     * 功能描述: 非注册用户
     * @param id
     * @return
     */
    public static boolean isMachineId(String id)
    {
        if (isNull(id))
        {
            return false;
        }
        if (id.length() != 32)
        {
            return false;
        }
        return true;
    }

    /**
     * 
     * 功能描述：手势码4-9位1-9数字
     * @param signs
     * @return
     */
    public static boolean isSigns(String signs)
    {
        if (FuncUtils.isNull(signs))
            return false;
        return Pattern.matches("[1-9]{4,9}", signs.trim());
    }

    /**
     * 流转字符串方法
     * 
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is)
    {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try
        {
            reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (null != reader)
                {
                    reader.close();
                }
            } catch (IOException e)
            {

            }
        }
        return sb.toString();
    }

    public static boolean isBankCard(String str)
    {
        if (str.matches("^\\d{15,19}$"))
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * 校验信用卡有限期
     * @param exp_date
     * @return
     */
    public static boolean isCard_ExpDate(String exp_date)
    {
        if (isNull(exp_date))
        {
            return false;
        }
        if (!exp_date.matches("^\\d{4}$"))
        {
            return false;
        }
        String month = exp_date.substring(exp_date.length() - 2,
                exp_date.length());
        if (Integer.parseInt(month) > 12 || Integer.parseInt(month) < 1)
        {
            return false;
        }
        if (Integer.parseInt(exp_date) < Integer.parseInt(DateUtil
                .getCurrentYearMonthStr()))
        {
            return false;
        }
        return true;
    }

    /**
     * 生成待签名串
     * @param paramMap
     * @return
     */
    public static String genSignData(JSONObject jsonObject)
    {
        StringBuffer content = new StringBuffer();

        // 按照key做首字母升序排列
        List<String> keys = new ArrayList<String>(jsonObject.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < keys.size(); i++)
        {
            String key = (String) keys.get(i);
            if ("sign".equals(key))
            {
                continue;
            }
            String value = (String) jsonObject.getString(key);
            // 空串不参与签名
            if (isNull(value))
            {
                continue;
            }
            content.append((i == 0 ? "" : "&") + key + "=" + value);

        }
        String signSrc = content.toString();
        if (signSrc.startsWith("&"))
        {
            signSrc = signSrc.replaceFirst("&", "");
        }
        return signSrc;
    }

    /**
     * 校验带中文 字符长度 oracle中文占3位
     * 
     * @param str
     * @return
     */
    public static int checkChineselen(String str)
    {
        int len = 0;
        if (FuncUtils.isNull(str))
        {
            return len;
        }
        for (int i = 0; i < str.length(); i++)
        {
            String bb = str.substring(i, i + 1);
            boolean cc = java.util.regex.Pattern.matches("[\u4E00-\u9FA5]", bb);
            if (cc)
            {
                len = len + 3;
            } else
            {
                len = len + 1;
            }
        }
        return len;
    }

    /**
     * 功能描述：金额校验，必须大于0.01
     * @param money
     * @return
     */
    public static boolean isMoney(String money)
    {
        if (FuncUtils.isNull(money))
        {
            return false;
        }
        Pattern pattern = Pattern.compile("^[0-9]{0,}[.]{0,1}[0-9]{0,2}$");
        if (!pattern.matcher(money).matches())
        {
            return false;
        }
        if (Double.parseDouble(money) < 0.01)
        {
            return false;
        }
        return true;
    }

    /**
     * 功能描述：比较金额是否相等
     * @param money1
     * @param money2
     * @return
     */
    public static boolean isMoneyEqual(String money1, String money2)
    {
        if (!isMoney(money1) || !isMoney(money2))
        {
            return false;
        }
        long long1 = Double.doubleToLongBits(Double.parseDouble(money1));
        long long2 = Double.doubleToLongBits(Double.parseDouble(money2));
        if (long1 == long2)
        {
            return true;
        }
        return false;
    }

    /**
     * 隐藏银行卡号 格式：622662******9275
     * @param bankcard
     * @return
     * @author guoyx
     */
    public static String hiddenBankCard(String bankcard)
    {
        if (!isBankCard(bankcard))
        {
            return bankcard;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bankcard.length(); i++)
        {
            if (i > 5 && i < bankcard.length() - 4)
            {
                sb.append("*");
            } else
            {
                sb.append(bankcard.charAt(i));
            }

        }
        return sb.toString();
    }

    /**
     * 格式化银行卡
     *
     * @return
     */
    public static String formatCardNumber(String card)
    {
        String temp = "";
        if (FuncUtils.isNull(card))
        {
            return card;
        }
        card = card.replaceAll(" ", "");
        int len = card.length();
        for (int i = 0; i < len; i++)
        {
            if (i % 4 == 0)
            {
                int end = i + 4;
                if (end > len)
                {
                    end = len;
                }
                temp = temp + card.substring(i, end) + " ";
            }
        }
        if (temp.endsWith(" "))
        {
            temp = temp.substring(0, temp.length() - 1);
        }
        return temp;
    }

    public static void main(String[] args)
    {

        System.out.println(isCard_ExpDate(null));
        System.out.println(isCard_ExpDate(""));
        System.out.println(isCard_ExpDate("123"));
        System.out.println(isCard_ExpDate("12356"));
        System.out.println(isCard_ExpDate("1235"));
        System.out.println(isCard_ExpDate("1200"));
        System.out.println(isCard_ExpDate("1206"));
        System.out.println(isCard_ExpDate("1209"));
        System.out.println(isCard_ExpDate("1305"));
        System.out.println(isCard_ExpDate("1306"));
        System.out.println(isCard_ExpDate("1307"));
    }
}
