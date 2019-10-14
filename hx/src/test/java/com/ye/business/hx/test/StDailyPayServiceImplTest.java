package com.ye.business.hx.test;

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
import com.lj.base.core.util.DateUtils;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.ye.business.hx.dto.FindStDailyPayPage;
import com.ye.business.hx.dto.StDailyPayDto;
import com.ye.business.hx.emus.PayMode;
import com.ye.business.hx.service.IStDailyPayService;

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
public class StDailyPayServiceImplTest extends SpringTestCase{

	@Resource
	IStDailyPayService stDailyPayService;



	/**
	 * 
	 *
	 * 方法说明：添加每日收费统计信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addStDailyPay() throws TsfaServiceException{
		Date stDate= DateUtils.addDays(new Date(), -5);
		StDailyPayDto stDailyPayDto = new StDailyPayDto();
		//add数据录入
//		stDailyPayDto.setCode("Code");
//		stDailyPayDto.setShopNo("ShopNo");
//		stDailyPayDto.setShopName("ShopName");
		stDailyPayDto.setMerchantNo("73a1b1b90c5a4e9fb323a1a1beb5616a");
		stDailyPayDto.setMerchantName("一鸣口腔");
		stDailyPayDto.setStDate(stDate);
		stDailyPayDto.setPayType("LJ_8bb8c849426841d7b53fa8c8395ba802");
		stDailyPayDto.setPayTypeName("现金");
		stDailyPayDto.setPayAmount(9999L);
		stDailyPayDto.setPayMode(PayMode.SUB.name());
		stDailyPayDto.setCreateDate(new Date());
		
		stDailyPayService.addStDailyPay(stDailyPayDto);
		
		stDailyPayDto.setStDate(stDate);
		stDailyPayDto.setPayType("2");
		stDailyPayDto.setPayTypeName("支付宝");
		stDailyPayDto.setPayAmount(100L);
		stDailyPayService.addStDailyPay(stDailyPayDto);
		
		stDailyPayDto.setStDate(stDate);
		stDailyPayDto.setPayType("3");
		stDailyPayDto.setPayTypeName("微信");
		stDailyPayDto.setPayAmount(200L);
		stDailyPayService.addStDailyPay(stDailyPayDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改每日收费统计信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateStDailyPay() throws TsfaServiceException{
		StDailyPayDto stDailyPayDto = new StDailyPayDto();
		//update数据录入
		stDailyPayDto.setCode("Code");
		stDailyPayDto.setShopNo("ShopNo");
		stDailyPayDto.setShopName("ShopName");
		stDailyPayDto.setMerchantNo("MerchantNo");
		stDailyPayDto.setMerchantName("MerchantName");
		stDailyPayDto.setStDate(new Date());
		stDailyPayDto.setPayType("PayType");
		stDailyPayDto.setPayTypeName("PayTypeName");
		stDailyPayDto.setPayAmount(0L);
		stDailyPayDto.setPayMode("PayMode");
		stDailyPayDto.setCreateDate(new Date());

		stDailyPayService.updateStDailyPay(stDailyPayDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找每日收费统计信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findStDailyPay() throws TsfaServiceException{
		StDailyPayDto findStDailyPay = new StDailyPayDto();
		findStDailyPay.setCode("111");
		stDailyPayService.findStDailyPay(findStDailyPay);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找每日收费统计信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findStDailyPayPage() throws TsfaServiceException{
		FindStDailyPayPage findStDailyPayPage = new FindStDailyPayPage();
		Page<StDailyPayDto> page = stDailyPayService.findStDailyPayPage(findStDailyPayPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找每日收费统计信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findStDailyPays() throws TsfaServiceException{
		FindStDailyPayPage findStDailyPayPage = new FindStDailyPayPage();
		List<StDailyPayDto> page = stDailyPayService.findStDailyPays(findStDailyPayPage);
		Assert.assertNotNull(page);
		
	}
	
	@Test
	public void batchAddStDailyPay(){
		String batchNum="stDailyPay20190619";
		stDailyPayService.batchAddStDailyPay(batchNum);
	}
}
