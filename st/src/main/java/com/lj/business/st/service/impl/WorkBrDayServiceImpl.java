package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IWorkBrDayDao;
import com.lj.business.st.domain.WorkBrDay;
import com.lj.business.st.dto.WorkBrDay.AddWorkBrDay;
import com.lj.business.st.dto.WorkBrDay.FindWorkBrDay;
import com.lj.business.st.dto.WorkBrDay.FindWorkBrDayReturn;
import com.lj.business.st.service.IWorkBrDayService;

/**
 * 类说明：实现类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 邹磊
 * 
 * 
 * CreateDate: 2017-06-14
 */
@Service
public class WorkBrDayServiceImpl implements IWorkBrDayService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(WorkBrDayServiceImpl.class);
	

	@Resource
	private IWorkBrDayDao workBrDayDao;
	
	
	@Override
	public void addWorkBrDay(
			AddWorkBrDay addWorkBrDay) throws TsfaServiceException {
		logger.debug("addWorkBrDay(AddWorkBrDay addWorkBrDay={}) - start", addWorkBrDay); 

		AssertUtils.notNull(addWorkBrDay);
		try {
			WorkBrDay workBrDay = new WorkBrDay();
			//add数据录入
			workBrDay.setCode(addWorkBrDay.getCode());
			workBrDay.setMerchantNo(addWorkBrDay.getMerchantNo());
			workBrDay.setAreaCode(addWorkBrDay.getAreaCode());
			workBrDay.setAreaName(addWorkBrDay.getAreaName());
			workBrDay.setShopNo(addWorkBrDay.getShopNo());
			workBrDay.setShopName(addWorkBrDay.getShopName());
			workBrDay.setMemberNoGm(addWorkBrDay.getMemberNoGm());
			workBrDay.setMemberNameGm(addWorkBrDay.getMemberNameGm());
			workBrDay.setCodeList(addWorkBrDay.getCodeList());
			workBrDay.setNameList(addWorkBrDay.getNameList());
			workBrDay.setValueList(addWorkBrDay.getValueList());
			workBrDay.setDaySt(addWorkBrDay.getDaySt());
			workBrDay.setDimensionSt(addWorkBrDay.getDimensionSt());
			workBrDay.setCreateDate(addWorkBrDay.getCreateDate());
			workBrDayDao.insert(workBrDay);
			logger.debug("addWorkBrDay(AddWorkBrDay) - end - return"); 
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增日工作简报表信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_BR_DAY_ADD_ERROR,"新增日工作简报表信息错误！",e);
		}
	}
	

	

	@Override
	public FindWorkBrDayReturn findWorkBrDay(
			FindWorkBrDay findWorkBrDay) throws TsfaServiceException {
		logger.debug("findWorkBrDay(FindWorkBrDay findWorkBrDay={}) - start", findWorkBrDay); //$NON-NLS-1$

		AssertUtils.notNull(findWorkBrDay);
		AssertUtils.notAllNull(findWorkBrDay.getCode(),"Code不能为空");
		try {
			WorkBrDay workBrDay = workBrDayDao.selectByPrimaryKey(findWorkBrDay.getCode());
			if(workBrDay == null){
				return null;
				//throw new TsfaServiceException(ErrorCode.WORK_BR_DAY_NOT_EXIST_ERROR,"日工作简报表信息不存在");
			}
			FindWorkBrDayReturn findWorkBrDayReturn = new FindWorkBrDayReturn();
			//find数据录入
			findWorkBrDayReturn.setCode(workBrDay.getCode());
			findWorkBrDayReturn.setMerchantNo(workBrDay.getMerchantNo());
			findWorkBrDayReturn.setAreaCode(workBrDay.getAreaCode());
			findWorkBrDayReturn.setAreaName(workBrDay.getAreaName());
			findWorkBrDayReturn.setShopNo(workBrDay.getShopNo());
			findWorkBrDayReturn.setShopName(workBrDay.getShopName());
			findWorkBrDayReturn.setMemberNoGm(workBrDay.getMemberNoGm());
			findWorkBrDayReturn.setMemberNameGm(workBrDay.getMemberNameGm());
			findWorkBrDayReturn.setCodeList(workBrDay.getCodeList());
			findWorkBrDayReturn.setNameList(workBrDay.getNameList());
			findWorkBrDayReturn.setValueList(workBrDay.getValueList());
			findWorkBrDayReturn.setDaySt(workBrDay.getDaySt());
			findWorkBrDayReturn.setDimensionSt(workBrDay.getDimensionSt());
			findWorkBrDayReturn.setCreateDate(workBrDay.getCreateDate());
			
			logger.debug("findWorkBrDay(FindWorkBrDay) - end - return value={}", findWorkBrDayReturn); //$NON-NLS-1$
			return findWorkBrDayReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			logger.error("查找日工作简报表信息信息错误！",e);
			throw new TsfaServiceException(ErrorCode.WORK_BR_DAY_FIND_ERROR,"查找日工作简报表信息信息错误！",e);
		}


	}



}
