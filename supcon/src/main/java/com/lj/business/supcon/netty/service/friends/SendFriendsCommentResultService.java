/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service.friends;

import io.netty.channel.Channel;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.friends.SendFriendsCommentResult;
import com.lj.business.weixin.dto.ImCommentCallBackDto;
import com.lj.business.weixin.emus.FriendsSendStatus;
import com.lj.business.weixin.service.IImFriendsCallBackFacade;

/**
 * 
 * 类说明：发送朋友圈评论结果返回
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年12月21日
 */
@Service
public class SendFriendsCommentResultService implements IService<SendFriendsCommentResult> {
	
	
	private static Logger LOG = LoggerFactory.getLogger(SendFriendsCommentResultService.class);

	@Resource
	IImFriendsCallBackFacade friendsCallBackFacade;
	
	@Override
	public void process(SendFriendsCommentResult request, Message message, Channel channel) {
		LOG.debug("SendFriendsCommentResultService.process ---- > request:{} , message:{}",request,message);
		ImCommentCallBackDto imCommentCallBackDto = new ImCommentCallBackDto();
		imCommentCallBackDto.setCommentsCode(request.getCode());
		imCommentCallBackDto.setRemark(request.getMessage());
		if(request.isResult()){
			imCommentCallBackDto.setStatus(FriendsSendStatus.SEND_SUCCESS.getStatus().toString());
			LOG.warn("send comment info  call back , success :{},",request);
		}else {
			imCommentCallBackDto.setStatus(FriendsSendStatus.SEND_FAIL.getStatus().toString());
			LOG.warn("send comment info  call back , fail :{},",request);
		}
		friendsCallBackFacade.toCommentCallBack(imCommentCallBackDto);
		LOG.debug(" friends add result is callbacl to weixin service  request:{}",request);
	}

}
