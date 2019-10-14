package com.lj.business.cp.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市杨恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.cp.dto.coupon.AddCoupon;
import com.lj.business.cp.dto.coupon.AddCouponVo;
import com.lj.business.cp.dto.coupon.DelCoupon;
import com.lj.business.cp.dto.coupon.FindCoupon;
import com.lj.business.cp.dto.coupon.FindCouponPage;
import com.lj.business.cp.dto.coupon.FindCouponPageReturn;
import com.lj.business.cp.dto.coupon.FindCouponReturn;
import com.lj.business.cp.dto.coupon.UpdateCoupon;
import com.lj.business.cp.dto.couponRule.FindCouponRuleReturn;
import com.lj.business.cp.service.ICouponService;

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
public class CouponServiceImplTest extends SpringTestCase {

	@Resource
	ICouponService couponService;

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
	public void addCoupon() throws TsfaServiceException {
		AddCoupon addCoupon = new AddCoupon();
		// add数据录入
		addCoupon.setMerchantNo("MerchantNo");
		addCoupon.setMerchantName("MerchantName");
//		addCoupon.setShopNo("ShopNo");
//		addCoupon.setShopName("ShopName");
		addCoupon.setRuleNo("RuleNo");
		addCoupon.setCouponNo("CouponNo");
		addCoupon.setCouponStatus("CouponStatus");
		addCoupon.setUseDate(new Date());
		addCoupon.setUpdateId("UpdateId");
		addCoupon.setUpdateDate(new Date());
		addCoupon.setCreateId("CreateId");
		addCoupon.setCreateDate(new Date());
		couponService.addCoupon(addCoupon);

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
	public void updateCoupon() throws TsfaServiceException {
		UpdateCoupon updateCoupon = new UpdateCoupon();
		// update数据录入
		updateCoupon.setCode("Code");
		updateCoupon.setMerchantNo("MerchantNo");
		updateCoupon.setMerchantName("MerchantName");
//		updateCoupon.setShopNo("ShopNo");
//		updateCoupon.setShopName("ShopName");
		updateCoupon.setRuleNo("RuleNo");
		updateCoupon.setCouponNo("CouponNo");
		updateCoupon.setCouponStatus("CouponStatus");
		// updateCoupon.setUseDate("UseDate");
		updateCoupon.setUpdateId("UpdateId");
		// updateCoupon.setUpdateDate("UpdateDate");
		updateCoupon.setCreateId("CreateId");
		// updateCoupon.setCreateDate("CreateDate");

		couponService.updateCoupon(updateCoupon);

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
	public void findCoupon() throws TsfaServiceException {
		FindCoupon findCoupon = new FindCoupon();
//		findCoupon.setCode("111");
//		couponService.findCoupon(findCoupon);
		findCoupon.setMerchantNo("87b828cab9f64d37b2eacc4ce5cc0eab");
//		findCoupon.setShopNo("LJ_efb92e4e77864980b6bf040ee232aeff");
		findCoupon.setRuleNo("LJ_1bc04310981942378d340d022d0aff5d");
		Map<String, Object> findCouponReturn =  couponService.gainCoupon(findCoupon);
		System.out.println(findCouponReturn);
		
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
	public void findCouponPage() throws TsfaServiceException {
	/*	FindCouponPage findCouponPage = new FindCouponPage();
		Page<FindCouponPageReturn> page = couponService.findCouponPage(findCouponPage);
		Assert.assertNotNull(page);*/
		FindCoupon findCoupon = new FindCoupon();
		findCoupon.setRuleNo("LJ_02384e911faf466997e7d57d7907c193");
		List<FindCouponReturn> list = couponService.queryCouponReturnList(findCoupon);
		System.out.println(list.toString());
	}

	@Test
	public void findCouponReturnList() throws TsfaServiceException {
		FindCoupon findCoupon = new FindCoupon();
		findCoupon.setMerchantNo("MerchantNo");
		List<FindCouponPageReturn> list = couponService.findCouponReturnList(findCoupon);
		System.out.println(list.toString());

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
	public void delCoupon() throws TsfaServiceException {
		DelCoupon delCoupon = new DelCoupon();
		delCoupon.setCode("111");
		couponService.delCoupon(delCoupon);

	}

	/**
	 * 
	 *
	 * 方法说明：根据优惠券编号，修改优惠券状态
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 杨杰 CreateDate: 2017年9月18日
	 *
	 */
	@Test
	public void updateCouponByCouponNo() throws TsfaServiceException {
		UpdateCoupon updateCoupon = new UpdateCoupon();
		updateCoupon.setCouponNo("111");
		updateCoupon.setMemberNoGm("123");
		updateCoupon.setMemberNameGm("明哥");
		String retStr = couponService.updateCouponByCouponNo(updateCoupon);
		System.out.println(retStr);
	}
	
	/**
	 * 
	 *
	 * 方法说明：客户端新增优惠券
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 杨杰
	 * @date 2017年9月30日
	 *
	 */
	@Test
	public void insertCoupon() throws TsfaServiceException {
		AddCouponVo addCouponVo = new AddCouponVo();
		// 商户号
		addCouponVo.setMerchantNo("2169d1820e254e86b110dff73e1bd58f");
		// 商户名称
		addCouponVo.setMerchantName("九阳电器");
		// 分店号
//		addCouponVo.setShopNo("LJ_dbfd184dbe6e4233b03596177c465aaf");
//		// 分店名称
//		addCouponVo.setShopName("深圳九阳南山分店");
		// 导购编号
		addCouponVo.setMemberNoGm("da08e545a349485aa8faa42a34d43978");
		// 导购名称
		addCouponVo.setMemberNameGm("烽火");
		// 用户编号
		addCouponVo.setMemberNo("33928b67d8b840aa8c80efd64c352b44");
		// 用户名称
		addCouponVo.setMemberName("欧饭饭的饭饭");
		// 规则编号
		addCouponVo.setRuleNo("LJ_fa5be696dd2c44da97962a329cbc3e63");
		// 微信号
		addCouponVo.setNoWx("jie13691215445");
		Map<String, Object> resultMap = couponService.insertCoupon(addCouponVo);
		System.out.println(resultMap);
	}
	
	
	
	
	
	
	@Test
	public void findCouponByRuleNo(){
		FindCoupon coupon =new FindCoupon();
//		coupon.setMemberNoGm("0c3ff17122f243ceaac2a69913980a2a");
//		coupon.setMemberNo("99dcdd8cf5f24417b3841053b1f4be42");
		coupon.setAddFriendsCode("LJ_0114a51f324d4a3790ff9d3090ce604b");
		coupon.setMerchantNo("87b828cab9f64d37b2eacc4ce5cc0eab");
		coupon.setCouponNo("");
//		coupon.setRuleNo("LJ_3b6e3b41fc6142eb9d411104f280bc57");
//		coupon.setShopNo(""); 
		coupon.setNoWxGm("");
		coupon.setInvitationNo("");
		coupon.setShareStatus("MEMBER");
		coupon.setCouponCode("");
		FindCouponRuleReturn couponRuleReturn = (FindCouponRuleReturn) couponService.gainCoupon(coupon);
		System.out.println(couponRuleReturn);
		
	}
	

}
