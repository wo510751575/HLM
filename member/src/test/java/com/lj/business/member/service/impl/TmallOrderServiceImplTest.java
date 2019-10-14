package com.lj.business.member.service.impl;

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
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.base.json.JsonUtils;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.FindTmallOrderPage;
import com.lj.business.member.dto.TmallOrderDto;
import com.lj.business.member.service.ITmallOrderService;

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
public class TmallOrderServiceImplTest extends SpringTestCase{

	@Resource
	ITmallOrderService tmallOrderService;



	/**
	 * 
	 *
	 * 方法说明：添加天猫订单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void addTmallOrder() throws TsfaServiceException{
		TmallOrderDto tmallOrderDto = new TmallOrderDto();
		//add数据录入
		tmallOrderDto.setCode("Code");
		tmallOrderDto.setMerchantNo("MerchantNo");
		tmallOrderDto.setMemberName("MemberName");
		tmallOrderDto.setMobile("Mobile");
		tmallOrderDto.setNoWw("NoWw");
		tmallOrderDto.setOrderNo("OrderNo");
		tmallOrderDto.setProductName("ProductName");
		tmallOrderDto.setProductUrl("ProductUrl");
		tmallOrderDto.setAmount(1000L);
		tmallOrderDto.setOrderDate("2019.02.19");
		tmallOrderDto.setCommentLevel("1");
		tmallOrderDto.setCommentCtx("CommentCtx");
		tmallOrderDto.setCreateDate(new Date());
		tmallOrderDto.setCreateId("CreateId");
		tmallOrderDto.setRemark("Remark");
		tmallOrderDto.setRemark2("Remark2");
		tmallOrderDto.setRemark3("Remark3");
		tmallOrderDto.setRemark4("Remark4");
		
		tmallOrderService.addTmallOrder(tmallOrderDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改天猫订单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void updateTmallOrder() throws TsfaServiceException{
		TmallOrderDto tmallOrderDto = new TmallOrderDto();
		//update数据录入
		tmallOrderDto.setCode("Code");
		tmallOrderDto.setMerchantNo("MerchantNo");
		tmallOrderDto.setMemberName("MemberName");
		tmallOrderDto.setMobile("Mobile");
		tmallOrderDto.setNoWw("NoWw");
		tmallOrderDto.setOrderNo("OrderNo");
		tmallOrderDto.setProductName("ProductName");
		tmallOrderDto.setProductUrl("ProductUrl");
		tmallOrderDto.setAmount(1000L);
		tmallOrderDto.setOrderDate("2019.02.19");
		tmallOrderDto.setCommentLevel("1");
		tmallOrderDto.setCommentCtx("CommentCtx");
		tmallOrderDto.setCreateDate(new Date());
		tmallOrderDto.setCreateId("CreateId");
		tmallOrderDto.setRemark("Remark");
		tmallOrderDto.setRemark2("Remark2");
		tmallOrderDto.setRemark3("Remark3");
		tmallOrderDto.setRemark4("Remark4");

		tmallOrderService.updateTmallOrder(tmallOrderDto);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找天猫订单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findTmallOrder() throws TsfaServiceException{
		TmallOrderDto findTmallOrder = new TmallOrderDto();
		findTmallOrder.setCode("111");
		tmallOrderService.findTmallOrder(findTmallOrder);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找天猫订单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findTmallOrderPage() throws TsfaServiceException{
		FindTmallOrderPage findTmallOrderPage = new FindTmallOrderPage();
		Page<TmallOrderDto> page = tmallOrderService.findTmallOrderPage(findTmallOrderPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找天猫订单信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author lhy CreateDate: 2019.02.19
	 *
	 */
	@Test
	public void findTmallOrders() throws TsfaServiceException{
		FindTmallOrderPage findTmallOrderPage = new FindTmallOrderPage();
		List<TmallOrderDto> page = tmallOrderService.findTmallOrders(findTmallOrderPage);
		Assert.assertNotNull(page);
		System.out.println(JsonUtils.jsonFromList(page));
	}
	
}
