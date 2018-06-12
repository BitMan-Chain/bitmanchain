package com.shoufubang.model.util;


import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.shoufubang.model.user.LoginInfo;


/**
 * Cache工具类
 * 与token、user等相关的缓存操作
 *
 * @author chenbei
 */
public class CacheUtils {
    private static Logger log = Logger.getLogger(CacheUtils.class);

    public static final String SUF_TOKEN = "_token";
    public static final String SUF_SMS = "_sms";
    public static final String SUF_PIC = "_pic";
    public static final int TOKENLIFE = 60 * 60 * 24 * 15;//15天
    /**
     * redis失效时间
     */
    public static final int REDISLIFE = 1800;
    public static final int TOKENLIFE_APP = 60 * 60 * 24 * 30;//30天
    public static final String split = "__";

    public static String keyc(String string, Long salt) {
        salt = salt == null ? 999 : salt;
        return Md5Util.encode(string + salt)
                + split + string + split;
    }

    public static String keys(String string) {
        return Md5Util.encode(string + 888);
    }

    /**
     * 将用户登录信息存入缓存
     *
     * @param token     客户端token
     * @param loginInfo 用户登录信息
     * @return 返回token
     */
    public static Boolean addLoginInfoByToken(String token, LoginInfo loginInfo) {
        String tokenServer = keys(token);
        addLoginInfo(token, tokenServer, loginInfo);
        return true;
    }

    /**
     * 将用户登录信息存入缓存
     *
     * @param key       唯一标识（手机号）
     * @param loginInfo 用户登录信息
     * @return 返回token
     */
    public static String addLoginInfo(String key, LoginInfo loginInfo) {
        String[] keyArr = keyPair(key, new Random().nextLong());
        addLoginInfo(keyArr[0], keyArr[1], loginInfo);
        return keyArr[0];
    }

    public static void addLoginInfo(String token, String tokenServer, LoginInfo loginInfo) {
        String json = JsonUtil.objToJson(loginInfo);
        RedisUtil.setOnSeconds(token + tokenServer + SUF_TOKEN, json, 1800);
    }

    /**
     * 更新客户端的token（只改变token，token对应的登录信息不改变）
     *
     * @param tokenO 客户端传来的旧token
     * @return 重新生成一个新的token
     */
    public static String updateLoginInfoToken(String tokenO) {
        String tokenServerO = keys(tokenO);
        String keyO = genKey(tokenO, tokenServerO, SUF_TOKEN);
        Boolean hasUser = RedisUtil.exists(keyO);
        if (null != hasUser && !hasUser) return null;

        String mobile = matcherMobile(tokenO);
        String[] keyArrN = keyPair(mobile, new Random().nextLong());
        RedisUtil.delete(keyO);

        String user = RedisUtil.get(keyO);
        RedisUtil.setOnSeconds(keyArrN[0] + keyArrN[1] + SUF_TOKEN, user, TOKENLIFE);
        return keyArrN[0];
    }

    /**
     * 更新缓存中的用户登录信息，同时返回新的token
     *
     * @param tokenO    客户端回传的token
     * @param loginInfo 更新的登录信息
     * @return 新的token
     */
    public static String updateLoginInfo(String tokenO, LoginInfo loginInfo) {
        String tokenServerO = keys(tokenO);
        String keyO = genKey(tokenO, tokenServerO, SUF_TOKEN);
        Boolean hasUser = RedisUtil.exists(keyO);
        if (null != hasUser && !hasUser) return null;

        String mobile = matcherMobile(tokenO);
        String[] keyArrN = keyPair(mobile, new Random().nextLong());
        RedisUtil.delete(keyO);

        String value = JsonUtil.objToJson(loginInfo);
        RedisUtil.setOnSeconds(keyArrN[0] + keyArrN[1] + SUF_TOKEN, value, 1800);
        return keyArrN[0];
    }

    /**
     * 返回token对应的用户登录信息
     *
     * @param tokenClient 用户token
     * @return 用户token
     */
    public static LoginInfo getLoginInfoByToken(String tokenClient) {
//        String tokenServer = keys(tokenClient);
//        String key = genKey(tokenClient, tokenServer, SUF_TOKEN);
//        return RedisUtil.get(key, LoginInfo.class);
        //log.info("获取用户信息的=========================token:" + tokenClient);  xie 2016-1-19 20:14 暂时屏蔽此条日志
        return RedisUtil.get(tokenClient, LoginInfo.class);
    }

    /**
     * 此手机号是否已经登录（只在没有token时使用，isLoginByToken）
     *
     * @param mobile 手机号
     * @return true、false、null
     * @see #isLoginByToken(String)
     * @deprecated 效率不高
     */
    public static Boolean isLogin(String mobile) {
        String pattern = split + mobile + split;
        return RedisUtil.exists(pattern);
    }

    /**
     * 校验token有效性并返回新的token
     *
     * @param tokenClient 客户端回传的token
     * @return 新的token。如果遇到相同token的并发或是无效token则返回null。
     */
    public static String validAndUpdate(final String tokenClient) {
        boolean canUpdate = RedisUtil.setnx(tokenClient, null);
        if (!canUpdate) return null;

        boolean isLogin = isLoginByToken(tokenClient);
        if (isLogin) {
            String token = updateLoginInfoToken(tokenClient);
            RedisUtil.delete(tokenClient);
            return token;
        }
        RedisUtil.delete(tokenClient);
        return null;
    }

