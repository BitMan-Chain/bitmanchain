package com.shoufubang.model.user;

import java.math.BigDecimal;

/**
 * 用户登录信息
 * 存储在缓存中
 * @author chenbei
 */
public class LoginInfo
{
    private String mobile;//手机号
    private Integer userId;//对应user表的Id
    private String name;
    private String cookieToken;
    
    public LoginInfo(){
    	
    }
    public LoginInfo(User user){
    	this.userId = user.getId();
    	this.mobile = user.getMobile();
        this.name = user.getName();
    }


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCookieToken() {
		return cookieToken;
	}
	public void setCookieToken(String cookieToken) {
		this.cookieToken = cookieToken;
	}

    
}
