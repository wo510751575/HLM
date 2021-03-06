/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service.chat;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.chat.MemberCancelChatInfoResponse;
import com.lj.business.utils.BeanUtils;
import com.lj.business.weixin.dto.imchat.AddImChatInfo;
import com.lj.business.weixin.dto.imchat.FindImChatInfo;
import com.lj.business.weixin.dto.imchat.FindImChatInfoReturn;
import com.lj.business.weixin.dto.imchat.UpdateImChatInfo;
import com.lj.business.weixin.emus.ChatInfoType;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.emus.MessageStatus;
import com.lj.business.weixin.emus.ReadFlag;
import com.lj.business.weixin.service.IImChatInfoService;

import io.netty.channel.Channel;

/**
 * 
 * 类说明：撤回聊天记录结果处理
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年8月30日
 */
@Service
public class MemberCancelChatInfoResponseService implements IService<MemberCancelChatInfoResponse> {

	private static Logger logger = LoggerFactory.getLogger(MemberCancelChatInfoResponseService.class);
	
	@Resource
	private ServerManager serverManager;
	@Resource
	private IImChatInfoService imChatInfoService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Override
	public void process(MemberCancelChatInfoResponse request, Message message, Channel channel) {
		logger.debug("process(CancelChatInfoResponse={}",request);
		AssertUtils.notNullAndEmpty(request.getMsgId(), "消息编号不能为空");
		if(request.isResult()) {
			/**
			 * 修改原消息为已撤回
			 */
			FindImChatInfo findImChatInfo = new FindImChatInfo();
			findImChatInfo.setCode(request.getMsgId());
			FindImChatInfoReturn findImChatInfoReturn= imChatInfoService.findImChatInfo(findImChatInfo);
			if(null !=findImChatInfoReturn){
				if(!findImChatInfoReturn.getType().equals(ChatInfoType.SYSTEM.getCode()) 
								&& !"你撤回了一条消息".equals(findImChatInfoReturn.getContent())){
					UpdateImChatInfo updateImChatInfo = new UpdateImChatInfo();
					updateImChatInfo.setCode(findImChatInfoReturn.getCode());
					updateImChatInfo.setStatus(MessageStatus.CANCEL.getCode());
					imChatInfoService.updateImChatInfo(updateImChatInfo);
				}
				
				//通知导购消息撤回
				if(StringUtils.isNotEmpty(findImChatInfoReturn.getMemberNoGm())) {
					IoSession gmSession= ChannelUtils.getSession(findImChatInfoReturn.getMemberNoGm());
					if (ChannelUtils.isLogin(gmSession)) {
						request.setMemberNo(findImChatInfoReturn.getMemberNo());
						Message CancelChatInfoResponse = new Message(MessageCode.MemberCancelChatInfoResponse, message.getMsgId(), request.toJson());
						serverManager.sendMessageAndCache(findImChatInfoReturn.getMemberNoGm(),gmSession.getChannel(), CancelChatInfoResponse);
						logger.info("已向导购{}发送聊天记录：{}", findImChatInfoReturn.getMemberNoGm(), request.getMsgId());
					} else {
						logger.info("导购客户端{}未登录，不通过Netty向其发送聊天记录：{}",findImChatInfoReturn.getMemberNoGm(), request.getMsgId());
					}
				}
				
				/**
				 * 创建一条新的消息给IM看
				 */
				taskExecutor.execute(new Runnable() {
					
					@Override
					public void run() {
						try {
							AddImChatInfo addImChatInfo = new AddImChatInfo();
							BeanUtils.copyPropertiesNull(addImChatInfo, findImChatInfoReturn, true);
							addImChatInfo.setCode(GUID.generateCode());
							addImChatInfo.setType(ChatInfoType.SYSTEM.getCode());
							addImChatInfo.setMsgSource(MessageSource.SA.getCode());
							addImChatInfo.setThirdReadFlag(ReadFlag.YES.getCode());
							addImChatInfo.setContent(request.getMessage());
							imChatInfoService.addImChatInfo(addImChatInfo);
						} catch (Exception e) {
							logger.error("最新聊天动态错误 e={}",e);
						}
					}
				});
				logger.info("已撤回（删除）聊天记录{}", request.getMsgId());
			}else {
				logger.info("原聊天记录不存在{}", request.getMsgId());
			}
			
			
			
		} else {
			
			logger.debug("已撤回（删除）聊天记录{}失败", request.getMsgId());
		}
	}
	
}
