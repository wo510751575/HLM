/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.business.member.dto.AddFriendsTaskDetailDto;
import com.lj.business.member.dto.AddFriendsTaskDto;
import com.lj.business.member.dto.addfriends.UpdateAddFriendStatus;
import com.lj.business.member.dto.addfriends.UpdateAddFriendStatusReturn;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.member.service.IAddFriendsTaskDetailService;
import com.lj.business.member.service.IAddFriendsTaskService;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.chat.ChatInfoRequest;
import com.lj.business.supcon.netty.message.contacts.AddFriendResult;
import com.lj.business.supcon.netty.message.contacts.AddFriendResultResponse;
import com.lj.business.weixin.dto.imchat.AddImChatInfo;
import com.lj.business.weixin.dto.imchat.AddImChatInfoReturn;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.service.IImChatInfoService;

import io.netty.channel.Channel;

/**
 * 
 * 类说明：添加客户微信好友结果处理实现类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月25日
 */
@Service
public class AddFriendResultService implements IService<AddFriendResult> {

	private static Logger logger = LoggerFactory.getLogger(AddFriendResultService.class);
	
	@Resource
	private ServerManager serverManager;
	
	@Autowired
	private IAddFriendsService addFriendsService;
	@Resource
	private IImChatInfoService imChatInfoService;
	@Resource
	private IAddFriendsTaskService addFriendsTaskService;
	@Autowired
	private IAddFriendsTaskDetailService addFriendsTaskDetailService;
	
