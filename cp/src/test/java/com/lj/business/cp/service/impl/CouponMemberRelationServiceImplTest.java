package com.lj.business.cp.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.cp.dto.couponMemberRelation.AddCouponMemberRelation;
import com.lj.business.cp.dto.couponMemberRelation.DelCouponMemberRelation;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelation;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelationPage;
import com.lj.business.cp.dto.couponMemberRelation.FindCouponMemberRelationPageReturn;
import com.lj.business.cp.dto.couponMemberRelation.UpdateCouponMemberRelation;
import com.lj.business.cp.service.ICouponMemberRelationService;

/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 杨杰
 * 
 * 
 *         CreateDate: 2017-06-14
 */
public class CouponMemberRelationServiceImplTest extends SpringTestCase {

	@Resource
	ICouponMemberRelationService couponMemberRelationService;

	/**
	 * 
	 *
	 * 方法说明：添加导购行为信息记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addCouponMemberRelation() throws TsfaServiceException {
		AddCouponMemberRelation addCouponMemberRelation = new AddCouponMemberRelation();
		// add数据录入
		addCouponMemberRelation.setCode("Code");
		addCouponMemberRelation.setMemberNoGm("MemberNoGm");
		addCouponMemberRelation.setMemberNameGm("MemberNameGm");
		addCouponMemberRelation.setMemberNo("MemberNo");
		addCouponMemberRelation.setMemberName("MemberName");
		addCouponMemberRelation.setCouponNo("CouponNo");
		addCouponMemberRelation.setCouponStatus("CouponStatus");
		addCouponMemberRelation.setUpdateId("UpdateId");
		// addCouponMemberRelation.setUpdateDate("UpdateDate");
		addCouponMemberRelation.setCreateId("CreateId");
		// addCouponMemberRelation.setCreateDate("CreateDate");

		couponMemberRelationService.addCouponMemberRelation(addCouponMemberRelation);

	}

	/**
	 * 
	 *
	 * 方法说明：修改导购行为信息记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void updateCouponMemberRelation() throws TsfaServiceException {
		UpdateCouponMemberRelation updateCouponMemberRelation = new UpdateCouponMemberRelation();
		// update数据录入
		updateCouponMemberRelation.setCode("Code");
		updateCouponMemberRelation.setMemberNoGm("MemberNoGm");
		updateCouponMemberRelation.setMemberNameGm("MemberNameGm");
		updateCouponMemberRelation.setMemberNo("MemberNo");
		updateCouponMemberRelation.setMemberName("MemberName");
		updateCouponMemberRelation.setCouponNo("CouponNo");
		updateCouponMemberRelation.setCouponStatus("CouponStatus");
		updateCouponMemberRelation.setUpdateId("UpdateId");
		// updateCouponMemberRelation.setUpdateDate("UpdateDate");
		updateCouponMemberRelation.setCreateId("CreateId");
		// updateCouponMemberRelation.setCreateDate("CreateDate");

		couponMemberRelationService.updateCouponMemberRelation(updateCouponMemberRelation);

	}

	/**
	 * 
	 *
	 * 方法说明：查找导购行为信息记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findCouponMemberRelationPage() throws TsfaServiceException {
		FindCouponMemberRelationPage findCouponMemberRelationPage = new FindCouponMemberRelationPage();
		Page<FindCouponMemberRelationPageReturn> page = couponMemberRelationService.findCouponMemberRelationPage(findCouponMemberRelationPage);
		Assert.assertNotNull(page);

	}

	/**
	 * 
	 *
	 * 方法说明：删除导购行为信息记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void delCouponMemberRelation() throws TsfaServiceException {
		DelCouponMemberRelation delCouponMemberRelation = new DelCouponMemberRelation();
		delCouponMemberRelation.setCode("111");
		couponMemberRelationService.delCouponMemberRelation(delCouponMemberRelation);

	}
	
	@Test
	public void findCouponMemberRelationList() throws TsfaServiceException {
		FindCouponMemberRelation findCouponMemberRelation = new FindCouponMemberRelation();
		findCouponMemberRelation.setMemberNoGm("123");
		couponMemberRelationService.findCouponMemberRelationList(findCouponMemberRelation);

	}
	
	@Test
	public void findCouponMemberRelation() throws TsfaServiceException {
		FindCouponMemberRelation findCouponMemberRelation = new FindCouponMemberRelation();
		findCouponMemberRelation.setMemberNoGm("123");
		findCouponMemberRelation.setMemberNo("123");
		findCouponMemberRelation.setCouponNo("123");
		couponMemberRelationService.findCouponMemberRelation(findCouponMemberRelation);
	}
}
