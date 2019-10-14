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
import com.lj.business.cp.couponRecords.AddCouponRecords;
import com.lj.business.cp.couponRecords.AddCouponRecordsReturn;
import com.lj.business.cp.couponRecords.DelCouponRecords;
import com.lj.business.cp.couponRecords.DelCouponRecordsReturn;
import com.lj.business.cp.couponRecords.FindCouponRecords;
import com.lj.business.cp.couponRecords.FindCouponRecordsPage;
import com.lj.business.cp.couponRecords.FindCouponRecordsPageReturn;
import com.lj.business.cp.couponRecords.FindCouponRecordsReturn;
import com.lj.business.cp.couponRecords.UpdateCouponRecords;
import com.lj.business.cp.couponRecords.UpdateCouponRecordsReturn;
import com.lj.business.cp.dao.ICouponRecordsDao;
import com.lj.business.cp.domain.CouponRecords;
import com.lj.business.cp.service.ICouponRecordsService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 * CreateDate: 2017-06-14
 */
@Service
public class CouponRecordsServiceImpl implements ICouponRecordsService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(CouponRecordsServiceImpl.class);
	
  
	@Resource
	private ICouponRecordsDao couponRecordsDao;
	
	
	@Override
	public AddCouponRecordsReturn addCouponRecords(
			AddCouponRecords addCouponRecords) throws TsfaServiceException {
		logger.debug("addCouponRecords(AddCouponRecords addCouponRecords={}) - start", addCouponRecords); 

		AssertUtils.notNull(addCouponRecords);
		try {
			CouponRecords couponRecords = new CouponRecords();
			//add数据录入
			couponRecords.setCode(GUID.getPreUUID());
			couponRecords.setMerchantNo(addCouponRecords.getMerchantNo());
//			couponRecords.setShopNo(addCouponRecords.getShopNo());
//			couponRecords.setShopName(addCouponRecords.getShopName());
			couponRecords.setMemberNo(addCouponRecords.getMemberNo());
			couponRecords.setMemberName(addCouponRecords.getMemberName());
			couponRecords.setAddFriendsCode(addCouponRecords.getAddFriendsCode());
			couponRecords.setNickName(addCouponRecords.getNickName());
			couponRecords.setCouponNo(addCouponRecords.getCouponNo());
			couponRecords.setCouponName(addCouponRecords.getCouponName());
			couponRecords.setUseDate(new Date());
			couponRecords.setCreateId(addCouponRecords.getCreateId());
			couponRecords.setCreateDate(new Date());
			couponRecordsDao.insertSelective(couponRecords);
			AddCouponRecordsReturn addCouponRecordsReturn = new AddCouponRecordsReturn();
			
			logger.debug("addCouponRecords(AddCouponRecords) - end - return value={}", addCouponRecordsReturn); 
			return addCouponRecordsReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增消费记录表信息错误",e);
			throw new TsfaServiceException(ErrorCode.COUPON_RECORDS_ADD_ERROR,"新增消费记录表信息错误",e);
		}
	}
	
	
	@Override
	public DelCouponRecordsReturn delCouponRecords(
			DelCouponRecords delCouponRecords) throws TsfaServiceException {
		logger.debug("delCouponRecords(DelCouponRecords delCouponRecords={}) - start", delCouponRecords); 

		AssertUtils.notNull(delCouponRecords);
		AssertUtils.notNull(delCouponRecords.getCode(),"CODE不能为空！");
		try {
			couponRecordsDao.deleteByPrimaryKey(delCouponRecords.getCode());
			DelCouponRecordsReturn delCouponRecordsReturn  = new DelCouponRecordsReturn();

			logger.debug("delCouponRecords(DelCouponRecords) - end - return value={}", delCouponRecordsReturn); 
			return delCouponRecordsReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除消费记录表信息错误",e);
			throw new TsfaServiceException(ErrorCode.COUPON_RECORDS_DEL_ERROR,"删除消费记录表信息错误",e);

		}
	}

	@Override
	public UpdateCouponRecordsReturn updateCouponRecords(
			UpdateCouponRecords updateCouponRecords)
			throws TsfaServiceException {
		logger.debug("updateCouponRecords(UpdateCouponRecords updateCouponRecords={}) - start", updateCouponRecords); 

		AssertUtils.notNull(updateCouponRecords);
		AssertUtils.notNullAndEmpty(updateCouponRecords.getCode(),"CODE不能为空");
		try {
			CouponRecords couponRecords = new CouponRecords();
			//update数据录入
			couponRecords.setCode(updateCouponRecords.getCode());
			couponRecords.setMerchantNo(updateCouponRecords.getMerchantNo());
//			couponRecords.setShopNo(updateCouponRecords.getShopNo());
//			couponRecords.setShopName(updateCouponRecords.getShopName());
			couponRecords.setMemberNo(updateCouponRecords.getMemberNo());
			couponRecords.setMemberName(updateCouponRecords.getMemberName());
			couponRecords.setAddFriendsCode(updateCouponRecords.getAddFriendsCode());
			couponRecords.setNickName(updateCouponRecords.getNickName());
			couponRecords.setCouponNo(updateCouponRecords.getCouponNo());
			couponRecords.setCouponName(updateCouponRecords.getCouponName());
			couponRecords.setUseDate(updateCouponRecords.getUseDate());
			couponRecords.setCreateId(updateCouponRecords.getCreateId());
			couponRecords.setCreateDate(updateCouponRecords.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(couponRecordsDao.updateByPrimaryKeySelective(couponRecords));
			UpdateCouponRecordsReturn updateCouponRecordsReturn = new UpdateCouponRecordsReturn();

			logger.debug("updateCouponRecords(UpdateCouponRecords) - end - return value={}", updateCouponRecordsReturn); 
			return updateCouponRecordsReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("修改消费记录表信息错误",e);
			throw new TsfaServiceException(ErrorCode.COUPON_RECORDS_UPDATE_ERROR,"修改消费记录表信息错误",e);
		}
	}

	

	@Override
	public FindCouponRecordsReturn findCouponRecords(
			FindCouponRecords findCouponRecords) throws TsfaServiceException {
		logger.debug("findCouponRecords(FindCouponRecords findCouponRecords={}) - start", findCouponRecords); 

		AssertUtils.notNull(findCouponRecords);
		AssertUtils.notAllNull(findCouponRecords.getCode(),"CODE不能为空");
		try {
			CouponRecords couponRecords = couponRecordsDao.selectByPrimaryKey(findCouponRecords.getCode());
			if(couponRecords == null){
				throw new TsfaServiceException(ErrorCode.COUPON_RECORDS_NOT_EXIST_ERROR,"消费记录表信息不存在错误");
			}
			FindCouponRecordsReturn findCouponRecordsReturn = new FindCouponRecordsReturn();
			//find数据录入
			findCouponRecordsReturn.setCode(couponRecords.getCode());
			findCouponRecordsReturn.setMerchantNo(couponRecords.getMerchantNo());
//			findCouponRecordsReturn.setShopNo(couponRecords.getShopNo());
//			findCouponRecordsReturn.setShopName(couponRecords.getShopName());
			findCouponRecordsReturn.setMemberNo(couponRecords.getMemberNo());
			findCouponRecordsReturn.setMemberName(couponRecords.getMemberName());
			findCouponRecordsReturn.setAddFriendsCode(couponRecords.getAddFriendsCode());
			findCouponRecordsReturn.setNickName(couponRecords.getNickName());
			findCouponRecordsReturn.setCouponNo(couponRecords.getCouponNo());
			findCouponRecordsReturn.setCouponName(couponRecords.getCouponName());
			findCouponRecordsReturn.setUseDate(couponRecords.getUseDate());
			findCouponRecordsReturn.setCreateId(couponRecords.getCreateId());
			findCouponRecordsReturn.setCreateDate(couponRecords.getCreateDate());
			
			logger.debug("findCouponRecords(FindCouponRecords) - end - return value={}", findCouponRecordsReturn); 
			return findCouponRecordsReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("消费记录表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.COUPON_RECORDS_FIND_ERROR,"消费记录表信息不存在错误",e);
		}


	}

	
	@Override
	public Page<FindCouponRecordsPageReturn> findCouponRecordsPage(
			FindCouponRecordsPage findCouponRecordsPage)
			throws TsfaServiceException {
		logger.debug("findCouponRecordsPage(FindCouponRecordsPage findCouponRecordsPage={}) - start", findCouponRecordsPage); 

		AssertUtils.notNull(findCouponRecordsPage);
		List<FindCouponRecordsPageReturn> returnList=null;
		int count = 0;
		try {
			count = couponRecordsDao.findCouponRecordsPageCount(findCouponRecordsPage);
			if(count >0) {
				returnList = couponRecordsDao.findCouponRecordsPage(findCouponRecordsPage);
			}
		}  catch (Exception e) {
			logger.error("消费记录表信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.COUPON_RECORDS_FIND_PAGE_ERROR,"消费记录表信息不存在错误",e);
		}
		Page<FindCouponRecordsPageReturn> returnPage = new Page<FindCouponRecordsPageReturn>(returnList, count, findCouponRecordsPage);

		logger.debug("findCouponRecordsPage(FindCouponRecordsPage) - end - return value={}", returnPage); 
		return  returnPage;
	} 


}
