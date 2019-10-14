package com.lj.business.cf.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cf.constant.ErrorCode;
import com.lj.business.cf.dao.IMessagePushDao;
import com.lj.business.cf.domain.MessagePush;
import com.lj.business.cf.dto.memberMessageRelation.AddMemberMessageRelation;
import com.lj.business.cf.dto.messagePush.AddMessagePushDto;
import com.lj.business.cf.dto.messagePush.DelMessagePush;
import com.lj.business.cf.dto.messagePush.FindMessagePush;
import com.lj.business.cf.dto.messagePush.FindMessagePushPage;
import com.lj.business.cf.dto.messagePush.FindMessagePushPageReturn;
import com.lj.business.cf.dto.messagePush.FindMessagePushReturn;
import com.lj.business.cf.dto.messagePush.MessagePushCodeDto;
import com.lj.business.cf.dto.messagePush.UpdateMessagePush;
import com.lj.business.cf.emus.MessagePushStatus;
import com.lj.business.cf.service.IMemberMessageRelationService;
import com.lj.business.cf.service.IMessagePushService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 冯辉
 * 
 * 
 * CreateDate: 2017-06-14
 */
@Service
public class MessagePushServiceImpl implements IMessagePushService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(MessagePushServiceImpl.class);
	

	@Resource
	private IMessagePushDao messagePushDao;
	
	@Resource
	private IMemberMessageRelationService memberMessageRelationService;
	
	@Override
	public List<MessagePushCodeDto> addMessagePush(
			AddMessagePushDto addMessagePushDto) throws TsfaServiceException {
		logger.debug("addMessagePush(AddMessagePush addMessagePush={}) - start", addMessagePushDto); 

		AssertUtils.notNull(addMessagePushDto);
		AssertUtils.notNullAndEmpty(addMessagePushDto.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notNullAndEmpty(addMessagePushDto.getMemberNoMsg(), "客户编号不能为空");
		AssertUtils.notNullAndEmpty(addMessagePushDto.getMemberNoGm(), "导购编号不能为空");
		AssertUtils.notNullAndEmpty(addMessagePushDto.getShopNo(), "分店编号不能为空");
		AssertUtils.notNullAndEmpty(addMessagePushDto.getMsgContent(), "消息内容不能为空");
		try {
			String[] dates = addMessagePushDto.getPushDate().split(",");
			List<MessagePushCodeDto> list = new ArrayList<>();
			for (String date : dates) {
				MessagePush messagePush = new MessagePush();
				//add数据录入
				messagePush.setCode(GUID.generateCode());
				messagePush.setMsgTitle(addMessagePushDto.getMsgTitle());
				messagePush.setMsgContent(addMessagePushDto.getMsgContent());
				messagePush.setMsgType(addMessagePushDto.getMsgType());
				messagePush.setPushDate(DateUtils.parseDate(date, DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss));
				messagePush.setMerchantNo(addMessagePushDto.getMerchantNo());
				messagePush.setMerchantName(addMessagePushDto.getMerchantName());
				messagePush.setShopNo(addMessagePushDto.getShopNo());
				messagePush.setShopName(addMessagePushDto.getShopName());
				messagePush.setMemberNoGm(addMessagePushDto.getMemberNoGm());
				messagePush.setMemberNameGm(addMessagePushDto.getMemberNameGm());
				messagePush.setMemberNoMsg(addMessagePushDto.getMemberNoMsg());
				messagePush.setMemberNamesMsg(addMessagePushDto.getMemberNamesMsg());
				messagePush.setCreateId(addMessagePushDto.getMemberNoGm());
				messagePush.setCreateDate(new Date());
				messagePushDao.insert(messagePush);
				
				saveMemberMessageRelation(messagePush, addMessagePushDto.getMemberWxNos());//保存明细表
				
				MessagePushCodeDto messagePushCodeDto = new MessagePushCodeDto();
				messagePushCodeDto.setCode(messagePush.getCode());
				messagePushCodeDto.setPushDate(date);
				list.add(messagePushCodeDto);
			}
			logger.debug("addMessagePush(AddMessagePush) - end - return");
			return list;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增导购行为信息记录表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.MESSAGE_PUSH_ADD_ERROR,"新增导购行为信息记录表信息错误！",e);
		}
	}
	
	
	private void saveMemberMessageRelation(MessagePush messagePush, String wxNos) {
		AddMemberMessageRelation addMemberMessageRelation = new AddMemberMessageRelation();
		addMemberMessageRelation.setMsgNo(messagePush.getCode());
		addMemberMessageRelation.setMerchantNo(messagePush.getMerchantNo());
		addMemberMessageRelation.setMerchantName(messagePush.getMerchantName());
		addMemberMessageRelation.setShopNo(messagePush.getShopNo());
		addMemberMessageRelation.setShopName(messagePush.getShopName());
		addMemberMessageRelation.setMemberNoGm(messagePush.getMemberNoGm());
		addMemberMessageRelation.setMemberNameGm(messagePush.getMemberNameGm());
		addMemberMessageRelation.setPushDate(messagePush.getPushDate());
		addMemberMessageRelation.setMsgStatus(MessagePushStatus.VALID.toString());
		addMemberMessageRelation.setCreateId(messagePush.getMemberNoGm());
		addMemberMessageRelation.setCreateDate(new Date());
		
		String[] nos = messagePush.getMemberNoMsg().split(",");
		String[] names = messagePush.getMemberNamesMsg().split(",");
		String[] wxs = wxNos.split(",");
		
		if (nos.length == names.length && nos.length == wxs.length && nos.length >= 1) {
			for (int i = 0; i < names.length; i++) {
				addMemberMessageRelation.setCode(GUID.generateCode());
				addMemberMessageRelation.setMemberNo(nos[i]);
				addMemberMessageRelation.setMemberName(names[i]);
				addMemberMessageRelation.setMemberWxNo(wxs[i]);
				
				memberMessageRelationService.addMemberMessageRelation(addMemberMessageRelation);
			}
		} else {
			logger.error("新增用户消息关联表信息错误，用户信息数据错误");
			throw new TsfaServiceException(ErrorCode.MEMBER_MESSAGE_RELATION_ADD_ERROR,"新增用户消息关联表信息错误，用户信息数据错误");
		}
	}


	@Override
	public void delMessagePush(
			DelMessagePush delMessagePush) throws TsfaServiceException {
		logger.debug("delMessagePush(DelMessagePush delMessagePush={}) - start", delMessagePush); 

		AssertUtils.notNull(delMessagePush);
		AssertUtils.notNull(delMessagePush.getCode(),"Code不能为空！");
		try {
			messagePushDao.deleteByPrimaryKey(delMessagePush.getCode());
			logger.debug("delMessagePush(DelMessagePush) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除导购行为信息记录表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.MESSAGE_PUSH_DEL_ERROR,"删除导购行为信息记录表信息错误！",e);

		}
	}

	@Override
	public void updateMessagePush(
			UpdateMessagePush updateMessagePush)
			throws TsfaServiceException {
		logger.debug("updateMessagePush(UpdateMessagePush updateMessagePush={}) - start", updateMessagePush); //$NON-NLS-1$

		AssertUtils.notNull(updateMessagePush);
		AssertUtils.notNullAndEmpty(updateMessagePush.getCode(),"Code不能为空");
		try {
			MessagePush messagePush = new MessagePush();
			//update数据录入
			messagePush.setCode(updateMessagePush.getCode());
			messagePush.setMsgTitle(updateMessagePush.getMsgTitle());
			messagePush.setMsgContent(updateMessagePush.getMsgContent());
			messagePush.setMsgType(updateMessagePush.getMsgType());
			messagePush.setPushDate(updateMessagePush.getPushDate());
			messagePush.setMemberNoGm(updateMessagePush.getMemberNoGm());
			messagePush.setMemberNameGm(updateMessagePush.getMemberNameGm());
			messagePush.setMemberNoMsg(updateMessagePush.getMemberNoMsg());
			messagePush.setMemberNamesMsg(updateMessagePush.getMemberNamesMsg());
			messagePush.setUpdateId(updateMessagePush.getUpdateId());
			messagePush.setUpdateDate(updateMessagePush.getUpdateDate());
			messagePush.setCreateId(updateMessagePush.getCreateId());
			messagePush.setCreateDate(updateMessagePush.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(messagePushDao.updateByPrimaryKeySelective(messagePush));
			logger.debug("updateMessagePush(UpdateMessagePush) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("导购行为信息记录表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.MESSAGE_PUSH_UPDATE_ERROR,"导购行为信息记录表信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindMessagePushReturn findMessagePush(
			FindMessagePush findMessagePush) throws TsfaServiceException {
		logger.debug("findMessagePush(FindMessagePush findMessagePush={}) - start", findMessagePush); //$NON-NLS-1$

		AssertUtils.notNull(findMessagePush);
		AssertUtils.notAllNull(findMessagePush.getCode(),"Code不能为空");
		try {
			MessagePush messagePush = messagePushDao.selectByPrimaryKey(findMessagePush.getCode());
			if(messagePush == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.MESSAGE_PUSH_NOT_EXIST_ERROR,"导购行为信息记录表信息不存在");
			}
			FindMessagePushReturn findMessagePushReturn = new FindMessagePushReturn();
			//find数据录入
			findMessagePushReturn.setCode(messagePush.getCode());
			findMessagePushReturn.setMsgTitle(messagePush.getMsgTitle());
			findMessagePushReturn.setMsgContent(messagePush.getMsgContent());
			findMessagePushReturn.setMsgType(messagePush.getMsgType());
			findMessagePushReturn.setPushDate(messagePush.getPushDate());
			findMessagePushReturn.setMemberNoGm(messagePush.getMemberNoGm());
			findMessagePushReturn.setMemberNameGm(messagePush.getMemberNameGm());
			findMessagePushReturn.setMemberNoMsg(messagePush.getMemberNoMsg());
			findMessagePushReturn.setMemberNamesMsg(messagePush.getMemberNamesMsg());
			findMessagePushReturn.setUpdateId(messagePush.getUpdateId());
			findMessagePushReturn.setUpdateDate(messagePush.getUpdateDate());
			findMessagePushReturn.setCreateId(messagePush.getCreateId());
			findMessagePushReturn.setCreateDate(messagePush.getCreateDate());
			
			logger.debug("findMessagePush(FindMessagePush) - end - return value={}", findMessagePushReturn); //$NON-NLS-1$
			return findMessagePushReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购行为信息记录表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.MESSAGE_PUSH_FIND_ERROR,"查找导购行为信息记录表信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindMessagePushPageReturn> findMessagePushPage(
			FindMessagePushPage findMessagePushPage)
			throws TsfaServiceException {
		logger.debug("findMessagePushPage(FindMessagePushPage findMessagePushPage={}) - start", findMessagePushPage); //$NON-NLS-1$

		AssertUtils.notNull(findMessagePushPage);
		List<FindMessagePushPageReturn> returnList;
		int count = 0;
		try {
			returnList = messagePushDao.findMessagePushPage(findMessagePushPage);
			count = messagePushDao.findMessagePushPageCount(findMessagePushPage);
		}  catch (Exception e) {
			logger.error("导购行为信息记录表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.MESSAGE_PUSH_FIND_PAGE_ERROR,"导购行为信息记录表信息不存在错误.！",e);
		}
		Page<FindMessagePushPageReturn> returnPage = new Page<FindMessagePushPageReturn>(returnList, count, findMessagePushPage);

		logger.debug("findMessagePushPage(FindMessagePushPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}

	@Override
	public List<FindMessagePushPageReturn> findMessagePushByGm(FindMessagePushPage findMessagePushPage) {
		logger.debug("findMessagePushByGm(FindMessagePushPage findMessagePushPage={}) - start", findMessagePushPage); //$NON-NLS-1$
		List<FindMessagePushPageReturn> returnList = messagePushDao.findMessagePushByGm(findMessagePushPage);
		logger.debug("findMessagePushByGm(findMessagePushPage) - end - return value={}", returnList); //$NON-NLS-1$
		return returnList;
	}


	@Override
	public List<FindMessagePushPageReturn> findMessagePushByPm(
			FindMessagePushPage findMessagePushPage) {
		logger.debug("findMessagePushByPm(FindMessagePushPage findMessagePushPage={}) - start", findMessagePushPage); //$NON-NLS-1$
		List<FindMessagePushPageReturn> returnList = messagePushDao.findMessagePushByPm(findMessagePushPage);
		logger.debug("findMessagePushByPm(findMessagePushPage) - end - return value={}", returnList); //$NON-NLS-1$
		return returnList;
	}


}
