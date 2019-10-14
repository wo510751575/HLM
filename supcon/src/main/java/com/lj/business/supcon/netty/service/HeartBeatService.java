/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service;

import io.netty.channel.Channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.common.HeartBeatRequest;

/**
 * 
 * 类说明：心跳检测实现类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月13日
 */
@Service
public class HeartBeatService implements IService<HeartBeatRequest> {

	private static Logger logger = LoggerFactory.getLogger(HeartBeatService.class);

	@Override
	public void process(HeartBeatRequest request, Message message, Channel channel) {
		logger.info("Client heart beat: [{} - {}]", ChannelUtils.getIp(channel), channel.id());
		return;
	}

}
