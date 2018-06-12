package com.shoufubang.model.util;

/**
 * 站内信，邮件，短信，微信发送属性
 * @author 2016年6月27日10:14:27 by 李帅
 *
 */
public class MsgCfgSwitchType {
	
	/**
	 * 需要发送消息的交易类型：
	 * 0-验证码、1-充值、2-投资、3-提现成功、6-奖励兑付、8-登录、9-新项目上线、10-提现失败、
	 * 11-网站统计、12-注册欢迎、13-注册赠送体验金、14-注册手机通过验证、15-注册赠送投资券
	 * 16-提现申请成功、17-提现申请失败、18-注册请求异常预警、19-验证码请求异常预警、20-实名认证请求异常预警
	 * 21-注册赠送30元现金投资红包
	 * 
	 * 以下三项由Admin系统负责发送通知：4-付息、5-还本、7-资金流向
	 */
	public static final byte MESSAGE_SEND_TYPE_VERIFICATIONCODE = 0;
	public static final byte MESSAGE_SEND_TYPE_RECHARGE = 1;
	public static final byte MESSAGE_SEND_TYPE_INVEST = 2;
	public static final byte MESSAGE_SEND_TYPE_EXCASH_SUCCESS = 3;
	public static final byte MESSAGE_SEND_TYPE_PAY_INTEREST = 4;
	public static final byte MESSAGE_SEND_TYPE_REPAY = 5;
	public static final byte MESSAGE_SEND_TYPE_REWARD = 6;
	public static final byte MESSAGE_SEND_TYPE_CAPITAL = 7;
	public static final byte MESSAGE_SEND_TYPE_LOGIN = 8;
	public static final byte MESSAGE_SEND_TYPE_SYSTEM_UP = 9;
	public static final byte MESSAGE_SEND_TYPE_EXCASH_FAILED = 10;
	public static final byte MESSAGE_SEND_TYPE_WEB_STATISTICS = 11;
	public static final byte MESSAGE_SEND_TYPE_REGISTER_WELCOME = 12;
	public static final byte MESSAGE_SEND_TYPE_REGISTER_EXPERIENCE = 13;
	public static final byte MESSAGE_SEND_TYPE_REGISTER_MOBILE_AUTH = 14;
	public static final byte MESSAGE_SEND_TYPE_REGISTER_INVEST = 15;
	public static final byte MESSAGE_SEND_TYPE_EXCASH_APPLY_SUCCESS = 16;
	public static final byte MESSAGE_SEND_TYPE_EXCASH_APPLY_FAILED = 17;
	public static final byte MESSAGE_SEND_TYPE_REGISTER_WARNING = 18;
	public static final byte MESSAGE_SEND_TYPE_VERIFICATIONCODE_WARNING = 19;
	public static final byte MESSAGE_SEND_TYPE_REALNAME_WARNING = 20;
	public static final byte MESSAGE_SEND_TYPE_REGISTER_30EXPERIENCE = 21;
	
	/**
	 * 发送支持方式：0-不支持发送     1-支持发送
	 */
	public static final byte MESSAGE_SEND_SWITCH_NO = 0;
	public static final byte MESSAGE_SEND_SWITCH_YES = 1;
	
	/**
	 * 消息是否已读：0-未读     1-已读
	 */
	public static final byte MESSAGE_READ_NO = 0;
	public static final byte MESSAGE_READ_YES = 1;
	
	/**
	 * 邮件标题，邮件服务器端模板名称
	 */
	public enum MsgCfgTitleType {

		MSG_SERVICE_RECHARGE((byte)1, "充值成功"),
		MSG_SERVICE_INVEST((byte)2, "投资成功"),
		MSG_SERVICE_EXCASH_SUCCESS((byte)3, "提现成功"),
		MSG_SERVICE_PAY_INTEREST((byte)4, "付息成功"),
		MSG_SERVICE_REPAY((byte)5, "还本成功"),
		MSG_SERVICE_REWARD((byte)6, "奖励兑付成功"),
		MSG_SERVICE_CAPITAL((byte)7, "资金流向"),
		MSG_SERVICE_LOGIN((byte)8, "登录成功"),
		MSG_SERVICE_SYSTEM_UP((byte)9, "新项目上线"),
		MSG_SERVICE_EXCASH_FAILED((byte)10, "提现失败"),
		MSG_SERVICE_WEB_STATISTICS((byte)11, "网站统计"),
		MSG_SERVICE_REGISTER_WELCOME((byte)12, "注册欢迎"),
		MSG_SERVICE_REGISTER_EXPERIENCE((byte)13, "注册赠送体验金"),
		MSG_SERVICE_REGISTER_MOBILE_AUTH((byte)14, "注册手机通过验证"),
		MSG_SERVICE_REGISTER_INVEST((byte)15, "百合注册赠送30元现金投资券"),
		MSG_SERVICE_EXCASH_APPLY_SUCCESS((byte)16, "提现申请成功"),
		MSG_SERVICE_EXCASH_APPLY_FAILED((byte)17, "提现申请失败"),
		MSG_SERVICE_REGISTER_WARNING((byte)18, "注册请求异常预警"),
		MSG_SERVICE_VERIFICATIONCODE_WARNING((byte)19, "验证码请求异常预警"),
		MSG_SERVICE_REALNAME_WARNING((byte)20, "实名认证请求异常预警"),
		MSG_SERVICE_REGISTER_30EXPERIENCE((byte)21, "注册赠送30元现金投资红包");
		
