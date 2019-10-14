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
import org.springframework.stereotype.Service;

import com.lj.business.member.dto.chatroom.FindChatRoom;
import com.lj.business.member.dto.chatroom.FindChatRoomReturn;
import com.lj.business.member.service.IChatRoomMemberService;
import com.lj.business.member.service.IChatRoomService;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.chatroom.GetChatRoomQRCodeRequest;

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
public class GetChatRoomQRCodeRequestService implements IService<GetChatRoomQRCodeRequest> {

	private static Logger logger = LoggerFactory.getLogger(GetChatRoomQRCodeRequestService.class);
	
	@Resource
	private ServerManager serverManager;
	@Resource
	private IChatRoomMemberService chatRoomMemberService;
	@Resource
	private IChatRoomService chatRoomService;
	

	
	@Override
	public void process(GetChatRoomQRCodeRequest request, Message message, Channel channel) {
		logger.debug("GetChatRoomQRCodeRequest.process ---- > request:{}",request);
		IoSession session = ChannelUtils.getSession(channel);
		FindChatRoom findChatRoom = new FindChatRoom();
		findChatRoom.setNoWxZk(session.getNoWxGm());
		findChatRoom.setChatRoomName(request.getChatRoomName());
		FindChatRoomReturn chatRoomReturn= chatRoomService.findChatRoomBySelective(findChatRoom);
		
		IoSession zkSession = ChannelUtils.getZkSessionByNoWx(chatRoomReturn.getNoWxZk());
		Message msg = new Message(MessageCode.GetChatRoomQRCodeRequest, request.toJson());
		serverManager.sendMessageNoCache(zkSession.getChannel(), msg);
	}

}
