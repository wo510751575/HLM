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

import com.ye.business.hx.dto.PatientMedicalCheckDto;
import com.ye.business.hx.dto.FindPatientMedicalCheckPage;
import com.ye.business.hx.service.IPatientMedicalCheckService;
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
public class PatientMedicalCheckServiceImplTest extends SpringTestCase{

	@Resource
	IPatientMedicalCheckService patientMedicalCheckService;



	/**
	 * 
	 *
	 * 方法说明：添加病历检查信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void addPatientMedicalCheck() throws TsfaServiceException{
		PatientMedicalCheckDto patientMedicalCheckDto = new PatientMedicalCheckDto();
		//add数据录入
		patientMedicalCheckDto.setCode("Code");
		patientMedicalCheckDto.setMedicalCode("MedicalCode");
		patientMedicalCheckDto.setCheckOralRemark("CheckOralRemark");
		patientMedicalCheckDto.setCheckAuxiliaryRemark("CheckAuxiliaryRemark");
		patientMedicalCheckDto.setCreateId("CreateId");
		patientMedicalCheckDto.setCreateName("CreateName");
		patientMedicalCheckDto.setRemark("Remark");
		patientMedicalCheckDto.setRemark2("Remark2");
		patientMedicalCheckDto.setRemark3("Remark3");
		patientMedicalCheckDto.setRemark4("Remark4");
		patientMedicalCheckDto.setUpdateId("UpdateId");
		patientMedicalCheckDto.setUpdateName("UpdateName");
		patientMedicalCheckDto.setDentalPosition("DentalPosition");
		patientMedicalCheckDto.setDentalSurface("DentalSurface");
		
		patientMedicalCheckService.addPatientMedicalCheck(patientMedicalCheckDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改病历检查信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void updatePatientMedicalCheck() throws TsfaServiceException{
		PatientMedicalCheckDto patientMedicalCheckDto = new PatientMedicalCheckDto();
		//update数据录入
		patientMedicalCheckDto.setCode("Code");
		patientMedicalCheckDto.setMedicalCode("MedicalCode");
		patientMedicalCheckDto.setCheckOralRemark("CheckOralRemark");
		patientMedicalCheckDto.setCheckAuxiliaryRemark("CheckAuxiliaryRemark");
		patientMedicalCheckDto.setCreateId("CreateId");
		patientMedicalCheckDto.setCreateName("CreateName");
		patientMedicalCheckDto.setRemark("Remark");
		patientMedicalCheckDto.setRemark2("Remark2");
		patientMedicalCheckDto.setRemark3("Remark3");
		patientMedicalCheckDto.setRemark4("Remark4");
		patientMedicalCheckDto.setUpdateId("UpdateId");
		patientMedicalCheckDto.setUpdateName("UpdateName");
		patientMedicalCheckDto.setDentalPosition("DentalPosition");
		patientMedicalCheckDto.setDentalSurface("DentalSurface");

		patientMedicalCheckService.updatePatientMedicalCheck(patientMedicalCheckDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找病历检查信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findPatientMedicalCheck() throws TsfaServiceException{
		PatientMedicalCheckDto findPatientMedicalCheck = new PatientMedicalCheckDto();
		findPatientMedicalCheck.setCode("111");
		patientMedicalCheckService.findPatientMedicalCheck(findPatientMedicalCheck);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找病历检查信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findPatientMedicalCheckPage() throws TsfaServiceException{
		FindPatientMedicalCheckPage findPatientMedicalCheckPage = new FindPatientMedicalCheckPage();
		Page<PatientMedicalCheckDto> page = patientMedicalCheckService.findPatientMedicalCheckPage(findPatientMedicalCheckPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找病历检查信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findPatientMedicalChecks() throws TsfaServiceException{
		FindPatientMedicalCheckPage findPatientMedicalCheckPage = new FindPatientMedicalCheckPage();
		List<PatientMedicalCheckDto> page = patientMedicalCheckService.findPatientMedicalChecks(findPatientMedicalCheckPage);
		Assert.assertNotNull(page);
		
	}
	
}
