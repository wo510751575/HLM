
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
 * 类说明：会员风控参数名
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
public class RiskControlParam {
	
	/**
	 * 系统名称
	 */
	public static final String SystemAliasName = "ms";

	/**
	 * 风控参数分组名称
	 */
	public static final String GroupName = "RISK_CONTROL_PARAM"; 
	
	
	/**
	 * 登录密码连续输入错误次数（3次）
	 */
	public static final String LoginPasswordErrorTimes = "loginPasswordErrorTimes"; 
	
	/**
	 * 登录密码锁定时间（小时）
	 */
	public static final String LoginPasswordLockTime = "loginPasswordLockTime"; 
	
	/**
	 * 支付密码连续输入错误次数（3次）
	 */
	public static final String AcctPasswordErrorTimes = "acctPasswordErrorTimes"; 
	
	/**
	 * 支付密码锁定时间（小时）
	 */
	public static final String AcctPasswordLockTime = "acctPasswordLockTime"; 
	
	/**
	 * 同一手机，验证码连续3次输入错误后锁定
	 */
	public static final String ValidateCodeErrorTimes = "validateCodeErrorTimes"; 
	
	/**
	 * 同一手机，验证码锁定时间（小时）
	 */
	public static final String ValidateCodeLockTime = "validateCodeLockTime"; 
	
	
}
