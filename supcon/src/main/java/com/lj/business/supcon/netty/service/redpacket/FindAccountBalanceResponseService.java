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

import com.lj.business.member.dto.shopterminal.UpdateShopTerminal;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.account.FindAccountBalanceResponse;

/**
 * 
 * 类说明：余额查询返回
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
public class FindAccountBalanceResponseService implements IService<FindAccountBalanceResponse> {

	private static Logger logger = LoggerFactory.getLogger(FindAccountBalanceResponseService.class);

	@Resource
	private IShopTerminalService shopTerminalService;
	
	@Override
	public void process(FindAccountBalanceResponse request, Message message, Channel channel) {
		UpdateShopTerminal updateShopTerminal = new UpdateShopTerminal();
		updateShopTerminal.setNoWx(request.getNoWx());
		updateShopTerminal.setWxBalance(request.getBalance());
		shopTerminalService.updateWxAccountBalance(updateShopTerminal);
		logger.info("已更新终端微信账户余额：{}", request);
	}

}
