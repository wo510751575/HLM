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
import com.lj.business.common.KeyConstant;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.friends.DeleteFriends;
import com.lj.distributecache.RedisCache;

import io.netty.channel.Channel;

/**
 * 
 * 类说明：删除朋友圈结果返回处理
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 * 
 *         CreateDate: 2018年7月29日
 */
@Service
public class DeleteFriendsService implements IService<DeleteFriends> {

	private static Logger LOG = LoggerFactory.getLogger(DeleteFriendsService.class);

	@Resource
	ServerManager serverManger;
	@Resource
	RedisCache redisCache;

	@Override
	public void process(DeleteFriends request, Message message, Channel channel) {
		LOG.debug("DeleteFriendsResultService.process ---- > request:{}", request);
		AssertUtils.notNull(request);
		AssertUtils.notNullAndEmpty(request.getFriendsId(), "朋友圈ID不能为空");
		AssertUtils.notNullAndEmpty(request.getCode(), "朋友圈code不能为空");
		IoSession gmSession = ChannelUtils.getSession(channel);
		redisCache.set(KeyConstant.DEL_FRIENDS_OPERATOR + request.getCode(), gmSession.getMemberNoGm(), 60 * 10);
		serverManger.sendLoginMsgAndCache(gmSession.getNoWxGm(), message);
	}

}
