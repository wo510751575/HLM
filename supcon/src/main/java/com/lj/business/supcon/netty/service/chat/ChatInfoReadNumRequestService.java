/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.chat.ChatInfoReadNumRequest;
import com.lj.business.weixin.dto.imchat.UpdateThirdHaveReadFromWeb;
import com.lj.business.weixin.service.IImChatInfoService;

import io.netty.channel.Channel;

/**
 * 聊天记录未读数服务
 * @author wo510
 *
 */
@Service
public class ChatInfoReadNumRequestService implements IService<ChatInfoReadNumRequest> {

	private static Logger logger = LoggerFactory.getLogger(ChatInfoReadNumRequestService.class);
    
	@Autowired
	IImChatInfoService imChatInfoService;
	
	@Override
	public void process(ChatInfoReadNumRequest request, Message message, Channel channel) {
		logger.info("process(ChatInfoReadNumRequest request={})",request);
		AssertUtils.notNullAndEmpty(request.getMemberNo(), "客户编号不能为空");
		IoSession session = ChannelUtils.getSession(channel);
		UpdateThirdHaveReadFromWeb updateThirdHaveReadFromWeb = new UpdateThirdHaveReadFromWeb();
		updateThirdHaveReadFromWeb.setMemberNo(request.getMemberNo());
		updateThirdHaveReadFromWeb.setNoWxGm(session.getNoWxGm());
		updateThirdHaveReadFromWeb.setMemberNoGm(session.getMemberNoGm());
		imChatInfoService.updateThirdHaveReadFromWeb(updateThirdHaveReadFromWeb);
	}
	
}
