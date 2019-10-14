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

import com.ye.business.hx.dto.HxPatientDto;
import com.ye.business.hx.dto.FindHxPatientPage;
import com.ye.business.hx.service.IHxPatientService;

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
public class HxPatientServiceImplTest extends SpringTestCase{

	@Resource
	IHxPatientService hxPatientService;



	/**
	 * 
	 *
	 * 方法说明：添加患者信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addHxPatient() throws TsfaServiceException{
		HxPatientDto hxPatientDto = new HxPatientDto();
		//add数据录入
		hxPatientDto.setCode("Code");
		hxPatientDto.setShopNo("ShopNo");
		hxPatientDto.setShopName("ShopName");
		hxPatientDto.setMerchantNo("MerchantNo");
		hxPatientDto.setMerchantName("MerchantName");
		hxPatientDto.setName("Name");
		hxPatientDto.setSex("Sex");
		hxPatientDto.setType("Type");
		hxPatientDto.setCaseNo("CaseNo");
		hxPatientDto.setBirthDate(new Date());
		hxPatientDto.setAge(1);
		hxPatientDto.setNationality("Nationality");
		hxPatientDto.setIdno("Idno");
		hxPatientDto.setPhone("Phone");
		hxPatientDto.setPhoneRemark("PhoneRemark");
		hxPatientDto.setPhoneNo("PhoneNo");
		hxPatientDto.setPhoneNoRemark("PhoneNoRemark");
		hxPatientDto.setWechat("Wechat");
		hxPatientDto.setQqNo("QqNo");
		hxPatientDto.setMail("Mail");
		hxPatientDto.setProvinceCode("ProvinceCode");
		hxPatientDto.setProvince("Province");
		hxPatientDto.setCityCode("CityCode");
		hxPatientDto.setCity("City");
		hxPatientDto.setAreaCode("AreaCode");
		hxPatientDto.setArea("Area");
		hxPatientDto.setAddrDetail("AddrDetail");
		hxPatientDto.setAddrInfo("AddrInfo");
		hxPatientDto.setSource1Code("Source1Code");
		hxPatientDto.setSource1("Source1");
		hxPatientDto.setSource2Code("Source2Code");
		hxPatientDto.setSource2("Source2");
		hxPatientDto.setSource3Code("Source3Code");
		hxPatientDto.setSource3("Source3");
		hxPatientDto.setRemark("Remark");
		hxPatientDto.setPastHistory("PastHistory");
		hxPatientDto.setAllergyHistory("AllergyHistory");
		hxPatientDto.setMedicationHistory("MedicationHistory");
		hxPatientDto.setCreateTime(new Date());
		hxPatientDto.setFirstMemberNo("FirstMemberNo");
		hxPatientDto.setFirstMemberName("FirstMemberName");
		hxPatientDto.setDutyMemberNo("DutyMemberNo");
		hxPatientDto.setDutyMemberName("DutyMemberName");
		hxPatientDto.setConsMemberNo("ConsMemberNo");
		hxPatientDto.setConsMemberName("ConsMemberName");
		hxPatientDto.setCreateId("CreateId");
		hxPatientDto.setCreateDate(new Date());
		hxPatientDto.setUpdateId("UpdateId");
		hxPatientDto.setUpdateDate(new Date());
		
		hxPatientService.addHxPatient(hxPatientDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改患者信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateHxPatient() throws TsfaServiceException{
		HxPatientDto hxPatientDto = new HxPatientDto();
		//update数据录入
		hxPatientDto.setCode("Code");
		hxPatientDto.setShopNo("ShopNo");
		hxPatientDto.setShopName("ShopName");
		hxPatientDto.setMerchantNo("MerchantNo");
		hxPatientDto.setMerchantName("MerchantName");
		hxPatientDto.setName("Name");
		hxPatientDto.setSex("Sex");
		hxPatientDto.setType("Type");
		hxPatientDto.setCaseNo("CaseNo");
		hxPatientDto.setBirthDate(new Date());
		hxPatientDto.setAge(1);
		hxPatientDto.setNationality("Nationality");
		hxPatientDto.setIdno("Idno");
		hxPatientDto.setPhone("Phone");
		hxPatientDto.setPhoneRemark("PhoneRemark");
		hxPatientDto.setPhoneNo("PhoneNo");
		hxPatientDto.setPhoneNoRemark("PhoneNoRemark");
		hxPatientDto.setWechat("Wechat");
		hxPatientDto.setQqNo("QqNo");
		hxPatientDto.setMail("Mail");
		hxPatientDto.setProvinceCode("ProvinceCode");
		hxPatientDto.setProvince("Province");
		hxPatientDto.setCityCode("CityCode");
		hxPatientDto.setCity("City");
		hxPatientDto.setAreaCode("AreaCode");
		hxPatientDto.setArea("Area");
		hxPatientDto.setAddrDetail("AddrDetail");
		hxPatientDto.setAddrInfo("AddrInfo");
		hxPatientDto.setSource1Code("Source1Code");
		hxPatientDto.setSource1("Source1");
		hxPatientDto.setSource2Code("Source2Code");
		hxPatientDto.setSource2("Source2");
		hxPatientDto.setSource3Code("Source3Code");
		hxPatientDto.setSource3("Source3");
		hxPatientDto.setRemark("Remark");
		hxPatientDto.setPastHistory("PastHistory");
		hxPatientDto.setAllergyHistory("AllergyHistory");
		hxPatientDto.setMedicationHistory("MedicationHistory");
		hxPatientDto.setCreateTime(new Date());
		hxPatientDto.setFirstMemberNo("FirstMemberNo");
		hxPatientDto.setFirstMemberName("FirstMemberName");
		hxPatientDto.setDutyMemberNo("DutyMemberNo");
		hxPatientDto.setDutyMemberName("DutyMemberName");
		hxPatientDto.setConsMemberNo("ConsMemberNo");
		hxPatientDto.setConsMemberName("ConsMemberName");
		hxPatientDto.setCreateId("CreateId");
		hxPatientDto.setCreateDate(new Date());
		hxPatientDto.setUpdateId("UpdateId");
		hxPatientDto.setUpdateDate(new Date());

		hxPatientService.updateHxPatient(hxPatientDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找患者信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findHxPatient() throws TsfaServiceException{
		HxPatientDto findHxPatient = new HxPatientDto();
		findHxPatient.setCode("111");
		hxPatientService.findHxPatient(findHxPatient);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找患者信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findHxPatientPage() throws TsfaServiceException{
		FindHxPatientPage findHxPatientPage = new FindHxPatientPage();
		Page<HxPatientDto> page = hxPatientService.findHxPatientPage(findHxPatientPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找患者信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findHxPatients() throws TsfaServiceException{
		FindHxPatientPage findHxPatientPage = new FindHxPatientPage();
		List<HxPatientDto> page = hxPatientService.findHxPatients(findHxPatientPage);
		Assert.assertNotNull(page);
		
	}
	
}
