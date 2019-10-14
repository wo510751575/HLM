package com.lj.business.member.service.impl;

import java.util.Date;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.constant.ErrorCode;
import com.lj.business.member.dao.IChatRoomDao;
import com.lj.business.member.dao.IChatRoomMemberDao;
import com.lj.business.member.domain.ChatRoom;
import com.lj.business.member.domain.ChatRoomMember;
import com.lj.business.member.dto.chatroom.AddChatRoomMember;
import com.lj.business.member.dto.chatroom.AddChatRoomMemberReturn;
import com.lj.business.member.dto.chatroom.DelChatRoomMember;
import com.lj.business.member.dto.chatroom.DelChatRoomMemberReturn;
import com.lj.business.member.dto.chatroom.FindChatRoomMember;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberPage;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberPageReturn;
import com.lj.business.member.dto.chatroom.FindChatRoomMemberReturn;
import com.lj.business.member.dto.chatroom.UpdateChatRoomMember;
import com.lj.business.member.dto.chatroom.UpdateChatRoomMemberReturn;
import com.lj.business.member.service.IChatRoomMemberService;
import com.lj.business.supcon.dto.chatroom.SyncChatRoomMessage;
import com.lj.business.supcon.service.ICommonService;
import com.lj.business.supcon.service.IWxChatRoomService;

/**
 * 
 * 
 * 类说明：群成员表实现类
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 深圳市扬恩科技
 * @author 曾垂瑜
 *   
 * CreateDate: 2018年03月22日
 */
@Service
public class ChatRoomMemberServiceImpl implements IChatRoomMemberService { 
	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(ChatRoomMemberServiceImpl.class);
	
	
	@Resource
	private IChatRoomDao chatRoomDao;
	
	@Resource
	private IChatRoomMemberDao chatRoomMemberDao;
	
	@Autowired 
	ICommonService commonService;
	
