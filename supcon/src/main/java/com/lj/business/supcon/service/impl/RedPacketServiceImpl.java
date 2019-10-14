/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.business.supcon.dto.redpacket.FindRedPacketMessage;
import com.lj.business.supcon.dto.redpacket.SendRedPacketMessage;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.redpacket.FindRedpacketRequest;
import com.lj.business.supcon.netty.message.redpacket.SendRedpacketRequest;
import com.lj.business.supcon.service.IRedPacketService;

/**
 * 
 * 类说明：微信红包服务接口实现
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月29日
 */
@Service
public class RedPacketServiceImpl implements IRedPacketService {

	private static Logger logger = LoggerFactory.getLogger(RedPacketServiceImpl.class);
	
	@Resource
	private ServerManager serverManager;

	@Override
	public void sendRedPacketMessage(SendRedPacketMessage sendRedPacketMessage) {
		logger.debug("sendRedPacketMessage(SendRedPacketMessage sendRedPacketMessage={}) - start", sendRedPacketMessage); 
		
		AssertUtils.notNull(sendRedPacketMessage);
		AssertUtils.notNullAndEmpty(sendRedPacketMessage.getRpCode(), "红包CODE不能为空");
		AssertUtils.notNullAndEmpty(sendRedPacketMessage.getType(), "红包类型不能为空");
		AssertUtils.notNullAndEmpty(sendRedPacketMessage.getNoWxShop(), "终端微信不能为空");
		AssertUtils.notNullAndEmpty(sendRedPacketMessage.getNoWx(), "客户微信不能为空");
		AssertUtils.notNull(sendRedPacketMessage.getAmt(), "红包金额不能为空");
		AssertUtils.notNullAndEmpty(sendRedPacketMessage.getDes(), "红包描述不能为空");
		
		// 生成发红包报文
		SendRedpacketRequest sendRedpacketRequest = new SendRedpacketRequest();
		BeanUtils.copyProperties(sendRedPacketMessage, sendRedpacketRequest);
		Message message = new Message(MessageCode.SendRedpacketRequest, sendRedpacketRequest.toJson());
		
		// 获取中控客户端连接
		IoSession session = ChannelUtils.getZkSessionByNoWx(sendRedPacketMessage.getNoWxShop());
		if(session != null && session.isLogin()) {
			serverManager.sendMessageAndCache(sendRedPacketMessage.getNoWxShop(), session.getChannel(), message);
			logger.info("已向中控客户端{}发送发红包报文消息：{}", sendRedPacketMessage.getNoWxShop(), message);
		} else {
			serverManager.cacheMsg(sendRedPacketMessage.getNoWxShop(), message);
			logger.info("中控客户端{}未登录，保存发红包报文消息：{}", sendRedPacketMessage.getNoWxShop(), message);
		}

		logger.debug("sendRedPacketMessage(SendRedPacketMessage) - end"); 
	}

	/**
	 * 
	 *
	 * 方法说明：发送查询红包报文
	 *
	 * @param findRedPacketMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年4月18日
	 *
	 */
	@Override
	public boolean sendFindRedpacketMessage(FindRedPacketMessage findRedPacketMessage) {
		logger.debug("sendFindRedpacketMessage(FindRedPacketMessage findRedPacketMessage={}) - start", findRedPacketMessage); 
		
		AssertUtils.notNull(findRedPacketMessage);
		AssertUtils.notNullAndEmpty(findRedPacketMessage.getRpCode(), "红包CODE不能为空");
		AssertUtils.notNullAndEmpty(findRedPacketMessage.getNoWxShop(), "终端微信不能为空");
		
		// 生成查询红包报文
		FindRedpacketRequest findRedpacketRequest = new FindRedpacketRequest();
		findRedpacketRequest.setRpCode(findRedPacketMessage.getRpCode());
		Message message = new Message(MessageCode.FindRedpacketRequest, findRedpacketRequest.toJson());
		
		boolean result = Boolean.TRUE;		// 发送结果
		
		// 获取中控客户端连接
		IoSession session = ChannelUtils.getZkSessionByNoWx(findRedPacketMessage.getNoWxShop());
		if(session != null && session.isLogin()) {
			serverManager.sendMessageAndCache(findRedPacketMessage.getNoWxShop(), session.getChannel(), message);
			logger.info("已向中控客户端{}发送查询红包报文消息：{}", findRedPacketMessage.getNoWxShop(), message);
		} else {
			result = Boolean.FALSE;
			logger.info("中控客户端{}未登录，发送查询红包报文消息失败：{}", findRedPacketMessage.getNoWxShop(), message);
		}

		logger.debug("sendFindRedpacketMessage(FindRedPacketMessage) - end - return value={}", result); 
		return result;
	}

}
