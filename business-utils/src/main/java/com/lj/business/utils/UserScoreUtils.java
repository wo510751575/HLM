package com.lj.business.utils;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

/**
 * 
 * 
 * 类说明：用户积分公用类
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
public class UserScoreUtils {
	/***
	 * 消费金额累积积分的计算
	 * 
	 * @param consumeAmount
	 * @return
	 */
	public static long  consume2score(long consumeAmount){
		return consumeAmount/100;
	}
	/***
	 * 积分兑RMB
	 * @param score
	 * @return
	 */
	public static long score2rmb(long score){
		return score/5;
	}
}
