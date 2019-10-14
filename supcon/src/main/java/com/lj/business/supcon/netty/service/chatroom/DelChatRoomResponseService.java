/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service.chatroom;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.business.member.dto.chatroom.FindChatRoom;
import com.lj.business.member.dto.chatroom.FindChatRoomReturn;
import com.lj.business.member.dto.chatroom.UpdateChatRoomMember;
import com.lj.business.member.service.IChatRoomMemberService;
import com.lj.business.member.service.IChatRoomService;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.chatroom.DelChatRoomResponse;
import com.lj.business.weixin.emus.ChatRoomStatus;

import io.netty.channel.Channel;

/**
 * 
 * 类说明：删除群成员结果处理
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
public class DelChatRoomResponseService implements IService<DelChatRoomResponse> {

	private static Logger logger = LoggerFactory.getLogger(DelChatRoomResponseService.class);
	
	@Resource
	private ServerManager serverManager;

	@Resource
	private IChatRoomMemberService chatRoomMemberService;
	@Resource
	private IChatRoomService chatRoomService;
	
	@Override
	public void process(DelChatRoomResponse request, Message message, Channel channel) {
		logger.debug("DelChatRoomResponse.process ---- > request:{}",request);
		
		IoSession session = ChannelUtils.getSession(channel);
		
		if(request.isResult()){
			if(StringUtils.isNotEmpty(request.getChatRoomName()) && StringUtils.isNotEmpty(request.getUserNames())){
				
				FindChatRoom findChatRoom = new FindChatRoom();
				findChatRoom.setChatRoomName(request.getChatRoomName());
				findChatRoom.setNoWxZk(session.getNoWxGm());
				FindChatRoomReturn findChatRoomReturn= chatRoomService.findChatRoomBySelective(findChatRoom);
				if(findChatRoomReturn == null) {
					logger.error("群通讯录不存在!");
					return;
				}
				//删除群聊成员
				String[] userNames = request.getUserNames().split(",");
				UpdateChatRoomMember  record = new UpdateChatRoomMember(); 
				record.setRoomCode(findChatRoomReturn.getCode());
				record.setStatus(ChatRoomStatus.N.getCode());
				record.setUserNames(userNames);
				chatRoomMemberService.updateByCond(record);
			}
		}
	}

}
