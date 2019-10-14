/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service.chat;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.chat.CancelChatInfoRequest;
import com.lj.business.weixin.service.IImChatInfoService;

import io.netty.channel.Channel;

/**
 * 导购撤回请求
 * @author wo510
 *
 */
@Service
public class CancelChatInfoRequestService implements IService<CancelChatInfoRequest> {

	private static Logger logger = LoggerFactory.getLogger(CancelChatInfoRequestService.class);
    
	@Autowired
	IImChatInfoService imChatInfoService;
	@Resource
	private ServerManager serverManager;
	
	@Override
	public void process(CancelChatInfoRequest request, Message message, Channel channel) {
		logger.info("process(CancelChatInfoRequest request={})",request);
		AssertUtils.notNullAndEmpty(request.getMsgId(), "消息编号不能为空");
		IoSession session = ChannelUtils.getZkSessionByGm(channel);
		
		// 当客户端登录时发送，未登录时当做离线记录不发送
		if (ChannelUtils.isLogin(session)) {
			Message CancelChatInfoRequest = new Message(MessageCode.CancelChatInfoRequest, message.getMsgId(), request.toJson());
			serverManager.sendMessageNoCache(session.getChannel(), CancelChatInfoRequest);
			logger.info("已向中控{}发送请求撤回消息报文：{}", session.getNoWxGm(), request.getMsgId());
		} else {
			logger.info("中控客户端{}未登录，不通过Netty向其发送聊天记录：{}", session.getNoWxGm(), request.getMsgId());
		}
	}
	
}
