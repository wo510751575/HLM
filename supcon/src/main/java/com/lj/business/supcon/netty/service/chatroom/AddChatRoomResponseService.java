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

import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.dto.chatroom.AddChatRoomMember;
import com.lj.business.member.dto.chatroom.FindChatRoom;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberReturn;
import com.lj.business.member.dto.chatroom.FindChatRoomReturn;
import com.lj.business.member.dto.chatroom.UpdateChatRoomMember;
import com.lj.business.member.service.IChatRoomMemberService;
import com.lj.business.member.service.IChatRoomService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.supcon.dto.chatroom.SyncChatRoomMessage;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.chatroom.AddChatRoomResponse;
import com.lj.business.supcon.service.IWxChatRoomService;
import com.lj.business.weixin.emus.ChatRoomStatus;

import io.netty.channel.Channel;

/**
 * 
 * 类说明：添加群成员结果处理
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
public class AddChatRoomResponseService implements IService<AddChatRoomResponse> {

	private static Logger logger = LoggerFactory.getLogger(AddChatRoomResponseService.class);
	
	@Resource
	private ServerManager serverManager;

	@Autowired
	private IChatRoomService chatRoomService;
	@Autowired
	private IChatRoomMemberService chatRoomMemberService;
	@Autowired
	private IPersonMemberService personMemberService;
	@Autowired
	private IWxChatRoomService wxChatRoomService;
	
	@Override
	public void process(AddChatRoomResponse request, Message message, Channel channel) {
		logger.debug("AddChatRoomResponse.process ---- > request:{}",request);
		IoSession session = ChannelUtils.getSession(channel);// 会话
		
		if(request.isResult()){
			if(StringUtils.isNotEmpty(request.getChatRoomName()) && StringUtils.isNotEmpty(request.getUserNames())){
				//获取群信息
				FindChatRoom findChatRoom = new FindChatRoom();
				findChatRoom.setNoWxZk(session.getNoWxGm());
				findChatRoom.setChatRoomName(request.getChatRoomName());
				FindChatRoomReturn chatRoomReturn= chatRoomService.findChatRoomBySelective(findChatRoom);
				
				String[] names = request.getUserNames().split(",");
				
				/**
				 * 群成员存在则修改为有效，否则新增
				 */
				/**
				 * 获取群成员最新备注名称
				 */
				for (String str : names) {
					FindChatRoomMemberReturn findChatRoomMemberReturn= chatRoomMemberService.findChatRoomMemberByNoWxSingle(chatRoomReturn.getCode(), str);
					if(findChatRoomMemberReturn==null){
						// 新增群成员记录
						AddChatRoomMember addChatRoomMember = new AddChatRoomMember();
						addChatRoomMember.setRoomCode(chatRoomReturn.getCode());
						addChatRoomMember.setNoWxZk(chatRoomReturn.getNoWxZk());
						addChatRoomMember.setChatRoomName(chatRoomReturn.getChatRoomName());
						addChatRoomMember.setStatus(ChatRoomStatus.Y.getCode());
						addChatRoomMember.setMerchantNo(chatRoomReturn.getMerchantNo());
						addChatRoomMember.setMerchantName(chatRoomReturn.getMerchantName());
						addChatRoomMember.setVersion(System.currentTimeMillis());
						FindPersonMemberReturn findPersonMemberBaseReturn= personMemberService.findPersonMemberByNoWxAndShopWx(str, chatRoomReturn.getNoWxZk());
						addChatRoomMember.setUserName(str);
						addChatRoomMember.setNickName(findPersonMemberBaseReturn.getMemberName());
						addChatRoomMember.setHeadUrl(findPersonMemberBaseReturn.getHeadAddress());
						addChatRoomMember.setMemberNo(findPersonMemberBaseReturn.getMemberNo());
						String memberName = StringUtils.isNotEmpty(findPersonMemberBaseReturn.getNickNameRemarkWx())?findPersonMemberBaseReturn.getNickNameRemarkWx():findPersonMemberBaseReturn.getMemberName();
						addChatRoomMember.setMemberName(memberName);
						chatRoomMemberService.addChatRoomMember(addChatRoomMember);
					}else {
						UpdateChatRoomMember updateChatRoomMember = new UpdateChatRoomMember();
						updateChatRoomMember.setCode(findChatRoomMemberReturn.getCode());
						updateChatRoomMember.setStatus(ChatRoomStatus.Y.getCode());
						chatRoomMemberService.updateChatRoomMember(updateChatRoomMember);
					}
					
				}
			}
		}else {
			// 向中控同步群信息
			SyncChatRoomMessage syncChatRoomMessage = new SyncChatRoomMessage();
			syncChatRoomMessage.setNoWxZk(session.getNoWxGm());
			syncChatRoomMessage.setChatRoomName(request.getChatRoomName());
			syncChatRoomMessage.setNowSync(true); // 立即同步
			wxChatRoomService.sendSyncChatRoom(syncChatRoomMessage);
		}
	}

}
