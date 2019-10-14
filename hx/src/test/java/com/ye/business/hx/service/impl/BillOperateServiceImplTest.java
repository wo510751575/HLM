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

import com.ye.business.hx.dto.BillOperateDto;
import com.ye.business.hx.dto.FindBillOperatePage;
import com.ye.business.hx.service.IBillOperateService;

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
public class BillOperateServiceImplTest extends SpringTestCase{

	@Resource
	IBillOperateService billOperateService;



	/**
	 * 
	 *
	 * 方法说明：添加账单交易操作信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addBillOperate() throws TsfaServiceException{
		BillOperateDto billOperateDto = new BillOperateDto();
		//add数据录入
		billOperateDto.setCode("Code");
		billOperateDto.setBillCode("BillCode");
		billOperateDto.setOperateType("OperateType");
		billOperateDto.setStatus("Status");
		billOperateDto.setProcess("Process");
		billOperateDto.setCheckStatus("CheckStatus");
		billOperateDto.setMemberNoGuid("MemberNoGuid");
		billOperateDto.setMemberNameGuid("MemberNameGuid");
		billOperateDto.setCheckerNoGuid("CheckerNoGuid");
		billOperateDto.setCheckerNameGuid("CheckerNameGuid");
		billOperateDto.setApplyTime(new Date());
		billOperateDto.setCheckTime(new Date());
		billOperateDto.setPayType("PayType");
		billOperateDto.setPayTypeName("PayTypeName");
		billOperateDto.setPayAmount(10000L);
		billOperateDto.setPayTime(new Date());
		billOperateDto.setRecieverNo("RecieverNo");
		billOperateDto.setRecieverName("RecieverName");
		billOperateDto.setUpdateId("UpdateId");
		billOperateDto.setUpdateDate(new Date());
		billOperateDto.setCreateId("CreateId");
		billOperateDto.setCreateDate(new Date());
		billOperateDto.setRemark("Remark");
		billOperateDto.setRemark1("Remark1");
		billOperateDto.setRemark2("Remark2");
		billOperateDto.setRemark3("Remark3");
		billOperateDto.setRemark4("Remark4");
		
		billOperateService.addBillOperate(billOperateDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改账单交易操作信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateBillOperate() throws TsfaServiceException{
		BillOperateDto billOperateDto = new BillOperateDto();
		//update数据录入
		billOperateDto.setCode("Code");
		billOperateDto.setBillCode("BillCode");
		billOperateDto.setOperateType("OperateType");
		billOperateDto.setStatus("Status");
		billOperateDto.setProcess("Process");
		billOperateDto.setCheckStatus("CheckStatus");
		billOperateDto.setMemberNoGuid("MemberNoGuid");
		billOperateDto.setMemberNameGuid("MemberNameGuid");
		billOperateDto.setCheckerNoGuid("CheckerNoGuid");
		billOperateDto.setCheckerNameGuid("CheckerNameGuid");
		billOperateDto.setApplyTime(new Date());
		billOperateDto.setCheckTime(new Date());
		billOperateDto.setPayType("PayType");
		billOperateDto.setPayTypeName("PayTypeName");
		billOperateDto.setPayAmount(10000L);
		billOperateDto.setPayTime(new Date());
		billOperateDto.setRecieverNo("RecieverNo");
		billOperateDto.setRecieverName("RecieverName");
		billOperateDto.setUpdateId("UpdateId");
		billOperateDto.setUpdateDate(new Date());
		billOperateDto.setCreateId("CreateId");
		billOperateDto.setCreateDate(new Date());
		billOperateDto.setRemark("Remark");
		billOperateDto.setRemark1("Remark1");
		billOperateDto.setRemark2("Remark2");
		billOperateDto.setRemark3("Remark3");
		billOperateDto.setRemark4("Remark4");

		billOperateService.updateBillOperate(billOperateDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单交易操作信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBillOperate() throws TsfaServiceException{
		BillOperateDto findBillOperate = new BillOperateDto();
		findBillOperate.setCode("111");
		billOperateService.findBillOperate(findBillOperate);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单交易操作信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBillOperatePage() throws TsfaServiceException{
		FindBillOperatePage findBillOperatePage = new FindBillOperatePage();
		Page<BillOperateDto> page = billOperateService.findBillOperatePage(findBillOperatePage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找账单交易操作信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findBillOperates() throws TsfaServiceException{
		FindBillOperatePage findBillOperatePage = new FindBillOperatePage();
		List<BillOperateDto> page = billOperateService.findBillOperates(findBillOperatePage);
		Assert.assertNotNull(page);
		
	}
	
}
