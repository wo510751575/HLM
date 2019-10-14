/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
package com.lj.business.supcon.netty.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.business.common.CommonConstant;
import com.lj.business.common.KeyConstant;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindPersonMemberBase;
import com.lj.business.member.dto.FindPersonMemberBaseReturn;
import com.lj.business.member.dto.addfriends.AddAddFriends;
import com.lj.business.member.dto.addfriends.AddAddFriendsReturn;
import com.lj.business.member.dto.addfriends.FindAddFriendsReturn;
import com.lj.business.member.dto.addfriends.UpdateAddFriends;
import com.lj.business.member.dto.im.FindGuidByShopAndMember;
import com.lj.business.member.dto.im.FindGuidByShopAndMemberReturn;
import com.lj.business.member.service.IAddFriendsService;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.member.service.IPersonMemberBaseService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.supcon.dto.contacts.FindWxInfoRequestDto;
import com.lj.business.supcon.dto.contacts.ScanAddFriendRequestDto;
import com.lj.business.supcon.netty.IService;
import com.lj.business.supcon.netty.IoSession;
import com.lj.business.supcon.netty.ServerManager;
import com.lj.business.supcon.netty.common.ChannelUtils;
import com.lj.business.supcon.netty.message.Message;
import com.lj.business.supcon.netty.message.MessageCode;
import com.lj.business.supcon.netty.message.ResponseCode;
import com.lj.business.supcon.netty.message.ResponseUtils;
import com.lj.business.supcon.netty.message.contacts.FindWxInfoReturn;
import com.lj.business.supcon.netty.message.contacts.ScanAddFriendRequest;
import com.lj.business.supcon.service.IContactsService;
import com.lj.distributecache.RedisCache;

import io.netty.channel.Channel;

/**
 * 
 * 类说明：返回客户微信基本信息
 *  
 * 
 * <p>
 * 详细描述：接收中控手机返回的客户微信基本信息，同时根据微信号判断是否已添加该客户
 * 1、同一导购不管绑定哪个微信号，只要添加了该微信，就都不能再添加了
 * 2、同一终端其他导购，相同导购微信已添加该客户，同样不能再添加了
 * 
 * 返回字段客户微信noWx说明：
 * 1、此字段对应微信app数据库存里的username字段
 * 2、如果客户是中控微信的好友，该字段将客户微信真实的username
 * 3、如果客户不是中控微信的好友（不包括客户将中控微信从好友列表中删除，即客户单向删除），该字段将返回以@stranger结尾的username，如：v1_90dc6fe8176a7753211f776f14b87363b92f018680f14d4e91cae823428c12a2@stranger
 * 4、该字段为空时，表示没有查到客户微信基本信息、获取失败、或者客户设置了隐私
 *   
 * @Company: 扬恩科技有限公司
 * @author 曾垂瑜
 *   
 * CreateDate: 2017年10月26日
 */
@Service
public class FindWxInfoReturnService implements IService<FindWxInfoReturn> {

	private static Logger logger = LoggerFactory.getLogger(FindWxInfoService.class);
	
	@Resource
	private ServerManager serverManager;
	@Resource
	private IGuidMemberService guidMemberService;
	@Resource
	private IPersonMemberService personMemberService;
	@Resource
	private IPersonMemberBaseService personMemberBaseService;
	@Resource
	private IAddFriendsService addFriendsService;
	@Resource
	RedisCache redisCache;//记录并区分添加方式
	@Resource 
	private IContactsService contactsService;

