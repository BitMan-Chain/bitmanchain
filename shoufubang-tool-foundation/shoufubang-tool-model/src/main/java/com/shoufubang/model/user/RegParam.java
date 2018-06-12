package com.shoufubang.model.user;

import org.omg.PortableInterceptor.INACTIVE;

/**
 * Created by
 */
public class RegParam {

   private Integer id;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * verificationCode
     */
    private String verificationCode;
    /**
     * token
     */
    private String token;
    /**
     * smsToken
     */
    private String smsToken;
   
    /**
     * 推荐人
     */
    private String inviteFrom;
  
   
  
    /**
     * 姓名
     */
    private String userName;
    /**
     * 身份证号
     */
    private String idcard;
   
    /**
     * 用户ID
     */
    private String userId;

    
   

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSmsToken() {
        return smsToken;
    }

    public void setSmsToken(String smsToken) {
        this.smsToken = smsToken;
    }

   

    public String getInviteFrom() {
        return inviteFrom;
    }

    public void setInviteFrom(String inviteFrom) {
        this.inviteFrom = inviteFrom;
    }

  

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
}
