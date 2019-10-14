package com.ye.business.hx.test;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;

import java.util.List;

import com.ye.business.hx.dto.PatientMedicalPlanDto;
import com.ye.business.hx.dto.FindPatientMedicalPlanPage;
import com.ye.business.hx.service.IPatientMedicalPlanService;
/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 段志鹏
 * 
 * 
 * CreateDate: 2017.12.11
 */
public class PatientMedicalPlanServiceImplTest extends SpringTestCase{

	@Resource
	IPatientMedicalPlanService patientMedicalPlanService;



	/**
	 * 
	 *
	 * 方法说明：添加诊断与治疗计划信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void addPatientMedicalPlan() throws TsfaServiceException{
		PatientMedicalPlanDto patientMedicalPlanDto = new PatientMedicalPlanDto();
		//add数据录入
		patientMedicalPlanDto.setCode("Code");
		patientMedicalPlanDto.setMedicalCode("MedicalCode");
		patientMedicalPlanDto.setCreateId("CreateId");
		patientMedicalPlanDto.setCreateName("CreateName");
		patientMedicalPlanDto.setRemark("Remark");
		patientMedicalPlanDto.setRemark2("Remark2");
		patientMedicalPlanDto.setRemark3("Remark3");
		patientMedicalPlanDto.setRemark4("Remark4");
		patientMedicalPlanDto.setUpdateId("UpdateId");
		patientMedicalPlanDto.setUpdateName("UpdateName");
		patientMedicalPlanDto.setDentalPosition("DentalPosition");
		patientMedicalPlanDto.setDentalSurface("DentalSurface");
		patientMedicalPlanDto.setPlanDiagnosisRemark("PlanDiagnosisRemark");
		patientMedicalPlanDto.setPlanTreatmentRemark("PlanTreatmentRemark");
		
		patientMedicalPlanService.addPatientMedicalPlan(patientMedicalPlanDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改诊断与治疗计划信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void updatePatientMedicalPlan() throws TsfaServiceException{
		PatientMedicalPlanDto patientMedicalPlanDto = new PatientMedicalPlanDto();
		//update数据录入
		patientMedicalPlanDto.setCode("Code");
		patientMedicalPlanDto.setMedicalCode("MedicalCode");
		patientMedicalPlanDto.setCreateId("CreateId");
		patientMedicalPlanDto.setCreateName("CreateName");
		patientMedicalPlanDto.setRemark("Remark");
		patientMedicalPlanDto.setRemark2("Remark2");
		patientMedicalPlanDto.setRemark3("Remark3");
		patientMedicalPlanDto.setRemark4("Remark4");
		patientMedicalPlanDto.setUpdateId("UpdateId");
		patientMedicalPlanDto.setUpdateName("UpdateName");
		patientMedicalPlanDto.setDentalPosition("DentalPosition");
		patientMedicalPlanDto.setDentalSurface("DentalSurface");
		patientMedicalPlanDto.setPlanDiagnosisRemark("PlanDiagnosisRemark");
		patientMedicalPlanDto.setPlanTreatmentRemark("PlanTreatmentRemark");

		patientMedicalPlanService.updatePatientMedicalPlan(patientMedicalPlanDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找诊断与治疗计划信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findPatientMedicalPlan() throws TsfaServiceException{
		PatientMedicalPlanDto findPatientMedicalPlan = new PatientMedicalPlanDto();
		findPatientMedicalPlan.setCode("111");
		patientMedicalPlanService.findPatientMedicalPlan(findPatientMedicalPlan);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找诊断与治疗计划信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findPatientMedicalPlanPage() throws TsfaServiceException{
		FindPatientMedicalPlanPage findPatientMedicalPlanPage = new FindPatientMedicalPlanPage();
		Page<PatientMedicalPlanDto> page = patientMedicalPlanService.findPatientMedicalPlanPage(findPatientMedicalPlanPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找诊断与治疗计划信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findPatientMedicalPlans() throws TsfaServiceException{
		FindPatientMedicalPlanPage findPatientMedicalPlanPage = new FindPatientMedicalPlanPage();
		List<PatientMedicalPlanDto> page = patientMedicalPlanService.findPatientMedicalPlans(findPatientMedicalPlanPage);
		Assert.assertNotNull(page);
		
	}
	
}
