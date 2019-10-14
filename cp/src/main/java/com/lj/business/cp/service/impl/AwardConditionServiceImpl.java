package com.lj.business.cp.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
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
import com.lj.business.cp.constant.ErrorCode;
import com.lj.business.cp.dao.IAwardConditionDao;
import com.lj.business.cp.domain.AwardCondition;
import com.lj.business.cp.dto.awardCondition.AddAwardCondition;
import com.lj.business.cp.dto.awardCondition.DelAwardCondition;
import com.lj.business.cp.dto.awardCondition.FindAwardCondition;
import com.lj.business.cp.dto.awardCondition.FindAwardConditionPage;
import com.lj.business.cp.dto.awardCondition.FindAwardConditionPageReturn;
import com.lj.business.cp.dto.awardCondition.FindAwardConditionReturn;
import com.lj.business.cp.dto.awardCondition.UpdateAwardCondition;
import com.lj.business.cp.service.IAwardConditionService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 杨杰
 * 
 * 
 *         CreateDate: 2017-06-14
 */
@Service
public class AwardConditionServiceImpl implements IAwardConditionService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(AwardConditionServiceImpl.class);

	@Autowired
	private IAwardConditionDao awardConditionDao;
    
	@Override
	public void addAwardCondition(AddAwardCondition addAwardCondition) throws TsfaServiceException {
		logger.debug("addAwardCondition(AddAwardCondition addAwardCondition={}) - start", addAwardCondition);

		AssertUtils.notNull(addAwardCondition);
		try {
			AwardCondition awardCondition = new AwardCondition();
			// add数据录入
			awardCondition.setCode(GUID.getPreUUID());
			awardCondition.setMerchantNo(addAwardCondition.getMerchantNo());
			awardCondition.setMerchantName(addAwardCondition.getMerchantName());
//			awardCondition.setShopNo(addAwardCondition.getShopNo());
//			awardCondition.setShopName(addAwardCondition.getShopName());
			awardCondition.setVeidooNo(addAwardCondition.getVeidooNo());
			awardCondition.setVeidooUp(addAwardCondition.getVeidooUp());
			awardCondition.setVeidooDown(addAwardCondition.getVeidooDown());
			awardCondition.setBeginDate(addAwardCondition.getBeginDate());
			awardCondition.setEndDate(addAwardCondition.getEndDate());
			awardCondition.setActivityDesc(addAwardCondition.getActivityDesc());
			awardCondition.setUpdateId(addAwardCondition.getUpdateId());
			awardCondition.setUpdateDate(new Date());
			awardCondition.setCreateId(addAwardCondition.getCreateId());
			awardCondition.setCreateDate(new Date());
			awardConditionDao.insert(awardCondition);
			logger.debug("addAwardCondition(AddAwardCondition) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增导购行为信息记录表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.AWARD_CONDITION_ADD_ERROR, "新增导购行为信息记录表信息错误！", e);
		}
	}

	@Override
	public void delAwardCondition(DelAwardCondition delAwardCondition) throws TsfaServiceException {
		logger.debug("delAwardCondition(DelAwardCondition delAwardCondition={}) - start", delAwardCondition);

		AssertUtils.notNull(delAwardCondition);
		AssertUtils.notNull(delAwardCondition.getCode(), "Code不能为空！");
		try {
			awardConditionDao.deleteByPrimaryKey(delAwardCondition.getCode());
			logger.debug("delAwardCondition(DelAwardCondition) - end - return"); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("删除导购行为信息记录表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.AWARD_CONDITION_DEL_ERROR, "删除导购行为信息记录表信息错误！", e);

		}
	}

	@Override
	public void updateAwardCondition(UpdateAwardCondition updateAwardCondition) throws TsfaServiceException {
		logger.debug("updateAwardCondition(UpdateAwardCondition updateAwardCondition={}) - start", updateAwardCondition); 

		AssertUtils.notNull(updateAwardCondition);
		AssertUtils.notNullAndEmpty(updateAwardCondition.getCode(), "Code不能为空");
		try {
			AwardCondition awardCondition = new AwardCondition();
			// update数据录入
			awardCondition.setCode(updateAwardCondition.getCode());
			awardCondition.setMerchantNo(updateAwardCondition.getMerchantNo());
			awardCondition.setMerchantName(updateAwardCondition.getMerchantName());
//			awardCondition.setShopNo(updateAwardCondition.getShopNo());
//			awardCondition.setShopName(updateAwardCondition.getShopName());
			awardCondition.setVeidooNo(updateAwardCondition.getVeidooNo());
			awardCondition.setVeidooUp(updateAwardCondition.getVeidooUp());
			awardCondition.setVeidooDown(updateAwardCondition.getVeidooDown());
			awardCondition.setBeginDate(updateAwardCondition.getBeginDate());
			awardCondition.setEndDate(updateAwardCondition.getEndDate());
			awardCondition.setActivityDesc(updateAwardCondition.getActivityDesc());
			awardCondition.setUpdateId(updateAwardCondition.getUpdateId());
			awardCondition.setUpdateDate(updateAwardCondition.getUpdateDate());
			awardCondition.setCreateId(updateAwardCondition.getCreateId());
			awardCondition.setCreateDate(updateAwardCondition.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(awardConditionDao.updateByPrimaryKeySelective(awardCondition));
			logger.debug("updateAwardCondition(UpdateAwardCondition) - end - return"); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("导购行为信息记录表信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.AWARD_CONDITION_UPDATE_ERROR, "导购行为信息记录表信息更新信息错误！", e);
		}
	}

	@Override
	public FindAwardConditionReturn findAwardCondition(FindAwardCondition findAwardCondition) throws TsfaServiceException {
		logger.debug("findAwardCondition(FindAwardCondition findAwardCondition={}) - start", findAwardCondition); 

		AssertUtils.notNull(findAwardCondition);
		AssertUtils.notAllNull(findAwardCondition.getCode(), "Code不能为空");
		try {
			AwardCondition awardCondition = awardConditionDao.selectByPrimaryKey(findAwardCondition.getCode());
			if (awardCondition == null) {
				return null;
				// throw new TsfaServiceException(ErrorCode.AWARD_CONDITION_NOT_EXIST_ERROR,"导购行为信息记录表信息不存在");
			}
			FindAwardConditionReturn findAwardConditionReturn = new FindAwardConditionReturn();
			// find数据录入
			findAwardConditionReturn.setCode(awardCondition.getCode());
			findAwardConditionReturn.setMerchantNo(awardCondition.getMerchantNo());
			findAwardConditionReturn.setMerchantName(awardCondition.getMerchantName());
//			findAwardConditionReturn.setShopNo(awardCondition.getShopNo());
//			findAwardConditionReturn.setShopName(awardCondition.getShopName());
			findAwardConditionReturn.setVeidooNo(awardCondition.getVeidooNo());
			findAwardConditionReturn.setVeidooUp(awardCondition.getVeidooUp());
			findAwardConditionReturn.setVeidooDown(awardCondition.getVeidooDown());
			findAwardConditionReturn.setBeginDate(awardCondition.getBeginDate());
			findAwardConditionReturn.setEndDate(awardCondition.getEndDate());
			findAwardConditionReturn.setActivityDesc(awardCondition.getActivityDesc());
			findAwardConditionReturn.setUpdateId(awardCondition.getUpdateId());
			findAwardConditionReturn.setUpdateDate(awardCondition.getUpdateDate());
			findAwardConditionReturn.setCreateId(awardCondition.getCreateId());
			findAwardConditionReturn.setCreateDate(awardCondition.getCreateDate());

			logger.debug("findAwardCondition(FindAwardCondition) - end - return value={}", findAwardConditionReturn); 
			return findAwardConditionReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购行为信息记录表信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.AWARD_CONDITION_FIND_ERROR, "查找导购行为信息记录表信息信息错误！", e);
		}

	}

	@Override
	public Page<FindAwardConditionPageReturn> findAwardConditionPage(FindAwardConditionPage findAwardConditionPage) throws TsfaServiceException {
		logger.debug("findAwardConditionPage(FindAwardConditionPage findAwardConditionPage={}) - start", findAwardConditionPage); 
		 AssertUtils.notNull(findAwardConditionPage);
		 List<FindAwardConditionPageReturn> returnList=null;
		 int count = 0;
		 try {
		 returnList = awardConditionDao.findAwardConditionPage(findAwardConditionPage);
		 count = awardConditionDao.findAwardConditionPageCount(findAwardConditionPage);
		 } catch (Exception e) {
		 logger.error("导购行为信息记录表信息不存在错误", e);
		 throw new TsfaServiceException(ErrorCode.AWARD_CONDITION_FIND_PAGE_ERROR, "导购行为信息记录表信息不存在错误.！", e);
		 }
		 Page<FindAwardConditionPageReturn> returnPage = new Page<FindAwardConditionPageReturn>(returnList, count, findAwardConditionPage);
		
				logger.debug("findAwardConditionPage(FindAwardConditionPage) - end - return value={}", returnPage); 
		 return returnPage;
	}

}