		private byte serviceId;
		private String msgContent;
		
		public byte getServiceId() {
			return serviceId;
		}
		public void setServiceId(byte serviceId) {
			this.serviceId = serviceId;
		}
		public String getMsgContent() {
			return msgContent;
		}
		public void setMsgContent(String msgContent) {
			this.msgContent = msgContent;
		}
		private MsgCfgTitleType(byte serviceId, String msgContent) {
			this.serviceId = serviceId;
			this.msgContent = msgContent;
		}
		private MsgCfgTitleType() {
		}

		public static String getMsgTitle(byte serviceId) {
			for(MsgCfgTitleType push: MsgCfgTitleType.values()) {
				if (serviceId == push.getServiceId()) {
					return push.getMsgContent();
				}
			}
			return "未定义通知类型";
		}
	}
	
	/**
	 * 邮件模板
	 */
	public enum MsgCfgMailTempType {

		MSG_SERVICE_RECHARGE((byte)1, "rechargeMail.ftl"),
		MSG_SERVICE_INVEST((byte)2, "investMail.ftl"),
		MSG_SERVICE_EXCASH_SUCCESS((byte)3, "excashSuccessMail.ftl"),
		MSG_SERVICE_PAY_INTEREST((byte)4, "payInterestMail.ftl"),
		MSG_SERVICE_REPAY((byte)5, "repayMail.ftl"),
		MSG_SERVICE_REWARD((byte)6, "rewardMail.ftl"),
		MSG_SERVICE_CAPITAL((byte)7, "capitalMail.ftl"),
		MSG_SERVICE_LOGIN((byte)8, "loginMail.ftl"),
		MSG_SERVICE_SYSTEM_UP((byte)9, "systemUpMail.ftl"),
		MSG_SERVICE_EXCASH_FAILED((byte)10, "excashFailedMail.ftl"),
		MSG_SERVICE_WEB_STATISTICS((byte)11, "webStatistics.ftl"),
		MSG_SERVICE_REGISTER_WARNING((byte)18, "registerWarning.ftl"),
		MSG_SERVICE_VERIFICATIONCODE_WARNING((byte)19, "verificationCodeWarning.ftl"),
		MSG_SERVICE_REALNAME_WARNING((byte)20, "realNameWarning.ftl");
		
		private byte serviceId;
		private String templateName;
		
		public byte getServiceId() {
			return serviceId;
		}
		public void setServiceId(byte serviceId) {
			this.serviceId = serviceId;
		}
		public String getTemplateName() {
			return templateName;
		}
		public void setTemplateName(String templateName) {
			this.templateName = templateName;
		}
		private MsgCfgMailTempType(byte serviceId, String templateName) {
			this.serviceId = serviceId;
			this.templateName = templateName;
		}
		private MsgCfgMailTempType() {
		}

		public static String getTemplate(byte serviceId) {
			for(MsgCfgMailTempType push: MsgCfgMailTempType.values()) {
				if (serviceId == push.getServiceId()) {
					return push.getTemplateName();
				}
			}
			return null;
		}
	}
	
	/**
	 * 邮件服务器模板ID
	 */
	public enum MsgCfgMailTempID {

		MSG_SERVICE_RECHARGE((byte)1, 13),
		MSG_SERVICE_INVEST((byte)2, 7),
		MSG_SERVICE_EXCASH_SUCCESS((byte)3, 11),
		MSG_SERVICE_PAY_INTEREST((byte)4, 8),
		MSG_SERVICE_REPAY((byte)5, 8),
		MSG_SERVICE_REWARD((byte)6, 15),
		MSG_SERVICE_CAPITAL((byte)7, 14),
		MSG_SERVICE_LOGIN((byte)8, 27),
		MSG_SERVICE_SYSTEM_UP((byte)9, null),
		MSG_SERVICE_EXCASH_FAILED((byte)10, 12),
		MSG_SERVICE_WEB_STATISTICS((byte)11, 10),
		MSG_SERVICE_REGISTER_WARNING((byte)18, 28),
		MSG_SERVICE_VERIFICATIONCODE_WARNING((byte)19, 28),
		MSG_SERVICE_REALNAME_WARNING((byte)20, 28);
		
