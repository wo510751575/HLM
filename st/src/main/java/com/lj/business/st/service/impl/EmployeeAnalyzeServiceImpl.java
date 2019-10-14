package com.lj.business.st.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lj.base.core.util.AssertUtils;
import com.lj.base.core.util.GUID;
import com.lj.base.exception.TsfaServiceException;
import com.lj.business.st.constant.ErrorCode;
import com.lj.business.st.dao.IEmployeeAnalyzeDao;
import com.lj.business.st.domain.EmployeeAnalyze;
import com.lj.business.st.dto.AddEmployeeAnalyze;
import com.lj.business.st.dto.AddEmployeeAnalyzeReturn;
import com.lj.business.st.service.IEmployeeAnalyzeService;

/**
 * 
 * 
 * 类说明：员工画像统计
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 扬恩科技有限公司
 * @author 罗书明
 *   
 * CreateDate: 2017年7月31日
 */
@Service
public class EmployeeAnalyzeServiceImpl implements IEmployeeAnalyzeService { 

	
	/** Logger for this class. */
	private static final Logger logger = LoggerFactory.getLogger(EmployeeAnalyzeServiceImpl.class);
	

	@Resource
	private IEmployeeAnalyzeDao employeeAnalyzeDao;
	
	
	@Override
	public AddEmployeeAnalyzeReturn addEmployeeAnalyze(
			AddEmployeeAnalyze addEmployeeAnalyze) throws TsfaServiceException {
		logger.debug("addEmployeeAnalyze(AddEmployeeAnalyze addEmployeeAnalyze={}) - start", addEmployeeAnalyze); 

		AssertUtils.notNull(addEmployeeAnalyze);
		try {
			EmployeeAnalyze employeeAnalyze = new EmployeeAnalyze();
			//add数据录入
			employeeAnalyze.setCode(GUID.generateByUUID());
			employeeAnalyze.setMerchantNo(addEmployeeAnalyze.getMerchantNo());
			employeeAnalyze.setAreaCode(addEmployeeAnalyze.getAreaCode());
			employeeAnalyze.setAreaName(addEmployeeAnalyze.getAreaName());
			employeeAnalyze.setShopNo(addEmployeeAnalyze.getShopNo());
			employeeAnalyze.setShopName(addEmployeeAnalyze.getShopName());
			employeeAnalyze.setStDate(addEmployeeAnalyze.getStDate());
			employeeAnalyze.setNumEmployee(addEmployeeAnalyze.getNumEmployee());
			employeeAnalyze.setRatioAgeTwenty(addEmployeeAnalyze.getRatioAgeTwenty());
			employeeAnalyze.setNumAgeTwenty(addEmployeeAnalyze.getNumAgeTwenty());
			employeeAnalyze.setRatioAgeThirty(addEmployeeAnalyze.getRatioAgeThirty());
			employeeAnalyze.setNumAgeThirty(addEmployeeAnalyze.getNumAgeThirty());
			employeeAnalyze.setRatioAgeForty(addEmployeeAnalyze.getRatioAgeForty());
			employeeAnalyze.setNumAgeForty(addEmployeeAnalyze.getNumAgeForty());
			employeeAnalyze.setDimensionSt(addEmployeeAnalyze.getDimensionSt());
			employeeAnalyze.setNumAgeFifty(addEmployeeAnalyze.getNumAgeFifty());
			employeeAnalyze.setRatioAgeFifty(addEmployeeAnalyze.getRatioAgeFifty());
			employeeAnalyze.setCreateDate(new Date());
			employeeAnalyzeDao.insertSelective(employeeAnalyze);
			AddEmployeeAnalyzeReturn addEmployeeAnalyzeReturn = new AddEmployeeAnalyzeReturn();
			
			logger.debug("addEmployeeAnalyze(AddEmployeeAnalyze) - end - return value={}", addEmployeeAnalyzeReturn); 
			return addEmployeeAnalyzeReturn;
		}catch (TsfaServiceException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}  catch (Exception e) {
			logger.error("新增员工画像信息错误！",e);
			throw new TsfaServiceException(ErrorCode.EMPLOYEE_ANALYZE_ADD_ERROR,"新增员工画像信息错误！",e);
		}
	}


	@Override
	public List<Map<String, Object>> findEmployeeAnalyzeList(
			Map<String, Object> map) throws TsfaServiceException {
		logger.debug("findEmployeeAnalyzeList(Map<String, Object> map={}) - start",map); 
		AssertUtils.notNull(map);
		List<Map<String,Object>> list=null;
		try {
			list=employeeAnalyzeDao.findEmployeeAnalyzeList(map);
		} catch (Exception e) {
			logger.error("查询员工画像信息错误！",e);
			throw new TsfaServiceException(ErrorCode.EMPLOYEE_ANALYZE_FIND_ERROR,"查询员工画像信息错误！",e);
		}
		logger.debug("findEmployeeAnalyzeList(map - end - return"); 
		return list;
	}





}