	@Override
	public void process(FindWxInfoReturn request, Message message, Channel channel) {
		logger.debug("收到中控微信基本信息返回FindWxInfoReturn={}",request.toJson());
		IoSession addFriendZkSession = ChannelUtils.getSession(channel);//用于添加好友的中控
		String zkWxNo = "zkWxNo";//中控微信号
		if(addFriendZkSession!=null && addFriendZkSession.isLogin()) {
			zkWxNo = addFriendZkSession.getNoWxGm();//设置商户中控的微信号
		}
		//通过缓存的Key判断是扫码查询还是主动搜索查询
		String addFriendKey = zkWxNo + request.getWxQrCode();//商户微信号+QrCode
		if(redisCache.hget(addFriendKey, KeyConstant.API_SEARCH_ADD_PREFIX)!=null) {//API接口主动搜索添加
			logger.info(addFriendKey+",接口进入手机号主动搜索并添加好友FindWxInfoReturn响应");
			redisCache.hdel(addFriendKey,KeyConstant.API_SEARCH_ADD_PREFIX);//处理完毕清除缓存
			
			findAndAddWxFriendByPhone(request,message,channel);
			
		}else if(redisCache.hget(addFriendKey,KeyConstant.OMS_SEARCH_ADD_PREFIX)!=null) {//OMS 通过导购助手搜索
			logger.info(request.getWxQrCode()+",OMS_wxQrCode进入主动搜索微信基本信息FindWxInfoReturn响应");
			redisCache.hset(addFriendKey
				,KeyConstant.OMS_SEARCH_ADD_RET_PREFIX,JsonUtils.jsonFromObject_AllToString(request));//OMS调用查询微信基本信息,搜索结果
		}else if(redisCache.hget(addFriendKey,KeyConstant.OMS_FRIEND_LIST_SEARCH_ADD_PREFIX)!=null) {//OMS 好友列表点击搜索添加
			FindWxInfoRequestDto dto3 = (FindWxInfoRequestDto)JsonUtils.objectFromJson(redisCache.hget(addFriendKey,KeyConstant.OMS_FRIEND_LIST_SEARCH_ADD_PREFIX)
					,FindWxInfoRequestDto.class);
			logger.debug("OMS_FRIEND_LIST_SEARCH_ADD_PREFIX FindWxInfoReturn dto3={}", dto3.toString());
			logger.info(request.getWxQrCode()+",OMS_wxQrCode添加好友列表中进入主动搜索并添加好友FindWxInfoReturn响应");
			if(!updateFindResult(request)) {//好友列表搜索失败
				return;
			}
			ScanAddFriendRequestDto scanAddFriendRequestDto = new ScanAddFriendRequestDto();
			scanAddFriendRequestDto.setNoWxGm(ChannelUtils.getSession(channel).getNoWxGm());//中控微信号
			scanAddFriendRequestDto.setScanId(request.getScanId());
			scanAddFriendRequestDto.setAddCode(dto3.getAddCode());//add_friends表主键
			scanAddFriendRequestDto.setNoWx(request.getNoWx());//微信号
			scanAddFriendRequestDto.setAlias(request.getAlias());
			scanAddFriendRequestDto.setNickRemark(request.getNickNameWx());
			contactsService.sendScanAddNewFriendMessage(scanAddFriendRequestDto);//向中控发送添加请求
			redisCache.hdel(addFriendKey, KeyConstant.OMS_FRIEND_LIST_SEARCH_ADD_PREFIX);//处理完毕清除缓存
			
		}else if(redisCache.hget(addFriendKey,KeyConstant.OMS_PMB_LIST_SEARCH_ADD_PREFIX)!=null) {//OMS 客户列表点击搜索添加
			logger.info(request.getWxQrCode()+",OMS_PMB_wxQrCode客户列表中点击进入主动搜索并添加好友FindWxInfoReturn响应");
			FindWxInfoRequestDto dto4 = (FindWxInfoRequestDto)JsonUtils.objectFromJson(redisCache.hget(addFriendKey,KeyConstant.OMS_PMB_LIST_SEARCH_ADD_PREFIX)
					,FindWxInfoRequestDto.class);
			// 微信客户已存在对应客户基础信息，不能添加好友
			FindPersonMemberBaseReturn memberBaseReturn = personMemberBaseService.findMemberBaseByNoWxOrAlias(request.getNoWx(), request.getAlias());
			if(memberBaseReturn != null) {
				logger.warn("微信客户{}已存在对应客户基础信息，不能添加好友：{}", dto4, memberBaseReturn);
				return;
			}
			ScanAddFriendRequestDto scanAddFriendRequestDto = new ScanAddFriendRequestDto();
			scanAddFriendRequestDto.setNoWxGm(ChannelUtils.getSession(channel).getNoWxGm());//中控微信号
			scanAddFriendRequestDto.setScanId(request.getScanId());
			scanAddFriendRequestDto.setAddCode(dto4.getAddCode());//pmb表主键+","+导购编号,带前缀
			scanAddFriendRequestDto.setNoWx(request.getNoWx());//微信号
			scanAddFriendRequestDto.setAlias(request.getAlias());
			scanAddFriendRequestDto.setNickRemark(request.getNickNameWx());
			contactsService.sendScanAddNewFriendMessage(scanAddFriendRequestDto);//向中控发送添加请求
			redisCache.hdel(addFriendKey,KeyConstant.OMS_PMB_LIST_SEARCH_ADD_PREFIX);//处理完毕清除缓存
			
		}else { //保留原有逻辑
			IoSession gmSession = ChannelUtils.getSession(request.getMemberNoGm());	// 获取导购手机对应的session
			
			// 搜索失败
			if(!request.isResult()) {
				logger.info("中控客户端获取客户微信基本信息失败:{}", request.getWxQrCode());
				ResponseCode responseCode = ResponseUtils.getErrorResponseByBusinessCode(com.lj.business.supcon.common.ErrorCode.FIND_WX_INFO_ERROR);
				request.setCode(responseCode.getCode());
				request.setMessage(StringUtils.toString(request.getMessage(), responseCode.getMessage()));
				send(request, message, gmSession);
				return;
			}
			
			// 中控客户端没有返回客户信息，原因：没有查到客户微信基本信息、获取失败、或者客户设置了隐私
			if(StringUtils.isEmpty(request.getNoWx())) {
				logger.info("中控客户端没有返回客户微信基本信息[{}]", request.getWxQrCode());
				request.setResult(Boolean.FALSE);
				String code = null;
				if(request.getWxQrCode().indexOf("u.wechat.com") != -1 || request.getWxQrCode().indexOf("weixin.qq.com") != -1) {	// 扫二维码搜索微信，返回设置了隐私的提示
					code = com.lj.business.supcon.common.ErrorCode.FIND_WX_INFO_ERROR_BY_PRIVACY;
				} else {	// 其他方式搜索微信，返回微信没有找到的提示
					code = com.lj.business.supcon.common.ErrorCode.FIND_WX_INFO_NOT_FOUND;
				}
				ResponseCode responseCode = ResponseUtils.getErrorResponseByBusinessCode(code);
				request.setCode(responseCode.getCode());
				request.setMessage(responseCode.getMessage());
				send(request, message, gmSession);
				return;
			} else if(request.getNoWx().endsWith(CommonConstant.WX_STRANGER_USERNAME_SUFFIX)) {	// 不是中控微信的好友，将以@stranger结尾
				/**
				 * 客户微信
				 * 1、此字段对应微信app数据库存里的username字段
				 * 2、如果客户是中控微信的好友，该字段将客户微信真实的username
				 * 3、如果客户不是中控微信的好友（不包括客户将中控微信从好友列表中删除，即客户单向删除），该字段将返回以@stranger结尾的username，如：v1_90dc6fe8176a7753211f776f14b87363b92f018680f14d4e91cae823428c12a2@stranger
				 * 4、该字段为空时，表示没有查到客户微信基本信息、获取失败、或者客户设置了隐私
				 */
				send(request, message, gmSession);
				return;
			}
			
			// 根据客户微信号和别名查询客户基础表信息
			FindPersonMemberBaseReturn findPersonMemberBaseReturn = null;
			try { // 此处要根据微信和别名同时查询，因为查询客户微信返回的微信号有可能是错误的（如真实微信是l-d-q123456，返回时为ldq123456）
				findPersonMemberBaseReturn = personMemberBaseService.findMemberBaseByNoWxOrAlias(request.getNoWx(), request.getAlias());
			} catch(TsfaServiceException e) {
				// 客户基础表不存在，直接返回
				if(ErrorCode.PERSON_MEMBER_BASE_NOT_EXIST_ERROR.equals(e.getExceptionCode())) {
					send(request, message, gmSession);
					return;
				}
				throw e;
			}
			// 客户基础表不存在，直接返回
			if(findPersonMemberBaseReturn == null) {
				send(request, message, gmSession);
				return;
			}
			
			request.setMemberNo(findPersonMemberBaseReturn.getMemberNo());
			if(StringUtils.isNotEmpty(findPersonMemberBaseReturn.getNickNameRemarkLocal())) {
				request.setMemberName(findPersonMemberBaseReturn.getNickNameRemarkLocal());
			} else if(StringUtils.isNotEmpty(findPersonMemberBaseReturn.getNickNameWx())) {
				request.setMemberName(findPersonMemberBaseReturn.getNickNameWx());
			} else {
				request.setMemberName(findPersonMemberBaseReturn.getMemberName());
			}
			
			send(request, message, gmSession);
		}
	}
	
