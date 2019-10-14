/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service.chat;

import java.util.HashMap;
import java.util.Map;

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
import com.lj.business.supcon.netty.message.chat.FindChatImageResponse;
import com.lj.business.weixin.common.Constants;
import com.lj.business.weixin.dto.imchat.FindImChatInfo;
import com.lj.business.weixin.dto.imchat.FindImChatInfoReturn;
import com.lj.business.weixin.dto.imchat.UpdateImChatInfo;
import com.lj.business.weixin.service.IImChatInfoService;

import io.netty.channel.Channel;

/**
 * 
 * 类说明：返回图片消息图片路径
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
public class FindChatImageResponseService implements IService<FindChatImageResponse> {

	private static Logger logger = LoggerFactory.getLogger(FindChatImageResponseService.class);
	
	@Resource
	private ServerManager serverManager;
	
	@Resource
	private IImChatInfoService imChatInfoService;

	@Override
	public void process(FindChatImageResponse response, Message message, Channel channel) {
		FindImChatInfo findImChatInfo = new FindImChatInfo();
		findImChatInfo.setCode(response.getMsgId());
		FindImChatInfoReturn findImChatInfoReturn = imChatInfoService.findImChatInfo(findImChatInfo);
		// 中控上传图片成功
		if(response.isResult() && StringUtils.isNotEmpty(response.getImg())) {
			String imgKey = response.getType() == 2 ? Constants.CHAT_IMAGE_BIG_KEY: Constants.CHAT_IMAGE_MIDDLE_KEY;	// 图片类型关键字(默认中图)
			Map<String, String> contentMap = new HashMap<String, String>();
			this.updateContent(imgKey, contentMap, response);
			// 重新生成报文内容body
			message.setBody(response.toJson());
		}
		
		// 导购端请求获取图片，且导购编号不为空，则将获取图片结果发给导购客户端
		if(response.getFindFlag() == 1 && StringUtils.isNotEmpty(findImChatInfoReturn.getMemberNoGm())) {
			IoSession gmSession = ChannelUtils.getSession(findImChatInfoReturn.getMemberNoGm());	// 导购客户端会话 
			if(gmSession != null && gmSession.isLogin()) {
				serverManager.sendMessageNoCache(gmSession.getChannel(), message);
				logger.debug("已向导购客户端{}发送上传图片报文{}", findImChatInfoReturn.getMemberNoGm(), findImChatInfoReturn.getCode());
			} else {
				logger.error("导购客户端{}未登录，不发送获取图片结果{}", findImChatInfoReturn.getMemberNoGm(), findImChatInfoReturn.getCode());
			}
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：将图片地址更新到聊天记录中拓展字段content
	 *
	 * @param imgKey
	 * @param contentMap
	 * @param response
	 * @param findImChatInfoReturn
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月30日
	 *
	 */
	private void updateContent(String imgKey,Map<String, String> contentMap, FindChatImageResponse response) {
		// 更新扩展字段content
		UpdateImChatInfo updateImChatInfo = new UpdateImChatInfo();
		updateImChatInfo.setCode(response.getMsgId());
//		updateImChatInfo.setContent(JsonUtils.jsonFromObject(contentMap));
		//增加大图入库
		boolean updateFlag = false;
		if(Constants.CHAT_IMAGE_BIG_KEY.equals(imgKey)) {
			updateImChatInfo.setBigImg(response.getImg());
			updateFlag=true;
		}
		if(Constants.CHAT_IMAGE_MIDDLE_KEY.equals(imgKey)) {
			updateImChatInfo.setMidImg(response.getImg());
			updateFlag=true;
		}
		if(updateFlag) {
			imChatInfoService.updateImChatInfo(updateImChatInfo);
		}
		// 设置图片扩展内容
		response.setContent(updateImChatInfo.getContent());
	}
}
