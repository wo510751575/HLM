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
import com.lj.business.member.dto.service.product.AddServiceProduct;
import com.lj.business.member.dto.service.product.FindServiceProduct;
import com.lj.business.member.dto.service.product.FindServiceProductPage;
import com.lj.business.member.dto.service.product.FindServiceProductPageReturn;
import com.lj.business.member.dto.service.product.UpdateServiceProduct;
import com.lj.business.member.service.IServiceProductService;


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
public class ServiceProductServiceImplTest extends SpringTestCase{

	@Resource
	private IServiceProductService serviceProductService;



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
	public void addServiceProduct() throws TsfaServiceException{
		AddServiceProduct addServiceProduct = new AddServiceProduct();
		//add数据录入
		addServiceProduct.setCode("Code");
		addServiceProduct.setProductName("ProductName");
//		addServiceProduct.setShopNo("ShopNo");
//		addServiceProduct.setShopName("ShopName");
		addServiceProduct.setMerchantNo("MerchantNo");
		addServiceProduct.setMerchantName("MerchantName");
		addServiceProduct.setKeywords("Keywords");
		addServiceProduct.setDescription("Description");
		addServiceProduct.setCoverAddr("CoverAddr");
		addServiceProduct.setImgAddr("ImgAddr");
		addServiceProduct.setCreateId("CreateId");
		addServiceProduct.setRemark("Remark");
		addServiceProduct.setRemark2("Remark2");
		addServiceProduct.setRemark3("Remark3");
		addServiceProduct.setRemark4("Remark4");
		
		Assert.assertNotNull(serviceProductService.addServiceProduct(addServiceProduct ));
		
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
	public void updateServiceProduct() throws TsfaServiceException{
		UpdateServiceProduct updateServiceProduct = new UpdateServiceProduct();
		//update数据录入
		updateServiceProduct.setCode("Code");
		updateServiceProduct.setProductName("ProductName");
//		updateServiceProduct.setShopNo("ShopNo");
//		updateServiceProduct.setShopName("ShopName");
		updateServiceProduct.setMerchantNo("MerchantNo");
		updateServiceProduct.setMerchantName("MerchantName");
		updateServiceProduct.setKeywords("Keywords");
		updateServiceProduct.setDescription("Description");
		updateServiceProduct.setCoverAddr("CoverAddr");
		updateServiceProduct.setImgAddr("ImgAddr");
		updateServiceProduct.setCreateDate(new Date());
		updateServiceProduct.setRemark("Remark");
		updateServiceProduct.setRemark2("Remark2");
		updateServiceProduct.setRemark3("Remark3");
		updateServiceProduct.setRemark4("Remark4");

		serviceProductService.updateServiceProduct(updateServiceProduct );
		
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
	public void findServiceProduct() throws TsfaServiceException{
		FindServiceProduct findServiceProduct = new FindServiceProduct();
		findServiceProduct.setCode("");
		serviceProductService.findServiceProduct(findServiceProduct);
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
	public void findServiceProductPage() throws TsfaServiceException{
		FindServiceProductPage findServiceProductPage = new FindServiceProductPage();
		Page<FindServiceProductPageReturn> page = serviceProductService.findServiceProductPage(findServiceProductPage);
		Assert.assertNotNull(page);
		
	}
}
