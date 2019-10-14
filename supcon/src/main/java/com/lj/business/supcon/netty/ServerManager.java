/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import com.lj.base.core.util.StringUtils;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.member.dto.terminalimstatus.TerminalImLoginResponse;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.enums.ConnectSource;
import com.lj.business.supcon.netty.enums.SessionCloseReason;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.service.LoginService;
import com.lj.distributecache.IDistributeCache;

import io.netty.channel.Channel;

/**
 * 
 * 
 * 类说明：Netty服务管理，主要是报文发送与缓存
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月10日
 */
@Service
public class ServerManager {

	private static Logger logger = LoggerFactory.getLogger(ServerManager.class);
	
	private static String NETTY_MSG_PREFIX = "NETTYMSG-";					// 报文消息缓存前缀（不包括离线聊天记录）
	private static String NETTY_MBRMSG_PREFIX = "NETTYMBRMSG-";			// 用户-消息映射缓存前缀（不包括离线聊天记录）
//	private static String NETTY_MBRLOGIN_PREFIX = "NETTYMBRLOGIN-";		// 客户端登录状态
	
	private static String SPLIT = "-";
	private static String SPLIT2 = "-{";
	/**
	 * 用于对账户缓存消息时进行同步控制
	 * 不考虑对以账户字符串为同步锁的垃圾回收,所以此处选择{@link Interners#newStrongInterner},而不是{@link Interners#newWeakInterner}
	 */
	public static Interner<String> pool = Interners.newStrongInterner();
	
	@Resource 
	private IDistributeCache distributeCache;
	
