package com.shoufubang.model.util;


/**
 * 资金记录属性
 * @author Administrator
 *
 */
public class CapitalType {
	
	/**
	 * 资金交易类型：1-充值
	 */
	public static final byte CAPITAL_TRADE_RECHARGE = 1;
	
	/**
	 * 资金交易类型：3-提现
	 */
	public static final byte CAPITAL_TRADE_CASH = 3;
	
	/**
	 * 资金流转方向：1-收入 2-支出
	 */
	public static final byte CAPITAL_DERECTION_INCOME = 1;
	public static final byte CAPITAL_DERECTION_EXPENDITURE = 2;
}
