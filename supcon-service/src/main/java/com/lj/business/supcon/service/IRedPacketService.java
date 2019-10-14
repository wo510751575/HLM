/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.service;

import com.lj.business.supcon.dto.redpacket.FindRedPacketMessage;
import com.lj.business.supcon.dto.redpacket.SendRedPacketMessage;

/**
 * 
 * 类说明：微信红包服务接口
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月29日
 */
public interface IRedPacketService {
	
	/**
	 * 
	 *
	 * 方法说明：发微信红包
	 *
	 * @param sendRedPacketMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月29日
	 *
	 */
	public void sendRedPacketMessage(SendRedPacketMessage sendRedPacketMessage);
	
	/**
	 * 
	 *
	 * 方法说明：发送查询红包报文
	 *
	 * @param findRedPacketMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月18日
	 * @return
	 *
	 */
	public boolean sendFindRedpacketMessage(FindRedPacketMessage findRedPacketMessage);

}