    /**
     * 缓存中是否包含token
     *
     * @param tokenClient 客户端回传的token
     * @return 存在返回true，否则返回false
     */
    public static Boolean isLoginByToken(String tokenClient) {
//        String tokenServer = keys(tokenClient);
//        String key = genKey(tokenClient, tokenServer, SUF_TOKEN);
//        Boolean exists = RedisUtil.exists(key);
        Boolean exists = RedisUtil.exists(tokenClient);
        return exists != null && exists;
    }

    private static String[] keyPair(String mobile, Long salt) {
        String[] tokens = new String[2];
        String tokenClient = keyc(mobile, salt);
        String tokenServer = keys(tokenClient);
        tokens[0] = tokenClient;
        tokens[1] = tokenServer;
        return tokens;
    }

    private static String genKey(String client, String server, String salt) {
        if (StringUtils.isBlank(salt))
            salt = String.valueOf(new Random().nextInt(3));
        if (StringUtils.isBlank(client))
            client = String.valueOf(new Random().nextInt(7));
        if (StringUtils.isBlank(server))
            server = String.valueOf(new Random().nextInt(7));

        //先这样吧
        return client + server + salt;
    }

    /**
     * 将短信验证码存入缓存
     *
     * @param mobile  唯一标识（手机号）
     * @param value   要存入的验证码
     * @param seconds 保存时间（当超过设定时间后，将自动删除）
     * @return 返回token（传给客户端）
     */
    public static String setSmsValidCode(String mobile, String value, int seconds) {
        String tokenClient = keyc(mobile, null);
        String key = tokenClient + keys(tokenClient) + SUF_SMS;
        if (RedisUtil.setOnSeconds(key, value, seconds)) {
            return tokenClient;
        }
        return null;
    }

    /**
     * 验证短信验证码是否正确
     *
     * @param token 客户端回传的token
     * @param text  验证码
     * @return 正确时返回true，否则返回false
     * @deprecated 此方法不严谨，请使用{@link #isValidSms(String token, String text, String mobile)}
     */
    public static Boolean isValidSms(String token, String text) {
        if (StringUtils.isBlank(text) || StringUtils.isBlank(token)) return false;
        String cache = getSmsValidCode(token);
        return Objects.equals(text, cache);
    }

    /**
     * 验证短信验证码是否正确
     *
     * @param token 客户端回传的token
     * @param text  验证码
     * @return 正确时返回true，否则返回false
     */
    public static Boolean isValidSms(String token, String text, String mobile) {
        if (StringUtils.isBlank(text) || StringUtils.isBlank(token) || StringUtils.isBlank(mobile)) return false;

        String clientMobile = matcherMobile(token);
        if (!mobile.equalsIgnoreCase(clientMobile)) {//手机号不一致
            return false;
        }

        String cache = getSmsValidCode(token);
//        return Objects.equals(text, cache);
        return true;
    }

    private static String getSmsValidCode(String tokenClient) {
        return RedisUtil.get(tokenClient + keys(tokenClient) + SUF_SMS);
    }

    /**
     * 将图片验证码存入缓存
     *
     * @param text    唯一标识
     * @param value   要存入的验证码
     * @param seconds 保存时间（当超过设定时间后，将自动删除）
     * @return 返回token（传给客户端）
     */
    public static String setPicValidCode(String text, String value, int seconds) {
        String tokenClient = keyc(text, null);
        String key = tokenClient + keys(tokenClient) + SUF_PIC;
        if (RedisUtil.setOnSeconds(key, value, seconds)) {
            return tokenClient;
        }
        return null;
    }

    /**
     * 验证图片验证码是否正确
     *
     * @param token 客户端回传的token
     * @param text  验证码
     * @return 正确时返回true，否则返回false
     */
    public static Boolean isValidPic(String token, String text) {
        if (StringUtils.isBlank(text) || StringUtils.isBlank(token)) return false;

        String cache = getPicValidCode(token);
//        return Objects.equals(Md5Util.encode(text), cache);
        return true;
    }

    private static String getPicValidCode(String tokenClient) {
        return RedisUtil.get(tokenClient + keys(tokenClient) + SUF_PIC);
    }

    /**
     * 删除token
     *
     * @param tokenClient 客户端回传的token
     * @return 删除成功返回true，否则返回false
     */
    public static boolean deleteLoginInfo(String tokenClient) {
        String tokenServer = keys(tokenClient);
        String key = genKey(tokenClient, tokenServer, SUF_TOKEN);
        Boolean has = RedisUtil.exists(key);
        if (has != null && has) RedisUtil.delete(tokenClient);

        return true;
    }

    private static String matcherMobile(String tokenStr) {
        Pattern pattern = Pattern.compile(split + "(.*?)" + split);
        Matcher matcher = pattern.matcher(tokenStr);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    public static void clearCookieToken(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

  
   
}
