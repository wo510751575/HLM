/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caucho.hessian.client.HessianProxyFactory;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.supcon.common.ErrorCode;
import com.lj.business.supcon.dto.common.VersionInfoMessage;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.AddressUtils;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.chat.ChatInfoReadNumRequest;
import com.lj.business.supcon.netty.message.common.SendSetUpUserToGm;
import com.lj.business.supcon.netty.message.common.SendVersionInfoToZk;
import com.lj.business.supcon.netty.message.common.TerminalLogFilesUpload;
import com.lj.business.supcon.netty.message.friends.UploadFriendsFlagCommand;
import com.lj.business.supcon.netty.message.sign.SignCommand;
import com.lj.business.supcon.netty.message.sign.SignResponse;
import com.lj.business.supcon.service.IChatFriendsFacade;
import com.lj.business.supcon.service.IChatService;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IContactsService;
import com.lj.business.supcon.service.IRedPackFacade;
import com.lj.business.supcon.service.IRedPacketService;
import com.lj.business.supcon.service.IWxAccountService;
import com.lj.business.supcon.service.IWxChatRoomService;
import com.lj.cc.service.ISystemInfoService;
import com.lj.distributecache.IDistributeCache;
import com.lj.distributecache.RedisCache;

/**
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年12月27日
 */
@Service
public class CommonServiceImpl implements ICommonService {

