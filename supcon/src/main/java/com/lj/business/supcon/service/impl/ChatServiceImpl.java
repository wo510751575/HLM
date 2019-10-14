/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaContextServiceException;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.supcon.common.ErrorCode;
import com.lj.business.supcon.dto.chat.CancelChatMessage;
import com.lj.business.supcon.dto.chat.ChatMessageRequest;
import com.lj.business.supcon.dto.chat.ChatMessageResponse;
import com.lj.business.supcon.dto.chat.FindChatImageMessage;
import com.lj.business.supcon.dto.chat.UploadChatFileMessage;
import com.lj.business.supcon.dto.chat.UploadChatVideoMessage;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.ResponseCode;
import com.lj.business.supcon.netty.message.ResponseUtils;
import com.lj.business.supcon.netty.message.chat.CancelChatInfoRequest;
import com.lj.business.supcon.netty.message.chat.ChatInfoRequest;
import com.lj.business.supcon.netty.message.chat.FindChatImageRequest;
import com.lj.business.supcon.netty.message.chat.UploadChatFileRequest;
import com.lj.business.supcon.netty.message.chat.UploadChatVideoRequest;
import com.lj.business.supcon.service.IChatService;
import com.lj.business.weixin.emus.MessageSource;
import com.lj.business.weixin.service.IImChatInfoService;
import com.lj.business.weixin.service.IImSensitiveWordsService;

/**
 * 
 * 类说明：IM聊天消息服务接口定义实现
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年11月15日
 */
@Service
public class ChatServiceImpl implements IChatService {

