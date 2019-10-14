package com.ye.business.hx.service;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import com.ye.business.hx.dto.PatientMedicalPlanDto;
import com.ye.business.hx.dto.FindPatientMedicalPlanPage;

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
public interface IPatientMedicalPlanService {
	
	/**
	 * 
	 *
	 * 方法说明：添加诊断与治疗计划信息
	 *
	 * @param patientMedicalPlanDto
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void addPatientMedicalPlan(PatientMedicalPlanDto patientMedicalPlanDto) throws TsfaServiceException;
	
	/**
	 * 
	 *
	 * 方法说明：查找诊断与治疗计划信息
	 *
	 * @param findPatientMedicalPlan
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public PatientMedicalPlanDto findPatientMedicalPlan(PatientMedicalPlanDto patientMedicalPlanDto) throws TsfaServiceException;
	
	
	/**
	 * 
	 *
	 * 方法说明：不分页查询诊断与治疗计划信息
	 *
	 * @param findPatientMedicalPlanPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public List<PatientMedicalPlanDto>  findPatientMedicalPlans(FindPatientMedicalPlanPage findPatientMedicalPlanPage)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：修改诊断与治疗计划信息
	 *
	 * @param updatePatientMedicalPlan
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public void updatePatientMedicalPlan(PatientMedicalPlanDto patientMedicalPlanDto)throws TsfaServiceException;

	/**
	 * 
	 *
	 * 方法说明：分页查询诊断与治疗计划信息
	 *
	 * @param findPatientMedicalPlanPage
	 * @return
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	public Page<PatientMedicalPlanDto> findPatientMedicalPlanPage(FindPatientMedicalPlanPage findPatientMedicalPlanPage) throws TsfaServiceException;
	

}
