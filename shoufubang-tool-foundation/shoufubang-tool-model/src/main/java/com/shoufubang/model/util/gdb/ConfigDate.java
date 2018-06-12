package com.shoufubang.model.util.gdb;

import java.util.ResourceBundle;

public final class ConfigDate {

	public static String CFCA_KT_UTL;
	
	// masterKey
	public static String MASTERKEY = "23fee1268c2a0a8923cccadfb6a08b6f";

	// 实名appkey
	public static String APPKEY_REALNAME = "n9xgkloibunisc55";
	// 三要素认证appkey
	public static String APPKEY_AUTH =  "dnxuklf2m25g23fz";
	// 车辆续保appkey
	public static String APPKEY_RENEWAL = "fcjw2ys59u1ca0wi";
	// 绑定银行卡
	public static String APPKEY_BANKCARD = "5rraquyum7h3us7b";
	// 航旅消费能力评估
	public static String APPKEY_AIRTRAVEL = "n17ssbpvk9dvcjns";
	// 学历认证
	public static String APPKEY_EDUCATION = "0zkn09pmjt4leria";
	
	//创世人数
	public static String BEGIN_USER;
	
	//实名数据接口
	public static String GWDATABANKURL;
	
	//三要素认证
	public static String GWDATAMOBILEURL;
	
	//续保接口
	public static String GWDATARENEWALURL;
	
	//绑卡接口
	public static String GWDATABANKCARDURL;
	
	//航旅消费能力评估
	public static String GWDATAAIRTRAVELURL;
	
	//学历认证接口
	public static String GWDATAEDUCATIONLURL;
	
	//邀请增加算力
	public static String INVHASHRATE;
	//邀请增加币数
	public static String  INVCURRENCY;
		
	// 实名送币数
	public static String REALNAMECURRENCY;
	// 实名送算力数
	public static String REALNAMEHASHRATE;
	
	// 三要素实名送币数
	public static String MOBLIEAUTHHASHRATE;
	// 三要素送算力数
	public static String MOBLIEAUTHCURRENCY;
	
	//车辆认证送币
	public static String CARAUTHHASHRATE;
	//车辆认证送算力
	public static String CARAUTHCURRENCY;
	
	//绑卡认证送币
	public static String BANKCARDHASHRATE;
	//绑卡认证送算力
	public static String BANKCARDCURRENCY;	
	
	//航旅消费能力加算力
	public static String AIRTRAVELHASHRATE;
	//航旅消费能力加币
	public static String AIRTRAVELCURRENCY;	
	
	
	//学历认证送算力
	public static String EDUCATIONHASHRATE;
	//学历认证加币
	public static String EDUCATIONCURRENCY;	
	
	// 登陆送算力+2
	public static String LOGINHASHRATE;
	
	// 挖矿开始时间
	public static String BEGINMINE;
	
	//挖矿每日产值
	public static String EVERYDAYMINE;
	
	//最多邀请人
	public static String INVMAXCOUNT;
	//初始算力
	public static String STARTSTRESS;
	
	//上传增加币
	public static String UPDATECURRENCY;
	//每日最大上传数
	public static String UPDATEMAXCOUNT;
	
	//创世分发糖果数
	public static String TOTALGENESISCANDY;

	
	static {
		ResourceBundle resource = ResourceBundle.getBundle("config");
		BEGIN_USER =resource.getString("begin_user");
		
		GWDATABANKURL = resource.getString("dwDatabankUrl");
		GWDATAMOBILEURL= resource.getString("dwDataMobileUrl");
		GWDATABANKCARDURL =  resource.getString("dwBankCardUrl");
		GWDATARENEWALURL = resource.getString("dwDataRenewalUrl");
		GWDATAAIRTRAVELURL =  resource.getString("dwDataAirTravelUrl");
		GWDATAEDUCATIONLURL = resource.getString("dwDataEducationUrl");
		
		
		REALNAMEHASHRATE=  resource.getString("realname_hashrate");
		REALNAMECURRENCY =	resource.getString("realname_currency");
		
		MOBLIEAUTHHASHRATE=  resource.getString("mobileauth_hashrate");
		MOBLIEAUTHCURRENCY = resource.getString("mobileauth_currency");
		
		CARAUTHHASHRATE=  resource.getString("carauth_hashrate");
		CARAUTHCURRENCY = resource.getString("carauth_currency");

		BANKCARDHASHRATE=  resource.getString("bankcard_hashrate");
		BANKCARDCURRENCY = resource.getString("bankcard_currency");
		
		AIRTRAVELHASHRATE = resource.getString("airtravel_hashrate");
		AIRTRAVELCURRENCY = resource.getString("airtravel_currency");
		
		EDUCATIONHASHRATE = resource.getString("education_hashrate");
		EDUCATIONCURRENCY = resource.getString("education_currency");
		
		
		BEGINMINE = resource.getString("begin_mine");
		EVERYDAYMINE =resource.getString("everyday_mine");
		INVMAXCOUNT =resource.getString("inv_maxcount");
		INVHASHRATE =resource.getString("inv_hashrate");
		INVCURRENCY =resource.getString("inv_currency");
		TOTALGENESISCANDY =resource.getString("total_genesis_candy");
		
		STARTSTRESS=resource.getString("start_stress");
		LOGINHASHRATE = resource.getString("login_hashrate");
		
		UPDATECURRENCY = resource.getString("update_currency");
		UPDATEMAXCOUNT = resource.getString("update_maxcount");
		
	}
}
