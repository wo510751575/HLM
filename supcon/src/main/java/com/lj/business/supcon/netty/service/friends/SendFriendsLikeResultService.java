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
import com.lj.business.supcon.netty.message.friends.SendFriendsLikeResult;
import com.lj.business.weixin.dto.ImLikeCallBackDto;
import com.lj.business.weixin.emus.FriendsSendStatus;
import com.lj.business.weixin.service.IImFriendsCallBackFacade;

/**
 * 
 * 类说明：发送朋友圈点赞结果返回
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
public class SendFriendsLikeResultService implements IService<SendFriendsLikeResult> {
	
	private static Logger LOG = LoggerFactory.getLogger(SendFriendsLikeResultService.class);
	
	@Resource
	IImFriendsCallBackFacade friendsCallBackFacade;

	@Override
	public void process(SendFriendsLikeResult request, Message message, Channel channel) {
		LOG.debug("SendFriendsInfoResultService.process ---- > request:{} , message:{}",request,message);
		ImLikeCallBackDto imLikeCallBackDto = new ImLikeCallBackDto();
		imLikeCallBackDto.setLikesCode(request.getCode());
		imLikeCallBackDto.setRemark(request.getMessage());
		if(request.isResult()){
			imLikeCallBackDto.setStatus(FriendsSendStatus.SEND_SUCCESS.getStatus());
			LOG.warn("imLike call back , like success :{},",request);
		}else {
			imLikeCallBackDto.setStatus(FriendsSendStatus.SEND_FAIL.getStatus());
			LOG.warn("imLike call back , like fail :{},",request);
		}
		friendsCallBackFacade.toLikeCallBack(imLikeCallBackDto);
		LOG.debug(" friends add result is callbacl to weixin service  request:{}",request);
		
	}

}