		private byte serviceId;
		private Integer templateId;
		
		public byte getServiceId() {
			return serviceId;
		}
		public void setServiceId(byte serviceId) {
			this.serviceId = serviceId;
		}
		public Integer getTemplateId() {
			return templateId;
		}
		public void setTemplateId(Integer templateId) {
			this.templateId = templateId;
		}
		private MsgCfgMailTempID(byte serviceId, Integer templateId) {
			this.serviceId = serviceId;
			this.templateId = templateId;
		}
		private MsgCfgMailTempID() {
		}

		public static Integer getTemplate(byte serviceId) {
			for(MsgCfgMailTempID push: MsgCfgMailTempID.values()) {
				if (serviceId == push.getServiceId()) {
					return push.getTemplateId();
				}
			}
			return null;
		}
	}
	
	/**
	 * 短信模板
	 */
	public enum MsgCfgSmsTempType {

		MSG_SERVICE_VERIFICATIONCODE((byte)0, "verificationCode.ftl"),
		MSG_SERVICE_RECHARGE((byte)1, "rechargeSms.ftl"),
		MSG_SERVICE_INVEST((byte)2, "investSms.ftl"),
		MSG_SERVICE_EXCASH_SUCCESS((byte)3, "excashSuccessSms.ftl"),
		MSG_SERVICE_PAY_INTEREST((byte)4, "payInterestSms.ftl"),
		MSG_SERVICE_REPAY((byte)5, "repaySms.ftl"),
		MSG_SERVICE_REWARD((byte)6, "rewardSms.ftl"),
		MSG_SERVICE_CAPITAL((byte)7, "capitalSms.ftl"),
		MSG_SERVICE_LOGIN((byte)8, "loginSms.ftl"),
		MSG_SERVICE_SYSTEM_UP((byte)9, "systemUpSms.ftl"),
		MSG_SERVICE_EXCASH_FAILED((byte)10, "excashFailedSms.ftl"),
		MSG_SERVICE_REGISTER_EXPERIENCE((byte)13, "registerExperienceSms.ftl"),
		MSG_SERVICE_REGISTER_INVEST((byte)15, "registerInvestSms.ftl"),
		MSG_SERVICE_EXCASH_APPLY_SUCCESS((byte)16, "excashApplySuccessSms.ftl"),
		MSG_SERVICE_EXCASH_APPLY_FAILED((byte)17, "excashApplyFailedSms.ftl"),
		MSG_SERVICE_REGISTER_30EXPERIENCE((byte)21, "register30ExperienceSms.ftl");

		
		private byte serviceId;
		private String templateName;
		
		public byte getServiceId() {
			return serviceId;
		}
		public void setServiceId(byte serviceId) {
			this.serviceId = serviceId;
		}
		public String getTemplateName() {
			return templateName;
		}
		public void setTemplateName(String templateName) {
			this.templateName = templateName;
		}
		private MsgCfgSmsTempType(byte serviceId, String templateName) {
			this.serviceId = serviceId;
			this.templateName = templateName;
		}
		private MsgCfgSmsTempType() {
		}

		public static String getTemplate(byte serviceId) {
			for(MsgCfgSmsTempType push: MsgCfgSmsTempType.values()) {
				if (serviceId == push.getServiceId()) {
					return push.getTemplateName();
				}
			}
			return null;
		}
	}
	
	/**
	 * 站内信模板
	 */
	public enum MsgCfgWebTempType {

		MSG_SERVICE_RECHARGE((byte)1, "rechargeWeb.ftl"),
		MSG_SERVICE_INVEST((byte)2, "investWeb.ftl"),
		MSG_SERVICE_EXCASH_SUCCESS((byte)3, "excashSuccessWeb.ftl"),
		MSG_SERVICE_PAY_INTEREST((byte)4, "payInterestWeb.ftl"),
		MSG_SERVICE_REPAY((byte)5, "repayWeb.ftl"),
		MSG_SERVICE_REWARD((byte)6, "rewardWeb.ftl"),
		MSG_SERVICE_CAPITAL((byte)7, "capitalWeb.ftl"),
		MSG_SERVICE_SYSTEM_UP((byte)9, "systemUpWeb.ftl"),
		MSG_SERVICE_EXCASH_FAILED((byte)10, "excashFailedWeb.ftl"),
		MSG_SERVICE_REGISTER_WELCOME((byte)12, "registerWelcomeWeb.ftl"),
		MSG_SERVICE_REGISTER_EXPERIENCE((byte)13, "registerExperienceWeb.ftl"),
		MSG_SERVICE_REGISTER_MOBILE_AUTH((byte)14, "registerMobileAuthWeb.ftl"),
		MSG_SERVICE_REGISTER_INVEST((byte)15, "registerInvestWeb.ftl"),
		MSG_SERVICE_EXCASH_APPLY_SUCCESS((byte)16, "excashApplySuccessWeb.ftl"),
		MSG_SERVICE_EXCASH_APPLY_FAILED((byte)17, "excashApplyFailedWeb.ftl"),
		MSG_SERVICE_REGISTER_30EXPERIENCE((byte)21, "register30ExperienceWeb.ftl");

		
		private byte serviceId;
		private String templateName;
		
