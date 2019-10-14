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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dto.addfriends.DistributionMember;
import com.lj.business.member.dto.addfriends.DistributionMemberReturn;
import com.lj.business.member.dto.addfriends.FindAddFriends;
import com.lj.business.member.dto.addfriends.FindAddFriendsReturn;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.ResponseCode;
import com.lj.business.supcon.netty.message.ResponseUtils;
import com.lj.business.supcon.netty.message.contacts.MemberClaimRequest;
import com.lj.business.supcon.netty.message.contacts.MemberClaimResponse;
import com.lj.distributecache.IDistributeCache;

/**
 * 
 * 类说明：客户认领服务
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月16日
 */
@Service
public class MemberClaimService implements IService<MemberClaimRequest> {

	private static Logger logger = LoggerFactory.getLogger(MemberClaimService.class);
	
	@Resource
	private ServerManager serverManager;
	
	@Autowired
	private IAddFriendsService addFriendsService;
	
	@Resource 
	private IDistributeCache distributeCache;

	@Override
	public void process(MemberClaimRequest request, Message message, Channel channel) {
		if(StringUtils.isEmpty(request.getMbrCode())) {
			return;
		}
		
		IoSession session = ChannelUtils.getSession(channel);
		
		MemberClaimResponse response = new MemberClaimResponse();
		response.setMbrCode(request.getMbrCode());
		
		// 认领客户
		if(request.isFlag()) {
			logger.info("导购[{}]确认认领该客户[{}]", session.getMemberNoGm(), request.getMbrCode());
			DistributionMember distributionMember = new DistributionMember();
			distributionMember.setMemberNoGm(session.getMemberNoGm());
			distributionMember.setCode(request.getMbrCode());
			try {
				DistributionMemberReturn distributionMemberReturn = addFriendsService.distributionMember(distributionMember);
				
				response.setResult(Boolean.TRUE);	// 处理成功
				response.setMemberNo(distributionMemberReturn.getMemberNo());
				response.setMemberNoGm(distributionMemberReturn.getMemberNo());
				response.setMemberNameGm(distributionMemberReturn.getMemberNameGm());
				response.setClaimTime(distributionMemberReturn.getClaimTime());
			} catch(TsfaServiceException e) {
				logger.error("客户认领失败", e);
				response.setResult(Boolean.FALSE);	// 处理失败
				ResponseCode responseCode = ResponseUtils.getErrorResponse(e);
				response.setCode(responseCode.getCode());
				response.setMessage(responseCode.getMessage());
				// 客户已被其他导购认领，则查询认领的导购信息
				if(ErrorCode.ADD_FRIENDS_HAND_DISTRIBUTION_ERROR.equals(e.getExceptionCode()) || ErrorCode.ADD_FRIENDS_DISTRIBUTION_ERROR.equals(e.getExceptionCode())) {
					FindAddFriends findAddFriends = new FindAddFriends();
					findAddFriends.setCode(request.getMbrCode());
					FindAddFriendsReturn findAddFriendsReturn = addFriendsService.findAddFriends(findAddFriends);
					response.setMemberNo(findAddFriendsReturn.getMemberNo());
					response.setMemberNoGm(findAddFriendsReturn.getMemberNoGm());
					response.setMemberNameGm(findAddFriendsReturn.getMemberNameGm());
					response.setClaimTime(findAddFriendsReturn.getAcceptTime().getTime());
				}
			} catch (Exception e) {
				logger.error("客户认领失败", e);
				response.setResult(Boolean.FALSE);	// 处理失败
				ResponseCode responseCode = ResponseUtils.getErrorResponse(e);
				response.setCode(responseCode.getCode());
				response.setMessage(responseCode.getMessage());
			}
		} /*else {	// 不认领，则删除缓存中发送认领通知消息的id
			logger.info("导购[{}]放弃认领该客户[{}]", session.getMemberNoGm(), request.getMbrCode());
			String claimKey = ContactsServiceImpl.getClaimGmKey(request.getMbrCode());
			String claimValue = distributeCache.get(claimKey);
			if(StringUtils.isNotEmpty(claimValue)) {
				Map<String, String> gm2MsgIdMap = JsonUtils.mapFromJson(claimValue);
				String msgIdStr = gm2MsgIdMap.get(session.getMemberNoGm());
				if(StringUtils.isNotEmpty(msgIdStr)) {
					// 删除映射
					gm2MsgIdMap.remove(msgIdStr);	
					if(gm2MsgIdMap.isEmpty()) {	 // 最后一个通知，则删除缓存
						distributeCache.del(claimKey);
					} else {	// 重新缓存
						distributeCache.set(claimKey, JsonUtils.jsonFromObject(gm2MsgIdMap));
					}
					// 删除缓存中的通知消息
					serverManager.removeMsg(msgIdStr.substring(0, 32), MessageCode.MemberClaimNotify);
				}
			}
		}*/
		
		// 响应处理结果
		Message responseMessage = new Message(MessageCode.MemberClaimResponse, response.toJson());
		serverManager.sendMessageNoCache(channel, responseMessage);	// 不缓存处理结果报文消息
		logger.info("已向{}导购客户端返回客户认领处理结果：{}", session.getMemberNoGm(), responseMessage);
	}
}
