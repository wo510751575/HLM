/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service.friends;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.friends.SendFriendsInfoResult;
import com.lj.business.weixin.dto.ImFriendsInfoCallBackDto;
import com.lj.business.weixin.emus.FriendsSendStatus;
import com.lj.business.weixin.service.IImFriendsCallBackFacade;
import com.lj.business.weixin.service.IImFriendsInfoService;
import com.lj.business.weixin.service.ISendFriendsJobService;

import io.netty.channel.Channel;

/**
 * 
 * 类说明：发送朋友圈结果返回
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
public class SendFriendsInfoResultService implements IService<SendFriendsInfoResult> {
	
	private static Logger LOG = LoggerFactory.getLogger(SendFriendsInfoResultService.class);
	
	@Resource
	IImFriendsCallBackFacade IImFriendsCallBackFacade;
	@Resource
	IImFriendsInfoService imFriendsInfoService;
	@Resource
	ISendFriendsJobService sendFriendsJobService;
	
	@Override
	public void process(SendFriendsInfoResult request, Message message, Channel channel) {
		LOG.debug("SendFriendsInfoResultService.process ---- > request:{} , message:{}",request,message);
		AssertUtils.notNullAndEmpty(request.getCode(),"朋友圈Code为空！");
		ImFriendsInfoCallBackDto imFriendsInfoCallBackDto = new ImFriendsInfoCallBackDto();
		imFriendsInfoCallBackDto.setFriendsCode(request.getCode());
		String remark = request.getMessage().length()>100?request.getMessage().substring(0,100):request.getMessage();
		imFriendsInfoCallBackDto.setRemark(remark);
		if(request.isResult()){
			imFriendsInfoCallBackDto.setFriendsId(request.getFriendsId());
			imFriendsInfoCallBackDto.setStatus(FriendsSendStatus.SEND_SUCCESS.getStatus().toString());
			LOG.warn("send Friends info  call back , success :{},",request);
		}else {
			imFriendsInfoCallBackDto.setStatus(FriendsSendStatus.SEND_FAIL.getStatus().toString());
			LOG.warn("send Friends info  call back , fail :{},",request);
		}
		IImFriendsCallBackFacade.toFriendsinfoCallBack(imFriendsInfoCallBackDto);
		LOG.debug(" friends add result is callbacl to weixin service  request:{}",request);
	}

}
