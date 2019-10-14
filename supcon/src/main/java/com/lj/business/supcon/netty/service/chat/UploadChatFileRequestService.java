/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 *
 */
package com.lj.business.supcon.netty.service.chat;

import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.chat.UploadChatFileRequest;
import com.lj.business.weixin.service.IImChatInfoService;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * 类说明：上传视频文件
 * CreateDate: 2018年8月3日
 */
@Service
public class UploadChatFileRequestService implements IService<UploadChatFileRequest> {

	private static Logger logger = LoggerFactory.getLogger(UploadChatFileRequestService.class);

	@Resource
	private ServerManager serverManager;

	@Resource
	private IImChatInfoService imChatInfoService;

	@Override
	public void process(UploadChatFileRequest request, Message message, Channel channel) {
		logger.info("上传文件请求：request={},message={},channel={}",request,message,channel);

		IoSession zkSession = ChannelUtils.getZkSessionByGm(channel);
		if(ChannelUtils.isLogin(zkSession)) {
			serverManager.sendMessageNoCache(zkSession.getChannel(), message);
			logger.info("已向中控客户端{}上传文件消息视频报文：{}", zkSession.getLoginAccountNo(), request.getMsgId());
		} else {
			logger.error("中控客户端未登录，不向其发送上传文件消息报文");
		}
	}

}
