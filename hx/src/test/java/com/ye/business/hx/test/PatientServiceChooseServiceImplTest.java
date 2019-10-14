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

import com.ye.business.hx.dto.PatientServiceChooseDto;
import com.ye.business.hx.dto.FindPatientServiceChoosePage;
import com.ye.business.hx.service.IPatientServiceChooseService;

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
public class PatientServiceChooseServiceImplTest extends SpringTestCase{

	@Resource
	IPatientServiceChooseService patientServiceChooseService;



	/**
	 * 
	 *
	 * 方法说明：添加患者服务选择（预约项目）信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addPatientServiceChoose() throws TsfaServiceException{
		PatientServiceChooseDto patientServiceChooseDto = new PatientServiceChooseDto();
		//add数据录入
		patientServiceChooseDto.setCode("Code");
		patientServiceChooseDto.setPatientReservationCode("PatientReservationCode");
		patientServiceChooseDto.setProjectCode("ProjectCode");
		patientServiceChooseDto.setProjectName("ProjectName");
		patientServiceChooseDto.setProjectPropertyCode("ProjectPropertyCode");
		patientServiceChooseDto.setProjectPropertyName("ProjectPropertyName");
		patientServiceChooseDto.setCreateDate(new Date());
		patientServiceChooseDto.setCreateId("CreateId");
		patientServiceChooseDto.setRemark("Remark");
		patientServiceChooseDto.setRemark2("Remark2");
		patientServiceChooseDto.setRemark3("Remark3");
		patientServiceChooseDto.setRemark4("Remark4");
		
		patientServiceChooseService.addPatientServiceChoose(patientServiceChooseDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改患者服务选择（预约项目）信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updatePatientServiceChoose() throws TsfaServiceException{
		PatientServiceChooseDto patientServiceChooseDto = new PatientServiceChooseDto();
		//update数据录入
		patientServiceChooseDto.setCode("Code");
		patientServiceChooseDto.setPatientReservationCode("PatientReservationCode");
		patientServiceChooseDto.setProjectCode("ProjectCode");
		patientServiceChooseDto.setProjectName("ProjectName");
		patientServiceChooseDto.setProjectPropertyCode("ProjectPropertyCode");
		patientServiceChooseDto.setProjectPropertyName("ProjectPropertyName");
		patientServiceChooseDto.setCreateDate(new Date());
		patientServiceChooseDto.setCreateId("CreateId");
		patientServiceChooseDto.setRemark("Remark");
		patientServiceChooseDto.setRemark2("Remark2");
		patientServiceChooseDto.setRemark3("Remark3");
		patientServiceChooseDto.setRemark4("Remark4");

		patientServiceChooseService.updatePatientServiceChoose(patientServiceChooseDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找患者服务选择（预约项目）信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPatientServiceChoose() throws TsfaServiceException{
		PatientServiceChooseDto findPatientServiceChoose = new PatientServiceChooseDto();
		findPatientServiceChoose.setCode("111");
		patientServiceChooseService.findPatientServiceChoose(findPatientServiceChoose);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找患者服务选择（预约项目）信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPatientServiceChoosePage() throws TsfaServiceException{
		FindPatientServiceChoosePage findPatientServiceChoosePage = new FindPatientServiceChoosePage();
		Page<PatientServiceChooseDto> page = patientServiceChooseService.findPatientServiceChoosePage(findPatientServiceChoosePage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找患者服务选择（预约项目）信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPatientServiceChooses() throws TsfaServiceException{
		FindPatientServiceChoosePage findPatientServiceChoosePage = new FindPatientServiceChoosePage();
		List<PatientServiceChooseDto> page = patientServiceChooseService.findPatientServiceChooses(findPatientServiceChoosePage);
		Assert.assertNotNull(page);
		
	}
	
}