	private static Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);
	
	@Resource
	private ServerManager serverManager;
	
	@Resource
	private IImSensitiveWordsService imSensitiveWordsService;
	
	@Resource
	private IGmAssistantShopService gmAssistantShopService;
	@Resource
	private IImChatInfoService imChatInfoService;

	@Override
	public ChatMessageResponse sendChatMessage(ChatMessageRequest chatMessageRequest) {
		logger.debug("sendChatMessage(ChatMessageRequest chatInfoMessage={}) - start", chatMessageRequest); 
		
		AssertUtils.notNull(chatMessageRequest, "参数为空");
		AssertUtils.notNullAndEmpty(chatMessageRequest.getSenderFlag(), "发送人标识不能为空");
		AssertUtils.notNullAndEmpty(chatMessageRequest.getReceiveFlag(), "接收人标识不能为空");
		AssertUtils.notNullAndEmpty(chatMessageRequest.getMsgId(), "消息ID不能为空");
//		AssertUtils.notNullAndEmpty(chatMessageRequest.getMemberNoGm(), "导购编号不能为空");
		AssertUtils.notNullAndEmpty(chatMessageRequest.getNoWxGm(), "导购微信号不能为空");
//		AssertUtils.notNullAndEmpty(chatMessageRequest.getMemberNo(), "客户编号不能为空");
		AssertUtils.notNullAndEmpty(chatMessageRequest.getNoWx(), "客户微信号不能为空");
		AssertUtils.notNullAndEmpty(chatMessageRequest.getType(), "消息类型不能为空");
		AssertUtils.notNullAndEmpty(chatMessageRequest.getCreateTime(), "发送时间不能为空");
		
		ChatMessageResponse response = null;
		try {
			if(chatMessageRequest.getReceiveFlag() == MessageSource.GM.getCode()) {	// 导购接收
				response = this.sendChatToGm(chatMessageRequest);
			} else {	// 客户接收
				response = this.sendChatToZk(chatMessageRequest);
			}
		} catch(TsfaContextServiceException e) {
			throw e;
		} catch(TsfaServiceException e) {
			throw e;
		} catch(Exception e) {
			logger.error("发送聊天记录失败", e);
			throw new TsfaServiceException(ErrorCode.SEND_CHAT_ERROR, "发送聊天记录失败");
		}
		
		logger.debug("sendChatMessage(ChatMessageRequest) - end - return value={}", response); 
		return response;
	}
	
	/**
	 * 
	 *
	 * 方法说明：向客户发送聊天消息
	 *
	 * @param chatMessageRequest
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月25日
	 *
	 */
	private ChatMessageResponse sendChatToZk(ChatMessageRequest chatMessageRequest) {
		ChatMessageResponse response = new ChatMessageResponse();
		
		// 构建报文消息
		Message chatMessage = this.buildChatMessage(chatMessageRequest);
		
		// 往中控客户端发送聊天信息
		String loginAccountNo = chatMessageRequest.getNoWxGm();	// 客户微信对应的中控客户端登录账号
		IoSession session = ChannelUtils.getZkSessionByNoWx(loginAccountNo); // 中控客户端session
		if (session != null && session.isLogin()) {		// 当中控客户端登录时发送，未登录时当做离线记录不发送
			serverManager.sendMessageNoCache(session.getChannel(), chatMessage);
			logger.info("已向中控客户端{}发送聊天记录：{}", loginAccountNo, chatMessage.getMsgId());
		}else{
			logger.info("中控客户端{}未登录，不向其发送聊天记录：{}", loginAccountNo, chatMessage.getMsgId());
			ResponseCode responseCode = ResponseUtils.getErrorResponseByBusinessCode(ErrorCode.ZKCLIENT_OFFLINE);
			Map<String, String> map = new HashMap<String, String>();
			map.put("code", responseCode.getCode());
			map.put("message", responseCode.getMessage());
			throw new TsfaContextServiceException(ErrorCode.ZKCLIENT_OFFLINE, "导购客户端已离线，发送失败！", map);
		}
		
		/*// 往导购客户端发送聊天记录，让导购同步到此记录
		loginAccountNo = chatMessageRequest.getMemberNoGm();
		session = ChannelUtils.getSession(loginAccountNo); 	// 导购客户端session
		if (session != null && session.isLogin()) {	// 当中控客户端登录时发送，未登录时当做离线记录不发送
			serverManager.sendMessageNoCache(session.getChannel(), chatMessage);
			logger.info("已向导购客户端{}同步发送聊天记录：{}", loginAccountNo, chatMessage.getMsgId());
		}else{
			logger.info("导购客户端{}未登录，不向其同步发送聊天记录：{}", loginAccountNo, chatMessage.getMsgId());
		}*/
		
		return response;
	}
	
	/**
	 * 
	 *
	 * 方法说明：向导购发送聊天消息
	 *
	 * @param chatMessageRequest
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月25日
	 *
	 */
	private ChatMessageResponse sendChatToGm(ChatMessageRequest chatMessageRequest) {
		ChatMessageResponse response = new ChatMessageResponse();
		
		// 构建报文消息
		Message message = this.buildChatMessage(chatMessageRequest);
		
		// 往导购客户端发送聊天记录
		String noWxGm = chatMessageRequest.getNoWxGm();
		String memberNoGm = chatMessageRequest.getMemberNoGm();
		
		//************************** 广播信息给员工 *************************
		FindGmAssistantShopReturn findGmAssistantShopReturn = gmAssistantShopService.findGmByWxAndNo(noWxGm, memberNoGm);
		if(findGmAssistantShopReturn !=null ) {
			IoSession sessionRec = ChannelUtils.getSession(memberNoGm);
			// 当客户端登录时发送，未登录时当做离线记录不发送
			if (ChannelUtils.isLogin(sessionRec)) {
				/**
				 * 如果导购切换登录的微信和当前终端微信一致才推送
				 */
				String noWx =sessionRec.getNoWxGm();
				if(StringUtils.isNotEmpty(noWx) && noWx.equals(noWxGm)) {
					serverManager.sendMessageNoCache(sessionRec.getChannel(), message);
					logger.info("已向导购{}发送聊天记录：{}", memberNoGm, message.getMsgId());
				}
			} else {
				logger.info("导购客户端{}未登录，不通过Netty向其发送聊天记录：{}",memberNoGm, message.getMsgId());
				serverManager.cacheMsg(memberNoGm, message);
			}
		}
		
		return response;
	}
	
	/**
	 * 
	 *
	 * 方法说明：构建报文消息
	 *
	 * @param chatMessageRequest
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年12月25日
	 *
	 */
	private Message buildChatMessage(ChatMessageRequest chatMessageRequest) {
		ChatInfoRequest chatRequest = new ChatInfoRequest();
		chatRequest.setResend(chatMessageRequest.isResend());
		chatRequest.setMemberNoGm(chatMessageRequest.getMemberNoGm());
		chatRequest.setNoWxGm(chatMessageRequest.getNoWxGm());
		chatRequest.setMemberNo(chatMessageRequest.getMemberNo());
		chatRequest.setNoWx(chatMessageRequest.getNoWx());
		chatRequest.setAlias(chatMessageRequest.getAlias());
		chatRequest.setSenderFlag(chatMessageRequest.getSenderFlag());	// 发送人标识
		chatRequest.setType(chatMessageRequest.getType());
		chatRequest.setContent(chatMessageRequest.getContent());
		chatRequest.setResources(chatMessageRequest.getResources());
		chatRequest.setShareTitle(chatMessageRequest.getShareTitle());
		chatRequest.setShareDes(chatMessageRequest.getShareDes());
		chatRequest.setShareUrl(chatMessageRequest.getShareUrl());
		chatRequest.setCreateTime(chatMessageRequest.getCreateTime());
		chatRequest.setGroupUserName(chatMessageRequest.getGroupUserName());
		chatRequest.setHeadUrl(chatMessageRequest.getHeadUrl());
		chatRequest.setRoomNickName(chatMessageRequest.getRoomNickName());
		chatRequest.setMemberNickName(chatMessageRequest.getMemberNickName());
		chatRequest.setMemberHeadUrl(chatMessageRequest.getMemberHeadUrl());
		chatRequest.setNoDisturb(chatMessageRequest.getNoDisturb());
		// 重新构建导购客户端不支持的消息类型，兼容后发送一条文本消息
		if(chatMessageRequest.getReceiveFlag() == 1) {	// 导购客户端接收
			this.buildChatByUnsupport(chatRequest);
		}
		
		Message chatInfoRequestMessage = new Message(MessageCode.ChatInfoRequest, chatMessageRequest.getMsgId(), chatRequest.toJson());
		return chatInfoRequestMessage; 
	}
	
	/**
	 * 
	 *
	 * 方法说明：重新构建导购客户端不支持的消息类型，兼容后发送一条文本消息
	 *
	 * @param request
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月14日
	 *
	 */
	private void buildChatByUnsupport(ChatInfoRequest request) {
		if(StringUtils.isNotEmpty(request.getShareTitle())) {
			request.setShareTitle(StringUtils.converJson(request.getShareTitle()));
		}
		if(StringUtils.isNotEmpty(request.getShareDes())) {
			request.setShareDes(StringUtils.converJson(request.getShareDes()));
		}
//		String content = null;
//		if(ChatInfoType.CARD.getCode().equals(request.getType())) {
//			content = "[收到一张微信名片：" + request.getShareTitle() + "]";
//		} else {
			return;
//		}
//		request.setType(ChatInfoType.TEXT.getCode());
//		request.setContent(content);
//		request.setResources(null);
//		request.setShareTitle(null);
//		request.setShareDes(null);
//		request.setShareUrl(null);
//		logger.info("导购客户端不支持的消息类型，转换为文本消息发送：{}", request);
	}

	@Override
	public void sendFindChatImageMessage(FindChatImageMessage findChatImageMessage) {
		logger.debug("sendFindChatImageMessage(FindChatImageMessage findChatImageMessage={}) - start", findChatImageMessage); 
		
		AssertUtils.notNull(findChatImageMessage);
		AssertUtils.notNullAndEmpty(findChatImageMessage.getNoWxShop(), "终端微信不能为空");
		AssertUtils.notNullAndEmpty(findChatImageMessage.getMsgId(), "消息ID不能为空");
		AssertUtils.notNullAndEmpty(findChatImageMessage.getType(), "图片类型不能为空");
		AssertUtils.notNullAndEmpty(findChatImageMessage.getFindFlag(), "查询标志不能为空");
		
		IoSession zkSession = ChannelUtils.getZkSessionByNoWx(findChatImageMessage.getNoWxShop());
		if(ChannelUtils.isLogin(zkSession)) {
			FindChatImageRequest request = new FindChatImageRequest();
			request.setMsgId(findChatImageMessage.getMsgId());
			request.setType(findChatImageMessage.getType());
			request.setFindFlag(findChatImageMessage.getFindFlag());
			Message message = new Message(MessageCode.FindChatImageRequest, request.toJson());
			serverManager.sendMessageNoCache(zkSession.getChannel(), message);
			logger.info("已向中控客户端{}发送获取图片消息图片报文：{}", findChatImageMessage.getNoWxShop(), findChatImageMessage.getMsgId());
		} else {
			logger.info("中控客户端{}未登录，不向其发送获取图片消息图片报文：{}", findChatImageMessage.getNoWxShop(), findChatImageMessage.getMsgId());
			throw new TsfaServiceException(ErrorCode.ZKCLIENT_OFFLINE, "导购客户端已离线，获取图片失败！");
		}
		
		logger.debug("sendFindChatImageMessage(FindChatImageMessage) - end"); 
	}

	/**
	 * 
	 *
	 * 方法说明：请求中控获取视频消息视频文件
	 *
	 * @param uploadChatVideoMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月3日
	 *
	 */
	@Override
	public void sendUploadChatVideoMessage(UploadChatVideoMessage uploadChatVideoMessage) {
		logger.debug("sendUploadChatVideoMessage(UploadChatVideoMessage uploadChatVideoMessage={}) - start", uploadChatVideoMessage); 
		
		AssertUtils.notNull(uploadChatVideoMessage);
		AssertUtils.notNullAndEmpty(uploadChatVideoMessage.getNoWxShop(), "终端微信不能为空");
		AssertUtils.notNullAndEmpty(uploadChatVideoMessage.getMsgId(), "消息ID不能为空");
		AssertUtils.notNullAndEmpty(uploadChatVideoMessage.getContent(), "视频参数内容不能为空");
		AssertUtils.notNullAndEmpty(uploadChatVideoMessage.getFindFlag(), "查询标志不能为空");
		
		IoSession zkSession = ChannelUtils.getZkSessionByNoWx(uploadChatVideoMessage.getNoWxShop());
		if(ChannelUtils.isLogin(zkSession)) {
			UploadChatVideoRequest request = new UploadChatVideoRequest();
			request.setMsgId(uploadChatVideoMessage.getMsgId());
			request.setContent(uploadChatVideoMessage.getContent());
			request.setFindFlag(uploadChatVideoMessage.getFindFlag());
			Message message = new Message(MessageCode.UploadChatVideoRequest, request.toJson());
			serverManager.sendMessageNoCache(zkSession.getChannel(), message);
			logger.info("已向中控客户端{}发送上传视频消息视频报文：{}", uploadChatVideoMessage.getNoWxShop(), uploadChatVideoMessage.getMsgId());
		} else {
			logger.info("中控客户端{}未登录，不向其发送上传视频消息视频报文：{}", uploadChatVideoMessage.getNoWxShop(), uploadChatVideoMessage.getMsgId());
			throw new TsfaServiceException(ErrorCode.ZKCLIENT_OFFLINE, "导购客户端已离线，下载视频失败！");
		}
		
		logger.debug("sendUploadChatVideoMessage(UploadChatVideoMessage) - end"); 
	}

	/**
	 * 
	 *
	 * 方法说明：请求中控撤回聊天消息
	 *
	 * @param cancelChatMessage
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月30日
	 *
	 */
	@Override
	public void sendCancelChatMessage(CancelChatMessage cancelChatMessage) {
		logger.debug("sendCancelChatMessage(CancelChatMessage cancelChatMessage={}) - start", cancelChatMessage); 
		
		AssertUtils.notNull(cancelChatMessage);
		AssertUtils.notNullAndEmpty(cancelChatMessage.getNoWxShop(), "终端微信不能为空");
		AssertUtils.notNullAndEmpty(cancelChatMessage.getMsgId(), "消息ID不能为空");
		
		IoSession zkSession = ChannelUtils.getZkSessionByNoWx(cancelChatMessage.getNoWxShop());
		if(ChannelUtils.isLogin(zkSession)) {
			CancelChatInfoRequest request = new CancelChatInfoRequest();
			request.setMsgId(cancelChatMessage.getMsgId());
			Message message = new Message(MessageCode.CancelChatInfoRequest, request.toJson());
			serverManager.sendMessageNoCache(zkSession.getChannel(), message);
			logger.info("已向中控客户端{}发送撤回聊天记录报文：{}", cancelChatMessage.getNoWxShop(), cancelChatMessage.getMsgId());
		} else {
			logger.info("中控客户端{}未登录，不向其发送撤回聊天记录报文：{}", cancelChatMessage.getNoWxShop(), cancelChatMessage.getMsgId());
			throw new TsfaServiceException(ErrorCode.ZKCLIENT_OFFLINE, "导购客户端已离线，撤回聊天记录失败！");
		}
		
		logger.debug("sendCancelChatMessage(CancelChatMessage) - end"); 
	}
	
	@Override
	public void requestZkUploadChatFile(UploadChatFileMessage uploadChatFileMessage) {
		logger.debug("requestZkUploadChatFile(UploadChatFileMessage uploadChatFileMessage={}) - start", uploadChatFileMessage); //$NON-NLS-1$

		AssertUtils.notNull(uploadChatFileMessage);
		AssertUtils.notNullAndEmpty(uploadChatFileMessage.getMsgId(), "消息ID不能为空");
		AssertUtils.notNullAndEmpty(uploadChatFileMessage.getContent(), "content不能为空");
		AssertUtils.notNullAndEmpty(uploadChatFileMessage.getNoWxShop(), "门店微信不能为空");
		IoSession zkSession = ChannelUtils.getZkSessionByNoWx(uploadChatFileMessage.getNoWxShop());
		if(ChannelUtils.isLogin(zkSession)) {
			UploadChatFileRequest request = new UploadChatFileRequest();
			request.setMsgId(uploadChatFileMessage.getMsgId());
			request.setContent(uploadChatFileMessage.getContent());
			request.setFindFlag(uploadChatFileMessage.getFindFlag());
			Message message = new Message(MessageCode.UploadChatFileRequest, request.toJson());
			serverManager.sendMessageNoCache(zkSession.getChannel(), message);
			logger.info("已向中控客户端{}发送下载文件报文：{}", uploadChatFileMessage.getNoWxShop(), uploadChatFileMessage.getMsgId());
		} else {
			logger.info("中控客户端{}未登录，不向其发送下载文件报文：{}", uploadChatFileMessage.getNoWxShop(), uploadChatFileMessage.getMsgId());
			throw new TsfaServiceException(ErrorCode.ZKCLIENT_OFFLINE, "中间件客户端已离线，撤回聊天记录失败！");
		}

		logger.debug("requestZkUploadChatFile(UploadChatFileMessage) - end");
	}
	/*@Override
	public void sendChatMessage(ChatMessageRequest chatMessageRequest) {
		logger.debug("sendChatInfoMessage(ChatMessageRequest chatInfoMessage={}) - start", chatMessageRequest); 
		
		AssertUtils.notNull(chatMessageRequest, "参数为空");
		AssertUtils.notNullAndEmpty(chatMessageRequest.getMemberNoGm(), "导购编号不能为空");
		AssertUtils.notNullAndEmpty(chatMessageRequest.getNoWxGm(), "导购微信号不能为空");
		AssertUtils.notNullAndEmpty(chatMessageRequest.getMemberNo(), "客户编号不能为空");
		AssertUtils.notNullAndEmpty(chatMessageRequest.getNoWx(), "客户微信号不能为空");
		AssertUtils.notNullAndEmpty(chatMessageRequest.getType(), "消息类型不能为空");
		AssertUtils.notNullAndEmpty(chatMessageRequest.getMsgSource(), "消息来源不能为空");
		
		try {
			// 1、导购手机客户端发送的聊天记录才需要敏感词过滤（中控客户端已由微信做了过滤，在此就不重复过滤了）
			if(StringUtils.isNotEmpty(chatMessageRequest.getContent()) && SensitiveWordsUtils.contains(chatMessageRequest.getContent())) {
				throw new TsfaServiceException(ErrorCode.INCLUDE_SENSITIVE_WORDS, "聊天记录包含敏感词");
			}
			
			// 消息ID
			String msgId = StringUtils.isNotEmpty(chatMessageRequest.getMsgId()) ? chatMessageRequest.getMsgId() : GUID.generateByUUID();
			
			// 2、保存聊天记录
			AddImChatInfo addImChatInfo=new AddImChatInfo();
			addImChatInfo.setResend(chatMessageRequest.isResend());
			addImChatInfo.setCode(msgId);
			addImChatInfo.setMemberNoGm(chatMessageRequest.getMemberNoGm());
			addImChatInfo.setNoWxGm(chatMessageRequest.getNoWxGm());
			addImChatInfo.setMemberNo(chatMessageRequest.getMemberNo());
			addImChatInfo.setNoWx(chatMessageRequest.getNoWx());
			addImChatInfo.setAlias(chatMessageRequest.getAlias());
			addImChatInfo.setResourcesPath(chatMessageRequest.getResources());
			addImChatInfo.setShareTitle(chatMessageRequest.getShareTitle());
			addImChatInfo.setShareDes(chatMessageRequest.getShareDes());
			addImChatInfo.setShareUrl(chatMessageRequest.getShareUrl());
			addImChatInfo.setContent(chatMessageRequest.getContent());
			addImChatInfo.setType(chatMessageRequest.getType());
			addImChatInfo.setSenderFlag(SenderFlag.GM.getCode());
			addImChatInfo.setMsgSource(chatMessageRequest.getMsgSource());
			// 发送图片表情，则从content中解析出图片地址
			if("47".equals(chatMessageRequest.getType()) && StringUtils.isNotEmpty(chatMessageRequest.getContent())) {
				Map<String, String> map = JsonUtils.mapFromJson(chatMessageRequest.getContent());
				if(map.containsKey("url")) {
					addImChatInfo.setResourcesPath(map.get("url"));
				}
			}
			AddImChatInfoReturn addImChatInfoReturn = imChatInfoService.addImChatInfo(addImChatInfo);
			logger.info("已保存聊天记录：" + addImChatInfo.getCode());
			
			// 如果消息已发送成功,则不再往接收端发送聊天消息
			if(chatMessageRequest.isResend() && MessageStatus.SUCCESS.getCode().equals(addImChatInfoReturn.getStatus())) {
				logger.info("聊天记录[{}]已由服务器向接收客户端发送成功,无需重复发送", addImChatInfo.getCode());
				return;
			}
		
			// 3、往中控客户端发送聊天信息
			ChatInfoRequest chatRequest = new ChatInfoRequest();
			chatRequest.setResend(chatMessageRequest.isResend());
			chatRequest.setMemberNoGm(chatMessageRequest.getMemberNoGm());
			chatRequest.setNoWxGm(chatMessageRequest.getNoWxGm());
			chatRequest.setMemberNo(chatMessageRequest.getMemberNo());
			chatRequest.setNoWx(chatMessageRequest.getNoWx());
			chatRequest.setAlias(chatMessageRequest.getAlias());
			chatRequest.setSenderFlag(1);	// 发送人标识：导购发送
			chatRequest.setType(chatMessageRequest.getType());
			chatRequest.setContent(chatMessageRequest.getContent());
			chatRequest.setResources(chatMessageRequest.getResources());
			chatRequest.setShareTitle(chatMessageRequest.getShareTitle());
			chatRequest.setShareDes(chatMessageRequest.getShareDes());
			chatRequest.setShareUrl(chatMessageRequest.getShareUrl());
			chatRequest.setCreateTime(addImChatInfoReturn.getCreateDate().getTime());
			Message chatInfoRequestMessage = new Message(MessageCode.ChatInfoRequest, msgId, chatRequest.toJson());
			
			String loginAccountNo = chatMessageRequest.getNoWxGm();	// 客户微信对应的中控客户端登录账号
			IoSession session = ChannelUtils.getZkSessionByNoWx(loginAccountNo); // 中控客户端session
			if (session != null && session.isLogin()) {		// 当中控客户端登录时发送，未登录时当做离线记录不发送
				serverManager.sendMessageNoCache(session.getChannel(), chatInfoRequestMessage);
				logger.info("已向中控客户端{}发送聊天记录：{}", loginAccountNo, chatInfoRequestMessage.getMsgId());
			}else{
				if(chatMessageRequest.getMsgSource() >= 3) {	// 第三方发送，中控客户端必须登陆
					logger.error("中控客户端[{}]已离线，不能发送聊天记录", loginAccountNo);
					ResponseCode responseCode = ResponseUtils.getErrorResponseByBusinessCode(ErrorCode.ZKMOBILE_INVAILD);
					UpdateImChatInfo updateImChatInfo = new UpdateImChatInfo();
					updateImChatInfo.setCode(msgId);
					updateImChatInfo.setStatus("3");
					updateImChatInfo.setErrorCode(responseCode.getCode());
					updateImChatInfo.setErrorMessage(responseCode.getMessage());
					imChatInfoService.updateImChatInfoStatus(updateImChatInfo);
					logger.info("已更新聊天记录发送失败：{}", updateImChatInfo.getCode());
					throw new TsfaServiceException(ErrorCode.ZKMOBILE_INVAILD, "导购客户端已离线，发送失败！");
				} else {
					logger.info("中控客户端{}未登录，不向其发送聊天记录：{}", loginAccountNo, chatInfoRequestMessage.getMsgId());
				}
			}
			
			// 4、往导购客户端发送聊天记录，让导购同步到此记录
			loginAccountNo = chatMessageRequest.getMemberNoGm();
			session = ChannelUtils.getSession(loginAccountNo); 	// 导购客户端session
			if (session != null && session.isLogin()) {	// 当中控客户端登录时发送，未登录时当做离线记录不发送
				serverManager.sendMessageNoCache(session.getChannel(), chatInfoRequestMessage);
				logger.info("已向导购客户端{}同步发送聊天记录：{}", loginAccountNo, chatInfoRequestMessage.getMsgId());
			}else{
				logger.info("导购客户端{}未登录，不向其同步发送聊天记录：{}", loginAccountNo, chatInfoRequestMessage.getMsgId());
			}
		} catch(TsfaServiceException e) {
			logger.error("发送聊天记录失败", e);
			throw e;
		} catch(Exception e) {
			logger.error("发送聊天记录失败", e);
			throw new TsfaServiceException(ErrorCode.SEND_CHAT_ERROR, "发送聊天记录失败");
		}
		
		logger.debug("sendChatInfoMessage(ChatMessageRequest) - end"); 
	}*/

}
