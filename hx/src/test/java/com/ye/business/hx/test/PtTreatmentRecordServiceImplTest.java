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

import com.ye.business.hx.dto.PtTreatmentRecordDto;
import com.ye.business.hx.dto.FindPtTreatmentRecordPage;
import com.ye.business.hx.service.IPtTreatmentRecordService;

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
public class PtTreatmentRecordServiceImplTest extends SpringTestCase{

	@Resource
	IPtTreatmentRecordService ptTreatmentRecordService;



	/**
	 * 
	 *
	 * 方法说明：添加患者就诊记录信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addPtTreatmentRecord() throws TsfaServiceException{
		PtTreatmentRecordDto ptTreatmentRecordDto = new PtTreatmentRecordDto();
		//add数据录入
		ptTreatmentRecordDto.setCode("Code");
		ptTreatmentRecordDto.setPatientNo("PatientNo");
		ptTreatmentRecordDto.setPatientName("PatientName");
		ptTreatmentRecordDto.setMedicalNo("MedicalNo");
		ptTreatmentRecordDto.setShopNo("ShopNo");
		ptTreatmentRecordDto.setShopName("ShopName");
		ptTreatmentRecordDto.setMerchantNo("MerchantNo");
		ptTreatmentRecordDto.setMerchantName("MerchantName");
		ptTreatmentRecordDto.setClinicTime(new Date());
		ptTreatmentRecordDto.setProjectCode("ProjectCode");
		ptTreatmentRecordDto.setProjectName("ProjectName");
		ptTreatmentRecordDto.setImg("Img");
		ptTreatmentRecordDto.setUpdateId("UpdateId");
		ptTreatmentRecordDto.setUpdateDate(new Date());
		ptTreatmentRecordDto.setCreateId("CreateId");
		ptTreatmentRecordDto.setCreateDate(new Date());
		ptTreatmentRecordDto.setRemark("Remark");
		ptTreatmentRecordDto.setRemark1("Remark1");
		ptTreatmentRecordDto.setRemark2("Remark2");
		ptTreatmentRecordDto.setRemark3("Remark3");
		ptTreatmentRecordDto.setRemark4("Remark4");
		
		ptTreatmentRecordService.addPtTreatmentRecord(ptTreatmentRecordDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改患者就诊记录信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updatePtTreatmentRecord() throws TsfaServiceException{
		PtTreatmentRecordDto ptTreatmentRecordDto = new PtTreatmentRecordDto();
		//update数据录入
		ptTreatmentRecordDto.setCode("Code");
		ptTreatmentRecordDto.setPatientNo("PatientNo");
		ptTreatmentRecordDto.setPatientName("PatientName");
		ptTreatmentRecordDto.setMedicalNo("MedicalNo");
		ptTreatmentRecordDto.setShopNo("ShopNo");
		ptTreatmentRecordDto.setShopName("ShopName");
		ptTreatmentRecordDto.setMerchantNo("MerchantNo");
		ptTreatmentRecordDto.setMerchantName("MerchantName");
		ptTreatmentRecordDto.setClinicTime(new Date());
		ptTreatmentRecordDto.setProjectCode("ProjectCode");
		ptTreatmentRecordDto.setProjectName("ProjectName");
		ptTreatmentRecordDto.setImg("Img");
		ptTreatmentRecordDto.setUpdateId("UpdateId");
		ptTreatmentRecordDto.setUpdateDate(new Date());
		ptTreatmentRecordDto.setCreateId("CreateId");
		ptTreatmentRecordDto.setCreateDate(new Date());
		ptTreatmentRecordDto.setRemark("Remark");
		ptTreatmentRecordDto.setRemark1("Remark1");
		ptTreatmentRecordDto.setRemark2("Remark2");
		ptTreatmentRecordDto.setRemark3("Remark3");
		ptTreatmentRecordDto.setRemark4("Remark4");

		ptTreatmentRecordService.updatePtTreatmentRecord(ptTreatmentRecordDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找患者就诊记录信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPtTreatmentRecord() throws TsfaServiceException{
		PtTreatmentRecordDto findPtTreatmentRecord = new PtTreatmentRecordDto();
		findPtTreatmentRecord.setCode("111");
		ptTreatmentRecordService.findPtTreatmentRecord(findPtTreatmentRecord);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找患者就诊记录信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPtTreatmentRecordPage() throws TsfaServiceException{
		FindPtTreatmentRecordPage findPtTreatmentRecordPage = new FindPtTreatmentRecordPage();
		Page<PtTreatmentRecordDto> page = ptTreatmentRecordService.findPtTreatmentRecordPage(findPtTreatmentRecordPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找患者就诊记录信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPtTreatmentRecords() throws TsfaServiceException{
		FindPtTreatmentRecordPage findPtTreatmentRecordPage = new FindPtTreatmentRecordPage();
		List<PtTreatmentRecordDto> page = ptTreatmentRecordService.findPtTreatmentRecords(findPtTreatmentRecordPage);
		Assert.assertNotNull(page);
		
	}
	
}
