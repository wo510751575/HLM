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

import com.ye.business.hx.dto.BillRefundDto;
import com.ye.business.hx.dto.FindBillRefundPage;
import com.ye.business.hx.service.IBillRefundService;

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
public class BillRefundServiceImplTest extends SpringTestCase{

	@Resource
	IBillRefundService billRefundService;



	/**
	 * 
	 *
	 * 方法说明：添加账单退款信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addBillRefund() throws TsfaServiceException{
		BillRefundDto billRefundDto = new BillRefundDto();
		//add数据录入
		billRefundDto.setCode("Code");
		billRefundDto.setBillCode("BillCode");
		billRefundDto.setOperateCode("OperateCode");
		billRefundDto.setShopNo("ShopNo");
		billRefundDto.setShopName("ShopName");
		billRefundDto.setMerchantNo("MerchantNo");
		billRefundDto.setMerchantName("MerchantName");
		billRefundDto.setPatientNo("PatientNo");
		billRefundDto.setPatientName("PatientName");
		billRefundDto.setMedicalNo("MedicalNo");
		billRefundDto.setPayType("PayType");
		billRefundDto.setPayTypeName("PayTypeName");
		billRefundDto.setRtAmount(10000L);
		billRefundDto.setRefundGdNo("RefundGdNo");
		billRefundDto.setRefundGdName("RefundGdName");
		billRefundDto.setRefundTime(new Date());
		billRefundDto.setRefundStatus("RefundStatus");
		billRefundDto.setStatus("Status");
		billRefundDto.setRefundType("ALL");
		billRefundDto.setUpdateId("UpdateId");
		billRefundDto.setUpdateDate(new Date());
		billRefundDto.setCreateId("CreateId");
		billRefundDto.setCreateDate(new Date());
		billRefundDto.setRemark("Remark");
		billRefundDto.setRemark1("Remark1");
		billRefundDto.setRemark2("Remark2");
		billRefundDto.setRemark3("Remark3");
		billRefundDto.setRemark4("Remark4");
		
		billRefundService.addBillRefund(billRefundDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改账单退款信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateBillRefund() throws TsfaServiceException{
		BillRefundDto billRefundDto = new BillRefundDto();
		//update数据录入
		billRefundDto.setCode("Code");
		billRefundDto.setBillCode("BillCode");
		billRefundDto.setOperateCode("OperateCode");
		billRefundDto.setShopNo("ShopNo");
		billRefundDto.setShopName("ShopName");
		billRefundDto.setMerchantNo("MerchantNo");
		billRefundDto.setMerchantName("MerchantName");
		billRefundDto.setPatientNo("PatientNo");
		billRefundDto.setPatientName("PatientName");
		billRefundDto.setMedicalNo("MedicalNo");
		billRefundDto.setPayType("PayType");
		billRefundDto.setPayTypeName("PayTypeName");
		billRefundDto.setRtAmount(10000L);
		billRefundDto.setRefundGdNo("RefundGdNo");
		billRefundDto.setRefundGdName("RefundGdName");
		billRefundDto.setRefundStatus("RefundStatus");
		billRefundDto.setStatus("Status");
		billRefundDto.setRefundType("RefundType");
		billRefundDto.setUpdateId("UpdateId");
		billRefundDto.setUpdateDate(new Date());
		billRefundDto.setCreateId("CreateId");
		billRefundDto.setCreateDate(new Date());
		billRefundDto.setRemark("Remark");
		billRefundDto.setRemark1("Remark1");
		billRefundDto.setRemark2("Remark2");
		billRefundDto.setRemark3("Remark3");
		billRefundDto.setRemark4("Remark4");

		billRefundService.updateBillRefund(billRefundDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单退款信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBillRefund() throws TsfaServiceException{
		BillRefundDto findBillRefund = new BillRefundDto();
		findBillRefund.setCode("111");
		billRefundService.findBillRefund(findBillRefund);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单退款信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBillRefundPage() throws TsfaServiceException{
		FindBillRefundPage findBillRefundPage = new FindBillRefundPage();
		Page<BillRefundDto> page = billRefundService.findBillRefundPage(findBillRefundPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单退款信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBillRefunds() throws TsfaServiceException{
		FindBillRefundPage findBillRefundPage = new FindBillRefundPage();
		List<BillRefundDto> page = billRefundService.findBillRefunds(findBillRefundPage);
		Assert.assertNotNull(page);
		
	}
	
}
