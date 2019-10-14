package com.ye.business.hx.service.impl;

import java.util.Date;
import java.util.List;

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
import com.ye.business.hx.dto.FindProjectPricePage;
import com.ye.business.hx.dto.ProjectPriceDto;
import com.ye.business.hx.emus.Status;
import com.ye.business.hx.emus.YN;
import com.ye.business.hx.service.IProjectPriceService;

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
public class ProjectPriceServiceImplTest extends SpringTestCase{

	@Resource
	IProjectPriceService projectPriceService;

       
	/**
	 * 
	 *
	 * 方法说明：添加项目单价信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addProjectPrice() throws TsfaServiceException{
		
		ProjectPriceDto projectPriceDto = new ProjectPriceDto();
		//add数据录入
		projectPriceDto.setMerchantNo("73a1b1b90c5a4e9fb323a1a1beb5616a");
		projectPriceDto.setMerchantName("一鸣口腔");
		projectPriceDto.setType1Name("缺省大类");
		projectPriceDto.setType1Code("LJ_9ec54d490b154ee68da697666ade04c1");
		projectPriceDto.setType2Name("口腔牙体");
		projectPriceDto.setType2Code("LJ_b98bd053b5584766975fa86138c541ff");
		projectPriceDto.setProjectName("龈下刮治");
//		projectPriceDto.setPinyin("Pinyin");
		projectPriceDto.setProjectUnit("牙");
		projectPriceDto.setPrice(30000L);
		projectPriceDto.setAllowItemDiscount(YN.Y.name());
		projectPriceDto.setAllowOrderDiscount(YN.Y.name());
		projectPriceDto.setIndexNo(1);
		projectPriceDto.setEnname("Enname");
		projectPriceDto.setMinPrice(100L);
		projectPriceDto.setMaxPrice(1000000L);
		projectPriceDto.setMinDiscount(10000);
		projectPriceDto.setStatus(Status.use.name());
		projectPriceDto.setAllowDeal(YN.Y.name());
		System.out.println(JsonUtils.jsonFromObject(projectPriceDto));
		//projectPriceService.addProjectPrice(projectPriceDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改项目单价信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateProjectPrice() throws TsfaServiceException{
		ProjectPriceDto projectPriceDto = new ProjectPriceDto();
		//update数据录入
		projectPriceDto.setCode("Code");
		projectPriceDto.setShopNo("ShopNo");
		projectPriceDto.setShopName("ShopName");
		projectPriceDto.setMerchantNo("MerchantNo");
		projectPriceDto.setMerchantName("MerchantName");
		projectPriceDto.setType1Name("Type1Name");
		projectPriceDto.setType1Code("Type1Code");
		projectPriceDto.setType2Name("Type2Name");
		projectPriceDto.setType2Code("Type2Code");
		projectPriceDto.setProjectName("ProjectName");
		projectPriceDto.setPinyin("Pinyin");
		projectPriceDto.setProjectUnit("ProjectUnit");
		projectPriceDto.setPrice(1000L);
		projectPriceDto.setAllowItemDiscount("AllowItemDiscount");
		projectPriceDto.setAllowOrderDiscount("AllowOrderDiscount");
		projectPriceDto.setIndexNo(1);
		projectPriceDto.setEnname("Enname");
		projectPriceDto.setMinPrice(100L);
		projectPriceDto.setMaxPrice(1000000L);
		projectPriceDto.setMinDiscount(10000);
		projectPriceDto.setStatus("Status");
		projectPriceDto.setAllowDeal("AllowDeal");
		projectPriceDto.setUpdateId("UpdateId");
		projectPriceDto.setUpdateDate(new Date());
		projectPriceDto.setCreateId("CreateId");
		projectPriceDto.setCreateDate(new Date());

		projectPriceService.updateProjectPrice(projectPriceDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找项目单价信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findProjectPrice() throws TsfaServiceException{
		ProjectPriceDto findProjectPrice = new ProjectPriceDto();
		findProjectPrice.setCode("111");
		projectPriceService.findProjectPrice(findProjectPrice);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找项目单价信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findProjectPricePage() throws TsfaServiceException{
		FindProjectPricePage findProjectPricePage = new FindProjectPricePage();
		Page<ProjectPriceDto> page = projectPriceService.findProjectPricePage(findProjectPricePage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找项目单价信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findProjectPrices() throws TsfaServiceException{
		FindProjectPricePage findProjectPricePage = new FindProjectPricePage();
		List<ProjectPriceDto> page = projectPriceService.findProjectPrices(findProjectPricePage);
		Assert.assertNotNull(page);
		
	}
	
}
