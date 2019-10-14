/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dto.chatroom.CreateChatRoom;
import com.lj.business.member.dto.chatroom.FindChatRoom;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberPage;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberPageReturn;
import com.lj.business.member.dto.chatroom.FindChatRoomReturn;
import com.lj.business.member.service.IChatRoomMemberService;
import com.lj.business.member.service.IChatRoomService;
import com.lj.business.supcon.common.Constants;
import com.lj.business.supcon.dto.chatroom.AddChatRoomMessage;
import com.lj.business.supcon.dto.chatroom.CreateChatRoomMessage;
import com.lj.business.supcon.dto.chatroom.DelChatRoomMessage;
import com.lj.business.supcon.dto.chatroom.DismissChatRoomMessage;
import com.lj.business.supcon.dto.chatroom.SyncChatRoomMessage;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.chatroom.AddChatRoomRequest;
import com.lj.business.supcon.netty.message.chatroom.CreateChatRoomRequest;
import com.lj.business.supcon.netty.message.chatroom.DelChatRoomRequest;
import com.lj.business.supcon.netty.message.chatroom.DismissChatRoomRequest;
import com.lj.business.supcon.netty.message.chatroom.SyncChatRoomRequest;
import com.lj.business.supcon.service.IWxChatRoomService;
import com.lj.business.weixin.emus.ChatRoomStatus;
import com.lj.distributecache.RedisCache;

/**
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 深圳市扬恩科技
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年9月10日
 */
@Service
public class WxChatRoomServiceImpl implements IWxChatRoomService {
	
	private static Logger logger = LoggerFactory.getLogger(WxChatRoomServiceImpl.class);
	
	@Resource
	private ServerManager serverManager;
	
	@Resource
	private RedisCache redisCache;
	@Autowired
	private IChatRoomService chatRoomService;
	@Autowired
	private IChatRoomMemberService chatRoomMemberService;

	/**
	 * 
	 *
	 * 方法说明：请求中控同步群信息
	 *
	 * @param syncChatRoomMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年9月10日
	 *
	 */
	@Override
	public boolean sendSyncChatRoom(SyncChatRoomMessage syncChatRoomMessage) {
		logger.debug("sendSyncChatRoom(SyncChatRoomMessage syncChatRoomMessage={}) - start", syncChatRoomMessage); 
		
		AssertUtils.notNull(syncChatRoomMessage);
		AssertUtils.notNullAndEmpty(syncChatRoomMessage.getNoWxZk(), "终端微信不能为空");
		
		boolean result = Boolean.TRUE;	// 结果
		
		IoSession zkSession = ChannelUtils.getZkSessionByNoWx(syncChatRoomMessage.getNoWxZk());
		if(ChannelUtils.isLogin(zkSession)) {
			if(!syncChatRoomMessage.isNowSync()) {
				// 同步频率控制
				String nextSyncBeginTimeString = redisCache.hget(Constants.SYNC_CHATROOM_CHCHE_KEY, syncChatRoomMessage.getNoWxZk() + syncChatRoomMessage.getChatRoomName());	// 下次同步起始时间
				// 已到下次同步时间
				if(StringUtils.isEmpty(nextSyncBeginTimeString) || new Long(nextSyncBeginTimeString) <= System.currentTimeMillis()) {
					this.syncChatRoom(syncChatRoomMessage, zkSession);
				} else {	// 没到同步时间
					logger.warn("{}下微信群{}下次同步时间{}未到", syncChatRoomMessage.getNoWxZk(), syncChatRoomMessage.getChatRoomName(), nextSyncBeginTimeString);
					result = Boolean.FALSE;
				}
			} else {
				this.syncChatRoom(syncChatRoomMessage, zkSession);
			}
		} else {
			logger.info("中控客户端{}未登录，不向其发送同步群信息报文：{}", syncChatRoomMessage.getNoWxZk(), syncChatRoomMessage.getChatRoomName());
			result = Boolean.FALSE;
		}
		
		logger.debug("sendSyncChatRoom(SyncChatRoomMessage) - end value={}", result); 
		return result;
	}

