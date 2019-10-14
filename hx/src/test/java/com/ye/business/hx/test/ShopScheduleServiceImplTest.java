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
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.base.mvc.web.test.SpringTestCase;

import java.util.Date;
import java.util.List;

import com.ye.business.hx.dto.ShopScheduleDto;
import com.ye.business.hx.dto.FindShopSchedulePage;
import com.ye.business.hx.service.IShopScheduleService;

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
public class ShopScheduleServiceImplTest extends SpringTestCase{

	@Resource
	IShopScheduleService shopScheduleService;



	/**
	 * 
	 *
	 * 方法说明：添加门店班次信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addShopSchedule() throws TsfaServiceException{
		ShopScheduleDto shopScheduleDto = new ShopScheduleDto();
		//add数据录入
//		shopScheduleDto.setCode("Code");
		shopScheduleDto.setShopNo("LJ_93e1cacfc67b4e8c96be08ef37200cd0");
		shopScheduleDto.setShopName("建材2");
		shopScheduleDto.setMerchantNo("e96f64d736564a768c9ab9f23f4962df");
		shopScheduleDto.setMerchantName("建材");
		shopScheduleDto.setScheduleName("正常班");
//		shopScheduleDto.setWorkTime(new Date());
//		shopScheduleDto.setOffTime(new Date());
		shopScheduleDto.setAptFlag("Y");
		shopScheduleDto.setDelFlag("N");
//		shopScheduleDto.setUpdateId("UpdateId");
//		shopScheduleDto.setUpdateDate(new Date());
		shopScheduleDto.setCreateId("LJ_78ad70d60ab74eb18bb0a4ecfde535ff");
//		shopScheduleDto.setCreateDate(new Date());
		System.out.println(JsonUtils.jsonFromObject(shopScheduleDto));
		//shopScheduleService.addShopSchedule(shopScheduleDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改门店班次信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateShopSchedule() throws TsfaServiceException{
		ShopScheduleDto shopScheduleDto = new ShopScheduleDto();
		//update数据录入
		shopScheduleDto.setCode("Code");
		shopScheduleDto.setShopNo("ShopNo");
		shopScheduleDto.setShopName("ShopName");
		shopScheduleDto.setMerchantNo("MerchantNo");
		shopScheduleDto.setMerchantName("MerchantName");
		shopScheduleDto.setScheduleName("ScheduleName");
		shopScheduleDto.setWorkTime(new Date());
		shopScheduleDto.setOffTime(new Date());
		shopScheduleDto.setAptFlag("AptFlag");
		shopScheduleDto.setDelFlag("DelFlag");
		shopScheduleDto.setUpdateId("UpdateId");
		shopScheduleDto.setUpdateDate(new Date());
		shopScheduleDto.setCreateId("CreateId");
		shopScheduleDto.setCreateDate(new Date());

		shopScheduleService.updateShopSchedule(shopScheduleDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找门店班次信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findShopSchedule() throws TsfaServiceException{
		ShopScheduleDto findShopSchedule = new ShopScheduleDto();
		findShopSchedule.setCode("111");
		shopScheduleService.findShopSchedule(findShopSchedule);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找门店班次信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findShopSchedulePage() throws TsfaServiceException{
		FindShopSchedulePage findShopSchedulePage = new FindShopSchedulePage();
		Page<ShopScheduleDto> page = shopScheduleService.findShopSchedulePage(findShopSchedulePage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找门店班次信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findShopSchedules() throws TsfaServiceException{
		FindShopSchedulePage findShopSchedulePage = new FindShopSchedulePage();
		List<ShopScheduleDto> page = shopScheduleService.findShopSchedules(findShopSchedulePage);
		Assert.assertNotNull(page);
		
	}
	
}
