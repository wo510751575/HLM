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
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.ResponseCode;
import com.lj.business.supcon.netty.message.ResponseUtils;
import com.lj.business.supcon.netty.message.chat.FindChatImageRequest;
import com.lj.business.supcon.netty.message.chat.FindChatImageResponse;
import com.lj.business.weixin.constant.ErrorCode;
import com.lj.business.weixin.dto.imchat.FindImChatInfo;
import com.lj.business.weixin.dto.imchat.FindImChatInfoReturn;
import com.lj.business.weixin.dto.imchat.UpdateImChatInfo;
import com.lj.business.weixin.emus.ChatInfoType;
import com.lj.business.weixin.service.IImChatInfoService;

import io.netty.channel.Channel;

/**
 * 
 * 类说明：获取图片消息图片路径
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月30日
 */
@Service
public class FindChatImageRequestService implements IService<FindChatImageRequest> {

	private static Logger logger = LoggerFactory.getLogger(FindChatImageRequestService.class);
	
	@Resource
	private ServerManager serverManager;
	
	@Resource
	private IImChatInfoService imChatInfoService;
	
	@Override
	public void process(FindChatImageRequest request, Message message, Channel channel) {
		FindImChatInfo findImChatInfo = new FindImChatInfo();
		findImChatInfo.setCode(request.getMsgId());
		FindImChatInfoReturn findImChatInfoReturn = imChatInfoService.findImChatInfo(findImChatInfo);
		//如果已有大图直接返回
		if(StringUtils.isNotEmpty(findImChatInfoReturn.getBigImg())) {
			FindChatImageResponse response = new FindChatImageResponse();
			response.setMsgId(request.getMsgId());
			response.setType(request.getType());
			response.setImg(findImChatInfoReturn.getBigImg());
			Message responseMessage = new Message(MessageCode.FindChatImageResponse, response.toJson());
			serverManager.sendMessageNoCache(channel, responseMessage);
			return;
		}
		/**
		 * 导购端传入memberNoGm ，回写到消息上
		 */
		if(request.getFindFlag() == 1) {
			IoSession  session=ChannelUtils.getSession(channel);
			UpdateImChatInfo updateImChatInfo = new UpdateImChatInfo();
			updateImChatInfo.setCode(request.getMsgId());
			updateImChatInfo.setMemberNoGm(session.getMemberNoGm());
			imChatInfoService.updateImChatInfo(updateImChatInfo);
		}
		
		// 必须是图片类型的聊天记录
		if(!ChatInfoType.IMG.getCode().equals(findImChatInfoReturn.getType())) {
			logger.error("错误的消息类型，获取图片路径必须是图片消息类型：{}", request.getMsgId());
			ResponseCode responseCode = ResponseUtils.getErrorResponseByBusinessCode(ErrorCode.IM_CHAT_TYPE_INVAILD);
			FindChatImageResponse response = new FindChatImageResponse(responseCode);
			response.setMsgId(request.getMsgId());
			response.setType(request.getType());
			Message responseMessage = new Message(MessageCode.FindChatImageResponse, response.toJson());
			serverManager.sendMessageNoCache(channel, responseMessage);
			return;
		}
		
//		String content = findImChatInfoReturn.getContent();	// 图片类型消息扩展内容，格式为JSON串
//		if(StringUtils.isEmpty(content)) {	// 请求中控端上传图片
			this.sendToZk(request, message, channel, findImChatInfoReturn);
		/*} else {
			// 将扩展内容转换为MAP对象
			Map<String, String> contentMap = JsonUtils.mapFromJson(content);
			String imgKey = request.getType() == 2 ? Constants.CHAT_IMAGE_BIG_KEY: Constants.CHAT_IMAGE_MIDDLE_KEY;	// 图片类型关键字(默认中图)
			String imgAddr = contentMap.get(imgKey);
			
			// 图片已上传到服务器，则直接返回
			if(StringUtils.isNotEmpty(imgAddr)) {
				FindChatImageResponse response = new FindChatImageResponse();
				response.setMsgId(request.getMsgId());
				response.setType(request.getType());
				response.setImg(imgAddr);
				response.setContent(content);
				Message responseMessage = new Message(MessageCode.FindChatImageResponse, response.toJson());
				serverManager.sendMessageNoCache(channel, responseMessage);
				return;
			} else {	// 没有上传到服务器，则请求中控端上传图片
				this.sendToZk(request, message, channel, findImChatInfoReturn);
			}
		}*/
	}

	/**
	 * 
	 *
	 * 方法说明：发送请求报文给中控
	 *
	 * @param request
	 * @param message
	 * @param channel
	 * @param findImChatInfoReturn
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月30日
	 *
	 */
	private void sendToZk(FindChatImageRequest request, Message message, Channel channel, FindImChatInfoReturn findImChatInfoReturn) {
		IoSession zkSession = ChannelUtils.getZkSessionByNoWx(findImChatInfoReturn.getNoWxGm()); // 中控客户端会话
		if(zkSession != null && zkSession.isLogin()) {
			serverManager.sendMessageNoCache(zkSession.getChannel(), message);
			logger.debug("已向中控客户端{}发送上传图片报文{}", findImChatInfoReturn.getNoWxGm(), request.getMsgId());
		} else {
			logger.error("中控客户端未登录：{}", findImChatInfoReturn.getNoWxGm());
			ResponseCode responseCode = ResponseUtils.getErrorResponseByBusinessCode(com.lj.business.supcon.common.ErrorCode.ZKCLIENT_OFFLINE);
			FindChatImageResponse response = new FindChatImageResponse(responseCode);
			response.setMsgId(request.getMsgId());
			response.setType(request.getType());
			Message responseMessage = new Message(MessageCode.FindChatImageResponse, response.toJson());
			serverManager.sendMessageNoCache(channel, responseMessage);
		}
	}
}
