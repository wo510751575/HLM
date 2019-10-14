
package com.lj.business.common;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

/**
 * 
 * 
 * 类说明：会员风控异常代码
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class RiskControlErrorCode {

	/**
	 * 本次充值金额大于实名用户最大可充值金额
	 */
	public static final String MORE_THAN_REAL_NAME_MAX_RECHARGE_AMOUNT = "more_than_real_name_max_recharge_amount";
	
	/**
	 * 本次充值金额大于非实名用户最大可充值金额
	 */
	public static final String MORE_THAN_UNREAL_NAME_MAX_RECHARGE_AMOUNT = "more_than_unreal_name_max_recharge_amount";
	
	/**
	 * 本次充值金额低于最小充值金额
	 */
	public static final String LESS_THAN_ALL_USER_MIN_RECHARGE_AMOUNT = "less_than_all_user_min_recharge_amount";
	
	/**
	 * 超过实名用户最大余额
	 */
	public static final String MORE_THAN_REAL_NAME_MAX_BALANCE = "more_than_real_name_max_balance";
	
	/**
	 * 超过非实名用户最大余额
	 */
	public static final String MORE_THAN_UNREAL_NAME_MAX_BALANCE = "more_than_unreal_name_max_balance";
	
	/**
	 * 本次支出金额大于实名用户最大可支出金额
	 */
	public static final String MORE_THAN_REAL_NAME_MAX_PAY_AMOUNT = "more_than_real_name_max_pay_amount";
	
	/**
	 * 本次支出金额大于非实名用户最大可支出金额
	 */
	public static final String MORE_THAN_UNREAL_NAME_MAX_PAY_AMOUNT = "more_than_unreal_name_max_pay_amount";
	
	/**
	 * 超过实名用户绑卡数量
	 */
	public static final String MORE_THAN_REAL_NAME_BIND_CARD_LIMIT = "more_than_real_name_bind_card_limit";
	
	/**
	 * 超过非实名用户绑卡数量
	 */
	public static final String MORE_THAN_UNREAL_NAME_BIND_CARD_LIMIT = "more_than_unreal_name_bind_card_limit";
	
	/**
	 * 当天累计支出超过限额
	 */
	public static final String MORE_THAN_DAY_LIMIT_PAY_AMOUNT = "more_than_day_limit_pay_amount";
	
	/**
	 * 小于最小提现金额
	 */
	public static final String LESS_THAN_WITHDRAW_MIN_AMOUNT = "less_than_withdraw_min_amount";
	
	/**
	 * 支付金额超过最大免密额度
	 */
	public static final String MORE_THAN_NO_PASSWORD_LIMIT = "more_than_no_password_limit";

}
