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

import com.alibaba.fastjson.JSON;
import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.base.mvc.web.test.SpringTestCase;

import java.util.Date;
import java.util.List;

import com.ye.business.hx.dto.BillDto;
import com.ye.business.hx.dto.BillPaymentDto;
import com.ye.business.hx.dto.FindBillPaymentPage;
import com.ye.business.hx.service.IBillPaymentService;

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
public class BillPaymentServiceImplTest extends SpringTestCase{

	@Resource
	IBillPaymentService billPaymentService;



	/**
	 * 
	 *
	 * 方法说明：添加账单支付信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addBillPayment() throws TsfaServiceException{
		BillPaymentDto billPaymentDto = new BillPaymentDto();
		//add数据录入
		billPaymentDto.setCode("Code");
		billPaymentDto.setPatientNo("PatientNo");
		billPaymentDto.setPatientName("PatientName");
		billPaymentDto.setMedicalNo("MedicalNo");
		billPaymentDto.setBillCode("BillCode");
		billPaymentDto.setOperateCode("OperateCode");
		billPaymentDto.setShopNo("ShopNo");
		billPaymentDto.setShopName("ShopName");
		billPaymentDto.setMerchantNo("MerchantNo");
		billPaymentDto.setMerchantName("MerchantName");
		billPaymentDto.setPayType("PayType");
		billPaymentDto.setPayTypeName("PayTypeName");
		billPaymentDto.setPayAmount(10000L);
		billPaymentDto.setPayTime(new Date());
		billPaymentDto.setRecieverNo("RecieverNo");
		billPaymentDto.setRecieverName("RecieverName");
		billPaymentDto.setBizType("BizType");
		billPaymentDto.setStatus("Status");
		billPaymentDto.setReceivableAmt(10000L);
		billPaymentDto.setDebtAmt(0L);
		billPaymentDto.setPayMode("PayMode");
		billPaymentDto.setUpdateId("UpdateId");
		billPaymentDto.setUpdateDate(new Date());
		billPaymentDto.setCreateId("CreateId");
		billPaymentDto.setCreateDate(new Date());
		billPaymentDto.setRemark("Remark");
		billPaymentDto.setRemark1("Remark1");
		billPaymentDto.setRemark2("Remark2");
		billPaymentDto.setRemark3("Remark3");
		billPaymentDto.setRemark4("Remark4");
		
		billPaymentService.addBillPayment(billPaymentDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改账单支付信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateBillPayment() throws TsfaServiceException{
		BillPaymentDto billPaymentDto = new BillPaymentDto();
		//update数据录入
		billPaymentDto.setCode("Code");
		billPaymentDto.setPatientNo("PatientNo");
		billPaymentDto.setPatientName("PatientName");
		billPaymentDto.setMedicalNo("MedicalNo");
		billPaymentDto.setBillCode("BillCode");
		billPaymentDto.setOperateCode("OperateCode");
		billPaymentDto.setShopNo("ShopNo");
		billPaymentDto.setShopName("ShopName");
		billPaymentDto.setMerchantNo("MerchantNo");
		billPaymentDto.setMerchantName("MerchantName");
		billPaymentDto.setPayType("PayType");
		billPaymentDto.setPayTypeName("PayTypeName");
		billPaymentDto.setPayAmount(10000L);
		billPaymentDto.setPayTime(new Date());
		billPaymentDto.setRecieverNo("RecieverNo");
		billPaymentDto.setRecieverName("RecieverName");
		billPaymentDto.setBizType("BizType");
		billPaymentDto.setStatus("Status");
		billPaymentDto.setReceivableAmt(10000L);
		billPaymentDto.setDebtAmt(0L);
		billPaymentDto.setPayMode("PayMode");
		billPaymentDto.setUpdateId("UpdateId");
		billPaymentDto.setUpdateDate(new Date());
		billPaymentDto.setCreateId("CreateId");
		billPaymentDto.setCreateDate(new Date());
		billPaymentDto.setRemark("Remark");
		billPaymentDto.setRemark1("Remark1");
		billPaymentDto.setRemark2("Remark2");
		billPaymentDto.setRemark3("Remark3");
		billPaymentDto.setRemark4("Remark4");

		billPaymentService.updateBillPayment(billPaymentDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单支付信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBillPayment() throws TsfaServiceException{
		BillPaymentDto findBillPayment = new BillPaymentDto();
		findBillPayment.setCode("111");
		billPaymentService.findBillPayment(findBillPayment);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单支付信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBillPaymentPage() throws TsfaServiceException{
		FindBillPaymentPage findBillPaymentPage = new FindBillPaymentPage();
		Page<BillPaymentDto> page = billPaymentService.findBillPaymentPage(findBillPaymentPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单支付信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBillPayments() throws TsfaServiceException{
		FindBillPaymentPage findBillPaymentPage = new FindBillPaymentPage();
		List<BillPaymentDto> page = billPaymentService.findBillPayments(findBillPaymentPage);
		Assert.assertNotNull(page);
		
	}
	/**
	 * 
	 *
	 * 方法说明：查找账单支付信息信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void paymentSum() throws TsfaServiceException{
		FindBillPaymentPage findBillPaymentPage = new FindBillPaymentPage();
		BillPaymentDto param=new BillPaymentDto();
		param.setMerchantNo("73a1b1b90c5a4e9fb323a1a1beb5616a");
		findBillPaymentPage.setParam(param);
		
		BillDto obj = billPaymentService.paymentSum(findBillPaymentPage);
		System.out.println(JSON.toJSON(obj));
		
	}
	
}
