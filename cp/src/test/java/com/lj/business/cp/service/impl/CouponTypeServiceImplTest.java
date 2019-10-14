package com.lj.business.cp.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.cp.dto.couponType.AddCouponType;
import com.lj.business.cp.dto.couponType.DelCouponType;
import com.lj.business.cp.dto.couponType.FindCouponType;
import com.lj.business.cp.dto.couponType.FindCouponTypePage;
import com.lj.business.cp.dto.couponType.FindCouponTypePageReturn;
import com.lj.business.cp.dto.couponType.UpdateCouponType;
import com.lj.business.cp.service.ICouponTypeService;

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
public class CouponTypeServiceImplTest extends SpringTestCase {

	@Resource
	ICouponTypeService couponTypeService;

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
	public void addCouponType() throws TsfaServiceException {
		AddCouponType addCouponType = new AddCouponType();
		// add数据录入
		addCouponType.setCode("Code");
		addCouponType.setCouponType("CouponType");
		addCouponType.setCouponRemark("CouponRemark");
		addCouponType.setMerchantNo("MerchantNo");
		addCouponType.setMerchantName("MerchantName");
		addCouponType.setUseEnable("UseEnable");
		addCouponType.setUpdateId("UpdateId");
		// addCouponType.setUpdateDate("UpdateDate");
		addCouponType.setCreateId("CreateId");
		addCouponType.setCreateDate(new Date());

		couponTypeService.addCouponType(addCouponType);

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
	public void updateCouponType() throws TsfaServiceException {
		UpdateCouponType updateCouponType = new UpdateCouponType();
		// update数据录入
		updateCouponType.setCode("Code");
		updateCouponType.setCouponType("CouponType");
		updateCouponType.setCouponRemark("CouponRemark");
		updateCouponType.setMerchantNo("MerchantNo");
		updateCouponType.setMerchantName("MerchantName");
		updateCouponType.setUseEnable("UseEnable");
		updateCouponType.setUpdateId("UpdateId");
		// updateCouponType.setUpdateDate("UpdateDate");
		updateCouponType.setCreateId("CreateId");
		// updateCouponType.setCreateDate("CreateDate");

		couponTypeService.updateCouponType(updateCouponType);

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
	public void findCouponType() throws TsfaServiceException {
		FindCouponType findCouponType = new FindCouponType();
		findCouponType.setCode("111");
		couponTypeService.findCouponType(findCouponType);
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
	public void findCouponTypePage() throws TsfaServiceException {
		FindCouponTypePage findCouponTypePage = new FindCouponTypePage();
		Page<FindCouponTypePageReturn> page = couponTypeService.findCouponTypePage(findCouponTypePage);
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
	public void delCouponType() throws TsfaServiceException {
		DelCouponType delCouponType = new DelCouponType();
		delCouponType.setCode("111");
		couponTypeService.delCouponType(delCouponType);

	}

	/**
	 * 
	 *
	 * 方法说明：获取优惠券类型
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 杨杰 CreateDate: 2017年9月18日
	 *
	 */
	@Test
	public void findCouponTypeList() throws TsfaServiceException {
		FindCouponType findCouponType = new FindCouponType();
		findCouponType.setUseEnable("YES");
		couponTypeService.findCouponTypeList(findCouponType);
	}
}
