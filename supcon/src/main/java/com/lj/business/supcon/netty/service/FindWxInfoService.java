/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service;

import io.netty.channel.Channel;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.business.supcon.common.ErrorCode;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.ResponseCode;
import com.lj.business.supcon.netty.message.ResponseUtils;
import com.lj.business.supcon.netty.message.contacts.FindWxInfoRequest;
import com.lj.business.supcon.netty.message.contacts.FindWxInfoReturn;

/**
 * 
 * 类说明：获取客户微信基本信息
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月26日
 */
@Service
public class FindWxInfoService implements IService<FindWxInfoRequest> {

	private static Logger logger = LoggerFactory.getLogger(FindWxInfoService.class);
	
	@Resource
	private ServerManager serverManager;

	@Override
	public void process(FindWxInfoRequest request, Message message, Channel channel) {
		AssertUtils.notNullAndEmpty(request.getMemberNoGm(),"导购号不能为空");
		// 请求中控客户端
		IoSession session = ChannelUtils.getSession(channel);	// 获取对应中控客户端session

		IoSession zkSession = ChannelUtils.getSession(session.getNoWxGm());
		if (ChannelUtils.isLogin(zkSession)) {	// 中控客户端已连接并登录，则发送信息
			serverManager.sendMessageNoCache(zkSession.getChannel(), message);	// 不缓存报文消息
			logger.info("已向中控客户端发送获取客户微信基本信息请求消息：" + message);
		} else {	// 中控客户端未登录，返回失败信息
			logger.error("导购[{}]对应中控客户端未登录，查询客户微信信息失败", request.getMemberNoGm());
			FindWxInfoReturn findWxInfoReturn = new FindWxInfoReturn();
			findWxInfoReturn.setResult(Boolean.FALSE);	// 失败
			ResponseCode responseCode = ResponseUtils.getErrorResponseByBusinessCode(ErrorCode.ZKCLIENT_OFFLINE);
			findWxInfoReturn.setCode(responseCode.getCode());
			findWxInfoReturn.setMessage(responseCode.getMessage());
			findWxInfoReturn.setMemberNoGm(request.getMemberNoGm());
			findWxInfoReturn.setWxQrCode(request.getWxQrCode());
			Message resultMessage = new Message(MessageCode.FindWxInfoReturn, findWxInfoReturn.toJson());
			serverManager.sendMessageNoCache(channel, resultMessage);
			logger.info("已向导购客户端发送获取客户微信基本信息失败消息：" + resultMessage);
		}
	}

}
