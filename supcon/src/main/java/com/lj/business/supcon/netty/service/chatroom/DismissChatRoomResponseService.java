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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.business.member.dto.chatroom.DelChatRoomMember;
import com.lj.business.member.service.IChatRoomMemberService;
import com.lj.business.member.service.IChatRoomService;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.chatroom.DismissChatRoomResponse;

import io.netty.channel.Channel;

/**
 * 
 * 类说明：解散群聊结果处理
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
public class DismissChatRoomResponseService implements IService<DismissChatRoomResponse> {

	private static Logger logger = LoggerFactory.getLogger(DismissChatRoomResponseService.class);
	
	@Resource
	private ServerManager serverManager;

	@Autowired
	private IChatRoomService chatRoomService;
	@Resource
	private IChatRoomMemberService chatRoomMemberService;
	
	@Override
	public void process(DismissChatRoomResponse request, Message message, Channel channel) {
		logger.debug("DismissChatRoomResponse.process ---- > request:{}",request);
		IoSession ioSession = ChannelUtils.getSession(channel);
		if(request.isResult()){
			if(StringUtils.isNotEmpty(request.getChatRoomName())){
				//删除本地群聊
				chatRoomService.delChatRoomByRoomName(request.getChatRoomName(),ioSession.getNoWxGm());
				
				DelChatRoomMember delChatRoomMember = new DelChatRoomMember();
				delChatRoomMember.setChatRoomName(request.getChatRoomName());
				delChatRoomMember.setNoWxZk(ioSession.getNoWxGm());
				chatRoomMemberService.delChatRoomMemberByCond(delChatRoomMember);
			}
		}
	}

}
