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

import com.ye.business.hx.dto.BillDetailDto;
import com.ye.business.hx.dto.FindBillDetailPage;
import com.ye.business.hx.service.IBillDetailService;

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
public class BillDetailServiceImplTest extends SpringTestCase{

	@Resource
	IBillDetailService billDetailService;



	/**
	 * 
	 *
	 * 方法说明：添加账单详情信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addBillDetail() throws TsfaServiceException{
		BillDetailDto billDetailDto = new BillDetailDto();
		//add数据录入
		billDetailDto.setCode("Code");
		billDetailDto.setBillCode("BillCode");
		billDetailDto.setShopNo("ShopNo");
		billDetailDto.setShopName("ShopName");
		billDetailDto.setMerchantNo("MerchantNo");
		billDetailDto.setMerchantName("MerchantName");
		billDetailDto.setProjectCode("ProjectCode");
		billDetailDto.setProjectName("ProjectName");
		billDetailDto.setProjectUnit("ProjectUnit");
		billDetailDto.setUnitPrice(100L);
		billDetailDto.setItemDisUnitPrice(10000L);
		billDetailDto.setItemNum(1);
		billDetailDto.setOriginalAmount(100L);
		billDetailDto.setItemDiscountAmount(100L);
		billDetailDto.setDiscountItem(10000);
		billDetailDto.setRtNum(0);
		billDetailDto.setRtAmount(0L);
		billDetailDto.setDiscountOrderStatus("N");
		billDetailDto.setAdvisoryNo("AdvisoryNo");
		billDetailDto.setAdvisoryName("AdvisoryName");
		billDetailDto.setDoctorNo("DoctorNo");
		billDetailDto.setDoctorName("DoctorName");
		billDetailDto.setAssistantNo("AssistantNo");
		billDetailDto.setAssistantName("AssistantName");
		billDetailDto.setUpdateId("UpdateId");
		billDetailDto.setUpdateDate(new Date());
		billDetailDto.setCreateId("CreateId");
		billDetailDto.setCreateDate(new Date());
		billDetailDto.setRemark("Remark");
		billDetailDto.setRemark1("Remark1");
		billDetailDto.setRemark2("Remark2");
		billDetailDto.setRemark3("Remark3");
		billDetailDto.setRemark4("Remark4");
		
		billDetailService.addBillDetail(billDetailDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改账单详情信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateBillDetail() throws TsfaServiceException{
		BillDetailDto billDetailDto = new BillDetailDto();
		//update数据录入
		billDetailDto.setCode("Code");
		billDetailDto.setBillCode("BillCode");
		billDetailDto.setShopNo("ShopNo");
		billDetailDto.setShopName("ShopName");
		billDetailDto.setMerchantNo("MerchantNo");
		billDetailDto.setMerchantName("MerchantName");
		billDetailDto.setProjectCode("ProjectCode");
		billDetailDto.setProjectName("ProjectName");
		billDetailDto.setProjectUnit("ProjectUnit");
		billDetailDto.setUnitPrice(100L);
		billDetailDto.setItemDisUnitPrice(10000L);
		billDetailDto.setItemNum(1);
		billDetailDto.setOriginalAmount(100L);
		billDetailDto.setItemDiscountAmount(100L);
		billDetailDto.setDiscountItem(10000);
		billDetailDto.setRtNum(0);
		billDetailDto.setRtAmount(0L);
		billDetailDto.setDiscountOrderStatus("DiscountOrderStatus");
		billDetailDto.setAdvisoryNo("AdvisoryNo");
		billDetailDto.setAdvisoryName("AdvisoryName");
		billDetailDto.setDoctorNo("DoctorNo");
		billDetailDto.setDoctorName("DoctorName");
		billDetailDto.setAssistantNo("AssistantNo");
		billDetailDto.setAssistantName("AssistantName");
		billDetailDto.setUpdateId("UpdateId");
		billDetailDto.setUpdateDate(new Date());
		billDetailDto.setCreateId("CreateId");
		billDetailDto.setCreateDate(new Date());
		billDetailDto.setRemark("Remark");
		billDetailDto.setRemark1("Remark1");
		billDetailDto.setRemark2("Remark2");
		billDetailDto.setRemark3("Remark3");
		billDetailDto.setRemark4("Remark4");

		billDetailService.updateBillDetail(billDetailDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单详情信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBillDetail() throws TsfaServiceException{
		BillDetailDto findBillDetail = new BillDetailDto();
		findBillDetail.setCode("111");
		billDetailService.findBillDetail(findBillDetail);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单详情信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBillDetailPage() throws TsfaServiceException{
		FindBillDetailPage findBillDetailPage = new FindBillDetailPage();
		Page<BillDetailDto> page = billDetailService.findBillDetailPage(findBillDetailPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单详情信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBillDetails() throws TsfaServiceException{
		FindBillDetailPage findBillDetailPage = new FindBillDetailPage();
		List<BillDetailDto> page = billDetailService.findBillDetails(findBillDetailPage);
		Assert.assertNotNull(page);
		
	}
	
}
