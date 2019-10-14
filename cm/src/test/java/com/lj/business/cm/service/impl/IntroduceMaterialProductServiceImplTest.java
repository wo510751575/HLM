package com.lj.business.cm.service.impl;

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
import com.lj.business.cm.dto.introduceMaterialProduct.AddIntroduceMaterialProduct;
import com.lj.business.cm.dto.introduceMaterialProduct.DelIntroduceMaterialProduct;
import com.lj.business.cm.dto.introduceMaterialProduct.FindIntroduceMaterialProduct;
import com.lj.business.cm.dto.introduceMaterialProduct.FindIntroduceMaterialProductPage;
import com.lj.business.cm.dto.introduceMaterialProduct.FindIntroduceMaterialProductPageReturn;
import com.lj.business.cm.dto.introduceMaterialProduct.UpdateIntroduceMaterialProduct;
import com.lj.business.cm.service.IIntroduceMaterialProductService;


/**
 * 类说明：测试类
 * 
 * 
 * <p>
 * 详细描述：.
 *
 * @author 冯辉
 * 
 * 
 * CreateDate: 2017-06-14
 */
public class IntroduceMaterialProductServiceImplTest extends SpringTestCase{

	@Resource
	IIntroduceMaterialProductService introduceMaterialProductService;



	/**
	 * 
	 *
	 * 方法说明：添加个人素材介绍产品关联表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void addIntroduceMaterialProduct() throws TsfaServiceException{
		AddIntroduceMaterialProduct addIntroduceMaterialProduct = new AddIntroduceMaterialProduct();
		//add数据录入
		addIntroduceMaterialProduct.setCode("Code");
		addIntroduceMaterialProduct.setMaterialCode("MaterialCode");
		addIntroduceMaterialProduct.setBomName("BomName");
		addIntroduceMaterialProduct.setBomAddress("BomAddress");
		addIntroduceMaterialProduct.setCreateId("CreateId");
		addIntroduceMaterialProduct.setCreateDate(new Date());
		addIntroduceMaterialProduct.setRemark("Remark");
		addIntroduceMaterialProduct.setRemark2("Remark2");
		addIntroduceMaterialProduct.setRemark3("Remark3");
		addIntroduceMaterialProduct.setRemark4("Remark4");
		
		introduceMaterialProductService.addIntroduceMaterialProduct(addIntroduceMaterialProduct );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：修改个人素材介绍产品关联表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void updateIntroduceMaterialProduct() throws TsfaServiceException{
		UpdateIntroduceMaterialProduct updateIntroduceMaterialProduct = new UpdateIntroduceMaterialProduct();
		//update数据录入
		updateIntroduceMaterialProduct.setCode("Code");
		updateIntroduceMaterialProduct.setMaterialCode("MaterialCode");
		updateIntroduceMaterialProduct.setBomName("BomName");
		updateIntroduceMaterialProduct.setBomAddress("BomAddress");
		updateIntroduceMaterialProduct.setCreateId("CreateId");
		updateIntroduceMaterialProduct.setCreateDate(new Date());
		updateIntroduceMaterialProduct.setRemark("Remark");
		updateIntroduceMaterialProduct.setRemark2("Remark2");
		updateIntroduceMaterialProduct.setRemark3("Remark3");
		updateIntroduceMaterialProduct.setRemark4("Remark4");

		introduceMaterialProductService.updateIntroduceMaterialProduct(updateIntroduceMaterialProduct );
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找个人素材介绍产品关联表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findIntroduceMaterialProduct() throws TsfaServiceException{
		FindIntroduceMaterialProduct findIntroduceMaterialProduct = new FindIntroduceMaterialProduct();
		findIntroduceMaterialProduct.setCode("111");
		introduceMaterialProductService.findIntroduceMaterialProduct(findIntroduceMaterialProduct);
	}
	
	/**
	 * 
	 *
	 * 方法说明：查找个人素材介绍产品关联表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void findIntroduceMaterialProductPage() throws TsfaServiceException{
		FindIntroduceMaterialProductPage findIntroduceMaterialProductPage = new FindIntroduceMaterialProductPage();
		Page<FindIntroduceMaterialProductPageReturn> page = introduceMaterialProductService.findIntroduceMaterialProductPage(findIntroduceMaterialProductPage);
		Assert.assertNotNull(page);
		
	}
	
	/**
	 * 
	 *
	 * 方法说明：删除个人素材介绍产品关联表信息
	 *
	 * @throws TsfaServiceException
	 *
	 * @author 彭阳 CreateDate: 2017年07月10日
	 *
	 */
	@Test
	public void delIntroduceMaterialProduct() throws TsfaServiceException{
		DelIntroduceMaterialProduct delIntroduceMaterialProduct = new DelIntroduceMaterialProduct();
		delIntroduceMaterialProduct.setCode("111");
		introduceMaterialProductService.delIntroduceMaterialProduct(delIntroduceMaterialProduct);
		
	}
	
	
}
