package com.ye.business.hx.test;
import java.util.List;

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
import com.ye.business.hx.dto.FindPatientMedicalTemplatePage;
import com.ye.business.hx.dto.PatientMedicalTemplateDto;
import com.ye.business.hx.service.IPatientMedicalTemplateService;
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
public class PatientMedicalTemplateServiceImplTest extends SpringTestCase{

	@Resource
	IPatientMedicalTemplateService patientMedicalTemplateService;



	/**
	 * 
	 *
	 * 方法说明：添加模版信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void addPatientMedicalTemplate() throws TsfaServiceException{
		PatientMedicalTemplateDto patientMedicalTemplateDto = new PatientMedicalTemplateDto();
		//add数据录入
		patientMedicalTemplateDto.setCode("Code");
		patientMedicalTemplateDto.setName("Name");
		patientMedicalTemplateDto.setMainRemark("MainRemark");
		patientMedicalTemplateDto.setMainCurrentRemark("MainCurrentRemark");
		patientMedicalTemplateDto.setMainPastRemark("MainPastRemark");
		patientMedicalTemplateDto.setCheckOralRemark("CheckOralRemark");
		patientMedicalTemplateDto.setCheckAuxiliaryRemark("CheckAuxiliaryRemark");
		patientMedicalTemplateDto.setPlanDiagnosisRemark("PlanDiagnosisRemark");
		patientMedicalTemplateDto.setPlanTreatmentRemark("PlanTreatmentRemark");
		patientMedicalTemplateDto.setDmDisposalRemark("DmDisposalRemark");
		patientMedicalTemplateDto.setDmMedicalRemark("DmMedicalRemark");
		patientMedicalTemplateDto.setOtherLabelRemark("OtherLabelRemark");
		patientMedicalTemplateDto.setOtherRemark("OtherRemark");
		patientMedicalTemplateDto.setCreateId("CreateId");
		patientMedicalTemplateDto.setCreateName("CreateName");
		patientMedicalTemplateDto.setRemark("Remark");
		patientMedicalTemplateDto.setRemark2("Remark2");
		patientMedicalTemplateDto.setRemark3("Remark3");
		patientMedicalTemplateDto.setRemark4("Remark4");
		patientMedicalTemplateDto.setUpdateId("UpdateId");
		patientMedicalTemplateDto.setUpdateName("UpdateName");
		
		patientMedicalTemplateService.addPatientMedicalTemplate(patientMedicalTemplateDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改模版信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void updatePatientMedicalTemplate() throws TsfaServiceException{
		PatientMedicalTemplateDto patientMedicalTemplateDto = new PatientMedicalTemplateDto();
		//update数据录入
		patientMedicalTemplateDto.setCode("Code");
		patientMedicalTemplateDto.setName("Name");
		patientMedicalTemplateDto.setMainRemark("MainRemark");
		patientMedicalTemplateDto.setMainCurrentRemark("MainCurrentRemark");
		patientMedicalTemplateDto.setMainPastRemark("MainPastRemark");
		patientMedicalTemplateDto.setCheckOralRemark("CheckOralRemark");
		patientMedicalTemplateDto.setCheckAuxiliaryRemark("CheckAuxiliaryRemark");
		patientMedicalTemplateDto.setPlanDiagnosisRemark("PlanDiagnosisRemark");
		patientMedicalTemplateDto.setPlanTreatmentRemark("PlanTreatmentRemark");
		patientMedicalTemplateDto.setDmDisposalRemark("DmDisposalRemark");
		patientMedicalTemplateDto.setDmMedicalRemark("DmMedicalRemark");
		patientMedicalTemplateDto.setOtherLabelRemark("OtherLabelRemark");
		patientMedicalTemplateDto.setOtherRemark("OtherRemark");
		patientMedicalTemplateDto.setCreateId("CreateId");
		patientMedicalTemplateDto.setCreateName("CreateName");
		patientMedicalTemplateDto.setRemark("Remark");
		patientMedicalTemplateDto.setRemark2("Remark2");
		patientMedicalTemplateDto.setRemark3("Remark3");
		patientMedicalTemplateDto.setRemark4("Remark4");
		patientMedicalTemplateDto.setUpdateId("UpdateId");
		patientMedicalTemplateDto.setUpdateName("UpdateName");

		patientMedicalTemplateService.updatePatientMedicalTemplate(patientMedicalTemplateDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找模版信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findPatientMedicalTemplate() throws TsfaServiceException{
		PatientMedicalTemplateDto findPatientMedicalTemplate = new PatientMedicalTemplateDto();
		findPatientMedicalTemplate.setCode("111");
		patientMedicalTemplateService.findPatientMedicalTemplate(findPatientMedicalTemplate);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找模版信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findPatientMedicalTemplatePage() throws TsfaServiceException{
		FindPatientMedicalTemplatePage findPatientMedicalTemplatePage = new FindPatientMedicalTemplatePage();
		Page<PatientMedicalTemplateDto> page = patientMedicalTemplateService.findPatientMedicalTemplatePage(findPatientMedicalTemplatePage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找模版信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findPatientMedicalTemplates() throws TsfaServiceException{
		FindPatientMedicalTemplatePage findPatientMedicalTemplatePage = new FindPatientMedicalTemplatePage();
		List<PatientMedicalTemplateDto> page = patientMedicalTemplateService.findPatientMedicalTemplates(findPatientMedicalTemplatePage);
		Assert.assertNotNull(page);
		
	}
	
}
