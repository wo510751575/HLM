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

import com.alibaba.fastjson.JSONArray;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.base.mvc.web.test.SpringTestCase;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ye.business.hx.dto.GuidScheduleDto;
import com.ye.business.hx.dto.FindGuidSchedulePage;
import com.ye.business.hx.service.IGuidScheduleService;

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
public class GuidScheduleServiceImplTest extends SpringTestCase{

	@Resource
	IGuidScheduleService guidScheduleService;



	/**
	 * 
	 *
	 * 方法说明：添加员工班次信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addGuidSchedule() throws TsfaServiceException{
		GuidScheduleDto guidScheduleDto = new GuidScheduleDto();
		//add数据录入
		guidScheduleDto.setCode("Code");
		guidScheduleDto.setMemberNoGuid("MemberNoGuid");
		guidScheduleDto.setMemberNameGuid("MemberNameGuid");
		guidScheduleDto.setShopNo("ShopNo");
		guidScheduleDto.setShopName("ShopName");
		guidScheduleDto.setMerchantNo("MerchantNo");
		guidScheduleDto.setMerchantName("MerchantName");
		guidScheduleDto.setType("Type");
		guidScheduleDto.setDayNum(1);
		guidScheduleDto.setScheduleCode("ScheduleCode");
		guidScheduleDto.setCreateId("CreateId");
		guidScheduleDto.setCreateDate(new Date());
		
		guidScheduleService.addGuidSchedule(guidScheduleDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改员工班次信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateGuidSchedule() throws TsfaServiceException{
		GuidScheduleDto guidScheduleDto = new GuidScheduleDto();
		//update数据录入
		guidScheduleDto.setCode("Code");
		guidScheduleDto.setMemberNoGuid("MemberNoGuid");
		guidScheduleDto.setMemberNameGuid("MemberNameGuid");
		guidScheduleDto.setShopNo("ShopNo");
		guidScheduleDto.setShopName("ShopName");
		guidScheduleDto.setMerchantNo("MerchantNo");
		guidScheduleDto.setMerchantName("MerchantName");
		guidScheduleDto.setType("Type");
		guidScheduleDto.setDayNum(1);
		guidScheduleDto.setScheduleCode("ScheduleCode");
		guidScheduleDto.setCreateId("CreateId");
		guidScheduleDto.setCreateDate(new Date());

		guidScheduleService.updateGuidSchedule(guidScheduleDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找员工班次信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findGuidSchedule() throws TsfaServiceException{
		GuidScheduleDto findGuidSchedule = new GuidScheduleDto();
		findGuidSchedule.setCode("111");
		guidScheduleService.findGuidSchedule(findGuidSchedule);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找员工班次信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findGuidSchedulePage() throws TsfaServiceException{
		FindGuidSchedulePage findGuidSchedulePage = new FindGuidSchedulePage();
		Page<GuidScheduleDto> page = guidScheduleService.findGuidSchedulePage(findGuidSchedulePage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找员工班次信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findGuidSchedules() throws TsfaServiceException{
		FindGuidSchedulePage findGuidSchedulePage = new FindGuidSchedulePage();
		List<GuidScheduleDto> page = guidScheduleService.findGuidSchedules(findGuidSchedulePage);
		Assert.assertNotNull(page);
		
	}
	
	@Test
	public void batchGuidSchedules() {
		GuidScheduleDto guidScheduleDto=new GuidScheduleDto();
		guidScheduleDto.setMemberNameGuid("测测");
//		guidScheduleDto.setShopNo(user.getCompany().getId());//商户即门诊
//		guidScheduleDto.setShopName(user.getCompany().getName());//商户即门诊
		guidScheduleDto.setMerchantNo("测测1");
		guidScheduleDto.setMerchantName("测测2");
		guidScheduleDto.setType("type");
		guidScheduleDto.setMemberNoGuid("testGUid");
//		guidScheduleDto
		String eString="[{\"dayNum\":1,\"scheduleCodes\":\"LJ_c2e02d2f63884da2abf78c1c973ae951,LJ_b724a967cbf946469d2184203ff77e4a\"},{\"dayNum\":2,\"scheduleCodes\":\"LJ_c2e02d2f63884da2abf78c1c973ae951\"}]";
		guidScheduleDto.setScheduleJson(eString);
		try {
		//1.批量新增
		guidScheduleService.addGuidScheduleBatch(guidScheduleDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String eString="[{\"dayNum\":1,\"scheduleCodes\":\"LJ_c2e02d2f63884da2abf78c1c973ae951,LJ_b724a967cbf946469d2184203ff77e4a\"},{\"dayNum\":2,\"scheduleCodes\":\"LJ_c2e02d2f63884da2abf78c1c973ae951\"}]";
		GuidScheduleDto dto=new GuidScheduleDto();
		dto.setMemberNoGuid("c874ecef6ef44284b9cb1495a929b415");
		dto.setType("FIXED");
		dto.setScheduleJson(eString);
		
		System.out.println(JsonUtils.jsonFromObject(dto));
		
		List<GuidScheduleDto> schedules = JSONArray.parseArray(eString,GuidScheduleDto.class);
	
		for (Iterator iterator = schedules.iterator(); iterator.hasNext();) {
			GuidScheduleDto guidScheduleDto = (GuidScheduleDto) iterator.next();
			System.out.println(guidScheduleDto.getDayNum());
			System.out.println(guidScheduleDto.getScheduleCodes());
		}
	}
}
