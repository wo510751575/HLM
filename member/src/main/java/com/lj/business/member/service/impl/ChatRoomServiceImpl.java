package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IChatRoomDao;
import com.lj.business.member.dao.IChatRoomMemberDao;
import com.lj.business.member.dao.IChatRoomPmDao;
import com.lj.business.member.domain.ChatRoom;
import com.lj.business.member.domain.ChatRoomMember;
import com.lj.business.member.domain.ChatRoomPm;
import com.lj.business.member.dto.FindPersonMemberReturn;
import com.lj.business.member.dto.chatroom.AddChatRoom;
import com.lj.business.member.dto.chatroom.AddChatRoomReturn;
import com.lj.business.member.dto.chatroom.CreateChatRoom;
import com.lj.business.member.dto.chatroom.DelChatRoom;
import com.lj.business.member.dto.chatroom.DelChatRoomReturn;
import com.lj.business.member.dto.chatroom.FindChatRoom;
import com.lj.business.member.dto.chatroom.FindChatRoomPage;
import com.lj.business.member.dto.chatroom.FindChatRoomPageReturn;
import com.lj.business.member.dto.chatroom.FindChatRoomReturn;
import com.lj.business.member.dto.chatroom.SyncChatRoom;
import com.lj.business.member.dto.chatroom.SyncChatRoomFromZk;
import com.lj.business.member.dto.chatroom.SyncChatRoomMember;
import com.lj.business.member.dto.chatroom.UpdateChatRoom;
import com.lj.business.member.dto.chatroom.UpdateChatRoomReturn;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopReturn;
import com.lj.business.member.dto.shopterminal.FindShopTerminalReturn;
import com.lj.business.member.service.IChatRoomService;
import com.lj.business.member.service.IGmAssistantShopService;
import com.lj.business.member.service.IPersonMemberService;
import com.lj.business.member.service.IShopTerminalService;
import com.lj.business.member.utils.JsonDateValueProcessor;
import com.lj.business.supcon.common.Constants;
import com.lj.business.supcon.dto.chatroom.SyncChatRoomMessage;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IWxChatRoomService;
import com.lj.business.utils.BeanUtils;
import com.lj.business.weixin.emus.ChatRoomStatus;
import com.lj.distributecache.RedisCache;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

/**
 * 
 * 
 * 类说明：群信息表实现类
 * 
 * 
 * <p>
 * 详细描述：
 * 
 * @Company: 深圳市扬恩科技
 * @author 曾垂瑜
 * 
 *         CreateDate: 2018年03月22日
 */
