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
import com.lj.business.cp.dto.couponRuleEx.AddCouponRuleEx;
import com.lj.business.cp.dto.couponRuleEx.DelCouponRuleEx;
import com.lj.business.cp.dto.couponRuleEx.FindCouponRuleEx;
import com.lj.business.cp.dto.couponRuleEx.FindCouponRuleExPage;
import com.lj.business.cp.dto.couponRuleEx.FindCouponRuleExPageReturn;
import com.lj.business.cp.dto.couponRuleEx.UpdateCouponRuleEx;
import com.lj.business.cp.service.ICouponRuleExService;


/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 彭阳
 * 
 * 
 * CreateDate: 2017-06-14
 */
public class CouponRuleExServiceImplTest extends SpringTestCase{

	@Resource
	ICouponRuleExService couponRuleExService;



	/**
	 * 
	 *
	 * 方法说明：添加优惠券规则业务扩展表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void addCouponRuleEx() throws TsfaServiceException{
		AddCouponRuleEx addCouponRuleEx = new AddCouponRuleEx();
		//add数据录入
		addCouponRuleEx.setCode("Code");
		addCouponRuleEx.setRuleCode("RuleCode");
		addCouponRuleEx.setUseNum(10);
		addCouponRuleEx.setSurplusNum(10);
		addCouponRuleEx.setPv(10);
		addCouponRuleEx.setUv(10);
		addCouponRuleEx.setCv(10);
		addCouponRuleEx.setCreateId("CreateId");
		addCouponRuleEx.setCreateDate(new Date());
		
		Assert.assertNotNull(couponRuleExService.addCouponRuleEx(addCouponRuleEx ));
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改优惠券规则业务扩展表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void updateCouponRuleEx() throws TsfaServiceException{
		UpdateCouponRuleEx updateCouponRuleEx = new UpdateCouponRuleEx();
//		//update数据录入
//		updateCouponRuleEx.setCode("Code");
//		updateCouponRuleEx.setRuleCode("RuleCode");
//		updateCouponRuleEx.setUseNum(10);
//		updateCouponRuleEx.setSurplusNum(10);
//		updateCouponRuleEx.setPv(10);
//		updateCouponRuleEx.setUv(10);
//		updateCouponRuleEx.setCv(10);
//		updateCouponRuleEx.setCreateId("CreateId");
//		updateCouponRuleEx.setCreateDate(new Date());
//
//		couponRuleExService.updateCouponRuleEx(updateCouponRuleEx );
		updateCouponRuleEx.setRuleCode("LJ_30b87ceb12b9423c8b5acdde14c41946");
		couponRuleExService.updateCouponCv(updateCouponRuleEx);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找优惠券规则业务扩展表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findCouponRuleEx() throws TsfaServiceException{
/*		FindCouponRuleEx findCouponRuleEx = new FindCouponRuleEx();
		findCouponRuleEx.setCode("");
		couponRuleExService.findCouponRuleEx(findCouponRuleEx);*/
		UpdateCouponRuleEx updateCouponRuleEx = new UpdateCouponRuleEx();
		updateCouponRuleEx.setRuleCode("LJ_30b87ceb12b9423c8b5acdde14c41946");
		couponRuleExService.updateCouponUseNum(updateCouponRuleEx);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找优惠券规则业务扩展表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findCouponRuleExPage() throws TsfaServiceException{
		FindCouponRuleExPage findCouponRuleExPage = new FindCouponRuleExPage();
		Page<FindCouponRuleExPageReturn> page = couponRuleExService.findCouponRuleExPage(findCouponRuleExPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除优惠券规则业务扩展表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void delCouponRuleEx() throws TsfaServiceException{
		DelCouponRuleEx delCouponRuleEx = new DelCouponRuleEx();
		delCouponRuleEx.setCode("");
		Assert.assertNotNull(couponRuleExService.delCouponRuleEx(delCouponRuleEx));
	}
}
