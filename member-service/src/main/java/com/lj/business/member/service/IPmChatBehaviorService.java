/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.member.service;

import com.lj.business.member.dto.UpdatePmChatBehavior;

/**
 * 
 * 类说明：客户导购聊天最新动态服务
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年12月29日
 */
public interface IPmChatBehaviorService {

	/**
	 * 
	 *
	 * 方法说明：更新最新聊天动态
	 *
	 * @param updatePmChatBehavior
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月29日
	 *
	 */
	public void updateBehavior(UpdatePmChatBehavior updatePmChatBehavior);

	/**
	 *@Desc 
	 *@param updatePmChatBehavior
	 *@return void
	 *@author 贾光朝
	 *@createDate 2019年5月7日下午4:01:03
	 */
	public void delete(UpdatePmChatBehavior updatePmChatBehavior);
}
