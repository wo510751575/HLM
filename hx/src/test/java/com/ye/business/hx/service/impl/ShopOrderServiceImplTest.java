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
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.base.mvc.web.test.SpringTestCase;

import java.util.Date;
import java.util.List;

import com.ye.business.hx.dto.ShopOrderDto;
import com.ye.business.hx.emus.OrderStatus;
import com.ye.business.hx.emus.OrderType;
import com.ye.business.hx.emus.PayType;
import com.ye.business.hx.dto.FindShopOrderPage;
import com.ye.business.hx.service.IShopOrderService;

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
public class ShopOrderServiceImplTest extends SpringTestCase{

	@Resource
	IShopOrderService shopOrderService;



	/**
	 * 
	 *
	 * 方法说明：添加门店订单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addShopOrder() throws TsfaServiceException{
		ShopOrderDto shopOrderDto = new ShopOrderDto();
		//add数据录入
		shopOrderDto.setCode("Code");
		shopOrderDto.setShopNo("73a1b1b90c5a4e9fb323a1a1beb5616a");
		shopOrderDto.setShopName("一鸣口腔");
		shopOrderDto.setMerchantNo("73a1b1b90c5a4e9fb323a1a1beb5616a");
		shopOrderDto.setMerchantName("一鸣口腔");
		shopOrderDto.setOrderNo("OrderNo");
		shopOrderDto.setOrderType(OrderType.SERVER.toString());
		shopOrderDto.setServeCode("LJ_9bc2d2673aa04d4397b0dc5557d136a6");
		shopOrderDto.setMemberNoGuid("6a76bc4a83734b20b5b4e8271b0de193");
//		shopOrderDto.setMemberNameGuid("徐医生");
//		shopOrderDto.setMobile("Mobile");
		shopOrderDto.setSerialNum("4200000273201903103310654110");
		shopOrderDto.setPayType(PayType.WX.toString());
		shopOrderDto.setAmount(1000L);
		shopOrderDto.setPayTime(new Date());
		shopOrderDto.setPayCert("test.jpg,test2.jpg");
		shopOrderDto.setStatus(OrderStatus.WAIT.toString());
//		shopOrderDto.setUpdateId("UpdateId");
//		shopOrderDto.setUpdateDate(new Date());
//		shopOrderDto.setCreateId("CreateId");
//		shopOrderDto.setCreateDate(new Date());
//		shopOrderDto.setRemark("Remark");
//		shopOrderDto.setRemark2("Remark2");
//		shopOrderDto.setRemark3("Remark3");
//		
//		shopOrderService.addShopOrder(shopOrderDto);
	
		System.out.println(JsonUtils.jsonFromObject(shopOrderDto));
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改门店订单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateShopOrder() throws TsfaServiceException{
		ShopOrderDto shopOrderDto = new ShopOrderDto();
		//update数据录入
		shopOrderDto.setCode("Code");
		shopOrderDto.setShopNo("ShopNo");
		shopOrderDto.setShopName("ShopName");
		shopOrderDto.setMerchantNo("MerchantNo");
		shopOrderDto.setMerchantName("MerchantName");
		shopOrderDto.setOrderNo("OrderNo");
		shopOrderDto.setOrderType("OrderType");
		shopOrderDto.setServeCode("ServeCode");
		shopOrderDto.setServeName("ServeName");
		shopOrderDto.setMemberNoGuid("MemberNoGuid");
		shopOrderDto.setMemberNameGuid("MemberNameGuid");
		shopOrderDto.setMobile("Mobile");
		shopOrderDto.setSerialNum("SerialNum");
		shopOrderDto.setPayType("PayType");
		shopOrderDto.setAmount(2000L);
		shopOrderDto.setPayTime(new Date());
		shopOrderDto.setPayCert("PayCert");
		shopOrderDto.setStatus("Status");
		shopOrderDto.setUpdateId("UpdateId");
		shopOrderDto.setUpdateDate(new Date());
		shopOrderDto.setCreateId("CreateId");
		shopOrderDto.setCreateDate(new Date());
		shopOrderDto.setRemark("Remark");
		shopOrderDto.setRemark2("Remark2");
		shopOrderDto.setRemark3("Remark3");

		shopOrderService.updateShopOrder(shopOrderDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找门店订单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findShopOrder() throws TsfaServiceException{
		ShopOrderDto findShopOrder = new ShopOrderDto();
		findShopOrder.setCode("111");
		shopOrderService.findShopOrder(findShopOrder);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找门店订单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findShopOrderPage() throws TsfaServiceException{
		FindShopOrderPage findShopOrderPage = new FindShopOrderPage();
		Page<ShopOrderDto> page = shopOrderService.findShopOrderPage(findShopOrderPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找门店订单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findShopOrders() throws TsfaServiceException{
		FindShopOrderPage findShopOrderPage = new FindShopOrderPage();
		List<ShopOrderDto> page = shopOrderService.findShopOrders(findShopOrderPage);
		Assert.assertNotNull(page);
		
	}
	
}