	@Override
	public void process(AddFriendResult request, Message message, Channel channel) {
		IoSession session = ChannelUtils.getSession(channel);
	    String mobile = request.getMobile();//取原始数据，防止中途被改
		// 更新添加微信好友状态
		UpdateAddFriendStatus updateAddFriendStatus = new UpdateAddFriendStatus();
		updateAddFriendStatus.setCode(request.getAddCode());
//		updateAddFriendStatus.setShopNo(session.getAttr("shopNo").toString());	// 分店编号
		updateAddFriendStatus.setMemberNoGm(request.getMemberNoGm());
		updateAddFriendStatus.setNoWxGm(request.getNoWxGm());
		updateAddFriendStatus.setStatus(request.isResult());
		updateAddFriendStatus.setNoWx(request.getNoWx());
		updateAddFriendStatus.setAlias(request.getAlias());
		updateAddFriendStatus.setNickNameWx(request.getNickNameWx());
		updateAddFriendStatus.setNickNameRemarkWx(request.getNickNameRemarkWx());
		updateAddFriendStatus.setHeadAddress(request.getHeadAddress());
		updateAddFriendStatus.setSex(request.getSex());
		updateAddFriendStatus.setLongitude(request.getLongitude());
		updateAddFriendStatus.setLatitude(request.getLatitude());
		updateAddFriendStatus.setValidation(request.getValidation());
		updateAddFriendStatus.setMobile(request.getMobile());
		UpdateAddFriendStatusReturn updateAddFriendStatusReturn = addFriendsService.updateAddFriendsStatus(updateAddFriendStatus);
		logger.info("已更新添加客户微信结果：{}", updateAddFriendStatusReturn);
		
		// 向中控客户端返回添加好友处理成功
		AddFriendResultResponse addResponse = new AddFriendResultResponse();
		if(updateAddFriendStatusReturn != null) {
			addResponse.setMemberNoGm(updateAddFriendStatusReturn.getMemberNoGm());
			addResponse.setMemberNo(updateAddFriendStatusReturn.getMemberNo());
			addResponse.setVersion(updateAddFriendStatusReturn.getVersion());
		}
		addResponse.setNoWxGm(request.getNoWxGm());
		addResponse.setNoWx(request.getNoWx());
		addResponse.setAlias(request.getAlias());
		Message responseMessage = new Message(MessageCode.AddFriendResultResponse, addResponse.toJson());
		serverManager.sendMessageAndCache(request.getNoWxGm(), session.getChannel(), responseMessage);
		logger.info("已向中控客户端发送添加微信好友处理成功报文：{}", responseMessage);
		
		// 如果返回结果为空，则是没有添加好友记录，即是客户扫导购加好，而不是导购扫客户加好友，需要分配或认领才能创建客户
		if(updateAddFriendStatusReturn != null) {
			// 通知导购手机客户端添加好友结果
			request.setMemberNo(updateAddFriendStatusReturn.getMemberNo());
			request.setMemberName(updateAddFriendStatusReturn.getMemberName());
			request.setMobile(updateAddFriendStatusReturn.getMobile());
			request.setNickNameRemarkLocal(updateAddFriendStatusReturn.getNickNameRemarkLocal());
			request.setCreateTime(updateAddFriendStatusReturn.getVersion());	// 版本号即客户创建时间
			request.setVersion(updateAddFriendStatusReturn.getVersion());
			/**
			 * 客户分类相关信息
			 * DZP 2018-12-14
			 */
			request.setCodePm(updateAddFriendStatusReturn.getCodePm());
			request.setPmTypeCode(updateAddFriendStatusReturn.getPmTypeCode());
			request.setPmTypeName(updateAddFriendStatusReturn.getPmTypeName());
			
			message.setBody(request.toJson());
			if(StringUtils.isNotEmpty(updateAddFriendStatusReturn.getMemberNoGm())) {
				request.setMemberNoGm(updateAddFriendStatusReturn.getMemberNoGm());
			}
			if(StringUtils.isNotEmpty(request.getMemberNoGm())) {
				IoSession gmSession = ChannelUtils.getSession(request.getMemberNoGm());	// 获取导购手机对应的session
				if(gmSession == null || !gmSession.isLogin()) {	// 导购未登录、离线，则保存消息，等待下次登录时通知
					serverManager.cacheMsg(request.getMemberNoGm(), message);
					logger.info("{}导购客户端未登录，保存离线消息：{}", request.getMemberNoGm(),  message);
					
					// 添加好友成功，则向导购客户端发送一条通过验证的聊天记录：我通过了你的朋友验证请求，现在我们可以开始聊天了
					if(request.isResult()) {
						Message notifyMessage = this.buildChatInfo(request);
						serverManager.cacheMsg(request.getMemberNoGm(), notifyMessage);
						logger.info("{}导购客户端未登录，不向其发送好友验证通过聊天记录：{}", request.getMemberNoGm(), notifyMessage.getMsgId());
					}
				} else {	// 连接并登录，则通知导购手机客户端添加好友结果
					serverManager.sendMessageAndCache(request.getMemberNoGm(), gmSession.getChannel(), message);
					logger.info("已向{}导购客户端发送添加微信好友结果：{}", request.getMemberNoGm(), message);
					
					// 添加好友成功，则向导购客户端发送一条通过验证的聊天记录：我通过了你的朋友验证请求，现在我们可以开始聊天了
					if(request.isResult()) {
						Message notifyMessage = this.buildChatInfo(request);
						serverManager.sendMessageAndCache(request.getMemberNoGm(), gmSession.getChannel(), notifyMessage);
						logger.info("已向导购客户端{}发送好友验证通过聊天记录：{}", request.getMemberNoGm(), notifyMessage.getMsgId());
					}
				}
			}
			
		}
		
		//通过手机号加粉任务需要变更任务信息,加好友 类型(6 通讯录手机号添加好友)
		if(request.getType() != null && (request.getType() == 6 || request.getType().equals(6))) {
			logger.info("加粉任务成功处理 request={}",request);
			request.setMobile(mobile);
			try {
				if(StringUtils.isNotEmpty(request.getMobile())) {
					updateFriendsTask(request,message,channel);
				}
			} catch (Exception e) {
				logger.error("处理加粉回执失败e={}",e);
			}
		}
	}
	
	
	/**
	 * 更新加粉任务数据
	 */
	public void updateFriendsTask(AddFriendResult request,Message message, Channel channel){
		    IoSession session = ChannelUtils.getSession(channel);
			logger.info("中控客户端反馈通过通讯录加好好友情况：{},{}", request.getMobile(),new Date(request.getCreateTime()));
			
			AddFriendsTaskDetailDto addFriendsTaskDetailDto = new AddFriendsTaskDetailDto();
			addFriendsTaskDetailDto.setNoWxGm(session.getNoWxGm());
			addFriendsTaskDetailDto.setPhone(request.getMobile());
			AddFriendsTaskDetailDto friendsTaskDetailDto= addFriendsTaskDetailService.findByCond(addFriendsTaskDetailDto);
			if(null ==friendsTaskDetailDto) {
				logger.error("任务详情已删除！code={}",request.getCode());
				return;
			}
			AddFriendsTaskDetailDto updateDetailDto = new AddFriendsTaskDetailDto();
			updateDetailDto.setCode(friendsTaskDetailDto.getCode());
			updateDetailDto.setCallbackDate(new Date(request.getCreateTime()));
			updateDetailDto.setDetail("已成功");
			String noWx = StringUtils.isNotEmpty(request.getAlias())?request.getAlias():request.getNoWx();
			updateDetailDto.setNoWx(noWx);
			addFriendsTaskDetailService.updateAddFriendsTaskDetail(updateDetailDto);
			
			//主任务成功数+1
			AddFriendsTaskDto addFriendsTaskDto = new AddFriendsTaskDto();
			addFriendsTaskDto.setCode(friendsTaskDetailDto.getTaskCode());
			AddFriendsTaskDto taskDto= addFriendsTaskService.findAddFriendsTask(addFriendsTaskDto);
			if(null ==taskDto) {
				logger.error("主任务已删除！code={}",request.getCode());
				return;
			}
			AddFriendsTaskDto updateDto = new AddFriendsTaskDto();
			updateDto.setCode(taskDto.getCode());
			updateDto.setSuccessNum(taskDto.getSuccessNum()+1);
			addFriendsTaskService.updateAddFriendsTask(updateDto);
	}
	/**
	 * 
	 *
	 * 方法说明：添加好友成功,发送验证通过聊天记录
	 *
	 * @param request
	 * @param session
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月18日
	 *
	 */
	public Message buildChatInfo(AddFriendResult request) {
		// 保存聊天记录
		AddImChatInfo addImChatInfo=new AddImChatInfo();
		addImChatInfo.setCode(GUID.generateByUUID());
		addImChatInfo.setMemberNoGm(request.getMemberNoGm());
		addImChatInfo.setNoWxGm(request.getNoWxGm());
		addImChatInfo.setMemberNo(request.getMemberNo());
		addImChatInfo.setNoWx(request.getNoWx());
		addImChatInfo.setAlias(request.getAlias());
		addImChatInfo.setContent("我通过了你的朋友验证请求，现在我们可以开始聊天了");
		addImChatInfo.setType("1");
		addImChatInfo.setSenderFlag(SenderFlag.ZK.getCode());
		addImChatInfo.setMsgSource(MessageSource.ZK.getCode());
		AddImChatInfoReturn addImChatInfoReturn = imChatInfoService.addImChatInfo(addImChatInfo);
		logger.info("已保存好友验证通过聊天记录：" + addImChatInfo.getCode());
	
		// 往导购客户端发送聊天信息
		ChatInfoRequest chatRequest = new ChatInfoRequest();
		chatRequest.setMemberNoGm(addImChatInfo.getMemberNoGm());
		chatRequest.setNoWxGm(addImChatInfo.getNoWxGm());
		chatRequest.setMemberNo(addImChatInfo.getMemberNo());
		chatRequest.setNoWx(addImChatInfo.getNoWx());
		chatRequest.setAlias(addImChatInfo.getAlias());
		chatRequest.setSenderFlag(addImChatInfo.getSenderFlag());
		chatRequest.setType(addImChatInfo.getType());
		chatRequest.setContent(addImChatInfo.getContent());
		chatRequest.setCreateTime(addImChatInfoReturn.getCreateDate().getTime());
		return new Message(MessageCode.ChatInfoRequest, addImChatInfo.getCode(), chatRequest.toJson());
	}
	
	
	
	
	
}