	private void send(FindWxInfoReturn request, Message message, IoSession session) {
		if(StringUtils.isNotEmpty(request.getNoWx())) {
			// 查询是否为绑定微信的未分配客户
			FindAddFriendsReturn notClaimedFriend = addFriendsService.findNotClaimedFriends(session.getNoWxGm(), request.getNoWx());
			if(notClaimedFriend != null) {	// 已是终端微信好友，但未认领
				request.setGmMbrFlag(Boolean.TRUE);
				request.setMessage("");
			}
		}
		
		message.setBody(request.toJson());
		
		if(session != null && session.isLogin()) {	// 连接并登录，则向导购手机客户端返回客户微信基本信息
			serverManager.sendMessageNoCache(session.getChannel(), message);
			logger.info("已向导购客户端返回客户微信基本信息：" + message);
		} else {// 导购未登录、离线
			//serverManager.cacheMsg(request.getMemberNoGm(), message);
			logger.info("导购未登录客户端，不保存离线消息：" + message);
		}
	}
	
	/**
	 * 方法说明：修改添加好友时,查询微信基本信息结果失败的状态处理
	 * @param request
	 * @return false 表示搜索失败
	 * @author 李端强 CreateDate: 2018年1月25日
	 */
	private boolean updateFindResult(FindWxInfoReturn request) {
		// 搜索失败
		if(!request.isResult()) {
			logger.info("updateFindResult,中控客户端获取客户微信基本信息失败:{}", request.getWxQrCode());
			ResponseCode responseCode = ResponseUtils.getErrorResponseByBusinessCode(com.lj.business.supcon.common.ErrorCode.FIND_WX_INFO_ERROR);
			String id = request.getAddCode();
			if(!StringUtils.isEmpty(id)) {//存在主键
				UpdateAddFriends updateAddFriends = new UpdateAddFriends();
				updateAddFriends.setCode(id);
				updateAddFriends.setRemark4(responseCode.getMessage());//不能添加的原因
				addFriendsService.updateAddFriends(updateAddFriends);//根据主键修改
			}
			return false;
		}
		// 中控客户端没有返回客户信息，原因：没有查到客户微信基本信息、获取失败、或者客户设置了隐私
		if(StringUtils.isEmpty(request.getNoWx())) {
			logger.info("updateFindResult,中控客户端没有返回客户微信基本信息[{}]", request.getWxQrCode());
			request.setResult(Boolean.FALSE);
			String code = null;
			if(request.getWxQrCode().indexOf("u.wechat.com") != -1 || request.getWxQrCode().indexOf("weixin.qq.com") != -1) {	// 扫二维码搜索微信，返回设置了隐私的提示
				code = com.lj.business.supcon.common.ErrorCode.FIND_WX_INFO_ERROR_BY_PRIVACY;
			} else {	// 其他方式搜索微信，返回微信没有找到的提示
				code = com.lj.business.supcon.common.ErrorCode.FIND_WX_INFO_NOT_FOUND;
			}
			ResponseCode responseCode = ResponseUtils.getErrorResponseByBusinessCode(code);
			String id = request.getAddCode();
			if(!StringUtils.isEmpty(id)) {//存在主键
				UpdateAddFriends updateAddFriends = new UpdateAddFriends();
				updateAddFriends.setCode(id);
				updateAddFriends.setRemark4(responseCode.getMessage());//不能添加的原因
				addFriendsService.updateAddFriends(updateAddFriends);//根据主键修改
			}
			return false;
		}
		return true;
	}
	
