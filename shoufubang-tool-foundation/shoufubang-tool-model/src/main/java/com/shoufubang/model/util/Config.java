package com.shoufubang.model.util;

import java.util.ResourceBundle;

public final class Config {
	public static final String PROJECT_TYPE="6, 7, 8, 9"; //(6/7/8/9) 月/季/双季/年悦盈
	
	public static String BANK_IMG_URL;//银行卡图片路径
	
	public static String HANDLINGCOSTRATE3;
	public static String HANDLINGCOSTSWITCH;
	public static String HANDLINGCOSTRATE5;
	public static String HANDLINGCOST;
	public static String MINCASHLIMIT;
	
	public static String BINDCARD_SYN_URL ;//同步请求绑卡URL地址
	public static String BINDCARD_ASYN_URL ;//异步请求绑卡URL地址
	
	public static String BINDCARD_AGREEMENT_SYN_URL ;//同步请求绑卡补签快捷协议URL地址
	public static String BINDCARD_AGREEMENT_ASYN_URL ;//异步请求绑卡补签快捷协议URL地址
	
	public static String CHARGE_SYN_URL;//同步请求充值URL地址
	public static String CHARGE_ASYN_URL;//异步请求充值URL地址
	
	public static String CASH_SYN_URL;//同步请求提现URL地址
	public static String CASH_ASYN_URL;//异步请求提现URL地址

	public static String INVEST_RET_URL;   //投资同步回调url
	public static String INVEST_NOTIFY_URL; //投资异步回调url
	
	public static String DEMAND_INVEST_NOTIFY_URL; // 活期投资异步回调
	
	public static String DEMAND_INVEST_RET_URL_WAP; // 活期投资wap跳转地址
	
	public static String REGISTER_SERVICEPDF_URL; //服务协议pdf路径
	public static String REGISTER_DEPUTEPDF_URL;//委托代收pdf路径
	
	public static String BASE_ASYNC_URL; //异步请求的base_async_url
	
	public static String CONTRACT_PDF_URL;//合同路径
	
	public static String SFB_LEAST_MONEY; //mb标起投金额
	public static String SB_LEAST_MONEY; //散标起投金额
	
	public static String PC_SYN_URL; //充值，绑卡，提现，投资等PC端回调地址为个人中心
	public static String PC_SYNRECHAGE_URL; //充值前端回调
	public static String PC_SYNCASH_URL;  //提现回调
	public static String PC_SYNBINDCARD_URL;  //绑卡回调
	public static String WAP_SYN_URL; //充值，绑卡，提现，投资等WAP端回调地址为个人中心
	public static String APP_SYN_URL; //充值，绑卡，提现，投资等APP端同步回调地址
	
	public static String OPERATE_BORROWER_URL;	// 还款转帐异步url
	public static String OPERATE_BORROWER_BACK_URL;	// 还款转帐同步url
	
	public static String OPENQUICK_ASYN_URL; // 开通快捷支付异步回调
	
	public static String SUCCESS_MSG; // 请求成功的返回信息
	public static String ERROR_MSG; // 请求失败的返回信息

    
	public static String IMG_URL; // 开通快捷支付异步回调
	/**
	 * 以下两项用于发送邮件
	 */
	public static String WEB_URL; //PC端服务器域名
	public static String REPAY_URL; //付本还息记录页地址
	
