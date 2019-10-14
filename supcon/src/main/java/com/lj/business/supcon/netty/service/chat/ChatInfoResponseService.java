/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service.chat;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.StringUtils;
import com.lj.business.ai.service.IProblemService;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.enums.ConnectSource;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.chat.ChatInfoResponse;
import com.lj.business.weixin.dto.imchat.FindAutoAnswerChatInfo;
import com.lj.business.weixin.dto.imchat.FindAutoAnswerChatInfoReturn;
import com.lj.business.weixin.dto.imchat.FindImChatInfo;
import com.lj.business.weixin.dto.imchat.FindImChatInfoReturn;
import com.lj.business.weixin.dto.imchat.SendImChatInfo;
import com.lj.business.weixin.dto.imchat.UpdateImChatInfoReceived;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.service.IImChatInfoService;

import io.netty.channel.Channel;

/**
 * 
 * 类说明：发送聊天消息结果实现类
 * 
 * 
 * <p>
 * 详细描述：接收端只响应处理失败的聊天记录
 * 
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 * 
 *         CreateDate: 2017年11月1日
 */
@Service
public class ChatInfoResponseService implements IService<ChatInfoResponse> {

	private static Logger logger = LoggerFactory.getLogger(ChatInfoResponseService.class);

	@Resource
	private ServerManager serverManager;
	
	@Resource
	private IImChatInfoService imChatInfoService;
	
	

	@Override
	public void process(ChatInfoResponse request, Message message, Channel channel) {
		

		// 接收端处理成功
		if (request.isResult()) {
			return;
		}
		
		// 更新聊天记录状态
		IoSession session = ChannelUtils.getSession(channel);
		// 查询聊天记录
		FindImChatInfo findImChatInfo = new FindImChatInfo();
		findImChatInfo.setCode(request.getMsgId());
		FindImChatInfoReturn chatReturn = imChatInfoService.findImChatInfo(findImChatInfo);

		
		UpdateImChatInfoReceived updateImChatInfoReceived = new UpdateImChatInfoReceived();
		updateImChatInfoReceived.setMsgId(request.getMsgId());
		updateImChatInfoReceived.setSuccess(request.isResult());
		updateImChatInfoReceived.setErrorCode(request.getCode());
		updateImChatInfoReceived.setErrorMessage(request.getMessage());
		if(session.getConnectSource() == ConnectSource.GM) {
			updateImChatInfoReceived.setReceivedFlag(1);	// 导购接收
		} else {
			updateImChatInfoReceived.setReceivedFlag(2);	// 客户接收
		}
		imChatInfoService.updateImChatInfoReceived(updateImChatInfoReceived);

		
		

		// 向聊天记录发送端发送失败结果
		ConnectSource connectSource = session.getConnectSource(); 	// 连接来源
		IoSession sessionSend = null;		// 发送方session
		String sendLoingAccountNo = null;	// 发送方登录账号
		// 如果失败结果来源为导购,则需将结果发给中控，获取中控session
		if (ConnectSource.GM == connectSource) {
			sendLoingAccountNo = chatReturn.getNoWxGm();
			sessionSend = ChannelUtils.getZkSessionByNoWx(sendLoingAccountNo);
		}else{ // 如果失败结果来源为中控，则需将结果发给导购，获取导购session
			sendLoingAccountNo = chatReturn.getMemberNoGm();
			if(StringUtils.isNotEmpty(sendLoingAccountNo)) {
				sessionSend = ChannelUtils.getSession(sendLoingAccountNo);
			}
		}
		
		
		// 当发送方客户端登录时发送，未登录时当做离线报文消息缓存起来不发送
		if (sessionSend != null && sessionSend.isLogin()) {
			serverManager.sendMessageAndCache(sendLoingAccountNo, sessionSend.getChannel(), message);
			logger.info("已向发送端{}发送聊天记录失败结果：{}", sendLoingAccountNo, request.getMsgId());
		}else{
			if(StringUtils.isNotEmpty(sendLoingAccountNo)) {
				serverManager.cacheMsg(sendLoingAccountNo, message);
				logger.info("{}客户端未登录，保存为离线消息：{}", sendLoingAccountNo, message);
			}
		}
		
		
		
		
	}

}
