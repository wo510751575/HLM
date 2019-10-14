package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
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
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.member.dto.FindGuidMember;
import com.lj.business.member.dto.FindGuidMemberReturn;
import com.lj.business.member.service.IGuidMemberService;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.ISalesGmDayFirstDao;
import com.lj.business.st.domain.SalesGmDayFirst;
import com.lj.business.st.dto.FindSalesGmDayFirstIndex;
import com.lj.business.st.dto.FindSalesGmDayFirstIndexReturn;
import com.lj.business.st.dto.salesGmDayDetail.FindSalesGmDayDetailReturn;
import com.lj.business.st.dto.salesGmDayFirst.AddSalesGmDayFirst;
import com.lj.business.st.dto.salesGmDayFirst.DelSalesGmDayFirst;
import com.lj.business.st.dto.salesGmDayFirst.FindSalesGmDayFirst;
import com.lj.business.st.dto.salesGmDayFirst.FindSalesGmDayFirstReturn;
import com.lj.business.st.dto.salesGmDayFirst.UpdateSalesGmDayFirst;
import com.lj.business.st.service.ISalesGmDayDetailService;
import com.lj.business.st.service.ISalesGmDayFirstService;

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
public class SalesGmDayFirstServiceImpl implements ISalesGmDayFirstService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(SalesGmDayFirstServiceImpl.class);
	

	@Resource
	private ISalesGmDayFirstDao salesGmDayFirstDao;
	
    @Resource
    private ISalesGmDayDetailService salesGmDayDetailService;
    
    @Resource
    private IGuidMemberService guidMemberService;
	
	
	@Override
	public void addSalesGmDayFirst(
			AddSalesGmDayFirst addSalesGmDayFirst) throws TsfaServiceException {
		logger.debug("addSalesGmDayFirst(AddSalesGmDayFirst addSalesGmDayFirst={}) - start", addSalesGmDayFirst); 

		AssertUtils.notNull(addSalesGmDayFirst);
		try {
			SalesGmDayFirst salesGmDayFirst = new SalesGmDayFirst();
			//add数据录入
			salesGmDayFirst.setCode(addSalesGmDayFirst.getCode());
			salesGmDayFirst.setMerchantNo(addSalesGmDayFirst.getMerchantNo());
			salesGmDayFirst.setAreaCode(addSalesGmDayFirst.getAreaCode());
			salesGmDayFirst.setAreaName(addSalesGmDayFirst.getAreaName());
			salesGmDayFirst.setShopNo(addSalesGmDayFirst.getShopNo());
			salesGmDayFirst.setShopName(addSalesGmDayFirst.getShopName());
			salesGmDayFirst.setMemberNoGm(addSalesGmDayFirst.getMemberNoGm());
			salesGmDayFirst.setMemberNameGm(addSalesGmDayFirst.getMemberNameGm());
			salesGmDayFirst.setHeadAddress(addSalesGmDayFirst.getHeadAddress());
			salesGmDayFirst.setNumSale(addSalesGmDayFirst.getNumSale());
			salesGmDayFirst.setNumSaleTarget(addSalesGmDayFirst.getNumSaleTarget());
			salesGmDayFirst.setDaySt(addSalesGmDayFirst.getDaySt());
			salesGmDayFirst.setUpdateDate(addSalesGmDayFirst.getUpdateDate());
			salesGmDayFirst.setCreateDate(addSalesGmDayFirst.getCreateDate());
			salesGmDayFirstDao.insert(salesGmDayFirst);
			logger.debug("addSalesGmDayFirst(AddSalesGmDayFirst) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增导购销售日冠军表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SALES_GM_DAY_FIRST_ADD_ERROR,"新增导购销售日冠军表信息错误！",e);
		}
	}
	
	
	@Override
	public void delSalesGmDayFirst(
			DelSalesGmDayFirst delSalesGmDayFirst) throws TsfaServiceException {
		logger.debug("delSalesGmDayFirst(DelSalesGmDayFirst delSalesGmDayFirst={}) - start", delSalesGmDayFirst); 

		AssertUtils.notNull(delSalesGmDayFirst);
		AssertUtils.notNull(delSalesGmDayFirst.getCode(),"Code不能为空！");
		try {
			salesGmDayFirstDao.deleteByPrimaryKey(delSalesGmDayFirst.getCode());
			logger.debug("delSalesGmDayFirst(DelSalesGmDayFirst) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("删除导购销售日冠军表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SALES_GM_DAY_FIRST_DEL_ERROR,"删除导购销售日冠军表信息错误！",e);

		}
	}

	@Override
	public void updateSalesGmDayFirst(
			UpdateSalesGmDayFirst updateSalesGmDayFirst)
			throws TsfaServiceException {
		logger.debug("updateSalesGmDayFirst(UpdateSalesGmDayFirst updateSalesGmDayFirst={}) - start", updateSalesGmDayFirst); //$NON-NLS-1$

		AssertUtils.notNull(updateSalesGmDayFirst);
		AssertUtils.notNullAndEmpty(updateSalesGmDayFirst.getCode(),"Code不能为空");
		try {
			SalesGmDayFirst salesGmDayFirst = new SalesGmDayFirst();
			//update数据录入
			salesGmDayFirst.setCode(updateSalesGmDayFirst.getCode());
			salesGmDayFirst.setMerchantNo(updateSalesGmDayFirst.getMerchantNo());
			salesGmDayFirst.setAreaCode(updateSalesGmDayFirst.getAreaCode());
			salesGmDayFirst.setAreaName(updateSalesGmDayFirst.getAreaName());
			salesGmDayFirst.setShopNo(updateSalesGmDayFirst.getShopNo());
			salesGmDayFirst.setShopName(updateSalesGmDayFirst.getShopName());
			salesGmDayFirst.setMemberNoGm(updateSalesGmDayFirst.getMemberNoGm());
			salesGmDayFirst.setMemberNameGm(updateSalesGmDayFirst.getMemberNameGm());
			salesGmDayFirst.setHeadAddress(updateSalesGmDayFirst.getHeadAddress());
			salesGmDayFirst.setNumSale(updateSalesGmDayFirst.getNumSale());
			salesGmDayFirst.setNumSaleTarget(updateSalesGmDayFirst.getNumSaleTarget());
			salesGmDayFirst.setDaySt(updateSalesGmDayFirst.getDaySt());
			salesGmDayFirst.setUpdateDate(updateSalesGmDayFirst.getUpdateDate());
			salesGmDayFirst.setCreateDate(updateSalesGmDayFirst.getCreateDate());
			AssertUtils.notUpdateMoreThanOne(salesGmDayFirstDao.updateByPrimaryKeySelective(salesGmDayFirst));
			logger.debug("updateSalesGmDayFirst(UpdateSalesGmDayFirst) - end - return"); //$NON-NLS-1$
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("导购销售日冠军表信息更新信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SALES_GM_DAY_FIRST_UPDATE_ERROR,"导购销售日冠军表信息更新信息错误！",e);
		}
	}

	

	@Override
	public FindSalesGmDayFirstReturn findSalesGmDayFirst(
			FindSalesGmDayFirst findSalesGmDayFirst) throws TsfaServiceException {
		logger.debug("findSalesGmDayFirst(FindSalesGmDayFirst findSalesGmDayFirst={}) - start", findSalesGmDayFirst); //$NON-NLS-1$

		AssertUtils.notNull(findSalesGmDayFirst);
		AssertUtils.notNullAndEmpty(findSalesGmDayFirst.getCode(),"Code不能为空");
		try {
			SalesGmDayFirst salesGmDayFirst = salesGmDayFirstDao.selectByPrimaryKey(findSalesGmDayFirst.getCode());
			if(salesGmDayFirst == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.SALES_GM_DAY_FIRST_NOT_EXIST_ERROR,"其他任务完成情况表信息不存在");
			}
			FindSalesGmDayFirstReturn findSalesGmDayFirstReturn = new FindSalesGmDayFirstReturn();
			//find数据录入
			findSalesGmDayFirstReturn.setCode(salesGmDayFirst.getCode());
			findSalesGmDayFirstReturn.setMerchantNo(salesGmDayFirst.getMerchantNo());
			findSalesGmDayFirstReturn.setAreaCode(salesGmDayFirst.getAreaCode());
			findSalesGmDayFirstReturn.setAreaName(salesGmDayFirst.getAreaName());
			findSalesGmDayFirstReturn.setShopNo(salesGmDayFirst.getShopNo());
			findSalesGmDayFirstReturn.setShopName(salesGmDayFirst.getShopName());
			findSalesGmDayFirstReturn.setMemberNoGm(salesGmDayFirst.getMemberNoGm());
			findSalesGmDayFirstReturn.setMemberNameGm(salesGmDayFirst.getMemberNameGm());
			findSalesGmDayFirstReturn.setHeadAddress(salesGmDayFirst.getHeadAddress());
			findSalesGmDayFirstReturn.setNumSale(salesGmDayFirst.getNumSale());
			findSalesGmDayFirstReturn.setNumSaleTarget(salesGmDayFirst.getNumSaleTarget());
			findSalesGmDayFirstReturn.setDaySt(salesGmDayFirst.getDaySt());
			findSalesGmDayFirstReturn.setUpdateDate(salesGmDayFirst.getUpdateDate());
			findSalesGmDayFirstReturn.setCreateDate(salesGmDayFirst.getCreateDate());
			
			logger.debug("findSalesGmDayFirst(FindSalesGmDayFirst) - end - return value={}", findSalesGmDayFirstReturn); //$NON-NLS-1$
			return findSalesGmDayFirstReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购销售日冠军表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SALES_GM_DAY_FIRST_FIND_ERROR,"查找导购销售日冠军表信息错误！",e);
		}


	}


	/* (non-Javadoc)
	 * @see com.lj.business.st.service.ISalesGmDayFirstService#findSalesGmDayFirst(com.lj.business.st.dto.FindSalesGmDayFirstIndex)
	 */
	@Override
	public List<FindSalesGmDayFirstIndexReturn> findSalesGmDayFirst(FindSalesGmDayFirstIndex findSalesGmDayFirstIndex) throws TsfaServiceException {
		logger.debug("findSalesGmDayFirst(FindSalesGmDayFirstIndex findSalesGmDayFirstIndex={}) - start", findSalesGmDayFirstIndex); //$NON-NLS-1$

		AssertUtils.notNull(findSalesGmDayFirstIndex);
		AssertUtils.notNullAndEmpty(findSalesGmDayFirstIndex.getAreaCode(), "区域code不能为空");
		AssertUtils.notNullAndEmpty(findSalesGmDayFirstIndex.getMerchantNo(), "商户编号不能为空");
		List<FindSalesGmDayFirstIndexReturn> list = new ArrayList<FindSalesGmDayFirstIndexReturn>();
		try{
			Date now = new Date();
			Date beginDate = DateUtils.getMonthFirstDay();
			Date endDate = DateUtils.getPreday(now);
			if(DateUtils.isSameDay(beginDate, now)){
				endDate = beginDate;
			}
			//查询本月月初到昨天的数据
			if(!DateUtils.isSameDay(beginDate, now)){
				findSalesGmDayFirstIndex.setBeginDate(org.apache.commons.lang.time.DateUtils.truncate(beginDate, Calendar.DAY_OF_MONTH));
				findSalesGmDayFirstIndex.setEndDate(org.apache.commons.lang.time.DateUtils.truncate(endDate, Calendar.DAY_OF_MONTH));
				list = salesGmDayFirstDao.findSalesGmDayFirst(findSalesGmDayFirstIndex);
			}
			
			//查询今天的数据
			if(list == null){
				list =  new ArrayList<FindSalesGmDayFirstIndexReturn>();
			}
			
			findSalesGmDayFirstIndex.setNow(org.apache.commons.lang.time.DateUtils.truncate(now, Calendar.DAY_OF_MONTH));
			FindSalesGmDayDetailReturn findSalesGmDayDetailReturn = salesGmDayDetailService.findSalesGmDayDetailFirst(findSalesGmDayFirstIndex);
			if(findSalesGmDayDetailReturn != null){
				FindSalesGmDayFirstIndexReturn findSalesGmDayFirstIndexReturn = new FindSalesGmDayFirstIndexReturn();
				findSalesGmDayFirstIndexReturn.setDaySt(findSalesGmDayDetailReturn.getDaySt());
				findSalesGmDayFirstIndexReturn.setHeadAddress(findSalesGmDayDetailReturn.getHeadAddress());
				findSalesGmDayFirstIndexReturn.setMemberNoGm(findSalesGmDayDetailReturn.getMemberNoGm());
				findSalesGmDayFirstIndexReturn.setMemberNameGm(findSalesGmDayDetailReturn.getMemberNameGm());
				findSalesGmDayFirstIndexReturn.setShopNo(findSalesGmDayDetailReturn.getShopNo());
				findSalesGmDayFirstIndexReturn.setShopName(findSalesGmDayDetailReturn.getShopName());
				list.add(findSalesGmDayFirstIndexReturn);
			}
			
			// 获取导购最新信息
			for(FindSalesGmDayFirstIndexReturn detail : list) {
				FindGuidMember findGuidMember = new FindGuidMember();
				findGuidMember.setMemberNo(detail.getMemberNoGm());
				FindGuidMemberReturn guid = guidMemberService.findGuidMember(findGuidMember);
				detail.setHeadAddress(guid.getHeadAddress());
				detail.setMemberNoGm(guid.getMemberNo());
				detail.setMemberNameGm(guid.getMemberName());
//				detail.setShopNo(guid.getShopNo());
//				detail.setShopName(guid.getShopName());
			}
			logger.debug("findSalesGmDayFirst() - end - return value={}", list); //$NON-NLS-1$
			return list;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购销售日冠军表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SALES_GM_DAY_FIRST_FIND_ERROR,"查找导购销售日冠军表信息错误！",e);
		}
		
	}


	/* (non-Javadoc)
	 * @see com.lj.business.st.service.ISalesGmDayFirstService#findSalesGmDayFirstByMAD(com.lj.business.st.dto.salesGmDayFirst.FindSalesGmDayFirst)
	 */
	@Override
	public FindSalesGmDayFirstReturn findSalesGmDayFirstByMAD(FindSalesGmDayFirst findSalesGmDayFirst) throws TsfaServiceException {
		AssertUtils.notNull(findSalesGmDayFirst);
		AssertUtils.notNullAndEmpty(findSalesGmDayFirst.getAreaCode(), "区域code不能为空");
		AssertUtils.notNullAndEmpty(findSalesGmDayFirst.getMerchantNo(), "商户编号不能为空");
		AssertUtils.notNullAndEmpty(findSalesGmDayFirst.getDaySt(), "统计时间不能为空");
		FindSalesGmDayFirstReturn findSalesGmDayFirstReturn = null;
		try{
			findSalesGmDayFirstReturn = salesGmDayFirstDao.findSalesGmDayFirstByMAD(findSalesGmDayFirst);
			return findSalesGmDayFirstReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找导购销售日冠军表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.SALES_GM_DAY_FIRST_FIND_ERROR,"查找导购销售日冠军表信息错误！",e);
		}
		
	}
	
}
