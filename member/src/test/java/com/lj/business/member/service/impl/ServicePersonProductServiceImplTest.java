package com.lj.business.member.service.impl;

/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市扬恩科技 License, Version 1.0 (the "License");
 * 
 */
import java.util.Date;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.lj.base.core.pagination.Page;
import com.lj.base.exception.TsfaServiceException;
import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.business.member.dto.service.personproduct.AddServicePersonProduct;
import com.lj.business.member.dto.service.personproduct.FindServicePersonProduct;
import com.lj.business.member.dto.service.personproduct.FindServicePersonProductPage;
import com.lj.business.member.dto.service.personproduct.FindServicePersonProductPageReturn;
import com.lj.business.member.dto.service.personproduct.UpdateServicePersonProduct;
import com.lj.business.member.service.IServicePersonProductService;


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
public class ServicePersonProductServiceImplTest extends SpringTestCase{

	@Resource
	private IServicePersonProductService servicePersonProductService;



	/**
	 * 
	 *
	 * 方法说明：添加服务人员作品表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void addServicePersonProduct() throws TsfaServiceException{
		AddServicePersonProduct addServicePersonProduct = new AddServicePersonProduct();
		//add数据录入
		addServicePersonProduct.setPersonNo("PersonNo");
		addServicePersonProduct.setPersonName("PersonName");
//		addServicePersonProduct.setShopNo("ShopNo");
//		addServicePersonProduct.setShopName("ShopName");
		addServicePersonProduct.setMerchantNo("MerchantNo");
		addServicePersonProduct.setMerchantName("MerchantName");
		addServicePersonProduct.setPrice(1l);
		addServicePersonProduct.setDescription("Description");
		addServicePersonProduct.setCoverAddr("CoverAddr");
		addServicePersonProduct.setImgAddr("ImgAddr");
		addServicePersonProduct.setCreateId("CreateId");
		addServicePersonProduct.setRemark("Remark");
		addServicePersonProduct.setRemark2("Remark2");
		addServicePersonProduct.setRemark3("Remark3");
		addServicePersonProduct.setRemark4("Remark4");
		
	  servicePersonProductService.addServicePersonProduct(addServicePersonProduct );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改服务人员作品表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void updateServicePersonProduct() throws TsfaServiceException{
		UpdateServicePersonProduct updateServicePersonProduct = new UpdateServicePersonProduct();
		//update数据录入
		updateServicePersonProduct.setCode("Code");
		updateServicePersonProduct.setPersonNo("PersonNo");
		updateServicePersonProduct.setPersonName("PersonName");
//		updateServicePersonProduct.setShopNo("ShopNo");
//		updateServicePersonProduct.setShopName("ShopName");
		updateServicePersonProduct.setMerchantNo("MerchantNo");
		updateServicePersonProduct.setMerchantName("MerchantName");
		updateServicePersonProduct.setPrice(2l);
		updateServicePersonProduct.setDescription("Description");
		updateServicePersonProduct.setCoverAddr("CoverAddr");
		updateServicePersonProduct.setImgAddr("ImgAddr");
		updateServicePersonProduct.setCreateId("CreateId");
		updateServicePersonProduct.setCreateDate(new Date());
		updateServicePersonProduct.setRemark("Remark");
		updateServicePersonProduct.setRemark2("Remark2");
		updateServicePersonProduct.setRemark3("Remark3");
		updateServicePersonProduct.setRemark4("Remark4");

		servicePersonProductService.updateServicePersonProduct(updateServicePersonProduct );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找服务人员作品表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findServicePersonProduct() throws TsfaServiceException{
		FindServicePersonProduct findServicePersonProduct = new FindServicePersonProduct();
		findServicePersonProduct.setCode("");
		servicePersonProductService.findServicePersonProduct(findServicePersonProduct);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找服务人员作品表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2016年10月10日
	 *
	 */
	@Test
	public void findServicePersonProductPage() throws TsfaServiceException{
		FindServicePersonProductPage findServicePersonProductPage = new FindServicePersonProductPage();
		Page<FindServicePersonProductPageReturn> page = servicePersonProductService.findServicePersonProductPage(findServicePersonProductPage);
		
	}
	
	
}