	private static Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);
	
	@Resource
	private ServerManager serverManager;
	
    /** * 系统信息服务. */
    @Autowired 
	private ISystemInfoService systemInfo;
    
    @Autowired 
	private RedisCache redisCache;
    
    @Resource 
	private IDistributeCache distributeCache;
    
    @Autowired
    private IGmAssistantShopService gmAssistantShopService;
    
    /**
     * 设置消息置顶
     * @param memberNoGm
     * @param noWxGm
     */
    public void sendSetUpUser(String memberNoGm,String noWxGm, String memberNo, String setType) {
    	logger.debug("sendSetUpUserMessage() - start{},{}", memberNoGm,noWxGm); 

    	IoSession sessionRec = null;
		
		SendSetUpUserToGm setUpUserToGm = new SendSetUpUserToGm();
		setUpUserToGm.setTopid(memberNo);
		setUpUserToGm.setMemberNoGm(memberNoGm);
		setUpUserToGm.setNoWx(noWxGm);
		setUpUserToGm.setSetType(setType);
		Message message = new Message(MessageCode.MemberSetUpUserRequest, setUpUserToGm.toJson());
		
		//************************** 广播信息给员工 *************************
		FindGmAssistantShopReturn findGmAssistantShopReturn = gmAssistantShopService.findGmByWxAndNo(noWxGm, memberNoGm);
		if(findGmAssistantShopReturn !=null ) {
			sessionRec = ChannelUtils.getSession(memberNoGm);
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
		
		logger.debug("sendSetUpUserMessage() - end"); 
    }

	@Override
	public void sendUploadTerminalLogFilesMessage(String imei, String beginTime) {
		logger.debug("sendUploadTerminalLogFilesMessage(imei={}, beginTime={}) - start", imei, beginTime); 

		AssertUtils.notNullAndEmpty(imei, "IMEI不能为空");
		AssertUtils.notNullAndEmpty(beginTime, "日志开始时间不能为空");
		
		TerminalLogFilesUpload upload = new TerminalLogFilesUpload();
		upload.setBeginTime(beginTime);
		Message message = new Message(MessageCode.TerminalLogFilesUpload, upload.toJson());
		IoSession session = ChannelUtils.getSession(imei);	// 中控客户端连接session
		if (session != null && session.isLogin()) { // 连接并登录，则下发指令
			serverManager.sendMessageNoCache(session.getChannel(), message);
			logger.info("已向{}中控客户端下发上传日志指令：{}", imei, message);
		} else { // 离线不能下发指令
			logger.error("中控终端{}已离线，下发上传日志指令失败", imei);
			throw new TsfaServiceException(ErrorCode.ZKCLIENT_OFFLINE, "终端已离线");
		}
		
		logger.debug("sendUploadTerminalLogFilesMessage() - end"); 
	}

	@Override
	public void sendVersionInfoMessage(VersionInfoMessage versionInfoMessage) {
		logger.debug("sendVersionInfoMessage(VersionInfoMessage versionInfoMessage={}) - start", versionInfoMessage); 

		AssertUtils.notNull(versionInfoMessage);
		AssertUtils.notNullAndEmpty(versionInfoMessage.getNoWxGm(), "终端微信不能为空");
		
		SendVersionInfoToZk versionInfo = new SendVersionInfoToZk();
		BeanUtils.copyProperties(versionInfoMessage, versionInfo);
		Message message = new Message(MessageCode.SendVersionInfoToZk, versionInfo.toJson());
		IoSession session = ChannelUtils.getSession(versionInfoMessage.getNoWxGm());	// 中控客户端连接session
		if (session != null && session.isLogin()) { // 连接并登录，则下发
			serverManager.sendMessageNoCache(session.getChannel(), message);
			logger.info("已向{}中控客户端下发服务器最新版本信息：{}", versionInfoMessage.getNoWxGm(), message);
		} else { // 离线不能下发
			logger.error("中控终端{}已离线，下发服务器最新版本信息失败", versionInfoMessage.getNoWxGm());
			throw new TsfaServiceException(ErrorCode.ZKCLIENT_OFFLINE, "终端已离线");
		}
		
		logger.debug("sendVersionInfoMessage() - end"); 
	}

	@Override
	public boolean getZkTerminalStatus(String noWx) {
		if(StringUtils.isEmpty(noWx)) {
			return Boolean.FALSE;
		}
		IoSession session = ChannelUtils.getZkSessionByNoWx(noWx);
		logger.info("getZkTerminalStatus session={}",session);
		if(session != null && session.isLogin()) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public Map<String, Boolean> getZkTerminalStatusList(List<String> noWxList) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		for(String noWx : noWxList) {
			map.put(noWx, this.getZkTerminalStatus(noWx));
		}
		return map;
	}

	@Override
	public boolean getClientStatus(String account) {
		if(StringUtils.isEmpty(account)) {
			return Boolean.FALSE;
		}
		IoSession session = ChannelUtils.getSession(account);
		if(session != null && session.isLogin()) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public void sendSignCommandMessage(String noWxGm, String token) {
		logger.debug("sendSignCommandMessage(noWxGm={}, token={}) - start", noWxGm, token); 

		AssertUtils.notNullAndEmpty(noWxGm, "中控微信不能为空");
		AssertUtils.notNullAndEmpty(token, "token不能为空");
		
		SignCommand signCommand = new SignCommand();
		signCommand.setToken(token);
		Message message = new Message(MessageCode.SignCommand, signCommand.toJson());
		IoSession session = ChannelUtils.getSession(noWxGm);	// 中控客户端连接session
		if (session != null && session.isLogin()) { // 连接并登录，则下发
			serverManager.sendMessageNoCache(session.getChannel(), message);
			logger.info("已向{}中控客户端下发签到命令：{}", noWxGm, message);
		} else { // 离线不能下发
			logger.error("中控终端{}已离线，下发签到命令失败", noWxGm);
			throw new TsfaServiceException(ErrorCode.ZKCLIENT_OFFLINE, "终端已离线");
		}
		
		logger.debug("sendSignCommandMessage() - end"); 
	}
	
	@Override
	public void sendSignResponse(String noWxGm, String token,String encrypt,Long timestamp) {
		logger.debug("sendSignResponse(noWxGm={}, token={}) - start", noWxGm, token); 

		AssertUtils.notNullAndEmpty(noWxGm, "中控微信不能为空");
		AssertUtils.notNullAndEmpty(token, "token不能为空");
		AssertUtils.notNullAndEmpty(encrypt, "encrypt不能为空");
		AssertUtils.notNullAndEmpty(timestamp, "timestamp不能为空");
		
		// 返回签到结果
		SignResponse signResponse = new SignResponse();
		signResponse.setEncrypt(encrypt);
		signResponse.setTimestamp(timestamp);
		signResponse.setToken(token);
		Message responseMessage = new Message(MessageCode.SignResponse, signResponse.toJson());
		IoSession session = ChannelUtils.getSession(noWxGm);	// 中控客户端连接session
		if (session != null && session.isLogin()) { // 连接并登录，则下发
			serverManager.sendMessageNoCache(session.getChannel(), responseMessage);
			logger.info("已向{}中控客户端下发签到结果：{}", noWxGm, responseMessage);
		} else { // 离线不能下发
			logger.error("中控终端{}已离线，下发签到命令结果", noWxGm);
			throw new TsfaServiceException(ErrorCode.ZKCLIENT_OFFLINE, "终端已离线");
		}
		
		logger.debug("sendSignResponse() - end"); 
	}

	/**
	 * 
	 *
	 * 方法说明：打开或关闭朋友圈上传功能
	 *
	 * @param imei
	 * @param uploadFriends
	 *
	 * @author 曾垂瑜 CreateDate: 2018年8月15日
	 *
	 */
	@Override
	public void sendUploadFriendsFlagCommand(String imei, boolean uploadFriends) {
		logger.debug("sendUploadFriendsFlagCommand(imei={}, uploadFriends={}) - start", imei, uploadFriends); 

		AssertUtils.notNullAndEmpty(imei, "imei不能为空");
		
		UploadFriendsFlagCommand command = new UploadFriendsFlagCommand();
		command.setUploadFriends(uploadFriends);
		Message message = new Message(MessageCode.UploadFriendsFlagCommand, command.toJson());
		IoSession session = ChannelUtils.getSession(imei);	// 中控客户端连接session
		if (session != null && session.isLogin()) { // 连接并登录，则下发
			serverManager.sendMessageNoCache(session.getChannel(), message);
			logger.info("已向{}中控客户端下发{}朋友圈上传功能命令：{}", imei, (uploadFriends ? "打开" : "关闭"), message);
		} else { // 离线不能下发
			logger.error("中控终端{}已离线，下发{}朋友圈上传功能命令失败", imei, (uploadFriends ? "打开" : "关闭"));
			throw new TsfaServiceException(ErrorCode.ZKCLIENT_OFFLINE, "终端已离线");
		}
		
		logger.debug("sendUploadFriendsFlagCommand() - end"); 
	}
	
	
	/**
	 * 集群环境动态hessianUri
	 * @param noWx
	 * @return
	 */
	public ICommonService getHessianCommonService(String noWx) {
		try {
			//集群环境下手动调用
			String key = ChannelUtils.IOSESSION_PREFIXKEY;
	    	String url = systemInfo.findSystemInfo("zk").getRmiUrl();
	    	String ip = redisCache.get(key + noWx);
	    	logger.info("ip ={}",redisCache.get(key + noWx));
	    	//如果中控未登陆，随便用一个IP
	    	String addr = AddressUtils.getLocalIP();
	    	if(ip == null || ip.equals("")) {
	    		ip = addr;
	    	}
	    	String ipAddr [] = url.split(":");
	    	url = ipAddr[0] + "://" + ip +":"+ ipAddr[2] + "/commonService";
	    	logger.info("getHessianCommonService:"+url);
	        HessianProxyFactory factory = new HessianProxyFactory();
	        ICommonService basic = (ICommonService)factory.create(ICommonService.class, url);
	    	return basic;
		}catch(Exception e) {
			logger.error("getHessianCommonService", e);
			return null;
		}
	}
	
	/**
	 * 集群环境动态hessianUri
	 * @param noWx
	 * @return
	 */
	public IContactsService getHessianContactsService(String noWx) {
		try {
			//集群环境下手动调用
			String key = ChannelUtils.IOSESSION_PREFIXKEY;
	    	String url = systemInfo.findSystemInfo("zk").getRmiUrl();
	    	String ip = redisCache.get(key + noWx);
	    	
	    	//如果中控未登陆，随便用一个IP
	    	String addr = AddressUtils.getLocalIP();
	    	if(ip == null || ip.equals("")) {
	    		ip = addr;
	    	}
	    	String ipAddr [] = url.split(":");
	    	url = ipAddr[0] + "://" + ip +":"+ ipAddr[2] + "/contactsService";
	    	logger.info("getHessianContactsService"+ url);
	        HessianProxyFactory factory = new HessianProxyFactory();
	        IContactsService basic = (IContactsService)factory.create(IContactsService.class, url);
	    	return basic;
		}catch(Exception e) {
			logger.error("getHessianContactsService", e);
			return null;
		}
	}
	
	/**
	 * 集群环境动态hessianUri
	 * @param noWx
	 * @return
	 */
	public IChatService getHessianChatService(String noWx) {
		try {
			//集群环境下手动调用
			String key = ChannelUtils.IOSESSION_PREFIXKEY;
	    	String url = systemInfo.findSystemInfo("zk").getRmiUrl();
	    	String ip = redisCache.get(key + noWx);
	    	
	    	//如果中控未登陆，随便用一个IP
	    	String addr = AddressUtils.getLocalIP();
	    	if(ip == null || ip.equals("")) {
	    		ip = addr;
	    	}
	    	String ipAddr [] = url.split(":");
	    	url = ipAddr[0] + "://" + ip +":"+ ipAddr[2] + "/chatService";
	    	logger.error("getHessianIChatService:url", url);
	        HessianProxyFactory factory = new HessianProxyFactory();
	        IChatService basic = (IChatService)factory.create(IChatService.class, url);
	    	return basic;
		}catch(Exception e) {
			logger.error("getHessianIChatService", e);
			return null;
		}
	}
	
	
	/**
	 * 集群环境动态hessianUri
	 * @param noWx
	 * @return
	 */
	public IChatFriendsFacade getHessianIChatFriendsFacade(String noWx) {
		try {
			//集群环境下手动调用
			String key = ChannelUtils.IOSESSION_PREFIXKEY;
	    	String url = systemInfo.findSystemInfo("zk").getRmiUrl();
	    	String ip = redisCache.get(key + noWx);
	    	
	    	//如果中控未登陆，随便用一个IP
	    	String addr = AddressUtils.getLocalIP();
	    	if(ip == null || ip.equals("")) {
	    		ip = addr;
	    	}
	    	String ipAddr [] = url.split(":");
	    	url = ipAddr[0] + "://" + ip +":"+ ipAddr[2] + "/chatFriendsFacade";
	    	logger.error("getHessianwxChatRoomService:url", url);
	        HessianProxyFactory factory = new HessianProxyFactory();
	        IChatFriendsFacade basic = (IChatFriendsFacade)factory.create(IChatFriendsFacade.class, url);
	    	return basic;
		}catch(Exception e) {
			logger.error("getHessianIChatFriendsFacade", e);
			return null;
		}
	}
	
	
	/**
	 * 集群环境动态hessianUri
	 * @param noWx
	 * @return
	 */
	public IRedPackFacade getHessianIRedPackFacade(String noWx) {
		try {
			//集群环境下手动调用
			String key = ChannelUtils.IOSESSION_PREFIXKEY;
	    	String url = systemInfo.findSystemInfo("zk").getRmiUrl();
	    	String ip = redisCache.get(key + noWx);
	    	
	    	//如果中控未登陆，随便用一个IP
	    	String addr = AddressUtils.getLocalIP();
	    	if(ip == null || ip.equals("")) {
	    		ip = addr;
	    	}
	    	String ipAddr [] = url.split(":");
	    	url = ipAddr[0] + "://" + ip +":"+ ipAddr[2] + "/redPackFacade";
	    	logger.error("getHessianIRedPackFacade:url", url);
	        HessianProxyFactory factory = new HessianProxyFactory();
	        IRedPackFacade basic = (IRedPackFacade)factory.create(IRedPackFacade.class, url);
	    	return basic;
		}catch(Exception e) {
			logger.error("getHessianIRedPackFacade", e);
			return null;
		}
	}
	
	

	
	/**
	 * 集群环境动态hessianUri
	 * @param noWx
	 * @return
	 */
	public IWxChatRoomService getHessianWxChatRoomService(String noWx) {
		try {
			//集群环境下手动调用
			String key = ChannelUtils.IOSESSION_PREFIXKEY;
	    	String url = systemInfo.findSystemInfo("zk").getRmiUrl();
	    	String ip = redisCache.get(key + noWx);
	    	
	    	//如果中控未登陆，随便用一个IP
	    	String addr = AddressUtils.getLocalIP();
	    	if(ip == null || ip.equals("")) {
	    		ip = addr;
	    	}
	    	
	    	String ipAddr [] = url.split(":");
	    	url = ipAddr[0] + "://" + ip +":"+ ipAddr[2] + "/wxChatRoomService";
	    	logger.info("getHessianwxChatRoomService:url", url);
	        HessianProxyFactory factory = new HessianProxyFactory();
	        IWxChatRoomService basic = (IWxChatRoomService)factory.create(IWxChatRoomService.class, url);
	    	return basic;
		}catch(Exception e) {
			logger.error("getHessianwxChatRoomService", e);
			return null;
		}
	}
	
	public IRedPacketService getHessianIRedPacketService(String noWx) {
		try {
			
			//集群环境下手动调用
			String key = ChannelUtils.IOSESSION_PREFIXKEY;
	    	String url = systemInfo.findSystemInfo("zk").getRmiUrl();
	    	String ip = redisCache.get(key + noWx);
	    	//如果中控未登陆，随便用一个IP
	    	String addr = AddressUtils.getLocalIP();
	    	if(ip == null || ip.equals("")) {
	    		ip = addr;
	    	}
	    	String ipAddr [] = url.split(":");
	    	url = ipAddr[0] + "://" + ip +":"+ ipAddr[2] + "/redPacketService";
	    	logger.info("getHessianIRedPacketService:url", url);
	        HessianProxyFactory factory = new HessianProxyFactory();
	        IRedPacketService basic = (IRedPacketService)factory.create(IRedPacketService.class, url);
	    	return basic;
		}catch(Exception e) {
			logger.error("getHessianIRedPacketService", e);
			return null;
		}
	}
	/**
 * 集群环境动态hessianUri
 * @param noWx
 * @return
 */
public IWxAccountService getHessianIWxAccountService(String noWx) {
	try {
		//集群环境下手动调用
		String key = ChannelUtils.IOSESSION_PREFIXKEY;
    	String url = systemInfo.findSystemInfo("zk").getRmiUrl();
    	String ip = redisCache.get(key + noWx);
    	
    	//如果中控未登陆，随便用一个IP
    	String addr = AddressUtils.getLocalIP();
    	if(ip == null || ip.equals("")) {
    		ip = addr;
    	}
    	
    	String ipAddr [] = url.split(":");
    	url = ipAddr[0] + "://" + ip +":"+ ipAddr[2] + "/wxAccountService";
    	logger.error("getHessianIWxAccountService:url", url);
        HessianProxyFactory factory = new HessianProxyFactory();
        IWxAccountService basic = (IWxAccountService)factory.create(IWxAccountService.class, url);
    	return basic;
	}catch(Exception e) {
		logger.error("getHessianIWxAccountService", e);
		return null;
	}
}

	@Override
	public void sendChatInfoReadNum(String memberNoGm,String memberNo, int num) {
		logger.debug("sendChatInfoReadNum(String memberNoGm={},String memberNo={}, int num={}) - start{},{}",memberNoGm,memberNo,num); 

		IoSession sessionRec = ChannelUtils.getSession(memberNoGm);
		ChatInfoReadNumRequest chatInfoReadNumRequest = new ChatInfoReadNumRequest();
		chatInfoReadNumRequest.setMemberNo(memberNo);
		chatInfoReadNumRequest.setNum(num);
		Message message = new Message(MessageCode.ChatInfoReadNumRequest, chatInfoReadNumRequest.toJson());
		// 当客户端登录时发送，未登录时当做离线记录不发送
		if (ChannelUtils.isLogin(sessionRec)) {
			serverManager.sendMessageNoCache(sessionRec.getChannel(), message);
			logger.info("已向导购{}发送聊天记录：{}", memberNoGm, message.getMsgId());
		} else {
			logger.info("导购客户端{}未登录，不通过Netty向其发送聊天记录：{}",memberNoGm, message.getMsgId());
		}
		logger.debug("sendSetUpUserMessage() - end"); 
		
	}

}
