/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.service;

/**
 * 
 * 类说明：微信账户相关服务接口
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月26日
 */
public interface IWxAccountService {

	/**
	 * 
	 *
	 * 方法说明：请求中控查询终端微信的账户余额
	 *
	 * @param noWx
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月26日
	 * @return 请求查询失败：中控终端未登录
	 *
	 */
	public boolean sendFindWxAccountBalanceMessage(String noWx);
	

}
