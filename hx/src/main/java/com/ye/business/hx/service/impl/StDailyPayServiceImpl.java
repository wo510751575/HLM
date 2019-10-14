package com.ye.business.hx.service.impl;

import java.util.ArrayList;
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
import org.springframework.stereotype.Service;

import com.lj.base.core.pagination.Page;
import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.ye.business.hx.constant.ErrorCode;
import com.ye.business.hx.dao.IStDailyPayDao;
import com.ye.business.hx.domain.StDailyPay;
import com.ye.business.hx.dto.FindStDailyPayPage;
import com.ye.business.hx.dto.StDailyPayDto;
import com.ye.business.hx.service.IStDailyPayService;
/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 * CreateDate: 2019.02.19
 */
@Service
public class StDailyPayServiceImpl implements IStDailyPayService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(StDailyPayServiceImpl.class);
	

	@Resource
	private IStDailyPayDao stDailyPayDao;
	
	
	@Override
	public void addStDailyPay(
			StDailyPayDto stDailyPayDto) throws TsfaServiceException {
		logger.debug("addStDailyPay(AddStDailyPay addStDailyPay={}) - start", stDailyPayDto); 

		AssertUtils.notNull(stDailyPayDto);
		try {
			StDailyPay stDailyPay = new StDailyPay();
			//add数据录入
			stDailyPay.setCode(GUID.getPreUUID());
			stDailyPay.setShopNo(stDailyPayDto.getShopNo());
			stDailyPay.setShopName(stDailyPayDto.getShopName());
			stDailyPay.setMerchantNo(stDailyPayDto.getMerchantNo());
			stDailyPay.setMerchantName(stDailyPayDto.getMerchantName());
			stDailyPay.setStDate(stDailyPayDto.getStDate());
			stDailyPay.setPayType(stDailyPayDto.getPayType());
			stDailyPay.setPayTypeName(stDailyPayDto.getPayTypeName());
			stDailyPay.setPayAmount(stDailyPayDto.getPayAmount());
			stDailyPay.setPayMode(stDailyPayDto.getPayMode());
			stDailyPay.setCreateDate(new Date());
			stDailyPayDao.insertSelective(stDailyPay);
			logger.debug("addStDailyPay(StDailyPayDto) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增每日收费统计信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ST_DAILY_PAY_ADD_ERROR,"新增每日收费统计信息错误！",e);
		}
	}
	
	
 	/**
	 * 
	 *
	 * 方法说明：不分页查询每日收费统计信息
	 *
	 * @param findStDailyPayPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	public List<StDailyPayDto>  findStDailyPays(FindStDailyPayPage findStDailyPayPage)throws TsfaServiceException{
		AssertUtils.notNull(findStDailyPayPage);
		List<StDailyPayDto> returnList=null;
		try {
			returnList = stDailyPayDao.findStDailyPays(findStDailyPayPage);
		} catch (Exception e) {
			logger.error("查找每日收费统计信息信息错误！", e);
			throw new TsfaServiceException(ErrorCode.ST_DAILY_PAY_NOT_EXIST_ERROR,"每日收费统计信息不存在");
		}
		return returnList;
	}
	

	@Override
	public void updateStDailyPay(
			StDailyPayDto stDailyPayDto)
			throws TsfaServiceException {
		logger.debug("updateStDailyPay(StDailyPayDto stDailyPayDto={}) - start", stDailyPayDto); //$NON-NLS-1$

		AssertUtils.notNull(stDailyPayDto);
		AssertUtils.notNullAndEmpty(stDailyPayDto.getCode(),"Code不能为空");
		try {
			StDailyPay stDailyPay = new StDailyPay();
			//update数据录入
			stDailyPay.setCode(stDailyPayDto.getCode());
			stDailyPay.setShopNo(stDailyPayDto.getShopNo());
			stDailyPay.setShopName(stDailyPayDto.getShopName());
			stDailyPay.setMerchantNo(stDailyPayDto.getMerchantNo());
			stDailyPay.setMerchantName(stDailyPayDto.getMerchantName());
			stDailyPay.setStDate(stDailyPayDto.getStDate());
			stDailyPay.setPayType(stDailyPayDto.getPayType());
			stDailyPay.setPayTypeName(stDailyPayDto.getPayTypeName());
			stDailyPay.setPayAmount(stDailyPayDto.getPayAmount());
			stDailyPay.setPayMode(stDailyPayDto.getPayMode());
			stDailyPay.setCreateDate(stDailyPayDto.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(stDailyPayDao.updateByPrimaryKeySelective(stDailyPay));
			logger.debug("updateStDailyPay(StDailyPayDto) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("每日收费统计信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ST_DAILY_PAY_UPDATE_ERROR,"每日收费统计信息更新信息错误！",e);
		}
	}

	

	@Override
	public StDailyPayDto findStDailyPay(
			StDailyPayDto stDailyPayDto) throws TsfaServiceException {
		logger.debug("findStDailyPay(FindStDailyPay findStDailyPay={}) - start", stDailyPayDto); //$NON-NLS-1$

		AssertUtils.notNull(stDailyPayDto);
		AssertUtils.notAllNull(stDailyPayDto.getCode(),"Code不能为空");
		try {
			StDailyPay stDailyPay = stDailyPayDao.selectByPrimaryKey(stDailyPayDto.getCode());
			if(stDailyPay == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.ST_DAILY_PAY_NOT_EXIST_ERROR,"每日收费统计信息不存在");
			}
			StDailyPayDto findStDailyPayReturn = new StDailyPayDto();
			//find数据录入
			findStDailyPayReturn.setCode(stDailyPay.getCode());
			findStDailyPayReturn.setShopNo(stDailyPay.getShopNo());
			findStDailyPayReturn.setShopName(stDailyPay.getShopName());
			findStDailyPayReturn.setMerchantNo(stDailyPay.getMerchantNo());
			findStDailyPayReturn.setMerchantName(stDailyPay.getMerchantName());
			findStDailyPayReturn.setStDate(stDailyPay.getStDate());
			findStDailyPayReturn.setPayType(stDailyPay.getPayType());
			findStDailyPayReturn.setPayTypeName(stDailyPay.getPayTypeName());
			findStDailyPayReturn.setPayAmount(stDailyPay.getPayAmount());
			findStDailyPayReturn.setPayMode(stDailyPay.getPayMode());
			findStDailyPayReturn.setCreateDate(stDailyPay.getCreateDate());
			
			logger.debug("findStDailyPay(StDailyPayDto) - end - return value={}", findStDailyPayReturn); //$NON-NLS-1$
			return findStDailyPayReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找每日收费统计信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.ST_DAILY_PAY_FIND_ERROR,"查找每日收费统计信息信息错误！",e);
		}


	}

	
	@Override
	public Page<StDailyPayDto> findStDailyPayPage(
			FindStDailyPayPage findStDailyPayPage)
			throws TsfaServiceException {
		logger.debug("findStDailyPayPage(FindStDailyPayPage findStDailyPayPage={}) - start", findStDailyPayPage); //$NON-NLS-1$

		AssertUtils.notNull(findStDailyPayPage);
		List<StDailyPayDto> returnList=null;
		int count = 0;
		try {
			returnList = stDailyPayDao.findStDailyPayPage(findStDailyPayPage);
			count = stDailyPayDao.findStDailyPayPageCount(findStDailyPayPage);
		}  catch (Exception e) {
			logger.error("每日收费统计信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.ST_DAILY_PAY_FIND_PAGE_ERROR,"每日收费统计信息不存在错误.！",e);
		}
		Page<StDailyPayDto> returnPage = new Page<StDailyPayDto>(returnList, count, findStDailyPayPage);

		logger.debug("findStDailyPayPage(FindStDailyPayPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	}


	@Override
	public void batchAddStDailyPay(String batchNum) throws TsfaServiceException {
		logger.info("批次{},1.开始按支付方式统计收支....", batchNum);
		Date yesterday = DateUtils.addDays(new Date(), -1);
		String stDate = DateUtils.formatDate(yesterday, DateUtils.PATTERN_yyyyMMdd);
		int logNum = 0;
		logNum = stDailyPayDao.batchAddStDailyPay(stDate);

		logger.info("批次{},2.按支付方式统计收支操作已完成！统计日期:{},新增数据:{}",batchNum,stDate,logNum);
	}


	@Override
	public Page<StDailyPayDto> findStDailyPayPageGroupByStDay(FindStDailyPayPage findStDailyPayPage)
			throws TsfaServiceException {
		logger.debug("findStDailyPayPageGroupByStDay(FindStDailyPayPage findStDailyPayPage={}) - start", findStDailyPayPage); //$NON-NLS-1$

		AssertUtils.notNull(findStDailyPayPage);
		AssertUtils.notNull(findStDailyPayPage.getParam());
		AssertUtils.notNull(findStDailyPayPage.getParam().getMerchantNo(),"商户号不能为空");
		List<StDailyPayDto> returnList=null;
		int count = 0;
		try {
			count = stDailyPayDao.findStDailyPayPageCountGroupByStDay(findStDailyPayPage);
			if(count>0) {
				returnList = stDailyPayDao.findStDailyPayPageGroupByStDay(findStDailyPayPage);
				for (StDailyPayDto stDailyPayDto : returnList) {
					List<StDailyPayDto> payDetails=getStDailyPayList(stDailyPayDto);
					stDailyPayDto.setPayDetails(payDetails);
				}
			}
		}  catch (Exception e) {
			logger.error("每日收费统计信息不存在错误",e);
			throw new TsfaServiceException(ErrorCode.ST_DAILY_PAY_FIND_PAGE_ERROR,"每日收费统计信息不存在错误.！",e);
		}
		Page<StDailyPayDto> returnPage = new Page<StDailyPayDto>(returnList, count, findStDailyPayPage);

		logger.debug("findStDailyPayPageGroupByStDay(FindStDailyPayPage) - end - return value={}", returnPage); //$NON-NLS-1$
		return  returnPage;
	} 

	private List<StDailyPayDto> getStDailyPayList(StDailyPayDto stDailyPayDto ){
		/** 支付方式code */
		String[] payType = stDailyPayDto.getPayType().split(",");
		/** 支付方式名称 */
		String[] payTypeName = stDailyPayDto.getPayTypeName().split(",");
		/** 金额（分为单位） */
		String[] payAmountStr = stDailyPayDto.getPayAmountStr().split(",");
		/** 当日支付方式对应的金额 */
		List<StDailyPayDto> payDetails =new ArrayList<StDailyPayDto>();
		for (int i = 0; i < payType.length; i++) {
			StDailyPayDto detail = new StDailyPayDto();
			detail.setPayType(payType[i]);
			detail.setPayTypeName(payTypeName[i]);
			detail.setPayAmountStr(payAmountStr[i]);
			payDetails.add(detail);
		}
		
		stDailyPayDto.setPayType(null);
		stDailyPayDto.setPayTypeName(null);
		stDailyPayDto.setPayAmountStr(null);
		return payDetails;
	}


	@Override
	public List<StDailyPayDto> stDailyPaySumGroupByPayType(FindStDailyPayPage findStDailyPayPage)
			throws TsfaServiceException {
		logger.debug("stDailyPaySumGroupByPayType(FindStDailyPayPage findStDailyPayPage={}) - start", //$NON-NLS-1$
				findStDailyPayPage);
		AssertUtils.notNull(findStDailyPayPage);
		AssertUtils.notNull(findStDailyPayPage.getParam());
		AssertUtils.notNull(findStDailyPayPage.getParam().getMerchantNo(), "商户号不能为空");
		
		List<StDailyPayDto> returnList = null;
		try {
			returnList = stDailyPayDao.stDailyPaySumGroupByPayType(findStDailyPayPage);

		} catch (Exception e) {
			logger.error("每日收费统计信息不存在错误", e);
			throw new TsfaServiceException(ErrorCode.ST_DAILY_PAY_FIND_PAGE_ERROR, "每日收费统计信息不存在错误.！", e);
		}

		logger.debug("stDailyPaySumGroupByPayType(FindStDailyPayPage) - end - return"); //$NON-NLS-1$
		return returnList;
	}

	
}
