package com.shoufubang.model.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/** 
 * <pre>项目名称：shoufubang-tool-model    
 * 类名称：Global    
 * 类描述：    
 * 创建人：李帅 ls314397644@163.com   
 * 创建时间：2016年11月28日 下午5:21:37    
 * 修改人：李帅 ls314397644@163.com    
 * 修改时间：2016年11月28日 下午5:21:37    
 * 修改备注：       
 * @version </pre>    
 */
public class Global {

	public	static Logger logger = LoggerFactory.getLogger(Global.class);
	public final static Double  BIGMONEY       =  10000.00;//比较超过10000发短信
	public final static Integer PASS_AUDIT     = 1;       //审核通过
	public final static Integer REFUSE         = 2;      //审核拒绝
/*
 *  101     ------------融资相关
 *  10101	101	等待审批		1	1
 *	10102	101	等待放款		1	1
 *	10103	101	已放款		1	1
 *	10104	101	部分结清		1	1
 *	10105	101	已结清		1	1
 *	10106	101	融资审核拒绝	1	1
 * 	10107	101	放款拒绝		1	1
 *  10108	101	逾期			1	1
 */
	public final static Integer FINANCE_PID    = 101;//  融资状态 对应 sfb_asset_dict 字典编码id
	public final static Integer WAIT_AUDIT     = 10101;
	public final static Integer WAIT_LOAN      = 10102;
	public final static Integer ALREADY_AUDIT  = 10103;
	public final static Integer PART_REPAY     = 10104;
	public final static Integer ALREADY_REPAY  = 10105;
	public final static Integer REFUSE_APPLY   = 10106;
	public final static Integer REFUSE_LOAN    = 10107;
	public final static Integer OVERDUE    = 10108;
/*
 *  102     ----------------发票原始状态
 *  10201	101	未申请融资(未审核)		1	1
 * 	10202	102	已融资		1	1
 * 	10203	102	审核拒绝		1	1
 * 	10204	102	审核通过		1	1
 */
	public final static Integer ORIGINAL_PID    = 102;//发票原始 pid
	public final static Integer UNAPPLY_INVOICE = 10201;
	public final static Integer APPLY_INVOICE   = 10202;
	public final static Integer APPLY_REFUSE   = 10203;
	public final static Integer APPLY_FINANCING   = 10204;
 
 /*  103     ----------------发票关联项目表发票状态
 *  10301	103	未结清		1	1
 *	10302	103	已结清		1	1
 */
	public final static Integer PAY_PID          = 103;//融资发票状态
	public final static Integer UNSETTLE_INVOICE = 10301;
	public final static Integer SETTLE_INVOICE   =10302;
/*	本金状态
	10401	104	正常		1	1
	10402	104	逾期		1	1
	10403	104	坏账		1	1
*/	
	public final static Integer PROPAY_PID         = 104;//本金状态
	public final static Integer NORMAL_PRINCIPAL   = 10401;
	public final static Integer OVERDUE_PRINCIPAL  = 10402;
	public final static Integer BADDEBT_PRINCIPAL  = 10403;
	
/*	利息状态
	10501	105	正常		1	1
	10502	105	逾期		1	1
*/	
	public final static Integer INTEREST_PID      = 105;//利息状态
	public final static Integer NORMAL_INTEREST   = 10501;
	public final static Integer OVERDUE_INTEREST  = 10502;	
	
/*	 10601   106  按月付息到期还本       1   1
	 10602   106 一次还本付息    1   1
	 10603   106  先收利息到期还本    1   1
*/
	public final static Integer REPAYMENT_PID     =106;//还款方式pid
	public final static Integer REPAYMENT_ONE    =10601;
	public final static Integer REPAYMENT_TWO    =10602;
	public final static Integer REPAYMENT_THREE    =10603;	
	
	
/*  10701	107	融资申请		1
	10702	107	融资审核		1
	10703	107	融资放款		1
	10704	107	买方还款		1
	10705	107	卖方还款		1
	10706	107	保理商还款		1
	10707	107	融资逾期		1
	10708   107  融资驳回
*/
	public final static Integer  LOAN_TYPE       =107;//交易类型原始pid
	public final static Integer  LOAN_APPLY      =10701;
	public final static Integer  LOAN_VERIFY     =10702;
	public final static Integer  LOAN_CREDIT     =10703;
	public final static Integer  REPAY_BUY       =10704;
	public final static Integer  REPAY_SELLE     =10705;
	public final static Integer  REPAY_FACTOR    =10706;
	public final static Integer  REPAY_OVERDUE   =10707;
	public final static Integer  REJECT_OVERDUE  =10708;
	
/*	 10801   108  应收       1   1
     10802   108  预收    1   1
*/
	public final static Integer PRODUCT_PID        =108;//产品收息方式原始pid
	public final static Integer TYPE_RECEIVABLE    =10801;


