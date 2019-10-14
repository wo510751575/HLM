/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.common;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lj.base.core.util.StringUtils;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.dto.terminalimstatus.TerminalImLoginResponse;
import com.lj.business.supcon.common.SpringContext;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.enums.ConnectSource;
import com.lj.business.supcon.netty.enums.SessionCloseReason;
import com.lj.distributecache.RedisCache;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * 
 * 
 * 类说明：channel工具类
 *  
 * 
 * <p>
 * 详细描述：<br>
 * 登录账户说明：<br>
 * 1、导购手机客户端为导购编号<br>
 * 2、中控手机客户端为手机串号：一个中控手机使用微信分身登录多个微信，所以不使用微信号，而使用手机串号对应唯一的一台中控手机客户端<br>
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月10日
 */
public final class ChannelUtils {

	private static Logger logger = LoggerFactory.getLogger(ChannelUtils.class);
	
	public static AttributeKey<IoSession> SESSION_KEY = AttributeKey.valueOf("session");

	/** 缓存服务器会话与登录账户的映射 */ 
	private static Map<IoSession, String> session2AccountMap  = new ConcurrentHashMap<>();	// ConcurrentHashMap在线程安全的基础上提供了更好的写并发能力，但同时降低了对读一致性的要求

	/** 缓存登录账户与服务器会话的映射 */
	private static ConcurrentMap<String, IoSession> account2SessionMap = new ConcurrentHashMap<>();
	
	/** 缓存微信号与中控客户端会话的映射 */
	private static ConcurrentMap<String, IoSession> noWx2SessionMap = new ConcurrentHashMap<>();
	
	private static com.lj.distributecache.RedisCache redisCache = SpringContext.getBean(RedisCache.class); //记录并区分添加方式
	
	public static String IOSESSION_PREFIXKEY = "IOSESSION_PREFIXKEY_";
	/**
	 * 
	 *
	 * 方法说明：添加新的会话
	 *
	 * @param channel
	 * @param session
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月10日
	 *
	 */
	public static boolean addSession(Channel channel, IoSession session) {
		String addr = AddressUtils.getLocalIP();//获得本机IP
		logger.info("addSession本机内网地址2："+addr);
		Attribute<IoSession> sessionAttr = channel.attr(SESSION_KEY);
		return sessionAttr.compareAndSet(null, session);
	}
	
	/**
	 * 
	 *
	 * 方法说明：获取链接当前会话
	 *
	 * @param channel
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月13日
	 *
	 */
	public static IoSession getSession(Channel channel) {
		Attribute<IoSession> sessionAttr = channel.attr(SESSION_KEY);
		return sessionAttr.get() ;
	}
	
	/**
	 * 
	 *
	 * 方法说明：根据导购客户端链接获取对应中控手机登录的session
	 *
	 * @param gmChannel
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月26日
	 *
	 */
	public static IoSession getZkSessionByGm(Channel gmChannel) {
		IoSession session = getSession(gmChannel);
		if(session == null || !session.isLogin()) {
			return null;
		}
		return noWx2SessionMap.get(session.getNoWxGm());
	}
	
	/**
	 * 
	 *
	 * 方法说明：根据中控手机绑定微信查询中控客户端会话
	 *
	 * @param noWx
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月9日
	 *
	 */
	public static IoSession getZkSessionByNoWx(String noWx) {
		return noWx2SessionMap.get(noWx);
	}
	
	/**
	 * 
	 *
	 * 方法说明：判断会话是否存在
	 *
	 * @param channel
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月16日
	 *
	 */
	public static boolean validateSession(Channel channel) {
		IoSession session = getSession(channel);
		if(session == null) {
			return Boolean.FALSE;
		}
		return session.isLogin();
	}
	
	/**
	 * 
	 *
	 * 方法说明：获取连接地址
	 *
	 * @param channel
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月13日
	 *
	 */
	public static String getIp(Channel channel) {
		return ((InetSocketAddress)channel.remoteAddress()).getAddress().toString().substring(1);
	}

	/**
	 * 
	 *
	 * 方法说明：根据登录账户获取会话
	 *
	 * @param loginAccountNo
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月24日
	 *
	 */
	public static IoSession getSession(String loginAccountNo) {
		return account2SessionMap.get(loginAccountNo);
	}
	
