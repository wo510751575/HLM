/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service.chatroom;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.GUID;
import com.lj.business.member.dto.chatroom.DelChatRoom;
import com.lj.business.member.dto.chatroom.DelChatRoomMember;
import com.lj.business.member.dto.chatroom.FindChatRoom;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberPage;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberPageReturn;
import com.lj.business.member.dto.chatroom.FindChatRoomReturn;
import com.lj.business.member.dto.chatroom.UpdateChatRoom;
import com.lj.business.member.dto.chatroom.UpdateChatRoomMember;
import com.lj.business.member.service.IChatRoomMemberService;
import com.lj.business.member.service.IChatRoomService;
import com.lj.business.supcon.dto.chatroom.SyncChatRoomMessage;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.chatroom.CreateChatRoomResponse;
import com.lj.business.supcon.service.IWxChatRoomService;
import com.lj.business.weixin.dto.imchat.AddImChatInfo;
import com.lj.business.weixin.emus.ChatInfoType;
import com.lj.business.weixin.emus.ChatRoomStatus;
import com.lj.business.weixin.emus.ChatRoomType;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.emus.MessageStatus;
import com.lj.business.weixin.emus.ReadFlag;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.service.IImChatInfoService;

import io.netty.channel.Channel;

/**
 * 
 * 类说明：创建群结果处理
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
public class CreateChatRoomResponseService implements IService<CreateChatRoomResponse> {

	private static Logger logger = LoggerFactory.getLogger(CreateChatRoomResponseService.class);
	
	@Resource
	private ServerManager serverManager;
	@Autowired
	private IChatRoomService chatRoomService;
	@Resource
	private IChatRoomMemberService chatRoomMemberService;
	@Autowired
	private IWxChatRoomService wxChatRoomService;
	@Autowired
	private IImChatInfoService imChatInfoService;
	
	@Override
	public void process(CreateChatRoomResponse request, Message message, Channel channel) {
		logger.debug("CreateChatRoomResponse.process 收到中控创建群聊响应报文---- > request:{}",request);
		logger.debug("CreateChatRoomResponse.process 收到中控创建群聊响应报文---- > message:{}",message);
		IoSession session = ChannelUtils.getSession(channel);
		if(request.isResult()){
			if(StringUtils.isEmpty(request.getChatRoomName())){
				//删除本地群聊
				DelChatRoom delChatRoom = new DelChatRoom();
				delChatRoom.setCode(request.getCode());
				chatRoomService.delChatRoom(delChatRoom);
				
				DelChatRoomMember delChatRoomMember = new DelChatRoomMember();
				delChatRoomMember.setRoomCode(request.getCode());
				chatRoomMemberService.delChatRoomMemberByCond(delChatRoomMember);
			}else{
				//更新群聊名称,头像
				UpdateChatRoom updateChatRoom = new UpdateChatRoom();
				updateChatRoom.setCode(request.getCode());
				updateChatRoom.setChatRoomName(request.getChatRoomName());
				updateChatRoom.setHeadUrl(request.getHeadUrl());
				updateChatRoom.setStatus(ChatRoomStatus.Y.getCode());
				chatRoomService.updateChatRoom(updateChatRoom);
				
				UpdateChatRoomMember updateChatRoomMember = new UpdateChatRoomMember();
				updateChatRoomMember.setRoomCode(request.getCode());
				updateChatRoomMember.setChatRoomName(request.getChatRoomName());
				updateChatRoomMember.setNoWxZk(session.getNoWxGm());
				updateChatRoomMember.setStatus(ChatRoomStatus.Y.getCode());
				chatRoomMemberService.updateChatRoomMemberByRoomCode(updateChatRoomMember);
				
				//获取群信息
				FindChatRoom findChatRoom = new FindChatRoom();
				findChatRoom.setCode(request.getCode());
				FindChatRoomReturn chatRoomReturn= chatRoomService.findChatRoomBySelective(findChatRoom);
				// 向中控同步群信息
				SyncChatRoomMessage syncChatRoomMessage = new SyncChatRoomMessage();
				syncChatRoomMessage.setNoWxZk(chatRoomReturn.getNoWxZk());
				syncChatRoomMessage.setChatRoomName(request.getChatRoomName());
				syncChatRoomMessage.setNowSync(false); // 不即可同步
				wxChatRoomService.sendSyncChatRoom(syncChatRoomMessage);

				//添加最新聊天动态，让群聊置顶
				try {
					//发送一条系统消息到群里
					String str = chatRoomMemberService.getNickNameByRoomCode(chatRoomReturn.getCode(), chatRoomReturn.getNoWxZk());
					addImChatInfo(chatRoomReturn.getNoWxZk(), chatRoomReturn.getCode(), "你邀请\""+str+"\""+"加入了群聊");
				} catch (Exception e) {
					logger.error("最新聊天动态错误 e={}",e);
				}
				//如果是导购端创建群给导购端发送消息
				FindChatRoomMemberPage findChatRoomMemberPage = new FindChatRoomMemberPage();
				findChatRoomMemberPage.setRoomCode(request.getCode());
				List<FindChatRoomMemberPageReturn> list= chatRoomMemberService.findChatRoomMemberList(findChatRoomMemberPage);
				if(null != list && list.size()>0){
					StringBuilder nickNames = new StringBuilder("");
					for (FindChatRoomMemberPageReturn findChatRoomMemberPageReturn : list) {
						if(!findChatRoomMemberPageReturn.getUserName().equals(chatRoomReturn.getRoomOwner())) {
							nickNames =nickNames.append(findChatRoomMemberPageReturn.getNickName()).append(",");
						}
					}
					if(nickNames.toString().length()>0){
						nickNames.deleteCharAt(nickNames.length()-1);
					}
					if(StringUtils.isNotEmpty(chatRoomReturn.getMemberNoGm())){
						//发送结果通知到导购手机端
						logger.info("创建群聊成功-通知导购={}",chatRoomReturn.getMemberNoGm());
						
						CreateChatRoomResponse CreateChatRoomResponse = new CreateChatRoomResponse();
						CreateChatRoomResponse.setChatRoomName(request.getChatRoomName());
						CreateChatRoomResponse.setHeadUrl(request.getHeadUrl());
						CreateChatRoomResponse.setNickNames(nickNames.toString());
						CreateChatRoomResponse.setRoomNickName(chatRoomReturn.getRoomNickName());
						CreateChatRoomResponse.setCode(chatRoomReturn.getCode());
						CreateChatRoomResponse.setNoWxGm(chatRoomReturn.getNoWxZk());
						
						Message resultMessage = new Message(MessageCode.CreateChatRoomResponse, CreateChatRoomResponse.toJson());
						IoSession ioSession=  ChannelUtils.getSession(chatRoomReturn.getMemberNoGm());
						if(null !=ioSession){
							serverManager.sendMessageAndCache(chatRoomReturn.getMemberNoGm(), ioSession.getChannel(), resultMessage);
						}
					
						
					}
				}
			}
			
		}else{
			//创建群聊失败-通知导购
			if(StringUtils.isNotBlank(request.getCode())){
				//获取群信息
				FindChatRoom findChatRoom = new FindChatRoom();
				findChatRoom.setCode(request.getCode());
				FindChatRoomReturn chatRoomReturn= chatRoomService.findChatRoom(findChatRoom);
				if(StringUtils.isNotEmpty(chatRoomReturn.getMemberNoGm())){
					String memberNoGm = chatRoomReturn.getMemberNoGm();
					//删除本地群聊
					DelChatRoom delChatRoom = new DelChatRoom();
					delChatRoom.setCode(request.getCode());
					chatRoomService.delChatRoom(delChatRoom);
					
					DelChatRoomMember delChatRoomMember = new DelChatRoomMember();
					delChatRoomMember.setRoomCode(request.getCode());
					chatRoomMemberService.delChatRoomMemberByCond(delChatRoomMember);
					
					//发送结果通知到导购手机端
					logger.info("创建群聊失败-通知导购 ={}",memberNoGm);
					CreateChatRoomResponse CreateChatRoomResponse = new CreateChatRoomResponse();
					CreateChatRoomResponse.setMessage(request.getMessage());
					CreateChatRoomResponse.setResult(Boolean.FALSE);
					CreateChatRoomResponse.setRoomNickName(chatRoomReturn.getRoomNickName());
					CreateChatRoomResponse.setCode(chatRoomReturn.getCode());
					CreateChatRoomResponse.setNoWxGm(chatRoomReturn.getNoWxZk());
					Message resultMessage = new Message(MessageCode.CreateChatRoomResponse, CreateChatRoomResponse.toJson());
					IoSession ioSession=  ChannelUtils.getSession(memberNoGm);
					if(null !=ioSession){
						serverManager.sendMessageAndCache(memberNoGm, ioSession.getChannel(), resultMessage);
					}
					
				}
			}
		}
	}

	
	/**
	 * 添加聊天消息-不发送
	 * @param noWxGm
	 * @param memberNo
	 * @param content
	 */
	private void addImChatInfo(final String noWxGm, final String memberNo, String content) {
		try {
			//发送消息
			
			AddImChatInfo addImChatInfo = new AddImChatInfo();
			addImChatInfo.setCode(GUID.generateCode());
			addImChatInfo.setSenderFlag(SenderFlag.GM.getCode());
			addImChatInfo.setNoWxGm(noWxGm);
			addImChatInfo.setMemberNo(memberNo);
			addImChatInfo.setType(ChatInfoType.SYSTEM.getCode());	
			addImChatInfo.setContent(content);
			addImChatInfo.setMsgSource(MessageSource.SA.getCode());
			addImChatInfo.setChatroomType(ChatRoomType.ROOM.getCode());
			addImChatInfo.setChatroomNoWx(noWxGm);
			addImChatInfo.setStatus(MessageStatus.SUCCESS.getCode());
			addImChatInfo.setThirdReadFlag(ReadFlag.NO.getCode());
			logger.info("发送群聊建群系统消息：{}", addImChatInfo);
			imChatInfoService.addImChatInfo(addImChatInfo);//0 为非历史聊天记录
			
		} catch(Exception e) {
			logger.error("发送H5链接失败", e);
		}
	}
}
