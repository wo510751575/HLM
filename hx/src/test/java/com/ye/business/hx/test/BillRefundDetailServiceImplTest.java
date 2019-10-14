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

import com.ye.business.hx.dto.BillRefundDetailDto;
import com.ye.business.hx.dto.FindBillRefundDetailPage;
import com.ye.business.hx.service.IBillRefundDetailService;

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
public class BillRefundDetailServiceImplTest extends SpringTestCase{

	@Resource
	IBillRefundDetailService billRefundDetailService;



	/**
	 * 
	 *
	 * 方法说明：添加账单退款详情信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addBillRefundDetail() throws TsfaServiceException{
		BillRefundDetailDto billRefundDetailDto = new BillRefundDetailDto();
		//add数据录入
		billRefundDetailDto.setCode("Code");
		billRefundDetailDto.setBillCode("BillCode");
		billRefundDetailDto.setRefundCode("RefundCode");
		billRefundDetailDto.setShopNo("ShopNo");
		billRefundDetailDto.setShopName("ShopName");
		billRefundDetailDto.setMerchantNo("MerchantNo");
		billRefundDetailDto.setMerchantName("MerchantName");
		billRefundDetailDto.setProjectCode("ProjectCode");
		billRefundDetailDto.setProjectName("ProjectName");
		billRefundDetailDto.setItemNum(1);
		billRefundDetailDto.setReturnAmount(10000L);
		billRefundDetailDto.setUpdateId("UpdateId");
		billRefundDetailDto.setUpdateDate(new Date());
		billRefundDetailDto.setCreateId("CreateId");
		billRefundDetailDto.setCreateDate(new Date());
		billRefundDetailDto.setRemark("Remark");
		billRefundDetailDto.setRemark1("Remark1");
		billRefundDetailDto.setRemark2("Remark2");
		billRefundDetailDto.setRemark3("Remark3");
		billRefundDetailDto.setRemark4("Remark4");
		
		billRefundDetailService.addBillRefundDetail(billRefundDetailDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改账单退款详情信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateBillRefundDetail() throws TsfaServiceException{
		BillRefundDetailDto billRefundDetailDto = new BillRefundDetailDto();
		//update数据录入
		billRefundDetailDto.setCode("Code");
		billRefundDetailDto.setBillCode("BillCode");
		billRefundDetailDto.setRefundCode("RefundCode");
		billRefundDetailDto.setShopNo("ShopNo");
		billRefundDetailDto.setShopName("ShopName");
		billRefundDetailDto.setMerchantNo("MerchantNo");
		billRefundDetailDto.setMerchantName("MerchantName");
		billRefundDetailDto.setProjectCode("ProjectCode");
		billRefundDetailDto.setProjectName("ProjectName");
		billRefundDetailDto.setItemNum(1);
		billRefundDetailDto.setReturnAmount(10000L);
		billRefundDetailDto.setUpdateId("UpdateId");
		billRefundDetailDto.setUpdateDate(new Date());
		billRefundDetailDto.setCreateId("CreateId");
		billRefundDetailDto.setCreateDate(new Date());
		billRefundDetailDto.setRemark("Remark");
		billRefundDetailDto.setRemark1("Remark1");
		billRefundDetailDto.setRemark2("Remark2");
		billRefundDetailDto.setRemark3("Remark3");
		billRefundDetailDto.setRemark4("Remark4");

		billRefundDetailService.updateBillRefundDetail(billRefundDetailDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单退款详情信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBillRefundDetail() throws TsfaServiceException{
		BillRefundDetailDto findBillRefundDetail = new BillRefundDetailDto();
		findBillRefundDetail.setCode("111");
		billRefundDetailService.findBillRefundDetail(findBillRefundDetail);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单退款详情信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBillRefundDetailPage() throws TsfaServiceException{
		FindBillRefundDetailPage findBillRefundDetailPage = new FindBillRefundDetailPage();
		Page<BillRefundDetailDto> page = billRefundDetailService.findBillRefundDetailPage(findBillRefundDetailPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单退款详情信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBillRefundDetails() throws TsfaServiceException{
		FindBillRefundDetailPage findBillRefundDetailPage = new FindBillRefundDetailPage();
		List<BillRefundDetailDto> page = billRefundDetailService.findBillRefundDetails(findBillRefundDetailPage);
		Assert.assertNotNull(page);
		
	}
	
}