	/**
	 * 
	 *
	 * 方法说明：注册会话,建立会话与导购信息的映射关系
	 *
	 * @param tidLoginResponse	终端登录返回信息
	 * @param connectSource		连接来源{@link ConnectSource}
	 * @param noWx				终端微信
	 * @param channel
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年10月13日
	 *
	 */
	public static IoSession registerSession(TerminalImLoginResponse tidLoginResponse, ConnectSource connectSource, String noWx, Channel channel) {
		try {
			String addr = AddressUtils.getLocalIP();//获得本机IP
			FindGuidMemberReturn member = tidLoginResponse.getFindGuidMemberReturn();
			FindShopTerminalReturn shopTerminalReturn = tidLoginResponse.getFindShopTerminalReturn();
			IoSession session = getSession(channel);
			String key = null;
			if(ConnectSource.GM == connectSource) {	// 来自导购手机的会话，映射的是导购编号
				key = member.getMemberNo();
			} else {								// 来自中控手机的会话，映射的是终端号
				key = noWx;
				session.getZkNoWxList().clear();
				session.addAllZkNoWx(tidLoginResponse.getNoWxList());	// 中控手机客户端支持微信分身的情况，此处应是保存中控手机绑定的微信列表 XXX
				// 缓存中控手机客户端微信号-会话链接的映射	 XXX
				for (String wx : tidLoginResponse.getNoWxList()) {
					noWx2SessionMap.put(wx, session);
					//缓存
					redisCache.set(IOSESSION_PREFIXKEY + wx, addr);
					logger.info("本机内网地址："+addr);
				}
			}
			
			// 登录账号只能单点登录，账号在其他客户端登录时，则退出原客户端
			IoSession oriSession = account2SessionMap.get(key);
			if(oriSession != null) {
				if(oriSession.getChannel() == null || !channel.id().equals(oriSession.getChannel().id())) {
					logger.warn("{}不同链接重复登录{}，新链接：{}，原链接：{}", connectSource.getName(), key, channel, oriSession.getChannel());
					session2AccountMap.remove(oriSession);
					
					// 如果是中控客户端，还需删除微信-会话链接的映射
					if(session.getConnectSource() == ConnectSource.ZK) {
						for(String wx : session.getZkNoWxList()) {
							noWx2SessionMap.remove(wx);
							//缓存
							redisCache.del(IOSESSION_PREFIXKEY + wx);
						}
					}
					oriSession.close(SessionCloseReason.OFFSITE);	// 登出：异地登录
				} else {	// 同一链接,重复登录
					logger.warn("{}同一链接重复登录{}: [{} - {}]", connectSource.getName(), key, session.getIpAddr(), channel.id());
	//				return session;	 // 将最后一次登录返回信息注册到session里,而不是直接返回
				}
				
			}
			if(ConnectSource.GM == connectSource) {	// 来自导购手机的会话，映射的是导购编号
				session.setMemberNoGm(member.getMemberNo());
				session.setMerchantNo(member.getMerchantNo());
			} else {	
				session.setTerminalCode(shopTerminalReturn.getCode());
				session.setMerchantNo(shopTerminalReturn.getMerchantNo());
			}
			session.setNoWxGm(noWx);
			session.setConnectSource(connectSource);
			session.setReconnected(session.isLogin() ? Boolean.TRUE : Boolean.FALSE);
			session.setLogin(Boolean.TRUE);
			
			account2SessionMap.put(key, session);
			session2AccountMap.put(session, key);
	
			redisCache.set(IOSESSION_PREFIXKEY + key, addr);
			logger.info("本机内网地址2："+addr);
			logger.info("[{} - {}] registered...", connectSource, key);
			return session;
		}catch(Exception e) {
			logger.info("registerSession:", e);
			return null;
		}
	}

	/**
	 * 
	 *
	 * 方法说明：注销通信渠道
	 *
	 * @param context
	 * @param closeReason	登出原因
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月4日
	 *
	 */
	public static IoSession ungisterContext(Channel context, SessionCloseReason closeReason){
		if(context  == null){
			return null;
		}
		IoSession session = ChannelUtils.getSession(context);
		String loginAccountNo = session2AccountMap.remove(session);		// 删除会话
		if(StringUtils.isNotEmpty(loginAccountNo)) {
			account2SessionMap.remove(loginAccountNo);
			
			// 如果是中控客户端，还需删除微信-会话链接的映射
			if(session.getConnectSource() == ConnectSource.ZK) {
				for(String noWx : session.getZkNoWxList()) {
					noWx2SessionMap.remove(noWx);
					
					redisCache.del(IOSESSION_PREFIXKEY + noWx);
				}
			}
		}
		if (session != null) {
			session.close(closeReason);
		}
		return session;
	}
	
	/**
	 * 
	 *
	 * 方法说明：返回服务器当前总链接数
	 *
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2017年11月9日
	 *
	 */
	public static int getConnectCount() {
		return session2AccountMap.size();
	}

	/**
	 * @return the account2SessionMap
	 */
	public static ConcurrentMap<String, IoSession> getAccount2SessionMap() {
		return account2SessionMap;
	}
	
	/**
	 * 
	 *
	 * 方法说明：根据会话判断客户端是否已登录
	 *
	 * @param session
	 * @return
	 *
	 * @author 曾垂瑜 CreateDate: 2018年1月31日
	 *
	 */
	public static boolean isLogin(IoSession session) {
		if(session == null) {
			return false;
		}
		return session.isLogin();
	}
	
}
