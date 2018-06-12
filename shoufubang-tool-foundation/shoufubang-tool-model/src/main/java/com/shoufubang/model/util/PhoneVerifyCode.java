package com.shoufubang.model.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.shoufubang.model.util.BrhMsg;
import com.shoufubang.model.util.BrhResult;
import com.shoufubang.model.util.Constant;
import com.shoufubang.model.util.GetUtils;
import com.shoufubang.model.util.TimeUtil1;
import com.shoufubang.model.util.sms.WeiWangSms;



	/**
	 * 
	 * @author shuaili
     * @date  2016年6月6日 上午10:10:08 
	 * @Title  
	 * @Description 
	 * @param  params 
	 * @return 
	 * @throws
	 * @version V1.0
	 */
@Controller
public class PhoneVerifyCode implements Serializable {
	private static final long serialVersionUID = -3964504952071535016L;

    
	private String phone;
	
	private String verifyCode;
	
	private String imgVerifyCode;
	
	private String remoteIp;
	
	private long time;
	
	private int verifyTimes;
	
	private String errorMsg;
	
	private List<String> phoneList;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getImgVerifyCode() {
		return imgVerifyCode;
	}

	public void setImgVerifyCode(String imgVerifyCode) {
		this.imgVerifyCode = imgVerifyCode;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getVerifyTimes() {
		return verifyTimes;
	}

	public void setVerifyTimes(int verifyTimes) {
		this.verifyTimes = verifyTimes;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public List<String> getPhoneList() {
		return phoneList == null ? new ArrayList<String>() : phoneList;
	}
	
	public void setPhoneList(List<String> phoneList) {
		this.phoneList = phoneList;
	}

	private boolean isPhoneExist(String phone) {
		return getPhoneList().contains(phone);
	}

	/**
	 * 获取短信验证码
	 * @param phone 手机号码
	 * @param verifyCode 验证码
	 * @param timeBetween 时间间隔(秒)
	 * @return
	 */
	public static PhoneVerifyCode getCode(String phone, String verifyCode, int timeBetween) {
		PhoneVerifyCode phoneVerifyCode = null;
		List<String> phoneList = new ArrayList<String>();
		
		Object obj = getRequest().getSession().getAttribute(Constant.SESSION_PHONE_CODE);
		if(obj != null) {
			phoneVerifyCode = (PhoneVerifyCode)obj;
			if(phoneVerifyCode.isPhoneExist(phone)
					&& TimeUtil1.getCurrentTime() - phoneVerifyCode.getTime() < timeBetween) {
				phoneVerifyCode.setErrorMsg("操作太频繁，两次发送时间间隔不能小于" + timeBetween + "秒");
				return phoneVerifyCode;
			}
			
			phoneList = phoneVerifyCode.getPhoneList();
		}
		phoneList.add(phone);
		
		phoneVerifyCode = new PhoneVerifyCode();
		phoneVerifyCode.setPhone(phone);
		phoneVerifyCode.setTime(TimeUtil1.getCurrentTime());
		phoneVerifyCode.setVerifyCode(verifyCode);
		phoneVerifyCode.setVerifyTimes(0);
		phoneVerifyCode.setRemoteIp(getRequestIp());
		phoneVerifyCode.setPhoneList(phoneList);
		getRequest().getSession().setAttribute(Constant.SESSION_PHONE_CODE, phoneVerifyCode);
		return phoneVerifyCode;
	}
	
	/**
	 * 获取短信验证码
	 * @param phone 手机号码
	 * @param verifyCode 验证码
	 * @param imgVerifyCode 图片验证码
	 * @param timeBetween 时间间隔(秒)
	 * @return
	 */
	public static PhoneVerifyCode getCode(String phone, String verifyCode, String imgVerifyCode, int timeBetween) {
		PhoneVerifyCode phoneVerifyCode = null;
		phoneVerifyCode = new PhoneVerifyCode();
		String sessionImageVerifyCode = (String)getRequest().getSession().getAttribute(Constant.SESSION_VERIFY_CODE);
		if(sessionImageVerifyCode==null){
			phoneVerifyCode.setErrorMsg("图片验证码超时...");
			return phoneVerifyCode;
			
		}
		if(!sessionImageVerifyCode.toLowerCase().equals(imgVerifyCode.toLowerCase())){
			phoneVerifyCode.setErrorMsg("图片验证码错误");
			return phoneVerifyCode;
		}
		
		String phoneImgVerifyCode = phone + imgVerifyCode; 
		List<String> phoneList = new ArrayList<String>();
		Object obj = getRequest().getSession().getAttribute(Constant.SESSION_PHONE_CODE);
		if(obj != null) {
			phoneVerifyCode = (PhoneVerifyCode)obj;
			if(!phoneImgVerifyCode.equalsIgnoreCase(phoneVerifyCode.getPhone() + phoneVerifyCode.getImgVerifyCode())) {
				phoneVerifyCode.setErrorMsg("图片验证码错误");
				return phoneVerifyCode;
			}
			
			if(phoneVerifyCode.isPhoneExist(phoneImgVerifyCode)
					&& TimeUtil1.getCurrentTime() - phoneVerifyCode.getTime() < timeBetween) {
				phoneVerifyCode.setErrorMsg("操作太频繁，两次发送时间间隔不能小于" + timeBetween + "秒");
				return phoneVerifyCode;
			}
			
			phoneList = phoneVerifyCode.getPhoneList();
		}
		phoneList.add(phoneImgVerifyCode);
		
		phoneVerifyCode = new PhoneVerifyCode();
		phoneVerifyCode.setPhone(phone);
		phoneVerifyCode.setTime(TimeUtil1.getCurrentTime());
		phoneVerifyCode.setVerifyCode(verifyCode);
		phoneVerifyCode.setImgVerifyCode(imgVerifyCode);
		phoneVerifyCode.setVerifyTimes(0);
		phoneVerifyCode.setRemoteIp(getRequestIp());
		phoneVerifyCode.setPhoneList(phoneList);
		getRequest().getSession().setAttribute(Constant.SESSION_PHONE_CODE, phoneVerifyCode);
		return phoneVerifyCode;
	}
	
	/**
	 * 短信验证码校验
	 * @param phone 手机号码
	 * @param verifyCode 验证码
	 * @param maxErrorTimes 最低错误次数
	 * @return
	 */
	public static String validate(String phone, String verifyCode, int maxErrorTimes) {
		if(StringUtils.isBlank(verifyCode)) return "验证码不能为空";
		
		Object obj = getRequest().getSession().getAttribute(Constant.SESSION_PHONE_CODE);
		if(obj == null || !(obj instanceof PhoneVerifyCode)) return "验证码过期或无效，请重新获取";
		
		PhoneVerifyCode sessionVerifyCode = (PhoneVerifyCode)obj;
		if((phone != null && !phone.equals(sessionVerifyCode.getPhone()))
				|| !verifyCode.equals(sessionVerifyCode.getVerifyCode())
				|| !getRequestIp().equals(sessionVerifyCode.getRemoteIp())) {
			if(sessionVerifyCode.getVerifyTimes() >= maxErrorTimes) {
				getRequest().getSession().removeAttribute(Constant.SESSION_PHONE_CODE);
				return "验证码过期或无效，请重新获取";
			}
			
			sessionVerifyCode.setVerifyTimes(sessionVerifyCode.getVerifyTimes() + 1);
			getRequest().getSession().setAttribute(Constant.SESSION_PHONE_CODE, sessionVerifyCode);
			
			return "验证码错误";
		}
		
		sessionVerifyCode.setVerifyTimes(0);
		getRequest().getSession().setAttribute(Constant.SESSION_PHONE_CODE, sessionVerifyCode);
		return null;
	}
	
	/**
	 * 短信验证码校验
	 * @param phone 手机号码
	 * @param verifyCode 验证码
	 * @param imgVerifyCode 图片验证码
	 * @param maxErrorTimes 最低错误次数
	 * @return
	 */
	public static String validate(String phone, String verifyCode, String imgVerifyCode, int maxErrorTimes) {
		if(StringUtils.isBlank(verifyCode)) return "短信验证码不能为空";
		if(StringUtils.isBlank(imgVerifyCode)) return "图片验证码不能为空";
		
		Object obj = getRequest().getSession().getAttribute(Constant.SESSION_PHONE_CODE);
		if(obj == null || !(obj instanceof PhoneVerifyCode)) return "验证码过期或无效，请重新获取";
		
		PhoneVerifyCode sessionVerifyCode = (PhoneVerifyCode)obj;
		if((phone != null && !phone.equals(sessionVerifyCode.getPhone()))
				|| !getRequestIp().equals(sessionVerifyCode.getRemoteIp())
				|| !verifyCode.equalsIgnoreCase(sessionVerifyCode.getVerifyCode())
				|| !imgVerifyCode.equalsIgnoreCase(sessionVerifyCode.getImgVerifyCode())) {
			if(sessionVerifyCode.getVerifyTimes() >= maxErrorTimes) {
				getRequest().getSession().removeAttribute(Constant.SESSION_PHONE_CODE);
				return "验证码过期或无效，请重新获取";
			}
			
			sessionVerifyCode.setVerifyTimes(sessionVerifyCode.getVerifyTimes() + 1);
			getRequest().getSession().setAttribute(Constant.SESSION_PHONE_CODE, sessionVerifyCode);
			
			return "验证码错误";
		}
		
		sessionVerifyCode.setVerifyTimes(0);
		getRequest().getSession().setAttribute(Constant.SESSION_PHONE_CODE, sessionVerifyCode);
		return null;
	}
	
	public static String getSessionPhone() {
		Object obj = getRequest().getSession().getAttribute(Constant.SESSION_PHONE_CODE);
		if(obj == null || !(obj instanceof PhoneVerifyCode)) return null;
		
		PhoneVerifyCode sessionVerifyCode = (PhoneVerifyCode)obj;
		return sessionVerifyCode.getPhone();
	}
	
	private static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	private static String getRequestIp() {
		HttpServletRequest request = getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null) ip = request.getRemoteAddr();
		if("0:0:0:0:0:0:0:1".equals(ip)) ip = "127.0.0.1";
		return ip;
	}
	
}