	/**
	 * 
	 *
	 * 方法说明：发送同步微信群报文
	 *
	 * @param syncChatRoomMessage
	 * @param zkSession
	 *
	 * @author 曾垂瑜 CreateDate: 2018年9月28日
	 *
	 */
	private void syncChatRoom(SyncChatRoomMessage syncChatRoomMessage, IoSession zkSession) {
		SyncChatRoomRequest request = new SyncChatRoomRequest();
		request.setChatRoomName(syncChatRoomMessage.getChatRoomName());
		Message message = new Message(MessageCode.SyncChatRoomRequest, request.toJson());
		serverManager.sendMessageNoCache(zkSession.getChannel(), message);
		logger.info("已向中控客户端{}发送同步群信息报文：{}", syncChatRoomMessage.getNoWxZk(), syncChatRoomMessage.getChatRoomName());
	}
	
	/**
	 *
	 * 方法说明：发送创建微信群报文
	 *
	 * @param createChatRoomMessage
	 * @param zkSession
	 *
	 * @author 段志鹏 CreateDate: 2018年10月25日
	 *
	 */
	private void createChatRoom(CreateChatRoomMessage createChatRoomMessage, IoSession zkSession) {
		CreateChatRoomRequest request = new CreateChatRoomRequest();
		request.setRoomNickName(createChatRoomMessage.getRoomNickName());
		request.setUserNames(createChatRoomMessage.getUserNames());
		request.setCode(createChatRoomMessage.getCode());
		Message message = new Message(MessageCode.CreateChatRoomRequest, request.toJson());
		serverManager.sendMessageNoCache(zkSession.getChannel(), message);
		logger.info("已向中控客户端{}发送创建群信息报文：{}", createChatRoomMessage.getNoWxZk(), createChatRoomMessage.getRoomNickName());
	}
	
	/**
	 *
	 * 方法说明：发送添加微信群成员报文
	 *
	 * @param addChatRoomMessage
	 * @param zkSession
	 *
	 * @author 段志鹏 CreateDate: 2018年10月26日
	 *
	 */
	private void addChatRoom(AddChatRoomMessage addChatRoomMessage, IoSession zkSession) {
		AddChatRoomRequest request = new AddChatRoomRequest();
		request.setChatRoomName(addChatRoomMessage.getChatRoomName());
		request.setUserNames(addChatRoomMessage.getUserNames());
		Message message = new Message(MessageCode.AddChatRoomRequest, request.toJson());
		serverManager.sendMessageNoCache(zkSession.getChannel(), message);
		logger.info("已向中控客户端{}发送添加微信群成员信息报文：{}", addChatRoomMessage.getNoWxZk(), addChatRoomMessage.getChatRoomName());
	}
	
	/**
	 *
	 * 方法说明：发送删除微信群成员报文
	 *
	 * @param addChatRoomMessage
	 * @param zkSession
	 *
	 * @author 段志鹏 CreateDate: 2018年10月26日
	 *
	 */
	private void DelChatRoom(DelChatRoomMessage delChatRoomMessage, IoSession zkSession) {
		DelChatRoomRequest request = new DelChatRoomRequest();
		request.setChatRoomName(delChatRoomMessage.getChatRoomName());
		request.setUserNames(delChatRoomMessage.getUserNames());
		Message message = new Message(MessageCode.DelChatRoomRequest, request.toJson());
		serverManager.sendMessageNoCache(zkSession.getChannel(), message);
		logger.info("已向中控客户端{}发送删除微信群成员信息报文：{}", delChatRoomMessage.getNoWxZk(), delChatRoomMessage.getChatRoomName());
	}
	
	/**
	 *
	 * 方法说明：解散微信群报文
	 *
	 * @param addChatRoomMessage
	 * @param zkSession
	 *
	 * @author 段志鹏 CreateDate: 2018年10月26日
	 *
	 */
	private void DismissChatRoom(DismissChatRoomMessage dismissChatRoomMessage, IoSession zkSession) {
		DismissChatRoomRequest request = new DismissChatRoomRequest();
		request.setChatRoomName(dismissChatRoomMessage.getChatRoomName());
		Message message = new Message(MessageCode.DismissChatRoomRequest, request.toJson());
		serverManager.sendMessageNoCache(zkSession.getChannel(), message);
		logger.info("已向中控客户端{}发送解散微信群信息报文：{}", dismissChatRoomMessage.getNoWxZk(), dismissChatRoomMessage.getChatRoomName());
	}

