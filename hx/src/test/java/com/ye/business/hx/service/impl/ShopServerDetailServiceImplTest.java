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
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;

import java.util.Date;
import java.util.List;

import com.ye.business.hx.dto.ShopServerDetailDto;
import com.ye.business.hx.emus.ServerDetailShop;
import com.ye.business.hx.dto.FindShopServerDetailPage;
import com.ye.business.hx.service.IShopServerDetailService;

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
public class ShopServerDetailServiceImplTest extends SpringTestCase{

	@Resource
	IShopServerDetailService shopServerDetailService;



	/**
	 * 
	 *
	 * 方法说明：添加门诊服务项信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addShopServerDetail() throws TsfaServiceException{
		ShopServerDetailDto shopServerDetailDto = new ShopServerDetailDto();
		//add数据录入
		shopServerDetailDto.setCode("Code");
		shopServerDetailDto.setShopServerCode("LJ_9c493896a0bc4e99b39395dacb28ef21");
		shopServerDetailDto.setServerCode("LJ_1875605f3f214ca094173f8e8355e7de");
		shopServerDetailDto.setServerName("基础版套餐");
		shopServerDetailDto.setUserType("2");
		shopServerDetailDto.setPrice(0L);
		shopServerDetailDto.setServerNum(10);
		shopServerDetailDto.setUseNum(0);
		shopServerDetailDto.setUnuseNum(0);
		shopServerDetailDto.setFreezeNum(0);
		shopServerDetailDto.setIsShop(ServerDetailShop.YES.toString());
		shopServerDetailDto.setServerDesc("1500元/人，精准客户指主诉明确且为正畸，种植和修复类型的客户，计算费用按客户实际到店计算");
//		shopServerDetailDto.setUpdateId("UpdateId");
//		shopServerDetailDto.setUpdateDate(new Date());
//		shopServerDetailDto.setCreateId("CreateId");
//		shopServerDetailDto.setCreateDate(new Date());
		shopServerDetailService.addShopServerDetail(shopServerDetailDto);
		
		
		shopServerDetailDto.setUserType("1");
		shopServerDetailDto.setIsShop(ServerDetailShop.NO.toString());
		shopServerDetailDto.setServerDesc("800元/人，精准客户指主诉明确且为正畸、种植和修复类型的客户，计算费用按线索有效性（何为有效性？）计算");
//		shopServerDetailDto.setUpdateId("UpdateId");
//		shopServerDetailDto.setUpdateDate(new Date());
//		shopServerDetailDto.setCreateId("CreateId");
//		shopServerDetailDto.setCreateDate(new Date());
		shopServerDetailService.addShopServerDetail(shopServerDetailDto);
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改门诊服务项信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateShopServerDetail() throws TsfaServiceException{
		ShopServerDetailDto shopServerDetailDto = new ShopServerDetailDto();
		//update数据录入
		shopServerDetailDto.setCode("Code");
		shopServerDetailDto.setShopServerCode("ShopServerCode");
		shopServerDetailDto.setServerCode("ServerCode");
		shopServerDetailDto.setServerName("ServerName");
		shopServerDetailDto.setUserType("UserType");
		shopServerDetailDto.setPrice(1000L);
		shopServerDetailDto.setServerNum(1);
		shopServerDetailDto.setUseNum(2);
		shopServerDetailDto.setUnuseNum(3);
		shopServerDetailDto.setFreezeNum(4);
		shopServerDetailDto.setUpdateId("UpdateId");
		shopServerDetailDto.setUpdateDate(new Date());
		shopServerDetailDto.setCreateId("CreateId");
		shopServerDetailDto.setCreateDate(new Date());

		shopServerDetailService.updateShopServerDetail(shopServerDetailDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找门诊服务项信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findShopServerDetail() throws TsfaServiceException{
		ShopServerDetailDto findShopServerDetail = new ShopServerDetailDto();
		findShopServerDetail.setCode("111");
		shopServerDetailService.findShopServerDetail(findShopServerDetail);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找门诊服务项信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findShopServerDetailPage() throws TsfaServiceException{
		FindShopServerDetailPage findShopServerDetailPage = new FindShopServerDetailPage();
		Page<ShopServerDetailDto> page = shopServerDetailService.findShopServerDetailPage(findShopServerDetailPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找门诊服务项信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findShopServerDetails() throws TsfaServiceException{
		FindShopServerDetailPage findShopServerDetailPage = new FindShopServerDetailPage();
		List<ShopServerDetailDto> page = shopServerDetailService.findShopServerDetails(findShopServerDetailPage);
		Assert.assertNotNull(page);
		
	}
	
}
