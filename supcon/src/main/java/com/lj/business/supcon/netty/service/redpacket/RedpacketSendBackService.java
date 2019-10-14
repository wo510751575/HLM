/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service.redpacket;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.redpacket.RedpacketSendBack;
import com.lj.business.weixin.dto.WxRedpackDetailInfoDto;
import com.lj.business.weixin.emus.RedPackStatus;
import com.lj.business.weixin.service.IWxRedpackDetailInfoService;

import io.netty.channel.Channel;

/**
 * 
 * 类说明：红包超时已退回
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月31日
 */
@Service
public class RedpacketSendBackService implements IService<RedpacketSendBack> {

	private static Logger logger = LoggerFactory.getLogger(RedpacketSendBackService.class);
	
	@Resource
	IWxRedpackDetailInfoService wxRedpackDetailInfoService;
    @Resource
    ServerManager  serverManager;
    
	@Override
	public void process(RedpacketSendBack request, Message message, Channel channel) {
		logger.info("RedpacketSendBackService.process ------------> {} ",request);
		 WxRedpackDetailInfoDto wxRedpackDetailInfoDto  = wxRedpackDetailInfoService.findWxRedpackDetailinfoByOrderNo(request.getOrderNo());
		 AssertUtils.notNull(wxRedpackDetailInfoDto);
		 wxRedpackDetailInfoDto.setStatus(RedPackStatus.BACK.getStatus());
		 wxRedpackDetailInfoDto.setReceiveDate(new Date());
		 wxRedpackDetailInfoService.updateWxRedpackDetailInfo(wxRedpackDetailInfoDto);
		 
		//转发失败原因给导购
			if(wxRedpackDetailInfoDto.getMsgId() != null) {
				request.setOrderNo(wxRedpackDetailInfoDto.getMsgId());
				request.setMemberNo(wxRedpackDetailInfoDto.getMemberNo());
				request.setMsgId(wxRedpackDetailInfoDto.getMsgId());
				Message systemMessage = new  Message(MessageCode.RedpacketSendBack,GUID.generateByUUID(),request.toJson());

				IoSession session = ChannelUtils.getSession(wxRedpackDetailInfoDto.getMemberNoGm());
				if (ChannelUtils.isLogin(session)) {	// 导购端已连接并登录，则发送信息
					serverManager.sendMessageNoCache(session.getChannel(), systemMessage);	// 不缓存报文消息
					logger.info("已向中导购客户端发送错误：" + message);
				}
			}
		 logger.info("RedpacketSendBackService.process----------->{}",wxRedpackDetailInfoDto);
		
	}

}
