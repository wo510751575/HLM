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
import com.lj.base.core.util.DateUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;

import java.util.Date;
import java.util.List;

import com.ye.business.hx.dto.GuidScheduleLogDto;
import com.ye.business.hx.emus.AptType;
import com.ye.business.hx.dto.FindGuidScheduleLogPage;
import com.ye.business.hx.service.IGuidScheduleLogService;

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
public class GuidScheduleLogServiceImplTest extends SpringTestCase{

	@Resource
	IGuidScheduleLogService guidScheduleLogService;



	/**
	 * 
	 *
	 * 方法说明：添加员工历史排班信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addGuidScheduleLog() throws TsfaServiceException{
		GuidScheduleLogDto guidScheduleLogDto = new GuidScheduleLogDto();
		//add数据录入
		guidScheduleLogDto.setCode("Code");
		guidScheduleLogDto.setMemberNoGuid("c874ecef6ef44284b9cb1495a929b415");
		guidScheduleLogDto.setMemberNameGuid("李医生");
		guidScheduleLogDto.setShopNo("73a1b1b90c5a4e9fb323a1a1beb5616a");
		guidScheduleLogDto.setShopName("一鸣口腔");
		guidScheduleLogDto.setMerchantNo("73a1b1b90c5a4e9fb323a1a1beb5616a");
		guidScheduleLogDto.setMerchantName("一鸣口腔");
		guidScheduleLogDto.setWorkDate(DateUtils.parseDate("2019-03-10",DateUtils.PATTERN_yyyy_MM_dd,null));
		guidScheduleLogDto.setDayNum(7);
		guidScheduleLogDto.setScheduleCode("LJ_c2e02d2f63884da2abf78c1c973ae951");
		guidScheduleLogDto.setScheduleName("休息");
		guidScheduleLogDto.setWorkTime(new Date());
		guidScheduleLogDto.setOffTime(new Date());
		guidScheduleLogDto.setAptFlag(AptType.Y.toString());
//		guidScheduleLogDto.setCreateId("CreateId");
		guidScheduleLogDto.setCreateDate(new Date());
		
		guidScheduleLogService.addGuidScheduleLog(guidScheduleLogDto);
		
		guidScheduleLogDto.setMemberNoGuid("6a76bc4a83734b20b5b4e8271b0de193");
		guidScheduleLogDto.setMemberNameGuid("徐医生");
		guidScheduleLogService.addGuidScheduleLog(guidScheduleLogDto);
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改员工历史排班信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateGuidScheduleLog() throws TsfaServiceException{
		GuidScheduleLogDto guidScheduleLogDto = new GuidScheduleLogDto();
		//update数据录入
		guidScheduleLogDto.setCode("Code");
		guidScheduleLogDto.setMemberNoGuid("MemberNoGuid");
		guidScheduleLogDto.setMemberNameGuid("MemberNameGuid");
		guidScheduleLogDto.setShopNo("ShopNo");
		guidScheduleLogDto.setShopName("ShopName");
		guidScheduleLogDto.setMerchantNo("MerchantNo");
		guidScheduleLogDto.setMerchantName("MerchantName");
		guidScheduleLogDto.setWorkDate(new Date());
		guidScheduleLogDto.setDayNum(1);
		guidScheduleLogDto.setScheduleCode("ScheduleCode");
		guidScheduleLogDto.setScheduleName("ScheduleName");
		guidScheduleLogDto.setWorkTime(new Date());
		guidScheduleLogDto.setOffTime(new Date());
		guidScheduleLogDto.setAptFlag("AptFlag");
		guidScheduleLogDto.setCreateId("CreateId");
		guidScheduleLogDto.setCreateDate(new Date());

		guidScheduleLogService.updateGuidScheduleLog(guidScheduleLogDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找员工历史排班信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findGuidScheduleLog() throws TsfaServiceException{
		GuidScheduleLogDto findGuidScheduleLog = new GuidScheduleLogDto();
		findGuidScheduleLog.setCode("LJ_98a2398651fa4499a15c52fdb400d7da");
		guidScheduleLogService.findGuidScheduleLog(findGuidScheduleLog);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找员工历史排班信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findGuidScheduleLogPage() throws TsfaServiceException{
		FindGuidScheduleLogPage findGuidScheduleLogPage = new FindGuidScheduleLogPage();
		Page<GuidScheduleLogDto> page = guidScheduleLogService.findGuidScheduleLogPage(findGuidScheduleLogPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找员工历史排班信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findGuidScheduleLogs() throws TsfaServiceException{
		FindGuidScheduleLogPage findGuidScheduleLogPage = new FindGuidScheduleLogPage();
		List<GuidScheduleLogDto> page = guidScheduleLogService.findGuidScheduleLogs(findGuidScheduleLogPage);
		Assert.assertNotNull(page);
		
	}
	
	@Test
	public void batchAddGuidScheduleLog() {
		String batchNum=(new Date().getTime())+"";
		guidScheduleLogService.batchAddGuidScheduleLog(batchNum);
	}
	
}
