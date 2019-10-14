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

import com.lj.base.core.util.StringUtils;
import com.lj.business.common.KeyConstant;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.friends.DeleteFriendsResult;
import com.lj.business.weixin.dto.ImFriendsInfoDto;
import com.lj.business.weixin.service.IImFriendsCallBackFacade;
import com.lj.business.weixin.service.IImFriendsInfoService;
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
public class DeleteFriendsResultService implements IService<DeleteFriendsResult> {

	private static Logger LOG = LoggerFactory.getLogger(DeleteFriendsResultService.class);

	@Resource
	private IImFriendsCallBackFacade imFriendsCallBackFacade;
	@Resource
	ServerManager serverManger;
	@Resource
	private IImFriendsInfoService friendsInfoService;
	@Resource
	RedisCache redisCache;

	@Override
	public void process(DeleteFriendsResult request, Message message, Channel channel) {
		LOG.debug("DeleteFriendsResultService.process ---- > request:{}", request);

		ImFriendsInfoDto dto = null;
		if (StringUtils.isNotEmpty(request.getCode())) {
			ImFriendsInfoDto imFriendsInfoDto = new ImFriendsInfoDto();
			imFriendsInfoDto.setCode(request.getCode());
			dto = friendsInfoService.findImFriendsInfo(imFriendsInfoDto);
		}
		if (request.isResult()) {
			imFriendsCallBackFacade.toDeleteFriendsCallBack(request.getCode());
			LOG.info("delete friends call back, success :{},", request);
		}
		/**
		 * 给该朋友圈导购和请求者回结果报文，不需要缓存，因为它下次上线，该朋友圈已删除 请求者可能是上级
		 */
		if (dto == null) {
			LOG.error("朋友圈不存在！");
		}
		if (StringUtils.isNotEmpty(dto.getMemberNoGm())) {
			serverManger.sendLoginMsgNoCache(dto.getMemberNoGm(), message);
		}
		String memberNoGm = redisCache.get(KeyConstant.DEL_FRIENDS_OPERATOR + request.getCode());
		if (StringUtils.isNotEmpty(memberNoGm) && !memberNoGm.equals(dto.getMemberNoGm())) {
			serverManger.sendLoginMsgNoCache(memberNoGm, message);
		}
		redisCache.del(KeyConstant.DEL_FRIENDS_OPERATOR + request.getCode());
	}

}
