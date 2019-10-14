/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service;

import io.netty.channel.Channel;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.business.member.dto.shopterminal.TerminalSign;
import com.lj.business.member.dto.shopterminal.TerminalSignReturn;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.sign.SignRequest;
import com.lj.business.supcon.netty.message.sign.SignResponse;

/**
 * 
 * 类说明：中控客户端签到
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月26日
 */
@Service
public class ZkSignService implements IService<SignRequest> {

	private static Logger logger = LoggerFactory.getLogger(ZkSignService.class);
	
	@Resource
	private IShopTerminalService shopTerminalService;
	
	@Resource
	private ServerManager serverManager;

	@Override
	public void process(SignRequest request, Message message, Channel channel) {
		TerminalSign terminalSign = new TerminalSign();
//		terminalSign.setWorkKey(request.getToken());	DZP 2019-04-22 废弃，直接获取DB中的token
		IoSession session= ChannelUtils.getSession(channel);
		terminalSign.setNoWx(session.getNoWxGm());
		TerminalSignReturn terminalSignReturn = shopTerminalService.sign(terminalSign);
		logger.info("终端{}签到成功：{}", terminalSign.getNoWx(), terminalSignReturn);

		// 返回签到结果
		SignResponse signResponse = new SignResponse();
		signResponse.setEncrypt(terminalSignReturn.getEncrypt());
		signResponse.setTimestamp(terminalSignReturn.getTimestamp());
		signResponse.setToken(terminalSignReturn.getToken());
		Message responseMessage = new Message(MessageCode.SignResponse, signResponse.toJson());
		serverManager.sendMessageNoCache(channel, responseMessage);
	}

}
