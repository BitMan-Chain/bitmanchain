package com.shoufubang.model.util;

import java.util.HashMap;
import java.util.Map;


	/**
	 * 
	 * @author shuaili
     * @date  2016年6月3日 下午1:31:35 
	 * @Title  
	 * @Description 
	 * @param  params 
	 * @return 
	 * @throws
	 * @version V1.0
	 */
public class Constant {
	/**
	 *  记录登录错误次数
	 */
    public static final String SESSION_LOGIN_NUM = "loginerror";
	/**
	 * 加密的accesstoken
	 */
    public static final String SESSION_ACCESSTOKEN_KEY = "lmhehinq";
    
    /**
     * accesstoken 的有效期
     */
    public static final long SESSION_ACCESSTOKEN_KEY_TIME = 86400;
    
    public static final int APP_ACCESSTOCKEN_TIME = 30;//APP失效30天
    
    // 后台session key
    public static final String SESSION_ADMIN_KEY = "session_admin_key";

    // 前台session key
    // 当前登录用户id: Integer
    public static final String SESSION_FRONT_KEY = "session_front_key";
    
    public static final String SESSION_FRONT_PHONE = "session_front_phone";
    public static final String SESSION_USER_INFO = "session_user_info";

    /**
     * 表名前缀
     */
    public static final String TAGLE_PRE = "htz_";

    /**
     * 提示页面的路径
     */
    public static final String SHOW_MESSAGE_PAGE = "result/showMessage";
    /**
     * 提示页面的路径--后台
     */
    public static final String ADMIN_SHOW_MESSAGE_PAGE = "admin/common/showMessage";
    /**
     * 分页对象
     */
    public static final String PAGE_VIEW = "pageView";
    
    /**
	  * 充值银行
	  */
	 public static final String METADATA_BANK = "umpay";
	 
	 /**
	  * 线上活动类型--注册奖励、新手指南
	  */
	 public static final String ACTIVE_TYPE = "activetype";
    
	 
	 
	   
		/**
		 * 短信服务商--微网
		 */
		public static final String SMS_WEIWANG = "WeiWang";
		
		/**
		 * 重新发送
		 */
		public static final Integer SEND_REPEAT = 0;
		/**
		 * 立即发送
		 */
		public static final Integer SEND_IMMEDIATE = 1;
		/**
		 * 延迟发送
		 */
		public static final Integer SEND_DELAY = 2;
    /**
	 * 元数据分类常量
	 */
	public static final Map<String,String> METADATA_TYPE = new HashMap<String,String>(){
		{
			put(METADATA_BANK,"提现银行");
			put(ACTIVE_TYPE,"线上活动类型");
		}
	};
	

    // 短信验证码类型
    /**
     * 邮箱
     */
    public static final int VALIDCODE_EMAIL = 1;
    
    /**
     * 注册验证手机号
     */
    public static final int VALIDCODE_REGISTER_PHONE = 12;

    /**
     * 手机号
     */
    public static final int VALIDCODE_PHONE = 2;

    /**
     * 添加银行帐户
     */
    public static final int VALIDCODE_ADDBANKACCOUNT = 3;
    
    /**
     * 债权转让手机动态码
     */
    public static final int VALIDCODE_SELLDYNAMICCODE = 4;
    
    /**
     * 自动投资
     */
    public static final int VALIDCODE_AUTOINVESTCODE = 5;
    
    /**
     * 设置支付密码手机动态码
     */
    public static final int VALIDCODE_PAYMENTCODE = 6;
    
    /**
     * 找回支付密码手机动态码
     */
    public static final int VALIDCODE_FORGETPAYMENTCODE = 7;
    
    /**
     * 找回登录密码手机动态码
     */
    public static final int VALIDCODE_FORGETLOGINODE = 8;
    
    /**
     * 解冻资金
     */
    public static final int VALIDCODE_FREEAMOUNT = 9;
    
    /**
     * 删除银行卡
     */
    public static final int VALIDCODE_DELETEBANKACCOUNT = 10;
    
    /**
     * 验证身份-修改昵称、已验证手机、邮箱
     */
    public static final int VALIDCODE_AUTHENTICATION  = 11;
    
    /**
     * 企业融资申请
     */
    public static final int VALIDCODE_COMPANYFINANCAPPLY  = 13;
    
    /**
     * 提前赎回申请
     */
    public static final int VALIDCODE_REDEEMAPPLY  = 14;
    
    /**
     * 设置登录密码
     */
    public static final int SET_LOGIN_PASSWORD  = 15;
    