	/**
	 * 方法说明：独立处理主动搜索手机号查询微信信息,并添加好友
	 * @author 李端强 CreateDate: 2018年1月4日
	 */
	private void findAndAddWxFriendByPhone(FindWxInfoReturn request, Message message, Channel channel) {
		// 中控客户端没有返回客户信息，原因：获取失败、或者客户设置了隐私
		if(StringUtils.isEmpty(request.getNoWx())) {
			logger.info("findAndAddWxFriendByPhone中控客户端没有返回客户微信基本信息[{}]", request.getWxQrCode());
			request.setResult(Boolean.FALSE);
			ResponseCode responseCode = ResponseUtils.getErrorResponseByBusinessCode(com.lj.business.supcon.common.ErrorCode.FIND_WX_INFO_ERROR_BY_PRIVACY);
			request.setCode(responseCode.getCode());
			request.setMessage(responseCode.getMessage());
			return;
		}
		// 根据客户微信号查询客户基础表信息
		FindPersonMemberBase findPersonMemberBase = new FindPersonMemberBase();
		findPersonMemberBase.setNoWx(request.getNoWx());
		FindPersonMemberBaseReturn findPersonMemberBaseReturn = null;
		try {
			findPersonMemberBaseReturn = personMemberBaseService.findPersonMemberBase(findPersonMemberBase);
		} catch(TsfaServiceException e) {
			// 客户基础表不存在，直接返回
			if(ErrorCode.PERSON_MEMBER_BASE_NOT_EXIST_ERROR.equals(e.getExceptionCode())) {
				addFriend(request,channel);//发送添加好友
				return;
			}
			throw e;
		}
		// 客户基础表不存在，直接返回
		if(findPersonMemberBaseReturn == null) {
			addFriend(request,channel);//发送添加好友
			return;
		}
		
		IoSession zkSession = ChannelUtils.getSession(channel);//中控登录信息
		
		// 客户基础表存在，则查询同一终端是否已添加该客户
		FindGuidByShopAndMember findGuidByShopAndMember = new FindGuidByShopAndMember();
		findGuidByShopAndMember.setShopWx(zkSession.getNoWxGm());
		findGuidByShopAndMember.setMemberNo(findPersonMemberBaseReturn.getMemberNo());
		List<FindGuidByShopAndMemberReturn> guidList = guidMemberService.findGuidByShopAndMember(findGuidByShopAndMember);
		// 同一终端没有添加该客户
		if(guidList == null || guidList.isEmpty()) {
			addFriend(request,channel);//发送添加好友
			return;
		}
	}
	
