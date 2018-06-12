package com.shoufubang.model.user;

/**
 * 短信类型
 * @author vincent
 *
 */
public final class SMSType {
	
	public static final String SEND_CODE = "sendCode"; //发送验证码
	
	public static final Byte VERIFICATION_TYPE_SMS = 1;
	public static final Byte VERIFICATION_TYPE_PIC = 2;
	public static final Long SMS_OUTTIME = 300000L;//短信验证码有效期，5分钟
	public static final Long SMS_RESET = 115000L;//短信验证码再次获取间隔时间，2分钟
	public static final Long PIC_OUTTIME = 300000L;//图片验证码有效期，5分钟
	public static final Integer SMS_REDIS_OUTTIME = 300;//短信验证码Redis中有效期，5分钟
	public static final Integer PIC_REDIS_OUTTIME = 300;//图片验证码Redis中有效期，5分钟
	public static final Integer IP_REDIS_NUMBER = 10;//同一IP客户端每天最多获取验证码数量
	public static final Integer SUCCESS = 1;//成功提示
	public static final Integer FAILED = 0;//失败提示
	
	//验证码用途：1:注册、2:找回密码、3:充值、4:提现、5:投资、6:绑卡7: 身份认证
	public static final Byte SEND_CODE_USE_REGISTER = 1; //注册
	public static final Byte SEND_CODE_USE_FIND_PASSWORD = 2; //找回密码
	public static final Byte SEND_CODE_USE_RECHARGE = 3; //充值
	public static final Byte SEND_CODE_USE_CASH = 4; //提现
	public static final Byte SEND_CODE_USE_INVEST = 5; //投资
	public static final Byte SEND_CODE_USE_BIND_CARD = 6; //绑卡
	public static final Byte SEND_CODE_USE_REAL_NAME = 7; //实名认证
	
}
