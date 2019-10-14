package com.lj.business.cp.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cp.constant.ErrorCode;
import com.lj.business.cp.dao.IAwardVeidooDao;
import com.lj.business.cp.domain.AwardVeidoo;
import com.lj.business.cp.dto.awardVeidoo.AddAwardVeidoo;
import com.lj.business.cp.dto.awardVeidoo.DelAwardVeidoo;
import com.lj.business.cp.dto.awardVeidoo.FindAwardVeidoo;
import com.lj.business.cp.dto.awardVeidoo.FindAwardVeidooPage;
import com.lj.business.cp.dto.awardVeidoo.FindAwardVeidooPageReturn;
import com.lj.business.cp.dto.awardVeidoo.FindAwardVeidooReturn;
import com.lj.business.cp.dto.awardVeidoo.UpdateAwardVeidoo;
import com.lj.business.cp.service.IAwardVeidooService;

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
public class AwardVeidooServiceImpl implements IAwardVeidooService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(AwardVeidooServiceImpl.class);

	@Resource
	private IAwardVeidooDao awardVeidooDao;

	@Override
	public void addAwardVeidoo(AddAwardVeidoo addAwardVeidoo) throws TsfaServiceException {
		logger.debug("addAwardVeidoo(AddAwardVeidoo addAwardVeidoo={}) - start", addAwardVeidoo);

		AssertUtils.notNull(addAwardVeidoo);
		try {
			AwardVeidoo awardVeidoo = new AwardVeidoo();
			// add数据录入
			awardVeidoo.setCode(GUID.getPreUUID());
			awardVeidoo.setMerchantNo(addAwardVeidoo.getMerchantNo());
			awardVeidoo.setMerchantName(addAwardVeidoo.getMerchantName());
//			awardVeidoo.setShopNo(addAwardVeidoo.getShopNo());
//			awardVeidoo.setShopName(addAwardVeidoo.getShopName());
			awardVeidoo.setAwardVeidoo(addAwardVeidoo.getAwardVeidoo());
			awardVeidoo.setUpdateId(addAwardVeidoo.getUpdateId());
			awardVeidoo.setUpdateDate(addAwardVeidoo.getUpdateDate());
			awardVeidoo.setCreateId(addAwardVeidoo.getCreateId());
			awardVeidoo.setCreateDate(addAwardVeidoo.getCreateDate());
			awardVeidooDao.insertSelective(awardVeidoo);
			logger.debug("addAwardVeidoo(AddAwardVeidoo) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增导购行为信息记录表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.AWARD_VEIDOO_ADD_ERROR, "新增导购行为信息记录表信息错误！", e);
		}
	}

	@Override
	public void delAwardVeidoo(DelAwardVeidoo delAwardVeidoo) throws TsfaServiceException {
		logger.debug("delAwardVeidoo(DelAwardVeidoo delAwardVeidoo={}) - start", delAwardVeidoo);

		AssertUtils.notNull(delAwardVeidoo);
		AssertUtils.notNull(delAwardVeidoo.getCode(), "Code不能为空！");
		try {
			awardVeidooDao.deleteByPrimaryKey(delAwardVeidoo.getCode());
			logger.debug("delAwardVeidoo(DelAwardVeidoo) - end - return"); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("删除导购行为信息记录表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.AWARD_VEIDOO_DEL_ERROR, "删除导购行为信息记录表信息错误！", e);

		}
	}

	@Override
	public void updateAwardVeidoo(UpdateAwardVeidoo updateAwardVeidoo) throws TsfaServiceException {
		logger.debug("updateAwardVeidoo(UpdateAwardVeidoo updateAwardVeidoo={}) - start", updateAwardVeidoo); 

		AssertUtils.notNull(updateAwardVeidoo);
		AssertUtils.notNullAndEmpty(updateAwardVeidoo.getCode(), "Code不能为空");
		try {
			AwardVeidoo awardVeidoo = new AwardVeidoo();
			// update数据录入
			awardVeidoo.setCode(updateAwardVeidoo.getCode());
			awardVeidoo.setMerchantNo(updateAwardVeidoo.getMerchantNo());
			awardVeidoo.setMerchantName(updateAwardVeidoo.getMerchantName());
//			awardVeidoo.setShopNo(updateAwardVeidoo.getShopNo());
//			awardVeidoo.setShopName(updateAwardVeidoo.getShopName());
			awardVeidoo.setAwardVeidoo(updateAwardVeidoo.getAwardVeidoo());
			awardVeidoo.setUpdateId(updateAwardVeidoo.getUpdateId());
			awardVeidoo.setUpdateDate(updateAwardVeidoo.getUpdateDate());
			awardVeidoo.setCreateId(updateAwardVeidoo.getCreateId());
			awardVeidoo.setCreateDate(updateAwardVeidoo.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(awardVeidooDao.updateByPrimaryKeySelective(awardVeidoo));
			logger.debug("updateAwardVeidoo(UpdateAwardVeidoo) - end - return"); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("导购行为信息记录表信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.AWARD_VEIDOO_UPDATE_ERROR, "导购行为信息记录表信息更新信息错误！", e);
		}
	}

	@Override
	public FindAwardVeidooReturn findAwardVeidoo(FindAwardVeidoo findAwardVeidoo) throws TsfaServiceException {
		logger.debug("findAwardVeidoo(FindAwardVeidoo findAwardVeidoo={}) - start", findAwardVeidoo); 

		AssertUtils.notNull(findAwardVeidoo);
		AssertUtils.notAllNull(findAwardVeidoo.getCode(), "Code不能为空");
		try {
			AwardVeidoo awardVeidoo = awardVeidooDao.selectByPrimaryKey(findAwardVeidoo.getCode());
			if (awardVeidoo == null) {
				return null;
				// throw new TsfaServiceException(ErrorCode.AWARD_VEIDOO_NOT_EXIST_ERROR,"导购行为信息记录表信息不存在");
			}
			FindAwardVeidooReturn findAwardVeidooReturn = new FindAwardVeidooReturn();
			// find数据录入
			findAwardVeidooReturn.setCode(awardVeidoo.getCode());
			findAwardVeidooReturn.setMerchantNo(awardVeidoo.getMerchantNo());
			findAwardVeidooReturn.setMerchantName(awardVeidoo.getMerchantName());
//			findAwardVeidooReturn.setShopNo(awardVeidoo.getShopNo());
//			findAwardVeidooReturn.setShopName(awardVeidoo.getShopName());
			findAwardVeidooReturn.setAwardVeidoo(awardVeidoo.getAwardVeidoo());
			findAwardVeidooReturn.setUpdateId(awardVeidoo.getUpdateId());
			findAwardVeidooReturn.setUpdateDate(awardVeidoo.getUpdateDate());
			findAwardVeidooReturn.setCreateId(awardVeidoo.getCreateId());
			findAwardVeidooReturn.setCreateDate(awardVeidoo.getCreateDate());

			logger.debug("findAwardVeidoo(FindAwardVeidoo) - end - return value={}", findAwardVeidooReturn); 
			return findAwardVeidooReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购行为信息记录表信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.AWARD_VEIDOO_FIND_ERROR, "查找导购行为信息记录表信息信息错误！", e);
		}

	}

	@Override
	public Page<FindAwardVeidooPageReturn> findAwardVeidooPage(FindAwardVeidooPage findAwardVeidooPage) throws TsfaServiceException {
		//		logger.debug("findAwardVeidooPage(FindAwardVeidooPage findAwardVeidooPage={}) - start", findAwardVeidooPage); 
		//
		// AssertUtils.notNull(findAwardVeidooPage);
		// List<FindAwardVeidooPageReturn> returnList;
		// int count = 0;
		// try {
		// returnList = awardVeidooDao.findAwardVeidooPage(findAwardVeidooPage);
		// count = awardVeidooDao.findAwardVeidooPageCount(findAwardVeidooPage);
		// } catch (Exception e) {
		// logger.error("导购行为信息记录表信息不存在错误", e);
		// throw new TsfaServiceException(ErrorCode.AWARD_VEIDOO_FIND_PAGE_ERROR, "导购行为信息记录表信息不存在错误.！", e);
		// }
		// Page<FindAwardVeidooPageReturn> returnPage = new Page<FindAwardVeidooPageReturn>(returnList, count, findAwardVeidooPage);
		//
		//		logger.debug("findAwardVeidooPage(FindAwardVeidooPage) - end - return value={}", returnPage); 
		// return returnPage;

		return null;
	}

}
