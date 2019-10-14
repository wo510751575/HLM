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
import org.springframework.stereotype.Service;

import com.lj.base.core.util.StringUtils;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.supcon.common.Constants;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.enums.ConnectSource;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.common.CommonResponse;
import com.lj.business.weixin.dto.imchat.ReceivedOfflineChatInfo;
import com.lj.business.weixin.dto.imchat.UpdateImChatInfoReceived;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.distributecache.IDistributeCache;

/**
 * 
 * 类说明：通讯报文响应处理实现类
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
public class CommonService implements IService<CommonResponse> {

	private static Logger logger = LoggerFactory.getLogger(CommonService.class);
	
	@Resource
	private ServerManager serverManager;
	
	@Resource
	private IImChatInfoService imChatInfoService;
	
	@Resource 
	private IDistributeCache distributeCache;

	@Override
	public void process(CommonResponse request, Message message, Channel channel) {
		// 从缓存中获取未发送的消息记录
		/*Message businessMessage = serverManager.getMsg(message.getMsgId());
		if(businessMessage == null) {
			return;
		}*/
		
		switch (MessageCode.getMessageCodeByCode(request.getMessageCode())) {	
		case ChatInfoRequest:				// 3000-发送聊天记录
			IoSession session = ChannelUtils.getSession(channel);
			if(session != null && session.getConnectSource() != null) {
				UpdateImChatInfoReceived updateImChatInfoReceived = new UpdateImChatInfoReceived();
				updateImChatInfoReceived.setMsgId(message.getMsgId());
				if(session.getConnectSource() == ConnectSource.GM) {
					updateImChatInfoReceived.setReceivedFlag(1);	// 导购接收
				} else {
					updateImChatInfoReceived.setReceivedFlag(2);	// 客户接收
				}
				imChatInfoService.updateImChatInfoReceived(updateImChatInfoReceived);
			} else {
				logger.error("收到聊天记录[{}]的回执，但session链接已失效: {}", message.getMsgId(), session);
			}
			break;
		case OfflineChatInfo:				// 3010-推送离线聊天记录
			// 从缓存中获取并更新这些离线聊天记录为已发送
			String key = Constants.OFFLINE_CHAT_CACHE_PREFIX + message.getMsgId();
			String offlineChatInfoJson = distributeCache.get(key);
			if(StringUtils.isNotEmpty(offlineChatInfoJson)) {
				ReceivedOfflineChatInfo receivedOfflineChatInfo = (ReceivedOfflineChatInfo) JsonUtils.objectFromJson(offlineChatInfoJson, ReceivedOfflineChatInfo.class);
				imChatInfoService.receivedOfflineChatInfo(receivedOfflineChatInfo);
				distributeCache.del(key);
				logger.info("已更新离线聊天记录为已发送状态：{}", receivedOfflineChatInfo);
			} else {
				key = Constants.OFFLINE_CHAT_CACHE_PREFIX;
				logger.debug("兼容历史离线消息 key={}",key);
				offlineChatInfoJson = distributeCache.get(key);
				if(StringUtils.isNotEmpty(offlineChatInfoJson)) {
					ReceivedOfflineChatInfo receivedOfflineChatInfo = (ReceivedOfflineChatInfo) JsonUtils.objectFromJson(offlineChatInfoJson, ReceivedOfflineChatInfo.class);
					imChatInfoService.receivedOfflineChatInfo(receivedOfflineChatInfo);
					distributeCache.del(key);
					logger.info("已更新离线聊天记录为已发送状态：{}", receivedOfflineChatInfo);
				}else {
					logger.error("没有找到推送离线聊天记录的消息：" + message.getMsgId());
				}
			}
			break;
		/*case MemberClaimNotify:			// 2040-客户认领通知
			Message businessMessage = serverManager.getMsg(message.getMsgId());
			if(businessMessage != null) {
				// 通知消息内容实例
				MemberClaimNotify claimNotify = (MemberClaimNotify) JsonUtils.objectFromJson(businessMessage.getBody(), MemberClaimNotify.class);
				String claimKey = ContactsServiceImpl.getClaimGmKey(claimNotify.getMbrCode());
				String claimValue = distributeCache.get(claimKey);
				// 设置导购对应认领通知消息id为已发送（即导购已收到该认领通知消息）
				if(StringUtils.isNotEmpty(claimValue)) {
					Map<String, String> gm2MsgIdMap = JsonUtils.mapFromJson(claimValue);
					IoSession session = ChannelUtils.getSession(channel);
					String msgIdStr = gm2MsgIdMap.get(session.getMemberNoGm());
					if(StringUtils.isNotEmpty(msgIdStr)) {
						gm2MsgIdMap.put(session.getMemberNoGm(), msgIdStr.substring(0, 32) + "1");	// 已收到
						distributeCache.set(claimKey, JsonUtils.jsonFromObject(gm2MsgIdMap));		// 重新缓存
					}
				}
			}
			break;*/
			
		/*case ScanAddFriendRequest:		// 导购扫码加客户
			
			break;*/
			
		default:
			break;
		}
		
		// 删除消息记录，表示该记录客户端已收到
		serverManager.removeMsg(message.getMsgId(), MessageCode.getMessageCodeByCode(request.getMessageCode()));
	}

}