    /**
     * 需要验证码次数限制的短信验证类型
     */
    public static final String NEED_PHONETYPELIMIT = "3,12";
    
    /**
     * 语音验证码-重复播放次数
     */
    public static final String VOICECODE_PAYTIMES= "3";
    
    // 积分变动加减类型
    /**
     * 增加
     */
    public static final int CREDIT_ADD = 1;

    /**
     * 减少
     */
    public static final int CREDIT_REDUCE = 2;
    

    // 图形验证码
    /**
     * 页面登录
     */
    public static final String LOGINSECCODE = "loginSecCode";
    /**
     * 弹层登录
     */
    public static final String LOGINLAYERSECCODE = "loginLayerSecCode";
    /**
     * 企业融资申请时使用的验证码
     */
    public static final String CORPAPPLYCODE = "corpapplyCode";

    /**
     * 支付
     */
    public static final String PAYSECCODE = "paySecCode";
    
    /**
     * 修改支付密码
     */
    public static final String  UPDATEPAYPWDCODE="UpdatePayPwdCode";

    /**
     * 密码保护
     */
    public static final String PWDPROTECTIONSECCODE = "ppSecCode";
    
    /**
     * 接口实名认证
     */
    public static final String REALNAMESECCODE1 = "realname_j";
    
    /**
     * 提交实名认证
     */
    public static final String REALNAMESECCODE2 = "realname_s";
    
    /**
     * 帐户充值
     */
    public static final String RECHARGECODE = "rechargeCode";
    
    /**
     * 确认投资信息
     */
    public static final String INVESTSECCODE = "investSecCode";
    /**
     * 问卷调查
     */
    public static final String QUESTIONNAIRESECCODE = "questionnaireSecCode";
    
    /**
     * 用户反馈
     */
    public static final String FEEDBACKESECCODE = "feedbackSecCode";
    
    
    /**
     * 项目讨论
     */
    public static final String COMMENTSECCODE = "commentSecCode";
    /**
     * 注册验证手机/手机认证
     */
    public static final String RPHONECCODE = "rphone";
    
    /**
     * 找回登录密码
     */
    public static final String FINDLOGINPASSWORDCODE = "findLoginPasswordCode";
    
    /**
     * 找回支付密码
     */
    public static final String FINDPAYMENTPASSWORDCODE = "findPaymentPasswordCode";
    
    /**
     * 邮箱激活
     */
    public static final String EMAILACTIVEDCODE = "emailActivedCode";
    
    /**
     * 债权转让手机动态验证
     */
    public static final String DynamicPhoneCode = "sellDynamicPhoneCode";
    
    /**
     * 绑定银行/设置支付密码
     */
    public static final String OtherPhoneCode = "otherPhoneCode";
    
    /**
     * 添加我的汇投金
     */
    public static final String PROMOCODE = "promoCode";
    
    /**
     * 验证身份
     */
    public static final String AUTHENTICATECODE = "authenticateCode";
    
    /**
     * 修改用户信息
     */
    public static final String UPDATEUSERCODE = "updateUserCode";
    /**
     * 申请融资
     */
    public static final String BORROWAPPLY = "borrowSecCode";
    
    /**
     * 净值标
     */
    public static final String NETPROJECTCODE = "netProjectCode"; 
    
    public static final String CODETYPES = LOGINSECCODE + "," +LOGINLAYERSECCODE+","+ PAYSECCODE +","+PWDPROTECTIONSECCODE+","+UPDATEPAYPWDCODE
    		+","+CORPAPPLYCODE+","+REALNAMESECCODE1+","+REALNAMESECCODE2+","
    		+RECHARGECODE+","+COMMENTSECCODE+","+INVESTSECCODE+","+QUESTIONNAIRESECCODE+","+FEEDBACKESECCODE+","+RPHONECCODE+","+DynamicPhoneCode+","+OtherPhoneCode
    		+FINDLOGINPASSWORDCODE+","+FINDPAYMENTPASSWORDCODE+","+EMAILACTIVEDCODE+","+PROMOCODE+","+UPDATEUSERCODE
    		+","+AUTHENTICATECODE+","+BORROWAPPLY+","+NETPROJECTCODE;

    // 后台管理标题内容型数据对应数据
    /**
     * 业务类型-企业直投
     */
    public static final int BUSINESS_TYPE = 1;

    /**
     * 业务类型-债权转让
     */
    public static final int BUSINESS_BONDS_TYPE = 2;

    // 金额的收入和支出方向
    /**
     * 收入
     */
    public static final int MONEY_DIRECTION_ACCEPT = 1;

