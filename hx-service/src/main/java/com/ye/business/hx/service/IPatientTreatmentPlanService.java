package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.PatientTreatmentPlanDto;
import com.ye.business.hx.dto.FindPatientTreatmentPlanPage;


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
public interface IPatientTreatmentPlanService {
	
	/**
	 * 
	 *
	 * 方法说明：添加正畸计划-治疗计划信息
	 *
	 * @param patientTreatmentPlanDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addPatientTreatmentPlan(PatientTreatmentPlanDto patientTreatmentPlanDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找正畸计划-治疗计划信息
	 *
	 * @param findPatientTreatmentPlan
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public PatientTreatmentPlanDto findPatientTreatmentPlan(PatientTreatmentPlanDto patientTreatmentPlanDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询正畸计划-治疗计划信息
	 *
	 * @param findPatientTreatmentPlanPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<PatientTreatmentPlanDto>  findPatientTreatmentPlans(FindPatientTreatmentPlanPage findPatientTreatmentPlanPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改正畸计划-治疗计划信息
	 *
	 * @param updatePatientTreatmentPlan
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updatePatientTreatmentPlan(PatientTreatmentPlanDto patientTreatmentPlanDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询正畸计划-治疗计划信息
	 *
	 * @param findPatientTreatmentPlanPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<PatientTreatmentPlanDto> findPatientTreatmentPlanPage(FindPatientTreatmentPlanPage findPatientTreatmentPlanPage) throws TsfaServiceException;

	/**   
	 * @Title: del   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param patientTreatmentPlanDto      
	 * @return: void      
	 * @throws   
	 */
	public void del(PatientTreatmentPlanDto patientTreatmentPlanDto);
	

}