	public final static Integer TYPE_ADVANCE       =10802;

/*	 10901   109  先收       1   1
	 10902   109  后收    1   1
*/
	public final static Integer OTHERF_PID        =109;//其他费用收息方式原始pid
	public final static Integer TYPE_OTHERF       =10901;
	public final static Integer TYPE_OTHERH       =10902;

/*	
	 11001	110	还原		1
	 11002	110	动用		1
	 11003	110	暂用		1
*/
	public final static Integer CREDIT_PID     		  =110;//额度操作
	public final static Integer CREDIT_REDUCTION      =11001;
	public final static Integer CREDIT_USE            =11002;
	public final static Integer CREDIT_PROVISIONAL    =11003;
	
	
/*	服务费收取状态
	11101	111	待支付		1
	11102	111	待确认		1
	11103	111	已确认		1	
*/
	public final static Integer CHARGE_PID     		  =111;//服务费收取方式pid
	public final static Integer CHARGE_BEPAID         =11101;
	public final static Integer CHARGE_CONFIRM        =11102;
	public final static Integer CHARGE_CFMD           =11103;
	
	/*	融资过程状态
		11301	113	融资申请成功
		11302	113	融资申请审核中
		11303	113	融资申请审核通过
		11304	113	融资申请审核未通过
		11305	113	放款成功
		11306	113	预计放款时间（审核通过后数分钟内）
		11307	113	还款中
		11308	113	还款完结		
	*/
	public final static Integer PROJECT_PROCESS_PID     		  =113;//融资过程状态pid
	public final static Integer PROCESS_SUCCESSFUL_APPLICATION    =11301;
	public final static Integer PROCESS_IN_AUDIT                  =11302;
	public final static Integer PROCESS_AUDITED                   =11303;
	public final static Integer PROCESS_AUDIT_FAILED              =11304;
	public final static Integer PROCESS_LOAN_SUCCESS              =11305;
	public final static Integer PROCESS_IN_LOAN                   =11306;
	public final static Integer PROCESS_REPAYMENT                 =11307;
	public final static Integer PROCESS_END_OF_REPAYMENT          =11308;
	
	/*	业务类型
	 	11401	114	应收账款融资		1
		11402	114	应付账款融资		1
		11403	114	动产质押融资		1
		11404	114	预融资付款		1
		11405	114	订单融资		1
	*/
	public final static Integer BUSINESS_TYPE_PID                =114;//业务类型pid
	public final static Integer ACCOUNT_RECEIVABLE_FINANCING     =11401;
	public final static Integer ACCOUNTS_PAYABLE_FINANCING       =11402;
	public final static Integer MOVABLE_ASSET_FINANCE       	 =11403;
	public final static Integer PRE_FINANCE_PAYMENT       		 =11404;
	public final static Integer ORDER_FINANCING       			 =11405;
	
	/*	业务模式
	 	11501	115	有追索权		1
		11502	115	无追索权		1
	*/
	public final static Integer BUSINESS_MODEL_PID                =115;//业务模式pid
	public final static Integer RECOURSE     					  =11501;
	public final static Integer WITHOUT_RECOURSE       			  =11502;
	
	/*	付息方
	 	11601	116	买方		1
		11602	116	卖方		1
	*/
	public final static Integer INTEREST_PAYING_PARTY_PID         =116;//付息方pid
	public final static Integer BUYER     					  	  =11601;
	public final static Integer SELLER       			  		  =11602;
	
