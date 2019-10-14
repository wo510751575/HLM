/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.common.KeyConstant;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.supcon.common.Constants;
import com.lj.business.supcon.common.ErrorCode;
import com.lj.business.supcon.dto.contacts.AddFriendsByPhoneMessage;
import com.lj.business.supcon.dto.contacts.FindWxInfoRequestDto;
import com.lj.business.supcon.dto.contacts.ForcedLogoutMessage;
import com.lj.business.supcon.dto.contacts.MemberClaimMessage;
import com.lj.business.supcon.dto.contacts.NewFriendMessage;
import com.lj.business.supcon.dto.contacts.PushNotifyMessage;
import com.lj.business.supcon.dto.contacts.ScanAddFriendRequestDto;
import com.lj.business.supcon.dto.contacts.SyncShopWxFriendsMessage;
import com.lj.business.supcon.dto.contacts.SyncWxChatDataMessage;
import com.lj.business.supcon.dto.contacts.SyncWxInfoMessage;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.chat.ChatInfoRequest;
import com.lj.business.supcon.netty.message.common.NotifyMessage;
import com.lj.business.supcon.netty.message.contacts.AddFriendByPhoneRequest;
import com.lj.business.supcon.netty.message.contacts.AddFriendResult;
import com.lj.business.supcon.netty.message.contacts.ForcedLogout;
import com.lj.business.supcon.netty.message.contacts.MemberClaimNotify;
import com.lj.business.supcon.netty.message.contacts.MemberClaimedNotify;
import com.lj.business.supcon.netty.message.contacts.SyncShopWxFriendsRequest;
import com.lj.business.supcon.netty.message.contacts.SyncWxChatDataRequest;
import com.lj.business.supcon.netty.message.contacts.SyncWxInfoRequest;
import com.lj.business.supcon.netty.service.AddFriendResultService;
import com.lj.business.supcon.service.IContactsService;
import com.lj.business.supcon.utils.AddQrCodeUtils;
import com.lj.business.weixin.dto.imchat.AddImChatInfo;
import com.lj.business.weixin.emus.ChatInfoType;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.emus.MessageStatus;
import com.lj.business.weixin.emus.SenderFlag;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.distributecache.IDistributeCache;
import com.lj.distributecache.RedisCache;

/**
 * 
 * 类说明：IM通讯录好友相关接口实现
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月14日
 */
@Service
public class ContactsServiceImpl implements IContactsService {

	private static Logger logger = LoggerFactory.getLogger(ContactsServiceImpl.class);
	
	@Resource
	private ServerManager serverManager;
	
	@Resource 
	private IDistributeCache distributeCache;
	
	@Resource
	private RedisCache redisCache;
	
	@Resource 
	private AddFriendResultService addFriendResultService;
	
	@Resource
	private IGuidMemberService guidMemberService;

	@Resource
	private IPersonMemberBaseService personMemberBaseService;
	@Autowired
	private IPersonMemberService personMemberService;
	@Resource
	private IImChatInfoService imChatInfoService;
    
	/**
	 * 
	 * 方法说明：向客户端发送新的好友消息
	 *
	 * @param newFriendMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月14日
	 *
	 */
	@Override
	public void sendNewFriendMessage(NewFriendMessage newFriendMessage) {
		logger.debug("sendNewFriendMessage(NewFriendMessage newFriendMessage={}) - start", newFriendMessage); //$NON-NLS-1$

		AssertUtils.notNull(newFriendMessage);
		AssertUtils.notNullAndEmpty(newFriendMessage.getMemberNoGm(), "导购编号不能为空");
		AssertUtils.notNullAndEmpty(newFriendMessage.getNoWxGm(), "导购微信号不能为空");
		AssertUtils.notNullAndEmpty(newFriendMessage.getMemberNo(), "客户编号不能为空");
		AssertUtils.notNullAndEmpty(newFriendMessage.getMemberName(), "客户名称不能为空");
		AssertUtils.notNullAndEmpty(newFriendMessage.getNoWx(), "客户微信不能为空");
		
		try {
			// 1、先通知导购新增客户
			notifyNewFriend4Gm(newFriendMessage);
			
			// 2、再通知其他导购（同终端下除认领导购外）客户已认领
			notifyMemberClaim4Gms(newFriendMessage);
			
		} catch(Exception e) {
			logger.error("通知导购客户端新客户失败", e);
			throw new TsfaServiceException(ErrorCode.NOTIFY_GM_NEW_FRIEND_ERROR_STRING, "通知导购客户端新客户失败", e);
		}
		
		logger.debug("sendNewFriendMessage(NewFriendMessage) - end"); 
	}

