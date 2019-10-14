/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service.chat;

import com.lj.base.core.util.StringUtils;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.chat.UploadChatFileResponse;
import com.lj.business.weixin.dto.imchat.FindImChatInfo;
import com.lj.business.weixin.dto.imchat.FindImChatInfoReturn;
import com.lj.business.weixin.dto.imchat.UpdateImChatInfo;
import com.lj.business.weixin.service.IImChatInfoService;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 
 * 类说明：下载文件
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
public class UploadChatFileResponseService implements IService<UploadChatFileResponse> {

	private static Logger logger = LoggerFactory.getLogger(UploadChatFileResponseService.class);
	
	@Resource
	private ServerManager serverManager;
	
	@Resource
	private IImChatInfoService imChatInfoService;

	@Override
	public void process(UploadChatFileResponse response, Message message, Channel channel) {
		logger.info("上传文件响应：response={},message={},channel={}",response,message,channel);

		FindImChatInfo findImChatInfo = new FindImChatInfo();
		findImChatInfo.setCode(response.getMsgId());
		FindImChatInfoReturn findImChatInfoReturn = imChatInfoService.findImChatInfo(findImChatInfo);

		// 中控下载成功
		if(response.isResult() && StringUtils.isNotEmpty(response.getFileUrl())) {
			UpdateImChatInfo updateImChatInfo = new UpdateImChatInfo();
			updateImChatInfo.setCode(response.getMsgId());
			updateImChatInfo.setResourcesPath(response.getFileUrl());
			imChatInfoService.updateImChatInfo(updateImChatInfo);
		}

		// 导购端请求获取，且导购编号不为空，则将获取结果发给导购客户端
		if(response.getFindFlag() == 1 && StringUtils.isNotEmpty(findImChatInfoReturn.getMemberNoGm())) {
			IoSession gmSession = ChannelUtils.getSession(findImChatInfoReturn.getMemberNoGm());	// 导购客户端会话
			if(gmSession != null && gmSession.isLogin()) {
				serverManager.sendMessageNoCache(gmSession.getChannel(), message);
				logger.debug("已向导购客户端{}发送上传文件报文{}", findImChatInfoReturn.getMemberNoGm(), findImChatInfoReturn.getCode());
			} else {
				logger.error("导购客户端{}未登录，不发送上传文件结果{}", findImChatInfoReturn.getMemberNoGm(), findImChatInfoReturn.getCode());
			}
		}
	}

}
