package com.shoufubang.model.user;

import java.math.BigDecimal;

/**
 * 提现相关属性
 * @author 
 *
 */
public class CashType {
	
	/**
	 * 提现状态：0-不允许 1-允许
	 */
	public static final byte ALLOW_WITHDRAWAL_NO = 0;
	public static final byte ALLOW_WITHDRAWAL_YES = 1;
	
	/**
	 * 每日提现最高次数
	 */
    public static final Integer EVERYDAY_CASH_UPPER_NUMBER = 3;
    
	/**
	 * 最少提现金额
	 */
    public static final Double WITHDRAW_LOWWER_MONEY =(double) 100;
	
	/**
	 * 联动提现手续费
	 */
    public static final BigDecimal WITHDRAW_FEE_MONEY = new BigDecimal(0);
	
	/**
	 * 联动提现金额以分为单位
	 */
	public static final BigDecimal UMPAY_UNIT_TRANSFORMATION = new BigDecimal(100);
	
	/**
	 * 提现状态：0-处理中 1-成功 2-失败 3-冻结
	 */
	public static final byte STATUS_PENDING = 0;
	public static final byte STATUS_SUCC = 1;
	public static final byte STATUS_FAIL = 2;
	public static final byte STATUS_FROZEN = 3;
	

}