	/**
	 *
	 * 方法说明：向中控端发送添加好友请求
	 * @param request
	 * @param channel
	 * @author 李端强 CreateDate: 2018年1月4日
	 */
	private void addFriend(FindWxInfoReturn request, Channel channel) {
		IoSession gmSession = ChannelUtils.getSession(channel);
		//处理ScanAddFriend逻辑
		// 保存添加微信好友记录
		AddAddFriends addAddFriends = new AddAddFriends();
		addAddFriends.setMemberNoGm(request.getMemberNoGm());//已指定导购编号
		//查询导购信息
		FindGuidMember findGuidMember = new FindGuidMember();
		findGuidMember.setMemberNo(request.getMemberNoGm());//导购编号,查询WX号
		
		addAddFriends.setNoWxGm(gmSession.getNoWxGm());//导购微信号
		addAddFriends.setWxQrCode(request.getWxQrCode());
		addAddFriends.setNoWx(request.getNoWx());//客户微信
		addAddFriends.setAlias(request.getAlias());
		addAddFriends.setNickNameWx(request.getNickNameWx());
		//addAddFriends.setNickRemark(request.getNickRemark());//可为空	
		addAddFriends.setHeadAddress(request.getHeadAddress());
		addAddFriends.setSex(request.getSex());
		//查询客户基本信息
		addAddFriends.setMobile(request.getWxQrCode());//可为空,直接通过手机号搜索添加
		//addAddFriends.setValidation(request.getValidation());//可为空
		addAddFriends.setWxAddType(5);//主动搜索添加时,增加添加渠道
		try {
			AddAddFriendsReturn addAddFriendsReturn = addFriendsService.addAddFriends(addAddFriends);
			logger.info("addFriend已保存添加微信好友记录：[{}]", addAddFriendsReturn);
		} catch(Exception e) {
			logger.error("addFriend保存添加微信好友记录异常", e);
			return;
		}
		//组装添加好友报文
		ScanAddFriendRequest scanAddFriendRequest = new ScanAddFriendRequest();
		scanAddFriendRequest.setScanId(request.getScanId());
		scanAddFriendRequest.setMemberNoGm(request.getMemberNoGm());//导购编号
		scanAddFriendRequest.setNoWxGm(gmSession.getNoWxGm());//导购微信号
		scanAddFriendRequest.setWxQrCode(request.getWxQrCode());//二维码 或手机号
		scanAddFriendRequest.setAlias(request.getAlias());
		scanAddFriendRequest.setNickNameWx(request.getNickNameWx());
		scanAddFriendRequest.setHeadAddress(request.getHeadAddress());
		scanAddFriendRequest.setSex(request.getSex());
		scanAddFriendRequest.setNoWx(request.getNoWx());//客户微信号
		String msgId = GUID.generateByUUID();
		Message addFriendRequestMessage = new Message(MessageCode.ScanAddFriendRequest, msgId, scanAddFriendRequest.toJson()); 
		
		// 请求中控客户端
		IoSession zkSession = ChannelUtils.getSession(gmSession.getNoWxGm());	// 获取对应中控客户端session
		if (zkSession == null || !zkSession.isLogin()) {	// 中控客户端未登录，返回添加失败信息
			logger.error("导购[{}]对应中控客户端未登录，请求添加客户微信失败", request.getMemberNoGm());
		} else {	// 中控客户端已连接并登录，则发送信息
			serverManager.sendMessageAndCache(gmSession.getNoWxGm(), zkSession.getChannel(), addFriendRequestMessage);
			logger.info("addFriend已向中控客户端发送请求添加客户微信消息：" + addFriendRequestMessage);
		}
	}
}