    /**
     * 支出
     */
    public static final int MONEY_DIRECTION_PAY = 2;

    // 奖励账户流水的操作类型
    /**
     * 注册奖励生效
     */
    public static final int REWARDLOG_TYPE_REGISTER = 1;

    /**
     * 推广奖励生效
     */
    public static final int REWARDLOG_TYPE_EXTEND = 2;

    /**
     * 投资付款
     */
    public static final int REWARDLOG_TYPE_INVEST = 3;
    
    /**
     * 推广
     */
    public static final String SPREADCODE = "spreadCode";
    
    // 投资状态变化
    /**
     * 融资中
     */
    public static final int INVEST_GOING = 1;
    
    /**
     * 已满额
     */
    public static final int INVEST_FULL = 2;
    
    /**
     * 收益中
     */
    public static final int INVEST_PROCEEDS = 3;
    

	/**
	 * 自定义记录cookie名
	 */
	public static final String VISIT_COOKIE_KEY = "search_cookie";
	
	/**
	 * search_cookie过期时间--一个月,单位秒
	 */
	public static final int COOKIE_MAX_AGE= 2592000;
	
	/**
	 * jsesssionid_cookie过期时间:1天,单位秒
	 */
	public static final int JSEESIONID_COOKIE_MAX_AGE= 86400*30;
	/**
	 * jsesssionid_cookie过期时间:60天,单位秒
	 */
	public static final int JSEESIONID_COOKIE_MAX_AGE60= 86400*30*60;
	
	 // 前台session key
    public static final String COOKIE_DOMAIN = "127.0.0.1";
	
    // 项目复审状态变化
    /**
     * 待审核
     */
    public static final int RECHECK_WAIT = 0;
    
    /**
     * 完成第一审核
     */
    public static final int RECHECK_FIRST = 1;
    
    /**
     * 完成第二审核
     */
    public static final int RECHECK_SECOND = 2;
    
    /**
 	 * 优惠券来源
 	 */
    public static final String JIAXIQUAN_FRIEND_INVEST = "friend_invest";//邀请好友成功投资获优惠券
    public static final String JIAXIQUAN_FRIEND_GIVE = "friend_give";//来自好友转赠的优惠券
    public static final String JIAXIQUAN_XINSHOW_LOTTERY = "xinshou_lottery";//新手抽奖获优惠券
    public static final String JIAXIQUAN_VIP_BIRTHDAY = "vip_birthday";//生日专属优惠券
    public static final String JIAXIQUAN_SHARE_FRILOTTERY = "share_frilottery";//与好友共享抽奖所获优惠券
    public static final String JIAXIQUAN_WEIBO = "weibo";//微博活动专属优惠券
    public static final String JIAXIQUAN_WEIXIN = "weixin";//微信活动专属优惠券
    public static final String JIAXIQUAN_CAISHENBI_LOTTERY = "caishenbi_lottery";//红包抽奖获优惠券
    public static final String LOGIN_URL = "/";
	
	public static final String SESSION_USER	= "sessionUser";
	public static final String SESSION_VERIFY_CODE = "verifyCode";
	public static final String SESSION_MSG_CODE = "msgCode";
	public static final String SESSION_DATE_FORMAT_TYPE = "dateFormatType";
	public static final String SESSION_EMAIL_CODE = "emailCode";
	public static final String SESSION_PHONE_CODE = "phoneCode";
	public static final String SESSION_REGISETER_PHONE = "registerPhone";
	public static final String NO_INTERCEPTOR_PATH = ".*/((assets)|(js)|(images)|(toIndex)|(verifyLogin)|(logout)|(verifycode*)|(common/domain)).*";
	
    /**
     * 优惠券来源名称
     */
    public static final Map<String,String> JIAXIQUAN_CATEGORY_MAP = new HashMap<String,String>(){
		{
			put(JIAXIQUAN_FRIEND_INVEST,"邀请好友成功投资获加优惠券");
			put(JIAXIQUAN_FRIEND_GIVE,"来自好友转赠的优惠券");
			put(JIAXIQUAN_XINSHOW_LOTTERY,"新手抽奖获优惠券");
			put(JIAXIQUAN_VIP_BIRTHDAY,"生日专属优惠券");
			put(JIAXIQUAN_SHARE_FRILOTTERY,"与好友共享抽奖所获优惠券");
			put(JIAXIQUAN_WEIBO,"微博活动专属优惠券");
			put(JIAXIQUAN_WEIXIN,"微信活动专属优惠券");
			put(JIAXIQUAN_CAISHENBI_LOTTERY,"红包抽奖获优惠券");
		}
	};
    
}