		public byte getServiceId() {
			return serviceId;
		}
		public void setServiceId(byte serviceId) {
			this.serviceId = serviceId;
		}
		public String getTemplateName() {
			return templateName;
		}
		public void setTemplateName(String templateName) {
			this.templateName = templateName;
		}
		private MsgCfgWebTempType(byte serviceId, String templateName) {
			this.serviceId = serviceId;
			this.templateName = templateName;
		}
		private MsgCfgWebTempType() {
		}

		public static String getTemplate(byte serviceId) {
			for(MsgCfgWebTempType push: MsgCfgWebTempType.values()) {
				if (serviceId == push.getServiceId()) {
					return push.getTemplateName();
				}
			}
			return null;
		}
	}
	
	/**
	 * 微信目前支持业务Bean类路径
	 */
	public enum MsgCfgWeChatClassBeanType {

		MSG_SERVICE_RECHARGE((byte)1, "com.moonbox.mb.thirdparty.ump.service.wechat.notice.entity.RechargeInfo"),
		MSG_SERVICE_INVEST((byte)2, "com.moonbox.mb.thirdparty.ump.service.wechat.notice.entity.InvestParams"),
		MSG_SERVICE_EXCASH_SUCCESS((byte)3, "com.moonbox.mb.thirdparty.ump.service.wechat.notice.entity.WithdrawVO"),
		MSG_SERVICE_LOGIN((byte)8, "com.moonbox.mb.thirdparty.ump.service.wechat.notice.entity.LoginNoticeInfo"),
		MSG_SERVICE_EXCASH_FAILED((byte)10, "com.moonbox.mb.thirdparty.ump.service.wechat.notice.entity.WithdrawVO");
		
		private byte serviceId;
		private String className;
		
		public byte getServiceId() {
			return serviceId;
		}
		public void setServiceId(byte serviceId) {
			this.serviceId = serviceId;
		}
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}
		private MsgCfgWeChatClassBeanType(byte serviceId, String className) {
			this.serviceId = serviceId;
			this.className = className;
		}
		private MsgCfgWeChatClassBeanType() {
		}

		public static String getWeChatClassBean(byte serviceId) {
			for(MsgCfgWeChatClassBeanType push: MsgCfgWeChatClassBeanType.values()) {
				if (serviceId == push.getServiceId()) {
					return push.getClassName();
				}
			}
			return null;
		}
	}
	
	/**
	 * 微信目前支持业务Bean参数列表，注意列表属性顺序决定了各业务中参数传递顺序
	 */
	public enum MsgCfgWeChatType {

		MSG_SERVICE_RECHARGE((byte)1, new String[]{"firstData", "account", "recharge_money", "recharge_channel", "recharge_status", "recharge_bank", "uid"}),
		MSG_SERVICE_INVEST((byte)2, new String[]{"firstData", "investmoney", "projectname", "projecttype", "repaydate", "repaytype", "guaranteecorp", "uid"}),
		MSG_SERVICE_EXCASH_SUCCESS((byte)3, new String[]{"wtype", "cashbank", "cashcard", "remarkData", "cashmoney", "uid", "isSucc", "firstData"}),
		MSG_SERVICE_LOGIN((byte)8, new String[]{"username", "vdef1", "uid"}),
		MSG_SERVICE_EXCASH_FAILED((byte)10, new String[]{"wtype", "cashbank", "cashcard", "remarkData", "cashmoney", "uid", "isSucc", "firstData"});
		
		private byte serviceId;
		private String[] weChatParams;
		
		public byte getServiceId() {
			return serviceId;
		}
		public void setServiceId(byte serviceId) {
			this.serviceId = serviceId;
		}
		public String[] getWeChatParams() {
			return weChatParams;
		}
		public void setWeChatParams(String[] weChatParams) {
			this.weChatParams = weChatParams;
		}
		private MsgCfgWeChatType(byte serviceId, String[] weChatParams) {
			this.serviceId = serviceId;
			this.weChatParams = weChatParams;
		}
		private MsgCfgWeChatType() {
		}

		public static String[] getMsgParams(byte serviceId) {
			for(MsgCfgWeChatType push: MsgCfgWeChatType.values()) {
				if (serviceId == push.getServiceId()) {
					return push.getWeChatParams();
				}
			}
			return null;
		}
	}
}