	@Override
	public AddChatRoomMemberReturn addChatRoomMember(AddChatRoomMember addChatRoomMember) throws TsfaServiceException {
		logger.debug("addChatRoomMember(AddChatRoomMember addChatRoomMember={}) - start", addChatRoomMember); 

		AssertUtils.notNull(addChatRoomMember);
		try {
			ChatRoomMember chatRoomMember = new ChatRoomMember();
			//add数据录入
			chatRoomMember.setCode(GUID.generateByUUID());
			chatRoomMember.setRoomCode(addChatRoomMember.getRoomCode());
			chatRoomMember.setNoWxZk(addChatRoomMember.getNoWxZk());
			chatRoomMember.setChatRoomName(addChatRoomMember.getChatRoomName());
			chatRoomMember.setUserName(addChatRoomMember.getUserName());
			chatRoomMember.setNickName(addChatRoomMember.getNickName());
			chatRoomMember.setHeadUrl(addChatRoomMember.getHeadUrl());
			chatRoomMember.setStatus(addChatRoomMember.getStatus());
			chatRoomMember.setMemberNoGm(addChatRoomMember.getMemberNoGm());
			chatRoomMember.setMemberNameGm(addChatRoomMember.getMemberNameGm());
			chatRoomMember.setMemberNo(addChatRoomMember.getMemberNo());
			chatRoomMember.setMemberName(addChatRoomMember.getMemberName());
//			chatRoomMember.setShopNo(addChatRoomMember.getShopNo());
//			chatRoomMember.setShopName(addChatRoomMember.getShopName());
			chatRoomMember.setMerchantNo(addChatRoomMember.getMerchantNo());
			chatRoomMember.setMerchantName(addChatRoomMember.getMerchantName());
			chatRoomMember.setVersion(System.currentTimeMillis());
			chatRoomMember.setCreateId(addChatRoomMember.getCreateId());
			chatRoomMember.setCreateDate(new Date());
			chatRoomMember.setRemark(addChatRoomMember.getRemark());
			chatRoomMember.setRemark2(addChatRoomMember.getRemark2());
			chatRoomMember.setRemark3(addChatRoomMember.getRemark3());
			chatRoomMember.setRemark4(addChatRoomMember.getRemark4());
			chatRoomMemberDao.insertSelective(chatRoomMember);
			AddChatRoomMemberReturn addChatRoomMemberReturn = new AddChatRoomMemberReturn();
			
			logger.debug("addChatRoomMember(AddChatRoomMember) - end - return value={}", addChatRoomMemberReturn); 
			return addChatRoomMemberReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增群成员表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_MEMBER_ADD_ERROR,"新增群成员表信息错误！",e);
		}
	}
	
	@Override
	public DelChatRoomMemberReturn delChatRoomMember(DelChatRoomMember delChatRoomMember) throws TsfaServiceException {
		logger.debug("delChatRoomMember(DelChatRoomMember delChatRoomMember={}) - start", delChatRoomMember); 

		AssertUtils.notNull(delChatRoomMember);
		AssertUtils.notNull(delChatRoomMember.getCode(),"Code不能为空！");
		try {
			chatRoomMemberDao.deleteByPrimaryKey(delChatRoomMember.getCode());
			DelChatRoomMemberReturn delChatRoomMemberReturn  = new DelChatRoomMemberReturn();

			logger.debug("delChatRoomMember(DelChatRoomMember) - end - return value={}", delChatRoomMemberReturn); 
			return delChatRoomMemberReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除群成员表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_MEMBER_DEL_ERROR,"删除群成员表信息错误！",e);
		}
	}

	@Override
	public UpdateChatRoomMemberReturn updateChatRoomMember(UpdateChatRoomMember updateChatRoomMember) throws TsfaServiceException {
		logger.debug("updateChatRoomMember(UpdateChatRoomMember updateChatRoomMember={}) - start", updateChatRoomMember); 

		AssertUtils.notNull(updateChatRoomMember);
		AssertUtils.notNullAndEmpty(updateChatRoomMember.getCode(),"Code不能为空");
		try {
			ChatRoomMember chatRoomMember = new ChatRoomMember();
			//update数据录入
			chatRoomMember.setCode(updateChatRoomMember.getCode());
			chatRoomMember.setRoomCode(updateChatRoomMember.getRoomCode());
			chatRoomMember.setNoWxZk(updateChatRoomMember.getNoWxZk());
			chatRoomMember.setChatRoomName(updateChatRoomMember.getChatRoomName());
			chatRoomMember.setUserName(updateChatRoomMember.getUserName());
			chatRoomMember.setNickName(updateChatRoomMember.getNickName());
			chatRoomMember.setHeadUrl(updateChatRoomMember.getHeadUrl());
			chatRoomMember.setStatus(updateChatRoomMember.getStatus());
			chatRoomMember.setMemberNoGm(updateChatRoomMember.getMemberNoGm());
			chatRoomMember.setMemberNameGm(updateChatRoomMember.getMemberNameGm());
			chatRoomMember.setMemberNo(updateChatRoomMember.getMemberNo());
			chatRoomMember.setMemberName(updateChatRoomMember.getMemberName());
//			chatRoomMember.setShopNo(updateChatRoomMember.getShopNo());
//			chatRoomMember.setShopName(updateChatRoomMember.getShopName());
			chatRoomMember.setMerchantNo(updateChatRoomMember.getMerchantNo());
			chatRoomMember.setMerchantName(updateChatRoomMember.getMerchantName());
			chatRoomMember.setVersion(System.currentTimeMillis());
			chatRoomMember.setCreateId(updateChatRoomMember.getCreateId());
			chatRoomMember.setCreateDate(updateChatRoomMember.getCreateDate());
			chatRoomMember.setRemark(updateChatRoomMember.getRemark());
			chatRoomMember.setRemark2(updateChatRoomMember.getRemark2());
			chatRoomMember.setRemark3(updateChatRoomMember.getRemark3());
			chatRoomMember.setRemark4(updateChatRoomMember.getRemark4());
			AssertUtils.notUpdateMoreThanOne(chatRoomMemberDao.updateByPrimaryKeySelective(chatRoomMember));
			UpdateChatRoomMemberReturn updateChatRoomMemberReturn = new UpdateChatRoomMemberReturn();

			logger.debug("updateChatRoomMember(UpdateChatRoomMember) - end - return value={}", updateChatRoomMemberReturn); 
			return updateChatRoomMemberReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("群成员表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_MEMBER_UPDATE_ERROR,"群成员表信息更新信息错误！",e);
		}
	}

	@Override
	public FindChatRoomMemberReturn findChatRoomMember(FindChatRoomMember findChatRoomMember) throws TsfaServiceException {
		logger.debug("findChatRoomMember(FindChatRoomMember findChatRoomMember={}) - start", findChatRoomMember); 

		AssertUtils.notNull(findChatRoomMember);
		AssertUtils.notAllNull(findChatRoomMember.getCode(),"Code不能为空");
		try {
			ChatRoomMember chatRoomMember = chatRoomMemberDao.selectByPrimaryKey(findChatRoomMember.getCode());
			if(chatRoomMember == null){
				throw new TsfaServiceException(ErrorCode.CHAT_ROOM_MEMBER_NOT_EXIST_ERROR,"群成员表信息不存在");
			}
			FindChatRoomMemberReturn findChatRoomMemberReturn = new FindChatRoomMemberReturn();
			//find数据录入
			findChatRoomMemberReturn.setCode(chatRoomMember.getCode());
			findChatRoomMemberReturn.setRoomCode(chatRoomMember.getRoomCode());
			findChatRoomMemberReturn.setNoWxZk(chatRoomMember.getNoWxZk());
			findChatRoomMemberReturn.setChatRoomName(chatRoomMember.getChatRoomName());
			findChatRoomMemberReturn.setUserName(chatRoomMember.getUserName());
			findChatRoomMemberReturn.setNickName(chatRoomMember.getNickName());
			findChatRoomMemberReturn.setHeadUrl(chatRoomMember.getHeadUrl());
			findChatRoomMemberReturn.setStatus(chatRoomMember.getStatus());
			findChatRoomMemberReturn.setMemberNoGm(chatRoomMember.getMemberNoGm());
			findChatRoomMemberReturn.setMemberNameGm(chatRoomMember.getMemberNameGm());
			findChatRoomMemberReturn.setMemberNo(chatRoomMember.getMemberNo());
			findChatRoomMemberReturn.setMemberName(chatRoomMember.getMemberName());
//			findChatRoomMemberReturn.setShopNo(chatRoomMember.getShopNo());
//			findChatRoomMemberReturn.setShopName(chatRoomMember.getShopName());
			findChatRoomMemberReturn.setMerchantNo(chatRoomMember.getMerchantNo());
			findChatRoomMemberReturn.setMerchantName(chatRoomMember.getMerchantName());
			findChatRoomMemberReturn.setVersion(chatRoomMember.getVersion());
			findChatRoomMemberReturn.setCreateId(chatRoomMember.getCreateId());
			findChatRoomMemberReturn.setCreateDate(chatRoomMember.getCreateDate());
			findChatRoomMemberReturn.setRemark(chatRoomMember.getRemark());
			findChatRoomMemberReturn.setRemark2(chatRoomMember.getRemark2());
			findChatRoomMemberReturn.setRemark3(chatRoomMember.getRemark3());
			findChatRoomMemberReturn.setRemark4(chatRoomMember.getRemark4());
			
			logger.debug("findChatRoomMember(FindChatRoomMember) - end - return value={}", findChatRoomMemberReturn); 
			return findChatRoomMemberReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找群成员表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_MEMBER_FIND_ERROR,"查找群成员表信息信息错误！",e);
		}
	}

	@Override
	public Page<FindChatRoomMemberPageReturn> findChatRoomMemberPage(FindChatRoomMemberPage findChatRoomMemberPage) throws TsfaServiceException {
		logger.debug("findChatRoomMemberPage(FindChatRoomMemberPage findChatRoomMemberPage={}) - start", findChatRoomMemberPage); 

		AssertUtils.notNull(findChatRoomMemberPage);
		List<FindChatRoomMemberPageReturn> returnList = null;
		int count = 0;
		try {
			count = chatRoomMemberDao.findChatRoomMemberPageCount(findChatRoomMemberPage);
			if(count > 0) {
				returnList = chatRoomMemberDao.findChatRoomMemberPage(findChatRoomMemberPage);
			}
		}  catch (Exception e) {
			logger.error("群成员表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_MEMBER_FIND_PAGE_ERROR,"群成员表信息不存在错误.！",e);
		}
		Page<FindChatRoomMemberPageReturn> returnPage = new Page<FindChatRoomMemberPageReturn>(returnList, count, findChatRoomMemberPage);

		logger.debug("findChatRoomMemberPage(FindChatRoomMemberPage) - end - return value={}", returnPage); 
		return  returnPage;
	}

	/**
	 * 
	 *
	 * 方法说明：查找群成员表信息(分页)
	 *
	 * @param findChatRoomMemberPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年03月22日
	 *
	 */
	@Override
	public List<FindChatRoomMemberPageReturn> findChatRoomMemberList(FindChatRoomMemberPage findChatRoomMemberPage) throws TsfaServiceException {
		logger.debug("findChatRoomMemberList(FindChatRoomMemberPage findChatRoomMemberPage={}) - start", findChatRoomMemberPage); 

		AssertUtils.notNull(findChatRoomMemberPage);
		List<FindChatRoomMemberPageReturn> returnList = null;
		try {
			findChatRoomMemberPage.setLimit(Integer.MAX_VALUE);
			returnList = chatRoomMemberDao.findChatRoomMemberPage(findChatRoomMemberPage);
		}  catch (Exception e) {
			logger.error("群成员表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_MEMBER_FIND_PAGE_ERROR,"群成员表信息不存在错误.！",e);
		}

		logger.debug("findChatRoomMemberList(FindChatRoomMemberPage) - end - return value={}", returnList); 
		return returnList;
	}

	/**
	 * 
	 *
	 * 方法说明：查询群聊下指定微信的群成员信息<br>
	 * <font color="red">注意：如果群成员信息不存在，则会向中控请求同步群信息</font>
	 *
	 * @param roomCode		群code
	 * @param userName		群成员微信号
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 曾垂瑜 CreateDate: 2018年9月28日
	 *
	 */
	@Override
	public FindChatRoomMemberReturn findChatRoomMemberByNoWx(String roomCode, String userName) throws TsfaServiceException {
		logger.debug("findChatRoomMemberByNoWx(String roomCode={}, String userName={}) - start", roomCode, userName); 

		AssertUtils.notNullAndEmpty(roomCode, "群code不能为空");
		AssertUtils.notNullAndEmpty(userName, "群成员微信号不能为空");
		
		FindChatRoomMemberReturn findChatRoomMemberReturn = null;
		try {
			findChatRoomMemberReturn = chatRoomMemberDao.findChatRoomMemberByNoWx(roomCode, userName);
			
			// 群成员不存在，则向中控同步群信息
			if(findChatRoomMemberReturn == null) {
				ChatRoom chatRoom = chatRoomDao.selectByPrimaryKey(roomCode);
				
				// 同步微信群信息
				SyncChatRoomMessage syncChatRoomMessage = new SyncChatRoomMessage();
				syncChatRoomMessage.setNoWxZk(chatRoom.getNoWxZk());
				syncChatRoomMessage.setChatRoomName(chatRoom.getChatRoomName());
				syncChatRoomMessage.setNowSync(true);	// 立即同步
				
				IWxChatRoomService basic =  commonService.getHessianWxChatRoomService(syncChatRoomMessage.getNoWxZk());
				basic.sendSyncChatRoom(syncChatRoomMessage);
			}
		}  catch (Exception e) {
			logger.error("群成员表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_MEMBER_FIND_PAGE_ERROR,"群成员表信息不存在错误.！",e);
		}

		logger.debug("findChatRoomMemberByNoWx(String roomCode, String userName) - end - return value={}", findChatRoomMemberReturn); 
		return findChatRoomMemberReturn;
	}


	
	@Override
	public DelChatRoomMemberReturn delChatRoomMemberByCond(DelChatRoomMember delChatRoomMember)
			throws TsfaServiceException {
		logger.debug("delChatRoomMemberByRoomCode(DelChatRoomMember delChatRoomMember={}) - start", delChatRoomMember); 

		AssertUtils.notNull(delChatRoomMember);
		AssertUtils.notAllNullAndEmpty(delChatRoomMember.getRoomCode(),delChatRoomMember.getChatRoomName(),"RoomCode/RoomName不能全部为空！");
		try {
			ChatRoomMember record = new ChatRoomMember();
			record.setRoomCode(delChatRoomMember.getRoomCode());
			record.setChatRoomName(delChatRoomMember.getChatRoomName());
			record.setNoWxZk(delChatRoomMember.getNoWxZk());
			record.setUserName(delChatRoomMember.getUserName());
			chatRoomMemberDao.deleteByCond(record);
			DelChatRoomMemberReturn delChatRoomMemberReturn  = new DelChatRoomMemberReturn();

			logger.debug("delChatRoomMemberByRoomCode(DelChatRoomMember) - end - return value={}", delChatRoomMemberReturn); 
			return delChatRoomMemberReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除群成员表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_MEMBER_DEL_ERROR,"删除群成员表信息错误！",e);
		}
	}

	@Override
	public UpdateChatRoomMemberReturn updateChatRoomMemberByRoomCode(UpdateChatRoomMember updateChatRoomMember)
			throws TsfaServiceException {
		logger.debug("updateChatRoomMemberByRoomCode(UpdateChatRoomMember updateChatRoomMember={}) - start", updateChatRoomMember); 

		AssertUtils.notNull(updateChatRoomMember);
		AssertUtils.notNullAndEmpty(updateChatRoomMember.getRoomCode(),"RoomCode不能为空");
		try {
			ChatRoomMember chatRoomMember = new ChatRoomMember();
			//update数据录入
			chatRoomMember.setRoomCode(updateChatRoomMember.getRoomCode());
			chatRoomMember.setNoWxZk(updateChatRoomMember.getNoWxZk());
			chatRoomMember.setChatRoomName(updateChatRoomMember.getChatRoomName());
			chatRoomMember.setUserName(updateChatRoomMember.getUserName());
			chatRoomMember.setNickName(updateChatRoomMember.getNickName());
			chatRoomMember.setHeadUrl(updateChatRoomMember.getHeadUrl());
			chatRoomMember.setStatus(updateChatRoomMember.getStatus());
			chatRoomMember.setMemberNoGm(updateChatRoomMember.getMemberNoGm());
			chatRoomMember.setMemberNameGm(updateChatRoomMember.getMemberNameGm());
			chatRoomMember.setMemberNo(updateChatRoomMember.getMemberNo());
			chatRoomMember.setMemberName(updateChatRoomMember.getMemberName());
//			chatRoomMember.setShopNo(updateChatRoomMember.getShopNo());
//			chatRoomMember.setShopName(updateChatRoomMember.getShopName());
			chatRoomMember.setMerchantNo(updateChatRoomMember.getMerchantNo());
			chatRoomMember.setMerchantName(updateChatRoomMember.getMerchantName());
			chatRoomMember.setVersion(System.currentTimeMillis());
			chatRoomMember.setCreateId(updateChatRoomMember.getCreateId());
			chatRoomMember.setCreateDate(updateChatRoomMember.getCreateDate());
			chatRoomMember.setRemark(updateChatRoomMember.getRemark());
			chatRoomMember.setRemark2(updateChatRoomMember.getRemark2());
			chatRoomMember.setRemark3(updateChatRoomMember.getRemark3());
			chatRoomMember.setRemark4(updateChatRoomMember.getRemark4());
			chatRoomMemberDao.updateByRoomCode(chatRoomMember);
			UpdateChatRoomMemberReturn updateChatRoomMemberReturn = new UpdateChatRoomMemberReturn();

			logger.debug("updateChatRoomMemberByRoomCode(UpdateChatRoomMember) - end - return value={}", updateChatRoomMemberReturn); 
			return updateChatRoomMemberReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("群成员表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_MEMBER_UPDATE_ERROR,"群成员表信息更新信息错误！",e);
		}
	}

	@Override
	public UpdateChatRoomMemberReturn synChatRoomOwnerHeadUrl(UpdateChatRoomMember updateChatRoomMember)
			throws TsfaServiceException {
		logger.debug("synChatRoomOwnerHeadUrl(UpdateChatRoomMember updateChatRoomMember={}) - start", updateChatRoomMember); 

		AssertUtils.notNull(updateChatRoomMember);
		AssertUtils.notNullAndEmpty(updateChatRoomMember.getNoWxZk(),"noWxZk不能为空");
		AssertUtils.notNullAndEmpty(updateChatRoomMember.getMerchantNo(),"merchantNo不能为空");
		try {
			ChatRoomMember chatRoomMember = new ChatRoomMember();
			//update数据录入
			chatRoomMember.setMerchantNo(updateChatRoomMember.getMerchantNo());
			chatRoomMember.setNoWxZk(updateChatRoomMember.getNoWxZk());
			chatRoomMember.setHeadUrl(updateChatRoomMember.getHeadUrl());
			chatRoomMemberDao.synChatRoomOwnerHeadUrl(chatRoomMember);
			UpdateChatRoomMemberReturn updateChatRoomMemberReturn = new UpdateChatRoomMemberReturn();

			logger.debug("updateChatRoomMemberByRoomCode(UpdateChatRoomMember) - end - return value={}", updateChatRoomMemberReturn); 
			return updateChatRoomMemberReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("群成员表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_MEMBER_UPDATE_ERROR,"群成员表信息更新信息错误！",e);
		}
	}

	@Override
	public String getNickNameByRoomCode(String roomCode, String noWxGm) throws TsfaServiceException {
		logger.debug("getNickNameByRoomCode(String roomCode={}, String noWxGm={}) - start", roomCode,noWxGm); 

		AssertUtils.notNullAndEmpty(roomCode,"群编号不能为空");
		AssertUtils.notNullAndEmpty(noWxGm,"中控微信不能为空");
		try {
			String str =chatRoomMemberDao.getNickNameByRoomCode(roomCode, noWxGm);

			logger.debug("getNickNameByRoomCode(String roomCode, String noWxGm) - end - return value={}", str); 
			return str;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("获取群成员昵称错误！",e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_MEMBER_FIND_ERROR,"获取群成员昵称错误！",e);
		}
	}

	@Override
	public UpdateChatRoomMemberReturn synChatRoomMember(UpdateChatRoomMember updateChatRoomMember)
			throws TsfaServiceException {
		logger.debug("synChatRoomOwnerHeadUrl(UpdateChatRoomMember updateChatRoomMember={}) - start", updateChatRoomMember); 

		AssertUtils.notNull(updateChatRoomMember);
		AssertUtils.notNullAndEmpty(updateChatRoomMember.getNoWxZk(),"中控微信不能为空");
		AssertUtils.notNullAndEmpty(updateChatRoomMember.getMemberNo(),"客户编号不能为空");
		AssertUtils.notNullAndEmpty(updateChatRoomMember.getMemberName(),"客户名称不能为空");
		try {
			ChatRoomMember chatRoomMember = new ChatRoomMember();
			//update数据录入
			chatRoomMember.setNoWxZk(updateChatRoomMember.getNoWxZk());
			chatRoomMember.setMemberNo(updateChatRoomMember.getMemberNo());
			chatRoomMember.setMemberName(updateChatRoomMember.getMemberName());
			chatRoomMemberDao.synChatRoomMember(chatRoomMember);
			UpdateChatRoomMemberReturn updateChatRoomMemberReturn = new UpdateChatRoomMemberReturn();

			logger.debug("updateChatRoomMemberByRoomCode(UpdateChatRoomMember) - end - return value={}", updateChatRoomMemberReturn); 
			return updateChatRoomMemberReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("群成员表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_MEMBER_UPDATE_ERROR,"群成员表信息更新信息错误！",e);
		}
	}

	@Override
	public FindChatRoomMemberReturn findChatRoomMemberByNoWxSingle(String roomCode, String userName)
			throws TsfaServiceException {
		logger.debug("findChatRoomMemberByNoWxSingle(String roomCode={}, String userName={}) - start", roomCode, userName); 

		AssertUtils.notNullAndEmpty(roomCode, "群code不能为空");
		AssertUtils.notNullAndEmpty(userName, "群成员微信号不能为空");
		
		FindChatRoomMemberReturn findChatRoomMemberReturn = null;
		try {
			findChatRoomMemberReturn = chatRoomMemberDao.findChatRoomMemberByNoWx(roomCode, userName);
		}  catch (Exception e) {
			logger.error("群成员表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_MEMBER_FIND_PAGE_ERROR,"群成员表信息不存在错误.！",e);
		}

		logger.debug("findChatRoomMemberByNoWxSingle(String roomCode, String userName) - end - return value={}", findChatRoomMemberReturn); 
		return findChatRoomMemberReturn;
	}

	@Override
	public int updateByCond(UpdateChatRoomMember updateChatRoomMember) throws TsfaServiceException {
		logger.debug("updateByCond(UpdateChatRoomMember updateChatRoomMember)={}) - start", updateChatRoomMember); 

		AssertUtils.notNull(updateChatRoomMember);
		AssertUtils.notNullAndEmpty(updateChatRoomMember.getRoomCode(),"群编号不能为空");
		try {
			ChatRoomMember record = new ChatRoomMember();
			record.setRoomCode(updateChatRoomMember.getRoomCode());
			record.setStatus(updateChatRoomMember.getStatus());
			record.setUserNames(updateChatRoomMember.getUserNames());
			int count = chatRoomMemberDao.updateByCond(record);

			logger.debug("updateByCond(UpdateChatRoomMember updateChatRoomMember) - end - return value={}", record); 
			return count;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("群成员表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_MEMBER_UPDATE_ERROR,"群成员表信息更新信息错误！",e);
		}
	}

	@Override
	public int cancelClaimed(UpdateChatRoomMember updateChatRoomMember) {
		logger.debug("cancelClaimed(UpdateChatRoomMember updateChatRoomMember={}) - start", updateChatRoomMember); 

		AssertUtils.notNull(updateChatRoomMember);
		AssertUtils.notNullAndEmpty(updateChatRoomMember.getRoomCode(),"RoomCode不能为空");
		try {
			ChatRoomMember chatRoomMember = new ChatRoomMember();
			//update数据录入
			chatRoomMember.setRoomCode(updateChatRoomMember.getRoomCode());
			int count =chatRoomMemberDao.cancelClaimed(chatRoomMember);
			UpdateChatRoomMemberReturn updateChatRoomMemberReturn = new UpdateChatRoomMemberReturn();

			logger.debug("cancelClaimed(UpdateChatRoomMember) - end - return value={}", updateChatRoomMemberReturn); 
			return count;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("群成员表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.CHAT_ROOM_MEMBER_UPDATE_ERROR,"群成员表信息更新信息错误！",e);
		}
	}

	@Override
	public void delete(UpdateChatRoomMember updateChatRoomMember) {
		chatRoomMemberDao.delete(updateChatRoomMember);
		
	}
}