	/**
	 * CFCA 参数
	 */
	public static String CFCA_KT_UTL;
	public static String CFCA_RA_UTL;
	public static String YANZHENGT;
	public static String CFCA_SA_URL;
	public static String PFX_URL;
	static {
		ResourceBundle resource = ResourceBundle.getBundle("config");
		CONTRACT_PDF_URL = resource.getString("contract_pdf_url");
		BASE_ASYNC_URL = resource.getString("base_async_url");
	
		
		SFB_LEAST_MONEY = resource.getString("sfb_least_money");//sfb标起投金额
		SB_LEAST_MONEY = resource.getString("sb_least_money");//散标起投金额
		
		HANDLINGCOSTRATE3 = resource.getString("handlingCostRate3");//提现手续费配置 按千分之三算
		
		HANDLINGCOSTSWITCH = resource.getString("handlingCostSwitch");//用户提现手续费 开关：1--按扣2元算；2--按扣千分之三算；3--按扣千分之五算
		
		HANDLINGCOSTRATE5 = resource.getString("handlingCostRate5");//提现手续费配置 按千分之五算
		
		HANDLINGCOST = resource.getString("handlingCost");//用户提现手续费配置
		
		MINCASHLIMIT = resource.getString("minCashLimit");//用户提现手续费配置
		
		BINDCARD_SYN_URL = BASE_ASYNC_URL + resource.getString("bind_card_syn_url");//同步请求绑卡URL地址
		BINDCARD_ASYN_URL = BASE_ASYNC_URL + resource.getString("bind_card_asyn_url");//异步请求绑卡URL地址
		
		BINDCARD_AGREEMENT_SYN_URL = BASE_ASYNC_URL + resource.getString("bind_card_agreement_syn_url");//同步请求绑卡补签快捷协议URL地址
		BINDCARD_AGREEMENT_ASYN_URL = BASE_ASYNC_URL + resource.getString("bind_card_agreement_asyn_url");//异步请求绑卡补签快捷协议URL地址
		
		CHARGE_SYN_URL = BASE_ASYNC_URL + resource.getString("charge_syn_url");//同步请求充值URL地址
		CHARGE_ASYN_URL = BASE_ASYNC_URL + resource.getString("charge_asyn_url");//异步请求充值URL地址
		
		CASH_SYN_URL = BASE_ASYNC_URL + resource.getString("cash_syn_url");//同步请求提现URL地址
		CASH_ASYN_URL = BASE_ASYNC_URL + resource.getString("cash_asyn_url");//异步请求提现URL地址
		
		REGISTER_SERVICEPDF_URL = resource.getString("register_servicepdf_url");//服务协议pdf路径
		REGISTER_DEPUTEPDF_URL = resource.getString("register_deputepdf_url");//委托代收pdf路径
		
		INVEST_RET_URL = BASE_ASYNC_URL + resource.getString("invest_ret_url"); //投资同步回调
		INVEST_NOTIFY_URL = BASE_ASYNC_URL + resource.getString("invest_notify_url"); //投资异步回调
		
		DEMAND_INVEST_NOTIFY_URL = BASE_ASYNC_URL + resource.getString("demand_invest_notify_url"); // 活期投资异步回调
		DEMAND_INVEST_RET_URL_WAP = resource.getString("demand_invest_ret_url_wap");
		
		PC_SYNRECHAGE_URL = resource.getString("pc_synrechage_url"); //充值，绑卡，提现，投资等PC端回调地址为个人中心
		PC_SYNCASH_URL = resource.getString("pc_syncash_url");
		PC_SYNBINDCARD_URL =resource.getString("pc_synbindcard_url");
		PC_SYN_URL = resource.getString("pc_syn_url");
		OPENQUICK_ASYN_URL =resource.getString("openquick_asyn_url"); //开通快捷支付
		WAP_SYN_URL = resource.getString("wap_syn_url"); //充值，绑卡，提现，投资等WAP端回调地址为个人中心
		APP_SYN_URL = BASE_ASYNC_URL + resource.getString("app_syn_url"); //充值，绑卡，提现，投资等APP端同步回调地址
		
		WEB_URL = resource.getString("web_url"); //PC端服务器域名
		REPAY_URL = resource.getString("repay_url"); //付本还息记录页地址
		
		OPERATE_BORROWER_URL = BASE_ASYNC_URL + resource.getString("operate_borrower_url");	// 还款转帐异步url
		OPERATE_BORROWER_BACK_URL = BASE_ASYNC_URL + resource.getString("operate_borrower_back_url");	// 还款转帐同步url
	//	SUCCESS_MSG =  resource.getString("success_msg");// 请求成功的返回信息
	//	ERROR_MSG =  resource.getString("error_msg");// 请求失败的返回信息
		IMG_URL = BASE_ASYNC_URL + resource.getString("img_url");
		
		CFCA_KT_UTL = resource.getString("cfca_kt_url"); //CFCA 请求url
		CFCA_RA_UTL = resource.getString("cfca_ra_url"); //CFCA 请求url
		YANZHENGT   = resource.getString("yanzhengT");
		CFCA_SA_URL = resource.getString("cfca_sa_url");
		PFX_URL = resource.getString("pfx_url");
	}
}
