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

import com.ye.business.hx.dto.PatientMedicalDmDto;
import com.ye.business.hx.dto.FindPatientMedicalDmPage;
import com.ye.business.hx.service.IPatientMedicalDmService;
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
public class PatientMedicalDmServiceImplTest extends SpringTestCase{

	@Resource
	IPatientMedicalDmService patientMedicalDmService;



	/**
	 * 
	 *
	 * 方法说明：添加处置与医嘱信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void addPatientMedicalDm() throws TsfaServiceException{
		PatientMedicalDmDto patientMedicalDmDto = new PatientMedicalDmDto();
		//add数据录入
		patientMedicalDmDto.setCode("Code");
		patientMedicalDmDto.setMedicalCode("MedicalCode");
		patientMedicalDmDto.setCreateId("CreateId");
		patientMedicalDmDto.setCreateName("CreateName");
		patientMedicalDmDto.setRemark("Remark");
		patientMedicalDmDto.setRemark2("Remark2");
		patientMedicalDmDto.setRemark3("Remark3");
		patientMedicalDmDto.setRemark4("Remark4");
		patientMedicalDmDto.setUpdateId("UpdateId");
		patientMedicalDmDto.setUpdateName("UpdateName");
		patientMedicalDmDto.setDentalPosition("DentalPosition");
		patientMedicalDmDto.setDentalSurface("DentalSurface");
		patientMedicalDmDto.setDmDisposalRemark("DmDisposalRemark");
		
		patientMedicalDmService.addPatientMedicalDm(patientMedicalDmDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改处置与医嘱信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void updatePatientMedicalDm() throws TsfaServiceException{
		PatientMedicalDmDto patientMedicalDmDto = new PatientMedicalDmDto();
		//update数据录入
		patientMedicalDmDto.setCode("Code");
		patientMedicalDmDto.setMedicalCode("MedicalCode");
		patientMedicalDmDto.setCreateId("CreateId");
		patientMedicalDmDto.setCreateName("CreateName");
		patientMedicalDmDto.setRemark("Remark");
		patientMedicalDmDto.setRemark2("Remark2");
		patientMedicalDmDto.setRemark3("Remark3");
		patientMedicalDmDto.setRemark4("Remark4");
		patientMedicalDmDto.setUpdateId("UpdateId");
		patientMedicalDmDto.setUpdateName("UpdateName");
		patientMedicalDmDto.setDentalPosition("DentalPosition");
		patientMedicalDmDto.setDentalSurface("DentalSurface");
		patientMedicalDmDto.setDmDisposalRemark("DmDisposalRemark");

		patientMedicalDmService.updatePatientMedicalDm(patientMedicalDmDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找处置与医嘱信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findPatientMedicalDm() throws TsfaServiceException{
		PatientMedicalDmDto findPatientMedicalDm = new PatientMedicalDmDto();
		findPatientMedicalDm.setCode("111");
		patientMedicalDmService.findPatientMedicalDm(findPatientMedicalDm);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找处置与医嘱信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findPatientMedicalDmPage() throws TsfaServiceException{
		FindPatientMedicalDmPage findPatientMedicalDmPage = new FindPatientMedicalDmPage();
		Page<PatientMedicalDmDto> page = patientMedicalDmService.findPatientMedicalDmPage(findPatientMedicalDmPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找处置与医嘱信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findPatientMedicalDms() throws TsfaServiceException{
		FindPatientMedicalDmPage findPatientMedicalDmPage = new FindPatientMedicalDmPage();
		List<PatientMedicalDmDto> page = patientMedicalDmService.findPatientMedicalDms(findPatientMedicalDmPage);
		Assert.assertNotNull(page);
		
	}
	
}
