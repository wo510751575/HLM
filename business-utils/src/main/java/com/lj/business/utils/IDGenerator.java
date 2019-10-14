package com.lj.business.utils;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */

import com.lj.base.core.util.GUID;

/**
 * 
 * 
 * 类说明：唯一ID号生成器
 *  
 * 
 * <p>
 * 详细描述：
 * 未来分布式系统，同一个系统的部署，IP最好是连续的
 * 这样在10台分布式服务器之内，ID唯一性能得到保证
 * @Company: 扬恩科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class IDGenerator {

	public static void main(String args []){
		for (int i = 0; i < 100; i++) {
			System.out.println(IDGenerator.generateDealerNo());
		}
		
	}
	/**
	 * 
	 *
	 * 方法说明：15位商户号生成
	 * 12位日期（去掉第一位）+ IP末1位 +校验码2位
	 * 2033年会出现日期第二位为0的情况
	 * @return
	 *
	 * @author 彭阳
	 */
	public static String generateDealerNo(){
		String id18ByGUID = GUID.generateByIP();
		return id18ByGUID.substring(3,15)+ id18ByGUID.substring(2,3)+id18ByGUID.substring(16);
	}
	/**
	 * 
	 *
	 * 方法说明：15位商户号生成
	 *IP末1位+12位日期（去掉第一位）+校验码2位
	 * 【未来分布式系统，同一个系统的部署，IP最好是连续的，
 	 * 这样在10台分布式服务器之内，ID唯一性能得到保证】
	 * @return
	 *
	 * @author 彭阳
	 */
	public static Long generateDigitalDealerNo(){
		return Long.valueOf(generateDealerNo());	
	}
 
	/***
	 *
	 * 方法说明：营销活动编号14位生成
	 * IP末1位+12位日期（去掉第一位）+校验码1位
	 * 【未来分布式系统，同一个系统的部署，IP最好是连续的，
 	 * 这样在10台分布式服务器之内，ID唯一性能得到保证】
	 * @return
	 *
	 * @author 彭阳  
	 * CreateDate: 2017-10-10
	 */
	public static Long generateMarketingActivityNo(){
		String id18ByGUID = GUID.generateByIP();
		return Long.valueOf(id18ByGUID.substring(2,15)+id18ByGUID.substring(17));
	} 
}
