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

import com.ye.business.hx.dto.PatientServiceDto;
import com.ye.business.hx.dto.FindPatientServicePage;
import com.ye.business.hx.service.IPatientServiceService;

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
public class PatientServiceServiceImplTest extends SpringTestCase{

	@Resource
	IPatientServiceService patientServiceService;



	/**
	 * 
	 *
	 * 方法说明：添加患者服务（预约/挂号/就诊）信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addPatientService() throws TsfaServiceException{
		PatientServiceDto patientServiceDto = new PatientServiceDto();
		//add数据录入
		patientServiceDto.setCode("Code");
		patientServiceDto.setPatientNo("PatientNo");
		patientServiceDto.setPatientName("PatientName");
		patientServiceDto.setMobile("Mobile");
		patientServiceDto.setPatientType("PatientType");
		patientServiceDto.setMedicalNo("MedicalNo");
		patientServiceDto.setAdvisoryNo("AdvisoryNo");
		patientServiceDto.setAdvisoryName("AdvisoryName");
		patientServiceDto.setShopNo("ShopNo");
		patientServiceDto.setShopName("ShopName");
		patientServiceDto.setMerchantNo("MerchantNo");
		patientServiceDto.setMerchantName("MerchantName");
		patientServiceDto.setReservationDate(new Date());
		patientServiceDto.setReservationDoctorNo("ReservationDoctorNo");
		patientServiceDto.setReservationDoctorName("ReservationDoctorName");
		patientServiceDto.setReservationType("ReservationType");
		patientServiceDto.setRegisteredDoctorNo("RegisteredDoctorNo");
		patientServiceDto.setRegisteredDoctorName("RegisteredDoctorName");
		patientServiceDto.setAssistantNo("AssistantNo");
		patientServiceDto.setAssistantName("AssistantName");
		patientServiceDto.setVistitingStatus("VistitingStatus");
		patientServiceDto.setVisitingType("VisitingType");
		patientServiceDto.setVisitingDate(new Date());
		patientServiceDto.setTriageDate(new Date());
		patientServiceDto.setReviewReservationDate(new Date());
		patientServiceDto.setFinishedDate(new Date());
		patientServiceDto.setCreateDate(new Date());
		patientServiceDto.setCreateId("CreateId");
		patientServiceDto.setCreateName("CreateName");
		patientServiceDto.setRemark("Remark");
		patientServiceDto.setRemark2("Remark2");
		patientServiceDto.setRemark3("Remark3");
		patientServiceDto.setRemark4("Remark4");
		patientServiceDto.setUpdateId("UpdateId");
		patientServiceDto.setUpdateName("UpdateName");
		patientServiceDto.setUpdateDate(new Date());
		
		patientServiceService.addPatientService(patientServiceDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改患者服务（预约/挂号/就诊）信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updatePatientService() throws TsfaServiceException{
		PatientServiceDto patientServiceDto = new PatientServiceDto();
		//update数据录入
		patientServiceDto.setCode("Code");
		patientServiceDto.setPatientNo("PatientNo");
		patientServiceDto.setPatientName("PatientName");
		patientServiceDto.setMobile("Mobile");
		patientServiceDto.setPatientType("PatientType");
		patientServiceDto.setMedicalNo("MedicalNo");
		patientServiceDto.setAdvisoryNo("AdvisoryNo");
		patientServiceDto.setAdvisoryName("AdvisoryName");
		patientServiceDto.setShopNo("ShopNo");
		patientServiceDto.setShopName("ShopName");
		patientServiceDto.setMerchantNo("MerchantNo");
		patientServiceDto.setMerchantName("MerchantName");
		patientServiceDto.setReservationDate(new Date());
		patientServiceDto.setReservationDoctorNo("ReservationDoctorNo");
		patientServiceDto.setReservationDoctorName("ReservationDoctorName");
		patientServiceDto.setReservationType("ReservationType");
		patientServiceDto.setRegisteredDoctorNo("RegisteredDoctorNo");
		patientServiceDto.setRegisteredDoctorName("RegisteredDoctorName");
		patientServiceDto.setAssistantNo("AssistantNo");
		patientServiceDto.setAssistantName("AssistantName");
		patientServiceDto.setVistitingStatus("VistitingStatus");
		patientServiceDto.setVisitingType("VisitingType");
		patientServiceDto.setVisitingDate(new Date());
		patientServiceDto.setTriageDate(new Date());
		patientServiceDto.setReviewReservationDate(new Date());
		patientServiceDto.setFinishedDate(new Date());
		patientServiceDto.setCreateDate(new Date());
		patientServiceDto.setCreateId("CreateId");
		patientServiceDto.setCreateName("CreateName");
		patientServiceDto.setRemark("Remark");
		patientServiceDto.setRemark2("Remark2");
		patientServiceDto.setRemark3("Remark3");
		patientServiceDto.setRemark4("Remark4");
		patientServiceDto.setUpdateId("UpdateId");
		patientServiceDto.setUpdateName("UpdateName");
		patientServiceDto.setUpdateDate(new Date());

		patientServiceService.updatePatientService(patientServiceDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找患者服务（预约/挂号/就诊）信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPatientService() throws TsfaServiceException{
		PatientServiceDto findPatientService = new PatientServiceDto();
		findPatientService.setCode("111");
		patientServiceService.findPatientService(findPatientService);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找患者服务（预约/挂号/就诊）信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPatientServicePage() throws TsfaServiceException{
		FindPatientServicePage findPatientServicePage = new FindPatientServicePage();
		Page<PatientServiceDto> page = patientServiceService.findPatientServicePage(findPatientServicePage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找患者服务（预约/挂号/就诊）信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPatientServices() throws TsfaServiceException{
		FindPatientServicePage findPatientServicePage = new FindPatientServicePage();
		List<PatientServiceDto> page = patientServiceService.findPatientServices(findPatientServicePage);
		Assert.assertNotNull(page);
		
	}
	
}
