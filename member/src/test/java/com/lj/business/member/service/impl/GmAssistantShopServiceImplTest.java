package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dao.IGmAssistantShopDao;
import com.lj.business.member.dto.gmAssistantShop.AddGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.DelGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShop;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopPage;
import com.lj.business.member.dto.gmAssistantShop.FindGmAssistantShopPageReturn;
import com.lj.business.member.dto.gmAssistantShop.UpdateGmAssistantShop;
import com.lj.business.member.dto.shopterminal.FindShopTidFromWeb;
import com.lj.business.member.service.IGmAssistantShopService;


/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 罗书明
 * 
 * 
 * CreateDate: 2017-12-2
 */
public class GmAssistantShopServiceImplTest extends SpringTestCase{

	@Resource
	IGmAssistantShopService gmAssistantShopService;
	@Resource
	IGmAssistantShopDao gmAssistantShopDao;



	/**
	 * 
	 *
	 * 方法说明：添加导购助手管理表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明  CreateDate: 2017年12月12日
	 *
	 */
	@Test
	public void addGmAssistantShop() throws TsfaServiceException{
		AddGmAssistantShop addGmAssistantShop = new AddGmAssistantShop();
		//add数据录入
		addGmAssistantShop.setAssistantNo("AssistantNo");
		addGmAssistantShop.setAssistantName("AssistantName");
		addGmAssistantShop.setMerchantNo("MerchantNo");
		addGmAssistantShop.setMerchantName("MerchantName");
//		addGmAssistantShop.setShopNo("ShopNo");
//		addGmAssistantShop.setShopName("ShopName");
		addGmAssistantShop.setCreateId("CreateId");
		addGmAssistantShop.setCreateDate(new Date());
		addGmAssistantShop.setRemark("Remark");
		addGmAssistantShop.setRemark2("Remark2");
		addGmAssistantShop.setRemark3("Remark3");
		addGmAssistantShop.setRemark4("Remark4");
		addGmAssistantShop.setTidCode("111");
		Assert.assertNotNull(gmAssistantShopService.addGmAssistantShop(addGmAssistantShop ));
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改导购助手管理表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明  CreateDate: 2017年12月12日
	 *
	 */
	@Test
	public void updateGmAssistantShop() throws TsfaServiceException{
		UpdateGmAssistantShop updateGmAssistantShop = new UpdateGmAssistantShop();
		//update数据录入
		updateGmAssistantShop.setCode("LJ_7dc289f1c0bc4e08bd486da0562bb727");
		updateGmAssistantShop.setAssistantNo("AssistantNo");
		updateGmAssistantShop.setAssistantName("AssistantName");
		updateGmAssistantShop.setMerchantNo("MerchantNo");
		updateGmAssistantShop.setMerchantName("MerchantName");
//		updateGmAssistantShop.setShopNo("ShopNo");
//		updateGmAssistantShop.setShopName("ShopName");
		updateGmAssistantShop.setCreateId("CreateId");
		updateGmAssistantShop.setCreateDate(new Date());
		updateGmAssistantShop.setRemark("Remark");
		updateGmAssistantShop.setRemark2("Remark2");
		updateGmAssistantShop.setRemark3("Remark3");
		updateGmAssistantShop.setRemark4("Remark4");
		updateGmAssistantShop.setTidCode("222");
		gmAssistantShopService.updateGmAssistantShop(updateGmAssistantShop );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找导购助手管理表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明  CreateDate: 2017年12月12日
	 *
	 */
	@Test
	public void findGmAssistantShop() throws TsfaServiceException{
		FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
		findGmAssistantShop.setCode("LJ_7dc289f1c0bc4e08bd486da0562bb727");
		System.out.println(gmAssistantShopService.findGmAssistantShop(findGmAssistantShop));
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找导购助手管理表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明  CreateDate: 2017年12月12日
	 *
	 */
	@Test
	public void findGmAssistantShopPage() throws TsfaServiceException{
		FindGmAssistantShopPage findGmAssistantShopPage = new FindGmAssistantShopPage();
		findGmAssistantShopPage.setCode("LJ_9fceee92b7c546259c330d5a1e43659c");
		Page<FindGmAssistantShopPageReturn> page = gmAssistantShopService.findGmAssistantShopPage(findGmAssistantShopPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除导购助手管理表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 罗书明  CreateDate: 2017年12月12日
	 *
	 */
	@Test
	public void delGmAssistantShop() throws TsfaServiceException{
		DelGmAssistantShop delGmAssistantShop = new DelGmAssistantShop();
		delGmAssistantShop.setCode("LJ_a28cc36b3f384658a9eb5f4c37d3df6a");
		Assert.assertNotNull(gmAssistantShopService.delGmAssistantShop(delGmAssistantShop));
		
	}

	@Test
	public void findShopNoFromWeb() throws TsfaServiceException{
		FindShopTidFromWeb findShopTidFromWeb = new FindShopTidFromWeb();
		findShopTidFromWeb.setMerchantNo("2169d1820e254e86b110dff73e1bd58f");
//		findShopTidFromWeb.setProvinceCode("104"); //关联shop省市区查询
		System.out.println(gmAssistantShopDao.findShopNoFromWeb(findShopTidFromWeb));
	}

	@Test
	public void findGmAssistantShopList() throws TsfaServiceException{
		FindGmAssistantShop findGmAssistantShop = new FindGmAssistantShop();
		findGmAssistantShop.setAssistantNo("111");
//		findGmAssistantShop.setShopNo("222");
		findGmAssistantShop.setTidCode("333");
		System.out.println(gmAssistantShopDao.findGmAssistantShopList(findGmAssistantShop));
	}
	
	
}
