package com.shoufubang.model.util;

import com.google.zxing.*;
import com.google.zxing.qrcode.Encode;

import htz.util.lang.Predef;
import htz.util.lang.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AccessTokenUtil {
	public static final String ACCESSTOKEN_KEY_LENGTH = "access_token_length" ;
	private static String defaultsp1 = "@#$*" ;
	private static String defaultsp2 = "$%^&" ;
	protected static Logger logger = LoggerFactory.getLogger(AccessTokenUtil.class);
	 
	
//	public static boolean isAccess(HttpServletRequest request ){
//		AccessTokenParam pp = new AccessTokenParam();
//		Object l = HttpRequestUtil.getKeyFromSession(request, ACCESSTOKEN_KEY_LENGTH);
//		String accesstokenstr = HttpRequestUtil.getKeyFromCookies(request, Constant.SESSION_ACCESSTOKEN_KEY);
//		if (StringUtils.isBlank(accesstokenstr)) {
//			return false;
//		}
//		if(l!=null){
//			if(accesstokenstr.length()!= Predef.toInt(l,0)){
//				return false;
//			}
//		}
//		pp = AccessTokenUtil.parseAccessToken(accesstokenstr);
//		String mac = "accesstokenmac";
//		if(StringUtils.isEmpty(mac)){
//			mac = SystemInfo.getInstance().getOs_mac();
//		}
//		if (!pp.getMac().equals(mac)) {
//			return false;
//		}
//		if (TimeUtil.currentTsToSecond() - pp.getDate() > Constant.SESSION_ACCESSTOKEN_KEY_TIME) {
//			return false;
//		}
//		String uid = Predef.toStr(WebUtils.getSessionAttribute(request, Constant.SESSION_FRONT_KEY));
//		if (!pp.getKey().equals(uid)) {
//			return false;
//		}
////		logger.info(" htz access_token is true ");
//		return true;
//		
//	}
	
	/**
	 * 生成accesstoken
	 * @param uid
	 * @return
	 */
	public static String createAccessToken(int uid){
		AccessTokenParam param = new AccessTokenParam();
    	String sp = "accesstokensp";
		long deskey = Long.parseLong("accesstokendeskey");
		String mac = "accesstokenmac";
    	param.setDate(TimeUtil.currentTsToSecond());
    	param.setDeskey(deskey);
    	param.setKey(uid+"");
    	param.setSp(sp.split(";"));
    	param.setMac(mac);
		return createAccessToken(param);
		
	}
	private static String createAccessToken(AccessTokenParam params){
		String [] sp = params.getSp();
		String mac = params.getMac();
		if(StringUtils.isEmpty(mac)){
			mac = SystemInfo.getInstance().getOs_mac();
		}
		long date = params.getDate();
		if(date==0){
			date = TimeUtil.currentTsToSecond();
		}
		if(sp==null){
			sp = new String[]{defaultsp1,defaultsp2};
		}
		String accesstoken = create(params.getKey(),mac,date,sp[0],sp[1]);
		Encode encode = new Encode();
		return encode.encode(accesstoken, params.getDeskey());
	}
	 
	private static AccessTokenParam parseAccessToken(String accesstokenstr,String[] sp, long deckey){
		AccessTokenParam pp = new AccessTokenParam();
		pp.setDeskey(deckey);
		pp.setSp(sp);
		if(StringUtils.isEmpty(accesstokenstr)){
			throw new RuntimeException("accesstoken is empty");
		}
		if(sp==null){
			throw new RuntimeException("sp is null ");
		}
		try{
			Encode encode = new Encode();
			String encodeaccesstoken = encode.decode(accesstokenstr, deckey);
			if(StringUtils.isEmpty(sp[0])){
				throw new RuntimeException("sp is null ");
			}
			if(StringUtils.isEmpty(sp[1])){
				throw new RuntimeException("sp is null ");
			}
			int index = encodeaccesstoken.indexOf(sp[0]);
			int index2 = encodeaccesstoken.indexOf(sp[1]);
			if(index==-1){
				return pp;
			}
			if(index2==-1){
				return pp;
			}
		 
			String key = encodeaccesstoken.substring(0,index);
			String mac = encodeaccesstoken.substring(index+sp[0].length(),index2);
			String date = encodeaccesstoken.substring(index2+sp[0].length());
			pp.setAccesstoken(accesstokenstr);
			pp.setDate(Long.valueOf(date));
			pp.setMac(mac);
			pp.setKey(key);
		}catch(Exception e){
			
		}
		return pp;
	}
	/**
	 * 解析accesstoken
	 * @param accesstokenstr
	 * @return
	 */
//	public static AccessTokenParam parseAccessToken(String accesstokenstr){
//	//	String sp = "accesstokensp",defaultsp1+";"+defaultsp2;
//		long deskey = Global.config.getLong("accesstokendeskey");
//		return parseAccessToken(accesstokenstr, sp.split(";"), deskey);
//	}
	private static String create(String uid, String mac, long date,String sp1,String sp2) {
		StringBuffer sb = new StringBuffer();
		sb.append(uid).append(sp1);
		sb.append(mac).append(sp2);
		sb.append(date);
		return sb.toString();
	}

	/**
	 * 获取当前有效的一个token值
	 * @param openid
	 * @return
	 */
//	public static Map<String,Object> getBaiHeToken(String openid){
//		if(Predef.isEmpty(openid)) return null;
//		Map<String,Object> token = new HashMap<>();
//		try{
//			String now = TimeUtil1.getCurrentTimeStr();
//			Map<String,Object> tokenInfo = Env.db().queryMap("select *  from htz_baihe_token where createtime<=? and endtime>=? and baiheid=? and status =1 ",now,now,openid);
//			if(Predef.isEmpty(tokenInfo)){
//				token = createBaiHeToken(openid);
//			}else{
//				token = Predef.asMap("token", Predef.toStr(tokenInfo.get("token")), "startTime", tokenInfo.get("createtime"), "endTime", tokenInfo.get("endtime"));
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return token;
//	}

	/**
	 * 校验百合的token是否有效
	 * @param openid
	 * @param token
	 * @return
	 */
//	public static boolean isRightBaiHeToken(String openid ,String token){
//		try{
//			String now = TimeUtil1.getCurrentTimeStr());
//			Map<String,Object> tokenInfo = Env.db().queryMap("select * from htz_baihe_token where createtime<=? and endtime>=? and token=? and baiheid=? ",now,now,token,openid);
//			if(Predef.isNotEmpty(tokenInfo)) return true;
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
//		return false;
//	}

	/**
	 * 生成百合token
	 * @param openid
	 * @return
	 * @throws Exception
	 */
//	private static Map<String,Object> createBaiHeToken(String openid)throws Exception{
//		String token = Md5Util.encode(openid + Common.getRandStr(8,false));
//		Date now = new Date();
//		String startTime = DateTimeUtil.getShortDateTimeString(now);
//		String endTime = TimeUtil.getNextMinute(startTime,120);
//		Env.db().insertReturnInt("insert into htz_baihe_token(`baiheid`,`token`,`createtime`,`endtime`) values (?,?,?,?)", openid, token, startTime, endTime);
//		return Predef.asMap("token",token,"startTime",startTime,"endTime",endTime);
//	}
	
	public static void main(String[] args) {
		AccessTokenParam params = new AccessTokenParam();
		params.setKey("12345");
		params.setDeskey(1586723456);
		String ss = AccessTokenUtil.createAccessToken(params);
		System.out.println(ss);
		AccessTokenParam sss = AccessTokenUtil.parseAccessToken(ss, new String[]{"@#$*","$%^&"}, 1586723456);
		System.out.println();
	}
}