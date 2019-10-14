/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.business.member.dto.im.PersonMemberWxInfo;
import com.lj.business.member.service.IPersonMemberImService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.supcon.common.Constants;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.contacts.SyncWxInfoRequest;
import com.lj.business.supcon.netty.message.contacts.SyncWxInfoResponse;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.distributecache.RedisCache;

import io.netty.channel.Channel;

/**
 * 
 * 
 * 类说明：客户微信基本信息同步请求
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年1月8日
 */
@Service
public class SyncWxInfoRequestService implements IService<SyncWxInfoRequest> {

	private static Logger logger = LoggerFactory.getLogger(SyncWxInfoRequestService.class);

	@Resource
	private ServerManager serverManager;
	
	@Resource
	private IPersonMemberImService personMemberImService;
	@Resource
	private IPersonMemberService personMemberService;
	
	@Resource
	private RedisCache redisCache;
	
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;
	
	@Override
	public void process(SyncWxInfoRequest request, Message message, Channel channel) {
		AssertUtils.notNullAndEmpty(request.getMemberNo(),"客户编号不能为空");
		IoSession zkSession = ChannelUtils.getZkSessionByGm(channel);
		IoSession gmSession = ChannelUtils.getSession(channel);
		request.setMemberNoGm(gmSession.getMemberNoGm());
		if(zkSession == null || !zkSession.isLogin()) {		// 中控登录才同步
			logger.error("中控客户端未登录，不能同步客户微信基本信息：{}", message.getBody());
			return;
		}
		
		// 同步频率控制
		String nextSyncBeginTimeString = redisCache.hget(Constants.SYNC_WXINFO_CHCHE_KEY, request.getMemberNoGm() + request.getMemberNo());	// 客户下次同步起始时间
		// 已到下次同步时间
		if(StringUtils.isEmpty(nextSyncBeginTimeString) || new Long(nextSyncBeginTimeString) <= System.currentTimeMillis()) {
			// 查询客户信息
			PersonMemberWxInfo personMemberWxInfo = personMemberImService.findPersonMemberWxInfo(request.getMemberNoGm(), request.getMemberNo(),zkSession.getNoWxGm());
			
			if(personMemberWxInfo.getVersion() <= request.getVersion()) {	// 如果客户端保存的版本号比服务器客户版本号小，则请求中控客户端查询
				logger.info("客户端保存的版本号比服务器客户版本号小，请求中控客户端同步消息zkSession.getChannel={}------------------------",zkSession.getChannel());
				serverManager.sendMessageNoCache(zkSession.getChannel(), message);
			} else {	// 服务器客户版本号比客户端保存的版本号大，则直接返回服务器客户微信基本信息
				SyncWxInfoResponse response = new SyncWxInfoResponse();
				response.setMemberNoGm(request.getMemberNoGm());
				response.setMemberNo(request.getMemberNo());
				response.setNoWx(personMemberWxInfo.getNoWx());
				response.setAlias(personMemberWxInfo.getAlias());
				response.setNickNameWx(personMemberWxInfo.getNickNameWx());
				response.setHeadAddress(personMemberWxInfo.getHeadAddress());
				response.setSex(personMemberWxInfo.getSex());
				response.setVersion(personMemberWxInfo.getVersion());
				Message responseMessage = new Message(MessageCode.SyncWxInfoResponse, GUID.generateByUUID(), response.toJson());
				serverManager.sendMessageAndCache(request.getMemberNoGm(), channel, responseMessage);
				
				Map<String, String> map = localCacheSystemParams.getSystemParamGroup("ms", "wxInfoSync");
				long nextSyncBeginTime = System.currentTimeMillis() + Long.valueOf(StringUtils.toString(map.get("hasSyncInterval"), "600000"));	// 下次同步时间：10分钟后
				redisCache.hset(Constants.SYNC_WXINFO_CHCHE_KEY, request.getMemberNoGm() + request.getMemberNo(), String.valueOf(nextSyncBeginTime));
			}
		} else {	// 没到同步时间
			logger.warn("客户{}下次同步时间{}未到", request.getMemberNo(), nextSyncBeginTimeString);
		}
	}

}