	@Override
	public boolean sendCreateChatRoom(CreateChatRoomMessage createChatRoomMessage) {
		logger.debug("sendCreateChatRoom(CreateChatRoomMessage createChatRoomMessage)={}) - start", createChatRoomMessage); 
		
		AssertUtils.notNull(createChatRoomMessage);
		AssertUtils.notNullAndEmpty(createChatRoomMessage.getNoWxZk(), "终端微信不能为空");
		AssertUtils.notNullAndEmpty(createChatRoomMessage.getRoomNickName(), "群昵称不能为空");
		AssertUtils.notNullAndEmpty(createChatRoomMessage.getUserNames(), "群成员不能为空");
		
		boolean result = Boolean.TRUE;	// 结果
		
		IoSession zkSession = ChannelUtils.getZkSessionByNoWx(createChatRoomMessage.getNoWxZk());
		if(ChannelUtils.isLogin(zkSession)) {
			/**
			 * 创建群聊，获取群code
			 */
			CreateChatRoom createChatRoom = new CreateChatRoom();
			createChatRoom.setPmCode(createChatRoomMessage.getPmCode());
			createChatRoom.setNoWxZk(createChatRoomMessage.getNoWxZk());
			createChatRoom.setRoomNickName(createChatRoomMessage.getRoomNickName());
			createChatRoom.setUserNames(createChatRoomMessage.getUserNames());
			createChatRoom.setMemberNoGm(createChatRoomMessage.getMemberNoGm());
			String code = chatRoomService.createChatRoom(createChatRoom);
			createChatRoomMessage.setCode(code);
			//发送创建群报文
			this.createChatRoom(createChatRoomMessage, zkSession);
		} else {
			logger.info("中控客户端{}未登录，不向其发送创建群信息报文：{}", createChatRoomMessage.getNoWxZk(), createChatRoomMessage.getRoomNickName());
			result = Boolean.FALSE;
		}
		logger.debug("sendCreateChatRoom(CreateChatRoomMessage) - end value={}", result); 
		return result;
	}

	@Override
	public boolean sendAddChatRoom(AddChatRoomMessage addChatRoomMessage) {
		logger.debug("sendAddChatRoom(AddChatRoomMessage addChatRoomMessage)={}) - start", addChatRoomMessage); 
		
		AssertUtils.notNull(addChatRoomMessage);
		AssertUtils.notNullAndEmpty(addChatRoomMessage.getNoWxZk(), "中控微信不能为空");
		AssertUtils.notNullAndEmpty(addChatRoomMessage.getChatRoomName(), "群名不能为空");
		AssertUtils.notNullAndEmpty(addChatRoomMessage.getUserNames(), "群成员不能为空");
		
		boolean result = Boolean.TRUE;	// 结果
		
		IoSession zkSession = ChannelUtils.getZkSessionByNoWx(addChatRoomMessage.getNoWxZk());
		if(ChannelUtils.isLogin(zkSession)) {
			/**
			 * 过滤已有群成员
			 */
			FindChatRoomMemberPage findChatRoomMemberPage = new FindChatRoomMemberPage();
			findChatRoomMemberPage.setChatRoomName(addChatRoomMessage.getChatRoomName());
			findChatRoomMemberPage.setNoWxZk(addChatRoomMessage.getNoWxZk());
			findChatRoomMemberPage.setStatus(ChatRoomStatus.Y.getCode());
			List<FindChatRoomMemberPageReturn> list= chatRoomMemberService.findChatRoomMemberList(findChatRoomMemberPage);
			for (FindChatRoomMemberPageReturn findChatRoomMemberPageReturn : list) {
				if(addChatRoomMessage.getUserNames().contains(findChatRoomMemberPageReturn.getUserName())){
					addChatRoomMessage.setUserNames(addChatRoomMessage.getUserNames().replace(findChatRoomMemberPageReturn.getUserName()+",", ""));
				}
			}
			/**
			 * 判断是否超过39人
			 */
			int addCount= addChatRoomMessage.getUserNames().split(",").length;
			if(addCount+list.size()>38){
				throw new TsfaServiceException(ErrorCode.CHAT_ROOM_CHAOYUAN_ERROR, "群成员总数不能超过39人！");
			}
			
			//发送增加群成员报文
			this.addChatRoom(addChatRoomMessage, zkSession);
		} else {
			logger.info("中控客户端{}未登录，不向其发送添加群成员信息报文：{}", addChatRoomMessage.getNoWxZk(), addChatRoomMessage.getChatRoomName());
			result = Boolean.FALSE;
		}
		logger.debug("sendAddChatRoom(AddChatRoomMessage) - end value={}", result); 
		return result;
	}

