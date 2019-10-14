/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service;

import io.netty.channel.Channel;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.StringUtils;
import com.lj.business.member.dto.im.SyncPersonWxInfoRequest;
import com.lj.business.member.dto.im.SyncPersonWxInfoResponse;
import com.lj.business.member.service.IPersonMemberImService;
import com.lj.business.supcon.common.Constants;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.contacts.SyncWxInfoResponse;
import com.lj.cc.clientintf.LocalCacheSystemParamsFromCC;
import com.lj.distributecache.RedisCache;

/**
 * 
 * 
 * 类说明：客户微信基本信息同步返回
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
public class SyncWxInfoResponseService implements IService<SyncWxInfoResponse> {

	private static Logger logger = LoggerFactory.getLogger(SyncWxInfoResponseService.class);

	@Resource
	private ServerManager serverManager;
	
	@Resource
	private IPersonMemberImService personMemberImService;
	
	@Resource
	private RedisCache redisCache;
	
	@Resource
	private LocalCacheSystemParamsFromCC localCacheSystemParams;

	@Override
	public void process(SyncWxInfoResponse request, Message message, Channel channel) {
		SyncPersonWxInfoRequest syncPersonWxInfoRequest = new SyncPersonWxInfoRequest();
		syncPersonWxInfoRequest.setMemberNoGm(request.getMemberNoGm());
		syncPersonWxInfoRequest.setMemberNo(request.getMemberNo());
		syncPersonWxInfoRequest.setNoWx(request.getNoWx());
		syncPersonWxInfoRequest.setAlias(request.getAlias());
		syncPersonWxInfoRequest.setSex(request.getSex());
		syncPersonWxInfoRequest.setNickNameWx(request.getNickNameWx());
		syncPersonWxInfoRequest.setHeadAddress(request.getHeadAddress());
		SyncPersonWxInfoResponse syncPersonWxInfoResponse = personMemberImService.syncPersonWxInfo(syncPersonWxInfoRequest);
		
		Map<String, String> map = localCacheSystemParams.getSystemParamGroup("ms", "wxInfoSync");
		if(syncPersonWxInfoResponse.isChanged()) {	// 客户微信基本信息有更新
			logger.info("已同步客户微信基本信息: {}", request.getMemberNo());
			
			// 同步给导购客户端
			IoSession gmSession = ChannelUtils.getSession(request.getMemberNoGm());
			if(gmSession != null && gmSession.isLogin()) { // 导购客户端登录才异步同步给导购，未登录则通过导购重新登录IM即可同步
				request.setVersion(syncPersonWxInfoResponse.getVersion());
				message.setBody(request.toJson());
				serverManager.sendMessageAndCache(request.getMemberNoGm(), gmSession.getChannel(), message);
			} else {	// 保存为离线消息
				serverManager.cacheMsg(request.getMemberNoGm(), message);
			}

			long nextSyncBeginTime = System.currentTimeMillis() + Long.valueOf(StringUtils.toString(map.get("hasSyncInterval"), "600000"));	// 下次同步时间：10分钟后
			redisCache.hset(Constants.SYNC_WXINFO_CHCHE_KEY, request.getMemberNoGm() + request.getMemberNo(), String.valueOf(nextSyncBeginTime));
		} else {	// 没有更新
			logger.info("客户微信基本信息没有更新：{}", request.getMemberNo());

			long nextSyncBeginTime = System.currentTimeMillis() + Long.valueOf(StringUtils.toString(map.get("notSyncInterval"), "300000"));	// 下次同步时间：5分钟后
			redisCache.hset(Constants.SYNC_WXINFO_CHCHE_KEY, request.getMemberNoGm() + request.getMemberNo(), String.valueOf(nextSyncBeginTime));
		}
	}

}