	/**
	 *
	 * 方法说明：通知导购新增客户
	 *
	 * @param newFriendMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月06日
	 *
	 */
	private void notifyNewFriend4Gm(NewFriendMessage newFriendMessage) {
		AddFriendResult addFriendResult = new AddFriendResult();
		addFriendResult.setResult(Boolean.TRUE);
		addFriendResult.setMemberNoGm(newFriendMessage.getMemberNoGm());
		addFriendResult.setNoWxGm(newFriendMessage.getNoWxGm());
		addFriendResult.setNoWx(newFriendMessage.getNoWx());
		addFriendResult.setAlias(newFriendMessage.getAlias());
		addFriendResult.setNickNameWx(newFriendMessage.getNickNameWx());
		addFriendResult.setNickNameRemarkWx(newFriendMessage.getNickNameRemarkWx());
		addFriendResult.setNickNameRemarkLocal(newFriendMessage.getNickNameRemarkLocal());
		addFriendResult.setHeadAddress(newFriendMessage.getHeadAddress());
		addFriendResult.setSex(newFriendMessage.getSex());
		addFriendResult.setLongitude(newFriendMessage.getLongitude());
		addFriendResult.setMemberNo(newFriendMessage.getMemberNo());
		addFriendResult.setMemberName(newFriendMessage.getMemberName());
		addFriendResult.setMobile(newFriendMessage.getMobile());
		addFriendResult.setCreateTime(newFriendMessage.getVersion());
		addFriendResult.setVersion(newFriendMessage.getVersion());
		/**
		 * 客户分类相关信息
		 * DZP 2018-12-14
		 */
		addFriendResult.setCodePm(newFriendMessage.getCodePm());
		addFriendResult.setPmTypeCode(newFriendMessage.getPmTypeCode());
		addFriendResult.setPmTypeName(newFriendMessage.getPmTypeName());
	//	Message message = new Message(MessageCode.AddFriendResult, addFriendResult.toJson());
		
		// 通知导购手机客户端新的好友
		IoSession ioSession = ChannelUtils.getSession(newFriendMessage.getMemberNoGm());	
		Message message = new  Message(MessageCode.AddFriendResult,GUID.generateByUUID(),addFriendResult.toJson());
		
		
		
		
		// 往导购客户端发送聊天信息
		ChatInfoRequest chatRequest = new ChatInfoRequest();
		chatRequest.setMemberNoGm(newFriendMessage.getMemberNoGm());
		chatRequest.setNoWxGm(newFriendMessage.getNoWxGm());
		chatRequest.setMemberNo(newFriendMessage.getMemberNo());
		chatRequest.setNoWx(newFriendMessage.getNoWx());
		chatRequest.setAlias(newFriendMessage.getAlias());
		chatRequest.setSenderFlag(SenderFlag.ZK.getCode());
		chatRequest.setType(ChatInfoType.SYSTEM.getCode());
		chatRequest.setContent("你的好友请求已通过，现在可以开始聊天了");
		chatRequest.setCreateTime(new Date().getTime());
		// 保存聊天记录
		AddImChatInfo addImChatInfo=new AddImChatInfo();
		addImChatInfo.setCode(GUID.generateByUUID());
		addImChatInfo.setMemberNoGm(newFriendMessage.getMemberNoGm());
		addImChatInfo.setNoWxGm(newFriendMessage.getNoWxGm());
		addImChatInfo.setMemberNo(newFriendMessage.getMemberNo());
		addImChatInfo.setNoWx(newFriendMessage.getNoWx());
		addImChatInfo.setAlias(newFriendMessage.getAlias());
		addImChatInfo.setContent("你的好友请求已通过，现在可以开始聊天了");
		addImChatInfo.setType(ChatInfoType.TEXT.getCode());
		addImChatInfo.setSenderFlag(SenderFlag.ZK.getCode());
		addImChatInfo.setMsgSource(MessageSource.ZK.getCode());
		addImChatInfo.setStatus(MessageStatus.SUCCESS.getCode());
		imChatInfoService.addImChatInfo(addImChatInfo);
		

		Message systemMessage = new  Message(MessageCode.ChatInfoRequest,GUID.generateByUUID(),chatRequest.toJson());
		
		if(ioSession!=null && ioSession.isLogin()){
			serverManager.sendMessageAndCache(newFriendMessage.getMemberNoGm(), ioSession.getChannel(), message);
			serverManager.sendMessageAndCache(newFriendMessage.getMemberNoGm(), ioSession.getChannel(), systemMessage);
			logger.info(" send addFriendsInfo success :{}",addFriendResult);
		}else {
			logger.info("缓存好友信息");
			serverManager.cacheMsg(newFriendMessage.getNoWxGm(), message);
		}
		//serverManager.sendLoginMsgAndCache(newFriendMessage.getMemberNoGm(), message);
	}
	
