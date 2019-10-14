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
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.cp.constant.ErrorCode;
import com.lj.business.cp.dao.ICouponTypeDao;
import com.lj.business.cp.domain.CouponType;
import com.lj.business.cp.dto.couponType.AddCouponType;
import com.lj.business.cp.dto.couponType.DelCouponType;
import com.lj.business.cp.dto.couponType.FindCouponType;
import com.lj.business.cp.dto.couponType.FindCouponTypePage;
import com.lj.business.cp.dto.couponType.FindCouponTypePageReturn;
import com.lj.business.cp.dto.couponType.FindCouponTypeReturn;
import com.lj.business.cp.dto.couponType.UpdateCouponType;
import com.lj.business.cp.service.ICouponTypeService;

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
public class CouponTypeServiceImpl implements ICouponTypeService {

	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(CouponTypeServiceImpl.class);

	@Resource
	private ICouponTypeDao couponTypeDao;

	@Override
	public void addCouponType(AddCouponType addCouponType) throws TsfaServiceException {
		logger.debug("addCouponType(AddCouponType addCouponType={}) - start", addCouponType);
		AssertUtils.notNull(addCouponType);
		try {
			CouponType couponType = new CouponType();
			// add数据录入
			couponType.setCode(GUID.getPreUUID());
			couponType.setCouponType(addCouponType.getCouponType());
			couponType.setCouponRemark(addCouponType.getCouponRemark());
			couponType.setMerchantNo(addCouponType.getMerchantNo());
			couponType.setMerchantName(addCouponType.getMerchantName());
			couponType.setUseEnable(addCouponType.getUseEnable());
			couponType.setUpdateId(addCouponType.getUpdateId());
			couponType.setUpdateDate(new Date());
			couponType.setCreateId(addCouponType.getCreateId());
			couponType.setCreateDate(new Date());
			couponTypeDao.insertSelective(couponType);
			logger.debug("addCouponType(AddCouponType) - end - return");
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("新增导购行为信息记录表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.COUPON_TYPE_ADD_ERROR, "新增导购行为信息记录表信息错误！", e);
		}
	}

	@Override
	public void delCouponType(DelCouponType delCouponType) throws TsfaServiceException {
		logger.debug("delCouponType(DelCouponType delCouponType={}) - start", delCouponType);

		AssertUtils.notNull(delCouponType);
		AssertUtils.notNull(delCouponType.getCode(), "Code不能为空！");
		try {
			couponTypeDao.deleteByPrimaryKey(delCouponType.getCode());
			logger.debug("delCouponType(DelCouponType) - end - return"); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("删除导购行为信息记录表信息错误！", e);
			throw new TsfaServiceException(ErrorCode.COUPON_TYPE_DEL_ERROR, "删除导购行为信息记录表信息错误！", e);

		}
	}

	@Override
	public void updateCouponType(UpdateCouponType updateCouponType) throws TsfaServiceException {
		logger.debug("updateCouponType(UpdateCouponType updateCouponType={}) - start", updateCouponType); 

		AssertUtils.notNull(updateCouponType);
		AssertUtils.notNullAndEmpty(updateCouponType.getCode(), "Code不能为空");
		try {
			CouponType couponType = new CouponType();
			// update数据录入
			couponType.setCode(updateCouponType.getCode());
			couponType.setCouponType(updateCouponType.getCouponType());
			couponType.setCouponRemark(updateCouponType.getCouponRemark());
			couponType.setMerchantNo(updateCouponType.getMerchantNo());
			couponType.setMerchantName(updateCouponType.getMerchantName());
			couponType.setUseEnable(updateCouponType.getUseEnable());
			couponType.setUpdateId(updateCouponType.getUpdateId());
			couponType.setUpdateDate(new Date());
			couponType.setCreateId(updateCouponType.getCreateId());
			couponType.setCreateDate(new Date());
			AssertUtils.notUpdateMoreThanOne(couponTypeDao.updateByPrimaryKeySelective(couponType));
			logger.debug("updateCouponType(UpdateCouponType) - end - return"); 
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("导购行为信息记录表信息更新信息错误！", e);
			throw new TsfaServiceException(ErrorCode.COUPON_TYPE_UPDATE_ERROR, "导购行为信息记录表信息更新信息错误！", e);
		}
	}

	@Override
	public FindCouponTypeReturn findCouponType(FindCouponType findCouponType) throws TsfaServiceException {
		logger.debug("findCouponType(FindCouponType findCouponType={}) - start", findCouponType); 

		AssertUtils.notNull(findCouponType);
		AssertUtils.notAllNull(findCouponType.getCode(), "Code不能为空");
		try {
			CouponType couponType = couponTypeDao.selectByPrimaryKey(findCouponType.getCode());
			if (couponType == null) {
				return null;
				// throw new TsfaServiceException(ErrorCode.COUPON_TYPE_NOT_EXIST_ERROR,"导购行为信息记录表信息不存在");
			}
			FindCouponTypeReturn findCouponTypeReturn = new FindCouponTypeReturn();
			// find数据录入
			findCouponTypeReturn.setCode(couponType.getCode());
			findCouponTypeReturn.setCouponType(couponType.getCouponType());
			findCouponTypeReturn.setCouponRemark(couponType.getCouponRemark());
			findCouponTypeReturn.setMerchantNo(couponType.getMerchantNo());
			findCouponTypeReturn.setMerchantName(couponType.getMerchantName());
			findCouponTypeReturn.setUseEnable(couponType.getUseEnable());
			findCouponTypeReturn.setUpdateId(couponType.getUpdateId());
			findCouponTypeReturn.setUpdateDate(couponType.getUpdateDate());
			findCouponTypeReturn.setCreateId(couponType.getCreateId());
			findCouponTypeReturn.setCreateDate(couponType.getCreateDate());

			logger.debug("findCouponType(FindCouponType) - end - return value={}", findCouponTypeReturn); 
			return findCouponTypeReturn;
		} catch (TsfaServiceException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购行为信息记录表信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.COUPON_TYPE_FIND_ERROR, "查找导购行为信息记录表信息信息错误！", e);
		}

	}

	@Override
	public Page<FindCouponTypePageReturn> findCouponTypePage(FindCouponTypePage findCouponTypePage) throws TsfaServiceException {
				logger.debug("findCouponTypePage(FindCouponTypePage findCouponTypePage={}) - start", findCouponTypePage); 
		
		 AssertUtils.notNull(findCouponTypePage);
		 List<FindCouponTypePageReturn> returnList=null;
		 int count = 0;
		 try {
		 returnList = couponTypeDao.findCouponTypePage(findCouponTypePage);
		 count = couponTypeDao.findCouponTypePageCount(findCouponTypePage);
		 } catch (Exception e) {
		 logger.error("导购行为信息记录表信息不存在错误",e);
		 throw new TsfaServiceException(ErrorCode.COUPON_TYPE_FIND_PAGE_ERROR,"导购行为信息记录表信息不存在错误.！",e);
		 }
		 Page<FindCouponTypePageReturn> returnPage = new Page<FindCouponTypePageReturn>(returnList, count, findCouponTypePage);
		
		 logger.debug("findCouponTypePage(FindCouponTypePage) - end - return value={}", returnPage); 
		 return returnPage;
	}

	@Override
	public List<FindCouponTypeReturn> findCouponTypeList(FindCouponType findCouponType) throws TsfaServiceException {
		return couponTypeDao.findCouponTypeList(findCouponType);
	}

}
