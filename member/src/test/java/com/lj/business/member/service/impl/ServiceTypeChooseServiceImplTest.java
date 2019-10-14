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
import com.lj.business.member.dto.service.typechoose.AddServiceTypeChoose;
import com.lj.business.member.dto.service.typechoose.FindServiceTypeChoose;
import com.lj.business.member.dto.service.typechoose.FindServiceTypeChoosePage;
import com.lj.business.member.dto.service.typechoose.FindServiceTypeChoosePageReturn;
import com.lj.business.member.dto.service.typechoose.UpdateServiceTypeChoose;
import com.lj.business.member.service.IServiceTypeChooseService;


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
public class ServiceTypeChooseServiceImplTest extends SpringTestCase{

	@Resource
	private  IServiceTypeChooseService serviceTypeChooseService;



	/**
	 * 
	 *
	 * 方法说明：添加服务类型选择表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void addServiceTypeChoose() throws TsfaServiceException{
		AddServiceTypeChoose addServiceTypeChoose = new AddServiceTypeChoose();
		//add数据录入
		addServiceTypeChoose.setMerchantNo("MerchantNo");
		addServiceTypeChoose.setMerchantName("MerchantName");
//		addServiceTypeChoose.setShopNo("ShopNo");
//		addServiceTypeChoose.setShopName("ShopName");
		addServiceTypeChoose.setServiceCode("ServiceCode");
		addServiceTypeChoose.setServiceType("ServiceType");
		addServiceTypeChoose.setServiceName("ServiceName");
		addServiceTypeChoose.setShowIndex(1);
		addServiceTypeChoose.setCreateId("CreateId");
		addServiceTypeChoose.setRemark("Remark");
		addServiceTypeChoose.setRemark2("Remark2");
		addServiceTypeChoose.setRemark3("Remark3");
		addServiceTypeChoose.setRemark4("Remark4");
		
		Assert.assertNotNull(serviceTypeChooseService.addServiceTypeChoose(addServiceTypeChoose ));
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改服务类型选择表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void updateServiceTypeChoose() throws TsfaServiceException{
		UpdateServiceTypeChoose updateServiceTypeChoose = new UpdateServiceTypeChoose();
		//update数据录入
		updateServiceTypeChoose.setCode("0f2472bbba554cbf9cc5d0e5b6c0e557");
		updateServiceTypeChoose.setMerchantNo("MerchantNo");
		updateServiceTypeChoose.setMerchantName("MerchantName");
//		updateServiceTypeChoose.setShopNo("ShopNo");
//		updateServiceTypeChoose.setShopName("ShopName");
		updateServiceTypeChoose.setServiceCode("ServiceCode");
		updateServiceTypeChoose.setServiceType("ServiceType");
		updateServiceTypeChoose.setServiceName("ServiceName");
		updateServiceTypeChoose.setShowIndex(1);
		updateServiceTypeChoose.setCreateId("CreateId");
		updateServiceTypeChoose.setCreateDate(new Date());
		updateServiceTypeChoose.setRemark("Remark");
		updateServiceTypeChoose.setRemark2("Remark2");
		updateServiceTypeChoose.setRemark3("Remark3");
		updateServiceTypeChoose.setRemark4("Remark4");

		serviceTypeChooseService.updateServiceTypeChoose(updateServiceTypeChoose );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找服务类型选择表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findServiceTypeChoose() throws TsfaServiceException{
		FindServiceTypeChoose findServiceTypeChoose = new FindServiceTypeChoose();
		findServiceTypeChoose.setCode("0f2472bbba554cbf9cc5d0e5b6c0e557");
		serviceTypeChooseService.findServiceTypeChoose(findServiceTypeChoose);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找服务类型选择表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findServiceTypeChoosePage() throws TsfaServiceException{
		FindServiceTypeChoosePage findServiceTypeChoosePage = new FindServiceTypeChoosePage();
		Page<FindServiceTypeChoosePageReturn> page = serviceTypeChooseService.findServiceTypeChoosePage(findServiceTypeChoosePage);
		Assert.assertNotNull(page);
		
	}
	
	
}
