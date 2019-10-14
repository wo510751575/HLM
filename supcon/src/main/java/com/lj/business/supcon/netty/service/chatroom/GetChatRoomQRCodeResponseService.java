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

import com.lj.base.core.util.StringUtils;
import com.lj.business.member.dto.chatroom.FindChatRoom;
import com.lj.business.member.dto.chatroom.FindChatRoomReturn;
import com.lj.business.member.dto.chatroom.UpdateChatRoom;
import com.lj.business.member.service.IChatRoomMemberService;
import com.lj.business.member.service.IChatRoomService;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.chatroom.GetChatRoomQRCodeResponse;

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
public class GetChatRoomQRCodeResponseService implements IService<GetChatRoomQRCodeResponse> {

	private static Logger logger = LoggerFactory.getLogger(GetChatRoomQRCodeResponseService.class);
	
	@Resource
	private ServerManager serverManager;
	@Resource
	private IChatRoomMemberService chatRoomMemberService;
	@Resource
	private IChatRoomService chatRoomService;
	
	@Override
	public void process(GetChatRoomQRCodeResponse response, Message message, Channel channel) {
		logger.debug("请求中控获取群聊二维码响应 response = {}", response);
		
		//1.中控回报文
		IoSession zkSession = ChannelUtils.getSession(channel);
		FindChatRoom findChatRoom = new FindChatRoom();
		findChatRoom.setNoWxZk(zkSession.getNoWxGm());
		findChatRoom.setChatRoomName(response.getChatRoomName());
		FindChatRoomReturn chatRoomReturn= chatRoomService.findChatRoomBySelective(findChatRoom);
		//2更新群聊表QRCode
		UpdateChatRoom updateChatRoom = new UpdateChatRoom();
		updateChatRoom.setCode(chatRoomReturn.getCode());
		if(StringUtils.isNotEmpty(response.getChatRoomRQCode())) {
			updateChatRoom.setQRCode(response.getChatRoomRQCode());
		}
		if(!chatRoomReturn.getRoomNickName().equals(response.getRoomNickName())) {
			updateChatRoom.setRoomNickName(response.getRoomNickName());
		}
		chatRoomService.updateChatRoom(updateChatRoom);
		//3.转发给认领的导购,从群聊信息中获取
		if(StringUtils.isNotEmpty(chatRoomReturn.getMemberNoGm())) {
			String memberNoGm =chatRoomReturn.getMemberNoGm();
			IoSession gmSession = ChannelUtils.getSession(memberNoGm);	// 获取导购手机对应的session
			// 当客户端登录时发送，未登录时当做离线记录不发送
			if (ChannelUtils.isLogin(gmSession)) {
				message.setBody(response.toJson());
				serverManager.sendMessageNoCache(gmSession.getChannel(), message);
				logger.info("已向导购{}发送聊天记录：{}", memberNoGm, message.getMsgId());
			}
		}
	}

}
