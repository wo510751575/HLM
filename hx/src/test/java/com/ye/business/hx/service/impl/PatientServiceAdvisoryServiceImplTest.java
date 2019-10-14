package com.ye.business.hx.service.impl;

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

import com.ye.business.hx.dto.PatientServiceAdvisoryDto;
import com.ye.business.hx.dto.FindPatientServiceAdvisoryPage;
import com.ye.business.hx.service.IPatientServiceAdvisoryService;

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
public class PatientServiceAdvisoryServiceImplTest extends SpringTestCase{

	@Resource
	IPatientServiceAdvisoryService patientServiceAdvisoryService;



	/**
	 * 
	 *
	 * 方法说明：添加患者服务咨询信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addPatientServiceAdvisory() throws TsfaServiceException{
		PatientServiceAdvisoryDto patientServiceAdvisoryDto = new PatientServiceAdvisoryDto();
		//add数据录入
		patientServiceAdvisoryDto.setCode("Code");
		patientServiceAdvisoryDto.setPatientReservationCode("PatientReservationCode");
		patientServiceAdvisoryDto.setPatientNo("PatientNo");
		patientServiceAdvisoryDto.setPatientName("PatientName");
		patientServiceAdvisoryDto.setProjectCodes("ProjectCodes");
		patientServiceAdvisoryDto.setProjectNames("ProjectNames");
		patientServiceAdvisoryDto.setAdvisoryContent("AdvisoryContent");
		patientServiceAdvisoryDto.setExplainRemark("ExplainRemark");
		patientServiceAdvisoryDto.setWant("Want");
		patientServiceAdvisoryDto.setCreateDate(new Date());
		patientServiceAdvisoryDto.setCreateId("CreateId");
		patientServiceAdvisoryDto.setRemark("Remark");
		patientServiceAdvisoryDto.setRemark2("Remark2");
		patientServiceAdvisoryDto.setRemark3("Remark3");
		patientServiceAdvisoryDto.setRemark4("Remark4");
		
		patientServiceAdvisoryService.addPatientServiceAdvisory(patientServiceAdvisoryDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改患者服务咨询信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updatePatientServiceAdvisory() throws TsfaServiceException{
		PatientServiceAdvisoryDto patientServiceAdvisoryDto = new PatientServiceAdvisoryDto();
		//update数据录入
		patientServiceAdvisoryDto.setCode("Code");
		patientServiceAdvisoryDto.setPatientReservationCode("PatientReservationCode");
		patientServiceAdvisoryDto.setPatientNo("PatientNo");
		patientServiceAdvisoryDto.setPatientName("PatientName");
		patientServiceAdvisoryDto.setProjectCodes("ProjectCodes");
		patientServiceAdvisoryDto.setProjectNames("ProjectNames");
		patientServiceAdvisoryDto.setAdvisoryContent("AdvisoryContent");
		patientServiceAdvisoryDto.setExplainRemark("ExplainRemark");
		patientServiceAdvisoryDto.setWant("Want");
		patientServiceAdvisoryDto.setCreateDate(new Date());
		patientServiceAdvisoryDto.setCreateId("CreateId");
		patientServiceAdvisoryDto.setRemark("Remark");
		patientServiceAdvisoryDto.setRemark2("Remark2");
		patientServiceAdvisoryDto.setRemark3("Remark3");
		patientServiceAdvisoryDto.setRemark4("Remark4");

		patientServiceAdvisoryService.updatePatientServiceAdvisory(patientServiceAdvisoryDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找患者服务咨询信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPatientServiceAdvisory() throws TsfaServiceException{
		PatientServiceAdvisoryDto findPatientServiceAdvisory = new PatientServiceAdvisoryDto();
		findPatientServiceAdvisory.setCode("111");
		patientServiceAdvisoryService.findPatientServiceAdvisory(findPatientServiceAdvisory);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找患者服务咨询信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPatientServiceAdvisoryPage() throws TsfaServiceException{
		FindPatientServiceAdvisoryPage findPatientServiceAdvisoryPage = new FindPatientServiceAdvisoryPage();
		Page<PatientServiceAdvisoryDto> page = patientServiceAdvisoryService.findPatientServiceAdvisoryPage(findPatientServiceAdvisoryPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找患者服务咨询信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPatientServiceAdvisorys() throws TsfaServiceException{
		FindPatientServiceAdvisoryPage findPatientServiceAdvisoryPage = new FindPatientServiceAdvisoryPage();
		List<PatientServiceAdvisoryDto> page = patientServiceAdvisoryService.findPatientServiceAdvisorys(findPatientServiceAdvisoryPage);
		Assert.assertNotNull(page);
		
	}
	
}