	/* 
	  20801 208 60天月结	1
	  20802 208 90天月结	1
	  20803 208 120天月结	1
	  20804 208 150天月结	1
	  20805 208 180天月结	1
	  20806 208 210天月结	1
	  20807 208 240天月结	1
	  20808 208 270天月结	1
	  20809 208 300天月结	1
	  208010 208 330天月结	1
	  208011 208 360天月结	1
	 */
	public final static Integer ACCOUNT_STYLE_ONE      =208;//结算方式
	public final static Integer ACCOUNT_STYLE_TWO      =20801;
	public final static Integer ACCOUNT_STYLE_THREE    =20802; 
	public final static Integer ACCOUNT_STYLE_FOUR     =20803; 
	public final static Integer ACCOUNT_STYLE_FIVE     =20804; 
	public final static Integer ACCOUNT_STYLE_SIX      =20805; 
	public final static Integer ACCOUNT_STYLE_SEVEN    =20806; 
	public final static Integer ACCOUNT_STYLE_EIGHT    =20807; 
	public final static Integer ACCOUNT_STYLE_NINE     =20808; 
	public final static Integer ACCOUNT_STYLE_TEN      =20809; 
	public final static Integer ACCOUNT_STYLE_ELEVEN   =208010; 
	public final static Integer ACCOUNT_STYLE_TWELVE   =208011; 

	
	
	
	/* 
	  20901 209   国有企业	1
	  20902 209   集体企业	1
	  20903 209   股份合作企业	1
	  20904 209   联营企业	1
	  20905 209   有限责任公司	1
	  20906 209   股份有限公司	1
	  20907 209   私营企业	1
	  20908 209   港澳台投资企业	1
	  20909 209   外商投资企业	1
	  20910 209  个体经营	1
	  20911 209   其他		1
	 */
	public final static Integer NATURE_PID      =209;//等级注册类型
	public final static Integer NATURE_STATE_OWNED      =20901;
	public final static Integer NATURE_COLLECTIVE      =20902; 
	public final static Integer NATURE_STOCK_PARTNERSHIP      =20903; 
	public final static Integer NATURE_JOINT_OPERATION      =20904; 
	public final static Integer NATURE_LLC      =20905; 
	public final static Integer NATURE_SC      =20906; 
	public final static Integer NATURE_PRIVATE      =20907; 
	public final static Integer NATURE_HMT_INVESTMENT      =20908; 
	public final static Integer NATURE_FOREIGN_INVESTMENT      =20909; 
	public final static Integer NATURE_INDIVADUAL      =209010; 
	public final static Integer NATURE_OTHER      =209011; 

/*
 * 
 * 21101	211	银行变动消息-供应商	贵公司在银行的专用回款账户已有资金到账,贵公司该行有应收账款质押融资业务,请核实上述款项并及时办理相关业务。	2
21102	211	账户变动消息-	贵公司的银行账户有变动，请知悉。	1
21103	211	企业审核通过-供应商	贵公司提交的企业资料审核已通过，请知悉。	2
21104	211	企业审核驳回-供应商	贵公司提交的企业资料未通过审核，请修改后重新提交.	2
21105	211	发票审核已通过-供应商	贵公司提交的发票审核已通过，请知悉。编号为:	2
21106	211	发票审核未通过-供应商	贵公司提交的发票未通过审核，请修改后重新提交，编号为:	2
21107	211	融资已审核	贵公司提交的融资申请审核已通过，请知悉.编号为:	1
21108	211	融资审核未通过	贵公司提交的融资申请未通过审核，请修改后重新提交.编号为：	1
21109	211	融资已放款	贵公司提交的融资申请已放款，请知悉,编号为:	1
21110	211	已还款申请-服务商	公司的融资已申请还款，请知悉，融资编号为：	1
21111	211	已申请融资-资金机构	公司的融资申请已提交，请知悉，融资编号为：	4
21112	211	服务费申请确认-服务商	公司的服务费已支付，请确认，融资编号为：	1
21113	211	发票已上传-核心企业	公司的发票已上传，请审核。发票号码为: 	3
21114	211	发票到期通知-核心企业	贵公司的发票已到期，请知悉。发票号码为：	3
21115	211	服务费确认-供应商	贵公司的融资的服务费，阳禾链已确认收到，融资编号为	1
21116	211	已还款申请-资金机构	公司的融资已申请还款，请知悉，融资编号为：	4
21117	211	发票已上传-服务商	公司的发票已上传，请知悉，发票号码为: 	1
21118	211	发票审核已通过-服务商	公司提交的发票审核已通过，请知悉，发票号码为: 	1
21119	211	发票审核未通过-服务商	公司提交的发票审核未通过，请知悉，发票号码为: 	1
 * 
 * 
 * */
	public final static Integer MESSAGE_PID           =211;//变动消息     
	public final static Integer MESSAGE_BANK          =21101;//银行变动消息    
	public final static Integer MESSAGE_ACCOUNT       =21102;//账户变动消息
	public final static Integer MESSAGE_ALDIT         =21103;//企业审核通过
	public final static Integer MESSAGE_REJECT         =21104;//企业审核驳回
	public final static Integer MESSAGE_INVOICE_AUDIT   =21105;//发票已审核
	public final static Integer MESSAGE_INVOICE_UNAUDIT    =21106;//发票审核未通过
	public final static Integer MESSAGE_FINANCE_AUDIT      =21107;//融资审核通过
	public final static Integer MESSAGE_FINANCE_UNAUDIT    =21108;//融资审核未通过
	public final static Integer GRANT_SUCCESS    =21109;// 放款通过
	public final static Integer REPAY_APPLY = 21110;	// 已申请还款
	public final static Integer MESSAGE_FINANCING_APPLICATION = 21111;	// 申请融资
	public final static Integer MESSAGE_PAYMENT_SERVICE_FEE = 21112;	// 支付服务费
	public final static Integer MESSAGE_INVOICE_UPLODE   =21113;//发票已审核 发票已上传
	public final static Integer MESSAGE_INVOICE_DUE = 21114;	// 发票到期通知
	public final static Integer MESSAGE_SERVICR_CHARGE = 21115; //确定收到服务费
	public final static Integer MESSAGE_APPLY_PAY = 21116; //已还款申请-资金机构	
	public final static Integer MESSAGE_INVOICE_UPLOAD_SERVICE = 21117; //发票已上传
	public final static Integer MESSAGE_INVOICE_CHECKIN_SERVICE = 21118; //发票审核已通过-服务商
	public final static Integer MESSAGE_INVOICE_UNCHECK_SERVICE = 21119; //发票审核未通过-服务商
	/* 	212   --------财务资料
	 *  21201         总收入
	 *  21202 	总利润
	 *  21203	总资产   
	 *  21204         总负债
	 *  21205	 应收账款
	 *  21206	 应付账款   
	 *  21207         银行借贷
	 */
	public final static Integer CAPITAL_TYPE   = 212; 
	public final static Integer CAPITAL_ONE    = 21201; 
	public final static Integer CAPITAL_TWO    = 21202; 
	public final static Integer CAPITAL_THREE  = 21203; 
	public final static Integer CAPITAL_FORE   = 21204; 
	public final static Integer CAPITAL_FIVE   = 21205; 
	public final static Integer CAPITAL_SIX    = 21206;
	public final static Integer CAPITAL_SEVEN  = 21207;
	
/*	30101	301	营业执照		1
    30102	301	税务登记证		1
    30103	301	组织结构代码证		1
    30104	301	开户许可证		1
    30105	301	法人身份证		1
    30106	301	信用代码证		1
    30107	301	统一信用代码证		1
*/
	public final static Integer CERTIFICATE_PID     =301;//五证
	public final static Integer BUSINESS_LICENSE    =30101;
	public final static Integer TAX_REGISTRATION    =30102;
	public final static Integer ORGANIZATION_CODE   =30103;
	public final static Integer ACCOUNT_OPENING     =30104;
	public final static Integer CORPORATE_IDENTITY  =30105;
	public final static Integer CREDIT_CODE  		=30106;
	public final static Integer UNIFORM_CREDIT_CODE =30107;
	
	
	
/*	30201	302	保理合同		1
	30202	301	贸易合同		1
*/
	public final static Integer CONTRACT_PID     		=302;//合同
	public final static Integer CONTRACT_FACTORING    	=30201;
	public final static Integer CONTRACT_TRADE    		=30202;

/*	30401	304	财务报告		1
	30402	304	审计报告		1
	30403	304	企业信用报告		1
	30404	304	历史贸易合同		1
	30405	304	其他附件		1
*/
	public final static Integer ACCESSORIES_PID      =304;//相关附件
	public final static Integer REPORTS_FINANCIAL    =30401;
	public final static Integer REPORT_AUDIT     	 =30402;
	public final static Integer ENTERPRISE_CREDIT    =30403;
	public final static Integer HISTORICAL_TRADE     =30404;
	public final static Integer ACCESSORIES_OTHER    =30405;
	
/*
	30501	305	药品（耗材）经营许可证附件
	30502	305	业务负责人身份证
	30503	305	财务负责人身份证
	30504	305	系统操作员身份证
	30505	305	财务资料附件		1
	30506	305	药品经营质量管理规范认证证书		1
*/
	public final static Integer SUPPLEMENTARYINFORMATION_PID      =305;//补充附件信息
	public final static Integer OPERATING_LICENSE_ANNEX    		  =30501;
	public final static Integer BUSINESSOFFICER_IDCARD    		  =30502;
	public final static Integer FINANCIALOFFICER_IDCARD    		  =30503;
	public final static Integer SYSTEMOPERATOR_IDCARD    		  =30504;
	public final static Integer FINANCIAL_INFORMATION_ANNEX    		  =30505;
	public final static Integer PHARMACEUTICAL_BUSINESS_CERTIFICATE   =30506;

	
	
	
	/*307 
	 *  30701	307	cfca 纸质印模文件		1
		30702	307	cfca 电子印摸文件		1
		30703	307	cfca 电子印章文件		1
		30704	307	cfca pfx证书文件		1
	 * 
	 * */
	public final static Integer  CFCA_PAPER     =30701;
	public final static Integer  CFCA_ELECTRON  =30702;
	public final static Integer  CFCA_SEAL      =30703;
	public final static Integer  CFCA_PFX       =30704;
	
