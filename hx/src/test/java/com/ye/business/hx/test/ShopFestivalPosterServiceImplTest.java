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

import com.ye.business.hx.dto.ShopFestivalPosterDto;
import com.ye.business.hx.dto.FindShopFestivalPosterPage;
import com.ye.business.hx.service.IShopFestivalPosterService;

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
public class ShopFestivalPosterServiceImplTest extends SpringTestCase{

	@Resource
	IShopFestivalPosterService shopFestivalPosterService;



	/**
	 * 
	 *
	 * 方法说明：添加门诊节日问候海报信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addShopFestivalPoster() throws TsfaServiceException{
		ShopFestivalPosterDto shopFestivalPosterDto = new ShopFestivalPosterDto();
		//add数据录入
		shopFestivalPosterDto.setCode("Code");
		shopFestivalPosterDto.setFpCode("FpCode");
		shopFestivalPosterDto.setShopNo("ShopNo");
		shopFestivalPosterDto.setShopName("ShopName");
		shopFestivalPosterDto.setMerchantNo("MerchantNo");
		shopFestivalPosterDto.setMerchantName("MerchantName");
		shopFestivalPosterDto.setShopWx("ShopWx");
		shopFestivalPosterDto.setTypeName("TypeName");
		shopFestivalPosterDto.setTemplateImg("TemplateImg");
		shopFestivalPosterDto.setQcordImg("QcordImg");
		shopFestivalPosterDto.setUpdateId("UpdateId");
		shopFestivalPosterDto.setUpdateDate(new Date());
		shopFestivalPosterDto.setCreateId("CreateId");
		shopFestivalPosterDto.setCreateDate(new Date());
		shopFestivalPosterDto.setRemark("Remark");
		shopFestivalPosterDto.setRemark1("Remark1");
		shopFestivalPosterDto.setRemark2("Remark2");
		shopFestivalPosterDto.setRemark3("Remark3");
		shopFestivalPosterDto.setRemark4("Remark4");
		
		shopFestivalPosterService.addShopFestivalPoster(shopFestivalPosterDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改门诊节日问候海报信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateShopFestivalPoster() throws TsfaServiceException{
		ShopFestivalPosterDto shopFestivalPosterDto = new ShopFestivalPosterDto();
		//update数据录入
		shopFestivalPosterDto.setCode("Code");
		shopFestivalPosterDto.setFpCode("FpCode");
		shopFestivalPosterDto.setShopNo("ShopNo");
		shopFestivalPosterDto.setShopName("ShopName");
		shopFestivalPosterDto.setMerchantNo("MerchantNo");
		shopFestivalPosterDto.setMerchantName("MerchantName");
		shopFestivalPosterDto.setShopWx("ShopWx");
		shopFestivalPosterDto.setTypeName("TypeName");
		shopFestivalPosterDto.setTemplateImg("TemplateImg");
		shopFestivalPosterDto.setQcordImg("QcordImg");
		shopFestivalPosterDto.setUpdateId("UpdateId");
		shopFestivalPosterDto.setUpdateDate(new Date());
		shopFestivalPosterDto.setCreateId("CreateId");
		shopFestivalPosterDto.setCreateDate(new Date());
		shopFestivalPosterDto.setRemark("Remark");
		shopFestivalPosterDto.setRemark1("Remark1");
		shopFestivalPosterDto.setRemark2("Remark2");
		shopFestivalPosterDto.setRemark3("Remark3");
		shopFestivalPosterDto.setRemark4("Remark4");

		shopFestivalPosterService.updateShopFestivalPoster(shopFestivalPosterDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找门诊节日问候海报信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findShopFestivalPoster() throws TsfaServiceException{
		ShopFestivalPosterDto findShopFestivalPoster = new ShopFestivalPosterDto();
		findShopFestivalPoster.setCode("111");
		shopFestivalPosterService.findShopFestivalPoster(findShopFestivalPoster);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找门诊节日问候海报信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findShopFestivalPosterPage() throws TsfaServiceException{
		FindShopFestivalPosterPage findShopFestivalPosterPage = new FindShopFestivalPosterPage();
		Page<ShopFestivalPosterDto> page = shopFestivalPosterService.findShopFestivalPosterPage(findShopFestivalPosterPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找门诊节日问候海报信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findShopFestivalPosters() throws TsfaServiceException{
		FindShopFestivalPosterPage findShopFestivalPosterPage = new FindShopFestivalPosterPage();
		List<ShopFestivalPosterDto> page = shopFestivalPosterService.findShopFestivalPosters(findShopFestivalPosterPage);
		Assert.assertNotNull(page);
		
	}
	
}
