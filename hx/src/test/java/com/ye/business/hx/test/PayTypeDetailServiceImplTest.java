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

import java.util.List;

import com.ye.business.hx.dto.PayTypeDetailDto;
import com.ye.business.hx.dto.FindPayTypeDetailPage;
import com.ye.business.hx.service.IPayTypeDetailService;

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
public class PayTypeDetailServiceImplTest extends SpringTestCase{

	@Resource
	IPayTypeDetailService payTypeDetailService;



	/**
	 * 
	 *
	 * 方法说明：添加支付方式详细信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addPayTypeDetail() throws TsfaServiceException{
		PayTypeDetailDto payTypeDetailDto = new PayTypeDetailDto();
		//add数据录入
		payTypeDetailDto.setCode("Code");
		payTypeDetailDto.setBillCode("BillCode");
		payTypeDetailDto.setOperateCode("OperateCode");
		payTypeDetailDto.setPayType("PayType");
		payTypeDetailDto.setPayTypeName("PayTypeName");
		payTypeDetailDto.setPayAmount(0L);
		
		payTypeDetailService.addPayTypeDetail(payTypeDetailDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改支付方式详细信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updatePayTypeDetail() throws TsfaServiceException{
		PayTypeDetailDto payTypeDetailDto = new PayTypeDetailDto();
		//update数据录入
		payTypeDetailDto.setCode("Code");
		payTypeDetailDto.setBillCode("BillCode");
		payTypeDetailDto.setOperateCode("OperateCode");
		payTypeDetailDto.setPayType("PayType");
		payTypeDetailDto.setPayTypeName("PayTypeName");
		payTypeDetailDto.setPayAmount(0L);

		payTypeDetailService.updatePayTypeDetail(payTypeDetailDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找支付方式详细信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPayTypeDetail() throws TsfaServiceException{
		PayTypeDetailDto findPayTypeDetail = new PayTypeDetailDto();
		findPayTypeDetail.setCode("111");
		payTypeDetailService.findPayTypeDetail(findPayTypeDetail);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找支付方式详细信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPayTypeDetailPage() throws TsfaServiceException{
		FindPayTypeDetailPage findPayTypeDetailPage = new FindPayTypeDetailPage();
		Page<PayTypeDetailDto> page = payTypeDetailService.findPayTypeDetailPage(findPayTypeDetailPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找支付方式详细信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findPayTypeDetails() throws TsfaServiceException{
		FindPayTypeDetailPage findPayTypeDetailPage = new FindPayTypeDetailPage();
		List<PayTypeDetailDto> page = payTypeDetailService.findPayTypeDetails(findPayTypeDetailPage);
		Assert.assertNotNull(page);
		
	}
	
}
