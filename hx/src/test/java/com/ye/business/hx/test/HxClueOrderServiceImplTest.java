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

import com.ye.business.hx.dto.HxClueOrderDto;
import com.ye.business.hx.dto.FindHxClueOrderPage;
import com.ye.business.hx.service.IHxClueOrderService;

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
public class HxClueOrderServiceImplTest extends SpringTestCase{

	@Resource
	IHxClueOrderService hxClueOrderService;



	/**
	 * 
	 *
	 * 方法说明：添加线索使用订单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addHxClueOrder() throws TsfaServiceException{
		HxClueOrderDto hxClueOrderDto = new HxClueOrderDto();
		//add数据录入
		hxClueOrderDto.setCode("Code");
		hxClueOrderDto.setClueCode("ClueCode");
		hxClueOrderDto.setMemberNo("MemberNo");
		hxClueOrderDto.setMemberName("MemberName");
		hxClueOrderDto.setShopNo("ShopNo");
		hxClueOrderDto.setShopName("ShopName");
		hxClueOrderDto.setMerchantNo("MerchantNo");
		hxClueOrderDto.setMerchantName("MerchantName");
		hxClueOrderDto.setShopServerCode("ShopServerCode");
		hxClueOrderDto.setServerName("ServerName");
		hxClueOrderDto.setServerCode("ServerCode");
		hxClueOrderDto.setUserType("UserType");
		hxClueOrderDto.setUserPrice(500L);
		hxClueOrderDto.setStatus("Status");
		hxClueOrderDto.setCreateId("CreateId");
		hxClueOrderDto.setCreateDate(new Date());
		hxClueOrderDto.setUpdateId("UpdateId");
		hxClueOrderDto.setUpdateDate(new Date());
		hxClueOrderDto.setRemark("Remark");
		
		hxClueOrderService.addHxClueOrder(hxClueOrderDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改线索使用订单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateHxClueOrder() throws TsfaServiceException{
		HxClueOrderDto hxClueOrderDto = new HxClueOrderDto();
		//update数据录入
		hxClueOrderDto.setCode("Code");
		hxClueOrderDto.setClueCode("ClueCode");
		hxClueOrderDto.setMemberNo("MemberNo");
		hxClueOrderDto.setMemberName("MemberName");
		hxClueOrderDto.setShopNo("ShopNo");
		hxClueOrderDto.setShopName("ShopName");
		hxClueOrderDto.setMerchantNo("MerchantNo");
		hxClueOrderDto.setMerchantName("MerchantName");
		hxClueOrderDto.setShopServerCode("ShopServerCode");
		hxClueOrderDto.setServerName("ServerName");
		hxClueOrderDto.setServerCode("ServerCode");
		hxClueOrderDto.setUserType("UserType");
		hxClueOrderDto.setUserPrice(100L);
		hxClueOrderDto.setStatus("Status");
		hxClueOrderDto.setCreateId("CreateId");
		hxClueOrderDto.setCreateDate(new Date());
		hxClueOrderDto.setUpdateId("UpdateId");
		hxClueOrderDto.setUpdateDate(new Date());
		hxClueOrderDto.setRemark("Remark");

		hxClueOrderService.updateHxClueOrder(hxClueOrderDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找线索使用订单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findHxClueOrder() throws TsfaServiceException{
		HxClueOrderDto findHxClueOrder = new HxClueOrderDto();
		findHxClueOrder.setCode("111");
		hxClueOrderService.findHxClueOrder(findHxClueOrder);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找线索使用订单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findHxClueOrderPage() throws TsfaServiceException{
		FindHxClueOrderPage findHxClueOrderPage = new FindHxClueOrderPage();
		Page<HxClueOrderDto> page = hxClueOrderService.findHxClueOrderPage(findHxClueOrderPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找线索使用订单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findHxClueOrders() throws TsfaServiceException{
		FindHxClueOrderPage findHxClueOrderPage = new FindHxClueOrderPage();
		List<HxClueOrderDto> page = hxClueOrderService.findHxClueOrders(findHxClueOrderPage);
		Assert.assertNotNull(page);
		
	}
	
}