	@Override
	public boolean sendDelChatRoom(DelChatRoomMessage delChatRoomMessage) {
		logger.debug("sendDelChatRoom(DelChatRoomMessage delChatRoomMessage)={}) - start", delChatRoomMessage); 
		
		AssertUtils.notNull(delChatRoomMessage);
		AssertUtils.notNullAndEmpty(delChatRoomMessage.getNoWxZk(), "中控微信不能为空");
		AssertUtils.notNullAndEmpty(delChatRoomMessage.getChatRoomName(), "群名不能为空");
		AssertUtils.notNullAndEmpty(delChatRoomMessage.getUserNames(), "群成员不能为空");
		
		boolean result = Boolean.TRUE;	// 结果
		
		IoSession zkSession = ChannelUtils.getZkSessionByNoWx(delChatRoomMessage.getNoWxZk());
		if(ChannelUtils.isLogin(zkSession)) {
			/**
			 * 过滤群主
			 */
			FindChatRoom findChatRoom = new FindChatRoom();
			findChatRoom.setChatRoomName(delChatRoomMessage.getChatRoomName());
			FindChatRoomReturn findChatRoomReturn= chatRoomService.findChatRoomBySelective(findChatRoom);
			if(delChatRoomMessage.getUserNames().contains(findChatRoomReturn.getRoomOwner())){
				delChatRoomMessage.getUserNames().replace(findChatRoomReturn.getRoomOwner()+",", "");
			}
			
			//发送增加群成员报文
			this.DelChatRoom(delChatRoomMessage, zkSession);
		} else {
			logger.info("中控客户端{}未登录，不向其发送删除群成员信息报文：{}", delChatRoomMessage.getNoWxZk(), delChatRoomMessage.getChatRoomName());
			result = Boolean.FALSE;
		}
		logger.debug("sendDelChatRoom(DelChatRoomMessage) - end value={}", result); 
		return result;
	}

	@Override
	public boolean sendDismissChatRoom(DismissChatRoomMessage dismissChatRoomMessage) {
		logger.debug("sendDismissChatRoom(DismissChatRoomMessage dismissChatRoomMessage)={}) - start", dismissChatRoomMessage); 
		
		AssertUtils.notNull(dismissChatRoomMessage);
		AssertUtils.notNullAndEmpty(dismissChatRoomMessage.getNoWxZk(), "中控微信不能为空");
		AssertUtils.notNullAndEmpty(dismissChatRoomMessage.getChatRoomName(), "群名不能为空");
		
		boolean result = Boolean.TRUE;	// 结果
		
		IoSession zkSession = ChannelUtils.getZkSessionByNoWx(dismissChatRoomMessage.getNoWxZk());
		if(ChannelUtils.isLogin(zkSession)) {
			//发送解散群报文
			this.DismissChatRoom(dismissChatRoomMessage, zkSession);
		} else {
			logger.info("中控客户端{}未登录，不向其发送解散群信息报文：{}", dismissChatRoomMessage.getNoWxZk(), dismissChatRoomMessage.getChatRoomName());
			result = Boolean.FALSE;
		}
		logger.debug("sendDismissChatRoom(DismissChatRoomMessage) - end value={}", result); 
		return result;
	}
	
}
