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

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.cp.dto.awardVeidoo.AddAwardVeidoo;
import com.lj.business.cp.dto.awardVeidoo.DelAwardVeidoo;
import com.lj.business.cp.dto.awardVeidoo.FindAwardVeidoo;
import com.lj.business.cp.dto.awardVeidoo.FindAwardVeidooPage;
import com.lj.business.cp.dto.awardVeidoo.FindAwardVeidooPageReturn;
import com.lj.business.cp.dto.awardVeidoo.UpdateAwardVeidoo;
import com.lj.business.cp.service.IAwardVeidooService;
import com.lj.cc.clientintf.IJob;
import com.lj.distributecache.RedisCache;

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
public class AwardVeidooServiceImplTest extends SpringTestCase {

	@Resource
	IAwardVeidooService awardVeidooService;
	
	@Resource
	IJob couponRuleTodayEnableDateJob;
	
	@Resource
	RedisCache redisCache;

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
	public void addAwardVeidoo() throws TsfaServiceException {
/*		AddAwardVeidoo addAwardVeidoo = new AddAwardVeidoo();
		// add数据录入
		addAwardVeidoo.setCode("Code");
		addAwardVeidoo.setMerchantNo("MerchantNo");
		addAwardVeidoo.setMerchantName("MerchantName");
		addAwardVeidoo.setShopNo("ShopNo");
		addAwardVeidoo.setShopName("ShopName");
		addAwardVeidoo.setAwardVeidoo("AwardVeidoo");
		addAwardVeidoo.setUpdateId("UpdateId");
		// addAwardVeidoo.setUpdateDate("UpdateDate");
		addAwardVeidoo.setCreateId("CreateId");
		// addAwardVeidoo.setCreateDate("CreateDate");

		awardVeidooService.addAwardVeidoo(addAwardVeidoo);*/
		double a = 100/15;
		System.out.println(a);

	}
	
	@Test
	public void test(){
		couponRuleTodayEnableDateJob.runJob();
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
	public void updateAwardVeidoo() throws TsfaServiceException {
		UpdateAwardVeidoo updateAwardVeidoo = new UpdateAwardVeidoo();
		// update数据录入
		updateAwardVeidoo.setCode("Code");
		updateAwardVeidoo.setMerchantNo("MerchantNo");
		updateAwardVeidoo.setMerchantName("MerchantName");
//		updateAwardVeidoo.setShopNo("ShopNo");
//		updateAwardVeidoo.setShopName("ShopName");
		updateAwardVeidoo.setAwardVeidoo("AwardVeidoo");
		updateAwardVeidoo.setUpdateId("UpdateId");
		// updateAwardVeidoo.setUpdateDate("UpdateDate");
		updateAwardVeidoo.setCreateId("CreateId");
		// updateAwardVeidoo.setCreateDate("CreateDate");

		awardVeidooService.updateAwardVeidoo(updateAwardVeidoo);

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
	public void findAwardVeidoo() throws TsfaServiceException {
		FindAwardVeidoo findAwardVeidoo = new FindAwardVeidoo();
		findAwardVeidoo.setCode("111");
		awardVeidooService.findAwardVeidoo(findAwardVeidoo);
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
	public void findAwardVeidooPage() throws TsfaServiceException {
		FindAwardVeidooPage findAwardVeidooPage = new FindAwardVeidooPage();
		Page<FindAwardVeidooPageReturn> page = awardVeidooService.findAwardVeidooPage(findAwardVeidooPage);

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
	public void delAwardVeidoo() throws TsfaServiceException {
		DelAwardVeidoo delAwardVeidoo = new DelAwardVeidoo();
		delAwardVeidoo.setCode("111");
		awardVeidooService.delAwardVeidoo(delAwardVeidoo);

	}
	
	@Test
   public void testRedis(){
		JedisPool pool = null;
		 Jedis jedis = null; 
		 String key = null;
		try {
			 pool =redisCache.getPool();
			 jedis = pool.getResource();
			 key = jedis.set("6628", "336");
			 
		} catch (Exception e) {
		   
		}finally{
			redisCache.returnResource(pool, jedis);
		}
		String str = redisCache.get("6628");
	    System.out.println(str);
	   
   }

}
