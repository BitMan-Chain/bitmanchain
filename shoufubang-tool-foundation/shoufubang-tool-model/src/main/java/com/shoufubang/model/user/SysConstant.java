package com.shoufubang.model.user;

/**
 * Created by 陈蓓 on 2015/11/9.
 */
public class SysConstant
{
    /**
     * 系统版本号
     */
    public static final String VERSION_SYS = "1.0";

    /**
     * 控制器requestMapping路径
     */
    public static final String PATH_REQUEST = "/"+VERSION_SYS;

    /**
     * session中的当前登录用户key（UserLogin对象全部属性）
     */
    public static final String CURRENTLOGINUSER = "CURRENTLOGINUSER";
    /**
     * session中的当前登录用户key（User对象部分属性）
     */
    public static final String CURRENTUSER = "CURRENTUSER";
    public static final String COOKIETOKEN = "token";
    public static final String COOKIEOPENID = "openid";


    //

}