	/*
	 * 31201	312	服务商关联统计表		1
		31202	312	服务商客户额度报表		1
		31203	312	服务商客户统计表		1
		31204	312	服务商买方还款明细表		1
		31205	312	服务商卖方还款明细表		1
		31206	312	服务商融资明细表		1
		31207	312	服务商业务汇总表		1
		31208	312	服务商应收账款明细表		1
		31209	312	服务商逾期明细报表		1
		31210	312	供应商待还款明细表		1
		31211	312	供应商融资明细表		1
		31212	312	供应商已还款明细表		1
		31213	312	供应商应收账款明细表		1
		31214	312	核心企业链属企业统计表		1
		31215	312	核心企业已付账款明细表		1
		31216	312	核心企业应付账款明细表		1
	 * */
	
	public final static String reports31201      ="服务商关联统计表";
	public final static String reports31202      ="服务商客户额度报表";
	public final static String reports31203      ="服务商客户统计表";
	public final static String reports31204      ="服务商买方还款明细表";
	public final static String reports31205      ="服务商卖方还款明细表";
	public final static String reports31206      ="服务商融资明细表";
	public final static String reports31207      ="服务商业务汇总表";
	public final static String reports31208      ="服务商应收账款明细表";
	public final static String reports31209      ="服务商逾期明细报表";
	public final static String reports31210      ="供应商待还款明细表";
	public final static String reports31211      ="供应商融资明细表";
	public final static String reports31212      ="供应商已还款明细表";
	public final static String reports31213      ="供应商应收账款明细表";
	public final static String reports31214      ="核心企业链属企业统计表";
	public final static String reports31215      ="核心企业已付账款明细表";
	public final static String reports31216      ="核心企业应付账款明细表";
	/*
		40101	401	消息已读		1
		40102	401	消息未读		1
	   */
	public final static Integer MESSAGE_RED      =40101;//消息系统未读
	public final static Integer MESSAGE_UNRED    =40102;//消息系统已读
	
	public final static Integer CONTRACT = 308; // 合同
	public final static Integer CONTRACT_BLXY = 30801; // 保理协议
	public final static Integer CONTRACT_YSZKGLTYS = 30802; // 应收账款管理同意书
	public final static Integer CONTRACT_YSZKZQZRTZS = 30803; // 应收账款债权转让通知书
	public final static Integer OFF_LINE_CONTRACT = 30804; // 应收账款债权转让通知书
	
}