	/**
	 *
	 * 方法说明：通知其他导购（同终端下除认领导购外）客户已认领
	 *
	 * @param newFriendMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月06日
	 *
	 */
	private void notifyMemberClaim4Gms(NewFriendMessage newFriendMessage) {
		String claimGmKey = getClaimGmKey(newFriendMessage.getCode());
		String claimGmValue = distributeCache.get(claimGmKey);	// 获取缓存中给所有导购发送认领导购的消息id
		if(StringUtils.isNotEmpty(claimGmValue)) {	// 缓存内容不为空
			// 先删除缓存
			distributeCache.del(claimGmKey);
			
			// 构建已认领通知消息业务对象
			MemberClaimedNotify claimedNotify = new MemberClaimedNotify();
			claimedNotify.setMbrCode(newFriendMessage.getCode());
			claimedNotify.setMemberNo(newFriendMessage.getMemberNo());
			claimedNotify.setMemberNoGm(newFriendMessage.getMemberNoGm());
			claimedNotify.setMemberNameGm(newFriendMessage.getMemberNameGm());
			claimedNotify.setClaimTime(newFriendMessage.getClaimTime().getTime());
			String cancelBody = claimedNotify.toJson();
			Message cancelMessage=new Message(MessageCode.MemberClaimedNotify, cancelBody);
			
			// 再逐个通知导购
			Map<String, String> gm2MsgIdMap = JsonUtils.mapFromJson(claimGmValue);
			Iterator<Map.Entry<String, String>> it = gm2MsgIdMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entry = it.next();
				String memberNoGm = entry.getKey();	// 导购编号
				if(!newFriendMessage.getMemberNoGm().equals(memberNoGm)) {	// 通知给未认领的其他导购
					cancelMessage.setMsgId(GUID.generateByUUID());
					try {
						serverManager.sendLoginMsgAndCache(memberNoGm, cancelMessage);
					} catch(Exception e) {
						logger.error("向导购客户端发送客户已认领通知失败" + cancelMessage, e);
					}
				}
			}
		}
	}

	/**
	 *
	 * 方法说明：向导购客户端发送客户认领通知消息
	 *
	 * @param memberClaimMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月16日
	 *
	 */
	@Override
	public void sendMemberClaimNotifyMessage(MemberClaimMessage memberClaimMessage, String memberNoGm) {
		logger.debug("sendMemberClaimNotifyMessage(MemberClaimMessage memberClaimMessage={}) - start", memberClaimMessage); 

		AssertUtils.notNull(memberClaimMessage);
		AssertUtils.notNull(memberClaimMessage.getMemberNoGmList(), "导购编号列表不能为空");
		AssertUtils.notNull(memberClaimMessage.getCode(), "认领code不能为空");
		AssertUtils.notNull(memberClaimMessage.getNoWx(), "客户微信号不能为空");
		
		// 构建通知报文消息实例
		MemberClaimNotify notify = new MemberClaimNotify();
		notify.setMbrCode(memberClaimMessage.getCode());
		notify.setNoWx(memberClaimMessage.getNoWx());
		notify.setAlias(memberClaimMessage.getAlias());
		notify.setNickNameWx(memberClaimMessage.getNickNameWx());
		notify.setNickNameRemarkWx(memberClaimMessage.getNickNameRemarkWx());
		notify.setHeadAddress(memberClaimMessage.getHeadAddress());
		notify.setSex(memberClaimMessage.getSex());
		notify.setCreateTime(memberClaimMessage.getCreateTime());
		Message message = new Message(MessageCode.MemberClaimNotify, notify.toJson());
		
		// 循环发送
		Map<String, String> gm2MsgIdMap = new HashMap<String, String>();	// 映射:导购编号-消息ID
		
			try {
				message.setMsgId(GUID.generateByUUID());	// 生成消息id
				serverManager.sendLoginMsgAndCache(memberNoGm, message);
				
				// 消息ID
				gm2MsgIdMap.put(memberNoGm, message.getMsgId());
			} catch(Exception e) {
				logger.error("向导购" + memberNoGm + "发送认领客户通知失败", e);
			}
			
			// 存入缓存
			distributeCache.set(getClaimGmKey(memberClaimMessage.getCode()), JsonUtils.jsonFromObject(gm2MsgIdMap));
		
		
		logger.debug("sendMemberClaimNotifyMessage(MemberClaimMessage) - end"); 
	}

	public static String getClaimGmKey(String code) {
		return KeyConstant.MEMBER_CLAIM_MSG_CACHE_PREFIX + code;
	}

	/**
	 *
	 * 方法说明：向客户端推送通知消息
	 *
	 * @param pushNotifyMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月20日
	 *
	 */
	@Override
	public void sendPushNotifyMessage(PushNotifyMessage pushNotifyMessage) {
		logger.debug("sendPushNotifyMessage(NotifyMessage pushNotifyMessage={}) - start", pushNotifyMessage); 

		AssertUtils.notNull(pushNotifyMessage);
		AssertUtils.notNull(pushNotifyMessage.getCacheAccountNo(), "终端帐号不能为空");
		AssertUtils.notNull(pushNotifyMessage.getType(), "通知类型不能为空");
		AssertUtils.notNull(pushNotifyMessage.getTitle(), "通知标题不能为空");
		
		try {
			NotifyMessage notifyMessage = new NotifyMessage();
			notifyMessage.setType(pushNotifyMessage.getType());
			notifyMessage.setTitle(pushNotifyMessage.getTitle());
			notifyMessage.setContent(pushNotifyMessage.getContent());
			notifyMessage.setTimestamp(System.currentTimeMillis());
			Message message = new Message(MessageCode.NotifyMessage, notifyMessage.toJson());
			if(pushNotifyMessage.isOfflinePush()) {	// 离线保存消息到缓存
				serverManager.sendLoginMsgAndCache(pushNotifyMessage.getCacheAccountNo(), message);
			} else {	// 离线不保存消息到缓存
				serverManager.sendLoginMsgNoCache(pushNotifyMessage.getCacheAccountNo(), message);
			}
		} catch(Exception e) {
			logger.error("向终端" + pushNotifyMessage.getCacheAccountNo() + "发送通知消息失败", e);
		}
		
		logger.debug("sendPushNotifyMessage(NotifyMessage) - end"); 
	}
	
	/**
	 * 方法说明：发送添加好友的请求
	 * @param findWxInfoRequestDto
	 * @param cachePre 添加途径的缓存KEY前缀
	 * @author 李端强 CreateDate: 2018年1月3日
	 *
	 */
	@Override
	public Map<String, Object> sendAddNewFriendMessage(FindWxInfoRequestDto request,String cachePre) {
		logger.debug("sendAddNewFriendMessage(FindWxInfoRequestDto findWxInfoRequestDto={}, String cachePre={}) - start", request, cachePre);
		Map<String, Object> msgMap = Maps.newHashMap();
		try {
			AssertUtils.notNullAndEmpty(request.getNoWxGM(),"中控微信不能为空");

			IoSession session = ChannelUtils.getZkSessionByNoWx(request.getNoWxGM()); // 中控客户端session
			if(session == null || !session.isLogin()) {//中控未登录
				throw new TsfaServiceException(ErrorCode.ZKCLIENT_OFFLINE, "中控微信已离线.");
			}
			
			// 根据客户微信号查询客户基础表信息
			FindPersonMemberBaseReturn findPersonMemberBaseReturn = findMemberBaseInfo(request);
			
			// 客户基础表存在，则查询同一终端是否已添加该客户
			if(findPersonMemberBaseReturn!=null) {
				FindPersonMemberReturn findPersonMemberReturn= personMemberService.findPersonMemberByNoWxAndShopWx(findPersonMemberBaseReturn.getNoWx(), request.getNoWxGM());
				// 同一终端已经添加该客户,放弃
				if(findPersonMemberReturn != null) {
					logger.warn("该客户已是终端好友:"+request.getWxQrCode());
					msgMap.put("msg", "该客户已是终端好友。");
					msgMap.put("data", findPersonMemberBaseReturn);
					return msgMap;
				}
			}
			
			String msgId = GUID.generateByUUID();
			Message findFriendRequestMessage = new Message(MessageCode.FindWxInfoRequest, msgId, JsonUtils.jsonFromObject(request));
			if (session != null && session.isLogin()) {		// 当中控客户端登录时发送，未登录时当做离线记录不发送
				serverManager.sendMessageNoCache(session.getChannel(), findFriendRequestMessage);
				logger.info("已向中控客户端{}发送查询微信信息请求：{}", request.getNoWxGM(), findFriendRequestMessage.getMsgId());
				String addFriendKey = request.getNoWxGM() + request.getWxQrCode();//商户微信号+QrCode
				redisCache.hset(addFriendKey, cachePre, JsonUtils.jsonFromObject(request));//保存搜索微信基本信息的方式
			}else {
				logger.error("中控客户端{}未登录,不能发送查询微信信息请求：{}", request.getNoWxGM(), findFriendRequestMessage.getMsgId());
			}
			logger.debug("sendAddNewFriendMessage(FindWxInfoRequestDto, String) - end - return value={}", "");  //$NON-NLS-2$
		} catch (Exception e) {
			msgMap.put("msg", "发送添加好友请求错误！！！！");
			return msgMap;
		}
		return msgMap;
	}

	/**
	 * 方法说明：根据客户微信号查询客户基础表信息
	 * @param request
	 * @author 李端强 CreateDate: 2018年03月06日
	 *
	 */
	private FindPersonMemberBaseReturn findMemberBaseInfo(
			FindWxInfoRequestDto request) {
		FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
		if(AddQrCodeUtils.isMobile(request.getWxQrCode())) {//手机号
			findPersonMemberBase.setMobile(request.getWxQrCode());
		}else if(StringUtils.isNumeric(request.getWxQrCode())){//纯数字QQ号
			findPersonMemberBase.setNoQQ(request.getWxQrCode());
		}else{//不满足上述条件,微信号
			findPersonMemberBase.setNoWx(request.getWxQrCode());
		}
		FindPersonMemberBaseReturn findPersonMemberBaseReturn = null;
		try {
			findPersonMemberBaseReturn = personMemberBaseService.findPersonMemberBase(findPersonMemberBase);
			logger.debug("sendAddNewFriendMessage findPersonMemberBaseReturn={}",findPersonMemberBaseReturn);
		} catch(TsfaServiceException e) {
			// 客户基础表不存在
			if(!com.lj.business.member.constant.ErrorCode.PERSON_MEMBER_BASE_NOT_EXIST_ERROR.equals(e.getExceptionCode())) {
				throw e;
			}
		}
		return findPersonMemberBaseReturn;
	}

	/**
	 *
	 * 方法说明：向导购客户端发出强制登出IM和APP的消息
	 *
	 * @param forcedLogoutMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月8日
	 *
	 */
	@Override
	public void sendForcedLogoutMessage(ForcedLogoutMessage forcedLogoutMessage) {
		logger.debug("sendForcedLogoutMessage(ForcedLogoutMessage forcedLogoutMessage={}) - start", forcedLogoutMessage); 

		AssertUtils.notNull(forcedLogoutMessage);
		AssertUtils.notNull(forcedLogoutMessage.getMemberNoGm(), "导购编号不能为空");
		AssertUtils.notNull(forcedLogoutMessage.getMessage(), "登出说明不能为空");
		
		try {
			ForcedLogout notifyMessage = new ForcedLogout();
			notifyMessage.setMessage(forcedLogoutMessage.getMessage());
			Message message = new Message(MessageCode.ForcedLogout, notifyMessage.toJson());
			serverManager.sendLoginMsgNoCache(forcedLogoutMessage.getMemberNoGm(), message);
		} catch(Exception e) {
			logger.error("向导购" + forcedLogoutMessage.getMemberNoGm() + "发送强制登出失败", e);
		}
		
		logger.debug("sendForcedLogoutMessage(ForcedLogoutMessage) - end"); 
	}
	
	/**
	 * 方法说明：查询基础信息之后,发送添加好友的请求
	 * @param sendScanAddNewFriendMessage
	 * @param cachePre 添加途径的缓存KEY前缀
	 * @author 李端强 CreateDate: 2018年1月3日
	 */
	@Override
	public boolean sendScanAddNewFriendMessage(ScanAddFriendRequestDto scanAddFriendRequestDto) {
		logger.debug("sendScanAddNewFriendMessage(ScanAddFriendRequestDto scanAddFriendRequestDto={}, String cachePre={}) - start", scanAddFriendRequestDto); 
		String loginAccountNo = scanAddFriendRequestDto.getNoWxGm();//中控微信号
		IoSession session = ChannelUtils.getZkSessionByNoWx(loginAccountNo); // 中控客户端session

		String msgId = GUID.generateByUUID();
		String reqJson = JsonUtils.jsonFromObject(scanAddFriendRequestDto);
		Message findFriendRequestMessage = new Message(MessageCode.ScanAddFriendRequest, msgId, reqJson);
		if (session != null && session.isLogin()) {		// 当中控客户端登录时发送，未登录时当做离线记录不发送
			serverManager.sendMessageNoCache(session.getChannel(), findFriendRequestMessage);
			logger.info("已向中控客户端{}发送添加微信好友请求：{}", loginAccountNo, findFriendRequestMessage.getMsgId());
		}else {
			logger.error("中控客户端{}未登录,不能发送添加微信好友请求：{}", loginAccountNo, findFriendRequestMessage.getMsgId());
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * 方法说明：向中控客户端发送同步微信通讯录请求
	 *
	 * @param syncShopWxFriendsMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月12日
	 *
	 */
	@Override
	public void sendSyncShopWxFriendsMessage(SyncShopWxFriendsMessage syncShopWxFriendsMessage) {
		logger.debug("sendSyncShopWxFriendsMessage(SyncShopWxFriendsMessage syncShopWxFriendsMessage={}) - start", syncShopWxFriendsMessage); 

		AssertUtils.notNull(syncShopWxFriendsMessage);
		AssertUtils.notNullAndEmpty(syncShopWxFriendsMessage.getNoWxShop(), "终端微信不能为空");
		
		try {
			IoSession session = ChannelUtils.getZkSessionByNoWx(syncShopWxFriendsMessage.getNoWxShop());	// 中控客户端连接session
			if (session != null && session.isLogin()) { // 连接并登录
				SyncShopWxFriendsRequest syncShopWxFriendsRequest = new SyncShopWxFriendsRequest();
				syncShopWxFriendsRequest.setNoWxShop(syncShopWxFriendsMessage.getNoWxShop());
				Message message = new Message(MessageCode.SyncShopWxFriendsRequest, syncShopWxFriendsRequest.toJson());
				serverManager.sendMessageNoCache(session.getChannel(), message);
				logger.info("已向{}中控客户端发送同步微信通讯录消息：{}", syncShopWxFriendsMessage.getNoWxShop(), message);
			} else { // 中控未登录、离线
				logger.info("{}中控客户端未登录，不发送同步微信通讯录消息", syncShopWxFriendsMessage.getNoWxShop());
				throw new TsfaServiceException(ErrorCode.ZKCLIENT_OFFLINE, "终端已离线");
			}
		} catch(TsfaServiceException e) {
			throw e;
		} catch(Exception e) {
			logger.error("向中控" + syncShopWxFriendsMessage.getNoWxShop() + "发送同步微信通讯录失败", e);
			throw new TsfaServiceException(ErrorCode.SEND_SOCKET_MESSAGE_ERROR, "发送微信通讯录同步请求失败");
		}
		
		logger.debug("sendSyncShopWxFriendsMessage(SyncShopWxFriendsMessage) - end"); 
	}

	
	/**
	 * 
	 * 方法说明：向中控客户端发送同步微信历史聊天记录请求
	 *
	 * @param SyncWxChatDataMessage
	 *
	 * @author zlh CreateDate: 2018年1月12日
	 *
	 */
	@Override
	public void sendSyncWxChatDataMessage(SyncWxChatDataMessage syncWxChatDataMessage) {
		logger.debug("sendSyncShopWxFriendsMessage(SyncShopWxFriendsMessage syncShopWxFriendsMessage={}) - start", syncWxChatDataMessage); 

		AssertUtils.notNull(syncWxChatDataMessage);
		AssertUtils.notNullAndEmpty(syncWxChatDataMessage.getNoWxGm(), "终端微信不能为空");
		AssertUtils.notNullAndEmpty(syncWxChatDataMessage.getChatTime(), "同步聊天時間不能为空");
		try {
			IoSession session = ChannelUtils.getZkSessionByNoWx(syncWxChatDataMessage.getNoWxGm());	// 中控客户端连接session
			if (session != null && session.isLogin()) { // 连接并登录
				SyncWxChatDataRequest syncWxChatDataRequest = new SyncWxChatDataRequest();
				syncWxChatDataRequest.setChatTime(syncWxChatDataMessage.getChatTime());
				syncWxChatDataRequest.setNoWxGm(syncWxChatDataMessage.getNoWxGm());
				Message message = new Message(MessageCode.SyncWxChatDataRequest, syncWxChatDataRequest.toJson());
				serverManager.sendMessageNoCache(session.getChannel(), message);
				logger.info("已向{}中控客户端发送历史聊天记录消息：{}", syncWxChatDataMessage.getNoWxGm(), message);
			} else { // 中控未登录、离线
				logger.info("{}中控客户端未登录，不发送同步微信历史聊天记录消息", syncWxChatDataMessage.getNoWxGm());
				throw new TsfaServiceException(ErrorCode.ZKCLIENT_OFFLINE, "终端已离线");
			}
		} catch(TsfaServiceException e) {
			throw e;
		} catch(Exception e) {
			logger.error("向中控" + syncWxChatDataMessage.getNoWxGm() + "发送同步微信历史聊天记录失败", e);
			throw new TsfaServiceException(ErrorCode.SEND_SOCKET_MESSAGE_ERROR, "发送微信历史聊天记录同步请求失败");
		}
		
		logger.debug("sendSyncShopWxFriendsMessage(SyncShopWxFriendsMessage) - end"); 
	}
	
	
	/**
	 * 
	 *
	 * 方法说明：向中控客户端发送同步客户微信基本信息的请求
	 *
	 * @param syncWxInfoMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年3月13日
	 * @return 发送是否成功
	 *
	 */
	@Override
	public boolean sendSyncWxInfoMessage(SyncWxInfoMessage syncWxInfoMessage) {
		logger.debug("sendSyncWxInfoMessage(SyncWxInfoMessage syncWxInfoMessage={}) - start", syncWxInfoMessage); 
		
		AssertUtils.notNull(syncWxInfoMessage);
		AssertUtils.notNullAndEmpty(syncWxInfoMessage.getNoWxShop(), "终端微信不能为空");
		AssertUtils.notNullAndEmpty(syncWxInfoMessage.getMemberNoGm(), "导购编号不能为空");
		AssertUtils.notNullAndEmpty(syncWxInfoMessage.getMemberNo(), "客户编号不能为空");
		
		boolean result = Boolean.TRUE;	// 结果
		
		IoSession zkSession = ChannelUtils.getZkSessionByNoWx(syncWxInfoMessage.getNoWxShop());
		if(ChannelUtils.isLogin(zkSession)) {		// 中控登录才同步
			// 同步频率控制
			String nextSyncBeginTimeString = redisCache.hget(Constants.SYNC_WXINFO_CHCHE_KEY, syncWxInfoMessage.getMemberNoGm() + syncWxInfoMessage.getMemberNo());	// 客户下次同步起始时间
			// 已到下次同步时间
			if(StringUtils.isEmpty(nextSyncBeginTimeString) || new Long(nextSyncBeginTimeString) <= System.currentTimeMillis()) {
				SyncWxInfoRequest syncWxInfoRequest = new SyncWxInfoRequest();
				syncWxInfoRequest.setMemberNoGm(syncWxInfoMessage.getMemberNoGm());
				syncWxInfoRequest.setMemberNo(syncWxInfoMessage.getMemberNo());
				syncWxInfoRequest.setNoWx(syncWxInfoMessage.getNoWx());
				Message message = new Message(MessageCode.SyncWxInfoRequest, syncWxInfoRequest.toJson());
				serverManager.sendMessageNoCache(zkSession.getChannel(), message);
			} else {	// 没到同步时间
				logger.warn("客户{}下次同步时间{}未到", syncWxInfoMessage.getMemberNo(), nextSyncBeginTimeString);
				result = Boolean.FALSE;
			}
		} else {
			logger.error("中控客户端未登录，不能同步客户微信基本信息");
			result = Boolean.FALSE;
		}
		
		logger.debug("sendSyncWxInfoMessage(SyncWxInfoMessage) - end - return value={}", result); 
		return result;
	}
	
	/**
	 * 
	 *
	 * 方法说明：向中控客户端发送手机号请求
	 *
	 * @param syncWxInfoMessage
	 *
	 * @author zlh CreateDate: 2018年3月13日
	 * @return 发送是否成功
	 *
	 */
	public boolean sendPhoneNumberToZKMessage(AddFriendsByPhoneMessage addFriendsByPhoneMessage) {
		logger.debug("sendPhoneNumberToZKMessage(AddFriendsByPhoneMessage addFriendsByPhoneMessage={}) - start", addFriendsByPhoneMessage); //$NON-NLS-1$
		
		AssertUtils.notNull(addFriendsByPhoneMessage);
		AssertUtils.notNullAndEmpty(addFriendsByPhoneMessage.getWxNoGm(), "终端微信不能为空");
		AssertUtils.notNullAndEmpty(addFriendsByPhoneMessage.getList(),"加粉集合不能为空");
		
		boolean result = Boolean.TRUE;	// 结果
		
		AddFriendByPhoneRequest addFriendByPhoneRequest = new AddFriendByPhoneRequest();
		addFriendByPhoneRequest.setList(addFriendsByPhoneMessage.getList());
		Message message = new Message(MessageCode.AddFriendByPhoneRequest, addFriendByPhoneRequest.toJson());
		serverManager.sendLoginMsgAndCache(addFriendsByPhoneMessage.getWxNoGm(), message);
		
		logger.debug("sendPhoneNumberToZKMessage(addFriendsByPhoneMessage) - end - return value={}", result); //$NON-NLS-1$
		return result;
	}

}
