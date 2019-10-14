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
import com.lj.business.cp.couponRecords.AddCouponRecords;
import com.lj.business.cp.couponRecords.DelCouponRecords;
import com.lj.business.cp.couponRecords.FindCouponRecords;
import com.lj.business.cp.couponRecords.FindCouponRecordsPage;
import com.lj.business.cp.couponRecords.FindCouponRecordsPageReturn;
import com.lj.business.cp.couponRecords.UpdateCouponRecords;
import com.lj.business.cp.service.ICouponRecordsService;


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
public class CouponRecordsServiceImplTest extends SpringTestCase{

	@Resource
	ICouponRecordsService couponRecordsService;



	/**
	 * 
	 *
	 * 方法说明：添加IM含敏感词聊天记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void addCouponRecords() throws TsfaServiceException{
		AddCouponRecords addCouponRecords = new AddCouponRecords();
		//add数据录入
		addCouponRecords.setCode("Code");
		addCouponRecords.setMerchantNo("MerchantNo");
//		addCouponRecords.setShopNo("ShopNo");
//		addCouponRecords.setShopName("ShopName");
		addCouponRecords.setMemberNo("MemberNo");
		addCouponRecords.setMemberName("MemberName");
		addCouponRecords.setCouponNo("CouponNo");
		addCouponRecords.setCouponName("CouponName");
		addCouponRecords.setUseDate(new Date());
		addCouponRecords.setCreateId("CreateId");
		addCouponRecords.setCreateDate(new Date());
		
		Assert.assertNotNull(couponRecordsService.addCouponRecords(addCouponRecords ));
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改IM含敏感词聊天记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void updateCouponRecords() throws TsfaServiceException{
		UpdateCouponRecords updateCouponRecords = new UpdateCouponRecords();
		//update数据录入
		updateCouponRecords.setCode("LJ_86d8163a448d4eff8f1886dd57c414a1");
		updateCouponRecords.setMerchantNo("87b828cab9f64d37b2eacc4ce5cc0eab");
//		updateCouponRecords.setShopNo("LJ_efb92e4e77864980b6bf040ee232aeff");
//		updateCouponRecords.setShopName("中控北京分店");
		updateCouponRecords.setMemberNo("8e614ef9cfaf451197ca47952548d33c");
		updateCouponRecords.setMemberName("我就是我");
		updateCouponRecords.setCouponNo("LJ_d66a049826e749128e627fee4b7c140b");
		updateCouponRecords.setCouponName("满8888减888");
		updateCouponRecords.setUseDate(new Date());
		updateCouponRecords.setCreateId("CreateId");
		updateCouponRecords.setCreateDate(new Date());

		couponRecordsService.updateCouponRecords(updateCouponRecords );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找IM含敏感词聊天记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findCouponRecords() throws TsfaServiceException{
		FindCouponRecords findCouponRecords = new FindCouponRecords();
		findCouponRecords.setCode("614516775989101727");
		couponRecordsService.findCouponRecords(findCouponRecords);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找IM含敏感词聊天记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findCouponRecordsPage() throws TsfaServiceException{
		FindCouponRecordsPage findCouponRecordsPage = new FindCouponRecordsPage();
		findCouponRecordsPage.setMerchantNo("87b828cab9f64d37b2eacc4ce5cc0eab");
		Page<FindCouponRecordsPageReturn> page = couponRecordsService.findCouponRecordsPage(findCouponRecordsPage);
		Assert.assertNotNull(page);
		System.out.println(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除IM含敏感词聊天记录表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void delCouponRecords() throws TsfaServiceException{
		DelCouponRecords delCouponRecords = new DelCouponRecords();
		delCouponRecords.setCode("614516775989101727");
		Assert.assertNotNull(couponRecordsService.delCouponRecords(delCouponRecords));
		
	}
	
	
}
