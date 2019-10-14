/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service;

import io.netty.channel.Channel;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.StringUtils;
import com.lj.business.member.dto.addfriends.AddAddFriends;
import com.lj.business.member.dto.addfriends.AddAddFriendsReturn;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.supcon.common.ErrorCode;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.BaseResponse;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.ResponseCode;
import com.lj.business.supcon.netty.message.ResponseUtils;
import com.lj.business.supcon.netty.message.contacts.AddFriendResult;
import com.lj.business.supcon.netty.message.contacts.ScanAddFriendRequest;
import com.lj.business.supcon.utils.AddQrCodeUtils;

/**
 * 
 * 类说明：导购扫码添加客户微信实现类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月24日
 */
@Service
public class ScanAddFriendService implements IService<ScanAddFriendRequest> {

	private static Logger logger = LoggerFactory.getLogger(ScanAddFriendService.class);
	
	@Resource
	private ServerManager serverManager;
	
	@Autowired
	private IAddFriendsService addFriendsService;

	@Override
	public void process(ScanAddFriendRequest request, Message message, Channel channel) {
		IoSession gmSession = ChannelUtils.getSession(channel);
		// 保存添加微信好友记录
		AddAddFriends addAddFriends = new AddAddFriends();
		addAddFriends.setMemberNoGm(request.getMemberNoGm());//导购编号
		addAddFriends.setNoWxGm(gmSession.getNoWxGm());
		addAddFriends.setWxAddType(1);// 导购扫码添加
		addAddFriends.setWxQrCode(request.getWxQrCode());
		addAddFriends.setNoWx(request.getNoWx());
		addAddFriends.setAlias(request.getAlias());
		addAddFriends.setNickNameWx(request.getNickNameWx());
		addAddFriends.setNickRemark(request.getNickRemark());
		addAddFriends.setHeadAddress(request.getHeadAddress());
		addAddFriends.setSex(request.getSex());
		addAddFriends.setMobile(request.getMobile());
		addAddFriends.setValidation(request.getValidation());
		addAddFriends.setLabelName(request.getLabelName());
		
		if(AddQrCodeUtils.isMobile(request.getWxQrCode())) {//手机号
			if(StringUtils.isEmpty(request.getMobile())) {
				addAddFriends.setMobile(request.getWxQrCode());
			}
			addAddFriends.setWxAddType(5);
		}else if(com.lj.base.core.util.StringUtils.isNumeric(request.getWxQrCode())) {//QQ号
			addAddFriends.setNoQq(request.getWxQrCode());
			addAddFriends.setWxAddType(7);
		}else if(request.getWxQrCode().indexOf("https://")==-1){//非扫码,则为微信号搜索
			addAddFriends.setNoWx(request.getWxQrCode());//微信号
			addAddFriends.setWxAddType(6);
		}
		AddAddFriendsReturn addAddFriendsReturn = null;
		try {
			addAddFriendsReturn = addFriendsService.addAddFriends(addAddFriends);
			logger.info("已保存添加微信好友记录：{}", addAddFriendsReturn);
		} catch(Exception e) {
			logger.error("保存添加微信好友记录异常", e);
			BaseResponse response = ResponseUtils.generateFailureResponse(e);
			Message resultMessage = new Message(MessageCode.AddFriendResult, response.toJson());
			serverManager.sendMessageAndCache(request.getMemberNoGm(), channel, resultMessage);
			logger.info("已向导购客户端发送添加客户微信失败消息：" + resultMessage);
			return;
		}
		
		// 请求中控客户端
		IoSession zkSession = ChannelUtils.getSession(gmSession.getNoWxGm());	// 获取对应中控客户端session
		if (zkSession == null || !zkSession.isLogin()) {	// 中控客户端未登录，返回添加失败信息
			logger.error("导购[{}]对应中控客户端未登录，请求添加客户微信失败", request.getMemberNoGm());
			AddFriendResult addFriendResult = new AddFriendResult();
			addFriendResult.setResult(Boolean.FALSE);	// 添加失败
			ResponseCode responseCode = ResponseUtils.getErrorResponseByBusinessCode(ErrorCode.ZKCLIENT_OFFLINE);
			addFriendResult.setCode(responseCode.getCode());
			addFriendResult.setMessage(responseCode.getMessage());
			addFriendResult.setMemberNoGm(request.getMemberNoGm());
			addFriendResult.setNoWxGm(gmSession.getNoWxGm());
			Message resultMessage = new Message(MessageCode.AddFriendResult, addFriendResult.toJson());
			serverManager.sendMessageAndCache(request.getMemberNoGm(), channel, resultMessage);
			logger.info("已向导购客户端发送添加客户微信失败消息：" + resultMessage);
		} else {	// 中控客户端已连接并登录，则发送信息
			request.setAddCode(addAddFriendsReturn.getCode());	// 设置服务器添加好友code
			message.setBody(request.toJson());
			serverManager.sendMessageAndCache(gmSession.getNoWxGm(), zkSession.getChannel(), message);
			logger.info("已向中控客户端发送请求添加客户微信消息：" + message);
		}
	}

}
