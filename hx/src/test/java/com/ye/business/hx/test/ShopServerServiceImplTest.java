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

import com.ye.business.hx.dto.ShopServerDto;
import com.ye.business.hx.dto.FindShopServerPage;
import com.ye.business.hx.service.IShopServerService;

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
public class ShopServerServiceImplTest extends SpringTestCase{

	@Resource
	IShopServerService shopServerService;



	/**
	 * 
	 *
	 * 方法说明：添加门诊服务信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addShopServer() throws TsfaServiceException{
		ShopServerDto shopServerDto = new ShopServerDto();
		//add数据录入
		shopServerDto.setCode("Code");
		shopServerDto.setShopNo("73a1b1b90c5a4e9fb323a1a1beb5616a");
		shopServerDto.setShopName("一鸣口腔");
		shopServerDto.setMerchantNo("73a1b1b90c5a4e9fb323a1a1beb5616a");
		shopServerDto.setMerchantName("一鸣口腔");
		shopServerDto.setServerCode("LJ_1875605f3f214ca094173f8e8355e7de");
		shopServerDto.setServerName("基础版套餐");
		shopServerDto.setPrice(3980000L);
		shopServerDto.setOriginalPrice(2980000L);
		shopServerDto.setOrderNo(null);
//		shopServerDto.setUpdateId("UpdateId");
//		shopServerDto.setUpdateDate(new Date());
//		shopServerDto.setCreateId("CreateId");
//		shopServerDto.setCreateDate(new Date());
		
		shopServerService.addShopServer(shopServerDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改门诊服务信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateShopServer() throws TsfaServiceException{
		ShopServerDto shopServerDto = new ShopServerDto();
		//update数据录入
		shopServerDto.setCode("Code");
		shopServerDto.setShopNo("ShopNo");
		shopServerDto.setShopName("ShopName");
		shopServerDto.setMerchantNo("MerchantNo");
		shopServerDto.setMerchantName("MerchantName");
		shopServerDto.setServerCode("ServerCode");
		shopServerDto.setServerName("ServerName");
		shopServerDto.setPrice(1000L);
		shopServerDto.setOriginalPrice(5000L);
		shopServerDto.setOrderNo("OrderNo");
		shopServerDto.setUpdateId("UpdateId");
		shopServerDto.setUpdateDate(new Date());
		shopServerDto.setCreateId("CreateId");
		shopServerDto.setCreateDate(new Date());

		shopServerService.updateShopServer(shopServerDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找门诊服务信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findShopServer() throws TsfaServiceException{
		ShopServerDto findShopServer = new ShopServerDto();
		findShopServer.setCode("111");
		shopServerService.findShopServer(findShopServer);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找门诊服务信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findShopServerPage() throws TsfaServiceException{
		FindShopServerPage findShopServerPage = new FindShopServerPage();
		Page<ShopServerDto> page = shopServerService.findShopServerPage(findShopServerPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找门诊服务信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findShopServers() throws TsfaServiceException{
		FindShopServerPage findShopServerPage = new FindShopServerPage();
		List<ShopServerDto> page = shopServerService.findShopServers(findShopServerPage);
		Assert.assertNotNull(page);
		
	}
	
}