@Service
public class ChatRoomServiceImpl implements IChatRoomService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ChatRoomServiceImpl.class);

	@Resource
	private IChatRoomDao chatRoomDao;
	@Resource
	private IChatRoomPmDao chatRoomPmDao;
	@Resource
	private IChatRoomMemberDao chatRoomMemberDao;
	@Autowired
	private IPersonMemberService personMemberService;
	@Resource
	private IShopTerminalService shopTerminalService;
	@Resource
	private RedisCache redisCache;	
	@Autowired
	private IGmAssistantShopService gmAssistantShopService;
    @Autowired 
	ICommonService commonService;
    
	@Override
	public AddChatRoomReturn addChatRoom(AddChatRoom addChatRoom) throws TsfaServiceException {
		logger.debug("addChatRoom(AddChatRoom addChatRoom={}) - start", addChatRoom);

		AssertUtils.notNull(addChatRoom);
		try {
			ChatRoom chatRoom = new ChatRoom();
			// add数据录入
			chatRoom.setCode(GUID.generateByUUID());
			chatRoom.setNoWxZk(addChatRoom.getNoWxZk());
			chatRoom.setChatRoomName(addChatRoom.getChatRoomName());
			chatRoom.setRoomNickName(addChatRoom.getRoomNickName());
			chatRoom.setRoomOwner(addChatRoom.getRoomOwner());
			chatRoom.setHeadUrl(addChatRoom.getHeadUrl());
			chatRoom.setStatus(addChatRoom.getStatus());
//			chatRoom.setShopNo(addChatRoom.getShopNo());
//			chatRoom.setShopName(addChatRoom.getShopName());
			chatRoom.setMerchantNo(addChatRoom.getMerchantNo());
			chatRoom.setMerchantName(addChatRoom.getMerchantName());
			chatRoom.setVersion(System.currentTimeMillis());
			chatRoom.setCreateId(addChatRoom.getCreateId());
			chatRoom.setCreateDate(new Date());
			chatRoom.setRemark(addChatRoom.getRemark());
			chatRoom.setRemark2(addChatRoom.getRemark2());
			chatRoom.setRemark3(addChatRoom.getRemark3());
			chatRoom.setRemark4(addChatRoom.getRemark4());

			if (StringUtils.isEmpty(addChatRoom.getMemberNoGm())) {
				// 1、客户是群主。客户是哪个导购的，系统自动将此群归属该导购。
				FindPersonMemberReturn findPersonMemberReturn = personMemberService.findPersonMemberByNoWxAndShopWx(addChatRoom.getRoomOwner(), addChatRoom.getNoWxZk());
				if (null != findPersonMemberReturn) {
					chatRoom.setMemberNoGm(findPersonMemberReturn.getMemberNoGm());
					chatRoom.setMemberNameGm(findPersonMemberReturn.getMemberNameGm());
				}
				
			} else {
				chatRoom.setMemberNoGm(addChatRoom.getMemberNoGm());
				chatRoom.setMemberNameGm(addChatRoom.getMemberNameGm());
			}

			chatRoomDao.insertSelective(chatRoom);
			AddChatRoomReturn addChatRoomReturn = new AddChatRoomReturn();

			logger.debug("addChatRoom(AddChatRoom) - end - return value={}", addChatRoomReturn);
			return addChatRoomReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增群信息表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_ADD_ERROR, "新增群信息表信息错误！", e);
		}
	}

	@Override
	public DelChatRoomReturn delChatRoom(DelChatRoom delChatRoom) throws TsfaServiceException {
		logger.debug("delChatRoom(DelChatRoom delChatRoom={}) - start", delChatRoom);

		AssertUtils.notNull(delChatRoom);
		AssertUtils.notNull(delChatRoom.getCode(), "Code不能为空！");
		try {
			chatRoomDao.deleteByPrimaryKey(delChatRoom.getCode());
			DelChatRoomReturn delChatRoomReturn = new DelChatRoomReturn();

			logger.debug("delChatRoom(DelChatRoom) - end - return value={}", delChatRoomReturn); 
			return delChatRoomReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("删除群信息表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_DEL_ERROR, "删除群信息表信息错误！", e);
		}
	}

	@Override
	public UpdateChatRoomReturn updateChatRoom(UpdateChatRoom updateChatRoom) throws TsfaServiceException {
		logger.debug("updateChatRoom(UpdateChatRoom updateChatRoom={}) - start", updateChatRoom); 

		AssertUtils.notNull(updateChatRoom);
		AssertUtils.notNullAndEmpty(updateChatRoom.getCode(), "Code不能为空");
		try {
			ChatRoom chatRoom = new ChatRoom();
			// update数据录入
			chatRoom.setCode(updateChatRoom.getCode());
			chatRoom.setNoWxZk(updateChatRoom.getNoWxZk());
			chatRoom.setChatRoomName(updateChatRoom.getChatRoomName());
			chatRoom.setRoomNickName(updateChatRoom.getRoomNickName());
			chatRoom.setRoomOwner(updateChatRoom.getRoomOwner());
			chatRoom.setHeadUrl(updateChatRoom.getHeadUrl());
			chatRoom.setStatus(updateChatRoom.getStatus());
//			chatRoom.setShopNo(updateChatRoom.getShopNo());
//			chatRoom.setShopName(updateChatRoom.getShopName());
			chatRoom.setMerchantNo(updateChatRoom.getMerchantNo());
			chatRoom.setMerchantName(updateChatRoom.getMerchantName());
			chatRoom.setVersion(System.currentTimeMillis());
			chatRoom.setRemark(updateChatRoom.getRemark());
			chatRoom.setRemark2(updateChatRoom.getRemark2());
			chatRoom.setRemark3(updateChatRoom.getRemark3());
			chatRoom.setRemark4(updateChatRoom.getRemark4());
			chatRoom.setMemberNoGm(updateChatRoom.getMemberNoGm());
			chatRoom.setMemberNameGm(updateChatRoom.getMemberNameGm());
			chatRoom.setNoDisturb(updateChatRoom.getNoDisturb());
			chatRoom.setQRCode(updateChatRoom.getQRCode());
			AssertUtils.notUpdateMoreThanOne(chatRoomDao.updateByPrimaryKeySelective(chatRoom));
			UpdateChatRoomReturn updateChatRoomReturn = new UpdateChatRoomReturn();

			logger.debug("updateChatRoom(UpdateChatRoom) - end - return value={}", updateChatRoomReturn); 
			return updateChatRoomReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("群信息表信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_UPDATE_ERROR, "群信息表信息更新信息错误！", e);
		}
	}

	
	
	@Override
	public void updateSetUpUser(ChatRoom chatRoom) throws TsfaServiceException {
		logger.debug("updateChatRoom(UpdateChatRoom updateChatRoom={}) - start", chatRoom); 

		AssertUtils.notNull(chatRoom);
		AssertUtils.notNullAndEmpty(chatRoom.getCode(), "Code不能为空");
		try {
	
			AssertUtils.notUpdateMoreThanOne(chatRoomDao.updateSetUpUser(chatRoom));

			logger.debug("updateChatRoom(UpdateChatRoom) - end - return value={}", chatRoom); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("群信息表信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_UPDATE_ERROR, "群信息表信息更新信息错误！", e);
		}
	}
	
	
	@Override
	public void updateCancleSetUpUser(ChatRoom chatRoom) throws TsfaServiceException {
		logger.debug("updateChatRoom(UpdateChatRoom updateChatRoom={}) - start", chatRoom); 

		AssertUtils.notNull(chatRoom);
		AssertUtils.notNullAndEmpty(chatRoom.getCode(), "Code不能为空");
		try {
	
			AssertUtils.notUpdateMoreThanOne(chatRoomDao.updateCancleSetUpUser(chatRoom));

			logger.debug("updateChatRoom(UpdateChatRoom) - end - return value={}", chatRoom); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("群信息表信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_UPDATE_ERROR, "群信息表信息更新信息错误！", e);
		}
	}
	
	
	@Override
	public FindChatRoomReturn findChatRoom(FindChatRoom findChatRoom) throws TsfaServiceException {
		logger.debug("findChatRoom(FindChatRoom findChatRoom={}) - start", findChatRoom); 

		AssertUtils.notNull(findChatRoom);
		AssertUtils.notAllNullAndEmpty(findChatRoom.getCode(), "Code不能为空");
		try {
			ChatRoom chatRoom = chatRoomDao.selectByPrimaryKey(findChatRoom.getCode());
			if (chatRoom == null) {
//				throw new TsfaServiceException(ErrorCode.CHAT_ROOM_NOT_EXIST_ERROR, "群信息表信息不存在");
				return null;
			}
			FindChatRoomReturn findChatRoomReturn = new FindChatRoomReturn();
			// find数据录入
			findChatRoomReturn.setCode(chatRoom.getCode());
			findChatRoomReturn.setNoWxZk(chatRoom.getNoWxZk());
			findChatRoomReturn.setChatRoomName(chatRoom.getChatRoomName());
			findChatRoomReturn.setRoomNickName(chatRoom.getRoomNickName());
			findChatRoomReturn.setRoomOwner(chatRoom.getRoomOwner());
			findChatRoomReturn.setHeadUrl(chatRoom.getHeadUrl());
			findChatRoomReturn.setStatus(chatRoom.getStatus());
//			findChatRoomReturn.setShopNo(chatRoom.getShopNo());
//			findChatRoomReturn.setShopName(chatRoom.getShopName());
			findChatRoomReturn.setMerchantNo(chatRoom.getMerchantNo());
			findChatRoomReturn.setMerchantName(chatRoom.getMerchantName());
			findChatRoomReturn.setVersion(chatRoom.getVersion());
			findChatRoomReturn.setCreateId(chatRoom.getCreateId());
			findChatRoomReturn.setCreateDate(chatRoom.getCreateDate());
			findChatRoomReturn.setRemark(chatRoom.getRemark());
			findChatRoomReturn.setRemark2(chatRoom.getRemark2());
			findChatRoomReturn.setRemark3(chatRoom.getRemark3());
			findChatRoomReturn.setRemark4(chatRoom.getRemark4());
			findChatRoomReturn.setMemberNoGm(chatRoom.getMemberNoGm());
			findChatRoomReturn.setMemberNameGm(chatRoom.getMemberNameGm());
			findChatRoomReturn.setNoDisturb(chatRoom.getNoDisturb());
			logger.debug("findChatRoom(FindChatRoom) - end - return value={}", findChatRoomReturn); //$NON-NLS-1$
			return findChatRoomReturn;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找群信息表信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_FIND_ERROR, "查找群信息表信息信息错误！", e);
		}
	}

	@Override
	public FindChatRoomReturn findChatRoomBySelective(FindChatRoom findChatRoom) throws TsfaServiceException {
		logger.debug("findChatRoomBySelective(FindChatRoom findChatRoom={}) - start", findChatRoom); 

		AssertUtils.notNull(findChatRoom);
		try {
			ChatRoom chatRoom = null;
			if (StringUtils.isNotEmpty(findChatRoom.getCode())) {
				chatRoom = chatRoomDao.selectByPrimaryKey(findChatRoom.getCode());
			} else {
				chatRoom = chatRoomDao.selectBySelective(findChatRoom);
			}
			FindChatRoomReturn findChatRoomReturn = null;
			if (chatRoom != null) {
				findChatRoomReturn = new FindChatRoomReturn();
				// find数据录入
				findChatRoomReturn.setQRCode(chatRoom.getQRCode());
				findChatRoomReturn.setCode(chatRoom.getCode());
				findChatRoomReturn.setNoWxZk(chatRoom.getNoWxZk());
				findChatRoomReturn.setChatRoomName(chatRoom.getChatRoomName());
				findChatRoomReturn.setRoomNickName(chatRoom.getRoomNickName());
				findChatRoomReturn.setRoomOwner(chatRoom.getRoomOwner());
				findChatRoomReturn.setHeadUrl(chatRoom.getHeadUrl());
				findChatRoomReturn.setStatus(chatRoom.getStatus());
//				findChatRoomReturn.setShopNo(chatRoom.getShopNo());
//				findChatRoomReturn.setShopName(chatRoom.getShopName());
				findChatRoomReturn.setMerchantNo(chatRoom.getMerchantNo());
				findChatRoomReturn.setMerchantName(chatRoom.getMerchantName());
				findChatRoomReturn.setVersion(chatRoom.getVersion());
				findChatRoomReturn.setCreateId(chatRoom.getCreateId());
				findChatRoomReturn.setCreateDate(chatRoom.getCreateDate());
				findChatRoomReturn.setRemark(chatRoom.getRemark());
				findChatRoomReturn.setRemark2(chatRoom.getRemark2());
				findChatRoomReturn.setRemark3(chatRoom.getRemark3());
				findChatRoomReturn.setRemark4(chatRoom.getRemark4());
				findChatRoomReturn.setMemberNoGm(chatRoom.getMemberNoGm());
				findChatRoomReturn.setMemberNameGm(chatRoom.getMemberNameGm());
				findChatRoomReturn.setNoDisturb(chatRoom.getNoDisturb());
			}

			logger.debug("findChatRoomBySelective(FindChatRoom) - end - return value={}", findChatRoomReturn); 
			return findChatRoomReturn;
		} catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找群信息表信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_FIND_ERROR, "查找群信息表信息信息错误！", e);
		}
	}

	@Override
	public Page<FindChatRoomPageReturn> findChatRoomPage(FindChatRoomPage findChatRoomPage)
			throws TsfaServiceException {
		logger.debug("findChatRoomPage(FindChatRoomPage findChatRoomPage={}) - start", findChatRoomPage);

		AssertUtils.notNull(findChatRoomPage);
		List<FindChatRoomPageReturn> returnList = null;
		int count = 0;
		try {
			count = chatRoomDao.findChatRoomPageCount(findChatRoomPage);
			if (count > 0) {
				returnList = chatRoomDao.findChatRoomPage(findChatRoomPage);
			}
		} catch (Exception e) {
			logger.error("群信息表信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_FIND_PAGE_ERROR, "群信息表信息不存在错误.！", e);
		}
		Page<FindChatRoomPageReturn> returnPage = new Page<FindChatRoomPageReturn>(returnList, count, findChatRoomPage);

		logger.debug("findChatRoomPage(FindChatRoomPage) - end - return value={}", returnPage);
		return returnPage;
	}

	/**
	 * 
	 *
	 * 方法说明：中控上传群信息
	 *
	 * @param paramJson
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年9月8日
	 *
	 */
	@Override
	public void syncChatRoomFromZk(String paramJson) throws TsfaServiceException {
		logger.debug("syncChatRoomFromZk(String paramJson={}) - start", paramJson); 

		if (StringUtils.isEmpty(paramJson)) {
			return;
		}

		// 将json数据转为对象
		@SuppressWarnings("rawtypes")
		Map<String, Class> clazzMap = new HashMap<String, Class>();
		clazzMap.put("roomList", SyncChatRoom.class);
		clazzMap.put("memberList", SyncChatRoomMember.class);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setClassMap(clazzMap);
		jsonConfig.setRootClass(SyncChatRoomFromZk.class);
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject ja = JSONObject.fromObject(paramJson, jsonConfig);
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss" }));
		SyncChatRoomFromZk syncChatRoomFromZk = (SyncChatRoomFromZk) JSONObject.toBean(ja, SyncChatRoomFromZk.class,clazzMap);

		if (syncChatRoomFromZk.getRoomList() != null && !syncChatRoomFromZk.getRoomList().isEmpty()) {
			/**
			 * 获取终端信息
			 */
			AssertUtils.notNullAndEmpty(syncChatRoomFromZk.getNoWxZk(), "终端微信不存在！");
			FindShopTerminalReturn findShopTerminalReturn= shopTerminalService.findShopTerminalByWx(syncChatRoomFromZk.getNoWxZk());
			AssertUtils.notNull(findShopTerminalReturn, "终端不存在！");
			
			FindChatRoom findChatRoom = new FindChatRoom();
			for (SyncChatRoom syncChatRoom : syncChatRoomFromZk.getRoomList()) {
				try {
					// 查询群信息
					findChatRoom.setNoWxZk(syncChatRoomFromZk.getNoWxZk());
					findChatRoom.setChatRoomName(syncChatRoom.getChatRoomName());
					findChatRoom.setMerchantNo(findShopTerminalReturn.getMerchantNo());
					ChatRoom chatRoom = chatRoomDao.selectBySelective(findChatRoom);
					if (chatRoom == null) { // 微信群不存在，则新增
						this.createChatRoomInfoBySync(syncChatRoomFromZk, findShopTerminalReturn, syncChatRoom,ChatRoomStatus.Y.getCode());
					} else { // 存在则更新
						this.updateChatRoomInfoBySync(syncChatRoomFromZk, findShopTerminalReturn, syncChatRoom, chatRoom);
					}

					long nextSyncBeginTime = System.currentTimeMillis() + 1800000; // 下次同步时间：30分钟后
					redisCache.hset(Constants.SYNC_CHATROOM_CHCHE_KEY,syncChatRoomFromZk.getNoWxZk() + syncChatRoom.getChatRoomName(),String.valueOf(nextSyncBeginTime));
				} catch (Exception e) {
					logger.error("同步微信群信息失败" + syncChatRoom, e);
				}
			}
		} else {
			logger.debug("中控微信{}没有加入任何微信群", syncChatRoomFromZk.getNoWxZk());
		}

		logger.debug("syncChatRoomFromZk(String) - end"); 
	}

	/**
	 * 
	 *
	 * 方法说明：新增微信群
	 *
	 * @param syncChatRoomFromZk
	 * @param shop
	 * @param syncChatRoom
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年9月25日
	 *
	 */
	private String createChatRoomInfoBySync(SyncChatRoomFromZk syncChatRoomFromZk, FindShopTerminalReturn shop,
			SyncChatRoom syncChatRoom,Integer chatRoomStatus) throws IllegalAccessException, InvocationTargetException {
		// 创建微信群信息记录
		ChatRoom room = new ChatRoom();
		room.setCode(GUID.generateByUUID());
		if(syncChatRoom.getRoomNickName().length()>500) {
			syncChatRoom.setRoomNickName(syncChatRoom.getRoomNickName().substring(0, 500));
		}
		BeanUtils.copyPropertiesNull(room, syncChatRoom, true);
		room.setNoWxZk(syncChatRoomFromZk.getNoWxZk());
		room.setStatus(chatRoomStatus);
//		room.setShopNo(shop.getShopNo());
//		room.setShopName(shop.getShopName());
		room.setMerchantNo(shop.getMerchantNo());
		room.setMerchantName(shop.getMerchantName());
		room.setVersion(System.currentTimeMillis());
		room.setCreateDate(new Date());
		room.setPmCode(syncChatRoom.getPmCode());
		room.setPmName(syncChatRoom.getPmName());
		if (StringUtils.isEmpty(syncChatRoom.getMemberNoGm())) {
			// 1、客户是群主。客户是哪个导购的，系统自动将此群归属该导购。
			FindPersonMemberReturn findPersonMemberReturn = personMemberService.findPersonMemberByNoWxAndShopWx(room.getRoomOwner(), shop.getNoWx());
			if (null != findPersonMemberReturn) {
				room.setMemberNoGm(findPersonMemberReturn.getMemberNoGm());
				room.setMemberNameGm(findPersonMemberReturn.getMemberNameGm());
			}
			
		} else {
			room.setMemberNoGm(syncChatRoom.getMemberNoGm());
			FindGmAssistantShopReturn findGuidMemberReturn= gmAssistantShopService.findGmByWxAndNo(shop.getNoWx(), syncChatRoom.getMemberNoGm());
			if(null != findGuidMemberReturn) {
				room.setMemberNameGm(findGuidMemberReturn.getAssistantName());
			}
		}
		chatRoomDao.insertSelective(room);
		
		
		logger.info("已创建微信群信息记录：{}", room);

		// 新增群成员记录
		ChatRoomMember chatRoomMember = new ChatRoomMember();
		chatRoomMember.setRoomCode(room.getCode());
		chatRoomMember.setNoWxZk(room.getNoWxZk());
		chatRoomMember.setChatRoomName(room.getChatRoomName());
		chatRoomMember.setStatus(ChatRoomStatus.Y.getCode());
//		chatRoomMember.setShopNo(shop.getShopNo());
//		chatRoomMember.setShopName(shop.getShopName());
		chatRoomMember.setMerchantNo(shop.getMerchantNo());
		chatRoomMember.setMerchantName(shop.getMerchantName());
		chatRoomMember.setVersion(System.currentTimeMillis());
		chatRoomMember.setCreateDate(new Date());
		chatRoomMember.setMemberNoGm(room.getMemberNoGm());
		chatRoomMember.setMemberNameGm(room.getMemberNameGm());

		for (SyncChatRoomMember syncChatRoomMember : syncChatRoom.getMemberList()) {
			chatRoomMember.setCode(GUID.generateByUUID());
			chatRoomMember.setUserName(syncChatRoomMember.getUserName());
			chatRoomMember.setNickName(syncChatRoomMember.getNickName());
			chatRoomMember.setHeadUrl(syncChatRoomMember.getHeadUrl());
			// 获取群成员信息
			FindPersonMemberReturn findPersonMemberReturn= personMemberService.findPersonMemberByNoWxAndShopWx(syncChatRoomMember.getUserName(), room.getNoWxZk());
			if(null != findPersonMemberReturn) {
				chatRoomMember.setMemberNo(findPersonMemberReturn.getMemberNo());
				String memberName = StringUtils.isNotEmpty(findPersonMemberReturn.getNickNameRemarkWx())?findPersonMemberReturn.getNickNameRemarkWx():findPersonMemberReturn.getMemberName();
				chatRoomMember.setMemberName(memberName);
			}else {
				chatRoomMember.setMemberName(syncChatRoomMember.getNickName());
			}
				
			chatRoomMemberDao.insertSelective(chatRoomMember);
			logger.info("已新增群成员记录：{}", chatRoomMember);
		}
		return room.getCode();
	}
	
	


	/**
	 * 
	 *
	 * 方法说明：更新微信群
	 *
	 * @param syncChatRoomFromZk
	 * @param shop
	 * @param syncChatRoom
	 * @param chatRoom
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年9月25日
	 *
	 */
	private void updateChatRoomInfoBySync(SyncChatRoomFromZk syncChatRoomFromZk, FindShopTerminalReturn shop,
			SyncChatRoom syncChatRoom, ChatRoom chatRoom) throws IllegalAccessException, InvocationTargetException {
		// 更新微信群记录
		ChatRoom updateChatRoom = new ChatRoom();
		updateChatRoom.setCode(chatRoom.getCode());
		boolean updateFlag = false; // 群信息有更新
		if (StringUtils.isNotEmpty(syncChatRoom.getChatRoomName())
				&& !syncChatRoom.getChatRoomName().equals(chatRoom.getChatRoomName())) {
			updateChatRoom.setChatRoomName(syncChatRoom.getChatRoomName());
			updateFlag = true;
		}
		if (StringUtils.isNotEmpty(syncChatRoom.getRoomNickName())
				&& !syncChatRoom.getRoomNickName().equals(chatRoom.getRoomNickName())) {
			if(syncChatRoom.getRoomNickName().length()>500) {
				syncChatRoom.setRoomNickName(syncChatRoom.getRoomNickName().substring(0, 500));
			}
			updateChatRoom.setRoomNickName(syncChatRoom.getRoomNickName());
			updateFlag = true;
		}
		if (StringUtils.isNotEmpty(syncChatRoom.getRoomOwner())
				&& !syncChatRoom.getRoomOwner().equals(chatRoom.getRoomOwner())) {
			updateChatRoom.setRoomOwner(syncChatRoom.getRoomOwner());
			updateFlag = true;
		}
		if (StringUtils.isNotEmpty(syncChatRoom.getHeadUrl())
				&& !syncChatRoom.getHeadUrl().equals(chatRoom.getHeadUrl())) {
			updateChatRoom.setHeadUrl(syncChatRoom.getHeadUrl());
			updateFlag = true;
		}
		if (chatRoom.getStatus().intValue() != ChatRoomStatus.Y.getCode() && StringUtils.isNotEmpty(syncChatRoom.getChatRoomName())) {
			updateChatRoom.setStatus(ChatRoomStatus.Y.getCode());
			updateFlag = true;
		}
		if (updateFlag) {
			updateChatRoom.setVersion(System.currentTimeMillis());
			chatRoomDao.updateByPrimaryKeySelective(updateChatRoom);
			logger.info("已更新微信群信息：{}", updateChatRoom);
		}

		/**
		 * 更新群成员记录：添加、修改、删除
		 */
		List<ChatRoomMember> chatRoomMemberList = chatRoomMemberDao.selectByRoomCode(chatRoom.getCode()); // 查询已存在群成员
		
		Map<String, ChatRoomMember> chatRoomMemberMap = new HashMap<String, ChatRoomMember>();
		for (ChatRoomMember chatRoomMember : chatRoomMemberList) { // 将服务器保存的成员记录列表转为按username映射的map
			chatRoomMemberMap.put(chatRoomMember.getUserName(), chatRoomMember);
		}
		// 循环中控客户端上传的群成员记录：处理发生新增或修改的群成员
		Map<String, SyncChatRoomMember> syncChatRoomMemberMap = new HashMap<String, SyncChatRoomMember>();
		for (SyncChatRoomMember syncChatRoomMember : syncChatRoom.getMemberList()) {
			ChatRoomMember chatRoomMember = chatRoomMemberMap.get(syncChatRoomMember.getUserName());
			if (chatRoomMember != null) { // 存在则更新
				updateFlag = false; // 群成员信息有更新
				ChatRoomMember updateChatRoomMember = new ChatRoomMember();
				updateChatRoomMember.setCode(chatRoomMember.getCode());
				if (StringUtils.isNotEmpty(syncChatRoomMember.getUserName())
						&& !syncChatRoomMember.getUserName().equals(chatRoomMember.getUserName())) {
					updateChatRoomMember.setUserName(syncChatRoomMember.getUserName());
					updateFlag = true;
				}
				if (StringUtils.isNotEmpty(syncChatRoomMember.getNickName())
						&& !syncChatRoomMember.getNickName().equals(chatRoomMember.getNickName())) {
					updateChatRoomMember.setNickName(syncChatRoomMember.getNickName());
					updateFlag = true;
				}
				if (StringUtils.isNotEmpty(syncChatRoomMember.getHeadUrl())
						&& !syncChatRoomMember.getHeadUrl().equals(chatRoomMember.getHeadUrl())) {
					updateChatRoomMember.setHeadUrl(syncChatRoomMember.getHeadUrl());
					updateFlag = true;
				}
				if (chatRoomMember.getStatus().intValue() != ChatRoomStatus.Y.getCode()) {
					updateChatRoomMember.setStatus(ChatRoomStatus.Y.getCode());
					updateFlag = true;
				}
				
				// 获取群成员信息// 更新备注名
				FindPersonMemberReturn findPersonMemberReturn= personMemberService.findPersonMemberByNoWxAndShopWx(syncChatRoomMember.getUserName(), chatRoomMember.getNoWxZk());
				if(null != findPersonMemberReturn) {
					String memberName = StringUtils.isNotEmpty(findPersonMemberReturn.getNickNameRemarkWx())?findPersonMemberReturn.getNickNameRemarkWx():findPersonMemberReturn.getMemberName();
					updateChatRoomMember.setMemberNo(findPersonMemberReturn.getMemberNo());
					if(!chatRoomMember.getMemberName().equals(memberName)) {
						updateChatRoomMember.setMemberName(memberName);
						updateFlag = true;
					}
				}else {
					//非中控好友
					updateChatRoomMember.setMemberName(syncChatRoomMember.getNickName());
					updateChatRoomMember.setMemberNo("");
					updateFlag = true;
				}
				
				if (updateFlag) {
					chatRoomMemberDao.updateByPrimaryKeySelective(updateChatRoomMember);
					logger.info("已更新微信群成员：{}", updateChatRoomMember);
				}
				
			} else { // 不存在则添加
				// 新增群成员记录
				chatRoomMember = new ChatRoomMember();
				chatRoomMember.setRoomCode(chatRoom.getCode());
				chatRoomMember.setNoWxZk(chatRoom.getNoWxZk());
				chatRoomMember.setChatRoomName(chatRoom.getChatRoomName());
				chatRoomMember.setStatus(1);
				chatRoomMember.setMerchantNo(shop.getMerchantNo());
				chatRoomMember.setMerchantName(shop.getMerchantName());
				chatRoomMember.setVersion(System.currentTimeMillis());
				chatRoomMember.setCreateDate(new Date());
				chatRoomMember.setCode(GUID.generateByUUID());
				chatRoomMember.setUserName(syncChatRoomMember.getUserName());
				chatRoomMember.setNickName(syncChatRoomMember.getNickName());
				chatRoomMember.setHeadUrl(syncChatRoomMember.getHeadUrl());
				chatRoomMember.setMemberNoGm(chatRoom.getMemberNoGm());
				chatRoomMember.setMemberNameGm(chatRoom.getMemberNameGm());
				// 获取群成员信息
				FindPersonMemberReturn findPersonMemberReturn= personMemberService.findPersonMemberByNoWxAndShopWx(syncChatRoomMember.getUserName(), chatRoomMember.getNoWxZk());
				if(null != findPersonMemberReturn) {
					String memberName = StringUtils.isNotEmpty(findPersonMemberReturn.getNickNameRemarkWx())?findPersonMemberReturn.getNickNameRemarkWx():findPersonMemberReturn.getMemberName();
					chatRoomMember.setMemberNo(findPersonMemberReturn.getMemberNo());
					chatRoomMember.setMemberName(memberName);
				}else {
					chatRoomMember.setMemberName(syncChatRoomMember.getNickName());
				}
				chatRoomMemberDao.insertSelective(chatRoomMember);
				logger.info("已新增群成员记录：{}", chatRoomMember);
			}

			// 将中控上传的成员记录列表转为按username映射的map
			syncChatRoomMemberMap.put(syncChatRoomMember.getUserName(), syncChatRoomMember);
		}

		// 循环服务器保存的群成员记录：将中控端上传的群成员列表里不存在的记录删除（退群的群成员）
		for (ChatRoomMember chatRoomMember : chatRoomMemberList) {
			if (!syncChatRoomMemberMap.containsKey(chatRoomMember.getUserName())) {
//				chatRoomMemberDao.deleteByPrimaryKey(chatRoomMember.getCode());
				//保留群成员信息-标记为无效
				ChatRoomMember record = new ChatRoomMember();
				record.setCode(chatRoomMember.getCode());
				record.setStatus(ChatRoomStatus.N.getCode());
				chatRoomMemberDao.updateByPrimaryKeySelective(record);
				logger.info("已删除群成员记录： {}", chatRoomMember);
			}
		}
	}

	@Override
	public FindChatRoomReturn findChatRoomAndInitWhileNon(FindChatRoom findChatRoom) throws TsfaServiceException {
		logger.debug("findChatRoomAndInitWhileNon(FindChatRoom findChatRoom={}) - start", findChatRoom); 

		FindChatRoomReturn findChatRoomReturn = this.findChatRoomBySelective(findChatRoom);
		if (findChatRoomReturn == null) { // 群记录不存则初始化，并向中控同步群信息
			try {
				// 初始化微信群信息记录
				FindShopTerminalReturn shopTerminalReturn = shopTerminalService
						.findShopTerminalNormal(findChatRoom.getNoWxZk());
				ChatRoom room = new ChatRoom();
				room.setCode(GUID.generateByUUID());
				room.setNoWxZk(findChatRoom.getNoWxZk());
				room.setChatRoomName(findChatRoom.getChatRoomName());
				room.setStatus(0);
//				room.setShopNo(shopTerminalReturn.getShopNo());
//				room.setShopName(shopTerminalReturn.getShopName());
				room.setMerchantNo(shopTerminalReturn.getMerchantNo());
				room.setMerchantName(shopTerminalReturn.getMerchantName());
				room.setVersion(System.currentTimeMillis());
				room.setCreateDate(new Date());
				chatRoomDao.insertSelective(room);
				logger.info("已初始化微信群记录：{}", room);

				// 向中控同步群信息
				SyncChatRoomMessage syncChatRoomMessage = new SyncChatRoomMessage();
				syncChatRoomMessage.setNoWxZk(room.getNoWxZk());
				syncChatRoomMessage.setChatRoomName(room.getChatRoomName());
				syncChatRoomMessage.setNowSync(true); // 立即同步
				logger.info("微信群信息{}不存在，需向中控发送同步群信息报文：{}", room.getChatRoomName(), syncChatRoomMessage);
				
				IWxChatRoomService basic =  commonService.getHessianWxChatRoomService(syncChatRoomMessage.getNoWxZk());

				basic.sendSyncChatRoom(syncChatRoomMessage);
				findChatRoomReturn = new FindChatRoomReturn();
				BeanUtils.copyPropertiesNull(findChatRoomReturn, room, true);
			} catch (TsfaServiceException e) {
				logger.error("获取微信群信息失败", e);
				throw e;
			} catch (Exception e) {
				logger.error("查找群信息表信息信息错误！", e);
				throw new TsfaServiceException(ErrorCode.CHAT_ROOM_FIND_ERROR, "查找群信息表信息信息错误！", e);
			}
		}

		logger.debug("findChatRoomAndInitWhileNon(FindChatRoomPage) - end - return value={}", findChatRoomReturn); 
		return findChatRoomReturn;
	}

	@Override
	public String createChatRoom(CreateChatRoom createChatRoom) throws TsfaServiceException {
		logger.debug("createChatRoom(CreateChatRoom createChatRoom)={}) - start", createChatRoom);

		AssertUtils.notNull(createChatRoom);
		AssertUtils.notNullAndEmpty(createChatRoom.getNoWxZk(), "终端微信不能为空");
		AssertUtils.notNullAndEmpty(createChatRoom.getRoomNickName(), "群名不能为空");
		AssertUtils.notNullAndEmpty(createChatRoom.getUserNames(), "群成员不能为空");
		if (createChatRoom.getUserNames().split(",").length > 38) {
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_ADD_ERROR, "群成员人数不能超过39人！");
		}
		try {
			// 查询终端信息
			FindShopTerminalReturn findShopTerminalReturn = shopTerminalService.findShopTerminalNormal(createChatRoom.getNoWxZk());

			SyncChatRoomFromZk syncChatRoomFromZk = new SyncChatRoomFromZk();
			syncChatRoomFromZk.setNoWxZk(createChatRoom.getNoWxZk());

			List<SyncChatRoomMember> memberList = new ArrayList<SyncChatRoomMember>();
			/**
			 * 添加群主信息
			 */
			SyncChatRoomMember syncChatRoomMember = new SyncChatRoomMember();
			syncChatRoomMember.setHeadUrl(findShopTerminalReturn.getHeadurl());
			syncChatRoomMember.setNickName(findShopTerminalReturn.getNickname());
			syncChatRoomMember.setUserName(findShopTerminalReturn.getUsernameWx());
			memberList.add(syncChatRoomMember);
			
			String[] usernames = createChatRoom.getUserNames().split(",");
			for (String userName : usernames) {
				// 获取群成员信息
				FindPersonMemberReturn baseReturn = personMemberService.findPersonMemberByNoWxAndShopWx(userName, createChatRoom.getNoWxZk());
				if (null == baseReturn) {
					throw new TsfaServiceException(ErrorCode.FIND_PMWXBP_INFO, "群成员微信错误！");
				}
				syncChatRoomMember = new SyncChatRoomMember();
				syncChatRoomMember.setHeadUrl(baseReturn.getHeadAddress());
				syncChatRoomMember.setNickName(baseReturn.getMemberName());
				syncChatRoomMember.setUserName(userName);
				syncChatRoomMember.setMemberNo(baseReturn.getMemberNo());
				String memberName = StringUtils.isNotEmpty(baseReturn.getNickNameRemarkWx())?baseReturn.getNickNameRemarkWx():baseReturn.getMemberName();
				syncChatRoomMember.setMemberName(memberName);
				memberList.add(syncChatRoomMember);
			}

			SyncChatRoom syncChatRoom = new SyncChatRoom();
			syncChatRoom.setMemberNoGm(createChatRoom.getMemberNoGm());
			syncChatRoom.setMemberList(memberList);
			syncChatRoom.setRoomNickName(createChatRoom.getRoomNickName());
			syncChatRoom.setRoomOwner(createChatRoom.getNoWxZk());
			syncChatRoom.setPmCode(createChatRoom.getPmCode());
			syncChatRoom.setPmName(createChatRoom.getPmName());
			String code = this.createChatRoomInfoBySync(syncChatRoomFromZk, findShopTerminalReturn, syncChatRoom,ChatRoomStatus.C.getCode());
			return code;
		} catch (Exception e) {
			logger.error("创建微信群信息失败" + createChatRoom.getRoomNickName(), e);
		}

		logger.debug("syncChatRoomFromZk(String) - end");

		return null;
	}

	@Override
	public DelChatRoomReturn delChatRoomByRoomName(String chatRoomName,String noWxGm) throws TsfaServiceException {
		logger.debug("delChatRoomByRoomName(String roomName={},noWxGm={}) - start", chatRoomName,noWxGm);

		AssertUtils.notNull(chatRoomName, "群名不能为空！");
		AssertUtils.notNull(noWxGm, "终端微信不能为空！");
		try {
			chatRoomDao.deleteByRoomName(chatRoomName,noWxGm);
			DelChatRoomReturn delChatRoomReturn = new DelChatRoomReturn();

			logger.debug("delChatRoomByRoomName(DelChatRoom) - end - return value={}", delChatRoomReturn); 
			return delChatRoomReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("删除群信息表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_DEL_ERROR, "删除群信息表信息错误！", e);
		}
	}

	@Override
	public List<FindChatRoomPageReturn> findChatRooms(FindChatRoomPage findChatRoomPage) throws TsfaServiceException {
		logger.debug("findChatRooms(FindChatRoomPage findChatRoomPage={}) - start", findChatRoomPage);

		AssertUtils.notNull(findChatRoomPage);
		List<FindChatRoomPageReturn> returnList = null;
		try {
			returnList = chatRoomDao.findChatRooms(findChatRoomPage);
		} catch (Exception e) {
			logger.error("群信息表信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_FIND_PAGE_ERROR, "群信息表信息不存在错误.！", e);
		}

		logger.debug("findChatRooms(FindChatRoomPage) - end - return value={}", returnList);
		return returnList;
	}
	@Override
	public UpdateChatRoomReturn cancelClaimed(UpdateChatRoom updateChatRoom) throws TsfaServiceException {
		logger.debug("cancelClaimed(UpdateChatRoom updateChatRoom={}) - start", updateChatRoom); //$NON-NLS-1$

		AssertUtils.notNull(updateChatRoom);
		AssertUtils.notNullAndEmpty(updateChatRoom.getCode(), "Code不能为空");
		try {
			ChatRoom chatRoom = new ChatRoom();
			// update数据录入
			chatRoom.setCode(updateChatRoom.getCode());
			AssertUtils.notUpdateMoreThanOne(chatRoomDao.cancelClaimed(chatRoom));
			UpdateChatRoomReturn updateChatRoomReturn = new UpdateChatRoomReturn();

			logger.debug("cancelClaimed(UpdateChatRoom) - end - return value={}", updateChatRoomReturn); //$NON-NLS-1$
			return updateChatRoomReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("取消认领错误！", e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_UPDATE_ERROR, "取消认领错误！", e);
		}
	}
	
	public UpdateChatRoomReturn claimedGroup(UpdateChatRoom updateChatRoom)throws TsfaServiceException{
		logger.debug("cancelClaimed(UpdateChatRoom updateChatRoom={}) - start", updateChatRoom); //$NON-NLS-1$

		AssertUtils.notNull(updateChatRoom);
		AssertUtils.notNullAndEmpty(updateChatRoom.getCode(), "Code不能为空");
		try {
			ChatRoom chatRoom = new ChatRoom();
			// update数据录入
			chatRoom.setCode(updateChatRoom.getCode());
			chatRoom.setMemberNameGm(updateChatRoom.getChatRoomName());
			chatRoom.setMemberNoGm(updateChatRoom.getMemberNoGm());
			AssertUtils.notUpdateMoreThanOne(chatRoomDao.cancelClaimed(chatRoom));
			UpdateChatRoomReturn updateChatRoomReturn = new UpdateChatRoomReturn();

			logger.debug("cancelClaimed(UpdateChatRoom) - end - return value={}", updateChatRoomReturn); //$NON-NLS-1$
			return updateChatRoomReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("认领群错误！", e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_UPDATE_ERROR, "认领群错误！", e);
		}
	}



	
	public List<ChatRoomPm> selectRoomGroup(ChatRoomPm chatroompm){
		return chatRoomPmDao.selectRoomGroup(chatroompm);
	}

	@Override
	public void createRoomGroup(ChatRoom room) {
		//先建群组
		if(room.getPmCode() != null) {
		   chatRoomPmDao.insertSelective(room);
		}
		
	}

	@Override
	public String selectSetUp(String roomCode) {
		return chatRoomDao.selectSetUp(roomCode);
	}

	@Override
	public void delete(UpdateChatRoom updateChatRoom) {
		chatRoomDao.delete(updateChatRoom);
		
	}

	@Override
	public void updateNoDisturb(UpdateChatRoom updateChatRoom) {
		logger.debug("updateNoDisturb(UpdateChatRoom updateChatRoom={}) - start", updateChatRoom); 
		AssertUtils.notNull(updateChatRoom);
		AssertUtils.notNullAndEmpty(updateChatRoom.getCode(), "编号不能为空");
		AssertUtils.notNullAndEmpty(updateChatRoom.getNoDisturb(), "免打扰设置状态不能为空");
		this.updateChatRoom(updateChatRoom);
	}

}
