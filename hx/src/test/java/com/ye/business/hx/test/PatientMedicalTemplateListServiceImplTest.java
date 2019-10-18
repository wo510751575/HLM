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

import com.ye.business.hx.dto.PatientMedicalTemplateListDto;
import com.ye.business.hx.dto.PatientMedicalTemplateListVo;
import com.ye.business.hx.dto.FindPatientMedicalTemplateListPage;
import com.ye.business.hx.service.IPatientMedicalTemplateListService;
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
public class PatientMedicalTemplateListServiceImplTest extends SpringTestCase{

	@Resource
	IPatientMedicalTemplateListService patientMedicalTemplateListService;



	/**
	 * 
	 *
	 * 方法说明：添加模版目录信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void addPatientMedicalTemplateList() throws TsfaServiceException{
		PatientMedicalTemplateListDto patientMedicalTemplateListDto = new PatientMedicalTemplateListDto();
		//add数据录入
		patientMedicalTemplateListDto.setCode("Code");
		patientMedicalTemplateListDto.setName("Name");
		patientMedicalTemplateListDto.setParentCode("ParentCode");
		patientMedicalTemplateListDto.setParentName("ParentName");
		patientMedicalTemplateListDto.setCreater("Creater");
		
		patientMedicalTemplateListService.addPatientMedicalTemplateList(patientMedicalTemplateListDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改模版目录信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void updatePatientMedicalTemplateList() throws TsfaServiceException{
		PatientMedicalTemplateListDto patientMedicalTemplateListDto = new PatientMedicalTemplateListDto();
		//update数据录入
		patientMedicalTemplateListDto.setCode("Code");
		patientMedicalTemplateListDto.setName("Name");
		patientMedicalTemplateListDto.setParentCode("ParentCode");
		patientMedicalTemplateListDto.setParentName("ParentName");
		patientMedicalTemplateListDto.setCreater("Creater");

		patientMedicalTemplateListService.updatePatientMedicalTemplateList(patientMedicalTemplateListDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找模版目录信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findPatientMedicalTemplateList() throws TsfaServiceException{
		PatientMedicalTemplateListDto findPatientMedicalTemplateList = new PatientMedicalTemplateListDto();
		findPatientMedicalTemplateList.setCode("111");
		patientMedicalTemplateListService.findPatientMedicalTemplateList(findPatientMedicalTemplateList);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找模版目录信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findPatientMedicalTemplateListPage() throws TsfaServiceException{
		FindPatientMedicalTemplateListPage findPatientMedicalTemplateListPage = new FindPatientMedicalTemplateListPage();
		Page<PatientMedicalTemplateListDto> page = patientMedicalTemplateListService.findPatientMedicalTemplateListPage(findPatientMedicalTemplateListPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找模版目录信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 段志鹏 CreateDate: 2017-12-14
	 *
	 */
	@Test
	public void findPatientMedicalTemplateLists() throws TsfaServiceException{
		FindPatientMedicalTemplateListPage findPatientMedicalTemplateListPage = new FindPatientMedicalTemplateListPage();
		List<PatientMedicalTemplateListVo> page = patientMedicalTemplateListService.findPatientMedicalTemplateLists(findPatientMedicalTemplateListPage);
		Assert.assertNotNull(page);
		
	}
	
}
