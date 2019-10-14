/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service.redpacket;

import io.netty.channel.Channel;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.redpacket.FindRedpacketResponse;
import com.lj.business.weixin.dto.UpdateRedpackDetailByPoll;
import com.lj.business.weixin.service.IWxRedpackDetailInfoService;

/**
 * 
 * 
 * 类说明：查询红包返回
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年4月18日
 */
@Service
public class FindRedpacketResponseService implements IService<FindRedpacketResponse> {

	private static Logger logger = LoggerFactory.getLogger(FindRedpacketResponseService.class);
	
	@Resource
	IWxRedpackDetailInfoService wxRedpackDetailInfoService ;
	
	@Override
	public void process(FindRedpacketResponse request, Message message, Channel channel) {
		logger.info("查询红包结果返回：{}", request);
		try{
			if(request.isResult()){
				UpdateRedpackDetailByPoll updateRedpackDetailByPoll = new UpdateRedpackDetailByPoll();
				updateRedpackDetailByPoll.setCode(request.getRpCode());
				updateRedpackDetailByPoll.setRedPackId(request.getOrderNo());
				updateRedpackDetailByPoll.setStatus(request.getStatus());
				updateRedpackDetailByPoll.setFailCode(request.getFailCode());
				updateRedpackDetailByPoll.setFailMsg(request.getFailMsg());
				wxRedpackDetailInfoService.updateRedpackDetailByPoll(updateRedpackDetailByPoll);
			} else {
				logger.error("中控端返回查询红包{}失败，不通知红包后台服务处理", request.getRpCode());
			}
		}catch(Exception e){
			logger.error("通知微信处理红包失败:",e);
		}
		
		logger.info("查询红包返回处理完成：{}", request);

	}

}
