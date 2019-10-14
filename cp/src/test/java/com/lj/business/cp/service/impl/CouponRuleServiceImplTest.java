package com.lj.business.cp.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.cp.dto.couponRule.AddCouponRule;
import com.lj.business.cp.dto.couponRule.DelCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRule;
import com.lj.business.cp.dto.couponRule.FindCouponRuleReturn;
import com.lj.business.cp.dto.couponRule.UpdateCouponRule;
import com.lj.business.cp.service.ICouponRuleService;

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
public class CouponRuleServiceImplTest extends SpringTestCase {

	@Resource
	ICouponRuleService couponRuleService;

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
	public void addCouponRule() throws TsfaServiceException {
		AddCouponRule addCouponRule = new AddCouponRule();
		// add数据录入
		addCouponRule.setCode("Code");
		addCouponRule.setMerchantNo("MerchantNo");
		addCouponRule.setMerchantName("MerchantName");
//		addCouponRule.setShopNo("ShopNo");
//		addCouponRule.setShopName("ShopName");
		// addCouponRule.setCouponNum("CouponNum");
		// addCouponRule.setCouponNotes("CouponNotes");
		// addCouponRule.setBeginDate("BeginDate");
		// addCouponRule.setEndDate("EndDate");
		addCouponRule.setCouponName("CouponName");
		// addCouponRule.setCouponMax("CouponMax");
		addCouponRule.setCouponTypeCode("CouponTypeCode");
		addCouponRule.setCouponType("CouponType");
		addCouponRule.setCouponRemark("CouponRemark");
		addCouponRule.setToCoupon("ToCoupon");
		addCouponRule.setUseScope("UseScope");
		addCouponRule.setRealName("YES");
		addCouponRule.setRuleStatus("YES");
		addCouponRule.setUpdateId("UpdateId");
		// addCouponRule.setUpdateDate("UpdateDate");
		addCouponRule.setCreateId("CreateId");
		// addCouponRule.setCreateDate("CreateDate");
		addCouponRule.setCouponAvgNum(0);
		couponRuleService.addCouponRule(addCouponRule);

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
	public void updateCouponRule() throws TsfaServiceException {
		UpdateCouponRule updateCouponRule = new UpdateCouponRule();
		// update数据录入
		updateCouponRule.setCode("Code");
		updateCouponRule.setMerchantNo("MerchantNo");
		updateCouponRule.setMerchantName("MerchantName");
//		updateCouponRule.setShopNo("ShopNo");
//		updateCouponRule.setShopName("ShopName");
		// updateCouponRule.setCouponNum("CouponNum");
		// updateCouponRule.setCouponNotes("CouponNotes");
		// updateCouponRule.setBeginDate("BeginDate");
		// updateCouponRule.setEndDate("EndDate");
		updateCouponRule.setCouponName("CouponName");
		// updateCouponRule.setCouponMax("CouponMax");
		updateCouponRule.setCouponTypeCode("CouponTypeCode");
		updateCouponRule.setCouponType("CouponType");
		updateCouponRule.setCouponRemark("CouponRemark");
		updateCouponRule.setToCoupon("ToCoupon");
		updateCouponRule.setUseScope("UseScope");
		updateCouponRule.setRealName("RealName");
		updateCouponRule.setRuleStatus("RuleStatus");
		updateCouponRule.setUpdateId("UpdateId");
		// updateCouponRule.setUpdateDate("UpdateDate");
		updateCouponRule.setCreateId("CreateId");
		// updateCouponRule.setCreateDate("CreateDate");

		couponRuleService.updateCouponRule(updateCouponRule);

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
	public void findCouponRulePage() throws TsfaServiceException {
	/*	FindCouponRulePage findCouponRulePage = new FindCouponRulePage();
		Page<FindCouponRulePageReturn> page = couponRuleService.findCouponRulePage(findCouponRulePage);
		Assert.assertNotNull(page);*/
		System.out.println(couponRuleService.findcouponRuleTodayEnableDate());
		List<FindCouponRuleReturn>  list = couponRuleService.queryCouponRulePast();
		System.out.println(list.size());
		

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
	public void delCouponRule() throws TsfaServiceException {
		DelCouponRule delCouponRule = new DelCouponRule();
		delCouponRule.setCode("111");
		couponRuleService.delCouponRule(delCouponRule);

	}

	/**
	 * 
	 *
	 * 方法说明：获取优惠券规则
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 杨杰 CreateDate: 2017年9月18日
	 *
	 */
	@Test
	public void findCouponRuleList() throws TsfaServiceException {
		FindCouponRule findCouponRule = new FindCouponRule();
		findCouponRule.setCouponType("优惠券");
		findCouponRule.setToCoupon("NONE");
		findCouponRule.setRealName("NO");
		findCouponRule.setRuleStatus("YES");
		findCouponRule.setMerchantNo("2169d1820e254e86b110dff73e1bd58f");
//		findCouponRule.setShopNo("LJ_dbfd184dbe6e4233b03596177c465aaf");
		couponRuleService.findCouponRuleList(findCouponRule);
	}

	/**
	 * 
	 *
	 * 方法说明：根据code获取优惠券规则
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 杨杰 CreateDate: 2017年9月18日
	 *
	 */
	@Test
	public void findCouponRule() throws TsfaServiceException {
		FindCouponRule findCouponRule = new FindCouponRule();
		findCouponRule.setCode("Code");
		couponRuleService.findCouponRule(findCouponRule);
	}
}