	/**
	 * 
	 *
	 * 方法说明：判断客户端登录状态,并向指定客户端发送消息(缓存报文)
	 *
	 * @param cacheAccountNo
	 * @param message
	 *
	 * @author 彭俊霖 CreateDate: 2018年03月06日
	 *
	 */
	public void sendLoginMsgAndCache(String cacheAccountNo, Message message) {
		IoSession session = ChannelUtils.getSession(cacheAccountNo);	// 获取导购手机对应的session
		if (session != null && session.isLogin() && null !=session.getChannel()) { // 连接并登录，则通知导购手机客户端结果
			this.sendMessageAndCache(cacheAccountNo, session.getChannel(), message);
			logger.info("已向{}客户端发送通知：{}", cacheAccountNo, message);
		} else { // 导购未登录、离线，则保存消息，等待下次登录时通知
			this.cacheMsg(cacheAccountNo, message);
			logger.info("{}客户端未登录，保存离线消息：{}", cacheAccountNo, message);
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：判断客户端登录状态,并向指定客户端发送消息(不缓存报文)
	 *
	 * @param cacheAccountNo
	 * @param message
	 *
	 * @author 彭俊霖 CreateDate: 2018年03月06日
	 *
	 */
	public void sendLoginMsgNoCache(String cacheAccountNo, Message message) {
		IoSession session = ChannelUtils.getSession(cacheAccountNo);	// 导购客户端连接session
		if (ChannelUtils.isLogin(session)) { // 连接并登录，则通知
			this.sendMessageNoCache(session.getChannel(), message);
			logger.info("已向{}客户端发送通知", cacheAccountNo);
		} else { // 导购未登录、离线，不保存
			logger.info("{}客户端未登录，不保存通知", cacheAccountNo);
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：向指定客户端发送消息(缓存报文)
	 *
	 * @param cacheAccountNo	缓存账号(导购客户端为导购编号，中控客户端为导购微信号)
	 * @param channel
	 * @param message
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月24日
	 *
	 */
	public void sendMessageAndCache(String cacheAccountNo, Channel channel, Message message) {
		if(message == null || channel == null) {
			return;
		}
		
		// 登录状态下，缓存报文
		if(StringUtils.isNotEmpty(cacheAccountNo)) {
			// 将发送消息保存到缓存redis中（此时是发送中状态，当收到客户端通讯响应报文9999时，删除此缓存，标识该消息发送成功）
			this.cacheMsg(cacheAccountNo, message);
		}
		
		// 发送报文
		this.sendMessage(channel, message);
	}
	
	/**
	 * 
	 *
	 * 方法说明：向指定客户端发送消息（此方法不缓存消息记录，慎用！）
	 *
	 * @param channel
	 * @param message
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月24日
	 *
	 */
	public void sendMessageNoCache(Channel channel, Message message) {
		if(message == null || channel == null) return;
		this.sendMessage(channel, message);
	}
	
	/**
	 * 
	 *
	 * 方法说明：发送报文消息
	 *
	 * @param channel
	 * @param message
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月9日
	 *
	 */
	private void sendMessage(Channel channel, Message message) {
		IoSession session = ChannelUtils.getSession(channel);	// 获取session,用于记录发送报文的内容
		logger.info("Send message[{} - {} - {}]: {}", session.getLoginAccountNo(), session.getIpAddr(), channel.id(), message);
		try {
			channel.writeAndFlush(message);
		} catch(Exception e) {
			logger.error("发送消息[" + message.getMsgId() + "]失败", e);
			/**
			 *  XXX 重发机制
			 *  当前只实现了链路断开情况下报文消息发送失败，客户端重连后服务器主动重发离线报文,请查看{@link LoginService#process}
			 */
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：缓存发送中的消息记录
	 *
	 * @param cacheAccountNo	缓存账号(导购客户端为导购编号，中控客户端为导购微信号)
	 * @param message
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月24日
	 *
	 */
	public void cacheMsg(String cacheAccountNo, Message message) {
		try {
			switch (message.getCode()) {
			case ChatInfoRequest:	// 聊天报文不缓存
				break;
			case CommonResponse:	// 通讯响应报文不缓存
				break;
			default:				// 其他报文
				String msgKey = getMsgKey(message.getMsgId());
				String msgValue = cacheAccountNo + SPLIT + JsonUtils.jsonFromObject_LongToString(message);	// 消息格式 = 登录账户-消息内容json串
				distributeCache.set(msgKey, msgValue);
				
				String mbrMsgKey = getMbrMsgKey(cacheAccountNo);
				synchronized (pool.intern(mbrMsgKey)) {	// 同步锁,防止并发情况下对同一个账号的消息缓存进行更新(添加或删除)而出现脏数据
					String mbrMsgValue = distributeCache.get(mbrMsgKey);
					Map<String, String> map = null;
					if(StringUtils.isNotEmpty(mbrMsgValue)) {
						map = JsonUtils.mapFromJson(mbrMsgValue);
					} else {
						map = new HashMap<String, String>();
					}
					map.put(msgKey, msgKey);
					distributeCache.set(mbrMsgKey, JsonUtils.jsonFromObject(map));
				}
				break;
			}
		} catch(Exception e) {
			logger.error("缓存发送中的消息记录[" + message.getMsgId() + "]失败", e);
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：当消息发送成功时，删除缓存中的消息记录
	 *
	 * @param msgId
	 * @param messageCode
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月24日
	 *
	 */
	public void removeMsg(String msgId, MessageCode messageCode) {
		try {
			switch (messageCode) {
			case ChatInfoRequest:	// 聊天报文不缓存
				break;
			case CommonResponse:	// 通讯响应报文不缓存
				break;
			default:				// 其他报文
				String msgKey = getMsgKey(msgId);
				String msgValue = distributeCache.get(msgKey);
				if(StringUtils.isEmpty(msgValue)) {
					break;
				}
				distributeCache.del(msgKey);	// 删除消息缓存
				
				// 获取消息对应的缓存账户
				logger.info("removeMsg的消息记录1[" + msgValue + "]");
//				String cacheAccountNo = msgValue.substring(0, msgValue.indexOf(SPLIT)); //微信号带-会出问题
				String cacheAccountNo = msgValue.substring(0, msgValue.indexOf(SPLIT2));
				logger.info("removeMsg的消息记录2[" + cacheAccountNo + "]");
				String mbrMsgKey = getMbrMsgKey(cacheAccountNo);
				synchronized (pool.intern(mbrMsgKey)) {	// 同步锁,防止并发情况下对同一个账号的消息缓存进行更新(添加或删除)而出现脏数据
					String mbrMsgValue = distributeCache.get(mbrMsgKey);	// 获取缓存账户对应的消息ID
					if(StringUtils.isNotEmpty(mbrMsgValue)) {
						Map<String, String> map = JsonUtils.mapFromJson(mbrMsgValue);
						map.remove(msgKey);
						distributeCache.set(mbrMsgKey, JsonUtils.jsonFromObject(map));
					} else {
						break;
					}
				}
				break;
			}
		} catch(Exception e) {
			logger.error("删除发送中的消息记录[" + messageCode.getCode() + " - " + msgId + "]失败", e);
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：根据会话从缓存中获取登录用户所有未发送成功的消息<br>
	 * 适用于已获取登录用户的会话{@link IoSession}的情况,如IM登录{@link LoginService#process}
	 *
	 * @param session
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月24日
	 *
	 */
	public List<Message> getCacheBySession(IoSession session) {
		logger.info("getCacheBySession(IoSession session={})",session);
		// 导购客户端按导购编号查询
		if(session.getConnectSource() == ConnectSource.GM) {
			return this.getCacheByAccount(session.getMemberNoGm());
		} else {	// 中控客户端，则按其绑定的微信号查询
			List<Message> messageList = new ArrayList<Message>();
			for(String noWx : session.getZkNoWxList()) {
				List<Message> msgList = this.getCacheByAccount(noWx);
				if(msgList != null && !msgList.isEmpty()) {
					messageList.addAll(msgList);
				}
			}
			return messageList;
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：根据账号获取缓存账户所有未发送成功的消息（离线聊天记录除外）<br>
	 * 适用于只知道登录账户且没有会话{@link IoSession}的情况
	 *
	 * @param cacheAccountNo	缓存账号(导购客户端为导购编号，中控客户端为导购微信号)
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月24日
	 *
	 */
	public List<Message> getCacheByAccount(String cacheAccountNo) {
		String mbrMsgKey = getMbrMsgKey(cacheAccountNo);
		String mbrMsgValue = distributeCache.get(mbrMsgKey);
		if(StringUtils.isEmpty(mbrMsgValue)) {
			return null;
		}
		Map<String, String> map = JsonUtils.mapFromJson(mbrMsgValue);
		List<Message> messageList = new ArrayList<Message>();
		for (String msgIdKey : map.keySet()) {  
			Message message = this.getMsgByMsgKey(msgIdKey);
			if(message != null) {
				messageList.add(message);
			}
		}
		return messageList;
	}
	
	/**
	 * 
	 *
	 * 方法说明：获取指定msgId的消息记录
	 *
	 * @param msgId
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月24日
	 *
	 */
	public Message getMsg(String msgId) {
		return this.getMsgByMsgKey(getMsgKey(msgId));
	}
	
	/**
	 * 
	 *
	 * 方法说明：获取指定msgId对应缓存key的消息记录
	 *
	 * @param msgKey
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月18日
	 *
	 */
	public Message getMsgByMsgKey(String msgKey) {
		try {
			String msgValue = distributeCache.get(msgKey);
			if(StringUtils.isEmpty(msgValue)) {
				return null;
			}
			//TODO 登录账号中可能包含SPLIT 待优化
			logger.info("获取发送中的消息记录1[" + msgValue + "]");
			//String messageJson = msgValue.substring(msgValue.indexOf(SPLIT) + 1);	// 去掉前面的登录账号  (脑残的写法，因为有人把微信号存入，微信号里面带了-号)
			
			String messageJson = msgValue.substring(msgValue.indexOf(SPLIT2) + 1);//split2 也不太合适，可能微信号也带-{
			logger.info("获取发送中的消息记录2[" + messageJson + "]");
			return (Message) JsonUtils.objectFromJson(messageJson, Message.class);
		} catch(Exception e) {
			logger.error("获取发送中的消息记录[" + msgKey + "]失败", e);
			return null;
		}
	}
	
	/**
	 * 
	 *
	 * 方法说明：登录
	 *
	 * @param tidLoginResponse
	 * @param connectSource
	 * @param noWx
	 * @param channel
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月4日
	 *
	 */
	public IoSession login(TerminalImLoginResponse tidLoginResponse, ConnectSource connectSource, String noWx, Channel channel) {
		// 注册session
		IoSession session = ChannelUtils.registerSession(tidLoginResponse, connectSource, noWx, channel);

		// 缓存登录状态
		/**
		 * 暂不缓存登录状态
		 * update by   zengchuiyu 
		 * update date 2017-12-07
		 */
//		String key = getMbrLoginKey(session.getLoginAccountNo());
//		String value = distributeCache.get(key);
//		ClientLoginStatus loginStatus = null;
//		if(StringUtils.isNotEmpty(value)) {
//			loginStatus = (ClientLoginStatus) JsonUtils.objectFromJson(value, ClientLoginStatus.class);
//		} else {
//			loginStatus = new ClientLoginStatus();
//		}
//		loginStatus.setMemberNoGm(session.getMemberNoGm());
//		loginStatus.setImei(session.getImei());
//		loginStatus.setConnectSource(session.getConnectSource());
//		loginStatus.setLoginTime(new Date());
//		loginStatus.setIpAddr(session.getIpAddr());
//		distributeCache.set(key, JsonUtils.jsonFromObject_LongToString(loginStatus));
		
		// 返回session
		return session;
	}
	
	/**
	 * 
	 *
	 * 方法说明：登出
	 * @param context
	 * @param closeReason	登出原因
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月4日
	 *
	 */
	public void logout(Channel channel, SessionCloseReason closeReason) {
		// 注销通道，包括session
		/*IoSession session = */ChannelUtils.ungisterContext(channel, closeReason);
		
		// 已登录成功后登出
		/**
		 * 暂不缓存登录状态
		 * update by   zengchuiyu 
		 * update date 2017-12-07
		 */
		/*if(session != null) {
			String loginAccountNo = session.getLoginAccountNo();	// 获取登录账号
			if(StringUtils.isNotEmpty(loginAccountNo)) {
				String key = getMbrLoginKey(session.getLoginAccountNo());
				String value = distributeCache.get(key);
				ClientLoginStatus loginStatus = null;
				if(StringUtils.isNotEmpty(value)) {
					loginStatus = (ClientLoginStatus) JsonUtils.objectFromJson(value, ClientLoginStatus.class);
				} else {
					return;
				}
				
				loginStatus.setLogoutTime(new Date()); // 登出时间
				distributeCache.set(key, JsonUtils.jsonFromObject_LongToString(loginStatus));
			}
		}*/
	}
	
	/**
	 * 
	 *
	 * 方法说明：获取缓存中的登录状态
	 *
	 * @param loginAccountNo
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月4日
	 *
	 */
	/**
	 * 暂不缓存登录状态
	 * update by   zengchuiyu 
	 * update date 2017-12-07
	 */
	/*public ClientLoginStatus getLastStatus(String loginAccountNo) {
		if(StringUtils.isNotEmpty(loginAccountNo)) {
			String key = getMbrLoginKey(loginAccountNo);
			String value = distributeCache.get(key);
			if(StringUtils.isNotEmpty(value)) {
				return (ClientLoginStatus) JsonUtils.objectFromJson(value, ClientLoginStatus.class);
			} else {
				return null;
			}
		}
		return null;
	}*/
	
	/**
	 * 
	 *
	 * 方法说明：获取账户缓存消息的KEY
	 *
	 * @param loginAccountNo
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年3月2日
	 *
	 */
	private static String getMbrMsgKey(String loginAccountNo) {
		return NETTY_MBRMSG_PREFIX + loginAccountNo;	// 增加前缀，避免分布式缓存redis中与其他应用场景有冲突
	}
	
	/**
	 * 
	 *
	 * 方法说明：获取缓存消息的KEY
	 *
	 * @param msgId
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年3月2日
	 *
	 */
	private static String getMsgKey(String msgId) {
		return NETTY_MSG_PREFIX + msgId;	// 增加前缀，避免分布式缓存redis中与其他应用场景有冲突
	}
	
	/*private static String getMbrLoginKey(String loginAccountNo) {
		return NETTY_MBRLOGIN_PREFIX + loginAccountNo;
	}*/
}
