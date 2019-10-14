package com.lj.business.cf.service.impl;

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
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cf.constant.ErrorCode;
import com.lj.business.cf.dao.IMemberMessageRelationDao;
import com.lj.business.cf.domain.MemberMessageRelation;
import com.lj.business.cf.dto.memberMessageRelation.AddMemberMessageRelation;
import com.lj.business.cf.dto.memberMessageRelation.DelMemberMessageRelation;
import com.lj.business.cf.dto.memberMessageRelation.FindMemberMessageRelation;
import com.lj.business.cf.dto.memberMessageRelation.FindMemberMessageRelationPage;
import com.lj.business.cf.dto.memberMessageRelation.FindMemberMessageRelationPageReturn;
import com.lj.business.cf.dto.memberMessageRelation.FindMemberMessageRelationReturn;
import com.lj.business.cf.dto.memberMessageRelation.UpdateMemberMessageRelation;
import com.lj.business.cf.service.IMemberMessageRelationService;

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
public class MemberMessageRelationServiceImpl implements IMemberMessageRelationService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(MemberMessageRelationServiceImpl.class);
	

	@Resource
	private IMemberMessageRelationDao memberMessageRelationDao;
	
	
	@Override
	public void addMemberMessageRelation(
			AddMemberMessageRelation addMemberMessageRelation) throws TsfaServiceException {
		logger.debug("addMemberMessageRelation(AddMemberMessageRelation addMemberMessageRelation={}) - start", addMemberMessageRelation); 

		AssertUtils.notNull(addMemberMessageRelation);
		try {
			MemberMessageRelation memberMessageRelation = new MemberMessageRelation();
			//add数据录入
			memberMessageRelation.setCode(addMemberMessageRelation.getCode());
			memberMessageRelation.setMsgNo(addMemberMessageRelation.getMsgNo());
			memberMessageRelation.setMerchantNo(addMemberMessageRelation.getMerchantNo());
			memberMessageRelation.setMerchantName(addMemberMessageRelation.getMerchantName());
			memberMessageRelation.setShopNo(addMemberMessageRelation.getShopNo());
			memberMessageRelation.setShopName(addMemberMessageRelation.getShopName());
			memberMessageRelation.setMemberNoGm(addMemberMessageRelation.getMemberNoGm());
			memberMessageRelation.setMemberNameGm(addMemberMessageRelation.getMemberNameGm());
			memberMessageRelation.setMemberNo(addMemberMessageRelation.getMemberNo());
			memberMessageRelation.setMemberName(addMemberMessageRelation.getMemberName());
			memberMessageRelation.setMemberWxNo(addMemberMessageRelation.getMemberWxNo());
			memberMessageRelation.setPushDate(addMemberMessageRelation.getPushDate());
			memberMessageRelation.setSuccessDate(addMemberMessageRelation.getSuccessDate());
			memberMessageRelation.setMsgStatus(addMemberMessageRelation.getMsgStatus());
			memberMessageRelation.setUpdateId(addMemberMessageRelation.getUpdateId());
			memberMessageRelation.setUpdateDate(addMemberMessageRelation.getUpdateDate());
			memberMessageRelation.setCreateId(addMemberMessageRelation.getCreateId());
			memberMessageRelation.setCreateDate(addMemberMessageRelation.getCreateDate());
			memberMessageRelationDao.insert(memberMessageRelation);
			logger.debug("addMemberMessageRelation(AddMemberMessageRelation) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增用户消息关联表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.MEMBER_MESSAGE_RELATION_ADD_ERROR,"新增用户消息关联表信息错误！",e);
		}
	}
	
	
	@Override
	public void delMemberMessageRelation(
			DelMemberMessageRelation delMemberMessageRelation) throws TsfaServiceException {
		logger.debug("delMemberMessageRelation(DelMemberMessageRelation delMemberMessageRelation={}) - start", delMemberMessageRelation); 

		AssertUtils.notNull(delMemberMessageRelation);
		AssertUtils.notNull(delMemberMessageRelation.getCode(),"Code不能为空！");
		try {
			memberMessageRelationDao.deleteByPrimaryKey(delMemberMessageRelation.getCode());
			logger.debug("delMemberMessageRelation(DelMemberMessageRelation) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除用户消息关联表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.MEMBER_MESSAGE_RELATION_DEL_ERROR,"删除用户消息关联表信息错误！",e);

		}
	}

	@Override
	public void updateMemberMessageRelation(
			UpdateMemberMessageRelation updateMemberMessageRelation)
			throws TsfaServiceException {
		logger.debug("updateMemberMessageRelation(UpdateMemberMessageRelation updateMemberMessageRelation={}) - start", updateMemberMessageRelation); //$NON-NLS-1$

		AssertUtils.notNull(updateMemberMessageRelation);
		AssertUtils.notNullAndEmpty(updateMemberMessageRelation.getMsgNo(),"消息编号不能为空");
		AssertUtils.notNullAndEmpty(updateMemberMessageRelation.getMsgStatus(),"消息状态不能为空");
		try {
			MemberMessageRelation memberMessageRelation = new MemberMessageRelation();
			//update数据录入
			memberMessageRelation.setCode(updateMemberMessageRelation.getCode());
			memberMessageRelation.setMsgNo(updateMemberMessageRelation.getMsgNo());
			memberMessageRelation.setMerchantNo(updateMemberMessageRelation.getMerchantNo());
			memberMessageRelation.setMerchantName(updateMemberMessageRelation.getMerchantName());
			memberMessageRelation.setShopNo(updateMemberMessageRelation.getShopNo());
			memberMessageRelation.setShopName(updateMemberMessageRelation.getShopName());
			memberMessageRelation.setMemberNoGm(updateMemberMessageRelation.getMemberNoGm());
			memberMessageRelation.setMemberNameGm(updateMemberMessageRelation.getMemberNameGm());
			memberMessageRelation.setMemberNo(updateMemberMessageRelation.getMemberNo());
			memberMessageRelation.setMemberName(updateMemberMessageRelation.getMemberName());
			memberMessageRelation.setMemberWxNo(updateMemberMessageRelation.getMemberWxNo());
			memberMessageRelation.setPushDate(updateMemberMessageRelation.getPushDate());
			memberMessageRelation.setSuccessDate(updateMemberMessageRelation.getSuccessDate());
			memberMessageRelation.setMsgStatus(updateMemberMessageRelation.getMsgStatus());
			memberMessageRelation.setUpdateId(updateMemberMessageRelation.getUpdateId());
			memberMessageRelation.setUpdateDate(updateMemberMessageRelation.getUpdateDate());
			memberMessageRelation.setCreateId(updateMemberMessageRelation.getCreateId());
			memberMessageRelation.setCreateDate(updateMemberMessageRelation.getCreateDate());
			memberMessageRelationDao.updateByPrimaryKeySelective(memberMessageRelation);
			logger.debug("updateMemberMessageRelation(UpdateMemberMessageRelation) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("用户消息关联表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.MEMBER_MESSAGE_RELATION_UPDATE_ERROR,"用户消息关联表信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindMemberMessageRelationReturn findMemberMessageRelation(
			FindMemberMessageRelation findMemberMessageRelation) throws TsfaServiceException {
		logger.debug("findMemberMessageRelation(FindMemberMessageRelation findMemberMessageRelation={}) - start", findMemberMessageRelation); //$NON-NLS-1$

		AssertUtils.notNull(findMemberMessageRelation);
		AssertUtils.notAllNull(findMemberMessageRelation.getCode(),"Code不能为空");
		try {
			MemberMessageRelation memberMessageRelation = memberMessageRelationDao.selectByPrimaryKey(findMemberMessageRelation.getCode());
			if(memberMessageRelation == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.MEMBER_MESSAGE_RELATION_NOT_EXIST_ERROR,"用户消息关联表信息不存在");
			}
			FindMemberMessageRelationReturn findMemberMessageRelationReturn = new FindMemberMessageRelationReturn();
			//find数据录入
			findMemberMessageRelationReturn.setCode(memberMessageRelation.getCode());
			findMemberMessageRelationReturn.setMsgNo(memberMessageRelation.getMsgNo());
			findMemberMessageRelationReturn.setMerchantNo(memberMessageRelation.getMerchantNo());
			findMemberMessageRelationReturn.setMerchantName(memberMessageRelation.getMerchantName());
			findMemberMessageRelationReturn.setShopNo(memberMessageRelation.getShopNo());
			findMemberMessageRelationReturn.setShopName(memberMessageRelation.getShopName());
			findMemberMessageRelationReturn.setMemberNoGm(memberMessageRelation.getMemberNoGm());
			findMemberMessageRelationReturn.setMemberNameGm(memberMessageRelation.getMemberNameGm());
			findMemberMessageRelationReturn.setMemberNo(memberMessageRelation.getMemberNo());
			findMemberMessageRelationReturn.setMemberName(memberMessageRelation.getMemberName());
			findMemberMessageRelationReturn.setMemberWxNo(memberMessageRelation.getMemberWxNo());
			findMemberMessageRelationReturn.setPushDate(memberMessageRelation.getPushDate());
			findMemberMessageRelationReturn.setSuccessDate(memberMessageRelation.getSuccessDate());
			findMemberMessageRelationReturn.setMsgStatus(memberMessageRelation.getMsgStatus());
			findMemberMessageRelationReturn.setUpdateId(memberMessageRelation.getUpdateId());
			findMemberMessageRelationReturn.setUpdateDate(memberMessageRelation.getUpdateDate());
			findMemberMessageRelationReturn.setCreateId(memberMessageRelation.getCreateId());
			findMemberMessageRelationReturn.setCreateDate(memberMessageRelation.getCreateDate());
			
			logger.debug("findMemberMessageRelation(FindMemberMessageRelation) - end - return value={}", findMemberMessageRelationReturn); //$NON-NLS-1$
			return findMemberMessageRelationReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找用户消息关联表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.MEMBER_MESSAGE_RELATION_FIND_ERROR,"查找用户消息关联表信息信息错误！",e);
		}


	}

	
	@Override
	public Page<FindMemberMessageRelationPageReturn> findMemberMessageRelationPage(
			FindMemberMessageRelationPage findMemberMessageRelationPage)
			throws TsfaServiceException {
		logger.debug("findMemberMessageRelationPage(FindMemberMessageRelationPage findMemberMessageRelationPage={}) - start", findMemberMessageRelationPage); //$NON-NLS-1$

		AssertUtils.notNull(findMemberMessageRelationPage);
		List<FindMemberMessageRelationPageReturn> returnList;
		int count = 0;
		try {
			returnList = memberMessageRelationDao.findMemberMessageRelationPage(findMemberMessageRelationPage);
			count = memberMessageRelationDao.findMemberMessageRelationPageCount(findMemberMessageRelationPage);
		}  catch (Exception e) {
			logger.error("用户消息关联表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.MEMBER_MESSAGE_RELATION_FIND_PAGE_ERROR,"用户消息关联表信息不存在错误.！",e);
		}
		Page<FindMemberMessageRelationPageReturn> returnPage = new Page<FindMemberMessageRelationPageReturn>(returnList, count, findMemberMessageRelationPage);

		logger.debug("findMemberMessageRelationPage(FindMemberMessageRelationPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	@Override
	public List<FindMemberMessageRelationReturn> findMemberMessageRelationByMerDay(
			FindMemberMessageRelation findMemberMessageRelation) {
		logger.debug("findMemberMessageRelationByMerDay(FindMemberMessageRelation findMemberMessageRelation={}) - start", findMemberMessageRelation); //$NON-NLS-1$

		AssertUtils.notNull(findMemberMessageRelation);
		List<FindMemberMessageRelationReturn> memberMessageList;
		try {
			memberMessageList = memberMessageRelationDao.findMemberMessageRelationByMerDay(findMemberMessageRelation);
		}  catch (Exception e) {
			logger.error("查找用户消息关联表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.MEMBER_MESSAGE_RELATION_FIND_ERROR,"查找用户消息关联表信息信息错误！",e);
		}

		logger.debug("findMemberMessageRelationByMerDay(FindMemberMessageRelationByMerDay) - end - return value={}", memberMessageList); //$NON-NLS-1$
		return memberMessageList;
	} 


}
