package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.DateUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.ISalesGmDayDetailDao;
import com.lj.business.st.domain.SalesGmDayDetail;
import com.lj.business.st.dto.FindSalesGmDayFirstCompleteRateDto;
import com.lj.business.st.dto.FindSalesGmDayFirstCompleteRateReturn;
import com.lj.business.st.dto.FindSalesGmDayFirstIndex;
import com.lj.business.st.dto.salesGmDayDetail.AddSalesGmDayDetail;
import com.lj.business.st.dto.salesGmDayDetail.DelSalesGmDayDetail;
import com.lj.business.st.dto.salesGmDayDetail.FindSalesGmDayDetail;
import com.lj.business.st.dto.salesGmDayDetail.FindSalesGmDayDetailFirstList;
import com.lj.business.st.dto.salesGmDayDetail.FindSalesGmDayDetailReturn;
import com.lj.business.st.dto.salesGmDayDetail.UpdateSalesGmDayDetail;
import com.lj.business.st.service.ISalesGmDayDetailService;

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
public class SalesGmDayDetailServiceImpl implements ISalesGmDayDetailService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(SalesGmDayDetailServiceImpl.class);
	

	@Resource
	private ISalesGmDayDetailDao salesGmDayDetailDao;
	
	
	@Override
	public void addSalesGmDayDetail(
			AddSalesGmDayDetail addSalesGmDayDetail) throws TsfaServiceException {
		logger.debug("addSalesGmDayDetail(AddSalesGmDayDetail addSalesGmDayDetail={}) - start", addSalesGmDayDetail); 

		AssertUtils.notNull(addSalesGmDayDetail);
		try {
			SalesGmDayDetail salesGmDayDetail = new SalesGmDayDetail();
			//add数据录入
			salesGmDayDetail.setCode(addSalesGmDayDetail.getCode());
			salesGmDayDetail.setMerchantNo(addSalesGmDayDetail.getMerchantNo());
			salesGmDayDetail.setAreaCode(addSalesGmDayDetail.getAreaCode());
			salesGmDayDetail.setAreaName(addSalesGmDayDetail.getAreaName());
			salesGmDayDetail.setShopNo(addSalesGmDayDetail.getShopNo());
			salesGmDayDetail.setShopName(addSalesGmDayDetail.getShopName());
			salesGmDayDetail.setMemberNoGm(addSalesGmDayDetail.getMemberNoGm());
			salesGmDayDetail.setMemberNameGm(addSalesGmDayDetail.getMemberNameGm());
			salesGmDayDetail.setHeadAddress(addSalesGmDayDetail.getHeadAddress());
			salesGmDayDetail.setNumSale(addSalesGmDayDetail.getNumSale());
			salesGmDayDetail.setNumSaleTarget(addSalesGmDayDetail.getNumSaleTarget());
			salesGmDayDetail.setDaySt(addSalesGmDayDetail.getDaySt());
			salesGmDayDetail.setUpdateDate(addSalesGmDayDetail.getUpdateDate());
			salesGmDayDetail.setCreateDate(addSalesGmDayDetail.getCreateDate());
			salesGmDayDetailDao.insert(salesGmDayDetail);
			logger.debug("addSalesGmDayDetail(AddSalesGmDayDetail) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增导购销售额日明细表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SALES_GM_DAY_DETAIL_ADD_ERROR,"新增导购销售额日明细表信息错误！",e);
		}
	}
	
	
	@Override
	public void delSalesGmDayDetail(
			DelSalesGmDayDetail delSalesGmDayDetail) throws TsfaServiceException {
		logger.debug("delSalesGmDayDetail(DelSalesGmDayDetail delSalesGmDayDetail={}) - start", delSalesGmDayDetail); 

		AssertUtils.notNull(delSalesGmDayDetail);
		AssertUtils.notNull(delSalesGmDayDetail.getCode(),"Code不能为空！");
		try {
			salesGmDayDetailDao.deleteByPrimaryKey(delSalesGmDayDetail.getCode());
			logger.debug("delSalesGmDayDetail(DelSalesGmDayDetail) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除导购销售额日明细表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SALES_GM_DAY_DETAIL_DEL_ERROR,"删除导购销售额日明细表信息错误！",e);

		}
	}

	@Override
	public void updateSalesGmDayDetail(
			UpdateSalesGmDayDetail updateSalesGmDayDetail)
			throws TsfaServiceException {
		logger.debug("updateSalesGmDayDetail(UpdateSalesGmDayDetail updateSalesGmDayDetail={}) - start", updateSalesGmDayDetail); //$NON-NLS-1$

		AssertUtils.notNull(updateSalesGmDayDetail);
		AssertUtils.notNullAndEmpty(updateSalesGmDayDetail.getCode(),"Code不能为空");
		try {
			SalesGmDayDetail salesGmDayDetail = new SalesGmDayDetail();
			//update数据录入
			salesGmDayDetail.setCode(updateSalesGmDayDetail.getCode());
			salesGmDayDetail.setMerchantNo(updateSalesGmDayDetail.getMerchantNo());
			salesGmDayDetail.setAreaCode(updateSalesGmDayDetail.getAreaCode());
			salesGmDayDetail.setAreaName(updateSalesGmDayDetail.getAreaName());
			salesGmDayDetail.setShopNo(updateSalesGmDayDetail.getShopNo());
			salesGmDayDetail.setShopName(updateSalesGmDayDetail.getShopName());
			salesGmDayDetail.setMemberNoGm(updateSalesGmDayDetail.getMemberNoGm());
			salesGmDayDetail.setMemberNameGm(updateSalesGmDayDetail.getMemberNameGm());
			salesGmDayDetail.setHeadAddress(updateSalesGmDayDetail.getHeadAddress());
			salesGmDayDetail.setNumSale(updateSalesGmDayDetail.getNumSale());
			salesGmDayDetail.setNumSaleTarget(updateSalesGmDayDetail.getNumSaleTarget());
			salesGmDayDetail.setDaySt(updateSalesGmDayDetail.getDaySt());
			salesGmDayDetail.setUpdateDate(updateSalesGmDayDetail.getUpdateDate());
			salesGmDayDetail.setCreateDate(updateSalesGmDayDetail.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(salesGmDayDetailDao.updateByPrimaryKeySelective(salesGmDayDetail));
			logger.debug("updateSalesGmDayDetail(UpdateSalesGmDayDetail) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("导购销售额日明细表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SALES_GM_DAY_DETAIL_UPDATE_ERROR,"导购销售额日明细表信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindSalesGmDayDetailReturn findSalesGmDayDetail(
			FindSalesGmDayDetail findSalesGmDayDetail) throws TsfaServiceException {
		logger.debug("findSalesGmDayDetail(FindSalesGmDayDetail findSalesGmDayDetail={}) - start", findSalesGmDayDetail); //$NON-NLS-1$

		AssertUtils.notNull(findSalesGmDayDetail);
		AssertUtils.notAllNull(findSalesGmDayDetail.getCode(),"Code不能为空");
		try {
			SalesGmDayDetail salesGmDayDetail = salesGmDayDetailDao.selectByPrimaryKey(findSalesGmDayDetail.getCode());
			if(salesGmDayDetail == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.SALES_GM_DAY_DETAIL_NOT_EXIST_ERROR,"其他任务完成情况表信息不存在");
			}
			FindSalesGmDayDetailReturn findSalesGmDayDetailReturn = new FindSalesGmDayDetailReturn();
			//find数据录入
			findSalesGmDayDetailReturn.setCode(salesGmDayDetail.getCode());
			findSalesGmDayDetailReturn.setMerchantNo(salesGmDayDetail.getMerchantNo());
			findSalesGmDayDetailReturn.setAreaCode(salesGmDayDetail.getAreaCode());
			findSalesGmDayDetailReturn.setAreaName(salesGmDayDetail.getAreaName());
			findSalesGmDayDetailReturn.setShopNo(salesGmDayDetail.getShopNo());
			findSalesGmDayDetailReturn.setShopName(salesGmDayDetail.getShopName());
			findSalesGmDayDetailReturn.setMemberNoGm(salesGmDayDetail.getMemberNoGm());
			findSalesGmDayDetailReturn.setMemberNameGm(salesGmDayDetail.getMemberNameGm());
			findSalesGmDayDetailReturn.setHeadAddress(salesGmDayDetail.getHeadAddress());
			findSalesGmDayDetailReturn.setNumSale(salesGmDayDetail.getNumSale());
			findSalesGmDayDetailReturn.setNumSaleTarget(salesGmDayDetail.getNumSaleTarget());
			findSalesGmDayDetailReturn.setDaySt(salesGmDayDetail.getDaySt());
			findSalesGmDayDetailReturn.setUpdateDate(salesGmDayDetail.getUpdateDate());
			findSalesGmDayDetailReturn.setCreateDate(salesGmDayDetail.getCreateDate());
			
			logger.debug("findSalesGmDayDetail(FindSalesGmDayDetail) - end - return value={}", findSalesGmDayDetailReturn); //$NON-NLS-1$
			return findSalesGmDayDetailReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购销售额日明细表表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SALES_GM_DAY_DETAIL_FIND_ERROR,"查找导购销售额日明细表表信息错误！",e);
		}


	}

	/* (non-Javadoc)
	 * @see com.lj.business.st.service.ISalesGmDayDetailService#findSalesGmDayDetailFirst(com.lj.business.st.dto.FindSalesGmDayFirstIndex)
	 */
	@Override
	public FindSalesGmDayDetailReturn findSalesGmDayDetailFirst(FindSalesGmDayFirstIndex findSalesGmDayFirstIndex)throws TsfaServiceException {
		logger.debug("findSalesGmDayDetailFirst(FindSalesGmDayFirstIndex findSalesGmDayFirstIndex={}) - start", findSalesGmDayFirstIndex); //$NON-NLS-1$

		AssertUtils.notNull(findSalesGmDayFirstIndex);
		AssertUtils.notNullAndEmpty(findSalesGmDayFirstIndex.getAreaCode(), "区域code不能为空");
		AssertUtils.notNullAndEmpty(findSalesGmDayFirstIndex.getMerchantNo(), "商户编号不能为空");
		FindSalesGmDayDetailReturn findSalesGmDayDetailReturn = null;
		try{
			findSalesGmDayDetailReturn = salesGmDayDetailDao.findSalesGmDayDetailFirst(findSalesGmDayFirstIndex);

			logger.debug("findSalesGmDayDetailFirst() - end - return value={}", findSalesGmDayDetailReturn); //$NON-NLS-1$
			return findSalesGmDayDetailReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购销售额日明细表表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SALES_GM_DAY_DETAIL_FIND_ERROR,"查找导购销售额日明细表表信息错误！",e);
		}
		
	}
	
	@Override
	public FindSalesGmDayFirstCompleteRateReturn findSalesGmDayFirstCompleteRate(FindSalesGmDayFirstCompleteRateDto findSalesGmDayFirstCompleteRateDto)throws TsfaServiceException {
		logger.debug("findSalesGmDayFirstCompleteRate(FindSalesGmDayFirstCompleteRateDto findSalesGmDayFirstCompleteRateDto={}) - start", findSalesGmDayFirstCompleteRateDto); //$NON-NLS-1$

		AssertUtils.notNull(findSalesGmDayFirstCompleteRateDto);
		AssertUtils.notNullAndEmpty(findSalesGmDayFirstCompleteRateDto.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notNullAndEmpty(findSalesGmDayFirstCompleteRateDto.getAreaCode(), "区域code不能为空");
		AssertUtils.notNullAndEmpty(findSalesGmDayFirstCompleteRateDto.getShopNo(), "分店编号不能为空");
		AssertUtils.notNullAndEmpty(findSalesGmDayFirstCompleteRateDto.getMemberNoGm(), "导购编号不能为空");
		AssertUtils.notNullAndEmpty(findSalesGmDayFirstCompleteRateDto.getDaySt(), "时间不能为空");
		try{
			FindSalesGmDayFirstCompleteRateReturn findSalesGmDayFirstCompleteRateReturn = new FindSalesGmDayFirstCompleteRateReturn();
			AddSalesGmDayDetail addSalesGmDayDetail = new AddSalesGmDayDetail();
			addSalesGmDayDetail.setMerchantNo(findSalesGmDayFirstCompleteRateDto.getMerchantNo());
			addSalesGmDayDetail.setAreaCode(findSalesGmDayFirstCompleteRateDto.getAreaCode());
			addSalesGmDayDetail.setShopNo(findSalesGmDayFirstCompleteRateDto.getShopNo());
			addSalesGmDayDetail.setMemberNoGm(findSalesGmDayFirstCompleteRateDto.getMemberNoGm());
			addSalesGmDayDetail.setDaySt(org.apache.commons.lang.time.DateUtils.truncate(DateUtils.parseDate(findSalesGmDayFirstCompleteRateDto.getDaySt(), DateUtils.PATTERN_yyyy_MM_dd_HH_mm_ss)
					, Calendar.DAY_OF_MONTH));
			FindSalesGmDayDetailReturn findSalesGmDayDetailReturn = salesGmDayDetailDao.findSalesGmDayDetailFirstByDetail(addSalesGmDayDetail);
			if(findSalesGmDayDetailReturn != null){
				findSalesGmDayFirstCompleteRateReturn.setNumSale(findSalesGmDayDetailReturn.getNumSale());
				findSalesGmDayFirstCompleteRateReturn.setNumSaleTarget(findSalesGmDayDetailReturn.getNumSaleTarget());
				if(findSalesGmDayDetailReturn.getNumSaleTarget() == null || findSalesGmDayDetailReturn.getNumSaleTarget() == 0){
					findSalesGmDayFirstCompleteRateReturn.setCompleteRate(0.0);
				}else{
					BigDecimal child = new BigDecimal(findSalesGmDayDetailReturn.getNumSale());
					BigDecimal mother = new BigDecimal(findSalesGmDayDetailReturn.getNumSaleTarget());
					findSalesGmDayFirstCompleteRateReturn.setCompleteRate(child.divide(mother, 4, BigDecimal.ROUND_HALF_UP).doubleValue());
				}
			}

			logger.debug("findSalesGmDayFirstCompleteRate() - end - return value={}", findSalesGmDayFirstCompleteRateReturn); //$NON-NLS-1$
			return findSalesGmDayFirstCompleteRateReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购销售额日明细表表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SALES_GM_DAY_DETAIL_FIND_ERROR,"查找导购销售额日明细表表信息错误！",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lj.business.st.service.ISalesGmDayDetailService#addOrUpdateSalesGmDayDetail(com.lj.business.st.dto.salesGmDayDetail.AddSalesGmDayDetail)
	 */
	@Override
	public void addOrUpdateSalesGmDayDetail(AddSalesGmDayDetail addSalesGmDayDetail)throws TsfaServiceException {
		logger.debug("addOrUpdateSalesGmDayDetail(AddSalesGmDayDetail addSalesGmDayDetail={}) - start", addSalesGmDayDetail); //$NON-NLS-1$

		AssertUtils.notNull(addSalesGmDayDetail);
		AssertUtils.notNullAndEmpty(addSalesGmDayDetail.getAreaCode(), "区域code不能为空");
		AssertUtils.notNullAndEmpty(addSalesGmDayDetail.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notNullAndEmpty(addSalesGmDayDetail.getMemberNoGm(), "导购编号不能为空");
		AssertUtils.notNullAndEmpty(addSalesGmDayDetail.getMemberNameGm(), "导购姓名不能为空");
		AssertUtils.notNullAndEmpty(addSalesGmDayDetail.getShopNo(), "分店编号不能为空");
		AssertUtils.notNullAndEmpty(addSalesGmDayDetail.getShopName(), "分店名称不能为空");
		AssertUtils.notNullAndEmpty(addSalesGmDayDetail.getDaySt(), "日期不能为空");
		AssertUtils.notNullAndEmpty(addSalesGmDayDetail.getNumSale(), "销售金额不能为空");
		AssertUtils.notNullAndEmpty(addSalesGmDayDetail.getNumSaleTarget(), "销售目标不能为空");
		try{
			// 查询当天是否存在
			FindSalesGmDayDetailReturn findSalesGmDayDetailReturn = salesGmDayDetailDao.findSalesGmDayDetailFirstByDetail(addSalesGmDayDetail);
			if(findSalesGmDayDetailReturn != null){
				//修改
				UpdateSalesGmDayDetail updateSalesGmDayDetail = new UpdateSalesGmDayDetail();
				updateSalesGmDayDetail.setCode(findSalesGmDayDetailReturn.getCode());
				updateSalesGmDayDetail.setNumSale(findSalesGmDayDetailReturn.getNumSale() + addSalesGmDayDetail.getNumSale());
				updateSalesGmDayDetail.setNumSaleTarget(addSalesGmDayDetail.getNumSaleTarget());
				updateSalesGmDayDetail.setHeadAddress(addSalesGmDayDetail.getHeadAddress());
				updateSalesGmDayDetail.setUpdateDate(new Date());
				updateSalesGmDayDetail(updateSalesGmDayDetail);
			}else{
				//新增
				addSalesGmDayDetail.setCode(GUID.generateCode());
				addSalesGmDayDetail.setCreateDate(new Date());
				addSalesGmDayDetail.setUpdateDate(new Date());
				addSalesGmDayDetail(addSalesGmDayDetail);
			}
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("新增导购销售额日明细表表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SALES_GM_DAY_DETAIL_ADD_ERROR,"新增导购销售额日明细表表信息错误！",e);
		}
		
		
		logger.debug("addOrUpdateSalesGmDayDetail() - end"); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.lj.business.st.service.ISalesGmDayDetailService#findSalesGmDayDetailFirstList(com.lj.business.st.dto.salesGmDayDetail.FindSalesGmDayDetailFirstList)
	 */
	@Override
	public List<FindSalesGmDayDetailReturn> findSalesGmDayDetailFirstList(FindSalesGmDayDetailFirstList findSalesGmDayDetailFirstList) throws TsfaServiceException {
		try{
			List<FindSalesGmDayDetailReturn> list = new ArrayList<FindSalesGmDayDetailReturn>();
			list = salesGmDayDetailDao.findSalesGmDayDetailFirstList(findSalesGmDayDetailFirstList);
			return list;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购销售额日明细表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SALES_GM_DAY_DETAIL_FIND_ERROR,"查找导购销售额日明细表信息错误！",e);
		}
	}
	
	
	
}
