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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.business.member.dto.terminalimstatus.UpdateTerminalImStatus;
import com.lj.business.member.service.ITerminalImStatusService;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.common.TerminalBatteryNotify;

/**
 * 
 * 类说明：终端电量通知服务
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月2日
 */
@Service
public class TerminalBatteryNotifyService implements IService<TerminalBatteryNotify> {

	private static Logger logger = LoggerFactory.getLogger(TerminalBatteryNotifyService.class);
	
	@Autowired
	private ITerminalImStatusService terminalImStatusService;

	@Override
	public void process(TerminalBatteryNotify request, Message message, Channel channel) {
		logger.info("更新终端IM状态信息- start");
		IoSession session = ChannelUtils.getSession(channel);
		UpdateTerminalImStatus updateTerminalImStatus = new UpdateTerminalImStatus();
		updateTerminalImStatus.setMemberNoGm(session.getMemberNoGm());
		updateTerminalImStatus.setTerminalType(session.getConnectSource().name());
		updateTerminalImStatus.setTerminalCode(session.getTerminalCode());
		updateTerminalImStatus.setBatteryLevel(request.getBatteryLevel());
		terminalImStatusService.updateTerminalImStatusByType(updateTerminalImStatus);
		logger.info("更新终端IM状态信息- end： updateTerminalImStatus= " + updateTerminalImStatus);
	}

}
