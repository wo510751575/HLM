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

import com.lj.base.core.util.AssertUtils;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.chat.UploadChatVideoRequest;
import com.lj.business.weixin.dto.imchat.UpdateImChatInfo;
import com.lj.business.weixin.service.IImChatInfoService;

import io.netty.channel.Channel;

/**
 * 
 * 类说明：上传视频文件
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年8月3日
 */
@Service
public class UploadChatVideoRequestService implements IService<UploadChatVideoRequest> {

	private static Logger logger = LoggerFactory.getLogger(UploadChatVideoRequestService.class);
	
	@Resource
	private ServerManager serverManager;
	
	@Resource
	private IImChatInfoService imChatInfoService;

	@Override
	public void process(UploadChatVideoRequest request, Message message, Channel channel) {
		logger.info("上传视频文件请求：request={},message={},channel={}",request,message,channel);
		AssertUtils.notNullAndEmpty(request.getMsgId(),"消息ID不能为空");
		
		/**
		 * 导购端传入memberNoGm ，回写到消息上
		 */
		if(request.getFindFlag() == 1) {
			IoSession gmSession = ChannelUtils.getSession(channel);
			UpdateImChatInfo updateImChatInfo = new UpdateImChatInfo();
			updateImChatInfo.setCode(request.getMsgId());
			updateImChatInfo.setMemberNoGm(gmSession.getMemberNoGm());
			imChatInfoService.updateImChatInfo(updateImChatInfo);
		}
		
		IoSession zkSession = ChannelUtils.getZkSessionByGm(channel);
		if(ChannelUtils.isLogin(zkSession)) {
			serverManager.sendMessageNoCache(zkSession.getChannel(), message);
			logger.info("已向中控客户端{}发送上传视频消息视频报文：{}", zkSession.getLoginAccountNo(), request.getMsgId());
		} else {
			logger.error("中控客户端未登录，不向其发送上传视频消息视频报文");
		}
	}

}
