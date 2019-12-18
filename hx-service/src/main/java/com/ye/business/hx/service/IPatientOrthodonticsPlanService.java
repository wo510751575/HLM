package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.PatientOrthodonticsPlanDto;
import com.ye.business.hx.dto.FindPatientOrthodonticsPlanPage;


import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;

import java.util.List;
/**
 * 类说明：接口类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 * CreateDate: 2017.12.14
 */
public interface IPatientOrthodonticsPlanService {
	
	/**
	 * 
	 *
	 * 方法说明：添加正畸病历-正畸计划信息
	 *
	 * @param patientOrthodonticsPlanDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addPatientOrthodonticsPlan(PatientOrthodonticsPlanDto patientOrthodonticsPlanDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找正畸病历-正畸计划信息
	 *
	 * @param findPatientOrthodonticsPlan
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public PatientOrthodonticsPlanDto findPatientOrthodonticsPlan(PatientOrthodonticsPlanDto patientOrthodonticsPlanDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询正畸病历-正畸计划信息
	 *
	 * @param findPatientOrthodonticsPlanPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<PatientOrthodonticsPlanDto>  findPatientOrthodonticsPlans(FindPatientOrthodonticsPlanPage findPatientOrthodonticsPlanPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改正畸病历-正畸计划信息
	 *
	 * @param updatePatientOrthodonticsPlan
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updatePatientOrthodonticsPlan(PatientOrthodonticsPlanDto patientOrthodonticsPlanDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询正畸病历-正畸计划信息
	 *
	 * @param findPatientOrthodonticsPlanPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<PatientOrthodonticsPlanDto> findPatientOrthodonticsPlanPage(FindPatientOrthodonticsPlanPage findPatientOrthodonticsPlanPage) throws TsfaServiceException;
	

}
