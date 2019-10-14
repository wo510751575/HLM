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

import java.util.Date;
import java.util.List;

import com.ye.business.hx.dto.PatientMedicalDto;
import com.ye.business.hx.dto.FindPatientMedicalPage;
import com.ye.business.hx.service.IPatientMedicalService;

/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author lhy
 * 
 * 
 * CreateDate: 2019.02.19
 */
public class PatientMedicalServiceImplTest extends SpringTestCase{

	@Resource
	IPatientMedicalService patientMedicalService;



	/**
	 * 
	 *
	 * 方法说明：添加患者病历信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addPatientMedical() throws TsfaServiceException{
		PatientMedicalDto patientMedicalDto = new PatientMedicalDto();
		//add数据录入
		patientMedicalDto.setCode("Code");
		patientMedicalDto.setPatientReservationCode("PatientReservationCode");
		patientMedicalDto.setPatientNo("PatientNo");
		patientMedicalDto.setPatientName("PatientName");
		patientMedicalDto.setDoctorNo("DoctorNo");
		patientMedicalDto.setDoctorName("DoctorName");
		patientMedicalDto.setAssistantNo("AssistantNo");
		patientMedicalDto.setAssistantName("AssistantName");
		patientMedicalDto.setVisitingDate(new Date());
		patientMedicalDto.setVisitingType("VisitingType");
		patientMedicalDto.setMainRemark("MainRemark");
		patientMedicalDto.setMainCurrentRemark("MainCurrentRemark");
		patientMedicalDto.setMainPastRemark("MainPastRemark");
		patientMedicalDto.setCheckOralRemark("CheckOralRemark");
		patientMedicalDto.setCheckAuxiliaryRemark("CheckAuxiliaryRemark");
		patientMedicalDto.setPlanDiagnosisRemark("PlanDiagnosisRemark");
		patientMedicalDto.setPlanTreatmentRemark("PlanTreatmentRemark");
		patientMedicalDto.setDmDisposalRemark("DmDisposalRemark");
		patientMedicalDto.setDmMedicalRemark("DmMedicalRemark");
		patientMedicalDto.setOtherLabelRemark("OtherLabelRemark");
		patientMedicalDto.setOtherRemark("OtherRemark");
		patientMedicalDto.setCreateDate(new Date());
		patientMedicalDto.setCreateId("CreateId");
		patientMedicalDto.setCreateName("CreateName");
		patientMedicalDto.setRemark("Remark");
		patientMedicalDto.setRemark2("Remark2");
		patientMedicalDto.setRemark3("Remark3");
		patientMedicalDto.setRemark4("Remark4");
		patientMedicalDto.setUpdateId("UpdateId");
		patientMedicalDto.setUpdateName("UpdateName");
		patientMedicalDto.setUpdateDate(new Date());
		
		patientMedicalService.addPatientMedical(patientMedicalDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改患者病历信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updatePatientMedical() throws TsfaServiceException{
		PatientMedicalDto patientMedicalDto = new PatientMedicalDto();
		//update数据录入
		patientMedicalDto.setCode("Code");
		patientMedicalDto.setPatientReservationCode("PatientReservationCode");
		patientMedicalDto.setPatientNo("PatientNo");
		patientMedicalDto.setPatientName("PatientName");
		patientMedicalDto.setDoctorNo("DoctorNo");
		patientMedicalDto.setDoctorName("DoctorName");
		patientMedicalDto.setAssistantNo("AssistantNo");
		patientMedicalDto.setAssistantName("AssistantName");
		patientMedicalDto.setVisitingDate(new Date());
		patientMedicalDto.setVisitingType("VisitingType");
		patientMedicalDto.setMainRemark("MainRemark");
		patientMedicalDto.setMainCurrentRemark("MainCurrentRemark");
		patientMedicalDto.setMainPastRemark("MainPastRemark");
		patientMedicalDto.setCheckOralRemark("CheckOralRemark");
		patientMedicalDto.setCheckAuxiliaryRemark("CheckAuxiliaryRemark");
		patientMedicalDto.setPlanDiagnosisRemark("PlanDiagnosisRemark");
		patientMedicalDto.setPlanTreatmentRemark("PlanTreatmentRemark");
		patientMedicalDto.setDmDisposalRemark("DmDisposalRemark");
		patientMedicalDto.setDmMedicalRemark("DmMedicalRemark");
		patientMedicalDto.setOtherLabelRemark("OtherLabelRemark");
		patientMedicalDto.setOtherRemark("OtherRemark");
		patientMedicalDto.setCreateDate(new Date());
		patientMedicalDto.setCreateId("CreateId");
		patientMedicalDto.setCreateName("CreateName");
		patientMedicalDto.setRemark("Remark");
		patientMedicalDto.setRemark2("Remark2");
		patientMedicalDto.setRemark3("Remark3");
		patientMedicalDto.setRemark4("Remark4");
		patientMedicalDto.setUpdateId("UpdateId");
		patientMedicalDto.setUpdateName("UpdateName");
		patientMedicalDto.setUpdateDate(new Date());

		patientMedicalService.updatePatientMedical(patientMedicalDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找患者病历信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPatientMedical() throws TsfaServiceException{
		PatientMedicalDto findPatientMedical = new PatientMedicalDto();
		findPatientMedical.setCode("111");
		patientMedicalService.findPatientMedical(findPatientMedical);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找患者病历信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPatientMedicalPage() throws TsfaServiceException{
		FindPatientMedicalPage findPatientMedicalPage = new FindPatientMedicalPage();
		Page<PatientMedicalDto> page = patientMedicalService.findPatientMedicalPage(findPatientMedicalPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找患者病历信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPatientMedicals() throws TsfaServiceException{
		FindPatientMedicalPage findPatientMedicalPage = new FindPatientMedicalPage();
		List<PatientMedicalDto> page = patientMedicalService.findPatientMedicals(findPatientMedicalPage);
		Assert.assertNotNull(page);
		
	}
	
}
