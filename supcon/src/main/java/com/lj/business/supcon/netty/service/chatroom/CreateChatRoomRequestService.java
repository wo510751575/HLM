/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service.chatroom;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.business.member.service.IChatRoomMemberService;
import com.lj.business.supcon.dto.chatroom.CreateChatRoomMessage;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.chatroom.CreateChatRoomRequest;
import com.lj.business.supcon.service.IWxChatRoomService;

import io.netty.channel.Channel;

/**
 * 
 * 类说明：创建群处理
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 段志鹏
 *   
 * CreateDate: 2018年10月25日
 */
@Service
public class CreateChatRoomRequestService implements IService<CreateChatRoomRequest> {

	private static Logger logger = LoggerFactory.getLogger(CreateChatRoomRequestService.class);
	
	@Resource
	private ServerManager serverManager;
	@Resource
	private IChatRoomMemberService chatRoomMemberService;
	@Autowired
	private IWxChatRoomService wxChatRoomService;
	
	@Override
	public void process(CreateChatRoomRequest request, Message message, Channel channel) {
		logger.debug("CreateChatRoomResponse.process ---- > request:{}",request);
		IoSession session = ChannelUtils.getSession(channel);
		
		CreateChatRoomMessage responseMessage = new CreateChatRoomMessage();
		responseMessage.setNoWxZk(session.getNoWxGm());
		responseMessage.setRoomNickName(request.getRoomNickName());
		responseMessage.setUserNames(request.getUserNames());
		responseMessage.setMemberNoGm(request.getMemberNoGm());
		
		//给中控发送创建群聊通知
		wxChatRoomService.sendCreateChatRoom(responseMessage);
	}

}
