/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.account.FindAccountBalanceRequest;
import com.lj.business.supcon.service.IWxAccountService;

/**
 * 
 * 类说明：微信账户相关服务接口实现
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
@Service
public class WxAccountServiceImpl implements IWxAccountService {

	private static Logger logger = LoggerFactory.getLogger(WxAccountServiceImpl.class);
	
	@Resource
	private ServerManager serverManager;
	
	@Override
	public boolean sendFindWxAccountBalanceMessage(String noWx) {
		logger.debug("sendFindWxAccountBalanceMessage(String noWx={}) - start", noWx); 
		
		AssertUtils.notNullAndEmpty(noWx, "终端微信不能为空");
		
		IoSession session = ChannelUtils.getZkSessionByNoWx(noWx);
		boolean result = Boolean.FALSE;
		if(session != null && session.isLogin()) {
			FindAccountBalanceRequest request = new FindAccountBalanceRequest();
			request.setNoWx(noWx);
			Message message = new Message(MessageCode.FindAccountBalanceRequest, GUID.generateByUUID(), request.toJson());
			serverManager.sendMessageNoCache(session.getChannel(), message);
			logger.info("已向中控终端发送查询终端微信{}账户余额请求", noWx);
		}
		
		logger.debug("sendFindWxAccountBalanceMessage(String) - end - return={}", result); 
		return result;
	}



}
