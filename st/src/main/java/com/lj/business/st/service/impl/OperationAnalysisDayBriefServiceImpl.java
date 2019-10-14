package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Calendar;
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
import com.lj.base.core.util.StringUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IOperationAnalysisDayBriefDao;
import com.lj.business.st.domain.OperationAnalysisDayBrief;
import com.lj.business.st.dto.FindOperateDayReport;
import com.lj.business.st.dto.OperationAnalysisDayBrief.AddOperationAnalysisDayBrief;
import com.lj.business.st.dto.OperationAnalysisDayBrief.AddOperationAnalysisDayBriefReturn;
import com.lj.business.st.dto.OperationAnalysisDayBrief.FindOperationAnalysisDayBrief;
import com.lj.business.st.dto.OperationAnalysisDayBrief.FindOperationAnalysisDayBriefReturn;
import com.lj.business.st.service.IOperationAnalysisDayBriefService;

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
public class OperationAnalysisDayBriefServiceImpl implements IOperationAnalysisDayBriefService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(OperationAnalysisDayBriefServiceImpl.class);
	

	@Resource
	private IOperationAnalysisDayBriefDao operationAnalysisDayBriefDao;
	
	
	public AddOperationAnalysisDayBriefReturn addOperationAnalysisDayBrief(
			AddOperationAnalysisDayBrief addOperationAnalysisDayBrief) throws TsfaServiceException {
		logger.debug("addOperationAnalysisDayBrief(AddOperationAnalysisDayBrief addOperationAnalysisDayBrief={}) - start", addOperationAnalysisDayBrief); 

		AssertUtils.notNull(addOperationAnalysisDayBrief);
		try {
			OperationAnalysisDayBrief operationAnalysisDayBrief = new OperationAnalysisDayBrief();
			//add数据录入
			operationAnalysisDayBrief.setCode(GUID.getPreUUID());
			operationAnalysisDayBrief.setMerchantNo(addOperationAnalysisDayBrief.getMerchantNo());
			operationAnalysisDayBrief.setAreaCode(addOperationAnalysisDayBrief.getAreaCode());
			operationAnalysisDayBrief.setAreaName(addOperationAnalysisDayBrief.getAreaName());
			operationAnalysisDayBrief.setShopNo(addOperationAnalysisDayBrief.getShopNo());
			operationAnalysisDayBrief.setShopName(addOperationAnalysisDayBrief.getShopName());
			operationAnalysisDayBrief.setMemberNoGm(addOperationAnalysisDayBrief.getMemberNoGm());
			operationAnalysisDayBrief.setMemberNameGm(addOperationAnalysisDayBrief.getMemberNameGm());
			operationAnalysisDayBrief.setBriefSale(addOperationAnalysisDayBrief.getBriefSale());
			operationAnalysisDayBrief.setBriefOrder(addOperationAnalysisDayBrief.getBriefOrder());
			operationAnalysisDayBrief.setBriefClientAction(addOperationAnalysisDayBrief.getBriefClientAction());
			operationAnalysisDayBrief.setBriefClientAnalyze(addOperationAnalysisDayBrief.getBriefClientAnalyze());
			operationAnalysisDayBrief.setBriefCf(addOperationAnalysisDayBrief.getBriefCf());
			operationAnalysisDayBrief.setBriefCaArea(addOperationAnalysisDayBrief.getBriefCaArea());
			operationAnalysisDayBrief.setDimensionSt(addOperationAnalysisDayBrief.getDimensionSt());
			operationAnalysisDayBrief.setCreateDate(addOperationAnalysisDayBrief.getCreateDate());
			operationAnalysisDayBrief.setDaySt(addOperationAnalysisDayBrief.getDaySt());
			operationAnalysisDayBriefDao.insertSelective(operationAnalysisDayBrief);
			AddOperationAnalysisDayBriefReturn addOperationAnalysisDayBriefReturn = new AddOperationAnalysisDayBriefReturn();
			
			logger.debug("addOperationAnalysisDayBrief(AddOperationAnalysisDayBrief) - end - return value={}", addOperationAnalysisDayBriefReturn); 
			return addOperationAnalysisDayBriefReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error(" 新增运营分析报表摘要表信息错误",e);
			throw new TsfaServiceException(ErrorCode.OPERATION_ANALYSIS_DAY_BRIEF_ADD_ERROR," 新增运营分析报表摘要表信息错误",e);
		}
	}
	
	
 
	public FindOperationAnalysisDayBriefReturn findOperationAnalysisDayBrief(
			FindOperationAnalysisDayBrief findOperationAnalysisDayBrief) throws TsfaServiceException {
		logger.debug("findOperationAnalysisDayBrief(FindOperationAnalysisDayBrief findOperationAnalysisDayBrief={}) - start", findOperationAnalysisDayBrief); //$NON-NLS-1$
 
		AssertUtils.notNull(findOperationAnalysisDayBrief); 
		AssertUtils.notAllNull(findOperationAnalysisDayBrief.getCode(),"CODE不能为空");
		try {
			OperationAnalysisDayBrief operationAnalysisDayBrief = operationAnalysisDayBriefDao.selectByPrimaryKey(findOperationAnalysisDayBrief.getCode());
			if(operationAnalysisDayBrief == null){
				throw new TsfaServiceException(ErrorCode.OPERATION_ANALYSIS_DAY_BRIEF_NOT_EXIST_ERROR,"报表项目信息不存在");
			}
			FindOperationAnalysisDayBriefReturn findOperationAnalysisDayBriefReturn = new FindOperationAnalysisDayBriefReturn();
			//find数据录入
			findOperationAnalysisDayBriefReturn.setCode(operationAnalysisDayBrief.getCode());
			findOperationAnalysisDayBriefReturn.setMerchantNo(operationAnalysisDayBrief.getMerchantNo());
			findOperationAnalysisDayBriefReturn.setAreaCode(operationAnalysisDayBrief.getAreaCode());
			findOperationAnalysisDayBriefReturn.setAreaName(operationAnalysisDayBrief.getAreaName());
			findOperationAnalysisDayBriefReturn.setShopNo(operationAnalysisDayBrief.getShopNo());
			findOperationAnalysisDayBriefReturn.setShopName(operationAnalysisDayBrief.getShopName());
			findOperationAnalysisDayBriefReturn.setMemberNoGm(operationAnalysisDayBrief.getMemberNoGm());
			findOperationAnalysisDayBriefReturn.setMemberNameGm(operationAnalysisDayBrief.getMemberNameGm());
			findOperationAnalysisDayBriefReturn.setBriefSale(operationAnalysisDayBrief.getBriefSale());
			findOperationAnalysisDayBriefReturn.setBriefOrder(operationAnalysisDayBrief.getBriefOrder());
			findOperationAnalysisDayBriefReturn.setBriefClientAction(operationAnalysisDayBrief.getBriefClientAction());
			findOperationAnalysisDayBriefReturn.setBriefClientAnalyze(operationAnalysisDayBrief.getBriefClientAnalyze());
			findOperationAnalysisDayBriefReturn.setBriefCf(operationAnalysisDayBrief.getBriefCf());
			findOperationAnalysisDayBriefReturn.setBriefCaArea(operationAnalysisDayBrief.getBriefCaArea());
			findOperationAnalysisDayBriefReturn.setDimensionSt(operationAnalysisDayBrief.getDimensionSt());
			findOperationAnalysisDayBriefReturn.setCreateDate(operationAnalysisDayBrief.getCreateDate());
			
			logger.debug("findOperationAnalysisDayBrief(FindOperationAnalysisDayBrief) - end - return value={}", findOperationAnalysisDayBriefReturn); //$NON-NLS-1$
			return findOperationAnalysisDayBriefReturn;
		}catch (TsfaServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error("查找报表项目信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.OPERATION_ANALYSIS_DAY_BRIEF_FIND_ERROR,"查找报表项目信息信息错误！",e);
		}


	}
	@Override
	public  List<OperationAnalysisDayBrief> selectYesterdayList(FindOperateDayReport findOperateDayReport) {
		AssertUtils.notNull(findOperateDayReport);
		try {
			String yesterday = getDate(1);
			findOperateDayReport.setBeginDate(yesterday + " 00:00:00");
			findOperateDayReport.setEndDate(yesterday + " 23:59:59");
			return operationAnalysisDayBriefDao.selectList(findOperateDayReport);
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找运营分析报表摘要表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.OPERATION_ANALYSIS_DAY_BRIEF_FIND_ERROR,"查找运营分析报表摘要表信息信息错误！",e);
		}
	}

	private static String getDate(Integer minusDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -minusDay);
		return DateUtils.formatDate(cal.getTime(), DateUtils.PATTERN_yyyy_MM_dd);
	}



}
